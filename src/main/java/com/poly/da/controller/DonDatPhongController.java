package com.poly.da.controller;


import com.poly.da.entity.DonDatPhong;
import com.poly.da.repository.DonDatPhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DonDatPhongController {

    @Autowired
    private DonDatPhongRepository donDatPhongRepo;

    @GetMapping("/admin/manage_bookings")
    public String manageOrders(@RequestParam(value = "keyword", required = false) String keyword,
                               Model model) {
        List<DonDatPhong> donDatPhongList;
        
        // Kiểm tra nếu có từ khóa tìm kiếm
        if (keyword != null && !keyword.isEmpty()) {
            // Thay thế phương thức bị lỗi bằng phương thức @Query đã sửa
            // (Đã sửa trong DonDatPhongRepository.java ở bước trước)
            donDatPhongList = donDatPhongRepo.searchDonDatPhongByKeyword(keyword); 
        } else {
            donDatPhongList = donDatPhongRepo.findAll();
        }

        model.addAttribute("donDatPhongList", donDatPhongList);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "admin/manage_bookings";

    }
}