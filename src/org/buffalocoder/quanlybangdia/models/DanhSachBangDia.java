package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.BangDiaDAO;

import java.util.ArrayList;

public class DanhSachBangDia {
    private ArrayList<BangDia> bangDias = null;

    public DanhSachBangDia(){
        loadData();
    }

    public void loadData(){
        bangDias = BangDiaDAO.getInstance().getBangDias();
    }

    public ArrayList<BangDia> getAll(){
        return  bangDias;
    }

    public boolean them(BangDia bangDia){
        if(bangDia == null || bangDias.contains(bangDia))
            return false;

        return  (bangDias.add(BangDiaDAO.getInstance().themBangDia(bangDia)));
    }

    public boolean xoa(String maBangDia){
        BangDia bangDia = bangDias.get(tim(maBangDia));

        if (bangDia == null)
            return false;

        return (bangDias.remove(bangDia) && BangDiaDAO.getInstance().xoaBangDia(maBangDia));
    }

    public boolean sua(BangDia bangDia){
        return bangDias.set(tim(bangDia.getMaBangDia()), BangDiaDAO.getInstance().suaBangDia(bangDia)) != null;
    }

    private int tim(String maBangDia){
        for (int i = 0; i < bangDias.size(); i++)
            if (bangDias.get(i).getMaBangDia().equals(maBangDia))
                return i;

        return -1;
    }
}
