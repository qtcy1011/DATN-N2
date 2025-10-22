package com.poly.da.repository;

import com.poly.da.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    

    Optional<NhanVien> findByEmail(String email);
}