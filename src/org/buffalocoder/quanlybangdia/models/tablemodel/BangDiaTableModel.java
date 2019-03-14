package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.BangDia;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BangDiaTableModel extends AbstractTableModel {
    private final ArrayList<BangDia> bangDiaList;

    private final String[] columnNames = new String[] {
            "Mã BĐ", "Tên băng đĩa", "Thể loại", "Tình trạng", "Đơn giá", "Hãng sản xuất", "Ghi chú"
    };

    private final Class[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class, String.class
    };

    private boolean[] canEdit = new boolean[]{
            false, true, true, true, true, true, true
    };

    public void setCanEdit(boolean[] canEdit) {
        this.canEdit = canEdit;
    }

    public BangDiaTableModel(ArrayList<BangDia> bangDiaList) {
        this.bangDiaList = bangDiaList;
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
        return bangDiaList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        BangDia row = bangDiaList.get(rowIndex);

        switch (columnIndex){
            case 0: return row.getMaBangDia();
            case 1: return row.getTenBangDia();
            case 2: return row.getTheLoai();
            case 3: return row.isTinhTrang() ? "Mới" : "Hư hỏng";
            case 4:
                Locale locale = new Locale("vi", "VN");
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                return numberFormat.format(row.getDonGia());
            case 5: return row.getHangSanXuat();
            case 6: return row.getGhiChu();
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        BangDia row = bangDiaList.get(rowIndex);

        switch (columnIndex){
            case 0: break;
            case 1: row.setTenBangDia((String)value); break;
            case 2: row.setTheLoai((String)value); break;
            case 3:
                String gioiTinh = value.toString();
                row.setTinhTrang(gioiTinh.equalsIgnoreCase("Mới"));
                break;
            case 4:
                try {
                    Locale locale = new Locale("vi", "VN");
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                    row.setDonGia((Double)numberFormat.parse((String)value));
                }catch (Exception e) {
                    try{
                        row.setDonGia(Double.parseDouble((String)value));
                    }catch (NumberFormatException ex){
                        return;
                    }
                }
                break;
            case 5: row.setHangSanXuat((String)value); break;
            case 6: row.setGhiChu((String)value); break;

        }
    }
}
