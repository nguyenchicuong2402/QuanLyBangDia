package org.buffalocoder.quanlybangdia.DemoList;

import org.buffalocoder.quanlybangdia.models.DiscModel;
public interface DiscModelList {
    public boolean addDisc(DiscModel DISC);

    public boolean editdisc(DiscModel newdisc,long stk);

    public boolean DeleteDisc(long id);

    public void showALL();



}
