package com.poly.da.controller;

import com.poly.da.entity.NhanVien;
import com.poly.da.entity.KhachHang;
import com.poly.da.repository.NhanVienRepository;
import com.poly.da.repository.KhachHangRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class LoginSuccessController {

    private static final String TEMP_HASH = "$2a$10$VWnG6QdZroBSqIsE1lyHgO9WcOGSsbM30F5tg/w/lrL68J3fVHX76";

    private final NhanVienRepository nhanVienRepo;
    private final KhachHangRepository khachHangRepo;

    public LoginSuccessController(NhanVienRepository nhanVienRepo, KhachHangRepository khachHangRepo) {
        this.nhanVienRepo = nhanVienRepo;
        this.khachHangRepo = khachHangRepo;
    }

    @GetMapping("/login-success-handler")
    public String handleSuccessfulLogin(Principal principal) {
        if (principal == null) {
            return "redirect:/login"; 
        }
        
        String email = principal.getName();


        NhanVien nv = nhanVienRepo.findByEmail(email).orElse(null);
        if (nv != null) {
            if (nv.getMatKhauHash().equals(TEMP_HASH)) { 

                return "redirect:/force-change-password"; 
            }
  
            if ("ADMIN".equals(nv.getVaiTro())) {
                return "redirect:/admin";
            } else {
                return "redirect:/nhanvien"; 
            }
        }

     
        KhachHang kh = khachHangRepo.findByEmail(email).orElse(null);
        if (kh != null) {
            if (kh.getMatKhauHash().equals(TEMP_HASH)) { 
          
                return "redirect:/force-change-password"; 
            }
         
            return "redirect:/"; 
        }

       
        return "redirect:/logout"; 
    }
}