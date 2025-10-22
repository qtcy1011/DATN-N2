package com.poly.da.service;

import com.poly.da.dao.NhanVienDAO;
import com.poly.da.entity.NhanVien;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Bắt buộc phải có @Service để Spring quản lý
public class NhanVienServiceImpl implements INhanVienService {
    
    // Sử dụng 'final' và Constructor Injection thay vì khởi tạo thủ công
    private final NhanVienDAO nhanVienDao;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder của Spring Security

    // Constructor Injection: Spring tự động inject các dependencies
    public NhanVienServiceImpl(NhanVienDAO nhanVienDao, PasswordEncoder passwordEncoder) {
        this.nhanVienDao = nhanVienDao;
        this.passwordEncoder = passwordEncoder;
    }

    // PHƯƠNG THỨC LOGIN ĐÃ ĐƯỢC XỬ LÝ BỞI SPRING SECURITY
    // Trong môi trường Spring Security, phương thức này không còn cần thiết cho việc xác thực.


    @Override
    public boolean createNhanVien(NhanVien nv) {
      
        // 1. Kiểm tra email đã tồn tại
        if (nhanVienDao.findByEmail(nv.getEmail()) != null) {
            return false;
        }
        
        // 2. MÃ HÓA MẬT KHẨU bằng PasswordEncoder của Spring
        String passwordPlaintext = nv.getMatKhauHash(); 
        
        // Sử dụng passwordEncoder.encode()
        String encodedPassword = passwordEncoder.encode(passwordPlaintext);
        
        nv.setMatKhauHash(encodedPassword); // Lưu mật khẩu đã mã hóa vào entity
        
        // Đặt trạng thái mặc định (nếu cần)
        if (nv.getTrangThai() == null) {
            nv.setTrangThai("Active");
        }

        // 3. Lưu vào CSDL
        return nhanVienDao.insert(nv);
    }

	@Override
	public boolean updateNhanVien(NhanVien nv) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNhanVien(String maNv) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NhanVien findById(String maNv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NhanVien> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}