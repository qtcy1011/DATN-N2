package com.poly.da.service;

import com.poly.da.entity.KhachHang;
import java.util.List;

public interface IKhachHangService {

   
    boolean register(KhachHang kh);

   
    boolean updateKhachHang(KhachHang kh);

   
    boolean deleteKhachHang(int maKh); 

    
    KhachHang findById(int maKh);

   
    List<KhachHang> findAll();
}