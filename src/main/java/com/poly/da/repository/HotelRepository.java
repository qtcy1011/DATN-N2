package com.poly.da.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poly.da.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
