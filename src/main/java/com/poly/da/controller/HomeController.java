package com.poly.da.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Xử lý yêu cầu truy cập trang chủ.
     * Ánh xạ các URL: /, /home, /index
     */
    @GetMapping({"/", "/home", "/index"})
    public String homePage() {

        return "index"; 
    }
}