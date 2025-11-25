package com.poly.da.controller;

import com.poly.da.entity.KhachHang;
import com.poly.da.entity.RoomType;
import com.poly.da.repository.RoomTypeRepository;
import com.poly.da.service.IKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final IKhachHangService khachHangService;

    @Autowired
    private RoomTypeRepository roomTypeRepo;

    @Autowired
    public AdminController(IKhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @GetMapping("/index")
    public String adminIndex() {
        return "admin/index";
    }

    // --------------------- LOẠI PHÒNG ---------------------

    // Hiển thị danh sách loại phòng
    @GetMapping("/manage_rooms")
    public String showRooms(Model model) {
        model.addAttribute("rooms", roomTypeRepo.findAll());
        return "admin/manage_rooms";
    }

    // Form chỉnh sửa loại phòng
    @GetMapping("/edit_room/{maLoaiPhong}")
    public String editRoom(@PathVariable("maLoaiPhong") String maLoaiPhong, Model model) {
        Optional<RoomType> roomOpt = roomTypeRepo.findById(maLoaiPhong);
        if (roomOpt.isPresent()) {
            model.addAttribute("room", roomOpt.get());
            return "admin/edit_room";
        } else {
            return "redirect:/admin/manage_rooms";
        }
    }

    // Cập nhật loại phòng sau khi chỉnh sửa
    @PostMapping("/update_room")
    public String updateRoom(@ModelAttribute("room") RoomType room) {
        roomTypeRepo.save(room);
        return "redirect:/admin/manage_rooms";
    }

    // Xóa loại phòng
    @GetMapping("/delete_room/{maLoaiPhong}")
    public String deleteRoom(@PathVariable("maLoaiPhong") String maLoaiPhong) {
        roomTypeRepo.deleteById(maLoaiPhong);
        return "redirect:/admin/manage_rooms";
    }

    // --------------------- KHÁCH HÀNG ---------------------

    @GetMapping("/manage_customers")
    public String manageCustomers(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        List<KhachHang> khachHangList;

        if (keyword != null && !keyword.trim().isEmpty()) {
            khachHangList = khachHangService.search(keyword.trim());
        } else {
            khachHangList = khachHangService.findAll();
        }

        model.addAttribute("khachHangList", khachHangList);
        model.addAttribute("keyword", keyword);

        return "admin/manage_customers";
    }


    @GetMapping("/delete_customer/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        khachHangService.deleteKhachHang(id);
        return "redirect:/admin/manage_customers";
    }

    @GetMapping("/edit_customer/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        KhachHang kh = khachHangService.findById(id);
        if (kh != null) {
            model.addAttribute("customer", kh);
            return "admin/edit_customer";
        } else {
            return "redirect:/admin/manage_customers";
        }
    }

    @PostMapping("/update_customer")
    public String updateCustomer(@ModelAttribute("customer") KhachHang kh) {
        khachHangService.updateKhachHang(kh);
        return "redirect:/admin/manage_customers";
    }
}
