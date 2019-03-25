package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.BangDiaDAO;

import java.util.ArrayList;

public class DanhSachBangDia {
    private ArrayList<BangDia> bangDias;

    public DanhSachBangDia(){
        bangDias = new ArrayList<BangDia>();
    }

    public void loadData(){
        bangDias = BangDiaDAO.getInstance().getBangDias();
    }

    public ArrayList<BangDia> getAll(){
        return  bangDias;
    }

    public boolean them(BangDia bangDia){
        if(bangDia != null && bangDias.contains(bangDia))
            return false;

        if (bangDias.add(bangDia)){
            // thêm băng đĩa vào db
            return true;
        }

        return false;
    }

    public boolean xoa(String maBangDia){
        BangDia bangDia = tim(maBangDia);

        if (bangDia == null)
            return false;

        if (bangDias.remove(bangDia)){
            // xoá băng đĩa trong db
            return true;
        }

        return false;
    }

    public boolean sua(BangDia bangDia){
        if (bangDia != null){
            int index = timVitri(bangDia.getMaBangDia());

            if (index == -1)
                return false;

            if (bangDias.set(index, bangDia) != null){
                // update thông tin băng đĩa trong db
                return true;
            }
        }

        return false;
    }

    private int timVitri(String maBangDia){
        for (int i = 0; i < bangDias.size(); i++)
            if (bangDias.get(i).getMaBangDia().equals(maBangDia))
                return i;

        return -1;
    }

    public BangDia tim(String maBangDia){
        for (BangDia bangDia: bangDias) {
            if(bangDia.getMaBangDia().equals(maBangDia))
                return bangDia;
        }
        return  null;
    }
}
