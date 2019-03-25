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
                        resultSet.getDouble("DONGIA")
                );

                bangDias.add(bangDia);
            }
        } catch (SQLException e) {
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
                        resultSet.getDouble("DONGIA")
                );
            }
        } catch (SQLException e) {
        }

        return bangDia;
    }

    public boolean themBangDia(BangDia bd){
        String sql = "INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI)" + "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, bd.getMaBangDia());
            ps.setString(2, bd.getTenBangDia());
            ps.setString(3, bd.getHangSanXuat());
            ps.setString(4, bd.getGhiChu());
            ps.setDouble(5, bd.getDonGia());
            ps.setBoolean(6, bd.isTinhTrang());
            ps.setString(7, bd.getTheLoai());

            return ps.executeUpdate()>0;
        }catch (Exception e){

        }
        return false;
    }

    public boolean xoaBangDia(String maBangDia){
        String sql = String.format("DELETE FROM BANGDIA WHERE MABD = '%s'", maBangDia);

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            return ps.executeUpdate()>0;
        }catch (Exception e){

        }
        return false;
    }
}
