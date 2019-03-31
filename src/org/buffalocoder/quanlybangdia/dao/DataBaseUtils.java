package org.buffalocoder.quanlybangdia.dao;

import java.sql.*;

public class DataBaseUtils {
    private static DataBaseUtils _instance;
    private static final String SERVER = "localhost";
    private static final int PORT = 1433;
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "sapassword";
    private static final String DB_NAME = "QUANLYBANGDIA";
//    private static final String URL = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s",
//            SERVER, PORT, DB_NAME, USERNAME, PASSWORD);

    private static final String URL = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=123456",
            SERVER, PORT, DB_NAME, USERNAME);//, PASSWORD);

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

    public void commitQuery() throws Exception {
        try {
            _connection.commit();
        } catch (SQLException e) {
            throw new Exception("Lỗi commit query");
        }
    }

    public void rollbackQuery() throws Exception {
        try {
            _connection.rollback();
        } catch (SQLException e) {
            throw new Exception("Lỗi rollback query");
        }
    }

    private Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new Exception("Không tìm thấy thư viện JDBC");
        } catch (SQLException e) {
            throw new Exception("Kết nối database thất bại");
        }
    }

    private DataBaseUtils() throws Exception {
        _connection = getConnection();
        _connection.setAutoCommit(false);
    }

    public static DataBaseUtils getInstance() throws Exception {
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
