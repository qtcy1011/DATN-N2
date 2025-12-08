// LoaiPhongRepository.java
package com.poly.da.repository;

import com.poly.da.entity.LoaiPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoaiPhongRepository extends JpaRepository<LoaiPhong, Integer> {
    
    /**
     * Phương thức tìm kiếm tất cả LoaiPhong dựa trên MaKhachSan.
     * Tên phương thức phải là findBy + TênThuộcTính (MaKhachSan).
     */
    List<LoaiPhong> findByMaKhachSan(Integer maKhachSan);
}