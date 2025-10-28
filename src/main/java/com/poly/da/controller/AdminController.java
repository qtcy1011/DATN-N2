package com.poly.da.controller;

import com.poly.da.entity.KhachHang;
import com.poly.da.service.IKhachHangService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final IKhachHangService khachHangService;

    //Dùng constructor injection để Spring tự tiêm service vào
    public AdminController(IKhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @GetMapping("/index")
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping("/manage_rooms")
    public String manageRooms() {
        return "admin/manage_rooms";
    }

    @GetMapping("/manage_customers")
    public String manageCustomers(Model model) {
        List<KhachHang> khachHangList = khachHangService.findAll(); // Lấy dữ liệu từ DB
        model.addAttribute("khachHangList", khachHangList); // Gửi sang HTML
        return "admin/manage_customers";
    }
}
