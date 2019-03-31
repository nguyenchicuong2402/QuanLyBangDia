package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.BangDia;

import java.security.spec.ECField;
import java.sql.*;
import java.util.ArrayList;

public class BangDiaDAO {
    private static BangDiaDAO _instance;
    private static DataBaseUtils dataBaseUtils;

    public BangDiaDAO () throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
    }

    public static BangDiaDAO getInstance() throws Exception {
        if (_instance == null) {
            synchronized (BangDiaDAO.class) {
                if (null == _instance) {
                    _instance = new BangDiaDAO();
                }
            }
        }
        return _instance;
    }

    public ArrayList<BangDia> getBangDias() throws Exception {
        ArrayList<BangDia> bangDias = new ArrayList<BangDia>();

        String sql = "SELECT * FROM BANGDIA";

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);

            while (resultSet.next()) {
                BangDia bangDia = new BangDia(
                        resultSet.getString("MABD"),
                        resultSet.getString("TENBD"),
                        resultSet.getString("THELOAI"),
                        resultSet.getBoolean("TINHTRANG"),
                        resultSet.getString("HANGSANXUAT"),
                        resultSet.getString("GHICHU"),
                        resultSet.getDouble("DONGIA"),
                        resultSet.getInt("SOLUONGTON")
                );

                bangDias.add(bangDia);
            }
        } catch (SQLException e) {
            throw new Exception("Đọc danh sách băng đĩa lỗi");
        }

        return bangDias;
    }

    public BangDia getBangDia(String maBangDia) throws Exception {
        BangDia bangDia = null;

        String sql = String.format("SELECT * FROM BANGDIA WHERE MABD = '%s'", maBangDia);

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);

            while (resultSet.next()) {
                bangDia = new BangDia(
                        resultSet.getString("MABD"),
                        resultSet.getString("TENBD"),
                        resultSet.getString("THELOAI"),
                        resultSet.getBoolean("TINHTRANG"),
                        resultSet.getString("HANGSANXUAT"),
                        resultSet.getString("GHICHU"),
                        resultSet.getDouble("DONGIA"),
                        resultSet.getInt("SOLUONGTON")
                );
            }
        } catch (SQLException e) {
            throw new Exception(String.format("Đọc dữ liệu băng đĩa %s lỗi", bangDia.getMaBangDia()));
        }

        return bangDia;
    }

    public BangDia themBangDia(BangDia bangDia) throws Exception {
        if (bangDia == null)
            return null;

        String sql = "INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, bangDia.getMaBangDia());
            ps.setString(2, bangDia.getTenBangDia());
            ps.setString(3, bangDia.getHangSanXuat());
            ps.setString(4, bangDia.getGhiChu());
            ps.setDouble(5, bangDia.getDonGia());
            ps.setBoolean(6, bangDia.isTinhTrang());
            ps.setString(7, bangDia.getTheLoai());
            ps.setInt(8, bangDia.getSoLuongTon());

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getBangDia(bangDia.getMaBangDia());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Thêm băng đĩa lỗi");
        }
        return null;
    }

    public boolean xoaBangDia(String maBangDia) throws Exception {
        if (getBangDia(maBangDia) == null)
            return false;

        String sql = "DELETE FROM BANGDIA WHERE MABD = ?";

        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, maBangDia);

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return true;
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá băng đĩa");
        }

        return false;
    }

    public BangDia suaBangDia(BangDia bangDia) throws Exception {
        if (bangDia == null)
            return null;

        String sql = "UPDATE BANGDIA SET " +
                "TENBD = ?, HANGSANXUAT = ?, GHICHU = ?, DONGIA = ?, " +
                "TINHTRANG = ?, THELOAI = ?, SOLUONGTON = ? WHERE MABD = ?";
        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, bangDia.getTenBangDia());
            ps.setString(2, bangDia.getHangSanXuat());
            ps.setString(3, bangDia.getGhiChu());
            ps.setDouble(4, bangDia.getDonGia());
            ps.setBoolean(5, bangDia.isTinhTrang());
            ps.setString(6, bangDia.getTheLoai());
            ps.setInt(7, bangDia.getSoLuongTon());
            ps.setString(8, bangDia.getMaBangDia());

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getBangDia(bangDia.getMaBangDia());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Cập nhật thông tin băng đĩa lỗi");
        }

        return null;
    }

    public String getMaBangDiaCuoi() throws Exception {
        String sql = "SELECT TOP 1 MABD FROM BANGDIA ORDER BY MABD DESC";

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            return resultSet.getString("MABD");
        } catch (SQLException e) {
            throw new Exception("Đọc dữ liệu băng đĩa lỗi");
        }
    }
}