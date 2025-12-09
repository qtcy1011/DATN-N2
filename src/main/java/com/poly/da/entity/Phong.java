package com.poly.da.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PHONG")
public class Phong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maPhong;

    private String tenPhong;

    private Integer maLoaiPhong;

    private String trangThai;

    private String ghiChu;
}

