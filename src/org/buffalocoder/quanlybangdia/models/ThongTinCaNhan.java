package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;
import java.util.Objects;

public class ThongTinCaNhan {
    private long cMND;
    private String hoTen;
    private boolean gioiTinh;
    private String soDienThoai;
    private String diaChi;
    private Date ngaySinh;

    public long getcMND() {
        return cMND;
    }

    public void setcMND(long cMND) {
        this.cMND = cMND;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public ThongTinCaNhan() {
    }

    public ThongTinCaNhan(long cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh) {
        this.cMND = cMND;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThongTinCaNhan that = (ThongTinCaNhan) o;
        return cMND == that.cMND;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cMND);
    }

    @Override
    public String toString() {
        return "ThongTinCaNhan{" +
                "cMND=" + cMND +
                ", hoTen='" + hoTen + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", ngaySinh=" + ngaySinh +
                '}';
    }
}
