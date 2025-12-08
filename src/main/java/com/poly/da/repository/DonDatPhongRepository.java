package com.poly.da.repository;

import com.poly.da.entity.DonDatPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonDatPhongRepository extends JpaRepository<DonDatPhong, Integer> {
    
    // ⭐️ Lưu ý: Thay đổi kiểu dữ liệu cho khóa chính từ String thành Integer 
    // để phù hợp với Entity DonDatPhong đã thống nhất (@Id private Integer maDonDatPhong).

    // ----------------------------------------------------------------------
    // PHƯƠNG THỨC TÌM KIẾM THEO TÊN PHƯƠNG THỨC (Derived Query Methods)
    // ----------------------------------------------------------------------

    /**
     * Tìm kiếm Đơn Đặt Phòng theo MaKhachHang hoặc MaDonDatPhong.
     * Hỗ trợ cho tìm kiếm ID số trong Controller.
     */
    List<DonDatPhong> findByMaDonDatPhongOrMaKhachHang(Integer maDonDatPhong, Integer maKhachHang);

    /**
     * Tìm kiếm đơn theo Trạng thái đơn (TrangThaiDon).
     * Ví dụ: "PENDING", "COMPLETED", "CANCELLED".
     */
    List<DonDatPhong> findByTrangThaiDon(String trangThaiDon);
    
    // ----------------------------------------------------------------------
    // PHƯƠNG THỨC TÌM KIẾM LINH HOẠT VỚI @Query (Tùy chọn)
    // ----------------------------------------------------------------------

    /**
     * Tìm kiếm linh hoạt theo MaDonDatPhong, MaKhachHang, hoặc TrangThaiDon.
     * Thường được sử dụng khi Controller cần một tìm kiếm duy nhất cho keyword.
     */
    @Query("SELECT d FROM DonDatPhong d WHERE " +
           "CAST(d.maDonDatPhong AS string) LIKE %:keyword% OR " +
           "CAST(d.maKhachHang AS string) LIKE %:keyword% OR " +
           "d.trangThaiDon LIKE %:keyword%")
    List<DonDatPhong> searchDonDatPhongByKeyword(@Param("keyword") String keyword);
}