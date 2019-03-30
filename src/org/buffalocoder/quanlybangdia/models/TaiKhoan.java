package org.buffalocoder.quanlybangdia.models;

import java.util.Objects;

public class TaiKhoan{
    private String tenTaiKhoan;
    private String matKhau;
    private int loaiTaiKhoan;
    private String maNhanVien;

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(int loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, int loaiTaiKhoan, String maNhanVien) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.maNhanVien = maNhanVien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaiKhoan taiKhoan = (TaiKhoan) o;
        return tenTaiKhoan.equals(taiKhoan.tenTaiKhoan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenTaiKhoan);
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", loaiTaiKhoan=" + loaiTaiKhoan +
                ", maNhanVien=" + maNhanVien+
                '}';
    }
}
