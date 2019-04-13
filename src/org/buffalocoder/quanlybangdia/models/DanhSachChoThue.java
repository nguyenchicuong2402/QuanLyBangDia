package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.HoaDonDAO;

import java.util.ArrayList;

public class DanhSachChoThue {
    private ArrayList<HoaDon> hoaDons;
    private static HoaDonDAO hoaDonDAO;

    public DanhSachChoThue() throws Exception {
        hoaDonDAO = HoaDonDAO.getInstance();
        loadData();
    }


    /**
     * Lấy tất cả danh sách cho thuê
     * @return
     */
    public ArrayList<HoaDon> getAll(){
        return hoaDons;
    }


    /**
     * Load danh sách hoá đơn từ DB
     * @throws Exception
     */
    public void loadData() throws Exception {
        hoaDons = hoaDonDAO.getHoaDons();
    }


    /**
     * tìm vị trí hoá đơn trong danh sách
     * @param maHoaDon
     * @return
     */
    public int tim (String maHoaDon){
        for (int i = 0; i < hoaDons.size(); i++)
            if (hoaDons.get(i).getMaHoaDon().equals(maHoaDon))
                return i;

        return -1;
    }


    /**
     * Thêm hoá đơn mới (không cho thêm hoá đơn trùng mã)
     * Lưu hoá đơn vào DB
     * @param hoaDon
     * @return
     * @throws Exception
     */
    public boolean them(HoaDon hoaDon) throws Exception {
        if(hoaDon == null || hoaDons.contains(hoaDon))
            return false;

        return  hoaDons.add(hoaDonDAO.themHoaDon(hoaDon));
    }


    /**
     * Xoá hoá đơn trong danh sách
     * Xoá hoá đơn tương ứng trong DB
     * @param maHoaDon
     * @return
     * @throws Exception
     */
    public boolean xoa(String maHoaDon) throws Exception {
        HoaDon hoaDon = hoaDons.get(tim(maHoaDon));

        if (hoaDon == null)
            return false;

        return hoaDonDAO.xoaHoaDon(maHoaDon) && hoaDons.remove(hoaDon);
    }


    /**
     * Cập nhật thông tin hoá đơn
     * Cập nhật thông tin tương ứng trong DB
     * @param hoaDon
     * @return
     * @throws Exception
     */
    public boolean sua(HoaDon hoaDon) throws Exception {
        return hoaDons.set(tim(hoaDon.getMaHoaDon()), hoaDonDAO.suaHoaDon(hoaDon)) != null;
    }


    /**
     * Phương thức tính tổng doanh thu (theo tháng, năm và tất cả)
     * Nếu tháng = 0 thì tính tổng doanh thu cả năm
     * Nếu năm = 0 thì tính tổng doanh thu của công ty
     * @param thang
     * @param nam
     * @return
     */
    public double tongDoanhThu(int thang, int nam){
        double tong = 0;

        for (HoaDon hoaDon : hoaDons){
            if (hoaDon.isTinhTrang()){
                if (thang == 0 && nam == 0){
                    tong += hoaDon.thanhTien();
                }else if (thang == 0 && hoaDon.getNgayLap().toLocalDate().getYear() == nam)
                    tong += hoaDon.thanhTien();
                else if (hoaDon.getNgayLap().toLocalDate().getMonth().getValue() == thang &&
                        hoaDon.getNgayLap().toLocalDate().getYear() == nam)
                    tong += hoaDon.thanhTien();
            }
        }

        return tong;
    }


    /**
     * Phương thức thanh toán hoá đơn
     * Cập nhật thông tin hoá đơn tương ứng trong DB
     * @param maHoaDon
     * @throws Exception
     */
    public void thanhToanHoaDon(String maHoaDon) throws Exception {
        hoaDonDAO.thanhToanHoaDon(maHoaDon);
    }


    /**
     * Lấy tổng số băng đĩa đã cho thuê
     * @return
     */
    public int soLuongBangDiaDaThue(){
        int tong = 0;

        for (HoaDon hoaDon : hoaDons)
            if (!hoaDon.isTinhTrang())
                tong += hoaDon.getSoLuong();

        return tong;
    }
}
