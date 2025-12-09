package com.poly.da.repository;

import com.poly.da.entity.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongRepository extends JpaRepository<Phong, Integer> {

    // Lấy tất cả phòng trống
    @Query("SELECT p FROM Phong p WHERE p.trangThai = 'Trong'")
    List<Phong> getRoomsAvailableNow();

    // Lấy phòng đang có khách
    @Query("SELECT p FROM Phong p WHERE p.trangThai = 'Co khach'")
    List<Phong> getRoomsOccupied();
    
}

