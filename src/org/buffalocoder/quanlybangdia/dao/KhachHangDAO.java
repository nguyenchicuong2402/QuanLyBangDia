package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class KhachHangDAO {
    private static KhachHangDAO _instance;
    private static DataBaseUtils dataBaseUtils;
    private static ThongTinCaNhanDAO thongTinCaNhanDAO;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;


    /**
     * Tạo kết nối DB
     *
     * @throws Exception
     */
    private KhachHangDAO() throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
        thongTinCaNhanDAO = ThongTinCaNhanDAO.getInstance();
    }


    /**
     * Design Pattern: Singleton
     *
     * @return
     * @throws Exception
     */
    public static KhachHangDAO getInstance() throws Exception {
        if (_instance == null) {
            synchronized (KhachHangDAO.class) {
                if (null == _instance) {
                    _instance = new KhachHangDAO();
                }
            }
        }
        return _instance;
    }


    /**
     * Đọc khách hàng từ DB
     *
     * @param maKhachHang
     * @return
     * @throws Exception
     */
    public KhachHang getKhachHang(String maKhachHang) throws Exception {
        KhachHang khachHang = null;
        String sql = String.format("SELECT * FROM VIEW_THONGTINKHACHHANG WHERE MAKH = '%s'", maKhachHang);

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
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

        } catch (Exception e) {
            throw new Exception("Lỗi lấy thông tin khách hàng");
        } finally {
            resultSet.close();
        }

        return khachHang;
    }


    /**
     * Đọc danh sách khách hàng trong Db
     *
     * @return
     * @throws Exception
     */
    public ArrayList<KhachHang> getKhachHangs() throws Exception {
        ArrayList<KhachHang> khachHangs = new ArrayList<>();
        String sql = String.format("SELECT * FROM VIEW_THONGTINKHACHHANG");

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);

            while (resultSet.next()) {
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
        } catch (Exception e) {
            throw new Exception("Lỗi lấy danh sách khách hàng");
        } finally {
            resultSet.close();
        }

        return khachHangs;
    }


    /**
     * Lấy mã khách hàng cuối
     * dùng để generate mã khách hàng mới
     *
     * @return
     * @throws Exception
     */
    public String getMaKhachHangCuoi() throws Exception {
        String sql = "SELECT TOP 1 MAKH FROM KHACHHANG ORDER BY MAKH DESC";

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            return resultSet.getString("MAKH");
        } catch (Exception e) {
            throw new Exception("Đọc dữ liệu khách hàng lỗi");
        } finally {
            resultSet.close();
        }
    }


    /**
     * Thêm khách hàng mới vào DB
     *
     * @param khachHang
     * @return
     * @throws Exception
     */
    public KhachHang themKhachHang(KhachHang khachHang) throws Exception {
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                khachHang.getcMND(),
                khachHang.getHoTen(),
                khachHang.isGioiTinh(),
                khachHang.getSoDienThoai(),
                khachHang.getDiaChi(),
                khachHang.getNgaySinh()
        );

        if (thongTinCaNhanDAO.themThongTinCaNhan(thongTinCaNhan) == null)
            return null;

        String sql = "INSERT INTO KHACHHANG (MAKH, CMND) VALUES (?,?)";
        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, khachHang.getMaKH());
            preparedStatement.setString(2, khachHang.getcMND());

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return getKhachHang(khachHang.getMaKH());
            }

        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm khách hàng");
        } finally {
            preparedStatement.close();
        }

        return null;
    }


    /**
     * Xoá khách hàng trong DB
     *
     * @param maKhachHang
     * @return
     * @throws Exception
     */
    public boolean xoaKhachHang(String maKhachHang) throws Exception {
        String cmnd = getKhachHang(maKhachHang).getcMND();
        String sql = "DELETE FROM KHACHHANG WHERE MAKH = ?";

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, maKhachHang);

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return thongTinCaNhanDAO.xoaThongTinCaNhan(cmnd);
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá khách hàng");
        } finally {
            preparedStatement.close();
        }

        return false;
    }


    /**
     * Cập nhật thông tin khách hàng trong DB
     *
     * @param khachHang
     * @return
     * @throws Exception
     */
    public KhachHang suaKhachHang(KhachHang khachHang) throws Exception {
        ThongTinCaNhan thongTinCaNhan = new ThongTinCaNhan(
                khachHang.getcMND(),
                khachHang.getHoTen(),
                khachHang.isGioiTinh(),
                khachHang.getSoDienThoai(),
                khachHang.getDiaChi(),
                khachHang.getNgaySinh()
        );

        if (thongTinCaNhanDAO.suaThongTinCaNhan(thongTinCaNhan) == null)
            return null;

        return getKhachHang(khachHang.getMaKH());
    }
}
