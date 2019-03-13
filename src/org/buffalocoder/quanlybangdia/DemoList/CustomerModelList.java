package org.buffalocoder.quanlybangdia.DemoList;

import org.buffalocoder.quanlybangdia.models.CustomerModel;
public  interface  CustomerModelList {

    public boolean addCustomer(CustomerModel CUS);

    public  boolean editCustomer (CustomerModel newCUS,Long MACUS);

    public void Sortlist();

    public void ShowAll();

    public CustomerModel FindbyID(Long ID);

    public CustomerModel FindbyName(String Name);

}
