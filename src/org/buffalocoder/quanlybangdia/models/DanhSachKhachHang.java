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

        return (khachHangs.add(khachHang)) && (KhachHangDAO.getInstance().themKhachHang(khachHang));
    }
}
