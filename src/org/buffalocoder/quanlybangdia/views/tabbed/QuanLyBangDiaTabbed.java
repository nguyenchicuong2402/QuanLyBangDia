package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.tablemodel.BangDiaTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.DangNhap;
import org.buffalocoder.quanlybangdia.views.dialog.BangDiaDialog;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class QuanLyBangDiaTabbed extends JPanel {
    private final boolean IS_ADMIN = DangNhap.taiKhoan.getLoaiTaiKhoan() == 1;
    private DanhSachBangDia danhSachBangDia;
    private ThongBaoDialog thongBaoDialog;

    private JTable tblBangDia;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private BangDiaTableModel bangDiaTableModel;
    private TableRowSorter<TableModel> sorter;
    private final Component rootComponent = this;
    private JScrollPane scrollPane;

    private void prepareUI(){
        this.setLayout(new BorderLayout());
        this.setFont(MaterialDesign.FONT_DEFAULT);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(MaterialDesign.COLOR_BACKGROUND);

        /*========== TOP PANEL ==========*/
        topPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(topPanel);
        this.add(topPanel, BorderLayout.NORTH);

        // chức năng
        funcPanel = new JPanel();
        MaterialDesign.materialPanel(funcPanel);
        if (IS_ADMIN)
            topPanel.add(funcPanel, BorderLayout.WEST);

        btnThem = new JButton("Thêm", MaterialDesign.ICON_THEM);
        btnThem.setPreferredSize(new Dimension(90, 40));
        btnThem.addActionListener(btnThem_Click());
        btnThem.setToolTipText("[Alt + T] Thêm băng đĩa mới");
        btnThem.setMnemonic(KeyEvent.VK_T);
        MaterialDesign.materialButton(btnThem);
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa", MaterialDesign.ICON_SUA);
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        btnSua.addActionListener(btnSua_Click());
        btnSua.setToolTipText("Vui lòng chọn băng đĩa cần cập nhật thông tin");
        btnSua.setMnemonic(KeyEvent.VK_S);
        btnSua.setEnabled(false);
        MaterialDesign.materialButton(btnSua);
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá", MaterialDesign.ICON_XOA);
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnXoa.addActionListener(btnXoa_Click());
        btnXoa.setToolTipText("Vui lòng chọn băng đĩa cần xoá");
        btnXoa.setMnemonic(KeyEvent.VK_X);
        btnXoa.setEnabled(false);
        MaterialDesign.materialButton(btnXoa);
        btnXoa.setBackground(MaterialDesign.COLOR_ERROR);
        funcPanel.add(btnXoa);

        // tìm kiếm
        searchPanel = new JPanel();
        MaterialDesign.materialPanel(searchPanel);
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

        bangDiaTableModel = new BangDiaTableModel(danhSachBangDia.getAll());

        tblBangDia = new JTable(bangDiaTableModel);
        tblBangDia.setRowSorter(sorter = new TableRowSorter<>(tblBangDia.getModel()));
        MaterialDesign.materialTable(tblBangDia);
        tblBangDia.addMouseListener(tblBangDia_MouseListener());

        scrollPane = new JScrollPane(tblBangDia);
        MaterialDesign.materialScrollPane(scrollPane);
        box.add(scrollPane, BorderLayout.CENTER);
    }

    public QuanLyBangDiaTabbed(){
        try {
            danhSachBangDia = new DanhSachBangDia();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        prepareUI();
    }

    public void refreshTable(){
        try {
            danhSachBangDia.loadData();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        bangDiaTableModel.setModel(danhSachBangDia.getAll());
        tblBangDia.setModel(bangDiaTableModel);

        tblBangDia.revalidate();
        tblBangDia.repaint();
    }

    private void thongBao(String message){
        thongBaoDialog = new ThongBaoDialog(
                new JFrame(),
                "Thông báo",
                message,
                ThongBaoDialog.OK_OPTION
        );
    }

    private void thongBaoLoi(String message){
        thongBaoDialog = new ThongBaoDialog(
                new JFrame(),
                "Lỗi",
                message,
                ThongBaoDialog.OK_OPTION
        );
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

                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Bạn có muốn xoá băng đĩa này không?\nTên băng đĩa: %s", tenBangDia),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
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

    private MouseListener tblBangDia_MouseListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSua.setEnabled(true);
                btnSua.setToolTipText("[Alt + S] Cập nhật thông tin băng đĩa");

                btnXoa.setEnabled(true);
                btnXoa.setToolTipText("[Alt + X] Xoá băng đĩa");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    // TODO thêm 1 button xoá băng đĩa hỏng

}
