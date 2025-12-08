package com.poly.da.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime; // Đổi từ LocalDate sang LocalDateTime cho NgayDat nếu cần chính xác thời gian

@Entity
@Table(name = "DONDATPHONG")
public class DonDatPhong {

    // 1. MaDonDatPhong: Đổi từ String sang Integer (do SQL là INT IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDonDatPhong")
    private Integer maDonDatPhong;

    // 2. MaKhachHang: Đổi từ String sang Integer (do SQL là INT)
    @Column(name = "MaKhachHang")
    private Integer maKhachHang;

    @Column(name = "NgayDat")
    private LocalDate ngayDat; // Giữ LocalDate hoặc dùng LocalDateTime

    @Column(name = "CheckIn")
    private LocalDate checkin;

    @Column(name = "CheckOut")
    private LocalDate checkout;

    @Column(name = "TongTienPhong")
    private Double tongTienPhong;

    @Column(name = "TyLeGiamVIP")
    private Double tyLeGiamVIP;

    @Column(name = "TienGiamTruVIP")
    private Double tienGiamTruVIP;

    @Column(name = "MaKhuyenMai")
    private String maKhuyenMai; // Mã khuyến mãi là String (VD: LASTMIN10)

    @Column(name = "GiamGiaKhac")
    private Double giamGiaKhac;

    @Column(name = "ThanhToanCuoi")
    private Double thanhToanCuoi;

    @Column(name = "TrangThaiDon")
    private String trangThaiDon;


    public DonDatPhong() {
    }

    // Constructor đã được cập nhật kiểu dữ liệu
    public DonDatPhong(Integer maDonDatPhong, LocalDate checkin, LocalDate checkout, Double giamGiaKhac,
                       Integer maKhachHang, String maKhuyenMai, LocalDate ngayDat, Double thanhToanCuoi,
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

    // Getter & Setter
    public Integer getMaDonDatPhong() { return maDonDatPhong; }
    // Giữ setter nhưng trong thực tế với IDENTITY, thường không dùng set ID
    public void setMaDonDatPhong(Integer maDonDatPhong) { this.maDonDatPhong = maDonDatPhong; } 
    
    public Integer getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(Integer maKhachHang) { this.maKhachHang = maKhachHang; }
    
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