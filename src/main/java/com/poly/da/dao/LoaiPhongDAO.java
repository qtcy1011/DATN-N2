package com.poly.da.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.da.entity.LoaiPhong;

public interface LoaiPhongDAO extends JpaRepository<LoaiPhong, Integer> {
}
