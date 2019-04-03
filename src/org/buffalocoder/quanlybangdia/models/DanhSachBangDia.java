package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.BangDiaDAO;

import java.util.ArrayList;
import java.util.Iterator;

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

        return (bangDiaDAO.xoaBangDia(maBangDia) && bangDias.remove(bangDia));
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

    public void xoaBangDiaHong(){
        ArrayList<String> dsXoa = new ArrayList<>();

        for (BangDia bangDia : bangDias){
            if (!bangDia.isTinhTrang()) {
                dsXoa.add(bangDia.getMaBangDia());
            }
        }

        for (String maBangDia : dsXoa){
            try {
                xoa(maBangDia);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int tongSoBangDiaTon(){
        int tong = 0;

        for (BangDia bangDia : bangDias)
            tong += bangDia.getSoLuongTon();

        return tong;
    }

    public ArrayList<String> getMaBangDias(){
        ArrayList<String> maBangDias = new ArrayList<>();

        for (BangDia bangDia : bangDias)
            maBangDias.add(bangDia.getMaBangDia());

        return maBangDias;
    }
}
