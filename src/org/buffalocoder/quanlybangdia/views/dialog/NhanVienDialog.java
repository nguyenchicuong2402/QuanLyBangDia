package org.buffalocoder.quanlybangdia.views.dialog;

import com.toedter.calendar.JDateChooser;
import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;

public class NhanVienDialog extends JDialog {
    private String tieuDe;
    private NhanVien nhanVien;
    private TaiKhoan taiKhoan;
    private boolean isEdit;

    private JPanel mainPanel, contentPanel, headerPanel, bottomPanel;
    private JLabel lblTieuDe, lblMaNV, lblCMND, lblHoTen, lblGioiTinh, lblSoDienThoai,
            lblDiaChi, lblNgaySinh, lblMoTa, lblTenTaiKhoan, lblMatKhau, lblNhapLaiMatKhau, lblLoaiTaiKhoan;
    private JButton btnThoat, btnLuu;
    private JTextField txtMaNV, txtCMND, txtHoTen, txtSoDienThoai, txtDiaChi, txtNgaySinh, txtMoTa,
                        txtTenTaiKhoan;
    private JPasswordField txtMatKhau, txtNhapLaiMatKhau;
    private JComboBox<String> cbGioiTinh, cbLoaiTaiKhoan;
    private JDateChooser dateChooser;

    private void prepareDialog(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Colors.PRIMARY, 2));
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
        box.add(Box.createVerticalStrut(10));

        Box bx9 = Box.createHorizontalBox();
        box.add(bx9);
        box.add(Box.createVerticalStrut(10));

        Box bx10 = Box.createHorizontalBox();
        box.add(bx10);
        box.add(Box.createVerticalStrut(10));

        Box bx11 = Box.createHorizontalBox();
        box.add(bx11);
        box.add(Box.createVerticalStrut(10));

        Box bx12= Box.createHorizontalBox();
        box.add(bx12);
        box.add(Box.createVerticalStrut(20));

        lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaNV);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaNV);

        txtMaNV = new JTextField("NV00015");
        MaterialDesign.materialTextField(txtMaNV);
        txtMaNV.setEditable(false);
        if (isEdit) txtMaNV.setText(nhanVien.getMaNhanVien());
        bx1.add(txtMaNV);
        bx1.add(Box.createHorizontalStrut(20));

        lblTenTaiKhoan = new JLabel("Tên tài khoản");
        lblTenTaiKhoan.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblTenTaiKhoan);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblTenTaiKhoan);

        txtTenTaiKhoan = new JTextField();
        MaterialDesign.materialTextField(txtTenTaiKhoan);
        if (isEdit) {
            txtTenTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
            txtTenTaiKhoan.setEditable(false);
        }
        bx2.add(txtTenTaiKhoan);
        bx2.add(Box.createHorizontalStrut(20));

        lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblMatKhau);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblMatKhau);

        txtMatKhau = new JPasswordField();
        MaterialDesign.materialTextField(txtMatKhau);
        bx3.add(txtMatKhau);
        bx3.add(Box.createHorizontalStrut(20));

        lblNhapLaiMatKhau = new JLabel("Nhập lại mật khẩu");
        lblNhapLaiMatKhau.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblNhapLaiMatKhau);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblNhapLaiMatKhau);

        txtNhapLaiMatKhau = new JPasswordField();
        MaterialDesign.materialTextField(txtNhapLaiMatKhau);
        bx4.add(txtNhapLaiMatKhau);
        bx4.add(Box.createHorizontalStrut(20));

        lblLoaiTaiKhoan = new JLabel("Loại tài khoản");
        lblLoaiTaiKhoan.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblLoaiTaiKhoan);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblLoaiTaiKhoan);

        cbLoaiTaiKhoan = new JComboBox<>(new String[]{"ADMIN", "NHÂN VIÊN"});
        if (isEdit) cbLoaiTaiKhoan.setSelectedItem(taiKhoan.getLoaiTaiKhoan() == 1 ? "ADMIN" : "NHÂN VIÊN");
        bx5.add(cbLoaiTaiKhoan);
        bx5.add(Box.createHorizontalStrut(20));

        lblHoTen = new JLabel("Họ và tên");
        lblHoTen.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblHoTen);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblHoTen);

        txtHoTen = new JTextField();
        MaterialDesign.materialTextField(txtHoTen);
        if (isEdit) txtHoTen.setText(nhanVien.getHoTen());
        bx6.add(txtHoTen);
        bx6.add(Box.createHorizontalStrut(20));

        lblCMND = new JLabel("Số CMND");
        lblCMND.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblCMND);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblCMND);

        txtCMND = new JTextField();
        MaterialDesign.materialTextField(txtCMND);
        if (isEdit) {
            txtCMND.setText(nhanVien.getcMND());
            txtCMND.setEditable(false);
        }
        bx7.add(txtCMND);
        bx7.add(Box.createHorizontalStrut(20));

        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblGioiTinh);
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblGioiTinh);

        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        if (isEdit) cbGioiTinh.setSelectedItem(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
        bx8.add(cbGioiTinh);
        bx8.add(Box.createHorizontalStrut(20));

        lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblNgaySinh);
        bx9.add(Box.createHorizontalStrut(20));
        bx9.add(lblNgaySinh);

        dateChooser = new JDateChooser(Formats.DATE_FORMAT.toPattern(), "##/##/####", '_');
        MaterialDesign.materialDateChooser(dateChooser);
        if (isEdit) dateChooser.setDate(nhanVien.getNgaySinh());
        else dateChooser.setDate(new java.util.Date());
        bx9.add(dateChooser);
        bx9.add(Box.createHorizontalStrut(20));

        lblSoDienThoai = new JLabel("Số điện thoại");
        lblSoDienThoai.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblSoDienThoai);
        bx10.add(Box.createHorizontalStrut(20));
        bx10.add(lblSoDienThoai);

        txtSoDienThoai = new JTextField();
        MaterialDesign.materialTextField(txtSoDienThoai);
        if (isEdit) txtSoDienThoai.setText(nhanVien.getSoDienThoai());
        bx10.add(txtSoDienThoai);
        bx10.add(Box.createHorizontalStrut(20));

        lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblDiaChi);
        bx11.add(Box.createHorizontalStrut(20));
        bx11.add(lblDiaChi);

        txtDiaChi = new JTextField();
        MaterialDesign.materialTextField(txtDiaChi);
        if (isEdit) txtDiaChi.setText(nhanVien.getDiaChi());
        bx11.add(txtDiaChi);
        bx11.add(Box.createHorizontalStrut(20));

        lblMoTa = new JLabel("Mô tả");
        lblMoTa.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblMoTa);
        bx12.add(Box.createHorizontalStrut(20));
        bx12.add(lblMoTa);

        txtMoTa = new JTextField();
        MaterialDesign.materialTextField(txtMoTa);
        if (isEdit) txtMoTa.setText(nhanVien.getMoTa());
        bx12.add(txtMoTa);
        bx12.add(Box.createHorizontalStrut(20));

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
                nhanVien = null;
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
                        cbGioiTinh.getSelectedItem().equals("Nam"),
                        txtSoDienThoai.getText().trim(),
                        txtDiaChi.getText().trim(),
                        Date.valueOf(Formats.DATE_FORMAT_SQL.format(dateChooser.getDate())),
                        txtMaNV.getText().trim(),
                        txtMoTa.getText().trim()
                );

                taiKhoan = new TaiKhoan(
                        txtTenTaiKhoan.getText().trim(),
                        txtMatKhau.getText().trim(),
                        cbLoaiTaiKhoan.getSelectedItem().equals("ADMIN") ? 1 : 0,
                        txtMaNV.getText().trim()
                );

                dispose();
            }
        };
    }

    public NhanVienDialog(JFrame frame, NhanVien nhanVien, TaiKhoan taiKhoan){
        super(frame, true);
        this.nhanVien = nhanVien;
        this.taiKhoan = taiKhoan;

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
        setSize(600, 750);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public NhanVien getNhanVien(){
        return nhanVien;
    }

    public TaiKhoan getTaiKhoan() { return taiKhoan; }
}
