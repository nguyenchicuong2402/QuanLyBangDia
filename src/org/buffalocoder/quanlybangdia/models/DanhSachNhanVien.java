package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;
import java.util.ArrayList;

public class DanhSachNhanVien {
    private ArrayList<NhanVien> nhanViens;

    public DanhSachNhanVien(){
        loadData();
    }

    public ArrayList<NhanVien> getAll(){
        return nhanViens;
    }

    public void loadData(){
        nhanViens = NhanVienDAO.getInstance().getNhanViens();
    }

    public boolean them(NhanVien nhanVien){
        if (nhanVien == null && nhanViens.contains(nhanVien))
            return false;

        return nhanViens.add(NhanVienDAO.getInstance().themNhanVien(nhanVien));
    }

    public boolean xoa(String maNhanVien){
        NhanVien nhanVien = nhanViens.get(tim(maNhanVien));

        if (nhanVien == null)
            return false;

        return NhanVienDAO.getInstance().xoaNhanVien(maNhanVien) && nhanViens.remove(nhanVien);
    }

    public boolean sua(NhanVien nhanVien){
        return nhanViens.set(tim(nhanVien.getMaNhanVien()),
                NhanVienDAO.getInstance().suaNhanVien(nhanVien)) != null;
    }

    public int tim (String maNhanVien){
        for (int i = 0; i < nhanViens.size(); i++)
            if (nhanViens.get(i).getMaNhanVien().equals(maNhanVien))
                return i;

        return -1;
    }
}
