package com.poly.da.service;

import com.poly.da.entity.KhachSan;
import com.poly.da.repository.KhachSanRepository;
import com.poly.da.repository.DanhGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode; // Cần import
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private KhachSanRepository khachSanRepo;

    @Autowired
    private DanhGiaRepository danhGiaRepo;

    /**
     * Cập nhật điểm rating cho MỘT khách sạn cụ thể.
     * Phương thức này nên được gọi sau mỗi khi có đánh giá mới được lưu.
     * * @param maKhachSan ID của khách sạn cần cập nhật rating.
     * @return KhachSan đã được cập nhật, hoặc null nếu không tìm thấy khách sạn.
     */
    public KhachSan updateSingleHotelRating(Integer maKhachSan) {
        // 1. Tính điểm trung bình từ Repository
        Double averageRating = danhGiaRepo.calculateAverageRatingByHotelId(maKhachSan);
        
        Optional<KhachSan> hotelOpt = khachSanRepo.findById(maKhachSan);
        
        if (hotelOpt.isPresent()) {
            KhachSan hotel = hotelOpt.get();
            
            if (averageRating != null) {
                // 2. Chuyển đổi và làm tròn (ví dụ: 4.567 -> 4.57)
                BigDecimal ratingValue = BigDecimal.valueOf(averageRating)
                                                   .setScale(2, RoundingMode.HALF_UP);
                
                // 3. Cập nhật Entity
                hotel.setRating(ratingValue);
            } else {
                // Nếu không có đánh giá, đặt rating là 0.00
                hotel.setRating(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
            }

            // 4. Lưu vào CSDL
            return khachSanRepo.save(hotel);
        }
        
        return null;
    }
}