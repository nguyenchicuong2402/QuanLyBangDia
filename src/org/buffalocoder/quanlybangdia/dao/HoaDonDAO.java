package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.HoaDon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoaDonDAO {
    private static HoaDonDAO _instance;
    private static DataBaseUtils dataBaseUtils;
    private static BangDiaDAO bangDiaDAO;

    private HoaDonDAO() throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
        bangDiaDAO = BangDiaDAO.getInstance();
    }

    public static HoaDonDAO getInstance() throws Exception {
        if(_instance == null) {
            synchronized(HoaDonDAO.class) {
                if(null == _instance) {
                    _instance  = new HoaDonDAO();
                }
            }
        }
        return _instance;
    }

    public ArrayList<HoaDon> getHoaDons() throws Exception {
        ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();

        String sql = "SELECT * FROM VIEW_HOADON";

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);

            while(resultSet.next()) {
                HoaDon hoaDon = new HoaDon(
                        bangDiaDAO.getBangDia(resultSet.getString("MABD")),
                        resultSet.getInt("SONGAYDUOCMUON"),
                        resultSet.getInt("SOLUONG"),
                        resultSet.getString("MAHD"),
                        KhachHangDAO.getInstance().getKhachHang(resultSet.getString("MAKH")),
                        resultSet.getDate("NGAYLAP")
                );

                hoaDons.add(hoaDon);
            }
        } catch (SQLException e) {
            throw new Exception("Lỗi lấy danh sách hoá đơn");
        }

        return hoaDons;
    }

    public HoaDon getHoaDon (String maHoaDon) throws Exception {
        HoaDon hoaDon = null;
        String sql = String.format("SELECT * FROM VIEW_HOADON WHERE MAHD = '%s'", maHoaDon);

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            hoaDon = new HoaDon(
                    bangDiaDAO.getBangDia(resultSet.getString("MABD")),
                    resultSet.getInt("SONGAYDUOCMUON"),
                    resultSet.getInt("SOLUONG"),
                    resultSet.getString("MAHD"),
                    KhachHangDAO.getInstance().getKhachHang(resultSet.getString("MAKH")),
                    resultSet.getDate("NGAYLAP")
            );
        } catch (Exception e) {
            throw new Exception("Lỗi lấy thông tin hoá đơn");
        }

        return hoaDon;
    }

    public HoaDon suaHoaDon(HoaDon hoaDon) throws Exception {
        String sql = "UPDATE CHITIETHOADON SET " +
                    "MABD = ?, SONGAYDUOCMUON = ?, SOLUONG = ? WHERE MAHD = ?";
        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, hoaDon.getBangDia().getMaBangDia());
            ps.setInt(2, hoaDon.getSoNgayDuocMuon());
            ps.setInt(3, hoaDon.getSoLuong());
            ps.setString(4, hoaDon.getMaHoaDon());

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getHoaDon(hoaDon.getMaHoaDon());
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật thông tin hoá đơn");
        }

        return null;
    }

    public HoaDon themHoaDon(HoaDon hoaDon) throws Exception {
        String sql = "INSERT INTO HOADON (MAHD, MAKH) VALUES (?,?)";
        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, hoaDon.getMaHoaDon());
            ps.setString(2, hoaDon.getKhachHang().getMaKH());

            if (ps.executeUpdate() > 0){
                sql = "INSERT INTO CHITIETHOADON (MAHD, MABD, SONGAYDUOCMUON, SOLUONG) VALUES (?, ?, ?, ?)";

                ps = dataBaseUtils.excuteQueryWrite(sql);

                ps.setString(1, hoaDon.getMaHoaDon());
                ps.setString(2, hoaDon.getBangDia().getMaBangDia());
                ps.setInt(3, hoaDon.getSoNgayDuocMuon());
                ps.setInt(4, hoaDon.getSoLuong());

                if (ps.executeUpdate() > 0){
                    dataBaseUtils.commitQuery();
                    return getHoaDon(hoaDon.getMaHoaDon());
                }
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm hoá đơn");
        }

        return null;
    }

    public boolean xoaHoaDon(String maHoaDon) throws Exception {
        String sql = "DELETE FROM CHITIETHOADON WHERE MAHD = ?";

        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, maHoaDon);

            if (ps.executeUpdate() > 0){
                sql = "DELETE FROM HOADON WHERE MAHD = ?";

                ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

                ps.setString(1, maHoaDon);

                if (ps.executeUpdate() > 0){
                    dataBaseUtils.commitQuery();
                    return true;
                }
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá hoá đơn");
        }

        return false;
    }
}
