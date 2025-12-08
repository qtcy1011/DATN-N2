package com.poly.da.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DONDATPHONG")
public class DonDatPhong {

    // 1. Khóa chính: Sử dụng Integer và tự tăng (IDENTITY)
    // Loại bỏ khai báo @Id String maDonDatPhong bị trùng lặp ở trên
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaDonDatPhong")
    private Integer maDonDatPhong;

    // 2. Khóa ngoại: Sử dụng Integer
    @Column(name = "MaKhachHang")
    private Integer maKhachHang;

    @Column(name = "NgayDat")
    private LocalDate ngayDat;

    @Column(name = "CheckIn")
    private LocalDate checkin;

    @Column(name = "CheckOut")
    private LocalDate checkout;
    
    // 3. Tiền tệ: Thống nhất sử dụng BigDecimal (chuẩn xác nhất)
    // precision = 18, scale = 2 là cấu hình tốt cho tiền tệ
    
    @Column(name = "TongTienPhong", precision = 18, scale = 2)
    private BigDecimal tongTienPhong;

    // Tỷ lệ giảm là phần trăm (VD: 5.00)
    @Column(name = "TyLeGiamVIP", precision = 5, scale = 2) 
    private BigDecimal tyLeGiamVIP;

    @Column(name = "TienGiamTruVIP", precision = 18, scale = 2)
    private BigDecimal tienGiamTruVIP;

    @Column(name = "MaKhuyenMai")
    private String maKhuyenMai;

    @Column(name = "GiamGiaKhac", precision = 18, scale = 2)
    private BigDecimal giamGiaKhac;

    @Column(name = "ThanhToanCuoi", precision = 18, scale = 2)
    private BigDecimal thanhToanCuoi;

    @Column(name = "TrangThaiDon")
    private String trangThaiDon;


    public DonDatPhong() {
    }

    // Constructor đã được cập nhật kiểu dữ liệu hợp nhất
    public DonDatPhong(Integer maDonDatPhong, LocalDate checkin, LocalDate checkout, BigDecimal giamGiaKhac,
                       Integer maKhachHang, String maKhuyenMai, LocalDate ngayDat, BigDecimal thanhToanCuoi,
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

    // Getter & Setter (Sử dụng kiểu dữ liệu đã thống nhất)
    
    public Integer getMaDonDatPhong() { return maDonDatPhong; }
    public void setMaDonDatPhong(Integer maDonDatPhong) { this.maDonDatPhong = maDonDatPhong; } 
    
    public Integer getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(Integer maKhachHang) { this.maKhachHang = maKhachHang; }
    
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