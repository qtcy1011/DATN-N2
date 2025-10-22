package com.poly.da.controller;

import com.poly.da.entity.NhanVien;
import com.poly.da.entity.KhachHang;
import com.poly.da.repository.NhanVienRepository;
import com.poly.da.repository.KhachHangRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.Optional;

@Controller
public class ChangePasswordController {

    private final PasswordEncoder passwordEncoder;
    private final NhanVienRepository nhanVienRepo;
    private final KhachHangRepository khachHangRepo;

    public ChangePasswordController(PasswordEncoder passwordEncoder, NhanVienRepository nhanVienRepo, KhachHangRepository khachHangRepo) {
        this.passwordEncoder = passwordEncoder;
        this.nhanVienRepo = nhanVienRepo;
        this.khachHangRepo = khachHangRepo;
    }

    @GetMapping("/force-change-password")
    public String showChangePasswordPage(@RequestParam(name = "type") String userType, Model model) {
        model.addAttribute("userType", userType);
        return "force_change_password"; 
    }


    @PostMapping("/update-password")
    public String updateNewPassword(@RequestParam String newPassword, 
                                    @RequestParam String confirmPassword,
                                    @RequestParam String userType,
                                    Principal principal,
                                    Model model) {
                                        
        if (principal == null) {
            return "redirect:/login"; 
        }

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp.");
            model.addAttribute("userType", userType);
            return "force_change_password";
        }

        String email = principal.getName();
        String newHashedPassword = passwordEncoder.encode(newPassword);

 
        if ("nv".equals(userType)) {
            Optional<NhanVien> nvOpt = nhanVienRepo.findByEmail(email);
            if (nvOpt.isPresent()) {
                NhanVien nv = nvOpt.get();
                nv.setMatKhauHash(newHashedPassword);
                nhanVienRepo.save(nv);
                return "redirect:/admin/index"; 
            }
        } else if ("kh".equals(userType)) {
            Optional<KhachHang> khOpt = khachHangRepo.findByEmail(email);
            if (khOpt.isPresent()) {
                KhachHang kh = khOpt.get();
                kh.setMatKhauHash(newHashedPassword);
                khachHangRepo.save(kh);
                return "redirect:/"; 
            }
        }

      
        return "redirect:/logout"; 
    }
}