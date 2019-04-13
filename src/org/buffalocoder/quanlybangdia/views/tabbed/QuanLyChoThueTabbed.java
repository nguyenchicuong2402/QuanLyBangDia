package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.*;
import org.buffalocoder.quanlybangdia.models.tablemodel.ChoThueTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.dialog.ChoThueDialog;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class QuanLyChoThueTabbed extends JPanel {

    private DanhSachBangDia danhSachBangDia;
    private DanhSachKhachHang danhSachKhachHang;
    private DanhSachChoThue danhSachChoThue;
    private ThongBaoDialog thongBaoDialog;
    private final Component rootComponent = this;

    private JTable tblChoThue;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem, btnTraBangDia;
    private JTextField txtTimKiem;
    private TableRowSorter<TableModel> sorter;
    private ChoThueTableModel choThueTableModel;
    private JScrollPane scrollPane;
    private JComboBox<String> cbFilter;

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

        btnThem = new JButton("Thêm", MaterialDesign.ICON_THEM);
        btnThem.setPreferredSize(new Dimension(100, 40));
        btnThem.addActionListener(btnThem_Click());
        btnThem.setEnabled(false);
        btnThem.setMnemonic(KeyEvent.VK_T);
        MaterialDesign.materialButton(btnThem);
        funcPanel.add(btnThem);

        if (danhSachKhachHang.getAll().size() <= 0)
            btnThem.setToolTipText("Không có khách hàng trong dữ liệu");
        else if (danhSachBangDia.getAll().size() <= 0)
            btnThem.setToolTipText("Không có băng đĩa trong dữ liệu");
        else {
            btnThem.setEnabled(true);
            btnThem.setToolTipText("[Alt + T] Thêm hoá đơn mới");
        }

        btnSua = new JButton("Sửa", MaterialDesign.ICON_SUA);
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        btnSua.addActionListener(btnSua_Click());
        btnSua.setToolTipText("Vui lòng chọn hoá đơn cần cập nhật thông tin");
        btnSua.setMnemonic(KeyEvent.VK_S);
        MaterialDesign.materialButton(btnSua);
        btnSua.setEnabled(false);
        funcPanel.add(btnSua);

        btnTraBangDia = new JButton("Thanh toán hóa đơn", MaterialDesign.ICON_XOA);
        btnTraBangDia.setPreferredSize(new Dimension(200, 40));
        btnTraBangDia.addActionListener(btnTraBangDia_Click());
        btnTraBangDia.setToolTipText("Vui lòng chọn hoá đơn cần trả");
        btnTraBangDia.setEnabled(false);
        MaterialDesign.materialButton(btnTraBangDia);
        funcPanel.add(btnTraBangDia);

        btnXoa = new JButton("Xoá hoá đơn", MaterialDesign.ICON_XOA);
        btnXoa.setPreferredSize(new Dimension(150, 40));
        btnXoa.addActionListener(btnXoa_Click());
        btnXoa.setToolTipText("Vui lòng chọn hoá đơn cần xoá/trả");
        btnXoa.setEnabled(false);
        btnXoa.setMnemonic(KeyEvent.VK_X);
        MaterialDesign.materialButton(btnXoa);
        btnXoa.setBackground(MaterialDesign.COLOR_ERROR);
        funcPanel.add(btnXoa);

        cbFilter = new JComboBox<>(new String[]{"Tất cả", "Đã thanh toán", "Đang thuê"});
        cbFilter.setPreferredSize(new Dimension(150, 40));
        MaterialDesign.materialComboBox(cbFilter);
        cbFilter.addActionListener(cbFilter_Selected());
        funcPanel.add(cbFilter);

        // tìm kiếm
        searchPanel = new JPanel();
        MaterialDesign.materialPanel(searchPanel);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(300, 40));
        txtTimKiem.getDocument().addDocumentListener(txtTimKiem_DocumentListener());
        MaterialDesign.materialTextField(txtTimKiem);
        searchPanel.add(txtTimKiem);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        btnTimKiem.addActionListener(btnTimKiem_Click());
        MaterialDesign.materialButton(btnTimKiem);
        searchPanel.add(btnTimKiem);

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        choThueTableModel = new ChoThueTableModel(danhSachChoThue.getAll());

        sorter = new TableRowSorter<>(choThueTableModel);

        tblChoThue = new JTable(choThueTableModel);
        tblChoThue.setRowSorter(sorter);
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

    public void refresh(){
        try {
            danhSachBangDia.loadData();
            danhSachKhachHang.loadData();
            danhSachChoThue.loadData();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        if (danhSachKhachHang.getAll().size() > 0 && danhSachBangDia.getAll().size() > 0){
            btnThem.setEnabled(true);
            btnThem.setToolTipText("[Alt + T] Thêm hoá đơn");
        }else if (danhSachKhachHang.getAll().size() <= 0) {
            btnThem.setEnabled(false);
            btnThem.setToolTipText("Vui lòng thêm khách hàng");
        }else if (danhSachBangDia.getAll().size() <= 0){
            btnThem.setEnabled(false);
            btnThem.setToolTipText("Vui lòng thêm băng đĩa");
        }

        choThueTableModel.setModel(danhSachChoThue.getAll());
        tblChoThue.setModel(choThueTableModel);

        sorter.setModel(choThueTableModel);

        tblChoThue.revalidate();
        tblChoThue.repaint();
    }

    private void filterTable(String filter_text) {
        if (filter_text.isEmpty())
            sorter.setRowFilter(null);
        else {
            try{
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    @Override
                    public boolean include(Entry<?, ?> entry) {
                        return (entry.getStringValue(1).contains(filter_text));
                    }
                };
                sorter.setRowFilter(filter);
            }catch (NumberFormatException e){
                txtTimKiem.selectAll();
            }
        }
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
                        refresh();
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
                    if (hoaDon != null){
                        danhSachChoThue.sua(hoaDon);
                        refresh();
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
                }else if (String.valueOf(tblChoThue.getValueAt(index, 7)).equalsIgnoreCase("Đã thanh toán")){
                    thongBao("Không thể xoá hoá đơn đã thanh toán");
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
                        refresh();
                    }catch (Exception e1){
                        thongBaoLoi(e1.getMessage());
                    }
                }
            }
        };
    }

    private ActionListener btnTraBangDia_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblChoThue.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn hoá đơn cần thanh toán");
                    return;
                }

                String maHoaDon = choThueTableModel.getValueAt(index, 0).toString();
                String tenKhachHang = choThueTableModel.getValueAt(index, 1).toString();
                String tenBangDia = choThueTableModel.getValueAt(index, 2).toString();

                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Xác nhận thanh toán hoá đơn này?\nTên khách hàng: %s\nTên băng đĩa: %s", tenKhachHang, tenBangDia),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    try{
                        danhSachChoThue.thanhToanHoaDon(maHoaDon);
                        refresh();
                    }catch (Exception e1){
                        thongBaoLoi(e1.getMessage());
                    }
                }
            }
        };
    }

    private ActionListener btnTimKiem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable(txtTimKiem.getText().trim());
            }
        };
    }

    private ActionListener cbFilter_Selected(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filter_text = String.valueOf(cbFilter.getSelectedItem());

                if (filter_text.equalsIgnoreCase("Tất cả"))
                    sorter.setRowFilter(null);
                else {
                    try{
                        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                            @Override
                            public boolean include(Entry<?, ?> entry) {
                                return (entry.getStringValue(7).contains(filter_text));
                            }
                        };
                        sorter.setRowFilter(filter);
                    }catch (NumberFormatException e1){
                        cbFilter.setSelectedIndex(0);
                    }
                }
            }
        };
    }

    private DocumentListener txtTimKiem_DocumentListener(){
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(txtTimKiem.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(txtTimKiem.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable(txtTimKiem.getText().trim());
            }
        };
    }

    private MouseListener tblChoThue_MouseListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = tblChoThue.getSelectedRow();

                btnSua.setEnabled(true);
                btnSua.setToolTipText("[Alt + S] Cập nhật thông tin hoá đơn");

                btnXoa.setToolTipText("[Alt + X] Xoá hoá đơn");
                btnXoa.setEnabled(true);

                if (rowSelected != -1 &&
                        String.valueOf(tblChoThue.getValueAt(rowSelected, 7)).equalsIgnoreCase("Đang thuê")){
                    btnTraBangDia.setToolTipText("[Alt + X] Thanh toán hoá đơn");
                    btnTraBangDia.setEnabled(true);
                }


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
