package org.buffalocoder.quanlybangdia.models.tablemodel;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.utils.Formats;

import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NhanVienTableModel extends AbstractTableModel {
    private ArrayList<NhanVien> nhanViens;

    private static String[] columnName = {
            "Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "CMND", "Số điện thoại", "Địa chỉ", "Mô tả"
    };

    public NhanVienTableModel(ArrayList<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
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
        try{
            NhanVien nhanVien = nhanViens.get(rowIndex);

            switch (columnIndex){
                case 0: return nhanVien.getMaNhanVien();
                case 1: return nhanVien.getHoTen();
                case 2: return nhanVien.isGioiTinh() ? "Nam" : "Nữ";
                case 3: return Formats.DATE_FORMAT.format(nhanVien.getNgaySinh());
                case 4: return nhanVien.getcMND();
                case 5: return nhanVien.getSoDienThoai();
                case 6: return nhanVien.getDiaChi();
                case 7: return nhanVien.getMoTa();
            }
        }catch (Exception e){

        }

        return null;
    }
}
