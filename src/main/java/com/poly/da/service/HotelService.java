package com.poly.da.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.poly.da.entity.Hotel;
import com.poly.da.repository.HotelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepo;

    public List<Hotel> findAll() {
        return hotelRepo.findAll();
    }

    public Hotel findById(Long id) {
        return hotelRepo.findById(id).orElse(null);
    }

    public Hotel save(Hotel hotel) {
        return hotelRepo.save(hotel);
    }

    public void delete(Long id) {
        hotelRepo.deleteById(id);
    }
}
