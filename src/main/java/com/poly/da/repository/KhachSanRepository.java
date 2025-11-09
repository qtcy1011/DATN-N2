package com.poly.da.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.da.entity.KhachSan;

public interface KhachSanRepository extends JpaRepository<KhachSan, Integer> {
	List<KhachSan> findByThanhPhoContainingIgnoreCase(String thanhPho);
}
