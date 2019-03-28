package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;
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

    public boolean them(NhanVien nhanVien){
        if (nhanVien == null && nhanViens.contains(nhanVien))
            return false;

        return (NhanVienDAO.getInstance().themNhanVien(nhanVien)) && nhanViens.add(NhanVienDAO.getInstance().getNhanVien(nhanVien.getMaNhanVien()));
    }

    public boolean xoa(String maNhanVien){
        NhanVien nhanVien = nhanViens.get(tim(maNhanVien));

        if (nhanVien == null)
            return false;

        return NhanVienDAO.getInstance().xoaNhanVien(maNhanVien) && nhanViens.remove(nhanVien);
    }

    public boolean sua(NhanVien nhanVien){
        if (nhanVien == null && !nhanViens.contains(nhanVien))
            return false;

        return xoa(nhanVien.getMaNhanVien()) && them(nhanVien);
    }

    public int tim (String maNhanVien){
        for (int i = 0; i < nhanViens.size(); i++)
            if (nhanViens.get(i).getMaNhanVien().equals(maNhanVien))
                return i;

        return -1;
    }
}
