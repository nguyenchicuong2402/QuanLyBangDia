package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAO {
    private static TaiKhoanDAO _instance;
    private static DataBaseUtils dataBaseUtils;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

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

    public TaiKhoan getTaiKhoan(String tenTaiKhoan) throws Exception {
        TaiKhoan taiKhoan = new TaiKhoan();
        String sql = String.format("SELECT * FROM TAIKHOAN WHERE TENTAIKHOAN = '%s'", tenTaiKhoan);

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            taiKhoan.setTenTaiKhoan(resultSet.getString("TENTAIKHOAN"));
            taiKhoan.setMatKhau(resultSet.getString("MATKHAU"));
            taiKhoan.setLoaiTaiKhoan(resultSet.getInt("LOAITK"));
            taiKhoan.setMaNhanVien(resultSet.getString("MANV"));
        } catch (Exception e) {
            throw new Exception("Lỗi đọc tài khoản");
        } finally {
            resultSet.close();
        }

        return taiKhoan;
    }

    public TaiKhoan getTaiKhoanByMaNhanVien (String maNhanVien) throws Exception {
        TaiKhoan taiKhoan = new TaiKhoan();
        String sql = String.format("SELECT * FROM TAIKHOAN WHERE MANV = '%s'", maNhanVien);

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            taiKhoan.setTenTaiKhoan(resultSet.getString("TENTAIKHOAN"));
            taiKhoan.setMatKhau(resultSet.getString("MATKHAU"));
            taiKhoan.setLoaiTaiKhoan(resultSet.getInt("LOAITK"));
            taiKhoan.setMaNhanVien(resultSet.getString("MANV"));

            dataBaseUtils.commitQuery();
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi đọc tài khoản");
        } finally {
            resultSet.close();
        }

        return taiKhoan;
    }

    public TaiKhoan themTaiKhoan(TaiKhoan taiKhoan) throws Exception {
        String sql = "INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, LOAITK, MANV) VALUES (?, ?, ?, ?)";

        if (taiKhoan == null)
            return null;

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, taiKhoan.getTenTaiKhoan());
            preparedStatement.setString(2, taiKhoan.getMatKhau());
            preparedStatement.setInt(3, taiKhoan.getLoaiTaiKhoan());
            preparedStatement.setString(4, taiKhoan.getMaNhanVien());

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getTaiKhoan(taiKhoan.getTenTaiKhoan());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            e.printStackTrace();
            throw new Exception("Lỗi thêm tài khoản");
        } finally {
            preparedStatement.close();
        }

        return null;
    }

    public TaiKhoan suaTaiKhoan (TaiKhoan taiKhoan) throws Exception {
        String sql = "UPDATE TAIKHOAN SET MATKHAU = ?, LOAITK = ? WHERE TENTAIKHOAN = ?";

        if (taiKhoan == null)
            return null;

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, taiKhoan.getMatKhau());
            preparedStatement.setInt(2, taiKhoan.getLoaiTaiKhoan());
            preparedStatement.setString(3, taiKhoan.getTenTaiKhoan());

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return getTaiKhoan(taiKhoan.getTenTaiKhoan());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật tài khoản");
        } finally {
            preparedStatement.close();
        }

        return null;
    }

    public boolean xoaTaiKhoan (String tenTaiKhoan) throws Exception {
        String sql = "DELETE FROM TAIKHOAN WHERE TENTAIKHOAN = ?";

        if (getTaiKhoan(tenTaiKhoan) == null)
            return false;

        try{
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, tenTaiKhoan);

            if (preparedStatement.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return true;
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá tài khoản");
        } finally {
            preparedStatement.close();
        }

        return false;
    }
}
