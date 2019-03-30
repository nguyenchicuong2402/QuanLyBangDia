package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {
    private static NhanVienDAO _instance;
    private static DataBaseUtils dataBaseUtils;
    private static ThongTinCaNhanDAO thongTinCaNhanDAO;

    public NhanVien getNhanVien(String maNhanVien) throws Exception {
        NhanVien nhanVien = null;

        String sql = String.format("SELECT * FROM VIEW_THONGTINNHANVIEN WHERE MANV = '%s'", maNhanVien);

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            nhanVien = new NhanVien(
                    resultSet.getString("CMND"),
                    resultSet.getString("HOTEN"),
                    resultSet.getInt("GIOITINH") == 1,
                    resultSet.getString("DIENTHOAI"),
                    resultSet.getString("DIACHI"),
                    resultSet.getDate("NGAYSINH"),
                    resultSet.getString("MANV"),
                    resultSet.getString("MOTA")
            );
        } catch (SQLException e) {
            throw new Exception("Lỗi lấy thông tin nhân viên");
        }

        return nhanVien;
    }

    public ArrayList<NhanVien> getNhanViens() throws Exception {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();

        String sql = String.format("SELECT * FROM VIEW_THONGTINNHANVIEN");

        try {
            ResultSet resultSet = dataBaseUtils.excuteQueryRead(sql);

            while (resultSet.next()){
                NhanVien nhanVien = new NhanVien(
                        resultSet.getString("CMND"),
                        resultSet.getString("HOTEN"),
                        resultSet.getInt("GIOITINH") == 1,
                        resultSet.getString("DIENTHOAI"),
                        resultSet.getString("DIACHI"),
                        resultSet.getDate("NGAYSINH"),
                        resultSet.getString("MANV"),
                        resultSet.getString("MOTA")
                );

                nhanViens.add(nhanVien);
            }
        } catch (SQLException e) {
            throw new Exception("Lỗi lấy danh sách nhân viên");
        }

        return nhanViens;
    }

    public NhanVien themNhanVien(NhanVien nhanVien) throws Exception {
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                nhanVien.getcMND(),
                nhanVien.getHoTen(),
                nhanVien.isGioiTinh(),
                nhanVien.getSoDienThoai(),
                nhanVien.getDiaChi(),
                nhanVien.getNgaySinh()
        );

        if (thongTinCaNhanDAO.themThongTinCaNhan(thongTinCaNhan) == null)
            return null;

        String sql = "INSERT INTO NHANVIEN (MANV, CMND, MOTA) VALUES (?,?,?)";
        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, nhanVien.getMaNhanVien());
            ps.setString(2, nhanVien.getcMND());
            ps.setString(3, nhanVien.getMoTa());

            if (ps.executeUpdate()>0){
                dataBaseUtils.commitQuery();
                return getNhanVien(nhanVien.getMaNhanVien());
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm nhân viên");
        }

        return null;
    }

    public boolean xoaNhanVien(String maNhanVien) throws Exception {
        String cmnd = getNhanVien(maNhanVien).getcMND();
        String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";

        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, maNhanVien);

            if (ps.executeUpdate() > 0){
                dataBaseUtils.commitQuery();
                return thongTinCaNhanDAO.xoaThongTinCaNhan(cmnd);
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá thông tin cá nhân");
        }

        return false;
    }

    public NhanVien suaNhanVien(NhanVien nhanVien) throws Exception {
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                nhanVien.getcMND(),
                nhanVien.getHoTen(),
                nhanVien.isGioiTinh(),
                nhanVien.getSoDienThoai(),
                nhanVien.getDiaChi(),
                nhanVien.getNgaySinh()
        );

        if (thongTinCaNhanDAO.suaThongTinCaNhan(thongTinCaNhan) == null)
            return null;

        String sql = "UPDATE NHANVIEN SET MOTA = ? WHERE MANV = ?";
        try {
            PreparedStatement ps = dataBaseUtils.excuteQueryWrite(sql);

            ps.setString(1, nhanVien.getMoTa());
            ps.setString(2, nhanVien.getMaNhanVien());

            if (ps.executeUpdate()>0){
                dataBaseUtils.commitQuery();
                return getNhanVien(nhanVien.getMaNhanVien());
            }
        }catch (Exception e){
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật thông tin nhân viên");
        }

        return null;
    }

    private NhanVienDAO() throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
        thongTinCaNhanDAO = ThongTinCaNhanDAO.getInstance();
    }

    public static NhanVienDAO getInstance() throws Exception {
        if(_instance == null) {
            synchronized(NhanVienDAO.class) {
                if(null == _instance) {
                    _instance  = new NhanVienDAO();
                }
            }
        }
        return _instance;
    }
}
