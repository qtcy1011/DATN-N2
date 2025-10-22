package com.poly.da.entity;

import java.math.BigDecimal;

public class XepHangVIP {
    private int maXepHangVIP;
    private String tenHang;
    private int diemToiThieu;
    private BigDecimal tyLeUuDai;
    private String moTa;

    public XepHangVIP() {
    }

    public int getMaXepHangVIP() {
        return maXepHangVIP;
    }

    public void setMaXepHangVIP(int maXepHangVIP) {
        this.maXepHangVIP = maXepHangVIP;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }
    
    public BigDecimal getTyLeUuDai() {
        return tyLeUuDai;
    }

    public void setTyLeUuDai(BigDecimal tyLeUuDai) {
        this.tyLeUuDai = tyLeUuDai;
    }
    
}