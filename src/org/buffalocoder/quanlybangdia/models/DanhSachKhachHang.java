package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.KhachHangDAO;

import java.util.ArrayList;

public class DanhSachKhachHang {
    private ArrayList<KhachHang> khachHangs;

    public DanhSachKhachHang(){
        loadData();
    }

    public ArrayList<KhachHang> getAll(){
        return khachHangs;
    }

    public void loadData(){
        khachHangs = KhachHangDAO.getInstance().getKhachHangs();
    }

    public boolean them(KhachHang khachHang){
        if (khachHang == null && khachHangs.contains(khachHang))
            return false;

        return khachHangs.add(KhachHangDAO.getInstance().themKhachHang(khachHang));
    }

    public boolean xoa(String maKhachHang){
        KhachHang khachHang = khachHangs.get(tim(maKhachHang));

        if (khachHang == null)
            return false;

        return KhachHangDAO.getInstance().xoaKhachHang(maKhachHang) && khachHangs.remove(khachHang);
    }

    public boolean sua(KhachHang khachHang){
        return khachHangs.set(tim(khachHang.getMaKH()), KhachHangDAO.getInstance().suaKhachHang(khachHang)) != null;
    }

    public int tim (String maKhachHang){
        for (int i = 0; i < khachHangs.size(); i++)
            if (khachHangs.get(i).getMaKH().equals(maKhachHang))
                return i;

        return -1;
    }
}
