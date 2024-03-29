package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.utils.Formats;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class KhachHangTableModel extends AbstractTableModel {
    private ArrayList<KhachHang> khachHangs;

    private final String[] columnNames = new String[]{
            "Mã KH", "Tên khách hàng", "Giới tính", "Ngày sinh", "CMND", "Số điện thoại", "Địa chỉ", "Ngày hết hạn"
    };

    public void setModel(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    public KhachHangTableModel(ArrayList<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return khachHangs.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            KhachHang row = khachHangs.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return row.getMaKH();
                case 1:
                    return row.getHoTen();
                case 2:
                    return row.isGioiTinh() ? "Nam" : "Nữ";
                case 3:
                    return Formats.DATE_FORMAT.format(row.getNgaySinh());
                case 4:
                    return row.getcMND();
                case 5:
                    return row.getSoDienThoai();
                case 6:
                    return row.getDiaChi();
                case 7:
                    return Formats.DATE_FORMAT.format(row.getNgayHetHan());
            }
        } catch (Exception e) {
        }


        return null;
    }
}
