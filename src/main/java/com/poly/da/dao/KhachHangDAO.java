package com.poly.da.dao;

import com.poly.da.entity.KhachHang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository 
public class KhachHangDAO {

    @Autowired
    private JdbcTemplate jdbc;
   
    private final DataSource dataSource;


    public KhachHangDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private KhachHang mapRowToKhachHang(ResultSet rs) throws SQLException {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(rs.getInt("MaKhachHang"));
        kh.setHoTen(rs.getString("HoTen"));
        kh.setEmail(rs.getString("Email"));
        kh.setSdt(rs.getString("SDT"));
        kh.setMatKhauHash(rs.getString("MatKhauHash"));
        kh.setTongDiemTichLuy(rs.getInt("TongDiemTichLuy"));
        kh.setMaXepHangVIP(rs.getInt("MaXepHangVIP"));
        kh.setNgayTao(rs.getTimestamp("NgayTao"));
        kh.setTrangThaiTaiKhoan(rs.getString("TrangThaiTaiKhoan"));
        return kh;
    }




    public boolean insert(KhachHang kh) {
        String sql = "INSERT INTO KHACHHANG (HoTen, Email, SDT, MatKhauHash, MaXepHangVIP) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getEmail());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getMatKhauHash()); // Mật khẩu đã mã hóa
            ps.setInt(5, kh.getMaXepHangVIP() > 0 ? kh.getMaXepHangVIP() : 1); // Giả định ID hạng VIP mặc định là 1

            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public KhachHang findByEmail(String email) {
        String sql = "SELECT * FROM KHACHHANG WHERE Email = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToKhachHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public KhachHang findById(int i) {
        String sql = "SELECT * FROM KHACHHANG WHERE MaKhachHang = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, i);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToKhachHang(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean update(KhachHang kh) {
        String sql = "UPDATE KHACHHANG SET HoTen=?, Email=?, SDT=?, MatKhauHash=?, "
                   + "TongDiemTichLuy=?, MaXepHangVIP=?, TrangThaiTaiKhoan=? WHERE MaKhachHang=?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getEmail());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getMatKhauHash());
            ps.setInt(5, kh.getTongDiemTichLuy());
            ps.setInt(6, kh.getMaXepHangVIP());
            ps.setString(7, kh.getTrangThaiTaiKhoan());
            ps.setInt(8, kh.getMaKhachHang());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(int maKh) { 
        String sql = "DELETE FROM KHACHHANG WHERE MaKhachHang = ?";
        
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, maKh); 
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<KhachHang> findAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KHACHHANG";
        
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapRowToKhachHang(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHang> search(String keyword) {
        String sql = "SELECT * FROM KHACHHANG " +
                     "WHERE hoTen LIKE ? OR email LIKE ? OR sdt LIKE ?";

        String kw = "%" + keyword + "%";

        return jdbc.query(sql, new KhachHangRowMapper(), kw, kw, kw);
    }


}