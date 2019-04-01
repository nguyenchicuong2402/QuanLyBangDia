package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongTinCaNhanDAO {
    private static ThongTinCaNhanDAO _instance;
    private static DataBaseUtils dataBaseUtils;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private ThongTinCaNhanDAO() throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
    }

    public static ThongTinCaNhanDAO getInstance() throws Exception {
        if(_instance == null) {
            synchronized(ThongTinCaNhanDAO.class) {
                if(null == _instance) {
                    _instance  = new ThongTinCaNhanDAO();
                }
            }
        }
        return _instance;
    }

    public ThongTinCaNhan getThongTinCaNhan(String CMND) throws Exception {
        ThongTinCaNhan thongTinCaNhan = null;
        String sql = String.format("SELECT * FROM THONGTINCANHAN WHERE CMND = '%s'", CMND);

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            thongTinCaNhan = new ThongTinCaNhan(
                    resultSet.getString("CMND"),
                    resultSet.getString("HOTEN"),
                    resultSet.getInt("GIOITINH") == 1,
                    resultSet.getString("DIENTHOAI"),
                    resultSet.getString("DIACHI"),
                    resultSet.getDate("NGAYSINH")
            );
        } catch (Exception e) {
            throw new Exception("Lỗi lấy thông tin cá nhân");
        } finally {
            resultSet.close();
        }

        return thongTinCaNhan;
    }

    public ThongTinCaNhan themThongTinCaNhan(ThongTinCaNhan thongTinCaNhan) throws Exception {
        String sql = "INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH) " +
                "VALUES (?,?,?,?,?,?)";

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, thongTinCaNhan.getcMND());
            preparedStatement.setString(2, thongTinCaNhan.getHoTen());
            preparedStatement.setString(3, thongTinCaNhan.getSoDienThoai());
            preparedStatement.setString(4, thongTinCaNhan.getDiaChi());
            preparedStatement.setInt(5, thongTinCaNhan.isGioiTinh() ? 1 : 0);
            preparedStatement.setDate(6, thongTinCaNhan.getNgaySinh());

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
            }
        } catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm thông tin cá nhân");
        } finally {
            preparedStatement.close();
        }

        return null;
    }

    public boolean xoaThongTinCaNhan(String CMND) throws Exception {
        String sql = "DELETE FROM THONGTINCANHAN WHERE CMND = ?";

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, CMND);

            if (preparedStatement.executeUpdate()>0){
                dataBaseUtils.commitQuery();
                return true;
            }
        } catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá thông tin cá nhân");
        } finally {
            preparedStatement.close();
        }

        return false;
    }

    public ThongTinCaNhan suaThongTinCaNhan(ThongTinCaNhan thongTinCaNhan) throws Exception {
        String sql = "UPDATE THONGTINCANHAN SET " +
                "HOTEN = ?, DIENTHOAI = ?, DIACHI = ?, GIOITINH = ?, NGAYSINH = ? " +
                "WHERE CMND = ?";

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, thongTinCaNhan.getHoTen());
            preparedStatement.setString(2, thongTinCaNhan.getSoDienThoai());
            preparedStatement.setString(3, thongTinCaNhan.getDiaChi());
            preparedStatement.setInt(4, thongTinCaNhan.isGioiTinh() ? 1 : 0);
            preparedStatement.setDate(5, thongTinCaNhan.getNgaySinh());
            preparedStatement.setString(6, thongTinCaNhan.getcMND());

            if(preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
            }
        } catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật thông tin cá nhân");
        } finally {
            preparedStatement.close();
        }

        return null;
    }
}
