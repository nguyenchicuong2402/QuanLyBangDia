package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachNhanVien;
import org.buffalocoder.quanlybangdia.models.tablemodel.NhanVienTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class QuanLyNhanVienTabbed extends JPanel {
    private JTable tblChoThue;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;
    private DanhSachNhanVien danhSachNhanVien;
    private NhanVienTableModel nhanVienTableModel;

    public QuanLyNhanVienTabbed(){
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
        btnThem.setPreferredSize(new Dimension(90, 40));
        MaterialDesign.materialButton(btnThem);
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnSua);
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnXoa);
        funcPanel.add(btnXoa);

        // tìm kiếm
        searchPanel = new JPanel();
        searchPanel.setBackground(Values.COLOR_BACKGROUND);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTuKhoa = new JTextField();
        txtTuKhoa.setPreferredSize(new Dimension(300, 40));
        MaterialDesign.materialTextField(txtTuKhoa);
        searchPanel.add(txtTuKhoa);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnTimKiem);
        searchPanel.add(btnTimKiem);

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        danhSachNhanVien = new DanhSachNhanVien();
        danhSachNhanVien.loadData();
        nhanVienTableModel = new NhanVienTableModel(danhSachNhanVien.getAll());

        tblChoThue = new JTable(nhanVienTableModel);
        MaterialDesign.materialTable(tblChoThue);
        box.add(new JScrollPane(tblChoThue));

    }
}
