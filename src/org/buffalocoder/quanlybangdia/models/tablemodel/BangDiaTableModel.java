package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.BangDia;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BangDiaTableModel extends AbstractTableModel {
    private final ArrayList<BangDia> bangDias;

    private final String[] columnNames = new String[] {
            "Mã BĐ", "Tên băng đĩa", "Thể loại", "Tình trạng", "Đơn giá", "Hãng sản xuất", "Ghi chú"
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
        if (rowIndex > getRowCount())
            return null;

        BangDia row = bangDias.get(rowIndex);

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
}
