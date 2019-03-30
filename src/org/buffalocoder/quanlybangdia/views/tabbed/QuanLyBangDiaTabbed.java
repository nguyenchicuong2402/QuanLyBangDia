package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.tablemodel.BangDiaTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;
import org.buffalocoder.quanlybangdia.views.DangNhap;
import org.buffalocoder.quanlybangdia.views.dialog.BangDiaDialog;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class QuanLyBangDiaTabbed extends JPanel {
    private final boolean IS_ADMIN = DangNhap.taiKhoan.getLoaiTaiKhoan() == 1;

    private JTable tblBangDia;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private BangDiaTableModel bangDiaTableModel;
    protected static DanhSachBangDia danhSachBangDia;
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
        MaterialDesign.materialTextField(txtTuKhoa);
        searchPanel.add(txtTuKhoa, BorderLayout.CENTER);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnTimKiem);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        //table
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        try{
            danhSachBangDia = new DanhSachBangDia();
        }catch (Exception e){
            thongBaoLoi(e.getMessage());
        }

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

    private void thongBao(String message){
        JOptionPane.showMessageDialog(rootComponent, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

    private void thongBaoLoi(String message){
        JOptionPane.showMessageDialog(rootComponent, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BangDiaDialog bangDiaDialog = new BangDiaDialog(new JFrame(), null);

                BangDia bangDia = bangDiaDialog.getBangDia();
                if (bangDia == null)
                    return;

                try{
                    danhSachBangDia.them(bangDia);
                    refreshTable();
                }catch (Exception e1){
                    thongBaoLoi(e1.getMessage());
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

                BangDiaDialog bangDiaDialog = new BangDiaDialog(new JFrame(),
                        danhSachBangDia.getAll().get(index));

                try{
                    danhSachBangDia.sua(bangDiaDialog.getBangDia());
                    refreshTable();
                }catch (Exception e1){
                    thongBaoLoi(e1.getMessage());
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

                if (selected == JOptionPane.OK_OPTION){
                    try{
                        danhSachBangDia.xoa(maBangDia);
                        refreshTable();
                    }catch (Exception e1){
                        thongBaoLoi(e1.getMessage());
                    }
                }
            }
        };
    }
}
