package com.poly.da.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.da.entity.KhachSan;
import java.util.List; // Import List

public interface KhachSanRepository extends JpaRepository<KhachSan, Integer> {

    // Thêm phương thức tìm kiếm này vào Repository
    List<KhachSan> findByThanhPhoContainingIgnoreCase(String thanhPho);
}