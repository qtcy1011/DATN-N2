package com.poly.da.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "KHACHSAN")
public class KhachSan {
	@Id
    private int maKhachSan;
    private String tenKhachSan;
    private String diaChi;
    private String thanhPho;
    private String quocGia;
    private int tieuChuanSao;
    private String moTa;
    private BigDecimal rating;
    private String urlHinhAnhDaiDien;


    public KhachSan() {
    
    }


    public int getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(int maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public String getTenKhachSan() {
        return tenKhachSan;
    }

    public void setTenKhachSan(String tenKhachSan) {
        this.tenKhachSan = tenKhachSan;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public int getTieuChuanSao() {
        return tieuChuanSao;
    }

    public void setTieuChuanSao(int tieuChuanSao) {
        this.tieuChuanSao = tieuChuanSao;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getUrlHinhAnhDaiDien() {
        return urlHinhAnhDaiDien;
    }

    public void setUrlHinhAnhDaiDien(String urlHinhAnhDaiDien) {
        this.urlHinhAnhDaiDien = urlHinhAnhDaiDien;
    }
    
    // (Bổ sung getters/setters cho QuocGia, MoTa...)
}