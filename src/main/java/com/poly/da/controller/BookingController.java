package com.poly.da.controller;

import com.poly.da.entity.*;
import com.poly.da.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BookingController {
    private final BookingRepository bookingRepo;
    private final HotelRepository hotelRepo;

    @GetMapping("/booking/{id}")
    public String booking(@PathVariable Long id, org.springframework.ui.Model model) {
        model.addAttribute("hotel", hotelRepo.findById(id).orElse(null));
        return "booking";
    }

    @PostMapping("/booking")
    public String saveBooking(Booking booking) {
        bookingRepo.save(booking);
        return "redirect:/";
    }
}
