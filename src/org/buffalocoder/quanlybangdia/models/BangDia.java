package org.buffalocoder.quanlybangdia.models;

import java.util.Objects;

public class BangDia {
    private String maBangDia;
    private String tenBangDia;
    private String theLoai;
    private boolean tinhTrang;
    private String hangSanXuat;
    private String ghiChu;
    private Double donGia;

    public BangDia() {
    }

    public BangDia(String maBangDia, String tenBangDia, String theLoai, boolean tinhTrang, String hangSanXuat, String ghiChu, Double donGia) {
        this.maBangDia = maBangDia;
        this.tenBangDia = tenBangDia;
        this.theLoai = theLoai;
        this.tinhTrang = tinhTrang;
        this.hangSanXuat = hangSanXuat;
        this.ghiChu = ghiChu;
        this.donGia = donGia;
    }

    public String getMaBangDia() {
        return maBangDia;
    }

    public void setMaBangDia(String maBangDia) {
        this.maBangDia = maBangDia;
    }

    public String getTenBangDia() {
        return tenBangDia;
    }

    public void setTenBangDia(String tenBangDia) {
        this.tenBangDia = tenBangDia;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BangDia bangDia = (BangDia) o;
        return maBangDia == bangDia.maBangDia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maBangDia);
    }

    @Override
    public String toString() {
        return "BangDia{" +
                "maBangDia=" + maBangDia +
                ", tenBangDia='" + tenBangDia + '\'' +
                ", theLoai='" + theLoai + '\'' +
                ", tinhTrang=" + tinhTrang +
                ", hangSanXuat='" + hangSanXuat + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                ", donGia=" + donGia +
                '}';
    }
}
