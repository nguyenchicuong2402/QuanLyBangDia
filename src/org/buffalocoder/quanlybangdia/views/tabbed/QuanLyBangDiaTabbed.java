package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.tablemodel.BangDiaTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;
import org.buffalocoder.quanlybangdia.views.DangNhap;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class QuanLyBangDiaTabbed extends JPanel {
    private final boolean IS_ADMIN = DangNhap.taiKhoan.getLoaiTaiKhoan() == 1;

    private JTable tblBangDia;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private BangDiaTableModel bangDiaTableModel;
    private DanhSachBangDia danhSachBangDia;
    private TableRowSorter<TableModel> sorter;
    private final Component rootComponent = this;

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
        if (IS_ADMIN)
            topPanel.add(funcPanel, BorderLayout.WEST);

        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(90, 40));
        btnThem.addActionListener(btnThem_Click());
        MaterialDesign.materialButton(btnThem);
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        btnSua.addActionListener(btnSua_Click());
        MaterialDesign.materialButton(btnSua);
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnXoa.addActionListener(btnXoa_Click());
        MaterialDesign.materialButton(btnXoa);
        funcPanel.add(btnXoa);

        // tìm kiếm
        searchPanel = new JPanel();
        searchPanel.setBackground(Values.COLOR_BACKGROUND);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTuKhoa = new JTextField();
        txtTuKhoa.setPreferredSize(new Dimension(300, 40));
        txtTuKhoa.addKeyListener(txtTuKhoa_Change());
        MaterialDesign.materialTextField(txtTuKhoa);
        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        btnTimKiem.addActionListener(btnTimKiem_Click());
        MaterialDesign.materialButton(btnTimKiem);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        //table
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        danhSachBangDia = new DanhSachBangDia();
        bangDiaTableModel = new BangDiaTableModel(danhSachBangDia.getAll());

        tblBangDia = new JTable(bangDiaTableModel);
        tblBangDia.setRowSorter(sorter = new TableRowSorter<>(tblBangDia.getModel()));
        MaterialDesign.materialTable(tblBangDia);
        box.add(new JScrollPane(tblBangDia), BorderLayout.CENTER);
    }

    private void refreshTable(){
        tblBangDia.revalidate();
        tblBangDia.repaint();
    }

    private void filterTable(String filterText){
        if (filterText.isEmpty())
            sorter.setRowFilter(null);
        else{
            sorter.setRowFilter(RowFilter.regexFilter(filterText));
        }
    }

    private void thongBao(String message){
        JOptionPane.showMessageDialog(rootComponent, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO lấy dữ liệu từ popup


                BangDia bangDia = new BangDia(
                        "BD00010",
                        "Nhạc trẻ",
                        "Nhạc",
                        true,
                        "ABC",
                        "ABC",
                        5000.0,
                        10
                );

                if (danhSachBangDia.them(bangDia)){
                    refreshTable();
                }else{
                    thongBao("Thêm băng đĩa không thành công");
                }
            }
        };
    }

    private ActionListener btnSua_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblBangDia.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn băng đĩa cần sửa");
                    return;
                }

                // TODO lấy dữ liệu từ popup

                BangDia bangDia = new BangDia(
                        "BD00010",
                        "Nhạc Sơn Tùng",
                        "Nhạc",
                        true,
                        "ABC",
                        "ABC",
                        5000.0,
                        10
                );

                if (danhSachBangDia.sua(bangDia)){
                    refreshTable();
                }else{
                    thongBao("Thay đổi băng đĩa không thành công");
                }
            }
        };
    }

    private ActionListener btnXoa_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblBangDia.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn băng đĩa cần xoá");
                    return;
                }

                String maBangDia = bangDiaTableModel.getValueAt(index, 0).toString();
                String tenBangDia = bangDiaTableModel.getValueAt(index, 1).toString();

                int selected = JOptionPane.showConfirmDialog(
                        rootComponent,
                        String.format("Bạn có muốn xoá băng đĩa này không?\nTên băng đĩa: %s", tenBangDia),
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION
                );

                if ((selected == JOptionPane.OK_OPTION) && danhSachBangDia.xoa(maBangDia))
                    refreshTable();
            }
        };
    }

    private ActionListener btnTimKiem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable(txtTuKhoa.getText().trim());
            }
        };
    }

    private KeyListener txtTuKhoa_Change(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                filterTable(txtTuKhoa.getText().trim());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                filterTable(txtTuKhoa.getText().trim());
            }
        };
    }
}
