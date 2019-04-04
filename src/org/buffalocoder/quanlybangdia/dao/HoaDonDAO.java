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
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

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
            resultSet = dataBaseUtils.excuteQueryRead(sql);

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
        } catch (Exception e) {
            throw new Exception("Lỗi lấy danh sách hoá đơn");
        } finally {
            resultSet.close();
        }

        return hoaDons;
    }

    public HoaDon getHoaDon (String maHoaDon) throws Exception {
        HoaDon hoaDon = null;
        String sql = String.format("SELECT * FROM VIEW_HOADON WHERE MAHD = '%s'", maHoaDon);

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
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
        } finally {
            resultSet.close();
        }

        return hoaDon;
    }

    public String getMaHoaDonCuoi () throws Exception {
        String sql = "SELECT TOP 1 MAHD FROM HOADON ORDER BY MAHD DESC";
        String ketQua;

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            ketQua = resultSet.getString("MAHD");
        } catch (SQLException e) {
            throw new Exception("Đọc dữ liệu hoá đơn lỗi");
        } finally {
            resultSet.close();
        }

        return ketQua;
    }

    public HoaDon suaHoaDon(HoaDon hoaDon) throws Exception {
        String sql = "UPDATE CHITIETHOADON SET " +
                    "MABD = ?, SONGAYDUOCMUON = ?, SOLUONG = ? WHERE MAHD = ?";

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, hoaDon.getBangDia().getMaBangDia());
            preparedStatement.setInt(2, hoaDon.getSoNgayDuocMuon());
            preparedStatement.setInt(3, hoaDon.getSoLuong());
            preparedStatement.setString(4, hoaDon.getMaHoaDon());

            if (preparedStatement.executeUpdate() > 0){
                sql = "UPDATE HOADON SET NGAYLAP = ? WHERE MAHD = ?";
                preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

                preparedStatement.setDate(1, hoaDon.getNgayLap());
                preparedStatement.setString(2, hoaDon.getMaHoaDon());

                if (preparedStatement.executeUpdate() > 0){
                    dataBaseUtils.commitQuery();
                    return getHoaDon(hoaDon.getMaHoaDon());
                }
            }
        } catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật thông tin hoá đơn");
        } finally {
            preparedStatement.close();
        }

        return null;
    }

    public HoaDon themHoaDon(HoaDon hoaDon) throws Exception {
        String sql = "INSERT INTO HOADON (MAHD, NGAYLAP, MAKH) VALUES (?,?,?)";
        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, hoaDon.getMaHoaDon());
            preparedStatement.setDate(2, hoaDon.getNgayLap());
            preparedStatement.setString(3, hoaDon.getKhachHang().getMaKH());

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                sql = "INSERT INTO CHITIETHOADON (MAHD, MABD, SONGAYDUOCMUON, SOLUONG) VALUES (?, ?, ?, ?)";

                preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

                preparedStatement.setString(1, hoaDon.getMaHoaDon());
                preparedStatement.setString(2, hoaDon.getBangDia().getMaBangDia());
                preparedStatement.setInt(3, hoaDon.getSoNgayDuocMuon());
                preparedStatement.setInt(4, hoaDon.getSoLuong());

                if (preparedStatement.executeUpdate() > 0){
                    dataBaseUtils.commitQuery();
                    return getHoaDon(hoaDon.getMaHoaDon());
                }
            }
        } catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm hoá đơn");
        } finally {
            preparedStatement.close();
        }

        return null;
    }

    public boolean xoaHoaDon(String maHoaDon) throws Exception {
        String sql = "DELETE FROM CHITIETHOADON WHERE MAHD = ?";

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, maHoaDon);

            if (preparedStatement.executeUpdate() > 0){
                sql = "DELETE FROM HOADON WHERE MAHD = ?";

                preparedStatement = DataBaseUtils.getInstance().excuteQueryWrite(sql);

                preparedStatement.setString(1, maHoaDon);

                if (preparedStatement.executeUpdate() > 0){
                    dataBaseUtils.commitQuery();
                    return true;
                }
            }
        } catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá hoá đơn");
        } finally {
            preparedStatement.close();
        }

        return false;
    }
}
