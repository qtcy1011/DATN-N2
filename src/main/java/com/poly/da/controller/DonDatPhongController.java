package com.poly.da.controller;

import com.poly.da.entity.DonDatPhong;
import com.poly.da.repository.DonDatPhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DonDatPhongController {

    @Autowired
    private DonDatPhongRepository donDatPhongRepo;


    // ============================
    // DANH SÁCH
    // ============================
    @GetMapping("/manage_bookings")
    public String manageOrders(@RequestParam(value = "keyword", required = false) String keyword,
                               Model model) {

        List<DonDatPhong> donDatPhongList;

        if (keyword != null && !keyword.isEmpty()) {
            donDatPhongList =
                    donDatPhongRepo.findByMaDonDatPhongContainingOrMaKhachHangContaining(keyword, keyword);
        } else {
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
    public String saveBooking(@ModelAttribute("donDatPhong") DonDatPhong donDatPhong) {
        donDatPhongRepo.save(donDatPhong);
        return "redirect:/admin/manage_bookings";
    }


    // ============================
    // SỬA ĐƠN
    // ============================
    @GetMapping("/edit_bookings/{id}")
    public String editBookingsForm(@PathVariable("id") String id, Model model) {

        DonDatPhong don = donDatPhongRepo.findById(id).orElse(null);

        if (don == null) return "redirect:/admin/manage_bookings";

        model.addAttribute("donDatPhong", don);
        return "admin/edit_bookings";
    }

    @PostMapping("/edit_bookings")
    public String updateBookings(@ModelAttribute("donDatPhong") DonDatPhong donDatPhong) {

        donDatPhongRepo.save(donDatPhong);
        return "redirect:/admin/manage_bookings";
    }


    // ============================
    // XÓA ĐƠN
    // ============================
    @GetMapping("/delete_bookings/{id}")
    public String deleteBooking(@PathVariable("id") String id) {

        donDatPhongRepo.deleteById(id);
        return "redirect:/admin/manage_bookings";
    }
}
