package com.poly.da.dao;

import com.poly.da.config.DBConfig;
import com.poly.da.entity.KhachSan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class KhachSanDAO {

    public List<KhachSan> searchByCity(String thanhPho) {
        List<KhachSan> list = new ArrayList<>();
        String sql = "SELECT MaKhachSan, TenKhachSan, DiaChi, ThanhPho, TieuChuanSao, Rating, UrlHinhAnhDaiDien " +
                     "FROM KHACHSAN WHERE ThanhPho LIKE ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + thanhPho + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    KhachSan ks = new KhachSan();
                    ks.setMaKhachSan(rs.getInt("MaKhachSan"));
                    ks.setTenKhachSan(rs.getString("TenKhachSan"));
                    ks.setDiaChi(rs.getString("DiaChi"));
                    ks.setThanhPho(rs.getString("ThanhPho"));
                    ks.setTieuChuanSao(rs.getInt("TieuChuanSao"));
                    ks.setRating(rs.getBigDecimal("Rating"));
                    ks.setUrlHinhAnhDaiDien(rs.getString("UrlHinhAnhDaiDien"));
                    list.add(ks);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm khách sạn: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}