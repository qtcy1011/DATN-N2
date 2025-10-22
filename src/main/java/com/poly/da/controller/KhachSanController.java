package com.poly.da.controller;

import com.poly.da.dao.KhachSanDAO;
import com.poly.da.entity.KhachSan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class KhachSanController {

    private final KhachSanDAO ksDao;

  
    public KhachSanController(KhachSanDAO ksDao) {
        this.ksDao = ksDao;
    }


    @GetMapping("/search")
    public String showSearchForm() {
        return "search"; 
    }


    @PostMapping("/search")
    public String searchHotels(@RequestParam(value = "city", required = false) String thanhPho,
                               Model model) {
        
        if (thanhPho == null || thanhPho.trim().isEmpty()) {
            model.addAttribute("error", "Vui lòng nhập tên thành phố!");
            return "search";
        }

        List<KhachSan> results = ksDao.searchByCity(thanhPho);
        
        model.addAttribute("searchCity", thanhPho);
        model.addAttribute("hotelList", results);
        
          return "results"; 
    }
}