package com.poly.da.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "XEPHANGVIP")
public class XepHangVIP {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaXepHangVIP")
    private Integer maXepHangVIP; // Đổi sang Integer cho ID
    
    @Column(name = "TenHang", unique = true)
    private String tenHang;
    
    @Column(name = "DiemToiThieu")
    private int diemToiThieu; // Giữ int vì NOT NULL trong SQL
    
    @Column(name = "TyLeUuDai")
    private BigDecimal tyLeUuDai;
    
    @Column(name = "MoTa")
    private String moTa;

    public XepHangVIP() {
    }
    
    // --- Getters and Setters đã cập nhật và bổ sung ---

    public Integer getMaXepHangVIP() {
        return maXepHangVIP;
    }

    public void setMaXepHangVIP(Integer maXepHangVIP) {
        this.maXepHangVIP = maXepHangVIP;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getDiemToiThieu() {
        return diemToiThieu;
    }

    public void setDiemToiThieu(int diemToiThieu) {
        this.diemToiThieu = diemToiThieu;
    }
    
    public BigDecimal getTyLeUuDai() {
        return tyLeUuDai;
    }

    public void setTyLeUuDai(BigDecimal tyLeUuDai) {
        this.tyLeUuDai = tyLeUuDai;
    }
    
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}