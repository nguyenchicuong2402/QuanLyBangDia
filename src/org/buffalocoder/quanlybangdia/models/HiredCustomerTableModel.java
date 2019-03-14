package org.buffalocoder.quanlybangdia.models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HiredCustomerTableModel extends AbstractTableModel {
    private final ArrayList<HiredCustomer> hiredList;

    private final String[] columnNames = new String[] {
            "ID", "Họ và tên", "Số lượng thuê hiện tại", "Ngày thuê gần nhất", "Ngày hết hạn gần nhất"
    };

    private final Class[] columnClass = new Class[] {
            String.class, String.class, Integer.class, String.class, String.class
    };

    private boolean[] canEdit = new boolean[]{
            false, true, true, true, true, true, true
    };

    public void setCanEdit(boolean[] canEdit) {
        this.canEdit = canEdit;
    }

    public HiredCustomerTableModel(ArrayList<HiredCustomer> hiredList) {
        this.hiredList = hiredList;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return hiredList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        HiredCustomer row = hiredList.get(rowIndex);

        switch (columnIndex){
            case 0: return row.getID();
            case 1: return row.getHoTen();
            case 2: return row.getSoLuongThueHT();
            case 3: return row.getNgayThueGN();
            case 4: return row.getNgayHHGN();

        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        HiredCustomer row = hiredList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                break;
            case 1:
                row.setHoTen((String) value);
                break;
            case 2:
                row.setSoLuongThueHT((Integer) value);
                break;
            case 3:
                row.setNgayThueGN((String) value);
                break;
            case 4:
                row.setNgayHHGN(((String) value));
                break;
        }
    }
}
