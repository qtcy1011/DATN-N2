package com.poly.da.controller;

import com.poly.da.entity.KhachSan;
import com.poly.da.entity.DanhGia;
import com.poly.da.entity.LoaiPhong;
import com.poly.da.entity.KhachHang;
import com.poly.da.repository.KhachSanRepository;
import com.poly.da.repository.DanhGiaRepository;
import com.poly.da.service.RatingService;
import com.poly.da.service.LoaiPhongService;

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
}
