package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.ChoThueDAO;

import java.util.ArrayList;

public class DanhSachChoThue {
    private ArrayList<HoaDon> hoaDons;

    public DanhSachChoThue(){
        hoaDons = new ArrayList<>();
    }

    public ArrayList<HoaDon> getAll(){
        return hoaDons;
    }

    public void loadData(){
        hoaDons = ChoThueDAO.getInstance().getHoaDons();
    }
}
