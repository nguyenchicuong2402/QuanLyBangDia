package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;

import java.util.ArrayList;

public class DanhSachNhanVien {
    private ArrayList<NhanVien> nhanViens;
    private static NhanVienDAO nhanVienDAO;

    public DanhSachNhanVien() throws Exception {
        nhanVienDAO = NhanVienDAO.getInstance();
        loadData();
    }


    /**
     * Lấy danh sách nhân viên
     *
     * @return
     */
    public ArrayList<NhanVien> getAll() {
        return nhanViens;
    }


    /**
     * Load danh sách nhân viên từ DB
     *
     * @throws Exception
     */
    public void loadData() throws Exception {
        nhanViens = nhanVienDAO.getNhanViens();
    }


    /**
     * Thêm nhân viên mới (không cho phép thêm trùng mã nhân viên + CMND)
     * Thêm nhân viên tương ứng vào DB
     *
     * @param nhanVien
     * @return
     * @throws Exception
     */
    public void them(NhanVien nhanVien) throws Exception {
        if (nhanVien == null && nhanViens.contains(nhanVien))
            throw new Exception("Đã có nhân viên trong hệ thống");

        nhanViens.add(nhanVienDAO.themNhanVien(nhanVien));
    }


    /**
     * Xoá nhân viên
     * Xoá nhân viên tương ứng trong DB
     *
     * @param maNhanVien
     * @return
     * @throws Exception
     */
    public boolean xoa(String maNhanVien) throws Exception {
        NhanVien nhanVien = nhanViens.get(tim(maNhanVien));

        if (nhanVien == null)
            return false;

        return nhanVienDAO.xoaNhanVien(maNhanVien) && nhanViens.remove(nhanVien);
    }


    /**
     * Cập nhật thông tin nhân viên
     * Cập nhật thông tin nhân viên tương ứng trong DB
     *
     * @param nhanVien
     * @return
     * @throws Exception
     */
    public boolean sua(NhanVien nhanVien) throws Exception {
        return nhanViens.set(tim(nhanVien.getMaNhanVien()),
                nhanVienDAO.suaNhanVien(nhanVien)) != null;
    }


    /**
     * Tìm vị trí nhân viên trong danh sách
     *
     * @param maNhanVien
     * @return
     */
    public int tim(String maNhanVien) {
        for (int i = 0; i < nhanViens.size(); i++)
            if (nhanViens.get(i).getMaNhanVien().equals(maNhanVien))
                return i;

        return -1;
    }
}
