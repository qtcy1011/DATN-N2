package com.poly.da.repository;

import com.poly.da.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DanhGiaRepository extends JpaRepository<DanhGia, Integer> {

    /**
     * Tính điểm trung bình cho một khách sạn theo ID.
     */
    @Query("SELECT AVG(d.diemSo) FROM DanhGia d WHERE d.maKhachSan = :maKhachSan")
    Double calculateAverageRatingByHotelId(@Param("maKhachSan") Integer maKhachSan);

    /**
     * Lấy danh sách đánh giá theo Mã Khách sạn.
     */
    List<DanhGia> findByMaKhachSan(Integer maKhachSan);
}
