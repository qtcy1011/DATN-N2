package com.poly.da.service;

import com.poly.da.dao.NhanVienDAO;
import com.poly.da.entity.NhanVien;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

@Service ("nhanVienDetailsService")
public class NhanVienDetailsService implements UserDetailsService {

    private final NhanVienDAO nhanVienDAO;

    public NhanVienDetailsService(NhanVienDAO nhanVienDAO) {
        this.nhanVienDAO = nhanVienDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        NhanVien nhanVien = nhanVienDAO.findByEmail(email);

        if (nhanVien == null) {
        	throw new UsernameNotFoundException("Không tìm thấy Nhân viên với email: " + email);
        }

                String roleWithPrefix = "ROLE_" + nhanVien.getVaiTro(); 
        return new User(
            nhanVien.getEmail(), 
            nhanVien.getMatKhauHash(),
            Collections.singletonList(new SimpleGrantedAuthority(roleWithPrefix)) 
        );
    }
}