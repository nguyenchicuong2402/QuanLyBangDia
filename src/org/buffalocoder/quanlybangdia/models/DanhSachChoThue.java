package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.HoaDonDAO;

import java.util.ArrayList;

public class DanhSachChoThue {
    private ArrayList<HoaDon> hoaDons;

    public DanhSachChoThue(){
        hoaDons = new ArrayList<>();
    }

    public ArrayList<HoaDon> getAll(){
        return hoaDons;
    }

    public void loadData(){
        hoaDons = HoaDonDAO.getInstance().getHoaDons();
    }

    public int tim (String maHoaDon){
        for (int i = 0; i < hoaDons.size(); i++)
            if (hoaDons.get(i).getMaHoaDon().equals(maHoaDon))
                return i;

        return -1;
    }

    public boolean them(HoaDon hoaDon){
        if(hoaDon == null || hoaDons.contains(hoaDon))
            return false;

        return  (hoaDons.add(hoaDon) && HoaDonDAO.getInstance().themHoaDon(hoaDon));
    }

    public boolean xoa(String maHoaDon){
        HoaDon hoaDon = hoaDons.get(tim(maHoaDon));

        if (hoaDon == null)
            return false;

        return (hoaDons.remove(hoaDon) && HoaDonDAO.getInstance().xoaHoaDon(maHoaDon));
    }

    public boolean sua(HoaDon hoaDon){
        return xoa(hoaDon.getMaHoaDon()) && them(hoaDon);
    }
}
