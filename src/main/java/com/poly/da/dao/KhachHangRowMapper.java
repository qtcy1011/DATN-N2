package com.poly.da.dao;

import com.poly.da.entity.KhachHang;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KhachHangRowMapper implements RowMapper<KhachHang> {

    @Override
    public KhachHang mapRow(ResultSet rs, int rowNum) throws SQLException {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(rs.getInt("maKhachHang"));
        kh.setHoTen(rs.getString("hoTen"));
        kh.setEmail(rs.getString("email"));
        kh.setSdt(rs.getString("sdt"));
        kh.setMatKhauHash(rs.getString("matKhauHash"));
        kh.setTongDiemTichLuy(rs.getInt("tongDiemTichLuy"));
        kh.setMaXepHangVIP(rs.getInt("maXepHangVIP"));
        kh.setNgayTao(rs.getDate("ngayTao"));
        kh.setTrangThaiTaiKhoan(rs.getString("trangThaiTaiKhoan"));
        return kh;
    }
}
