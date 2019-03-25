package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAO {
    private static TaiKhoanDAO _instance;

    public TaiKhoan getTaiKhoan(String tenTaiKhoan){
        TaiKhoan taiKhoan = new TaiKhoan();

        String sql = String.format("SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN = '%s'", tenTaiKhoan);

        try {
            ResultSet rs = DataBaseUtils.getInstance().excuteQueryRead(sql);
            rs.next();

            taiKhoan.setTenTaiKhoan(rs.getString("TENTAIKHOAN"));
            taiKhoan.setMatKhau(rs.getString("MATKHAU"));
            taiKhoan.setLoaiTaiKhoan(rs.getInt("LOAITK"));

        } catch (SQLException e) {
            System.out.println("Lỗi đọc database");
        }

        return taiKhoan;
    }

    public static TaiKhoanDAO getInstance() {
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
