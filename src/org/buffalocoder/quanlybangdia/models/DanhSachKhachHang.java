package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.KhachHangDAO;

import java.util.ArrayList;

public class DanhSachKhachHang {
    private ArrayList<KhachHang> khachHangs;
    private static KhachHangDAO khachHangDAO;

    public DanhSachKhachHang() throws Exception {
        khachHangDAO = KhachHangDAO.getInstance();
        loadData();
    }


    /**
     * Lấy danh sách khách hàng
     * @return
     */
    public ArrayList<KhachHang> getAll(){
        return khachHangs;
    }


    /**
     * Load danh sách khách hàng từ DB lên
     * @throws Exception
     */
    public void loadData() throws Exception {
        khachHangs = khachHangDAO.getKhachHangs();
    }


    /**
     * Thêm khách hàng mới (không cho thêm trùng mã khách hàng + CMND)
     * Lưu khách hàng vào DB
     * @param khachHang
     * @return
     * @throws Exception
     */
    public void them(KhachHang khachHang) throws Exception {
        // kiểm tra trùng mã khách hàng
        if (khachHang == null && khachHangs.contains(khachHang))
            throw new Exception("Đã có khách hàng này trong hệ thống");

        // kiểm tra trùng CMND
        for (KhachHang khachHang1 : khachHangs){
            if (khachHang.getcMND().equals(khachHang1.getcMND()))
                throw new Exception("Đã có thông tin người này trong hệ thống");
        }

        khachHangs.add(khachHangDAO.themKhachHang(khachHang));
    }


    /**
     * Xoá khách hàng
     * Xoá khách hàng tương ứng trong DB
     * @param maKhachHang
     * @return
     * @throws Exception
     */
    public boolean xoa(String maKhachHang) throws Exception {
        KhachHang khachHang = khachHangs.get(tim(maKhachHang));

        if (khachHang == null)
            return false;

        return khachHangDAO.xoaKhachHang(maKhachHang) && khachHangs.remove(khachHang);
    }


    /**
     * Cập nhật thông tin khách hàng
     * Cập nhật thông tin khách hàng tương ứng trong DB
     * @param khachHang
     * @return
     * @throws Exception
     */
    public boolean sua(KhachHang khachHang) throws Exception {
        return khachHangs.set(tim(khachHang.getMaKH()), khachHangDAO.suaKhachHang(khachHang)) != null;
    }


    /**
     * Tìm vị trí của khách hàng trong danh sách
     * @param maKhachHang
     * @return
     */
    public int tim (String maKhachHang){
        for (int i = 0; i < khachHangs.size(); i++)
            if (khachHangs.get(i).getMaKH().equals(maKhachHang))
                return i;

        return -1;
    }
}
