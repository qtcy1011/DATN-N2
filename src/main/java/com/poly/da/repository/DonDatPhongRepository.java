package com.poly.da.repository;

import com.poly.da.entity.DonDatPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Cần import
import org.springframework.data.repository.query.Param; // Cần import

import java.util.List;

// Đảm bảo Khóa chính (MaDonDatPhong) trong Entity là Integer hoặc Long.
// Vì bạn đang gặp lỗi liên quan đến tìm kiếm LIKE trên ID (Integer), 
// nên ta phải giả định khóa chính là Integer và sửa lại JpaRepository<DonDatPhong, Integer>
public interface DonDatPhongRepository extends JpaRepository<DonDatPhong, Integer> { 
    
    // Xóa/Vô hiệu hóa phương thức cũ bị lỗi:
    // List<DonDatPhong> findByMaDonDatPhongContainingOrMaKhachHangContaining(String maDon, String maKhach);

    /**
     * Phương thức tìm kiếm đơn đặt phòng theo MaDonDatPhong HOẶC MaKhachHang
     * (Ép kiểu trường Integer sang String để sử dụng toán tử LIKE)
     * * @param keyword Chuỗi tìm kiếm (Ví dụ: "123")
     * @return Danh sách các đơn đặt phòng thỏa mãn
     */
    @Query("SELECT d FROM DonDatPhong d WHERE " +
           "CAST(d.maDonDatPhong AS string) LIKE CONCAT('%', :keyword, '%') OR " +
           "CAST(d.maKhachHang AS string) LIKE CONCAT('%', :keyword, '%')")
    List<DonDatPhong> searchDonDatPhongByKeyword(@Param("keyword") String keyword);
}