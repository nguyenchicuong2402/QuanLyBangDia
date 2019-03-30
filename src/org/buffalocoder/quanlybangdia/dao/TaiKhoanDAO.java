package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAO {
    private static TaiKhoanDAO _instance;
    private static DataBaseUtils dataBaseUtils;

    public TaiKhoan getTaiKhoan(String tenTaiKhoan) throws Exception {
        TaiKhoan taiKhoan = new TaiKhoan();

        String sql = String.format("SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN = '%s'", tenTaiKhoan);

        try {
            ResultSet rs = dataBaseUtils.excuteQueryRead(sql);
            rs.next();

            taiKhoan.setTenTaiKhoan(rs.getString("TENTAIKHOAN"));
            taiKhoan.setMatKhau(rs.getString("MATKHAU"));
            taiKhoan.setLoaiTaiKhoan(rs.getInt("LOAITK"));

        } catch (SQLException e) {
            throw new Exception("Lỗi đọc tài khoản");
        }

        return taiKhoan;
    }

    public TaiKhoan themTaiKhoan(TaiKhoan taiKhoan) throws Exception {
        if (taiKhoan == null)
            return null;

        String sql = "INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, LOAITK, MANV) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, taiKhoan.getTenTaiKhoan());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setInt(3, taiKhoan.getLoaiTaiKhoan());
            ps.setString(4, taiKhoan.getNhanVien().getMaNhanVien());

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getTaiKhoan(taiKhoan.getTenTaiKhoan());
            }
        } catch (SQLException e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm tài khoản");
        }

        return null;
    }

    public TaiKhoan suaTaiKhoan (TaiKhoan taiKhoan) throws Exception {
        if (taiKhoan == null)
            return null;

        String sql = "UPDATE TAIKHOAN SET MATKHAU = ?, LOAITK = ? WHERE TENTAIKHOAN = ?";

        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, taiKhoan.getMatKhau());
            ps.setInt(2, taiKhoan.getLoaiTaiKhoan());
            ps.setString(3, taiKhoan.getTenTaiKhoan());

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getTaiKhoan(taiKhoan.getTenTaiKhoan());
            }
        } catch (SQLException e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật tài khoản");
        }

        return null;
    }

    public boolean xoaTaiKhoan (String tenTaiKhoan) throws Exception {
        if (getTaiKhoan(tenTaiKhoan) == null)
            return false;

        String sql = "DELETE FROM TAIKHOAN WHERE TENTAIKHOAN = ?";

        try{
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, tenTaiKhoan);

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return true;
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá tài khoản");
        }

        return false;
    }

    private TaiKhoanDAO() throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
    }

    public static TaiKhoanDAO getInstance() throws Exception {
        if(_instance == null) {
            synchronized(TaiKhoanDAO.class) {
                if(null == _instance) {
                    _instance  = new TaiKhoanDAO();
                }
            }
        }
        return _instance;
    }
}
