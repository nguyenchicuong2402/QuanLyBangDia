package org.buffalocoder.quanlybangdia.dao;

import jdk.swing.interop.SwingInterOpUtils;
import org.buffalocoder.quanlybangdia.models.NhanVien;

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
