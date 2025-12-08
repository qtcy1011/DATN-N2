package com.poly.da.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "DONDATPHONG") // bỏ tab
public class DonDatPhong {

    @Id
    private String maDonDatPhong;

    private LocalDate checkin;
    private LocalDate checkout;
    
    @Column(precision = 18, scale = 2)
    private BigDecimal giamGiaKhac;

    private String maKhachHang;
    private String maKhuyenMai;
    private LocalDate ngayDat;

    @Column(precision = 18, scale = 2)
    private BigDecimal thanhToanCuoi;

    @Column(precision = 18, scale = 2)
    private BigDecimal tienGiamTruVIP;

    @Column(precision = 18, scale = 2)
    private BigDecimal tongTienPhong;

    private String trangThaiDon;

    @Column(precision = 5, scale = 2)
    private BigDecimal tyLeGiamVIP;

    public DonDatPhong() {
    }

    public DonDatPhong(String maDonDatPhong, LocalDate checkin, LocalDate checkout, BigDecimal giamGiaKhac,
                       String maKhachHang, String maKhuyenMai, LocalDate ngayDat, BigDecimal thanhToanCuoi,
                       BigDecimal tienGiamTruVIP, BigDecimal tongTienPhong, String trangThaiDon, BigDecimal tyLeGiamVIP) {
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

    public BigDecimal getTongTienPhong() { return tongTienPhong; }
    public void setTongTienPhong(BigDecimal tongTienPhong) { this.tongTienPhong = tongTienPhong; }

    public BigDecimal getTyLeGiamVIP() { return tyLeGiamVIP; }
    public void setTyLeGiamVIP(BigDecimal tyLeGiamVIP) { this.tyLeGiamVIP = tyLeGiamVIP; }

    public BigDecimal getTienGiamTruVIP() { return tienGiamTruVIP; }
    public void setTienGiamTruVIP(BigDecimal tienGiamTruVIP) { this.tienGiamTruVIP = tienGiamTruVIP; }

    public String getMaKhuyenMai() { return maKhuyenMai; }
    public void setMaKhuyenMai(String maKhuyenMai) { this.maKhuyenMai = maKhuyenMai; }

    public BigDecimal getGiamGiaKhac() { return giamGiaKhac; }
    public void setGiamGiaKhac(BigDecimal giamGiaKhac) { this.giamGiaKhac = giamGiaKhac; }

    public BigDecimal getThanhToanCuoi() { return thanhToanCuoi; }
    public void setThanhToanCuoi(BigDecimal thanhToanCuoi) { this.thanhToanCuoi = thanhToanCuoi; }

    public String getTrangThaiDon() { return trangThaiDon; }
    public void setTrangThaiDon(String trangThaiDon) { this.trangThaiDon = trangThaiDon; }
}