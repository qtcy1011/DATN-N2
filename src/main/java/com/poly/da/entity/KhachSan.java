package com.poly.da.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "KHACHSAN")
public class KhachSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKhachSan")
    private Integer maKhachSan;

    @Column(name = "TenKhachSan", nullable = false)
    private String tenKhachSan;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "ThanhPho")
    private String thanhPho;

    @Column(name = "QuocGia")
    private String quocGia;

    @Column(name = "TieuChuanSao")
    private int tieuChuanSao;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "Rating")
    private BigDecimal rating;

    @Column(name = "UrlHinhAnhDaiDien")
    private String urlHinhAnhDaiDien;

    @Column(name = "GiaMoiDem")
    private BigDecimal giaMoiDem;
    
    // ❌ ĐÃ XÓA: Mối quan hệ @OneToMany với LoaiPhong
    // private List<LoaiPhong> loaiPhongs; 

    public KhachSan() {}

    // GETTER - SETTER
    public Integer getMaKhachSan() { return maKhachSan; }
    public void setMaKhachSan(Integer maKhachSan) { this.maKhachSan = maKhachSan; }

    public String getTenKhachSan() { return tenKhachSan; }
    public void setTenKhachSan(String tenKhachSan) { this.tenKhachSan = tenKhachSan; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getThanhPho() { return thanhPho; }
    public void setThanhPho(String thanhPho) { this.thanhPho = thanhPho; }

    public String getQuocGia() { return quocGia; }
    public void setQuocGia(String quocGia) { this.quocGia = quocGia; }

    public int getTieuChuanSao() { return tieuChuanSao; }
    public void setTieuChuanSao(int tieuChuanSao) { this.tieuChuanSao = tieuChuanSao; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public BigDecimal getRating() { return rating; }
    public void setRating(BigDecimal rating) { this.rating = rating; }

    public String getUrlHinhAnhDaiDien() { return urlHinhAnhDaiDien; }
    public void setUrlHinhAnhDaiDien(String urlHinhAnhDaiDien) { this.urlHinhAnhDaiDien = urlHinhAnhDaiDien; }

    public BigDecimal getGiaMoiDem() { return giaMoiDem; }
    public void setGiaMoiDem(BigDecimal giaMoiDem) { this.giaMoiDem = giaMoiDem; }

    // ❌ ĐÃ XÓA: Getter/Setter cho loaiPhongs
    // public List<LoaiPhong> getLoaiPhongs() { return loaiPhongs; }
    // public void setLoaiPhongs(List<LoaiPhong> loaiPhongs) { this.loaiPhongs = loaiPhongs; }
}