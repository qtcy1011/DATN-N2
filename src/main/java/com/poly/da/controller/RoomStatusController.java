package com.poly.da.controller;

import com.poly.da.repository.PhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomStatusController {

    @Autowired
    private PhongRepository phongRepo;

    @GetMapping("/admin/room-status")
    public String roomStatus(Model model) {

        model.addAttribute("available", phongRepo.getRoomsAvailableNow());
        model.addAttribute("occupied", phongRepo.getRoomsOccupiedNow());

        return "admin/room_status";
    }
}
