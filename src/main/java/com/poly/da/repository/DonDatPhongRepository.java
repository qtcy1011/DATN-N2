package com.poly.da.repository;

import com.poly.da.entity.DonDatPhong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonDatPhongRepository extends JpaRepository<DonDatPhong, String> {
    List<DonDatPhong> findByMaDonDatPhongContainingOrMaKhachHangContaining(String maDon, String maKhach);
}
