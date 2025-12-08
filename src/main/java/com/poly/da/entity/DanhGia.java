package com.poly.da.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DANHGIA")
public class DanhGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDanhGia")
    private Integer maDanhGia;

    @Column(name = "MaKhachSan")
    private Integer maKhachSan;

    @Column(name = "MaKhachHang")
    private Integer maKhachHang;

    @Column(name = "DiemSo")     // <--- tên cột đúng trong SQL
    private Integer diemSo;      // <--- kiểu INT đúng theo DB

    @Column(name = "NoiDung")
    private String noiDung;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao = LocalDateTime.now();

    public DanhGia() {
    }

    // GETTER & SETTER

    public Integer getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(Integer maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public Integer getMaKhachSan() {
        return maKhachSan;
    }

    public void setMaKhachSan(Integer maKhachSan) {
        this.maKhachSan = maKhachSan;
    }

    public Integer getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(Integer maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Integer getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(Integer diemSo) {
        this.diemSo = diemSo;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }
}
