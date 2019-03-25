package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.KhachHangDAO;

import java.util.ArrayList;

public class DanhSachKhachHang {
    private ArrayList<KhachHang> khachHangs;

    public DanhSachKhachHang(){
        khachHangs = new ArrayList<>();
    }

    public ArrayList<KhachHang> getAll(){
        return khachHangs;
    }

    public void loadData(){
        khachHangs = KhachHangDAO.getInstance().getKhachHangs();
    }
}
