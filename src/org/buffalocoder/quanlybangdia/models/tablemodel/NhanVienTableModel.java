package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.NhanVien;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NhanVienTableModel extends AbstractTableModel {
    private ArrayList<NhanVien> nhanViens;

    private static String[] columnName = {
            "Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "CMND", "Số điện thoại", "Địa chỉ", "Mô tả"
    };

    public NhanVienTableModel() {
        nhanViens = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return nhanViens.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NhanVien nhanVien = nhanViens.get(rowIndex);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-YYYY");

        switch (columnIndex){
            case 0: return nhanVien.getMaNhanVien();
            case 1: return nhanVien.getHoTen();
            case 2: return nhanVien.isGioiTinh() ? "Nam" : "Nữ";
            case 3:
                try {
                    return df.parse(nhanVien.getNgaySinh().toString());
                } catch (ParseException e) {
                    return "NaN";
                }
            case 4: return nhanVien.getcMND();
            case 5: return nhanVien.getSoDienThoai();
            case 6: return nhanVien.getDiaChi();
            case 7:
                try {
                    return df.parse(nhanVien.getNgaySinh().toString());
                } catch (ParseException e) {
                    return "NaN";
                }
            case 8: return nhanVien.getMoTa();
        }

        return null;
    }
}
