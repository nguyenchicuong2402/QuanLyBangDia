package org.buffalocoder.quanlybangdia.models;

import javax.swing.table.AbstractTableModel;

public class NhanVienTableModel extends AbstractTableModel {
//    private final List<NhanVien> nhanVienList;
//
//    private final String[] columnNames = new String[] {
//            "Mã NV", "Họ", "Tên", "Phái", "Tuổi", "Tiền lương"
//    };
//
//    private final Class[] columnClass = new Class[] {
//            Integer.class, String.class, String.class, String.class, Integer.class, String.class
//    };
//
//    private final boolean[] canEdit = new boolean[]{
//            false, true, true, true, true, true
//    };
//
//    public NhanVienTableModel(List<NhanVien> nhanVienList) {
//        this.nhanVienList = nhanVienList;
//    }
//
//    @Override
//    public String getColumnName(int column)
//    {
//        return columnNames[column];
//    }
//
//    @Override
//    public Class<?> getColumnClass(int columnIndex)
//    {
//        return columnClass[columnIndex];
//    }
//
//    @Override
//    public int getColumnCount()
//    {
//        return columnNames.length;
//    }
//
//    @Override
//    public int getRowCount()
//    {
//        return nhanVienList.size();
//    }
//
//
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex)
//    {
//        NhanVien row = nhanVienList.get(rowIndex);
//
//        switch (columnIndex){
//            case 0: return row.getMaNhanVien();
//            case 1: return row.getHo();
//            case 2: return row.getTen();
//            case 3: return row.getPhai() ? "Nam" : "Nữ";
//            case 4: return row.getTuoi();
//            case 5:
//                Locale locale = new Locale("vi", "VN");
//                NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//                return numberFormat.format(row.getTienLuong());
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return canEdit[columnIndex];
//    }
//
//    @Override
//    public void setValueAt(Object value, int rowIndex, int columnIndex){
//        NhanVien row = nhanVienList.get(rowIndex);
//
//        switch (columnIndex){
//            case 0: break;
//            case 1: row.setHo((String)value); break;
//            case 2: row.setTen((String)value); break;
//            case 3:
//                String gioiTinh = (String)value;
//                row.setPhai(gioiTinh.equalsIgnoreCase("Nam"));
//                break;
//            case 4: row.setTuoi((Integer)value); break;
//            case 5:
//                try {
//                    Locale locale = new Locale("vi", "VN");
//                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
//                    row.setTienLuong((Double)numberFormat.parse((String)value));
//                }catch (Exception e) {
//                    try{
//                        row.setTienLuong(Double.parseDouble((String)value));
//                    }catch (NumberFormatException ex){
//                        return;
//                    }
//                }
//                break;
//        }
//    }
}
