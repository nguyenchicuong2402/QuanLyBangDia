package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.HoaDon;
import org.buffalocoder.quanlybangdia.utils.Utils;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.util.ArrayList;

public class ChoThueTableModel extends AbstractTableModel {
    private final ArrayList<HoaDon> hoaDons;

    private final String[] columnNames = new String[]{
            "Mã HD", "Tên khách hàng", "Tên băng đĩa", "Số lượng", "Ngày thuê", "Số ngày được thuê"
    };


    public ChoThueTableModel(ArrayList<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
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
        return hoaDons.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            HoaDon row = hoaDons.get(rowIndex);

            switch (columnIndex) {
                case 0: return row.getMaHoaDon();
                case 1: return row.getKhachHang().getHoTen();
                case 2: return row.getBangDia().getTenBangDia();
                case 3: return row.getSoLuong();
                case 4: return Utils.DATE_FORMAT.format(row.getNgayLap());
                case 5: return row.getSoNgayDuocMuon();
            }
        }catch (Exception e){

        }

        return null;
    }
}