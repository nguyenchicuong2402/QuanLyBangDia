package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.HoaDon;
import org.buffalocoder.quanlybangdia.utils.Formats;

import javax.swing.table.AbstractTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChoThueTableModel extends AbstractTableModel {
    private ArrayList<HoaDon> hoaDons;

    private final String[] columnNames = new String[]{
            "Mã HD", "Tên khách hàng", "Tên băng đĩa", "Số lượng", "Ngày thuê", "Số ngày được thuê", "Thành tiền", "Tình trạng"
    };

    public void setModel(ArrayList<HoaDon> hoaDons){
        this.hoaDons = hoaDons;
    }

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
            HoaDon hoaDon = hoaDons.get(rowIndex);

            switch (columnIndex) {
                case 0: return hoaDon.getMaHoaDon();
                case 1: return hoaDon.getKhachHang().getHoTen();
                case 2: return hoaDon.getBangDia().getTenBangDia();
                case 3: return hoaDon.getSoLuong();
                case 4: return Formats.DATE_FORMAT.format(hoaDon.getNgayLap());
                case 5: return hoaDon.getSoNgayDuocMuon();
                case 6:
                    Locale locale = new Locale("vi", "VN");
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                    return numberFormat.format(hoaDon.thanhTien());
                case 7: return hoaDon.isTinhTrang() ? "Đã thanh toán" : "Đang thuê";
            }
        }catch (Exception e){

        }

        return null;
    }
}