package org.buffalocoder.quanlybangdia.models;

public class HiredCustomer {

    private String ID, hoTen;
    private int soLuongThueHT;
    private String ngayThueGN, ngayHHGN;

    public HiredCustomer(String ID, String hoTen, int soLuongThueHT, String ngayThueGN, String ngayHHGN) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.soLuongThueHT = soLuongThueHT;
        this.ngayThueGN = ngayThueGN;
        this.ngayHHGN = ngayHHGN;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSoLuongThueHT() {
        return soLuongThueHT;
    }

    public void setSoLuongThueHT(int soLuongThueHT) {
        this.soLuongThueHT = soLuongThueHT;
    }

    public String getNgayThueGN() {
        return ngayThueGN;
    }

    public void setNgayThueGN(String ngayThueGN) {
        this.ngayThueGN = ngayThueGN;
    }

    public String getNgayHHGN() {
        return ngayHHGN;
    }

    public void setNgayHHGN(String ngayHHGN) {
        this.ngayHHGN = ngayHHGN;
    }

    @Override
    public String toString() {
        return "HiredCustomer{" +
                "ID='" + ID + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", soLuongThueHT=" + soLuongThueHT +
                ", ngayThueGN='" + ngayThueGN + '\'' +
                ", ngayHHGN='" + ngayHHGN + '\'' +
                '}';
    }
}
