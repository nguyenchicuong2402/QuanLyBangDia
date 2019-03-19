package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.HoaDon;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.ArrayList;

public class HoaDonTableModel extends AbstractTableModel {
    private final ArrayList<HoaDon> hoaDons;

    private final String[] columnNames = new String[] {
            "Mã HD", "Tên khách hàng", "Tên băng đĩa", "Số lượng", "Ngày thuê", "Số ngày được thuê"
    };

    private final Class[] columnClass = new Class[] {
            String.class, String.class, String.class, Integer.class, String.class, Integer.class
    };

    private boolean[] canEdit = new boolean[]{
            false, true, true, true, true, true
    };

    public void setCanEdit(boolean[] canEdit) {
        this.canEdit = canEdit;
    }

    public HoaDonTableModel(ArrayList<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
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
        return hoaDons.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        HoaDon row = hoaDons.get(rowIndex);

        switch (columnIndex){
            case 0: return row.getMaHoaDon();
            case 1: return row.getKhachHang().getHoTen();
            case 2: return row.getBangDia().getTenBangDia();
            case 3: return row.getSoLuong();
            case 4: return row.getNgayLap();
            case 5: return row.getSoNgayDuocMuon();
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        HoaDon row = hoaDons.get(rowIndex);

        switch (columnIndex) {
            case 0:
                break;
            case 1:
                row.getKhachHang().setHoTen((String)value);
                break;
            case 2:
                row.getBangDia().setTenBangDia((String)value);
                break;
            case 3:
                row.setSoLuong((Integer)value);
                break;
            case 4:
                row.setNgayLap((Date)value);
                break;
            case 5:
                row.setSoNgayDuocMuon((Integer)value);
                break;
        }
    }
}
