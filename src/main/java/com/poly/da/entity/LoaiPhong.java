package com.poly.da.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; 

@Entity
@Table(name = "LOAIPHONG")
public class LoaiPhong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaLoaiPhong")
    private Integer maLoaiPhong;

    // ⚠️ ĐÃ XÓA @ManyToOne VÀ THAY BẰNG KHÓA NGOẠI DẠNG SỐ NGUYÊN
    @Column(name = "MaKhachSan", nullable = false)
    private Integer maKhachSan; // <-- Giờ là thuộc tính thường

    @Column(name = "TenLoaiPhong", nullable = false)
    private String tenLoaiPhong;

    @Column(name = "SucChua", nullable = false)
    private Integer sucChua;

    @Column(name = "DienTich")
    private Integer dienTich;

    @Column(name = "TienNghi")
    private String tienNghi;

    @Column(name = "GiaNiemYet", nullable = false)
    private BigDecimal giaNiemYet;


    public LoaiPhong() {}

    // GETTER - SETTER
    public Integer getMaLoaiPhong() { return maLoaiPhong; }
    public void setMaLoaiPhong(Integer maLoaiPhong) { this.maLoaiPhong = maLoaiPhong; }

    // ⚠️ GETTER/SETTER CHO MA KHACH SAN
    public Integer getMaKhachSan() { return maKhachSan; }
    public void setMaKhachSan(Integer maKhachSan) { this.maKhachSan = maKhachSan; }
    
    public String getTenLoaiPhong() { return tenLoaiPhong; }
    public void setTenLoaiPhong(String tenLoaiPhong) { this.tenLoaiPhong = tenLoaiPhong; }

    public Integer getSucChua() { return sucChua; }
    public void setSucChua(Integer sucChua) { this.sucChua = sucChua; }

    public Integer getDienTich() { return dienTich; }
    public void setDienTich(Integer dienTich) { this.dienTich = dienTich; }

    public String getTienNghi() { return tienNghi; }
    public void setTienNghi(String tienNghi) { this.tienNghi = tienNghi; }

    public BigDecimal getGiaNiemYet() { return giaNiemYet; }
    public void setGiaNiemYet(BigDecimal giaNiemYet) { this.giaNiemYet = giaNiemYet; }
}