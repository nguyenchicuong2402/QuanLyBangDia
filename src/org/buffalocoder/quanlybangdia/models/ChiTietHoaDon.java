package org.buffalocoder.quanlybangdia.models;

public class ChiTietHoaDon {
    private BangDia bangDia;
    private int soNgayDuocMuon;
    private int soLuong;
    private boolean tinhTrang;  // true: đã trả, false: đang thuê

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
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

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(BangDia bangDia, int soNgayDuocMuon, int soLuong) {
        this.bangDia = bangDia;
        this.soNgayDuocMuon = soNgayDuocMuon;
        this.soLuong = soLuong;
        this.tinhTrang = false;
    }

    public ChiTietHoaDon(BangDia bangDia, int soNgayDuocMuon, int soLuong, boolean tinhTrang) {
        this.bangDia = bangDia;
        this.soNgayDuocMuon = soNgayDuocMuon;
        this.soLuong = soLuong;
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "bangDia=" + bangDia +
                ", soNgayDuocMuon=" + soNgayDuocMuon +
                ", soLuong=" + soLuong +
                ", tinhTrang=" + tinhTrang +
                '}';
    }
}
