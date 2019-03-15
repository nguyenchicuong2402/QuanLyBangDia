package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;
import java.util.Objects;

public class KhachHang extends ThongTinCaNhan{
    private long maKH;
    private Date ngayHetHan;

    public long getMaKH() {
        return maKH;
    }

    public void setMaKH(long maKH) {
        this.maKH = maKH;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public KhachHang(long cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh) {
        super(cMND, hoTen, gioiTinh, soDienThoai, diaChi, ngaySinh);
    }

    public KhachHang(long cMND, String hoTen, boolean gioiTinh, String soDienThoai, String diaChi, Date ngaySinh, long maKH, Date ngayHetHan) {
        super(cMND, hoTen, gioiTinh, soDienThoai, diaChi, ngaySinh);
        this.maKH = maKH;
        this.ngayHetHan = ngayHetHan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        KhachHang khachHang = (KhachHang) o;
        return maKH == khachHang.maKH;
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