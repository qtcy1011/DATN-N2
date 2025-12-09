package com.poly.da.controller;

import com.poly.da.repository.PhongRepository;
import com.poly.da.service.PhongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/phong")
public class RoomStatusController {

    @Autowired
    private PhongService service;

    @GetMapping("/tatca")
    public String allRooms(Model model) {
        model.addAttribute("list", service.getAll());
        return "admin/list";
    }

    @GetMapping("/trong")
    public String roomsAvailable(Model model) {
        model.addAttribute("list", service.getRoomsAvailable());
        return "admin/list";
    }

    @GetMapping("/cokhach")
    public String roomsOccupied(Model model) {
        model.addAttribute("list", service.getRoomsOccupied());
        return "admin/list";
    }
}


