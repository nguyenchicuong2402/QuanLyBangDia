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

    public ArrayList<KhachHang> getAll(){
        return khachHangs;
    }

    public void loadData() throws Exception {
        khachHangs = khachHangDAO.getKhachHangs();
    }

    public boolean them(KhachHang khachHang) throws Exception {
        if (khachHang == null && khachHangs.contains(khachHang))
            return false;

        return khachHangs.add(khachHangDAO.themKhachHang(khachHang));
    }

    public boolean xoa(String maKhachHang) throws Exception {
        KhachHang khachHang = khachHangs.get(tim(maKhachHang));

        if (khachHang == null)
            return false;

        return khachHangDAO.xoaKhachHang(maKhachHang) && khachHangs.remove(khachHang);
    }

    public boolean sua(KhachHang khachHang) throws Exception {
        return khachHangs.set(tim(khachHang.getMaKH()), khachHangDAO.suaKhachHang(khachHang)) != null;
    }

    public int tim (String maKhachHang){
        for (int i = 0; i < khachHangs.size(); i++)
            if (khachHangs.get(i).getMaKH().equals(maKhachHang))
                return i;

        return -1;
    }
}
