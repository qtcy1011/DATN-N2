package com.poly.da.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime; // Sử dụng API Date/Time hiện đại của Java

@Entity 
@Table(name = "KHACHHANG")
public class KhachHang {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Dùng cho cột tự tăng (IDENTITY)
    @Column(name = "MaKhachHang")
	private Integer maKhachHang; // Dùng Integer cho ID
    
    @Column(name = "HoTen")
    private String hoTen;
    
    @Column(name = "Email", unique = true)
    private String email;
    
    @Column(name = "SDT", unique = true)
    private String sdt;
    
    @Column(name = "MatKhauHash")
    private String matKhauHash;
    
    @Column(name = "TongDiemTichLuy")
    private int tongDiemTichLuy;
    
    @Column(name = "MaXepHangVIP")
    private int maXepHangVIP;
    
    @Column(name = "NgayTao")
    private LocalDateTime ngayTao; // Đổi sang LocalDateTime
    
    @Column(name = "TrangThaiTaiKhoan")
    private String trangThaiTaiKhoan;
    
    @Column(name = "VaiTro") // Trường bị thiếu, đã thêm
    private String vaiTro;

    public KhachHang() {
    }
    
    // Thêm constructor đầy đủ (Tùy chọn)
    public KhachHang(String hoTen, String email, String sdt, String matKhauHash, int tongDiemTichLuy,
                     int maXepHangVIP, LocalDateTime ngayTao, String trangThaiTaiKhoan, String vaiTro) {
        this.hoTen = hoTen;
        this.email = email;
        this.sdt = sdt;
        this.matKhauHash = matKhauHash;
        this.tongDiemTichLuy = tongDiemTichLuy;
        this.maXepHangVIP = maXepHangVIP;
        this.ngayTao = ngayTao;
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
        this.vaiTro = vaiTro;
    }


    // Getters and Setters đã cập nhật kiểu dữ liệu

    public Integer getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(Integer maKhachHang) {
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

    public LocalDateTime getNgayTao() { // Đã đổi kiểu trả về
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime timestamp) { // Đã đổi kiểu tham số
        this.ngayTao = timestamp;
    }

    public String getTrangThaiTaiKhoan() {
        return trangThaiTaiKhoan;
    }

    public void setTrangThaiTaiKhoan(String trangThaiTaiKhoan) {
        this.trangThaiTaiKhoan = trangThaiTaiKhoan;
    }
    
    // Getter & Setter cho VaiTro
    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

	public void setNgayTao(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void setNgayTao(Timestamp timestamp) {
		// TODO Auto-generated method stub
		
	}
}