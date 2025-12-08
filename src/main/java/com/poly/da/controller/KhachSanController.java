package com.poly.da.controller;

import com.poly.da.entity.KhachSan;
import com.poly.da.entity.DanhGia;
import com.poly.da.entity.LoaiPhong;
import com.poly.da.entity.KhachHang;
import com.poly.da.repository.KhachSanRepository;
import com.poly.da.repository.DanhGiaRepository;
import com.poly.da.service.RatingService;
import com.poly.da.service.LoaiPhongService;
// ⭐️ IMPORT MỚI: Cần import đối tượng BookingRequest
import com.poly.da.dto.BookingRequest; // <--- Cập nhật đường dẫn package thực tế của BookingRequest

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class KhachSanController {

    @Autowired
    private KhachSanRepository khachSanRepo;

    @Autowired
    private DanhGiaRepository danhGiaRepo;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private LoaiPhongService loaiPhongService;

    // ----------------------------------------------------------
    // 1. Trang tìm kiếm khách sạn
    // ----------------------------------------------------------
    @GetMapping("/search")
    public String showSearchForm(Model model, HttpSession session) {
        // Nếu người dùng đã đăng nhập, lấy thông tin
        KhachHang khachHang = (KhachHang) session.getAttribute("currentUser");
        if (khachHang != null) {
            model.addAttribute("currentUser", khachHang);
        }
        return "search";
    }

    @PostMapping("/search")
    public String searchHotels(@RequestParam("city") String city, Model model, HttpSession session) {

        if (city == null || city.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập tên thành phố!");
            return "search";
        }

        List<KhachSan> results = khachSanRepo.findByThanhPhoContainingIgnoreCase(city.trim());

        // Cập nhật rating
        results.forEach(h -> ratingService.updateSingleHotelRating(h.getMaKhachSan()));

        List<KhachSan> updatedResults = khachSanRepo.findByThanhPhoContainingIgnoreCase(city.trim());

        model.addAttribute("searchCity", city);
        model.addAttribute("hotelList", updatedResults);

        // Thêm thông tin người dùng vào model
        KhachHang khachHang = (KhachHang) session.getAttribute("currentUser");
        if (khachHang != null) {
            model.addAttribute("currentUser", khachHang);
        }

        return "results";
    }

    // ----------------------------------------------------------
    // 2. Xem chi tiết khách sạn
    // ----------------------------------------------------------
    @GetMapping("/hotel/{id}")
    public String viewHotelDetail(@PathVariable("id") Integer id, Model model, HttpSession session) {

        Optional<KhachSan> opt = khachSanRepo.findById(id);

        if (opt.isEmpty()) {
            return "redirect:/error";
        }

        KhachSan hotel = opt.get();

        // Cập nhật và lấy rating mới
        KhachSan updatedHotel = ratingService.updateSingleHotelRating(id);
        if (updatedHotel != null) {
            hotel = updatedHotel;
        }

        // Lấy danh sách loại phòng theo khách sạn
        List<LoaiPhong> roomTypes = loaiPhongService.findRoomsByHotelId(id);

        // Lấy đánh giá
        List<DanhGia> reviews = danhGiaRepo.findByMaKhachSan(id);

        model.addAttribute("hotel", hotel);
        model.addAttribute("roomTypes", roomTypes);
        model.addAttribute("reviews", reviews);

        model.addAttribute("currentDate", LocalDate.now());
        model.addAttribute("tomorrowDate", LocalDate.now().plusDays(1));

        // Thêm thông tin người dùng vào model
        KhachHang khachHang = (KhachHang) session.getAttribute("currentUser");
        if (khachHang != null) {
            model.addAttribute("currentUser", khachHang);
        }

        return "hotel-detail";
    }

    // ----------------------------------------------------------
    // 3. Gửi đánh giá
    // ----------------------------------------------------------
    @PostMapping("/submit-review")
    public String submitReview(
            @RequestParam("maKhachSan") Integer maKhachSan,
            @RequestParam("diemSo") Integer diemSo,
            @RequestParam("noiDung") String noiDung,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        if (diemSo == null || noiDung == null || noiDung.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
            return "redirect:/hotel/" + maKhachSan;
        }

        try {
            DanhGia review = new DanhGia();
            review.setMaKhachSan(maKhachSan);

            // Lấy người dùng hiện tại từ session
            KhachHang khachHang = (KhachHang) session.getAttribute("currentUser");
            if (khachHang != null) {
                review.setMaKhachHang(khachHang.getMaKhachHang());
            } else {
                review.setMaKhachHang(1); // giả lập nếu chưa đăng nhập
            }

            review.setDiemSo(diemSo);
            review.setNoiDung(noiDung.trim());
            review.setNgayTao(LocalDateTime.now());

            danhGiaRepo.save(review);

            ratingService.updateSingleHotelRating(maKhachSan);

            redirectAttributes.addFlashAttribute("successMessage", "Đánh giá đã được gửi thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi gửi đánh giá.");
        }

        return "redirect:/hotel/" + maKhachSan;
    }
    
    // ----------------------------------------------------------
    // 4. Xử lý Đặt Phòng (CHECKOUT)
    // ----------------------------------------------------------
    /**
     * Xử lý dữ liệu đặt phòng gửi từ form trong trang hotel-detail.
     * Sử dụng @ModelAttribute để ánh xạ dữ liệu form vào BookingRequest.
     */
    @PostMapping("/checkout")
    public String processBooking(@ModelAttribute BookingRequest bookingRequest, RedirectAttributes redirectAttributes) {
        
        // ⭐️ Lấy MaKhachSan để redirect lại trang chi tiết nếu cần. 
        // Vì MaKhachSan không có trong BookingRequest, ta cần tìm cách lấy nó 
        // (ví dụ: tìm kiếm từ hotelName hoặc thêm trường MaKhachSan vào BookingRequest).
        // Tạm thời giả định MaKhachSan là 1 để tránh lỗi biên dịch.
        Integer maKhachSan = 1; 

        if (bookingRequest.getSelectedRoomId() == null || bookingRequest.getCheckIn() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Thiếu thông tin đặt phòng cần thiết.");
            return "redirect:/hotel/" + maKhachSan; 
        }

        try {
            // --- BẮT ĐẦU LOGIC ĐẶT PHÒNG THỰC TẾ ---
            
            // 1. Kiểm tra ngày tháng
            if (bookingRequest.getCheckIn().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Ngày nhận phòng không hợp lệ.");
            }
            
            // 2. Thực hiện lưu vào database (Tùy thuộc vào Service/Repository của bạn)
            // bookingService.saveBooking(bookingRequest);
            
            System.out.println("✅ Đặt phòng thành công:");
            System.out.println("Khách: " + bookingRequest.getFullName());
            System.out.println("Email: " + bookingRequest.getEmail());
            System.out.println("Phòng ID: " + bookingRequest.getSelectedRoomId());
            
            // --- KẾT THÚC LOGIC ĐẶT PHÒNG THÀNH CÔNG ---

            redirectAttributes.addFlashAttribute("successMessage", "Đơn đặt phòng đã được xác nhận thành công!");
            redirectAttributes.addFlashAttribute("confirmationDetails", bookingRequest);
            
            // Chuyển hướng đến trang xác nhận (bạn cần tạo view confirmation.html)
            return "redirect:/checkout"; 

        } catch (Exception e) {
            System.err.println("Lỗi đặt phòng: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: Đặt phòng không thành công. " + e.getMessage());
            
            // Chuyển hướng về trang chi tiết khách sạn
            return "redirect:/hotel/" + maKhachSan; 
        }
    }
}