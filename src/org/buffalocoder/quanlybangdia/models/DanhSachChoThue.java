package org.buffalocoder.quanlybangdia.models;

import java.util.ArrayList;

public class DanhSachChoThue {
    private ArrayList<HoaDon> hoaDons;

    public DanhSachChoThue(){
        hoaDons = new ArrayList<>();
    }

    public ArrayList<HoaDon> getAll(){
        return hoaDons;
    }
}
