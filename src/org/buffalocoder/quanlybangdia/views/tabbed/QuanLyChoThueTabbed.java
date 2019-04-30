package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachChoThue;
import org.buffalocoder.quanlybangdia.models.DanhSachKhachHang;
import org.buffalocoder.quanlybangdia.models.HoaDon;
import org.buffalocoder.quanlybangdia.models.tablemodel.ChoThueTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.dialog.ChoThueDialog;
import org.buffalocoder.quanlybangdia.views.dialog.ThanhToanDialog;
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

    private int indexFilter = 0;
    private JTable tblChoThue;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem, btnThanhToan;
    private JTextField txtTimKiem;
    private JComboBox<String> cbFilterTimKiem;
    private TableRowSorter<TableModel> sorter;
    private ChoThueTableModel choThueTableModel;
    private JScrollPane scrollPane;
    private JComboBox<String> cbFilter;


    /**
     * Tạo GUI
     */
    private void prepareUI() {
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

        btnXoa = new JButton("Xoá", MaterialDesign.ICON_XOA);
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        btnXoa.addActionListener(btnXoa_Click());
        btnXoa.setToolTipText("Vui lòng chọn hoá đơn cần xoá");
        btnXoa.setEnabled(false);
        btnXoa.setMnemonic(KeyEvent.VK_X);
        MaterialDesign.materialButton(btnXoa);
        btnXoa.setBackground(MaterialDesign.COLOR_ERROR);
        funcPanel.add(btnXoa);

        btnThanhToan = new JButton("Thanh toán", MaterialDesign.ICON_THANHTOAN);
        btnThanhToan.setPreferredSize(new Dimension(140, 40));
        btnThanhToan.addActionListener(btnThanhToan_Click());
        btnThanhToan.setToolTipText("Vui lòng chọn hoá đơn cần thanh toán");
        btnThanhToan.setEnabled(false);
        MaterialDesign.materialButton(btnThanhToan);
        funcPanel.add(btnThanhToan);

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

        cbFilterTimKiem = new JComboBox<>(new String[]{
                "Mã hoá đơn",
                "Tên khách hàng",
                "Tên băng đĩa"
        });
        MaterialDesign.materialComboBox(cbFilterTimKiem);
        cbFilterTimKiem.setPreferredSize(new Dimension(150, 40));
        cbFilterTimKiem.addActionListener(cbFilterTimKiem_Changed());
        searchPanel.add(cbFilterTimKiem);

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

        refresh(true);
    }


    /**
     * Kiểm tra tình trạng thuê trước khi thêm/sửa
     *
     * @param hoaDon
     * @param soLuongCu
     * @return
     */
    private boolean kiemTraTinhTrangThue(HoaDon hoaDon, int soLuongCu) {
        // kiểm tra số lượng đặt có đủ không
        if ((hoaDon.getBangDia().getSoLuongTon() + soLuongCu) < hoaDon.getSoLuong()) {
            thongBao("Không đủ số lượng băng đĩa");
            return false;
        }

        // kiểm tra băng đĩa
        if (!hoaDon.getBangDia().isTinhTrang()) {
            thongBao("Băng đĩa không còn sử dụng được");
            return false;
        }

        // kiểm tra người dùng có mượn quá hạn không
        for (HoaDon hoaDon1 : danhSachChoThue.getAll()) {
            if (hoaDon1.getKhachHang().getMaKH().equals(
                    hoaDon.getKhachHang().getMaKH())) {
                if (!hoaDon1.isThueQuaHan()) {
                    thongBao("Khách hàng thuê băng đĩa quá hạn\nVui lòng nhắc khách hàng trả băng đĩa trước khi thuê");
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * Dialog thông báo
     *
     * @param message
     */
    private void thongBao(String message) {
        thongBaoDialog = new ThongBaoDialog(
                new JFrame(),
                "Thông báo",
                message,
                ThongBaoDialog.OK_OPTION
        );
    }


    /**
     * Dialog thông báo lỗi
     *
     * @param message
     */
    private void thongBaoLoi(String message) {
        thongBaoDialog = new ThongBaoDialog(
                new JFrame(),
                "Lỗi",
                message,
                ThongBaoDialog.OK_OPTION
        );
    }


    /**
     * Refresh giao diện khi có cập nhật dữ liệu
     */
    public void refresh(boolean reloadData) {
        int oldSelected = getCurrentSelected();

        if (reloadData) {
            // load lại dữ liệu từ DB
            try {
                danhSachBangDia.loadData();
                danhSachKhachHang.loadData();
                danhSachChoThue.loadData();
            } catch (Exception e) {
                thongBaoLoi(e.getMessage());
            }

            // load lại table
            choThueTableModel.setModel(danhSachChoThue.getAll());
            tblChoThue.setModel(choThueTableModel);

            sorter.setModel(choThueTableModel);

            tblChoThue.revalidate();
            tblChoThue.repaint();
            setCurrentSelected(oldSelected);
        }

        /**
         * Bật tắt nút thêm hoá đơn
         * Khi chưa có người dùng và băng đĩa thì k được thêm hoá đơn
         */
        if (danhSachKhachHang.getAll().size() > 0 && danhSachBangDia.getAll().size() > 0) {
            btnThem.setEnabled(true);
            btnThem.setToolTipText("[Alt + T] Thêm hoá đơn");
        } else if (danhSachKhachHang.getAll().size() <= 0) {
            btnThem.setEnabled(false);
            btnThem.setToolTipText("Vui lòng thêm khách hàng");
        } else if (danhSachBangDia.getAll().size() <= 0) {
            btnThem.setEnabled(false);
            btnThem.setToolTipText("Vui lòng thêm băng đĩa");
        }


        /**
         * Bật tắt nút xoá, sửa, thanh toán
         * Khi người dùng chưa chọn hoá đơn nào thì disable nút xoá, sửa, thanh toán
         * Nếu hoá đơn đã thanh toán thì disable nút thanh toán
         */
        int rowSelected = -1;
        try {
            rowSelected = tblChoThue.convertRowIndexToModel(tblChoThue.getSelectedRow());
        } catch (Exception e) {
        }

        if (rowSelected == -1) {
            btnSua.setToolTipText("Vui lòng chọn hoá đơn cần cập nhật thông tin");
            btnSua.setEnabled(false);

            btnXoa.setToolTipText("Vui lòng chọn hoá đơn cần xoá");
            btnXoa.setEnabled(false);

            btnThanhToan.setToolTipText("Vui lòng chọn hoá đơn cần thanh toán");
            btnThanhToan.setEnabled(false);
        } else {
            btnSua.setEnabled(true);
            btnSua.setToolTipText("[Alt + S] Cập nhật thông tin hoá đơn");

            btnXoa.setToolTipText("[Alt + X] Xoá hoá đơn");
            btnXoa.setEnabled(true);

            if (String.valueOf(tblChoThue.getModel().getValueAt(rowSelected, 7)).equalsIgnoreCase("Đang thuê")) {
                btnThanhToan.setToolTipText("Thanh toán hoá đơn");
                btnThanhToan.setEnabled(true);
            } else {
                btnThanhToan.setToolTipText("Hoá đơn đã được thanh toán");
                btnThanhToan.setEnabled(false);

                btnSua.setToolTipText("Không thể cập nhật hoá đơn đã thanh toán");
                btnSua.setEnabled(false);

                btnXoa.setToolTipText("Không thể xoá hoá đơn đã thanh toán");
                btnXoa.setEnabled(false);
            }
        }
    }


    /**
     * Tìm kiếm
     * Sử dụng dối tượng filter table
     *
     * @param filter_text
     */
    private void filterTable(String filter_text) {
        if (filter_text.isEmpty())
            sorter.setRowFilter(null);
        else {
            try {
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    @Override
                    public boolean include(Entry<?, ?> entry) {
                        return (entry.getStringValue(indexFilter).contains(filter_text));
                    }
                };
                sorter.setRowFilter(filter);
            } catch (NumberFormatException e) {
                txtTimKiem.selectAll();
            }
        }
    }


    /**
     * Lấy vị trí đang chọn trong table
     *
     * @return
     */
    private int getCurrentSelected() {
        try {
            return tblChoThue.convertRowIndexToModel(tblChoThue.getSelectedRow());
        } catch (Exception e) {
            return -1;
        }
    }


    /**
     * Set row được chọn
     *
     * @param oldSelected
     */
    private void setCurrentSelected(int oldSelected) {
        if (oldSelected != -1 && oldSelected <= tblChoThue.getModel().getRowCount()) {
            tblChoThue.setRowSelectionInterval(oldSelected, oldSelected);
        } else if (oldSelected != -1 && oldSelected > tblChoThue.getModel().getRowCount()) {
            tblChoThue.setRowSelectionInterval(oldSelected - 1, oldSelected - 1);
        } else if (oldSelected == -1 && tblChoThue.getModel().getRowCount() > 0) {
            tblChoThue.setRowSelectionInterval(0, 0);
        } else tblChoThue.clearSelection();
    }


    /**
     * Sự kiện button thêm
     *
     * @return
     */
    private ActionListener btnThem_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // hiện dialog thêm hoá đơn
                ChoThueDialog choThueDialog = null;
                try {
                    choThueDialog = new ChoThueDialog(new JFrame(), null);
                } catch (Exception ex) {
                    thongBaoLoi(ex.getMessage());
                }

                // lấy hoá đơn nhập trong dialog
                HoaDon hoaDon = choThueDialog.getHoaDon();

                // kiểm tra tình trạng thuê và thêm vào DB
                try {
                    if (hoaDon != null && kiemTraTinhTrangThue(hoaDon, 0)) {
                        danhSachChoThue.them(hoaDon);
                        refresh(true);
                    }
                } catch (Exception e1) {
                    thongBaoLoi(e1.getMessage());
                }
            }
        };
    }


    /**
     * Sự kiện button sửa
     *
     * @return
     */
    private ActionListener btnSua_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nếu người dùng chưa chọn dòng nào thì thông báo
                if (getCurrentSelected() == -1) {
                    thongBao("Vui lòng chọn hoá đơn cần sửa");
                    return;
                }

                // lấy thông tin hoá đơn
                HoaDon hoaDon = danhSachChoThue.getAll().get(getCurrentSelected());
                int soLuongCu = hoaDon.getSoLuong();

                // hiện dialog sửa và thông tin sản phẩm
                ChoThueDialog choThueDialog = null;
                try {
                    choThueDialog = new ChoThueDialog(new JFrame(), hoaDon);
                } catch (Exception ex) {
                    thongBaoLoi(ex.getMessage());
                }

                // lấy thông tin hoá đơn đã sửa
                hoaDon = choThueDialog.getHoaDon();

                // kiểm tra hoá đơn có rỗng không và tình trạng thuê
                try {
                    if (hoaDon != null && kiemTraTinhTrangThue(hoaDon, soLuongCu)) {
                        danhSachChoThue.sua(hoaDon);
                        refresh(true);
                    }
                } catch (Exception e1) {
                    thongBaoLoi(e1.getMessage());
                }
            }
        };
    }


    /**
     * Sự kiện button xoá
     *
     * @return
     */
    private ActionListener btnXoa_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nếu người dùng chưa chọn dòng nào thì thông báo
                // nếu hoá đơn đó đã thanh toán thì không cho xoá
                if (getCurrentSelected() == -1) {
                    thongBao("Vui lòng chọn hoá đơn cần xoá");
                    return;
                } else if (String.valueOf(tblChoThue.getModel().getValueAt(getCurrentSelected(), 7)).equalsIgnoreCase("Đã thanh toán")) {
                    thongBao("Không thể xoá hoá đơn đã thanh toán");
                    return;
                }

                // lấy thông tin hoá đơn cần xoá
                String maHoaDon = choThueTableModel.getValueAt(getCurrentSelected(), 0).toString();
                String tenKhachHang = choThueTableModel.getValueAt(getCurrentSelected(), 1).toString();
                String tenBangDia = choThueTableModel.getValueAt(getCurrentSelected(), 2).toString();

                // hiện dialog xác nhận
                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Bạn có muốn xoá hoá đơn này không?\nTên khách hàng: %s\nTên băng đĩa: %s", tenKhachHang, tenBangDia),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                // nếu người dùng đồng ý
                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION) {
                    try {
                        danhSachChoThue.xoa(maHoaDon);
                        tblChoThue.clearSelection();
                        refresh(true);
                    } catch (Exception e1) {
                        thongBaoLoi(e1.getMessage());
                    }
                }
            }
        };
    }


    /**
     * Sự kiện button thanh toán
     *
     * @return
     */
    private ActionListener btnThanhToan_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // nếu người dùng chưa chọn dòng nào thì thông báo
                if (getCurrentSelected() == -1) {
                    thongBao("Vui lòng chọn hoá đơn cần thanh toán");
                    return;
                }

                // lấy thông tin hoá đơn cần thanh toán
                String maHoaDon = choThueTableModel.getValueAt(getCurrentSelected(), 0).toString();
                String tenKhachHang = choThueTableModel.getValueAt(getCurrentSelected(), 1).toString();
                String tenBangDia = choThueTableModel.getValueAt(getCurrentSelected(), 2).toString();
                int soLuong = Integer.parseInt(choThueTableModel.getValueAt(getCurrentSelected(), 3).toString());

                ThanhToanDialog thanhToanDialog = new ThanhToanDialog(
                        new JFrame(),
                        tenKhachHang,
                        tenBangDia,
                        soLuong
                );

                if (thanhToanDialog.getKetQua() == 0) {
                    try {
                        danhSachChoThue.thanhToanHoaDon(maHoaDon);
                        refresh(true);
                    } catch (Exception e1) {
                        thongBaoLoi(e1.getMessage());
                    }
                } else if (thanhToanDialog.getKetQua() > 0) {
                    try {
                        HoaDon hoaDon = danhSachChoThue.getAll().get(danhSachChoThue.tim(maHoaDon));
                        hoaDon.setSoLuong(thanhToanDialog.getKetQua());

                        danhSachChoThue.sua(hoaDon);
                        refresh(true);
                    } catch (Exception e1) {
                        thongBaoLoi(e1.getMessage());
                    }
                }
            }
        };
    }


    /**
     * Sự kiện khi chọn tìm kiếm theo gì
     *
     * @return
     */
    private ActionListener cbFilterTimKiem_Changed() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cbFilterTimKiem.getSelectedIndex()) {
                    case 0:
                        indexFilter = 0;
                        break;
                    case 1:
                        indexFilter = 1;
                        break;
                    case 2:
                        indexFilter = 2;
                        break;
                }

                filterTable(txtTimKiem.getText().trim());
            }
        };
    }


    /**
     * Sự kiện ComboBox filter table theo Tình trạng hoá đơn
     *
     * @return
     */
    private ActionListener cbFilter_Selected() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filter_text = String.valueOf(cbFilter.getSelectedItem());

                /**
                 * Nếu người dùng chọn tất cả thì hiện tất cả hoá đơn trong bảng
                 * Nếu người dùng chọn Đang thuê thì chỉ hiện hoá đơn đang thuê
                 * Nếu người dùng chọn Đã thanh toán thì chỉ hiện hoá đơn đã thanh toán
                 */
                if (filter_text.equalsIgnoreCase("Tất cả"))
                    sorter.setRowFilter(null);
                else {
                    try {
                        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                            @Override
                            public boolean include(Entry<?, ?> entry) {
                                return (entry.getStringValue(7).contains(filter_text));
                            }
                        };
                        sorter.setRowFilter(filter);
                    } catch (NumberFormatException e1) {
                        cbFilter.setSelectedIndex(0);
                    }
                }
            }
        };
    }


    /**
     * Sự kiện khi nhập text tìm kiếm
     * Tìm kiếm realtime
     *
     * @return
     */
    private DocumentListener txtTimKiem_DocumentListener() {
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
     * Sự kiện table cho thuê
     *
     * @return
     */
    private MouseListener tblChoThue_MouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refresh(false);
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


    public QuanLyChoThueTabbed() {
        try {
            danhSachChoThue = new DanhSachChoThue();
            danhSachKhachHang = new DanhSachKhachHang();
            danhSachBangDia = new DanhSachBangDia();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
        }

        prepareUI();
    }
}
