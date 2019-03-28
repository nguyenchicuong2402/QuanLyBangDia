package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachKhachHang;
import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.models.tablemodel.KhachHangTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class QuanLyKhachHangTabbed extends JPanel {
    private JTable tblKhachHang;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;
    private KhachHangTableModel khachHangTableModel;
    private DanhSachKhachHang danhSachKhachHang;
    private Component rootComponent = this;

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

        danhSachKhachHang = new DanhSachKhachHang();
        khachHangTableModel = new KhachHangTableModel(danhSachKhachHang.getAll());

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));

        tblKhachHang = new JTable(khachHangTableModel);
        MaterialDesign.materialTable(tblKhachHang);
        box.add(new JScrollPane(tblKhachHang));

        this.add(box, BorderLayout.CENTER);
    }

    private void refreshTable(){
        tblKhachHang.revalidate();
        tblKhachHang.repaint();
    }

    private void thongBao(String message){
        JOptionPane.showMessageDialog(rootComponent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO lấy dữ liệu nhập vào

                KhachHang khachHang = new KhachHang(
                        "222222222",
                        "Nguyễn Văn A",
                        true,
                        "0123456789",
                        "IUH",
                        Date.valueOf("1999-12-12"),
                        "KH00002"
                );

                if (danhSachKhachHang.them(khachHang)){
                    refreshTable();
                }else thongBao("Thêm khách hàng không thành công");
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

                if ((selected == JOptionPane.OK_OPTION) && danhSachKhachHang.xoa(maKhachHang))
                    refreshTable();
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

                // TODO lấy dữ liệu từ popup

                KhachHang khachHang = new KhachHang(
                        "222222222",
                        "IUH",
                        true,
                        "0123456789",
                        "IUH",
                        Date.valueOf("1999-12-12"),
                        "KH00002"
                );

                if (danhSachKhachHang.sua(khachHang)){
                    refreshTable();
                }else{
                    thongBao("Thay đổi thông tin khách hàng không thành công");
                }
            }
        };
    }
}
