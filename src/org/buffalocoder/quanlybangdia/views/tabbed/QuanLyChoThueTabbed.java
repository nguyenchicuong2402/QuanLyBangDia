package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.*;
import org.buffalocoder.quanlybangdia.models.tablemodel.ChoThueTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.dialog.ChoThueDialog;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class QuanLyChoThueTabbed extends JPanel {

    private DanhSachBangDia danhSachBangDia;
    private DanhSachKhachHang danhSachKhachHang;
    private DanhSachChoThue danhSachChoThue;
    private ThongBaoDialog thongBaoDialog;

    private JTable tblChoThue;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;
    private ChoThueTableModel choThueTableModel;
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
        topPanel.add(funcPanel, BorderLayout.WEST);

        btnThem = new JButton("Thêm");
        btnThem.setPreferredSize(new Dimension(90, 40));
        btnThem.addActionListener(btnThem_Click());
        btnThem.setEnabled(false);

        if (danhSachKhachHang.getAll().size() <= 0)
            btnThem.setToolTipText("Không có khách hàng trong dữ liệu");
        else if (danhSachBangDia.getAll().size() <= 0)
            btnThem.setToolTipText("Không có băng đĩa trong dữ liệu");
        else {
            btnThem.setEnabled(true);
            btnThem.setToolTipText("[Alt + T] Thêm hoá đơn mới");
        }

        btnThem.setMnemonic(KeyEvent.VK_T);
        MaterialDesign.materialButton(btnThem);
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        btnSua.addActionListener(btnSua_Click());
        btnSua.setToolTipText("Vui lòng chọn hoá đơn cần cập nhật thông tin");
        btnSua.setMnemonic(KeyEvent.VK_S);
        MaterialDesign.materialButton(btnSua);
        btnSua.setEnabled(false);
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnXoa.addActionListener(btnXoa_Click());
        btnXoa.setToolTipText("Vui lòng chọn hoá đơn cần xoá");
        btnXoa.setEnabled(false);
        btnXoa.setMnemonic(KeyEvent.VK_X);
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
        searchPanel.add(txtTuKhoa);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnTimKiem);
        searchPanel.add(btnTimKiem);

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        choThueTableModel = new ChoThueTableModel(danhSachChoThue.getAll());

        tblChoThue = new JTable(choThueTableModel);
        MaterialDesign.materialTable(tblChoThue);
        tblChoThue.addMouseListener(tblChoThue_MouseListener());

        scrollPane = new JScrollPane(tblChoThue);
        MaterialDesign.materialScrollPane(scrollPane);
        box.add(scrollPane, BorderLayout.CENTER);
    }

    public QuanLyChoThueTabbed(){
        try {
            danhSachChoThue = new DanhSachChoThue();
            danhSachKhachHang = new DanhSachKhachHang();
            danhSachBangDia = new DanhSachBangDia();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        prepareUI();
    }

    private boolean kiemTraTinhTrangThue(HoaDon hoaDon, boolean isChinhSua){
        // kiểm tra số lượng đặt có đủ không
        if ((hoaDon.getBangDia().getSoLuongTon() + (isChinhSua ? hoaDon.getSoLuong() : 0)) < hoaDon.getSoLuong()){
            thongBao("Không đủ số lượng băng đĩa");
            return false;
        }

        // kiểm tra băng đĩa
        if (!hoaDon.getBangDia().isTinhTrang()){
            thongBao("Băng đĩa không còn sử dụng được");
            return false;
        }

        // kiểm tra người dùng có mượn quá hạn không
        for (HoaDon hoaDon1 : danhSachChoThue.getAll()){
            if (hoaDon1.getKhachHang().getMaKH().equals(
                    hoaDon.getKhachHang().getMaKH())){
                if (!hoaDon1.isTinhTrangThue()){
                    thongBao("Khách hàng thuê băng đĩa quá hạn\nVui lòng nhắc khách hàng trả băng đĩa trước khi thuê");
                    return false;
                }
            }
        }

        return true;
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

    public void refreshTable(){
        try {
            danhSachBangDia.loadData();
            danhSachKhachHang.loadData();
            danhSachChoThue.loadData();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        choThueTableModel.setModel(danhSachChoThue.getAll());
        tblChoThue.setModel(choThueTableModel);

        tblChoThue.revalidate();
        tblChoThue.repaint();
    }

    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChoThueDialog choThueDialog = new ChoThueDialog(new JFrame(), null);
                HoaDon hoaDon = choThueDialog.getHoaDon();

                try{
                    if (hoaDon != null && kiemTraTinhTrangThue(hoaDon, false)){
                        danhSachChoThue.them(hoaDon);
                        refreshTable();
                    }
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
                int index = tblChoThue.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn hoá đơn cần sửa");
                    return;
                }

                HoaDon hoaDon = danhSachChoThue.getAll().get(index);
                ChoThueDialog choThueDialog = new ChoThueDialog(new JFrame(), hoaDon);
                hoaDon = choThueDialog.getHoaDon();

                try{
                    if (hoaDon != null && kiemTraTinhTrangThue(hoaDon, true)){
                        danhSachChoThue.sua(hoaDon);
                        refreshTable();
                    }
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
                int index = tblChoThue.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn hoá đơn cần xoá");
                    return;
                }

                String maHoaDon = choThueTableModel.getValueAt(index, 0).toString();
                String tenKhachHang = choThueTableModel.getValueAt(index, 1).toString();
                String tenBangDia = choThueTableModel.getValueAt(index, 2).toString();

                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Bạn có muốn xoá hoá đơn này không?\nTên khách hàng: %s\nTên băng đĩa: %s", tenKhachHang, tenBangDia),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    try{
                        danhSachChoThue.xoa(maHoaDon);
                        refreshTable();
                    }catch (Exception e1){
                        thongBaoLoi(e1.getMessage());
                    }
                }
            }
        };
    }

    private MouseListener tblChoThue_MouseListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSua.setEnabled(true);
                btnSua.setToolTipText("[Alt + S] Cập nhật thông tin hoá đơn");

                btnXoa.setToolTipText("[Alt + X] Xoá hoá đơn");
                btnXoa.setEnabled(true);
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
