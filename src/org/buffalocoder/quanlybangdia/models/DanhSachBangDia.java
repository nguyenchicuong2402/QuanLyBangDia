package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.BangDiaDAO;

import java.util.ArrayList;

public class DanhSachBangDia {
    private ArrayList<BangDia> bangDias = null;
    private static BangDiaDAO bangDiaDAO;

    public DanhSachBangDia() throws Exception {
        bangDiaDAO = BangDiaDAO.getInstance();
        loadData();
    }

    public void loadData() throws Exception {
        bangDias = bangDiaDAO.getBangDias();
    }

    public ArrayList<BangDia> getAll(){
        return  bangDias;
    }

    public boolean them(BangDia bangDia) throws Exception {
        if(bangDia == null || bangDias.contains(bangDia))
            return false;

        return  (bangDias.add(bangDiaDAO.themBangDia(bangDia)));
    }

    public boolean xoa(String maBangDia) throws Exception {
        BangDia bangDia = bangDias.get(tim(maBangDia));

        if (bangDia == null)
            return false;

        return (bangDias.remove(bangDia) && bangDiaDAO.xoaBangDia(maBangDia));
    }

    public boolean sua(BangDia bangDia) throws Exception {
        return bangDias.set(tim(bangDia.getMaBangDia()), bangDiaDAO.suaBangDia(bangDia)) != null;
    }

    public int tim(String maBangDia){
        for (int i = 0; i < bangDias.size(); i++)
            if (bangDias.get(i).getMaBangDia().equals(maBangDia))
                return i;

        return -1;
    }

    public ArrayList<String> getMaBangDias(){
        ArrayList<String> maBangDias = new ArrayList<>();

        for (BangDia bangDia : bangDias)
            maBangDias.add(bangDia.getMaBangDia());

        return maBangDias;
    }
}
