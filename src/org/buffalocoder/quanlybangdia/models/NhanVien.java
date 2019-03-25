package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;

public class NhanVien extends ThongTinCaNhan{
    private String maNhanVien;
    private String moTa;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public NhanVien(String cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh) {
        super(cMND, hoTen, gioiTinh, soDienThoai, diaChi, ngaySinh);
    }

    public NhanVien(String cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh, String maNhanVien, String moTa) {
        super(cMND, hoTen, gioiTinh, soDienThoai, diaChi, ngaySinh);
        this.maNhanVien = maNhanVien;
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", moTa='" + moTa + '\'' +
                "} " + super.toString();
    }
}
