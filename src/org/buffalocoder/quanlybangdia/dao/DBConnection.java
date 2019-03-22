package org.buffalocoder.quanlybangdia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String SERVER = "localhost";
    private static final int PORT = 1433;
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sapassword";
    private static final String DB_NAME = "QUANLYBANGDIA";
    private static final String URL = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s",
                                    SERVER, PORT, DB_NAME, USERNAME, PASSWORD);

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            System.out.println("Kết nối thành công");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy thư viện JDBC");
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối database");
        }
        return null;

    }
}
