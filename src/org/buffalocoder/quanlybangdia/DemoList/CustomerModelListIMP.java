package org.buffalocoder.quanlybangdia.DemoList;

import org.buffalocoder.quanlybangdia.models.CustomerModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerModelListIMP implements CustomerModelList {

    public static List<CustomerModel> listCUS=new ArrayList<CustomerModel>();
    @Override
    public boolean addCustomer(CustomerModel CUS) {
        boolean kiemtra=false;
        for(CustomerModel acc: listCUS)
        {
            if((acc.getId()==CUS.getId()))
                kiemtra=true;
            break;

        }
        if(kiemtra) {
            return false;
        }
        else {
            CustomerModelListIMP.listCUS.add(CUS);
            return true;
        }
    }

    @Override
    public boolean editCustomer(CustomerModel newCUS, Long MACUS) {
        return false;
    }

    @Override
    public void Sortlist() {

    }

    @Override
    public void ShowAll() {

    }

    @Override
    public CustomerModel FindbyID(Long ID) {
        for( CustomerModel acc:listCUS)
        {
            if(acc.getId()==ID)
                return acc;
        }
        return null;
    }

    @Override
    public CustomerModel FindbyName(String Name) {
        for( CustomerModel acc:listCUS)
        {
            if(acc.getFullName().equals(Name));
            return acc;
        }
        return null;


    }
}
