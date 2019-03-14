package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.KhachHang;
import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class KhachHangTableModel extends AbstractTableModel {
    private final ArrayList<KhachHang> khachHangs;

    private final String[] columnNames = new String[] {
            "Mã KH", "Tên khách hàng", "Giới tính", "Ngày sinh", "CMND", "Số điện thoại", "Địa chỉ", "Ngày hết hạn"
    };

    private final Class[] columnClass = new Class[] {
            Long.class, String.class, String.class, String.class, Long.class, String.class, String.class
    };

    private boolean[] canEdit = new boolean[]{
            false, true, true, true, false, true, true
    };

    public void setCanEdit(boolean[] canEdit) {
        this.canEdit = canEdit;
    }

    public KhachHangTableModel(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
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
        return khachHangs.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        KhachHang row = khachHangs.get(rowIndex);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");

        switch (columnIndex){
            case 0: return row.getMaKH();
            case 1: return row.getHoTen();
            case 2: return row.isGioiTinh() ? "Nam" : "Nữ";
            case 3:
                try {
                    return df.parse(row.getNgaySinh().toString());
                } catch (ParseException e) {
                    return "NaN";
                }
            case 4: return row.getcMND();
            case 5: return row.getSoDienThoai();
            case 6: return row.getDiaChi();
            case 7:
                try {
                    return df.parse(row.getNgaySinh().toString());
                } catch (ParseException e) {
                    return "NaN";
                }
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        KhachHang row = khachHangs.get(rowIndex);

        switch (columnIndex){
            case 0: break;
            case 1: row.setHoTen((String)value); break;
            case 2:
                String gioiTinh = value.toString();
                row.setGioiTinh(gioiTinh.equalsIgnoreCase("Nam"));
                break;
            case 3: break;
            case 4: break;
            case 5: row.setSoDienThoai((String)value); break;
            case 6: row.setDiaChi((String)value); break;
            case 7: break;
        }
    }
}
