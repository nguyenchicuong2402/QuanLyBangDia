package org.buffalocoder.quanlybangdia.models;

import java.util.Objects;

public class TaiKhoan {
    private String tenTaiKhoan;
    private String matKhau;
    private int loaiTaiKhoan;
    private NhanVien nhanVien;

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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public TaiKhoan() {
    }

    public TaiKhoan(String tenTaiKhoan, String matKhau, int loaiTaiKhoan, NhanVien nhanVien) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.nhanVien = nhanVien;
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
                ", nhanVien=" + nhanVien +
                '}';
    }
}
