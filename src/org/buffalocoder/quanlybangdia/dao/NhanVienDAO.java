package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NhanVienDAO {
    private static NhanVienDAO _instance;
    private static DataBaseUtils dataBaseUtils;
    private static ThongTinCaNhanDAO thongTinCaNhanDAO;
    private static TaiKhoanDAO taiKhoanDAO;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;


    /**
     * Kết nối DB
     *
     * @throws Exception
     */
    private NhanVienDAO() throws Exception {
        dataBaseUtils = DataBaseUtils.getInstance();
        thongTinCaNhanDAO = ThongTinCaNhanDAO.getInstance();
        taiKhoanDAO = TaiKhoanDAO.getInstance();
    }


    /**
     * Design Patter: Singleton
     *
     * @return
     * @throws Exception
     */
    public static NhanVienDAO getInstance() throws Exception {
        if (_instance == null) {
            synchronized (NhanVienDAO.class) {
                if (null == _instance) {
                    _instance = new NhanVienDAO();
                }
            }
        }
        return _instance;
    }


    /**
     * Đọc nhân viên từ DB
     *
     * @param maNhanVien
     * @return
     * @throws Exception
     */
    public NhanVien getNhanVien(String maNhanVien) throws Exception {
        NhanVien nhanVien = null;
        String sql = String.format("SELECT * FROM VIEW_THONGTINNHANVIEN WHERE MANV = '%s'", maNhanVien);

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
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
        } catch (Exception e) {
            throw new Exception("Lỗi lấy thông tin nhân viên");
        } finally {
            resultSet.close();
        }

        return nhanVien;
    }


    /**
     * Đọc danh sách nhân viên từ DB
     *
     * @return
     * @throws Exception
     */
    public ArrayList<NhanVien> getNhanViens() throws Exception {
        ArrayList<NhanVien> nhanViens = new ArrayList<>();
        String sql = String.format("SELECT * FROM VIEW_THONGTINNHANVIEN");

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);

            while (resultSet.next()) {
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
        } catch (Exception e) {
            throw new Exception("Lỗi lấy danh sách nhân viên");
        } finally {
            resultSet.close();
        }

        return nhanViens;
    }


    /**
     * Lấy mã nhân viên cuối
     * dùng để generate mã nhân viên mới
     *
     * @return
     * @throws Exception
     */
    public String getMaNhanVienCuoi() throws Exception {
        String sql = "SELECT TOP 1 MANV FROM NHANVIEN ORDER BY MANV DESC";

        try {
            resultSet = dataBaseUtils.excuteQueryRead(sql);
            resultSet.next();

            return resultSet.getString("MANV");
        } catch (Exception e) {
            throw new Exception("Đọc dữ liệu nhân viên lỗi");
        } finally {
            resultSet.close();
        }
    }


    /**
     * Thêm nhân viên mới vào DB
     *
     * @param nhanVien
     * @return
     * @throws Exception
     */
    public NhanVien themNhanVien(NhanVien nhanVien) throws Exception {
        String sql = "INSERT INTO NHANVIEN (MANV, CMND, MOTA) VALUES (?,?,?)";

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

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, nhanVien.getMaNhanVien());
            preparedStatement.setString(2, nhanVien.getcMND());
            preparedStatement.setString(3, nhanVien.getMoTa());

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return getNhanVien(nhanVien.getMaNhanVien());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi thêm nhân viên");
        } finally {
            preparedStatement.close();
        }

        return null;
    }


    /**
     * Xoá nhân viên mới vào DB
     *
     * @param maNhanVien
     * @return
     * @throws Exception
     */
    public boolean xoaNhanVien(String maNhanVien) throws Exception {
        String cmnd = getNhanVien(maNhanVien).getcMND();
        String tenTaiKhoan = taiKhoanDAO.getTaiKhoanByMaNhanVien(maNhanVien).getTenTaiKhoan();
        String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";

        try {
            if (!taiKhoanDAO.xoaTaiKhoan(tenTaiKhoan))
                return false;

            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, maNhanVien);

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return thongTinCaNhanDAO.xoaThongTinCaNhan(cmnd);
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi xoá nhân viên");
        } finally {
            preparedStatement.close();
        }

        return false;
    }


    /**
     * Cập nhật nhân viên vào DB
     *
     * @param nhanVien
     * @return
     * @throws Exception
     */
    public NhanVien suaNhanVien(NhanVien nhanVien) throws Exception {
        String sql = "UPDATE NHANVIEN SET MOTA = ? WHERE MANV = ?";

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

        try {
            preparedStatement = dataBaseUtils.excuteQueryWrite(sql);

            preparedStatement.setString(1, nhanVien.getMoTa());
            preparedStatement.setString(2, nhanVien.getMaNhanVien());

            if (preparedStatement.executeUpdate() > 0) {
                dataBaseUtils.commitQuery();
                return getNhanVien(nhanVien.getMaNhanVien());
            }
        } catch (Exception e) {
            dataBaseUtils.rollbackQuery();
            throw new Exception("Lỗi cập nhật thông tin nhân viên");
        } finally {
            preparedStatement.close();
        }

        return null;
    }
}
