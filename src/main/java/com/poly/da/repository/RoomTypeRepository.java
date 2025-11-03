package com.poly.da.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.da.entity.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {
}