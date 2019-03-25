package org.buffalocoder.quanlybangdia.dao;

import java.sql.*;

public class DataBaseUtils {
    private static DataBaseUtils _instance;
    private static final String SERVER = "localhost";
    private static final int PORT = 1433;
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sapassword";
    private static final String DB_NAME = "QUANLYBANGDIA";
    private static final String URL = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s",
            SERVER, PORT, DB_NAME, USERNAME, PASSWORD);

    private Connection _connection;

    public PreparedStatement excuteQueryWrite(String sql){
        try {
            PreparedStatement ps = _connection.prepareStatement(sql);
            return ps;
        }catch (Exception e){

        }
        return null;
    }

    public ResultSet excuteQueryRead(String sql){
        Statement st = null;
        ResultSet rs = null;

        try {
            st = _connection.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    private Connection getConnection() {
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

    private DataBaseUtils() {
        _connection = getConnection();
    }

    public static DataBaseUtils getInstance() {
        if(_instance == null) {
            synchronized(DataBaseUtils.class) {
                if(null == _instance) {
                    _instance  = new DataBaseUtils();
                }
            }
        }
        return _instance;
    }
}
