package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.models.tablemodel.KhachHangTableModel;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class QuanLyKhachHangTabbed extends JPanel {
    private JTable tblKhachHang;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;

    public QuanLyKhachHangTabbed(){
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

        ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
        KhachHangTableModel khachHangTableModel = new KhachHangTableModel(khachHangs);
        tblKhachHang = new JTable(khachHangTableModel);
        tblKhachHang.setRowHeight(30);
        tblKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblKhachHang.setAutoscrolls(true);
        tblKhachHang.getColumnModel().getColumn(3).setCellEditor(defaultCellEditor);
        this.add(new JScrollPane(tblKhachHang), BorderLayout.CENTER);

        sorter = new TableRowSorter<TableModel>(tblKhachHang.getModel());
        tblKhachHang.setRowSorter(sorter);

    }
}
