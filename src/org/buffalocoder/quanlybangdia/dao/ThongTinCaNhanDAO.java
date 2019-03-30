package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongTinCaNhanDAO {
    private static ThongTinCaNhanDAO _instance;
    private static DataBaseUtils dataBaseUtils;

    public ThongTinCaNhan getThongTinCaNhan(String CMND) throws Exception {
        ThongTinCaNhan thongTinCaNhan = null;

        String sql = String.format("SELECT * FROM THONGTINCANHAN WHERE CMND = '%s'", CMND);

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            thongTinCaNhan = new ThongTinCaNhan(
                    resultSet.getString("CMND"),
                    resultSet.getString("HOTEN"),
                    resultSet.getInt("GIOITINH") == 1,
                    resultSet.getString("DIENTHOAI"),
                    resultSet.getString("DIACHI"),
                    resultSet.getDate("NGAYSINH")
            );
        } catch (SQLException e) {
            throw new Exception("Lỗi lấy thông tin cá nhân");
        }

        return thongTinCaNhan;
    }

    public ThongTinCaNhan themThongTinCaNhan(ThongTinCaNhan thongTinCaNhan) throws Exception {
        String sql = "INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH) " +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, thongTinCaNhan.getcMND());
            ps.setString(2, thongTinCaNhan.getHoTen());
            ps.setString(3, thongTinCaNhan.getSoDienThoai());
            ps.setString(4, thongTinCaNhan.getDiaChi());
            ps.setInt(5, thongTinCaNhan.isGioiTinh() ? 1 : 0);
            ps.setDate(6, thongTinCaNhan.getNgaySinh());

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm thông tin cá nhân");
        }
        return null;
    }

    public boolean xoaThongTinCaNhan(String CMND) throws Exception {
        String sql = "DELETE FROM THONGTINCANHAN WHERE CMND = ?";

        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, CMND);

            if (ps.executeUpdate()>0){
                dataBaseUtils.commitQuery();
                return true;
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá thông tin cá nhân");
        }

        return false;
    }

    public ThongTinCaNhan suaThongTinCaNhan(ThongTinCaNhan thongTinCaNhan) throws Exception {
        String sql = "UPDATE THONGTINCANHAN SET " +
                "HOTEN = ?, DIENTHOAI = ?, DIACHI = ?, GIOITINH = ?, NGAYSINH = ? " +
                "WHERE CMND = ?";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, thongTinCaNhan.getHoTen());
            ps.setString(2, thongTinCaNhan.getSoDienThoai());
            ps.setString(3, thongTinCaNhan.getDiaChi());
            ps.setInt(4, thongTinCaNhan.isGioiTinh() ? 1 : 0);
            ps.setDate(5, thongTinCaNhan.getNgaySinh());
            ps.setString(6, thongTinCaNhan.getcMND());

            if(ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật thông tin cá nhân");
        }

        return null;
    }

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
}
