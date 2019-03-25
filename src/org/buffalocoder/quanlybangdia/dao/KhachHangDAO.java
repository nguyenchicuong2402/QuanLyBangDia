package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.KhachHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangDAO {
    private static KhachHangDAO _instance;

    public KhachHang getKhachHang(String maKhachHang){
        KhachHang khachHang = null;

        String sql = String.format("SELECT * FROM VIEW_THONGTINKHACHHANG WHERE MAKH = '%s'", maKhachHang);

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);
            resultSet.next();

            khachHang = new KhachHang(
                    resultSet.getString("CMND"),
                    resultSet.getString("HOTEN"),
                    resultSet.getBoolean("GIOITINH"),
                    resultSet.getString("DIENTHOAI"),
                    resultSet.getString("DIACHI"),
                    resultSet.getDate("NGAYSINH"),
                    resultSet.getString("MAKH"),
                    resultSet.getDate("NGAYHETHAN")
            );
        } catch (SQLException e) {
            System.out.println("Lỗi đọc database");
        }

        return khachHang;
    }

    public ArrayList<KhachHang> getKhachHangs(){
        ArrayList<KhachHang> khachHangs = new ArrayList<>();

        String sql = String.format("SELECT * FROM VIEW_THONGTINKHACHHANG");

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);

            while (resultSet.next()){
                KhachHang khachHang = new KhachHang(
                        resultSet.getString("CMND"),
                        resultSet.getString("HOTEN"),
                        resultSet.getBoolean("GIOITINH"),
                        resultSet.getString("DIENTHOAI"),
                        resultSet.getString("DIACHI"),
                        resultSet.getDate("NGAYSINH"),
                        resultSet.getString("MAKH"),
                        resultSet.getDate("NGAYHETHAN")
                );

                khachHangs.add(khachHang);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi đọc database");
        }

        return khachHangs;
    }

    public static KhachHangDAO getInstance() {
        if(_instance == null) {
            synchronized(KhachHangDAO.class) {
                if(null == _instance) {
                    _instance  = new KhachHangDAO();
                }
            }
        }
        return _instance;
    }
}
