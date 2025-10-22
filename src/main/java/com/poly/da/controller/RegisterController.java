package com.poly.da.controller;

import com.poly.da.entity.KhachHang;
import com.poly.da.service.IKhachHangService; // Dùng Service đã implement logic mã hóa
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final IKhachHangService khService;


    public RegisterController(IKhachHangService khService) {
        this.khService = khService;
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "register"; 
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("khachHang") KhachHang kh, 
                               @RequestParam("matKhau") String matKhau,
                               @RequestParam("xacNhanMatKhau") String xacNhanMatKhau, 
                               Model model) {
        

        if (!matKhau.equals(xacNhanMatKhau)) {
            model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp.");
            return "register"; 
        }

           kh.setMatKhauHash(matKhau); 
        
          boolean success = khService.register(kh);

        if (success) {
        
            return "redirect:/login?registered";
        } else {
             model.addAttribute("error", "Đăng ký thất bại. Email có thể đã được sử dụng.");
            return "register";
        }
    }
}