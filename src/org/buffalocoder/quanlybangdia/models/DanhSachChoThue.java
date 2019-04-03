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

    public ArrayList<HoaDon> getAll(){
        return hoaDons;
    }

    public void loadData() throws Exception {
        hoaDons = hoaDonDAO.getHoaDons();
    }

    public int tim (String maHoaDon){
        for (int i = 0; i < hoaDons.size(); i++)
            if (hoaDons.get(i).getMaHoaDon().equals(maHoaDon))
                return i;

        return -1;
    }

    public boolean them(HoaDon hoaDon) throws Exception {
        if(hoaDon == null || hoaDons.contains(hoaDon))
            return false;

        return  hoaDons.add(hoaDonDAO.themHoaDon(hoaDon));
    }

    public boolean xoa(String maHoaDon) throws Exception {
        HoaDon hoaDon = hoaDons.get(tim(maHoaDon));

        if (hoaDon == null)
            return false;

        return hoaDonDAO.xoaHoaDon(maHoaDon) && hoaDons.remove(hoaDon);
    }

    public boolean sua(HoaDon hoaDon) throws Exception {
        return hoaDons.set(tim(hoaDon.getMaHoaDon()), hoaDonDAO.suaHoaDon(hoaDon)) != null;
    }

    public double tongDoanhThu(){
        double tong = 0;

        for (HoaDon hoaDon : hoaDons){
            tong += hoaDon.thanhTien();
        }

        return tong;
    }

    public int soLuongBangDiaDaThue(){
        int tong = 0;

        for (HoaDon hoaDon : hoaDons)
            tong += hoaDon.getSoLuong();

        return tong;
    }
}
