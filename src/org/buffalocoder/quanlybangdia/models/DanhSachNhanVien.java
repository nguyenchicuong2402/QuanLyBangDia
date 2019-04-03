package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;
import org.buffalocoder.quanlybangdia.dao.TaiKhoanDAO;

import java.util.ArrayList;

public class DanhSachNhanVien {
    private ArrayList<NhanVien> nhanViens;
    private static NhanVienDAO nhanVienDAO;

    public DanhSachNhanVien() throws Exception {
        nhanVienDAO = NhanVienDAO.getInstance();
        loadData();
    }

    public ArrayList<NhanVien> getAll(){
        return nhanViens;
    }

    public void loadData() throws Exception {
        nhanViens = nhanVienDAO.getNhanViens();
    }

    public boolean them(NhanVien nhanVien) throws Exception {
        if (nhanVien == null && nhanViens.contains(nhanVien))
            return false;

        return nhanViens.add(nhanVienDAO.themNhanVien(nhanVien));
    }

    public boolean xoa(String maNhanVien) throws Exception {
        NhanVien nhanVien = nhanViens.get(tim(maNhanVien));

        if (nhanVien == null)
            return false;

        return nhanVienDAO.xoaNhanVien(maNhanVien) && nhanViens.remove(nhanVien);
    }

    public boolean sua(NhanVien nhanVien) throws Exception {
        return nhanViens.set(tim(nhanVien.getMaNhanVien()),
                nhanVienDAO.suaNhanVien(nhanVien)) != null;
    }

    public int tim (String maNhanVien){
        for (int i = 0; i < nhanViens.size(); i++)
            if (nhanViens.get(i).getMaNhanVien().equals(maNhanVien))
                return i;

        return -1;
    }
}
