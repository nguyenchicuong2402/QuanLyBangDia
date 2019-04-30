package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ThongTinCaNhanDAO {
    private static ThongTinCaNhanDAO _instance;
    private static DataBaseUtils dataBaseUtils;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;


    /**
     * Tạo kết nối DB
     *
     * @throws Exception
     */
    private ThongTinCaNhanDAO() throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
    }


    /**
     * Design Pattern: Singleton
     *
     * @return
     * @throws Exception
     */
    public static ThongTinCaNhanDAO getInstance() throws Exception {
        if (_instance == null) {
            synchronized (ThongTinCaNhanDAO.class) {
                if (null == _instance) {
                    _instance = new ThongTinCaNhanDAO();
                }
            }
        }
        return _instance;
    }


    /**
     * Lấy thông tin cá nhân
     *
     * @param CMND
     * @return
     * @throws Exception
     */
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


    /**
     * Thêm thông tin cá nhân váo DB
     *
     * @param thongTinCaNhan
     * @return
     * @throws Exception
     */
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

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Thông tin cá nhân người này đã có trong hệ thống");
        } finally {
            preparedStatement.close();
        }

        return null;
    }


    /**
     * Xoá thông tin cá nhân trong DB
     *
     * @param CMND
     * @return
     * @throws Exception
     */
    public boolean xoaThongTinCaNhan(String CMND) throws Exception {
        String sql = "DELETE FROM THONGTINCANHAN WHERE CMND = ?";

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, CMND);

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return true;
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá thông tin cá nhân");
        } finally {
            preparedStatement.close();
        }

        return false;
    }


    /**
     * Cập nhật thông tin cá nhân trong DB
     *
     * @param thongTinCaNhan
     * @return
     * @throws Exception
     */
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

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật thông tin cá nhân");
        } finally {
            preparedStatement.close();
        }

        return null;
    }
}
