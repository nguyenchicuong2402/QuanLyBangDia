package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.BangDia;

import java.sql.*;
import java.util.ArrayList;

public class BangDiaDB {
    public ArrayList<BangDia> getAllBangDia() {
        ArrayList<BangDia> dsBangDia = new ArrayList<BangDia>();

        Connection conn = DBConnection.getConnection();
        try {
            System.out.println("Kết nối thành công DB");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BANGDIA");
            while(rs.next()) {
                String maBD = rs.getString("MABD");
                String tenBD = rs.getString("TENBD");
                String hangSX = rs.getString("HANGSANXUAT");
                String ghiChu = rs.getString("GHICHU");
                double donGia = rs.getDouble("DONGIA");
                boolean tinhTrang = rs.getBoolean("TINHTRANG");
                String theLoai = rs.getString("THELOAI");
// BangDia(String maBangDia, String tenBangDia, String theLoai, boolean tinhTrang, String hangSanXuat, String ghiChu, Double donGia)
                BangDia bd = new BangDia(maBD, tenBD, theLoai, tinhTrang, hangSX, ghiChu, donGia);
                dsBangDia.add(bd);
            }
            return  dsBangDia;
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return null;
    }

    public boolean themDataBase(BangDia bd){
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI)" + "VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
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
    public boolean xoaDatabase(String maBD){
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM BANGDIA WHERE MABD =" + maBD;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            return ps.executeUpdate()>0;
        }catch (Exception e){

        }
        return false;
    }
}
