package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienDAO {
    private static NhanVienDAO _instance;

    public NhanVien getNhanVien(String maNhanVien){
        NhanVien nhanVien = null;

        String sql = String.format("SELECT * FROM VIEW_THONGTINNHANVIEN WHERE MANV = '%s'", maNhanVien);

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);
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
            System.out.println("Lỗi đọc database");
        }

        return nhanVien;
    }

    public ArrayList<NhanVien> getNhanViens(){
        ArrayList<NhanVien> nhanViens = new ArrayList<>();

        String sql = String.format("SELECT * FROM VIEW_THONGTINNHANVIEN");

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);

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
            System.out.println("Lỗi đọc database");
        }

        return nhanViens;
    }

    public NhanVien themNhanVien(NhanVien nhanVien){
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                nhanVien.getcMND(),
                nhanVien.getHoTen(),
                nhanVien.isGioiTinh(),
                nhanVien.getSoDienThoai(),
                nhanVien.getDiaChi(),
                nhanVien.getNgaySinh()
        );

        if (ThongTinCaNhanDAO.getInstance().themThongTinCaNhan(thongTinCaNhan) == null)
            return null;

        String sql = "INSERT INTO NHANVIEN (MANV, CMND, MOTA) VALUES (?,?,?)";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, nhanVien.getMaNhanVien());
            ps.setString(2, nhanVien.getcMND());
            ps.setString(3, nhanVien.getMoTa());

            if (ps.executeUpdate()>0)
                return getNhanVien(nhanVien.getMaNhanVien());
        }catch (Exception e){
            System.out.println("[ERROR]: Thêm nhân viên");
        }

        return null;
    }

    public boolean xoaNhanVien(String maNhanVien){
        String cmnd = getNhanVien(maNhanVien).getcMND();
        String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, maNhanVien);

            if (ps.executeUpdate() > 0)
                return ThongTinCaNhanDAO.getInstance().xoaThongTinCaNhan(cmnd);
            else return false;
        }catch (Exception e){
            System.out.println("[ERROR]: Xoá nhân viên");
            return false;
        }
    }

    public NhanVien suaNhanVien(NhanVien nhanVien){
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                nhanVien.getcMND(),
                nhanVien.getHoTen(),
                nhanVien.isGioiTinh(),
                nhanVien.getSoDienThoai(),
                nhanVien.getDiaChi(),
                nhanVien.getNgaySinh()
        );

        if (ThongTinCaNhanDAO.getInstance().suaThongTinCaNhan(thongTinCaNhan) == null)
            return null;

        String sql = "UPDATE NHANVIEN SET MOTA = ? WHERE MANV = ?";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, nhanVien.getMoTa());
            ps.setString(2, nhanVien.getMaNhanVien());

            if (ps.executeUpdate()>0)
                return getNhanVien(nhanVien.getMaNhanVien());
        }catch (Exception e){
            System.out.println("[ERROR]: Sửa nhân viên");
        }

        return null;
    }

    public static NhanVienDAO getInstance() {
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
