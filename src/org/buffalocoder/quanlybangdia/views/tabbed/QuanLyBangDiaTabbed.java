package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.BangDiaTableModel;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class QuanLyBangDiaTabbed extends JPanel {
    private JTable tblBangDia;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;

    public QuanLyBangDiaTabbed(){
        this.setLayout(new BorderLayout());
        this.setFont(Values.FONT_PLAIN_DEFAULT);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Values.COLOR_BACKGROUND);

        /*========== TOP PANEL ==========*/
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Values.COLOR_BACKGROUND);
        this.add(topPanel, BorderLayout.NORTH);

        // chức năng
        funcPanel = new JPanel();
        funcPanel.setBackground(Values.COLOR_BACKGROUND);
        topPanel.add(funcPanel, BorderLayout.WEST);

        btnThem = new JButton("Thêm");
        btnThem.setBackground(Values.COLOR_PRIMARY);
        btnThem.setForeground(Values.COLOR_TEXT);
        btnThem.setBorder(BorderFactory.createEmptyBorder());
        btnThem.setPreferredSize(new Dimension(90, 40));
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(Values.COLOR_PRIMARY);
        btnSua.setForeground(Values.COLOR_TEXT);
        btnSua.setBorder(BorderFactory.createEmptyBorder());
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        btnXoa.setBackground(Values.COLOR_PRIMARY);
        btnXoa.setForeground(Values.COLOR_TEXT);
        btnXoa.setBorder(BorderFactory.createEmptyBorder());
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        funcPanel.add(btnXoa);

        // tìm kiếm
        searchPanel = new JPanel();
        searchPanel.setBackground(Values.COLOR_BACKGROUND);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTuKhoa = new JTextField();
        txtTuKhoa.setPreferredSize(new Dimension(300, 40));
        txtTuKhoa.setFont(Values.FONT_PLAIN_DEFAULT);
        txtTuKhoa.setBackground(Values.COLOR_BACKGROUND);
        txtTuKhoa.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtTuKhoa.setMargin(new Insets(0, 0, 100, 0));
        searchPanel.add(txtTuKhoa);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(Values.COLOR_PRIMARY);
        btnTimKiem.setForeground(Values.COLOR_TEXT);
        btnTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        searchPanel.add(btnTimKiem);

        // table
        JComboBox<String> cbTinhTrang = new JComboBox<>(new String[]{"Mới", "Hư hỏng"});
        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(cbTinhTrang);

        ArrayList<BangDia> bangDiaList = new ArrayList<BangDia>();
        BangDiaTableModel bangDiaTableModel = new BangDiaTableModel(bangDiaList);
        tblBangDia = new JTable(bangDiaTableModel);
        tblBangDia.setRowHeight(30);
        tblBangDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBangDia.setAutoscrolls(true);
        tblBangDia.getColumnModel().getColumn(3).setCellEditor(defaultCellEditor);
        this.add(new JScrollPane(tblBangDia), BorderLayout.CENTER);

        sorter = new TableRowSorter<TableModel>(tblBangDia.getModel());
        tblBangDia.setRowSorter(sorter);

        bangDiaList.add(new BangDia("111", "ab", "sd", true, "asda", "dsa", 10000.0));
        bangDiaList.add(new BangDia("112", "ab", "sd", true, "asda", "dsa", 10000.0));
        bangDiaList.add(new BangDia("113", "ab", "sd", true, "asda", "dsa", 10000.0));
        bangDiaList.add(new BangDia("114", "ab", "sd", true, "asda", "dsa", 10000.0));
        bangDiaList.add(new BangDia("115", "ab", "sd", true, "asda", "dsa", 10000.0));
        bangDiaList.add(new BangDia("116z", "ab", "sd", true, "asda", "dsa", 10000.0));
    }

    private void filterTableNhanVien(String key_word) {
        if (key_word.isEmpty())
            sorter.setRowFilter(null);
        else {
            try{
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    @Override
                    public boolean include(Entry<?, ?> entry) {
                        return ((Integer) entry.getValue(0)) == Integer.parseInt(key_word.trim());
                    }
                };
                sorter.setRowFilter(filter);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(QuanLyBangDiaTabbed.this,
                        "Vui lòng nhập số",
                        "Thông báo",
                        JOptionPane.ERROR_MESSAGE);

                txtTuKhoa.selectAll();
            }

        }
    }
}
