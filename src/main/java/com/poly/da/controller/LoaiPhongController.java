package com.poly.da.controller;

import com.poly.da.dao.LoaiPhongDAO;
import com.poly.da.entity.LoaiPhong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoaiPhongController {

    @Autowired
    private LoaiPhongDAO loaiPhongDAO;

    @GetMapping("/admin/loaiphong")
    public String viewLoaiPhong(Model model) {

        List<LoaiPhong> list = loaiPhongDAO.findAll();  // LẤY DỮ LIỆU TỪ CSDL

        model.addAttribute("items", list); // GỬI QUA JSP

        return "admin/loaiphong";  // TRẢ VỀ FILE JSP
    }
}
