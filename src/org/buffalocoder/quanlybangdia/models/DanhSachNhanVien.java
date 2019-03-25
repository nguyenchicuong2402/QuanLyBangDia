package org.buffalocoder.quanlybangdia.models;

import java.util.ArrayList;

public class DanhSachNhanVien {
    private ArrayList<NhanVien> nhanViens;

    public DanhSachNhanVien(){
        nhanViens = new ArrayList<>();
    }

    public ArrayList<NhanVien> getAll(){
        return nhanViens;
    }
}
