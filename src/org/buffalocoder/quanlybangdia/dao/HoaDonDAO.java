package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.HoaDon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoaDonDAO {
    private static HoaDonDAO _instance;

    public static HoaDonDAO getInstance() {
        if(_instance == null) {
            synchronized(HoaDonDAO.class) {
                if(null == _instance) {
                    _instance  = new HoaDonDAO();
                }
            }
        }
        return _instance;
    }

    public ArrayList<HoaDon> getHoaDons() {
        ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();

        String sql = "SELECT * FROM VIEW_HOADON";

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);

            while(resultSet.next()) {
                HoaDon hoaDon = new HoaDon(
                        BangDiaDAO.getInstance().getBangDia(resultSet.getString("MABD")),
                        resultSet.getInt("SONGAYDUOCMUON"),
                        resultSet.getInt("SOLUONG"),
                        resultSet.getString("MAHD"),
                        KhachHangDAO.getInstance().getKhachHang(resultSet.getString("MAKH")),
                        resultSet.getDate("NGAYLAP")
                );

                hoaDons.add(hoaDon);
            }
        } catch (SQLException e) {
        }

        return hoaDons;
    }

    public HoaDon getHoaDon (String maHoaDon){
        HoaDon hoaDon = null;
        String sql = String.format("SELECT * FROM VIEW_HOADON WHERE MAHD = '%s'", maHoaDon);

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);
            resultSet.next();

            hoaDon = new HoaDon(
                    BangDiaDAO.getInstance().getBangDia(resultSet.getString("MABD")),
                    resultSet.getInt("SONGAYDUOCMUON"),
                    resultSet.getInt("SOLUONG"),
                    resultSet.getString("MAHD"),
                    KhachHangDAO.getInstance().getKhachHang(resultSet.getString("MAKH")),
                    resultSet.getDate("NGAYLAP")
            );
        } catch (SQLException e) {
        }

        return hoaDon;
    }

    public HoaDon suaHoaDon(HoaDon hoaDon){
        String sql = "UPDATE CHITIETHOADON SET " +
                    "MABD = ?, SONGAYDUOCMUON = ?, SOLUONG = ? WHERE MAHD = ?";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, hoaDon.getBangDia().getMaBangDia());
            ps.setInt(2, hoaDon.getSoNgayDuocMuon());
            ps.setInt(3, hoaDon.getSoLuong());
            ps.setString(4, hoaDon.getMaHoaDon());

            if (ps.executeUpdate() > 0)
                return getHoaDon(hoaDon.getMaHoaDon());

        }catch (Exception e){
            System.out.println("[ERROR]: Thêm hoá đơn");
            e.printStackTrace();
        }

        return null;
    }

    public HoaDon themHoaDon(HoaDon hoaDon){
        String sql = "INSERT INTO HOADON (MAHD, MAKH) VALUES (?,?)";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, hoaDon.getMaHoaDon());
            ps.setString(2, hoaDon.getKhachHang().getMaKH());

            if (ps.executeUpdate() > 0){
                sql = "INSERT INTO CHITIETHOADON (MAHD, MABD, SONGAYDUOCMUON, SOLUONG) VALUES (?, ?, ?, ?)";

                ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

                ps.setString(1, hoaDon.getMaHoaDon());
                ps.setString(2, hoaDon.getBangDia().getMaBangDia());
                ps.setInt(3, hoaDon.getSoNgayDuocMuon());
                ps.setInt(4, hoaDon.getSoLuong());

                if (ps.executeUpdate() > 0)
                    return getHoaDon(hoaDon.getMaHoaDon());
            }
        }catch (Exception e){
            System.out.println("[ERROR]: Thêm hoá đơn");
            e.printStackTrace();
        }

        return null;
    }

    public boolean xoaHoaDon(String maHoaDon){
        String sql = "DELETE FROM CHITIETHOADON WHERE MAHD = ?";

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, maHoaDon);

            if (ps.executeUpdate() > 0){
                sql = "DELETE FROM HOADON WHERE MAHD = ?";

                ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

                ps.setString(1, maHoaDon);

                return ps.executeUpdate() > 0;
            }
            else return false;
        }catch (Exception e){
            System.out.println("[ERROR]: Xoá hoá đơn");
            return false;
        }
    }
}
