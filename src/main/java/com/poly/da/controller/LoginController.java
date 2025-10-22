package com.poly.da.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
             
              return "login"; 
    }
    @GetMapping("/admin/login") 
    public String adminLogin(Model model) {
              
              return "admin/login"; 
    }
}