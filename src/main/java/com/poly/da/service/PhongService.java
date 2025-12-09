package com.poly.da.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.da.entity.Phong;
import com.poly.da.repository.PhongRepository;

@Service
public class PhongService {

    @Autowired
    private PhongRepository repo;

    public List<Phong> getAll() {
        return repo.findAll();
    }

    public List<Phong> getRoomsAvailable() {
        return repo.getRoomsAvailableNow();
    }

    public List<Phong> getRoomsOccupied() {
        return repo.getRoomsOccupied();
    }

    public Phong save(Phong p) {
        return repo.save(p);
    }
}
