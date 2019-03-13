package org.buffalocoder.quanlybangdia.DemoList;
import org.buffalocoder.quanlybangdia.models.EmployeeModel;
public interface EmployeeModelList {
    public boolean addemploy(EmployeeModel EMP);

    public  boolean editEmploy (EmployeeModel newEMP,Long MAEMP);

    public  boolean deleteemploy(Long SID);

}
