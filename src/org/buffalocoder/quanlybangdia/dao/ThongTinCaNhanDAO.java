package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongTinCaNhanDAO {
    private static ThongTinCaNhanDAO _instance;

    public ThongTinCaNhan getThongTinCaNhan(String CMND){
        ThongTinCaNhan thongTinCaNhan = null;

        String sql = String.format("SELECT * FROM THONGTINCANHAN WHERE CMND = '%s'", CMND);

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);
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
            System.out.println("Lỗi đọc database");
        }

        return thongTinCaNhan;
    }

    public ThongTinCaNhan themThongTinCaNhan(ThongTinCaNhan thongTinCaNhan){
        String sql = "INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH) " +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, thongTinCaNhan.getcMND());
            ps.setString(2, thongTinCaNhan.getHoTen());
            ps.setString(3, thongTinCaNhan.getSoDienThoai());
            ps.setString(4, thongTinCaNhan.getDiaChi());
            ps.setInt(5, thongTinCaNhan.isGioiTinh() ? 1 : 0);
            ps.setDate(6, thongTinCaNhan.getNgaySinh());

            if (ps.executeUpdate() > 0){
                System.out.println(getThongTinCaNhan(thongTinCaNhan.getcMND()));
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
            }
        }catch (Exception e){
            System.out.println("[ERROR]: Thêm thông tin cá nhân");
        }
        return null;
    }

    public boolean xoaThongTinCaNhan(String CMND){
        String sql = "DELETE FROM THONGTINCANHAN WHERE CMND = ?";

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, CMND);

            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("[ERROR]: Xoá thông tin cá nhân");
            return false;
        }
    }

    public ThongTinCaNhan suaThongTinCaNhan(ThongTinCaNhan thongTinCaNhan){
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

            if(ps.executeUpdate()>0)
                return getThongTinCaNhan(thongTinCaNhan.getcMND());
        }catch (Exception e){
            System.out.println("[ERROR]: Sửa thông tin cá nhân");
        }

        return null;
    }

    public static ThongTinCaNhanDAO getInstance() {
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
