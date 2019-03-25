package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachNhanVien {
    private ArrayList<NhanVien> nhanViens;

    public DanhSachNhanVien(){
        nhanViens = new ArrayList<>();
    }

    public ArrayList<NhanVien> getAll(){
        return nhanViens;
    }

    public void loadData(){
        nhanViens = NhanVienDAO.getInstance().getNhanViens();
    }
}
