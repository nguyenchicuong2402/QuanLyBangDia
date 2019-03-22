package org.buffalocoder.quanlybangdia.models;

import java.util.ArrayList;

public class DanhSachBangDia {
    private ArrayList<BangDia> dsBangDia;
    public DanhSachBangDia(){
        dsBangDia = new ArrayList<BangDia>();
    }
    public ArrayList<BangDia> getAll(){
        return  dsBangDia;
    }

    public boolean themBD(BangDia bd){
        if(dsBangDia.contains(bd))
            return false;
        else
        {
            dsBangDia.add(bd);
            return true;
        }
    }

    public BangDia timBD(String maBD){
        for (BangDia bd: dsBangDia) {
            if(bd.getMaBangDia().equals(maBD))
                return bd;
        }
        return  null;
    }
}
