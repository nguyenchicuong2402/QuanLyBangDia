package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangDAO {
    private static KhachHangDAO _instance;

    public KhachHang getKhachHang(String maKhachHang){
        KhachHang khachHang = null;

        String sql = String.format("SELECT * FROM KHACHHANG WHERE MAKH = '%s'", maKhachHang);

        try {

            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);
            resultSet.next();

            ThongTinCaNhan thongTinCaNhan = ThongTinCaNhanDAO.getInstance().getThongTinCaNhan(resultSet.getString("CMND"));

            khachHang = new KhachHang(
                    thongTinCaNhan.getcMND(),
                    thongTinCaNhan.getHoTen(),
                    thongTinCaNhan.isGioiTinh(),
                    thongTinCaNhan.getSoDienThoai(),
                    thongTinCaNhan.getDiaChi(),
                    thongTinCaNhan.getNgaySinh(),
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
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                khachHang.getcMND(),
                khachHang.getHoTen(),
                khachHang.isGioiTinh(),
                khachHang.getSoDienThoai(),
                khachHang.getDiaChi(),
                khachHang.getNgaySinh()
        );

        if (!ThongTinCaNhanDAO.getInstance().themThongTinCaNhan(thongTinCaNhan))
            return false;

        String sql = "INSERT INTO KHACHHANG (MAKH, CMND) VALUES (?,?)";
        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, khachHang.getMaKH());
            ps.setString(2, khachHang.getcMND());

            return ps.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("[ERROR]: Thêm khách hàng vào DB");
            return false;
        }
    }

    public boolean xoaKhachHang(String maKhachHang){
        String cmnd = getKhachHang(maKhachHang).getcMND();
        String sql = "DELETE FROM KHACHHANG WHERE MAKH = ?";

        try {
            PreparedStatement ps = DataBaseUtils.getInstance().excuteQueryWrite(sql);

            ps.setString(1, maKhachHang);

            if (ps.executeUpdate() > 0)
                return ThongTinCaNhanDAO.getInstance().xoaThongTinCaNhan(cmnd);
            else return false;
        }catch (Exception e){
            System.out.println("[ERROR]: Xoá Khách hàng trong DB");
            return false;
        }
    }

    public boolean suaKhachHang(KhachHang khachHang){
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                khachHang.getcMND(),
                khachHang.getHoTen(),
                khachHang.isGioiTinh(),
                khachHang.getSoDienThoai(),
                khachHang.getDiaChi(),
                khachHang.getNgaySinh()
        );

        if (!ThongTinCaNhanDAO.getInstance().suaThongTinCaNhan(thongTinCaNhan))
            return false;

        return true;
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
