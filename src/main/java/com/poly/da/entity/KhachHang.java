package com.poly.da.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity 
@Table(name = "KHACHHANG")
public class KhachHang {
	@Id
	private int maKhachHang;
    private String hoTen;
    private String email;
    private String sdt;
    private String matKhauHash;
    private int tongDiemTichLuy;
    private int maXepHangVIP;
    private Date ngayTao;
    private String trangThaiTaiKhoan;

    public KhachHang() {
    }

      public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatKhauHash() {
        return matKhauHash;
    }

    public void setMatKhauHash(String matKhauHash) {
        this.matKhauHash = matKhauHash;
    }

    public int getTongDiemTichLuy() {
        return tongDiemTichLuy;
    }

    public void setTongDiemTichLuy(int tongDiemTichLuy) {
        this.tongDiemTichLuy = tongDiemTichLuy;
    }

    public int getMaXepHangVIP() {
        return maXepHangVIP;
    }

    public void setMaXepHangVIP(int maXepHangVIP) {
        this.maXepHangVIP = maXepHangVIP;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTrangThaiTaiKhoan() {
        return trangThaiTaiKhoan;
    }

    public void setTrangThaiTaiKhoan(String trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }
}