package com.poly.da.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "LOAIPHONG")
public class RoomType {

    @Id
    @Column(name = "MaLoaiPhong")
    private String maLoaiPhong;

    @Column(name = "MaKhachSan")
    private String maKhachSan;

    @Column(name = "TenLoaiPhong")
    private String tenLoaiPhong;

    @Column(name = "SucChua")
    private Integer sucChua;

    @Column(name = "DienTich")
    private Double dienTich;

    @Column(name = "TienNghi")
    private String tienNghi;

    @Column(name = "GiaNiemYet")
    private Double giaNiemYet;

    // ----- Getter & Setter -----
    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }

    public String getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(String maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public Integer getSucChua() {
        return sucChua;
    }

    public void setSucChua(Integer sucChua) {
        this.sucChua = sucChua;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }

    public String getTienNghi() {
        return tienNghi;
    }

    public void setTienNghi(String tienNghi) {
        this.tienNghi = tienNghi;
    }

    public Double getGiaNiemYet() {
        return giaNiemYet;
    }

    public void setGiaNiemYet(Double giaNiemYet) {
        this.giaNiemYet = giaNiemYet;
    }
}
