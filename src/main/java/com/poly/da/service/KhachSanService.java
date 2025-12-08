package com.poly.da.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.da.entity.KhachSan;
import com.poly.da.repository.KhachSanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KhachSanService {

    @Autowired
    private KhachSanRepository repo;

    public List<KhachSan> findAll() {
        return repo.findAll();
    }

    public Optional<KhachSan> findById(Integer id) {
        return repo.findById(id);
    }

    public KhachSan save(KhachSan ks) {
        return repo.save(ks);
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
