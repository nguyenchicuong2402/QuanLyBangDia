package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.KhachHang;

import java.sql.PreparedStatement;
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

    public boolean themKhachHang(KhachHang khachHang){
        String sql = "INSERT INTO VIEW_THONGTINKHACHHANG (CMND, MAKH, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, khachHang.getcMND());
            ps.setString(2, khachHang.getMaKH());
            ps.setString(3, khachHang.getHoTen());
            ps.setString(4, khachHang.getSoDienThoai());
            ps.setString(5, khachHang.getDiaChi());
            ps.setInt(6, khachHang.isGioiTinh() ? 1 : 0);
            ps.setDate(7, khachHang.getNgaySinh());

            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("[ERROR]: Thêm khách hàng vào DB");
            return false;
        }
    }

    public boolean xoaKhachHang(String maKhachHang){
        String sql = String.format("DELETE FROM VIEW_THONGTINKHACHHANG WHERE CMND = ?");

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, maKhachHang);

            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("[ERROR]: Xoá Khách hàng trong DB");
            return false;
        }
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
