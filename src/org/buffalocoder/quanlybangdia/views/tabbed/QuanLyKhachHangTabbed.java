package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachKhachHang;
import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.models.tablemodel.KhachHangTableModel;
import org.buffalocoder.quanlybangdia.utils.Colors;
import org.buffalocoder.quanlybangdia.utils.Fonts;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.dialog.KhachHangDialog;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class QuanLyKhachHangTabbed extends JPanel {
    private JTable tblKhachHang;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private KhachHangTableModel khachHangTableModel;
    protected static DanhSachKhachHang danhSachKhachHang;
    private Component rootComponent = this;
    private JScrollPane scrollPane;

    public QuanLyKhachHangTabbed(){
        this.setLayout(new BorderLayout());
        this.setFont(Fonts.DEFAULT);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Colors.BACKGROUND);

        /*========== TOP PANEL ==========*/
        topPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(topPanel);
        this.add(topPanel, BorderLayout.NORTH);

        // chức năng
        funcPanel = new JPanel();
        MaterialDesign.materialPanel(funcPanel);
        topPanel.add(funcPanel, BorderLayout.WEST);

        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(90, 40));
        MaterialDesign.materialButton(btnThem);
        btnThem.addActionListener(btnThem_Click());
        btnThem.setToolTipText("[Alt + T] Thêm khách hàng mới");
        btnThem.setMnemonic(KeyEvent.VK_T);
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnSua);
        btnSua.addActionListener(btnSua_Click());
        btnSua.setToolTipText("Vui lòng chọn khách hàng cần cập nhật thông tin");
        btnSua.setMnemonic(KeyEvent.VK_S);
        btnSua.setEnabled(false);
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnXoa);
        btnXoa.addActionListener(btnXoa_Click());
        btnXoa.setToolTipText("[Alt + X] Xoá khách hàng");
        btnXoa.setMnemonic(KeyEvent.VK_X);
        btnXoa.setBackground(Colors.ERROR);
        funcPanel.add(btnXoa);

        // tìm kiếm
        searchPanel = new JPanel();
        MaterialDesign.materialPanel(searchPanel);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTuKhoa = new JTextField();
        txtTuKhoa.setPreferredSize(new Dimension(300, 40));
        MaterialDesign.materialTextField(txtTuKhoa);
        searchPanel.add(txtTuKhoa);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnTimKiem);
        searchPanel.add(btnTimKiem);

        //table
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        try{
            danhSachKhachHang = new DanhSachKhachHang();
        }catch (Exception e){
            thongBaoLoi(e.getMessage());
        }
        khachHangTableModel = new KhachHangTableModel(danhSachKhachHang.getAll());

        tblKhachHang = new JTable(khachHangTableModel);
        MaterialDesign.materialTable(tblKhachHang);
        tblKhachHang.addMouseListener(tblKhachHang_MouseListener());

        scrollPane = new JScrollPane(tblKhachHang);
        MaterialDesign.materialScrollPane(scrollPane);
        box.add(scrollPane, BorderLayout.CENTER);
    }

    private void refreshTable(){
        tblKhachHang.revalidate();
        tblKhachHang.repaint();
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
                KhachHangDialog khachHangDialog = new KhachHangDialog(new JFrame(), null);

                KhachHang khachHang = khachHangDialog.getKhachHang();
                if (khachHang == null)
                    return;

                try{
                    danhSachKhachHang.them(khachHang);
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
                int index = tblKhachHang.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn khách hàng cần sửa");
                    return;
                }

                KhachHangDialog khachHangDialog = new KhachHangDialog(new JFrame(),
                        danhSachKhachHang.getAll().get(index));

                try{
                    danhSachKhachHang.sua(khachHangDialog.getKhachHang());
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
                int index = tblKhachHang.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn khách hàng cần xoá");
                    return;
                }

                String maKhachHang = khachHangTableModel.getValueAt(index, 0).toString();
                String tenKhachHang = khachHangTableModel.getValueAt(index, 1).toString();

                int selected = JOptionPane.showConfirmDialog(
                        rootComponent,
                        String.format("Bạn có muốn xoá khách hàng này không?\nTên khách hàng: %s", tenKhachHang),
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (selected == JOptionPane.OK_OPTION){
                    try{
                        danhSachKhachHang.xoa(maKhachHang);
                        refreshTable();
                    }catch (Exception e1){
                        thongBaoLoi(e1.getMessage());
                    }
                }

            }
        };
    }

    private MouseListener tblKhachHang_MouseListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSua.setEnabled(true);
                btnSua.setToolTipText("[Alt + S] Cập nhật thông tin khách hàng");
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
}
