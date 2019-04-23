package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.tablemodel.BangDiaTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.DangNhap;
import org.buffalocoder.quanlybangdia.views.dialog.BangDiaDialog;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;
import org.buffalocoder.quanlybangdia.views.dialog.XoaBangDiaDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private JButton btnThem, btnXoa, btnSua, btnXoaBangDiaHong, btnTimKiem;
    private JTextField txtTimKiem;
    private BangDiaTableModel bangDiaTableModel;
    private TableRowSorter<TableModel> sorter;
    private final Component rootComponent = this;
    private JScrollPane scrollPane;


    /**
     * Tạo GUI
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
        if (IS_ADMIN)
            topPanel.add(funcPanel, BorderLayout.WEST);

        btnThem = new JButton("Thêm", MaterialDesign.ICON_THEM);
        btnThem.setPreferredSize(new Dimension(100, 40));
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

        btnXoaBangDiaHong = new JButton("Xoá băng đĩa hỏng", MaterialDesign.ICON_XOA);
        btnXoaBangDiaHong.setPreferredSize(new Dimension(200, 40));
        btnXoaBangDiaHong.addActionListener(btnXoaBangDiaHong_Click());
        btnXoaBangDiaHong.setToolTipText("Xoá băng đĩa đã hư hỏng");
        MaterialDesign.materialButton(btnXoaBangDiaHong);
        btnXoaBangDiaHong.setBackground(MaterialDesign.COLOR_ERROR);
        funcPanel.add(btnXoaBangDiaHong);

        // tìm kiếm
        searchPanel = new JPanel();
        MaterialDesign.materialPanel(searchPanel);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(300, 40));
        txtTimKiem.getDocument().addDocumentListener(txtTimKiem_DocumentListener());
        MaterialDesign.materialTextField(txtTimKiem);
        searchPanel.add(txtTimKiem, BorderLayout.CENTER);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        btnTimKiem.addActionListener(btnTimKiem_Click());
        MaterialDesign.materialButton(btnTimKiem);
        searchPanel.add(btnTimKiem, BorderLayout.EAST);

        //table
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(10));
        this.add(box, BorderLayout.CENTER);

        bangDiaTableModel = new BangDiaTableModel(danhSachBangDia.getAll());

        sorter = new TableRowSorter<TableModel>(bangDiaTableModel);

        tblBangDia = new JTable(bangDiaTableModel);
        tblBangDia.setRowSorter(sorter = new TableRowSorter<>(tblBangDia.getModel()));
        MaterialDesign.materialTable(tblBangDia);
        tblBangDia.addMouseListener(tblBangDia_MouseListener());

        scrollPane = new JScrollPane(tblBangDia);
        MaterialDesign.materialScrollPane(scrollPane);
        box.add(scrollPane, BorderLayout.CENTER);

        refresh(true);
    }


    /**
     * Refresh lại tab khi có cập nhật dữ liệu
     */
    public void refresh(boolean reloadData){
        int oldSelected = getCurrentSelected();

        if (reloadData){
            // load lại dữ liệu từ DB
            try {
                danhSachBangDia.loadData();
            } catch (Exception e) {
                thongBaoLoi(e.getMessage());
            }

            // load lại table băng đĩa
            bangDiaTableModel.setModel(danhSachBangDia.getAll());
            tblBangDia.setModel(bangDiaTableModel);

            sorter.setModel(bangDiaTableModel);

            tblBangDia.revalidate();
            tblBangDia.repaint();
            setCurrentSelected(oldSelected);
        }

        // Nếu chưa có băng đĩa nào hư hỏng thì tắt nút xoá băng đĩa hỏng
        btnXoaBangDiaHong.setEnabled(false);
        for (BangDia bangDia : danhSachBangDia.getAll()){
            if (!bangDia.isTinhTrang()){
                btnXoaBangDiaHong.setEnabled(true);
                break;
            }
        }

        /**
         * Kiểm tra xem người dùng có chọn dòng nào không
         * Nếu người dùng có chọn thì bật nút xoá và sửa
         */
        if (tblBangDia.getSelectedRow() == -1){
            btnSua.setToolTipText("Vui lòng chọn băng đĩa cần cập nhật thông tin");
            btnSua.setEnabled(false);

            btnXoa.setToolTipText("Vui lòng chọn băng đĩa cần xoá");
            btnXoa.setEnabled(false);
        }else{
            btnSua.setEnabled(true);
            btnSua.setToolTipText("[Alt + S] Cập nhật thông tin băng đĩa");

            btnXoa.setEnabled(true);
            btnXoa.setToolTipText("[Alt + X] Xoá băng đĩa");
        }
    }


    /**
     * Tìm kiếm băng đĩa
     * Sử dụng đối tượng filter trong table
     * Tìm kiếm theo tên băng đĩa
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
     * Dialog Thông báo
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
     * Lấy vị trí đang chọn trong table
     * @return
     */
    private int getCurrentSelected(){
        try{
            return tblBangDia.convertRowIndexToModel(tblBangDia.getSelectedRow());
        }catch (Exception e){
            return -1;
        }
    }


    /**
     * Set row được chọn
     * @param oldSelected
     */
    private void setCurrentSelected(int oldSelected){
        if (oldSelected != -1 && oldSelected <= tblBangDia.getModel().getRowCount()){
            tblBangDia.setRowSelectionInterval(oldSelected, oldSelected);
        }else if (oldSelected != -1 && oldSelected > tblBangDia.getModel().getRowCount()){
            tblBangDia.setRowSelectionInterval(oldSelected - 1, oldSelected - 1);
        }else if (oldSelected == -1 && tblBangDia.getModel().getRowCount() > 0){
            tblBangDia.setRowSelectionInterval(0, 0);
        }else tblBangDia.clearSelection();
    }


    /**
     * Sự kiện button thêm
     * @return
     */
    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // hiện dialog nhập
                BangDiaDialog bangDiaDialog = null;
                try {
                    bangDiaDialog = new BangDiaDialog(new JFrame(), null);
                } catch (Exception ex) {
                    thongBaoLoi(ex.getMessage());
                }

                // lấy băng đĩa vừa nhập
                BangDia bangDia = bangDiaDialog.getBangDia();

                // nếu băng đĩa null thì không thêm vào
                if (bangDia == null)
                    return;

                // thêm băng đĩa vào danh sách và lưu vào DB
                try{
                    danhSachBangDia.them(bangDia);
                    refresh(true);
                }catch (Exception e1){
                    thongBaoLoi(e1.getMessage());
                }
            }
        };
    }


    /**
     * Sự kiện nút Sửa
     * @return
     */
    private ActionListener btnSua_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nếu người dùng chưa chọn thì thông báo
                if (getCurrentSelected() == -1){
                    thongBao("Vui lòng chọn băng đĩa cần sửa");
                    return;
                }

                // hiện dialog sửa băng đĩa
                BangDiaDialog bangDiaDialog = null;
                try {
                    bangDiaDialog = new BangDiaDialog(new JFrame(),
                            danhSachBangDia.getAll().get(getCurrentSelected()));
                } catch (Exception ex) {
                    thongBaoLoi(ex.getMessage());
                }

                // lấy thông tin băng đĩa vừa sửa
                BangDia bangDia = bangDiaDialog.getBangDia();

                // nếu người dùng không sửa
                if (bangDia == null)
                    return;

                // sửa băng đĩa trong danh sách và DB
                try{
                    danhSachBangDia.sua(bangDia);
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
                // nếu người dùng chưa chọn dòng nào thì thông báo
                if (getCurrentSelected() == -1){
                    thongBao("Vui lòng chọn băng đĩa cần xoá");
                    return;
                }

                // lấy thông tin băng đĩa đã chọn
                String maBangDia = tblBangDia.getModel().getValueAt(getCurrentSelected(), 0).toString();
                String tenBangDia = tblBangDia.getModel().getValueAt(getCurrentSelected(), 1).toString();
                int soLuong = Integer.parseInt(tblBangDia.getModel().getValueAt(getCurrentSelected(), 5).toString());

                XoaBangDiaDialog xoaBangDiaDialog = new XoaBangDiaDialog(new JFrame(), maBangDia, tenBangDia, soLuong);

                if (xoaBangDiaDialog.getKetQua() == 0){
                    try{
                        danhSachBangDia.xoa(maBangDia);
                        tblBangDia.clearSelection();
                        refresh(true);
                    }catch (Exception e1){
                        thongBaoLoi(e1.getMessage());
                    }
                }else if (xoaBangDiaDialog.getKetQua() > 0){
                    try{
                        BangDia bangDia = danhSachBangDia.getAll().get(danhSachBangDia.tim(maBangDia));
                        bangDia.setSoLuongTon(xoaBangDiaDialog.getKetQua());

                        danhSachBangDia.sua(bangDia);
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
     * Sự kiện button xoá băng đĩa hỏng
     * @return
     */
    private ActionListener btnXoaBangDiaHong_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // hiện dialog xác nhận xoá
                thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Thông báo",
                        "Thao tác này sẽ xoá tất cả băng đĩa hỏng trong hệ thống\nBạn có muốn tiếp tục không ?",
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                // nếu người dùng đồng ý
                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    danhSachBangDia.xoaBangDiaHong();
                    refresh(true);
                }
            }
        };
    }


    /**
     * Sự kiện khi chọn 1 dòng trên table
     * @return
     */
    private MouseListener tblBangDia_MouseListener(){
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
     * Sự kiện khi nhập text vào ô tìm kiếm
     * Dùng để tìm kiếm realtime
     * @return
     */
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


    /**
     * Constructor
     */
    public QuanLyBangDiaTabbed(){
        // kết nối db
        try {
            danhSachBangDia = new DanhSachBangDia();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        // Tạo GUI
        prepareUI();
    }
}
