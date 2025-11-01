package com.poly.da.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Bookingcontroller {
	 @GetMapping("/booking")
	    public String bookingPage(@RequestParam(name = "place", required = false) String place, Model model) {
	        if (place != null) {
	            String displayName = place.replaceAll("([A-Z])", " $1").trim();
	            displayName = displayName.substring(0, 1).toUpperCase() + displayName.substring(1);

	            model.addAttribute("place", place);
	            model.addAttribute("displayName", displayName);
	        }
	        return "booking";
	    }
}
