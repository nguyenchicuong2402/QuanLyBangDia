package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.BangDia;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BangDiaTableModel extends AbstractTableModel {
    private ArrayList<BangDia> bangDias;

    private final String[] columnNames = new String[] {
            "Mã BĐ", "Tên băng đĩa", "Thể loại", "Tình trạng", "Đơn giá", "Số lượng tồn", "Hãng sản xuất", "Ghi chú"
    };

    public BangDiaTableModel(ArrayList<BangDia> bangDias) {
        this.bangDias = bangDias;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return bangDias.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        BangDia bangDia;

        if (rowIndex > getRowCount())
            return null;

        try{
            bangDia = bangDias.get(rowIndex);
        }catch (IndexOutOfBoundsException e){
            bangDias.trimToSize();
            return null;
        }

        switch (columnIndex){
            case 0: return bangDia.getMaBangDia();
            case 1: return bangDia.getTenBangDia();
            case 2: return bangDia.getTheLoai();
            case 3: return bangDia.isTinhTrang() ? "Mới" : "Hư hỏng";
            case 4:
                Locale locale = new Locale("vi", "VN");
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                return numberFormat.format(bangDia.getDonGia());
            case 5: return bangDia.getSoLuongTon();
            case 6: return bangDia.getHangSanXuat();
            case 7: return bangDia.getGhiChu();
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (rowIndex > getRowCount())
            return;

        BangDia row = bangDias.get(rowIndex);

        switch (columnIndex){
            case 0: row.setMaBangDia(aValue.toString());    break;
            case 1: row.setTenBangDia(aValue.toString());   break;
            case 2: row.setTheLoai(aValue.toString());      break;
            case 3: row.setTinhTrang(aValue.toString().equalsIgnoreCase("Mới"));    break;
            case 4: row.setDonGia((Double) aValue); break;
            case 5: row.setHangSanXuat(aValue.toString());  break;
            case 6: row.setGhiChu(aValue.toString());
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if ((columnIndex >= 0) && (columnIndex < getColumnCount()))
            return getValueAt(0, columnIndex).getClass();


        return Object.class;
    }


}
