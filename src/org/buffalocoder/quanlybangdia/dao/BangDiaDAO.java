package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.BangDia;

import java.sql.*;
import java.util.ArrayList;

public class BangDiaDAO {
    private static BangDiaDAO _instance;

    public static BangDiaDAO getInstance() {
        if (_instance == null) {
            synchronized (BangDiaDAO.class) {
                if (null == _instance) {
                    _instance = new BangDiaDAO();
                }
            }
        }
        return _instance;
    }

    public ArrayList<BangDia> getBangDias() {
        ArrayList<BangDia> bangDias = new ArrayList<BangDia>();

        String sql = "SELECT * FROM BANGDIA";

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);

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
            System.out.println("Lấy danh sách băng đĩa lỗi");
        }

        return bangDias;
    }

    public BangDia getBangDia(String maBangDia) {
        BangDia bangDia = null;

        String sql = String.format("SELECT * FROM BANGDIA WHERE MABD = '%s'", maBangDia);

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);

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
            System.out.println("Lấy băng đĩa lỗi");
        }

        return bangDia;
    }

    public BangDia themBangDia(BangDia bangDia) {
        String sql = "INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON)" + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, bangDia.getMaBangDia());
            ps.setString(2, bangDia.getTenBangDia());
            ps.setString(3, bangDia.getHangSanXuat());
            ps.setString(4, bangDia.getGhiChu());
            ps.setDouble(5, bangDia.getDonGia());
            ps.setBoolean(6, bangDia.isTinhTrang());
            ps.setString(7, bangDia.getTheLoai());
            ps.setInt(8, bangDia.getSoLuongTon());

            if (ps.executeUpdate() > 0)
                return getBangDia(bangDia.getMaBangDia());
        } catch (Exception e) {
            System.out.println("[ERROR] Thêm băng đĩa");
        }
        return null;
    }

    public boolean xoaBangDia(String maBangDia) {
        String sql = "DELETE FROM BANGDIA WHERE MABD = ?";

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, maBangDia);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("[ERROR]: Xoá băng đĩa");
            return false;
        }
    }

    public BangDia suaBangDia(BangDia bangDia){
        String sql = "UPDATE BANGDIA SET " +
                "TENBD = ?, HANGSANXUAT = ?, GHICHU = ?, DONGIA = ?, " +
                "TINHTRANG = ?, THELOAI = ?, SOLUONGTON = ? WHERE MABD = ?";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, bangDia.getTenBangDia());
            ps.setString(2, bangDia.getHangSanXuat());
            ps.setString(3, bangDia.getGhiChu());
            ps.setDouble(4, bangDia.getDonGia());
            ps.setBoolean(5, bangDia.isTinhTrang());
            ps.setString(6, bangDia.getTheLoai());
            ps.setInt(7, bangDia.getSoLuongTon());
            ps.setString(8, bangDia.getMaBangDia());

            if (ps.executeUpdate() > 0)
                return getBangDia(bangDia.getMaBangDia());
        } catch (Exception e) {
            System.out.println("[ERROR]Sửa băng đĩa");
            e.printStackTrace();
        }

        return null;
    }
}