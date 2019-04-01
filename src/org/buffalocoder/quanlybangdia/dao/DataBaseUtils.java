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

    private static Connection _connection;

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

    public PreparedStatement excuteQueryWrite(String sql){
        try {
            return _connection.prepareStatement(sql);
        }catch (Exception e){

        }
        return null;
    }

    public ResultSet excuteQueryRead(String sql){
        try {
            return _connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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

    public boolean resetDatabase() throws Exception {
        final String sql = "{call RESET_DATABASE}";

        try (CallableStatement stmt = _connection.prepareCall(sql)) {
            stmt.execute();
            commitQuery();
            return true;
        } catch (SQLException e) {
            throw new Exception("Lỗi reset database");
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
}
