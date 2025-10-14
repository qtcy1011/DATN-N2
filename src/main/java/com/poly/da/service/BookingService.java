package com.poly.da.service;

import com.poly.da.entity.Booking;
import com.poly.da.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

}