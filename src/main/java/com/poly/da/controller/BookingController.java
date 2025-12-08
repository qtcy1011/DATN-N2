package com.poly.da.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.da.dto.BookingRequest;
import com.poly.da.entity.KhachSan;
import com.poly.da.repository.KhachSanRepository;

@Controller
public class BookingController {
	
	 @Autowired
	    private KhachSanRepository khachSanRepo;
	 
	 @GetMapping("/booking")
	    public String bookingPage(@RequestParam(name = "place", required = false) String place, Model model) {
		 String displayName = (place != null && !place.isEmpty())
	                ? place.replaceAll("([A-Z])", " $1").trim()
	                : "Việt Nam";

	        displayName = displayName.substring(0, 1).toUpperCase() + displayName.substring(1);

	        List<KhachSan> danhSachKhachSan;
	        if (place != null && !place.isEmpty()) {
	            danhSachKhachSan = khachSanRepo.findByThanhPhoContainingIgnoreCase(place);
	        } else {
	            danhSachKhachSan = khachSanRepo.findAll();
	        }

	        model.addAttribute("place", place);
	        model.addAttribute("displayName", displayName);
	        model.addAttribute("hotels", danhSachKhachSan);

	        return "booking";
	    }
	 @GetMapping("/hotel-detail")
     public String showHotelDetail(@RequestParam("id") Integer hotelId, Model model) {
         
         // 1. Dùng KhachSanRepository để tìm kiếm khách sạn theo ID
         Optional<KhachSan> optionalHotel = khachSanRepo.findById(hotelId);
         
         if (optionalHotel.isPresent()) {
             // 2. Nếu tìm thấy, thêm đối tượng KhachSan vào Model
             model.addAttribute("hotel", optionalHotel.get());
             // 3. Trả về tên file Thymeleaf (hotel-detail.html)
             return "hotel-detail";
         } else {
             // 4. Xử lý trường hợp không tìm thấy (ví dụ: quay lại trang tìm kiếm hoặc trang lỗi)
             // Tùy chọn 1: Trả về trang lỗi 404
             // return "error/404"; 
             
             // Tùy chọn 2: Chuyển hướng về trang chủ/tìm kiếm
             return "redirect:/booking"; 
         }
	 }
	 @PostMapping("/checkout")
	 public String processCheckout(
	     @ModelAttribute BookingRequest booking, // Spring tự động điền dữ liệu vào đối tượng này
	     Model model) {
	     
	     // Bây giờ bạn có thể truy cập tất cả thông tin qua đối tượng 'booking'
	     String tenKhachSan = booking.getHotelName();
	     LocalDate ngayNhan = booking.getCheckIn();
	     
	     // 1. Logic xử lý:
	     // ...
	     
	     // 2. Chuyển thông tin sang trang Checkout
	     model.addAttribute("bookingData", booking);
	     
	     // 3. Chuyển hướng
	     return "checkout"; 
	 }
}
