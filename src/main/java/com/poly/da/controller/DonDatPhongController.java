package com.poly.da.controller;

import com.poly.da.entity.DonDatPhong;
import com.poly.da.repository.DonDatPhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class DonDatPhongController {

    @Autowired
    private DonDatPhongRepository donDatPhongRepo;


    // ============================
    // DANH SÁCH & TÌM KIẾM
    // ============================
    @GetMapping("/manage_bookings")
    public String manageOrders(@RequestParam(value = "keyword", required = false) String keyword,
                               Model model) {

        List<DonDatPhong> donDatPhongList;

        // ⭐️ Giải quyết xung đột: Sử dụng một logic tìm kiếm rõ ràng
        if (keyword != null && !keyword.isEmpty()) {
            // Sử dụng một phương thức tìm kiếm linh hoạt hơn (giả sử bạn đã định nghĩa nó trong Repository)
            // Nếu bạn có @Query searchDonDatPhongByKeyword, thì nên dùng nó.
            
            // Tạm thời sử dụng logic tìm kiếm theo ID đơn hàng và ID khách hàng
            // (Cần có phương thức này trong DonDatPhongRepository nếu chưa có)
            try {
                 // Thử tìm kiếm theo số nếu người dùng nhập số ID đơn hàng
                Integer id = Integer.parseInt(keyword.trim());
                donDatPhongList = donDatPhongRepo.findByMaDonDatPhongOrMaKhachHang(id, id);
            } catch (NumberFormatException e) {
                // Nếu không phải là số (có thể là trạng thái đơn, tên khách, v.v.)
                // Giả định bạn có một phương thức @Query linh hoạt
                // Thay thế bằng phương thức thực tế của bạn, ví dụ:
                // donDatPhongList = donDatPhongRepo.searchFlexible(keyword);
                donDatPhongList = donDatPhongRepo.findAll(); // Tạm thời trả về tất cả nếu không phải là ID số
            }
        } else {
            // Trường hợp không có từ khóa
            donDatPhongList = donDatPhongRepo.findAll();
        }

        model.addAttribute("donDatPhongList", donDatPhongList);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "admin/manage_bookings";
    }


    // ============================
    // THÊM ĐƠN
    // ============================
    @GetMapping("/add_bookings")
    public String addBookingsForm(Model model) {
        model.addAttribute("donDatPhong", new DonDatPhong());
        return "admin/add_bookings";
    }

    @PostMapping("/add_bookings")
    public String saveBooking(@ModelAttribute("donDatPhong") DonDatPhong donDatPhong, RedirectAttributes redirectAttributes) {
        try {
            donDatPhongRepo.save(donDatPhong);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm đơn đặt phòng thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi thêm đơn đặt phòng.");
        }
        return "redirect:/admin/manage_bookings";
    }


    // ============================
    // SỬA ĐƠN
    // ============================
    @GetMapping("/edit_bookings/{id}")
    // ⭐️ Chuyển kiểu ID từ String sang Integer cho phù hợp với Entity
    public String editBookingsForm(@PathVariable("id") Integer id, Model model) {

        Optional<DonDatPhong> opt = donDatPhongRepo.findById(id);

        if (opt.isEmpty()) return "redirect:/admin/manage_bookings";

        model.addAttribute("donDatPhong", opt.get());
        return "admin/edit_bookings";
    }

    @PostMapping("/edit_bookings")
    public String updateBookings(@ModelAttribute("donDatPhong") DonDatPhong donDatPhong, RedirectAttributes redirectAttributes) {

        try {
            donDatPhongRepo.save(donDatPhong);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật đơn đặt phòng thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật đơn đặt phòng.");
        }
        return "redirect:/admin/manage_bookings";
    }


    // ============================
    // XÓA ĐƠN
    // ============================
    @GetMapping("/delete_bookings/{id}")
    // ⭐️ Chuyển kiểu ID từ String sang Integer cho phù hợp với Entity
    public String deleteBooking(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

        try {
            donDatPhongRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa đơn đặt phòng thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa đơn đặt phòng.");
        }
        return "redirect:/admin/manage_bookings";
    }
}