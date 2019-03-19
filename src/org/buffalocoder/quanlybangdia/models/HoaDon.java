package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;
import java.util.Objects;

public class HoaDon {
    private String maHoaDon;
    private KhachHang khachHang;
    private Date ngayLap;
    private BangDia bangDia;
    private int soNgayDuocMuon;
    private int soLuong;

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public BangDia getBangDia() {
        return bangDia;
    }

    public void setBangDia(BangDia bangDia) {
        this.bangDia = bangDia;
    }

    public int getSoNgayDuocMuon() {
        return soNgayDuocMuon;
    }

    public void setSoNgayDuocMuon(int soNgayDuocMuon) {
        this.soNgayDuocMuon = soNgayDuocMuon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, KhachHang khachHang, Date ngayLap, BangDia bangDia, int soNgayDuocMuon, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.ngayLap = ngayLap;
        this.bangDia = bangDia;
        this.soNgayDuocMuon = soNgayDuocMuon;
        this.soLuong = soLuong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoaDon hoaDon = (HoaDon) o;
        return maHoaDon == hoaDon.maHoaDon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHoaDon);
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon=" + maHoaDon +
                ", khachHang=" + khachHang +
                ", ngayLap=" + ngayLap +
                ", bangDia=" + bangDia +
                ", soNgayDuocMuon=" + soNgayDuocMuon +
                ", soLuong=" + soLuong +
                '}';
    }
}
