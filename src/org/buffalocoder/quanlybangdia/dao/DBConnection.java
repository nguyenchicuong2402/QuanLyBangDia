package org.buffalocoder.quanlybangdia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            try {
                System.out.println("Kết nối thành công");
                return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUANLYBANGDIA;user=root;password=root");
            } catch (SQLException e) {
                // TODO: handle exception
            }
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
        }
        return null;

    }
}
