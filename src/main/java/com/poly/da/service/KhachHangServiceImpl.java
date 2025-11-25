package com.poly.da.service;

import com.poly.da.dao.KhachHangDAO;
import com.poly.da.entity.KhachHang;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements IKhachHangService {
    
    private final KhachHangDAO khachHangDao;
    private final PasswordEncoder passwordEncoder; 

    public KhachHangServiceImpl(KhachHangDAO khachHangDao, PasswordEncoder passwordEncoder) {
        this.khachHangDao = khachHangDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<KhachHang> search(String keyword) {
        return khachHangDao.search(keyword);
    }

    @Override
    public boolean register(KhachHang kh) {

        if (khachHangDao.findByEmail(kh.getEmail()) != null) {
            return false; 
        }
        

        String encodedPassword = passwordEncoder.encode(kh.getMatKhauHash());
        kh.setMatKhauHash(encodedPassword); 

        return khachHangDao.insert(kh);
    }
    
    @Override
    public boolean updateKhachHang(KhachHang kh) {
        KhachHang existingKh = khachHangDao.findById(kh.getMaKhachHang());
        if (existingKh == null) return false;

        // Giữ lại mật khẩu đã mã hóa cũ nếu form không gửi mật khẩu mới
        if (kh.getMatKhauHash() == null || kh.getMatKhauHash().isEmpty()) {
            kh.setMatKhauHash(existingKh.getMatKhauHash());
        } else {
            // Nếu có mật khẩu mới, mã hóa nó
            String encodedPassword = passwordEncoder.encode(kh.getMatKhauHash());
            kh.setMatKhauHash(encodedPassword);
        }
        
        return khachHangDao.update(kh);
    }
    
    @Override
    public boolean deleteKhachHang(int maKh) { // <-- ĐÃ SỬA: Thay đổi String thành int
        // Có thể là xóa mềm hoặc xóa cứng
        return khachHangDao.delete(maKh); // Gọi phương thức delete(int) của DAO
    }
    
    @Override
    public KhachHang findById(int maKh) {
        return khachHangDao.findById(maKh);
    }
    
    @Override
    public List<KhachHang> findAll() {
        return khachHangDao.findAll();
    }
}