package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachKhachHang;
import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.models.tablemodel.KhachHangTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.dialog.KhachHangDialog;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;

public class QuanLyKhachHangTabbed extends JPanel {
    private DanhSachKhachHang danhSachKhachHang;
    private ThongBaoDialog thongBaoDialog;

    private JTable tblKhachHang;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTimKiem;
    private KhachHangTableModel khachHangTableModel;
    private Component rootComponent = this;
    private JScrollPane scrollPane;
    private TableRowSorter<TableModel> sorter;


    /**
     * Tao GUI
     */
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
        btnThem.setToolTipText("[Alt + T] Thêm khách hàng mới");
        btnThem.setMnemonic(KeyEvent.VK_T);
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa", MaterialDesign.ICON_SUA);
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnSua);
        btnSua.addActionListener(btnSua_Click());
        btnSua.setToolTipText("Vui lòng chọn khách hàng cần cập nhật thông tin");
        btnSua.setMnemonic(KeyEvent.VK_S);
        btnSua.setEnabled(false);
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá", MaterialDesign.ICON_XOA);
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        MaterialDesign.materialButton(btnXoa);
        btnXoa.addActionListener(btnXoa_Click());
        btnXoa.setToolTipText("Vui lòng chọn khách hàng cần xoá");
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
        txtTimKiem.getDocument().addDocumentListener(txtTimKiem_DocumentListerner());
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

        khachHangTableModel = new KhachHangTableModel(danhSachKhachHang.getAll());

        sorter = new TableRowSorter<>(khachHangTableModel);

        tblKhachHang = new JTable(khachHangTableModel);
        tblKhachHang.setRowSorter(sorter);
        MaterialDesign.materialTable(tblKhachHang);
        tblKhachHang.addMouseListener(tblKhachHang_MouseListener());

        scrollPane = new JScrollPane(tblKhachHang);
        MaterialDesign.materialScrollPane(scrollPane);
        box.add(scrollPane, BorderLayout.CENTER);

        refresh(true);
    }


    /**
     * Lấy vị trí đang chọn trong table
     * @return
     */
    private int getCurrentSelected(){
        try{
            return tblKhachHang.convertRowIndexToModel(tblKhachHang.getSelectedRow());
        }catch (Exception e){
            return -1;
        }
    }


    /**
     * Refresh giao diện khi có cập nhật
     */
    public void refresh(boolean reloadData){
        if (reloadData){
            // load lai dữ liệu
            try {
                danhSachKhachHang.loadData();
            } catch (Exception e) {
                thongBaoLoi(e.getMessage());
            }

            // cập nhật table
            khachHangTableModel.setModel(danhSachKhachHang.getAll());
            tblKhachHang.setModel(khachHangTableModel);

            sorter.setModel(khachHangTableModel);

            tblKhachHang.revalidate();
            tblKhachHang.repaint();
            tblKhachHang.clearSelection();
        }

        // bật tắt chức năng sữa, xoá
        if (getCurrentSelected() != -1){
            btnSua.setEnabled(true);
            btnSua.setToolTipText("[Alt + S] Cập nhật thông tin khách hàng");

            btnXoa.setToolTipText("[Alt + X] Xoá khách hàng");
            btnXoa.setEnabled(true);
        }else{
            btnSua.setToolTipText("Vui lòng chọn khách hàng cần cập nhật thông tin");
            btnSua.setEnabled(false);

            btnXoa.setToolTipText("Vui lòng chọn khách hàng cần xoá");
            btnXoa.setEnabled(false);
        }
    }


    /**
     * Tìm kiếm record theo tên khách hàng
     * Dùng đối tượng filter table
     * @param filter_text
     */
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


    /**
     * Dialog thông báo
     * @param message
     */
    private void thongBao(String message){
        thongBaoDialog = new ThongBaoDialog(
                new JFrame(),
                "Thông báo",
                message,
                ThongBaoDialog.OK_OPTION
        );
    }


    /**
     * Dialog thông báo lỗi
     * @param message
     */
    private void thongBaoLoi(String message){
        thongBaoDialog = new ThongBaoDialog(
                new JFrame(),
                "Lỗi",
                message,
                ThongBaoDialog.OK_OPTION
        );
    }


    /**
     * Sự kiện button thêm
     * @return
     */
    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // dialog nhập khách hàng mới
                KhachHangDialog khachHangDialog = null;
                try {
                    khachHangDialog = new KhachHangDialog(new JFrame(), null);
                } catch (Exception ex) {
                    thongBaoLoi(ex.getMessage());
                }

                // lấy khách hàng vừa nhập
                KhachHang khachHang = khachHangDialog.getKhachHang();

                // nếu người dùng không muốn thêm khách hàng
                if (khachHang == null)
                    return;

                // thêm vào DB
                try{
                    danhSachKhachHang.them(khachHang);
                    refresh(true);
                }catch (Exception e1){
                    thongBaoLoi(e1.getMessage());
                }
            }
        };
    }


    /**
     * Sự kiện button sửa
     * @return
     */
    private ActionListener btnSua_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nếu người dùng chưa chọn dòng nào thì thông báo
                if (getCurrentSelected() == -1){
                    thongBao("Vui lòng chọn khách hàng cần sửa");
                    return;
                }

                // hiện dialog sửa thông tin khách hàng
                KhachHangDialog khachHangDialog = null;
                try {
                    khachHangDialog = new KhachHangDialog(new JFrame(),
                            danhSachKhachHang.getAll().get(getCurrentSelected()));
                } catch (Exception ex) {
                    thongBaoLoi(ex.getMessage());
                }

                // lấy thông tin khách hàng
                KhachHang khachHang = khachHangDialog.getKhachHang();

                // kiểm tra khách hàng có null không
                if (khachHang == null)
                    return;

                // lưu thông tin thay đổi vào DB
                try{
                    danhSachKhachHang.sua(khachHang);
                    refresh(true);
                }catch (Exception e1){
                    thongBaoLoi(e1.getMessage());
                }
            }
        };
    }


    /**
     * Sự kiện button xoá
     * @return
     */
    private ActionListener btnXoa_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nếu người dùng chưa chọn dòn gnào
                if (getCurrentSelected() == -1){
                    thongBao("Vui lòng chọn khách hàng cần xoá");
                    return;
                }

                // lấy thông tin khách hàng ở dòng vừa chọn
                String maKhachHang = tblKhachHang.getModel().getValueAt(getCurrentSelected(), 0).toString();
                String tenKhachHang = tblKhachHang.getModel().getValueAt(getCurrentSelected(), 1).toString();

                // dialog cảnh báo người dùng
                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Bạn có muốn xoá khách hàng này không?\nTên khách hàng: %s", tenKhachHang),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                // nếu người dùng đồng ý
                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    try{
                        danhSachKhachHang.xoa(maKhachHang);
                        tblKhachHang.clearSelection();
                        refresh(true);
                    }catch (Exception e1){
                        thongBaoLoi(e1.getMessage());
                    }
                }
            }
        };
    }


    /**
     * Sự kiện button tìm kiếm
     * @return
     */
    private ActionListener btnTimKiem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable(txtTimKiem.getText().trim());
            }
        };
    }


    /**
     * Sự kiện khi nhập text tìm kiếm
     * D2ung để tìm kiếm realtime
     * @return
     */
    private DocumentListener txtTimKiem_DocumentListerner(){
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


    /**
     * Sự kiện table
     * @return
     */
    private MouseListener tblKhachHang_MouseListener(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refresh(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }


    /**
     * Constructor
     */
    public QuanLyKhachHangTabbed(){
        // kết nối DB
        try {
            danhSachKhachHang = new DanhSachKhachHang();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        // Tạo GUI
        prepareUI();
    }
}
