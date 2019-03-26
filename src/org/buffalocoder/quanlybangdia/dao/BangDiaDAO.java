package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.BangDia;

import java.sql.*;
import java.util.ArrayList;

public class BangDiaDAO {
    private static BangDiaDAO _instance;

    public static BangDiaDAO getInstance() {
        if(_instance == null) {
            synchronized(BangDiaDAO.class) {
                if(null == _instance) {
                    _instance  = new BangDiaDAO();
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

            while(resultSet.next()) {
                BangDia bangDia = new BangDia(
                        resultSet.getString("MABD") ,
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

    public BangDia getBangDia(String maBangDia){
        BangDia bangDia = null;

        String sql = String.format("SELECT * FROM BANGDIA WHERE MABD = '%s'", maBangDia);

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);

            while(resultSet.next()) {
                bangDia = new BangDia(
                        resultSet.getString("MABD") ,
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

    public boolean themBangDia(BangDia bangDia){
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

            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Thêm băng đĩa lỗi");
            return false;
        }
    }

    public boolean xoaBangDia(String maBangDia){
        String sql = String.format("DELETE FROM BANGDIA WHERE MABD = '%s'", maBangDia);

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Xoá băng đĩa lỗi");
            return false;
        }
    }

    public boolean suaBangDia(BangDia bangDia){
       return xoaBangDia(bangDia.getMaBangDia()) && themBangDia(bangDia);
    }
}
