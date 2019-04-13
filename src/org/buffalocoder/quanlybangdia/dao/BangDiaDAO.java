package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.BangDia;

import java.sql.*;
import java.util.ArrayList;

public class BangDiaDAO {
    private static BangDiaDAO _instance;
    private static DataBaseUtils dataBaseUtils;

    public BangDiaDAO () throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
    }


    /**
     * Design Pattern: Singleton
     * @return
     * @throws Exception
     */
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


    /**
     * Đọc danh sách băng đĩa từ DB
     * @return
     * @throws Exception
     */
    public ArrayList<BangDia> getBangDias() throws Exception {
        ArrayList<BangDia> bangDias = new ArrayList<BangDia>();
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM BANGDIA";

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);

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

        } catch (Exception e) {
            throw new Exception("Đọc danh sách băng đĩa lỗi");
        } finally {
            resultSet.close();
        }

        return bangDias;
    }


    /**
     * Lấy băng đĩa từ DB
     * @param maBangDia
     * @return
     * @throws Exception
     */
    public BangDia getBangDia(String maBangDia) throws Exception {
        BangDia bangDia = null;
        String sql = String.format("SELECT * FROM BANGDIA WHERE MABD = '%s'", maBangDia);
        ResultSet resultSet = null;

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);

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
        } finally {
            resultSet.close();
        }

        return bangDia;
    }


    /**
     * Lấy mã băng đĩa cuối trong DB
     * Dùng để generate mã băng đĩa mới
     * @return
     * @throws Exception
     */
    public String getMaBangDiaCuoi() throws Exception {
        String sql = "SELECT TOP 1 MABD FROM BANGDIA ORDER BY MABD DESC";
        ResultSet resultSet = null;
        String ketQua;

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            ketQua = resultSet.getString("MABD");
        } catch (SQLException e) {
            throw new Exception("Đọc dữ liệu băng đĩa lỗi");
        } finally {
            resultSet.close();
        }

        return ketQua;
    }


    /**
     * Thêm băng đĩa mới vào DB
     * @param bangDia
     * @return
     * @throws Exception
     */
    public BangDia themBangDia(BangDia bangDia) throws Exception {
        String sql = "INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        if (bangDia == null)
            return null;

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, bangDia.getMaBangDia());
            preparedStatement.setString(2, bangDia.getTenBangDia());
            preparedStatement.setString(3, bangDia.getHangSanXuat());
            preparedStatement.setString(4, bangDia.getGhiChu());
            preparedStatement.setDouble(5, bangDia.getDonGia());
            preparedStatement.setBoolean(6, bangDia.isTinhTrang());
            preparedStatement.setString(7, bangDia.getTheLoai());
            preparedStatement.setInt(8, bangDia.getSoLuongTon());

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getBangDia(bangDia.getMaBangDia());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Thêm băng đĩa lỗi");
        } finally {
            preparedStatement.close();
        }

        return null;
    }


    /**
     * Xoá băng đĩa trong DB
     * @param maBangDia
     * @return
     * @throws Exception
     */
    public boolean xoaBangDia(String maBangDia) throws Exception {
        String sql = "DELETE FROM BANGDIA WHERE MABD = ?";
        PreparedStatement preparedStatement = null;

        if (getBangDia(maBangDia) == null)
            return false;

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, maBangDia);

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return true;
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá băng đĩa");
        } finally {
            preparedStatement.close();
        }

        return false;
    }


    /**
     * Cập nhật thông tin băng đĩa trong DB
     * @param bangDia
     * @return
     * @throws Exception
     */
    public BangDia suaBangDia(BangDia bangDia) throws Exception {
        String sql = "UPDATE BANGDIA SET " +
                "TENBD = ?, HANGSANXUAT = ?, GHICHU = ?, DONGIA = ?, " +
                "TINHTRANG = ?, THELOAI = ?, SOLUONGTON = ? WHERE MABD = ?";
        PreparedStatement preparedStatement = null;

        if (bangDia == null)
            return null;

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, bangDia.getTenBangDia());
            preparedStatement.setString(2, bangDia.getHangSanXuat());
            preparedStatement.setString(3, bangDia.getGhiChu());
            preparedStatement.setDouble(4, bangDia.getDonGia());
            preparedStatement.setBoolean(5, bangDia.isTinhTrang());
            preparedStatement.setString(6, bangDia.getTheLoai());
            preparedStatement.setInt(7, bangDia.getSoLuongTon());
            preparedStatement.setString(8, bangDia.getMaBangDia());

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getBangDia(bangDia.getMaBangDia());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Cập nhật thông tin băng đĩa lỗi");
        } finally {
            preparedStatement.close();
        }

        return null;
    }
}