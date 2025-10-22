package com.poly.da.service;

import com.poly.da.dao.NhanVienDAO;
import com.poly.da.entity.NhanVien;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; // Dùng PasswordEncoder của Spring Security

@Service
public class NhanVienService {

    private final NhanVienDAO nhanVienDAO;
    private final PasswordEncoder passwordEncoder;

    // Sử dụng Dependency Injection (DI) qua constructor
    public NhanVienService(NhanVienDAO nhanVienDAO, PasswordEncoder passwordEncoder) {
        this.nhanVienDAO = nhanVienDAO;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Đăng ký một nhân viên mới.
     * @param nv Đối tượng NhanVien.
     * @return true nếu đăng ký thành công.
     */
    public boolean registerNewNhanVien(NhanVien nv) {
        // 1. Kiểm tra Email đã tồn tại chưa (Tùy chọn)
        if (nhanVienDAO.findByEmail(nv.getEmail()) != null) {
            return false; // Email đã tồn tại
        }

        // 2. Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(nv.getMatKhauHash());
        nv.setMatKhauHash(encodedPassword);

        // 3. Đặt vai trò và trạng thái mặc định
        nv.setVaiTro("NHANVIEN"); // Hoặc "USER" tùy quy tắc của bạn
        nv.setTrangThai("ACTIVE");

        // 4. Lưu vào CSDL
        return nhanVienDAO.insert(nv);
    }
}