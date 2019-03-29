package org.buffalocoder.quanlybangdia.views.dialog;

import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.utils.Colors;
import org.buffalocoder.quanlybangdia.utils.Fonts;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class NhanVienDialog extends JDialog {
    private String tieuDe;
    private NhanVien nhanVien;
    private boolean isEdit;

    private JPanel mainPanel, contentPanel, headerPanel, bottomPanel;
    private JLabel lblTieuDe, lblMaNV, lblCMND, lblHoTen, lblGioiTinh, lblSoDienThoai,
            lblDiaChi, lblNgaySinh, lblMoTa;
    private JButton btnThoat, btnLuu;
    private JTextField txtMaNV, txtCMND, txtHoTen, txtSoDienThoai, txtDiaChi, txtNgaySinh, txtMoTa;
    private JComboBox<String> cbGioiTinh;

    private void prepareDialog(){
        mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);

        // HEADER PANEL
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 60));
        headerPanel.setBackground(Colors.PRIMARY);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        lblTieuDe = new JLabel(tieuDe);
        MaterialDesign.materialLabel(lblTieuDe);
        lblTieuDe.setForeground(Color.WHITE);
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(Fonts.TITLE_2);
        headerPanel.add(lblTieuDe);

        // CONTENT PANEL
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        Box box = Box.createVerticalBox();
        contentPanel.add(box);
        box.add(Box.createVerticalStrut(20));

        Box bx1 = Box.createHorizontalBox();
        box.add(bx1);
        box.add(Box.createVerticalStrut(10));

        Box bx2 = Box.createHorizontalBox();
        box.add(bx2);
        box.add(Box.createVerticalStrut(10));

        Box bx3 = Box.createHorizontalBox();
        box.add(bx3);
        box.add(Box.createVerticalStrut(10));

        Box bx4 = Box.createHorizontalBox();
        box.add(bx4);
        box.add(Box.createVerticalStrut(10));

        Box bx5 = Box.createHorizontalBox();
        box.add(bx5);
        box.add(Box.createVerticalStrut(10));

        Box bx6 = Box.createHorizontalBox();
        box.add(bx6);
        box.add(Box.createVerticalStrut(10));

        Box bx7 = Box.createHorizontalBox();
        box.add(bx7);
        box.add(Box.createVerticalStrut(10));

        Box bx8 = Box.createHorizontalBox();
        box.add(bx8);
        box.add(Box.createVerticalStrut(20));

        lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaNV);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaNV);

        txtMaNV = new JTextField("NV00011");
        MaterialDesign.materialTextField(txtMaNV);
        txtMaNV.setEditable(false);
        if (isEdit) txtMaNV.setText(nhanVien.getMaNhanVien());
        bx1.add(txtMaNV);
        bx1.add(Box.createHorizontalStrut(20));

        lblHoTen = new JLabel("Họ và tên");
        lblHoTen.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblHoTen);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblHoTen);

        txtHoTen = new JTextField();
        MaterialDesign.materialTextField(txtHoTen);
        if (isEdit) txtHoTen.setText(nhanVien.getHoTen());
        bx2.add(txtHoTen);
        bx2.add(Box.createHorizontalStrut(20));

        lblCMND = new JLabel("Số CMND");
        lblCMND.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblCMND);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblCMND);

        txtCMND = new JTextField();
        MaterialDesign.materialTextField(txtCMND);
        if (isEdit) txtCMND.setText(nhanVien.getcMND());
        bx3.add(txtCMND);
        bx3.add(Box.createHorizontalStrut(20));

        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblGioiTinh);

        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        if (isEdit) cbGioiTinh.setSelectedItem(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
        bx4.add(cbGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));

        lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblNgaySinh);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblNgaySinh);

        txtNgaySinh = new JTextField();
        MaterialDesign.materialTextField(txtNgaySinh);
        if (isEdit) txtNgaySinh.setText(Utils.DATE_FORMAT.format(nhanVien.getNgaySinh()));
        bx5.add(txtNgaySinh);
        bx5.add(Box.createHorizontalStrut(20));

        lblSoDienThoai = new JLabel("Số điện thoại");
        lblSoDienThoai.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblSoDienThoai);

        txtSoDienThoai = new JTextField();
        MaterialDesign.materialTextField(txtSoDienThoai);
        if (isEdit) txtSoDienThoai.setText(nhanVien.getSoDienThoai());
        bx6.add(txtSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));

        lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblDiaChi);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblDiaChi);

        txtDiaChi = new JTextField();
        MaterialDesign.materialTextField(txtDiaChi);
        if (isEdit) txtDiaChi.setText(nhanVien.getDiaChi());
        bx7.add(txtDiaChi);
        bx7.add(Box.createHorizontalStrut(20));

        lblMoTa = new JLabel("Mô tả");
        lblMoTa.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblMoTa);
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblMoTa);

        txtMoTa = new JTextField();
        MaterialDesign.materialTextField(txtMoTa);
        if (isEdit) txtMoTa.setText(nhanVien.getMoTa());
        bx7.add(txtMoTa);
        bx7.add(Box.createHorizontalStrut(20));

        // BOTTOM PANEL
        bottomPanel = new JPanel(new GridLayout(1, 2, 1, 10));
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        btnThoat = new JButton("Đóng");
        MaterialDesign.materialButton(btnThoat);
        btnThoat.setPreferredSize(new Dimension(250, 50));
        btnThoat.addActionListener(btnThoat_Click());
        bottomPanel.add(btnThoat);

        btnLuu = new JButton(isEdit ? "Lưu" : "Thêm");
        MaterialDesign.materialButton(btnLuu);
        btnLuu.addActionListener(btnLuu_Click());
        bottomPanel.add(btnLuu);
    }

    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NhanVienDialog.this.dispose();
            }
        };
    }

    private ActionListener btnLuu_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanVien = new NhanVien(
                        txtCMND.getText().trim(),
                        txtHoTen.getText().trim(),
                        cbGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Nam"),
                        txtSoDienThoai.getText().trim(),
                        txtDiaChi.getText().trim(),
                        Date.valueOf(txtNgaySinh.getText().trim()),
                        txtMaNV.getText().trim(),
                        txtMoTa.getText().trim()
                );

                dispose();
            }
        };
    }

    public NhanVienDialog(JFrame frame, NhanVien nhanVien){
        super(frame, true);
        this.nhanVien = nhanVien;

        if (nhanVien == null){
            tieuDe = "Thêm Nhân viên";
            isEdit = false;
        }else{
            tieuDe = "Sửa thông tin nhân viên";
            isEdit = true;
        }

        prepareDialog();

        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        setResizable(false);
        setSize(600, 550);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public NhanVien getNhanVien(){
        return nhanVien;
    }
}
