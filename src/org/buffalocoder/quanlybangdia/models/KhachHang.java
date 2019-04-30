package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;
import java.util.Objects;

public class KhachHang extends ThongTinCaNhan {
    private String maKH;
    private Date ngayHetHan;

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public KhachHang() {
    }

    public KhachHang(String cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh) {
        super(cMND, hoTen, gioiTinh, soDienThoai, diaChi, ngaySinh);
    }

    public KhachHang(String cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh, String maKH, Date ngayHetHan) {
        super(cMND, hoTen, gioiTinh, soDienThoai, diaChi, ngaySinh);
        this.maKH = maKH;
        this.ngayHetHan = ngayHetHan;
    }

    public KhachHang(String cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh, String maKH) {
        super(cMND, hoTen, gioiTinh, soDienThoai, diaChi, ngaySinh);
        this.maKH = maKH;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        KhachHang khachHang = (KhachHang) o;
        return maKH.equals(khachHang.maKH);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maKH);
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH=" + maKH +
                ", ngayHetHan=" + ngayHetHan +
                "} " + super.toString();
    }
}
