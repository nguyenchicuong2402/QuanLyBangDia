package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.dao.TaiKhoanDAO;
import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachNhanVien;
import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.models.tablemodel.NhanVienTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.dialog.NhanVienDialog;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class QuanLyNhanVienTabbed extends JPanel {
    private DanhSachNhanVien danhSachNhanVien;
    private TaiKhoanDAO taiKhoanDAO;
    private ThongBaoDialog thongBaoDialog;

    private JTable tblNhanVien;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTimKiem;
    private NhanVienTableModel nhanVienTableModel;
    private final Component rootComponent = this;
    private JScrollPane scrollPane;
    private TableRowSorter<TableModel> sorter;

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
        MaterialDesign.materialButton(btnThem);
        btnThem.addActionListener(btnThem_Click());
        btnThem.setToolTipText("[Alt + T] Thêm nhân viên mới");
        btnThem.setMnemonic(KeyEvent.VK_T);
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa", MaterialDesign.ICON_SUA);
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnSua);
        btnSua.addActionListener(btnSua_Click());
        btnSua.setToolTipText("Vui lòng chọn nhân viên cần cập nhật thông tin");
        btnSua.setMnemonic(KeyEvent.VK_S);
        btnSua.setEnabled(false);
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá", MaterialDesign.ICON_XOA);
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnXoa);
        btnXoa.addActionListener(btnXoa_Click());
        btnXoa.setToolTipText("Vui lòng chọn nhân viên cần xoá");
        btnXoa.setEnabled(false);
        btnXoa.setMnemonic(KeyEvent.VK_X);
        btnXoa.setBackground(MaterialDesign.COLOR_ERROR);
        funcPanel.add(btnXoa);

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

        //table
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        nhanVienTableModel = new NhanVienTableModel(danhSachNhanVien.getAll());

        sorter = new TableRowSorter<>(nhanVienTableModel);

        tblNhanVien = new JTable(nhanVienTableModel);
        tblNhanVien.setRowSorter(sorter);
        MaterialDesign.materialTable(tblNhanVien);
        tblNhanVien.addMouseListener(tblNhanVien_MouseListener());

        scrollPane = new JScrollPane(tblNhanVien);
        MaterialDesign.materialScrollPane(scrollPane);
        box.add(scrollPane, BorderLayout.CENTER);
    }

    public QuanLyNhanVienTabbed(){
        try{
            taiKhoanDAO = TaiKhoanDAO.getInstance();
            danhSachNhanVien = new DanhSachNhanVien();
        }catch (Exception e){
            thongBaoLoi(e.getMessage());
        }

        prepareUI();
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
    
    public void refreshTable(){
        try {
            danhSachNhanVien.loadData();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        nhanVienTableModel.setModel(danhSachNhanVien.getAll());
        tblNhanVien.setModel(nhanVienTableModel);

        sorter.setModel(nhanVienTableModel);

        tblNhanVien.revalidate();
        tblNhanVien.repaint();
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
                NhanVienDialog nhanVienDialog = new NhanVienDialog(new JFrame(), null, null);

                NhanVien nhanVien = nhanVienDialog.getNhanVien();
                TaiKhoan taiKhoan = nhanVienDialog.getTaiKhoan();

                if (nhanVien == null && taiKhoan == null)
                    return;

                try{
                    danhSachNhanVien.them(nhanVien);
                    taiKhoanDAO.themTaiKhoan(taiKhoan);
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
                int index = tblNhanVien.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn nhân viên cần sửa");
                    return;
                }

                NhanVien nhanVien = danhSachNhanVien.getAll().get(index);
                TaiKhoan taiKhoan = null;
                try {
                    taiKhoan = taiKhoanDAO.getTaiKhoanByMaNhanVien(nhanVien.getMaNhanVien());
                } catch (Exception e1) {
                    thongBaoLoi(e1.getMessage());
                    return;
                }
                NhanVienDialog nhanVienDialog = new NhanVienDialog(new JFrame(), nhanVien, taiKhoan);

                nhanVien = nhanVienDialog.getNhanVien();
                taiKhoan = nhanVienDialog.getTaiKhoan();

                if (nhanVien == null)
                    return;

                try{
                    danhSachNhanVien.sua(nhanVien);

                    if (taiKhoan != null)
                        taiKhoanDAO.suaTaiKhoan(taiKhoan);

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
                int index = tblNhanVien.getSelectedRow();

                if (index == -1){
                    thongBao("Vui lòng chọn nhân viên cần xoá");
                    return;
                }

                String maNhanVien = nhanVienTableModel.getValueAt(index, 0).toString();
                String tenNhanVien = nhanVienTableModel.getValueAt(index, 1).toString();

                if (maNhanVien.equals("NV00001")){
                    thongBao("Không thể xoá admin mặc định");
                    return;
                }

                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Bạn có muốn xoá nhân viên này không?\nTên nhân viên: %s", tenNhanVien),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    try{
                        danhSachNhanVien.xoa(maNhanVien);
                        refreshTable();
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

    private MouseListener tblNhanVien_MouseListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnSua.setEnabled(true);
                btnSua.setToolTipText("[Alt + S] Cập nhật thông tin nhân viên");

                btnXoa.setToolTipText("[Alt + X] Xoá nhân viên");
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
