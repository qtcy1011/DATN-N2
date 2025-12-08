// LoaiPhongService.java
package com.poly.da.service;

import com.poly.da.entity.LoaiPhong;
import com.poly.da.repository.LoaiPhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoaiPhongService {
    
    @Autowired
    private LoaiPhongRepository loaiPhongRepo;

    public List<LoaiPhong> findRoomsByHotelId(Integer hotelId) {
        // Đảm bảo gọi phương thức đã định nghĩa ở trên
        return loaiPhongRepo.findByMaKhachSan(hotelId); 
    }
}