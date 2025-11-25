package com.poly.da.entity;


import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "DONDATPHONG") // bỏ tab
public class DonDatPhong {
    @Id
    private String maDonDatPhong;

    private LocalDate checkin;
    private LocalDate checkout;
    private Double giamGiaKhac;
    private String maKhachHang;
    private String maKhuyenMai;
    private LocalDate ngayDat;
    private Double thanhToanCuoi;
    private Double tienGiamTruVIP;
    private Double tongTienPhong;
    private String trangThaiDon;
    private Double tyLeGiamVIP;

    public DonDatPhong() {
    }

    public DonDatPhong(String maDonDatPhong, LocalDate checkin, LocalDate checkout, Double giamGiaKhac,
                       String maKhachHang, String maKhuyenMai, LocalDate ngayDat, Double thanhToanCuoi,
                       Double tienGiamTruVIP, Double tongTienPhong, String trangThaiDon, Double tyLeGiamVIP) {
        this.maDonDatPhong = maDonDatPhong;
        this.checkin = checkin;
        this.checkout = checkout;
        this.giamGiaKhac = giamGiaKhac;
        this.maKhachHang = maKhachHang;
        this.maKhuyenMai = maKhuyenMai;
        this.ngayDat = ngayDat;
        this.thanhToanCuoi = thanhToanCuoi;
        this.tienGiamTruVIP = tienGiamTruVIP;
        this.tongTienPhong = tongTienPhong;
        this.trangThaiDon = trangThaiDon;
        this.tyLeGiamVIP = tyLeGiamVIP;
    }

    // getter & setter dùng đúng kiểu dữ liệu
    public String getMaDonDatPhong() { return maDonDatPhong; }
    public void setMaDonDatPhong(String maDonDatPhong) { this.maDonDatPhong = maDonDatPhong; }
    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }
    public LocalDate getNgayDat() { return ngayDat; }
    public void setNgayDat(LocalDate ngayDat) { this.ngayDat = ngayDat; }
    public LocalDate getCheckin() { return checkin; }
    public void setCheckin(LocalDate checkin) { this.checkin = checkin; }
    public LocalDate getCheckout() { return checkout; }
    public void setCheckout(LocalDate checkout) { this.checkout = checkout; }
    public Double getTongTienPhong() { return tongTienPhong; }
    public void setTongTienPhong(Double tongTienPhong) { this.tongTienPhong = tongTienPhong; }
    public Double getTyLeGiamVIP() { return tyLeGiamVIP; }
    public void setTyLeGiamVIP(Double tyLeGiamVIP) { this.tyLeGiamVIP = tyLeGiamVIP; }
    public Double getTienGiamTruVIP() { return tienGiamTruVIP; }
    public void setTienGiamTruVIP(Double tienGiamTruVIP) { this.tienGiamTruVIP = tienGiamTruVIP; }
    public String getMaKhuyenMai() { return maKhuyenMai; }
    public void setMaKhuyenMai(String maKhuyenMai) { this.maKhuyenMai = maKhuyenMai; }
    public Double getGiamGiaKhac() { return giamGiaKhac; }
    public void setGiamGiaKhac(Double giamGiaKhac) { this.giamGiaKhac = giamGiaKhac; }
    public Double getThanhToanCuoi() { return thanhToanCuoi; }
    public void setThanhToanCuoi(Double thanhToanCuoi) { this.thanhToanCuoi = thanhToanCuoi; }
    public String getTrangThaiDon() { return trangThaiDon; }
    public void setTrangThaiDon(String trangThaiDon) { this.trangThaiDon = trangThaiDon; }
}
