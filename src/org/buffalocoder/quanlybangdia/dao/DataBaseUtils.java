package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.XML.QuanLyXML;

import java.sql.*;

public class DataBaseUtils {
    private static DataBaseUtils _instance;
    private static QuanLyXML quanLyXML;
    private static final String TEMPLATE_URL = "jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s";
    private static String url = "";

    private static Connection _connection;

    private DataBaseUtils() throws Exception {
        quanLyXML = new QuanLyXML();
        final String config[] = quanLyXML.getConfigDatabase();

        url = String.format(TEMPLATE_URL, config[0], Integer.parseInt(config[1]), config[2], config[3], config[4]);

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
            rollbackQuery();
            throw new Exception("Lỗi reset database");
        }
    }

    public boolean excuteProcedure(String sql) throws Exception {
        try (CallableStatement stmt = _connection.prepareCall(sql)) {
            stmt.execute();
            commitQuery();
            return true;
        } catch (SQLException e) {
            rollbackQuery();
            throw new Exception("Lỗi reset database");
        }
    }

    private Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            throw new Exception("Không tìm thấy thư viện JDBC");
        } catch (SQLException e) {
            throw new Exception("Kết nối database thất bại");
        }
    }
}
