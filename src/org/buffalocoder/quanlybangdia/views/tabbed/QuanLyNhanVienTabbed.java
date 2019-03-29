package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachNhanVien;
import org.buffalocoder.quanlybangdia.models.HoaDon;
//import org.buffalocoder.quanlybangdia.models.tablemodel.HoaDonTableModel;
import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.tablemodel.NhanVienTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;
import org.buffalocoder.quanlybangdia.views.dialog.NhanVienDialog;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class QuanLyNhanVienTabbed extends JPanel {
    private JTable tblNhanVien;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private DanhSachNhanVien danhSachNhanVien;
    private NhanVienTableModel nhanVienTableModel;
    private final Component rootComponent = this;

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
        btnThem.addActionListener(btnThem_Click());
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnSua);
        btnSua.addActionListener(btnSua_Click());
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnXoa);
        btnXoa.addActionListener(btnXoa_Click());
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

        //table
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        danhSachNhanVien = new DanhSachNhanVien();
        nhanVienTableModel = new NhanVienTableModel(danhSachNhanVien.getAll());

        tblNhanVien = new JTable(nhanVienTableModel);
        MaterialDesign.materialTable(tblNhanVien);
        box.add(new JScrollPane(tblNhanVien), BorderLayout.CENTER);
    }

    private void refreshTable(){
        tblNhanVien.revalidate();
        tblNhanVien.repaint();
    }

    private void thongBao(String message){
        JOptionPane.showMessageDialog(rootComponent, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVienDialog nhanVienDialog = new NhanVienDialog(new JFrame(), null);

                NhanVien nhanVien = nhanVienDialog.getNhanVien();
                if (nhanVien == null)
                    return;

                if (danhSachNhanVien.them(nhanVien)){
                    refreshTable();
                }else{
                    thongBao("Thêm nhân viên không thành công");
                }
            }
        };
    }

    private ActionListener btnSua_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblNhanVien.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn nhân viên cần sửa");
                    return;
                }

                NhanVienDialog nhanVienDialog = new NhanVienDialog(new JFrame(),
                        danhSachNhanVien.getAll().get(index));

                NhanVien nhanVien = nhanVienDialog.getNhanVien();
                if (nhanVien == null)
                    return;

                if (danhSachNhanVien.sua(nhanVien)){
                    refreshTable();
                }else{
                    thongBao("Thay đổi nhân viên không thành công");
                }
            }
        };
    }

    private ActionListener btnXoa_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblNhanVien.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn khách hàng cần xoá");
                    return;
                }

                String maKhachHang = nhanVienTableModel.getValueAt(index, 0).toString();
                String tenKhachHang = nhanVienTableModel.getValueAt(index, 1).toString();

                int selected = JOptionPane.showConfirmDialog(
                        rootComponent,
                        String.format("Bạn có muốn xoá khách hàng này không?\nTên khách hàng: %s", tenKhachHang),
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION
                );

                if ((selected == JOptionPane.OK_OPTION) && danhSachNhanVien.xoa(maKhachHang))
                    refreshTable();
            }
        };
    }
}
