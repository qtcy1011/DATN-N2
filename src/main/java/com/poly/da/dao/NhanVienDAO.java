package com.poly.da.dao;

import com.poly.da.entity.NhanVien;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository 
public class NhanVienDAO {

    private final DataSource dataSource;


    public NhanVienDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private NhanVien mapRowToNhanVien(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
              nv.setMaNhanVien(rs.getInt("MaNhanVien")); 
        nv.setHoTen(rs.getString("HoTen"));
        nv.setEmail(rs.getString("Email"));
        nv.setSdt(rs.getString("SDT"));
        nv.setMatKhauHash(rs.getString("MatKhauHash"));
        nv.setNgayTao(rs.getTimestamp("NgayTao"));
        nv.setVaiTro(rs.getString("VaiTro"));
        nv.setTrangThai(rs.getString("TrangThai"));
        return nv;
    }


    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO NHANVIEN (HoTen, Email, SDT, MatKhauHash, VaiTro, TrangThai) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getEmail());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getMatKhauHash()); 
            ps.setString(5, nv.getVaiTro());
            ps.setString(6, nv.getTrangThai());

            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public NhanVien findByEmail(String email) {
        String sql = "SELECT * FROM NHANVIEN WHERE Email = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToNhanVien(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

 
    public NhanVien findById(int maNv) {

        String sql = "SELECT * FROM NHANVIEN WHERE MaNhanVien = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
                    ps.setInt(1, maNv); 
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToNhanVien(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(NhanVien nv) {

        String sql = "UPDATE NHANVIEN SET HoTen=?, Email=?, SDT=?, MatKhauHash=?, "
                   + "VaiTro=?, TrangThai=? WHERE MaNhanVien=?"; 
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getEmail());
            ps.setString(3, nv.getSdt());
            ps.setString(4, nv.getMatKhauHash());
            ps.setString(5, nv.getVaiTro());
            ps.setString(6, nv.getTrangThai());
            ps.setInt(7, nv.getMaNhanVien());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(int maNv) {

        String sql = "DELETE FROM NHANVIEN WHERE MaNhanVien = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
    
            ps.setInt(1, maNv);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<NhanVien> findAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN";
        
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapRowToNhanVien(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}