package com.poly.da.service;

import com.poly.da.entity.NhanVien;
import java.util.List;

public interface INhanVienService {
    
   
    boolean createNhanVien(NhanVien nv);
    
   
    boolean updateNhanVien(NhanVien nv); 
    
  
    boolean deleteNhanVien(String maNv);
    
    
    NhanVien findById(String maNv);
    
  
    List<NhanVien> findAll();
}