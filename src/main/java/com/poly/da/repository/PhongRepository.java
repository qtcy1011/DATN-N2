package com.poly.da.repository;

import com.poly.da.entity.Phong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhongRepository extends JpaRepository<Phong, Integer> {

    // PHÒNG ĐANG CÓ KHÁCH
    @Query("""
        SELECT p FROM Phong p 
        WHERE p.maPhong IN (
            SELECT d.maPhong FROM DonDatPhong d
            WHERE d.checkIn <= CURRENT_DATE AND d.checkOut > CURRENT_DATE
        )
    """)
    List<Phong> getRoomsOccupiedNow();

    // PHÒNG CÒN TRỐNG
    @Query("""
        SELECT p FROM Phong p
        WHERE p.maPhong NOT IN (
            SELECT d.maPhong FROM DonDatPhong d
            WHERE d.checkIn <= CURRENT_DATE AND d.checkOut > CURRENT_DATE
        )
    """)
    List<Phong> getRoomsAvailableNow();
}
