package com.poly.da.service;

import com.poly.da.dao.KhachHangDAO;
import com.poly.da.entity.KhachHang;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

@Service ("khachHangDetailsService" )
public class KhachHangDetailsService implements UserDetailsService {

    private final KhachHangDAO khachHangDAO;

    public KhachHangDetailsService(KhachHangDAO khachHangDAO) {
        this.khachHangDAO = khachHangDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        KhachHang khachHang = khachHangDAO.findByEmail(email);

      
        if (khachHang == null) {
            throw new UsernameNotFoundException("Không tìm thấy Khách Hàng với email: " + email);
        }

     
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_KHACHHANG") 
        );


        return new org.springframework.security.core.userdetails.User(
                khachHang.getEmail(),
                khachHang.getMatKhauHash(), 
                authorities
        );
    }
}