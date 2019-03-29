package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachChoThue;
import org.buffalocoder.quanlybangdia.models.HoaDon;
import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.models.tablemodel.ChoThueTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class QuanLyChoThueTabbed extends JPanel {

    private JTable tblChoThue;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;
    private ChoThueTableModel choThueTableModel;
    private DanhSachChoThue danhSachHoaDon;
    private final Component rootComponent = this;

    public QuanLyChoThueTabbed(){
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

        danhSachHoaDon = new DanhSachChoThue();
        danhSachHoaDon.loadData();
        choThueTableModel = new ChoThueTableModel(danhSachHoaDon.getAll());

        tblChoThue = new JTable(choThueTableModel);
        MaterialDesign.materialTable(tblChoThue);
        box.add(new JScrollPane(tblChoThue));
    }

    private void thongBao(String message){
        JOptionPane.showMessageDialog(rootComponent, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

    private void refreshTable(){
        tblChoThue.revalidate();
        tblChoThue.repaint();
    }

    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO lấy dữ liệu từ popup

                BangDia bangDia = QuanLyBangDiaTabbed.danhSachBangDia.getAll().get(2);
                KhachHang khachHang = QuanLyKhachHangTabbed.danhSachKhachHang.getAll().get(1);
                HoaDon hoaDon = new HoaDon(
                        bangDia,
                        10,
                        1,
                        "HD00003",
                        khachHang
                );

                if (danhSachHoaDon.them(hoaDon)){
                    refreshTable();
                }else{
                    thongBao("Thêm hoá đơn không thành công");
                }
            }
        };
    }

    private ActionListener btnSua_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblChoThue.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn hoá đơn cần sửa");
                    return;
                }

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

                KhachHang khachHang = new KhachHang(
                        "222222222",
                        "Nguyễn Văn A",
                        true,
                        "0123456789",
                        "IUH",
                        Date.valueOf("1999-12-12"),
                        "KH00002"
                );

                HoaDon hoaDon = new HoaDon(
                        bangDia,
                        20,
                        1,
                        "HD00005",
                        khachHang
                );

                if (danhSachHoaDon.sua(hoaDon)){
                    refreshTable();
                }else{
                    thongBao("Thay đổi hoá đơn không thành công");
                }
            }
        };
    }

    private ActionListener btnXoa_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblChoThue.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn hoá đơn cần xoá");
                    return;
                }

                String maHoaDon = choThueTableModel.getValueAt(index, 0).toString();
                String tenKhachHang = choThueTableModel.getValueAt(index, 1).toString();
                String tenBangDia = choThueTableModel.getValueAt(index, 2).toString();

                int selected = JOptionPane.showConfirmDialog(
                        rootComponent,
                        String.format("Bạn có muốn xoá hoá đơn này không?\nTên khách hàng: %s\nTên băng đĩa: %s", tenKhachHang, tenBangDia),
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.OK_CANCEL_OPTION
                );

                if ((selected == JOptionPane.OK_OPTION) && danhSachHoaDon.xoa(maHoaDon))
                    refreshTable();
            }
        };
    }

}
