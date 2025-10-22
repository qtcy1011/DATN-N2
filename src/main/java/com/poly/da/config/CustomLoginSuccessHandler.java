package com.poly.da.config;

import com.poly.da.repository.NhanVienRepository;
import com.poly.da.repository.KhachHangRepository;
import com.poly.da.entity.NhanVien;
import com.poly.da.entity.KhachHang;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    // HASH TẠM THỜI MỚI (Từ mật khẩu gốc "temp123")
    private static final String TEMP_HASH = "$2a$10$VWnG6QdZroBSqIsE1lyHgO9WcOGSsbM30F5tg/w/lrL68J3fVHX76";

    private final NhanVienRepository nhanVienRepo;
    private final KhachHangRepository khachHangRepo;

    public CustomLoginSuccessHandler(NhanVienRepository nhanVienRepo, KhachHangRepository khachHangRepo) {
        this.nhanVienRepo = nhanVienRepo;
        this.khachHangRepo = khachHangRepo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

 
        NhanVien nv = nhanVienRepo.findByEmail(email).orElse(null);
        if (nv != null) {
            if (nv.getMatKhauHash().equals(TEMP_HASH)) {
                response.sendRedirect("/force-change-password?type=nv");
                return;
            }

            if ("ADMIN".equalsIgnoreCase(nv.getVaiTro())) {
                response.sendRedirect("/admin/index");
            } else {
                response.sendRedirect("/nhanvien/index");
            }
            return;
        }


        KhachHang kh = khachHangRepo.findByEmail(email).orElse(null);
        if (kh != null) {
            if (kh.getMatKhauHash().equals(TEMP_HASH)) {

                response.sendRedirect("/force-change-password?type=kh");
                return;
            }

            response.sendRedirect("/"); 
            return;
        }
        

        response.sendRedirect("/"); 
    }
}