package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachNhanVien;
import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.tablemodel.NhanVienTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class QuanLyNhanVienTabbed extends JPanel {
    private JTable tblNhanVien;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;
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

        tblNhanVien = new JTable(nhanVienTableModel);
        MaterialDesign.materialTable(tblNhanVien);
        box.add(new JScrollPane(tblNhanVien));
    }

    private void refreshTable(){
        tblNhanVien.revalidate();
        tblNhanVien.repaint();
    }

    private void thongBao(String message){
        JOptionPane.showMessageDialog(rootComponent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO lấy dữ liệu nhập vào

                NhanVien nhanVien = new NhanVien(
                        "333333333",
                        "Nguyễn Văn B",
                        true,
                        "0123456789",
                        "IUH",
                        Date.valueOf("1999-12-12"),
                        "NV00010",
                        "Thống kê"
                );

                if (danhSachNhanVien.them(nhanVien)){
                    refreshTable();
                }else thongBao("Thêm nhân viên không thành công");
            }
        };
    }

    private ActionListener btnXoa_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblNhanVien.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn nhân viên cần xoá");
                    return;
                }

                String maNhanVien = nhanVienTableModel.getValueAt(index, 0).toString();
                String tenNhanVien = nhanVienTableModel.getValueAt(index, 1).toString();

                int selected = JOptionPane.showConfirmDialog(
                        rootComponent,
                        String.format("Bạn có muốn xoá nhân viên này không?\nTên nhân viên: %s", tenNhanVien),
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION
                );

                if ((selected == JOptionPane.OK_OPTION) && danhSachNhanVien.xoa(maNhanVien))
                    refreshTable();
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

                // TODO lấy dữ liệu từ popup

                NhanVien nhanVien = new NhanVien(
                        "333333333",
                        "Khoa Julies",
                        true,
                        "0123456789",
                        "IUH",
                        Date.valueOf("1999-12-12"),
                        "NV00010",
                        "Thống kê"
                );

                if (danhSachNhanVien.sua(nhanVien)){
                    refreshTable();
                }else{
                    thongBao("Thay đổi thông tin nhân viên không thành công");
                }
            }
        };
    }
}
