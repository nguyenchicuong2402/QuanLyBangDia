package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;
import java.util.Objects;

public class HoaDon extends ChiTietHoaDon{
    private String maHoaDon;
    private KhachHang khachHang;
    private Date ngayLap;

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

    public HoaDon() {
    }

    public HoaDon(BangDia bangDia, int soNgayDuocMuon, int soLuong) {
        super(bangDia, soNgayDuocMuon, soLuong);
    }

    public HoaDon(BangDia bangDia, int soNgayDuocMuon, int soLuong, String maHoaDon, KhachHang khachHang, Date ngayLap) {
        super(bangDia, soNgayDuocMuon, soLuong);
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.ngayLap = ngayLap;
    }

    public HoaDon(BangDia bangDia, int soNgayDuocMuon, int soLuong, String maHoaDon, KhachHang khachHang) {
        super(bangDia, soNgayDuocMuon, soLuong);
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
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
                "maHoaDon='" + maHoaDon + '\'' +
                ", khachHang=" + khachHang +
                ", ngayLap=" + ngayLap +
                "} " + super.toString();
    }
}
