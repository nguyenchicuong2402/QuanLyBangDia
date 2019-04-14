package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.dao.TaiKhoanDAO;
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
    private final Component rootComponent = this;

    private JTable tblNhanVien;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTimKiem;
    private NhanVienTableModel nhanVienTableModel;
    private JScrollPane scrollPane;
    private TableRowSorter<TableModel> sorter;


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


    /**
     * Filter table theo tên nhân viên
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
     * Cập nhật giao diện khi có sự thay đổi dữ liệu
     */
    public void refresh(boolean reloadData){
        int selected = tblNhanVien.convertRowIndexToModel(tblNhanVien.getSelectedRow());

        if (reloadData){
            // load dữ liệu từ DB
            try {
                danhSachNhanVien.loadData();
            } catch (Exception e) {
                thongBaoLoi(e.getMessage());
            }

            // load dữ liệu lên table
            nhanVienTableModel.setModel(danhSachNhanVien.getAll());
            tblNhanVien.setModel(nhanVienTableModel);

            sorter.setModel(nhanVienTableModel);

            tblNhanVien.revalidate();
            tblNhanVien.repaint();
            tblNhanVien.clearSelection();
        }

        // bật tắt nút xoá/sửa
        if (tblNhanVien.getSelectedRow() != -1){
            btnSua.setEnabled(true);
            btnSua.setToolTipText("[Alt + S] Cập nhật thông tin nhân viên");

            btnXoa.setToolTipText("[Alt + X] Xoá nhân viên");
            btnXoa.setEnabled(true);
        }else {
            btnSua.setToolTipText("Vui lòng chọn nhân viên cần cập nhật thông tin");
            btnSua.setEnabled(false);

            btnXoa.setToolTipText("Vui lòng chọn nhân viên cần xoá");
            btnXoa.setEnabled(false);
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
     * Sự kiện nút thêm
     * @return
     */
    private ActionListener btnThem_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // hiện dialog thêm
                NhanVienDialog nhanVienDialog = new NhanVienDialog(new JFrame(), null, null);

                // lấy thông tin nhân viên + tài khoản vừa nhập
                NhanVien nhanVien = nhanVienDialog.getNhanVien();
                TaiKhoan taiKhoan = nhanVienDialog.getTaiKhoan();

                // nếu người dùng không muốn thêm
                if (nhanVien == null && taiKhoan == null)
                    return;

                // thêm nhân viên và tài khoản vào DB
                try{
                    danhSachNhanVien.them(nhanVien);
                    taiKhoanDAO.themTaiKhoan(taiKhoan);
                    refresh(true);
                }catch (Exception e1){
                    thongBaoLoi(e1.getMessage());
                }
            }
        };
    }


    /**
     * Sự kiện sửa thông tin nhân viên
     * @return
     */
    private ActionListener btnSua_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lấy vị trí người dùng chọn
                int index = tblNhanVien.convertRowIndexToModel(tblNhanVien.getSelectedRow());

                // nếu người dùng chưa chọn dòng nào
                if (index == -1){
                    thongBao("Vui lòng chọn nhân viên cần sửa");
                    return;
                }

                // lấy thông tin nhân viên + tài khoản
                NhanVien nhanVien = danhSachNhanVien.getAll().get(index);
                TaiKhoan taiKhoan = null;
                try {
                    taiKhoan = taiKhoanDAO.getTaiKhoanByMaNhanVien(nhanVien.getMaNhanVien());
                } catch (Exception e1) {
                    thongBaoLoi(e1.getMessage());
                    return;
                }

                // hiện dialog chỉnh sửa
                NhanVienDialog nhanVienDialog = new NhanVienDialog(new JFrame(), nhanVien, taiKhoan);

                // lấy thông tin nhân viên và tài khoản sau khi chỉnh sửa
                nhanVien = nhanVienDialog.getNhanVien();
                taiKhoan = nhanVienDialog.getTaiKhoan();

                // nếu người dùng không muốn chỉnh sửa
                if (nhanVien == null)
                    return;

                // lưu dữ liệu vào DB
                try{
                    danhSachNhanVien.sua(nhanVien);

                    if (taiKhoan != null)
                        taiKhoanDAO.suaTaiKhoan(taiKhoan);

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
                // lấy vị trí người dùng chọn
                int index = tblNhanVien.convertRowIndexToModel(tblNhanVien.getSelectedRow());

                // nếu người dùng chưa chọn dòng nào thì thông báo
                if (index == -1){
                    thongBao("Vui lòng chọn nhân viên cần xoá");
                    return;
                }

                // lấy thông tin nhân viên ở dòng đã chọn
                String maNhanVien = tblNhanVien.getModel().getValueAt(index, 0).toString();
                String tenNhanVien = tblNhanVien.getModel().getValueAt(index, 1).toString();

                // nếu người dùng chọn tài khoản mặc định thì thông báo không cho xoá
                if (maNhanVien.equals("NV00001")){
                    thongBao("Không thể xoá admin mặc định");
                    return;
                }

                // dialog xác nhận xoá
                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Bạn có muốn xoá nhân viên này không?\nTên nhân viên: %s", tenNhanVien),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                // nếu người dùng đồng ý xoá
                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    try{
                        danhSachNhanVien.xoa(maNhanVien);
                        tblNhanVien.clearSelection();
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
     * Sự kiện table nhân viên
     * @return
     */
    private MouseListener tblNhanVien_MouseListener(){
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
    public QuanLyNhanVienTabbed(){
        // kết nối DB
        try{
            taiKhoanDAO = TaiKhoanDAO.getInstance();
            danhSachNhanVien = new DanhSachNhanVien();
        }catch (Exception e){
            thongBaoLoi(e.getMessage());
        }

        prepareUI();
    }
}
