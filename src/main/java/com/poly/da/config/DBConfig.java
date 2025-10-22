package com.poly.da.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConfig {
    
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE = "application.properties";

    static {
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.err.println("Lỗi: Không tìm thấy file " + PROPERTIES_FILE);
            } else {
                PROPERTIES.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            // Đăng ký Driver
            Class.forName(PROPERTIES.getProperty("db.driver"));
            
            // Mở kết nối
            return DriverManager.getConnection(
                PROPERTIES.getProperty("db.url"),
                PROPERTIES.getProperty("db.username"),
                PROPERTIES.getProperty("db.password")
            );
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy JDBC Driver.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Lỗi: Không thể kết nối CSDL.");
            e.printStackTrace();
            return null;
        }
    }
}