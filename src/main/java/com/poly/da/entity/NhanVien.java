package com.poly.da.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate; // Sử dụng LocalDate cho kiểu DATE trong SQL

@Entity 
@Table(name = "NHANVIEN")
public class NhanVien {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Dùng cho cột tự tăng (IDENTITY)
    @Column(name = "MaNhanVien")
    private Integer maNhanVien; // Dùng Integer cho ID

    @Column(name = "HoTen")
    private String hoTen;
    
    @Column(name = "Email", unique = true)
    private String email;
    
    @Column(name = "SDT", unique = true)
    private String sdt;
    
    @Column(name = "MatKhauHash")
    private String matKhauHash;
    
    @Column(name = "VaiTro")
    private String vaiTro; 
    
    @Column(name = "NgayTao")
    private Timestamp ngayTao; // Đổi sang LocalDate để khớp với DATE trong SQL
    
    @Column(name = "TrangThai")
    private String trangThai;

    public NhanVien() {
    }
    
    // --- Constructor đầy đủ (Tùy chọn) ---
    public NhanVien(String hoTen, String email, String sdt, String matKhauHash, String vaiTro, 
                    Timestamp ngayTao, String trangThai) {
        this.hoTen = hoTen;
        this.email = email;
        this.sdt = sdt;
        this.matKhauHash = matKhauHash;
        this.vaiTro = vaiTro;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    // --- Getters và Setters đã cập nhật kiểu dữ liệu ---

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
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

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Timestamp getNgayTao() { // Đã đổi kiểu trả về
        return ngayTao;
    }

    public void setNgayTao(Timestamp timestamp) { // Đã đổi kiểu tham số
        this.ngayTao = timestamp;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}