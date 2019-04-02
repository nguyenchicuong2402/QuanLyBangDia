package org.buffalocoder.quanlybangdia.views;

import org.buffalocoder.quanlybangdia.XML.QuanLyXML;
import org.buffalocoder.quanlybangdia.dao.TaiKhoanDAO;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;

import javax.swing.*;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class DangNhap extends JFrame {

    public static TaiKhoan taiKhoan;
    private QuanLyXML ql = new QuanLyXML();
    private TaiKhoanDAO taiKhoanDAO;
    private ThongBaoDialog thongBaoDialog;

    private JPanel mainPanel, headerPanel, contentPanel, bottomPanel;
    private JLabel lblTieuDe, lblTenNguoiDung, lblMatKhau, lblBanQuyen, lblLoi;
    private JTextField txtTenNguoiDung;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap, btnThoat;
    private JCheckBox cbGhiNho;
    private Component rootComponent = this;

    private void prepareUI(){
        mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        //========== HEADER PANEL ==========//
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(MaterialDesign.COLOR_PRIMARY);
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 180));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        lblTieuDe = new JLabel("Đăng nhập");
        MaterialDesign.materialLabel(lblTieuDe);
        lblTieuDe.setFont(MaterialDesign.FONT_TITLE_1);
        lblTieuDe.setForeground(Color.WHITE);
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(lblTieuDe, BorderLayout.CENTER);


        //========== CONTENT PANEL ==========//
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        MaterialDesign.materialPanel(contentPanel);
        contentPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        Box box = Box.createVerticalBox();
        box.setBackground(MaterialDesign.COLOR_BACKGROUND);

        contentPanel.add(Box.createHorizontalStrut(100));
        contentPanel.add(box);
        contentPanel.add(Box.createHorizontalStrut(100));

        Box bx1 = Box.createVerticalBox();
        bx1.setSize(100, 30);
        box.add(Box.createVerticalStrut(50));
        box.add(bx1);
        box.add(Box.createVerticalStrut(20));

        Box bx2 = Box.createVerticalBox();
        box.add(bx2);
        box.add(Box.createVerticalStrut(20));

        Box bx3 = Box.createHorizontalBox();
        box.add(bx3);
        box.add(Box.createVerticalStrut(10));

        Box bx4 = Box.createHorizontalBox();
        box.add(bx4);
        box.add(Box.createVerticalStrut(10));

        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        MaterialDesign.materialPanel(panel);
        Box bx5 = Box.createVerticalBox();
        bx5.add(panel);
        box.add(bx5);
        box.add(Box.createVerticalStrut(20));

        lblTenNguoiDung = new JLabel("Tên người dùng");
        MaterialDesign.materialLabel(lblTenNguoiDung);
        bx1.add(lblTenNguoiDung);
        bx1.add(Box.createVerticalStrut(10));

        txtTenNguoiDung = new JTextField();
        MaterialDesign.materialTextField(txtTenNguoiDung);
        txtTenNguoiDung.addKeyListener(txtTenNguoiDung_Changed());
        bx1.add(txtTenNguoiDung);

        lblMatKhau = new JLabel("Mật khẩu");
        MaterialDesign.materialLabel(lblMatKhau);
        bx2.add(lblMatKhau);
        bx2.add(Box.createVerticalStrut(10));

        txtMatKhau = new JPasswordField();
        txtMatKhau.addKeyListener(txtMatKhau_Changed());
        MaterialDesign.materialTextField(txtMatKhau);
        bx2.add(txtMatKhau);

        cbGhiNho = new JCheckBox("Ghi nhớ đăng nhập");
        MaterialDesign.materialCheckBox(cbGhiNho);
        bx3.add(cbGhiNho);
        bx3.add(Box.createHorizontalGlue());

        lblLoi = new JLabel(" ");
        MaterialDesign.materialLabel(lblLoi);
        lblLoi.setForeground(MaterialDesign.COLOR_ERROR);
        lblLoi.setHorizontalAlignment(SwingConstants.CENTER);
        bx4.add(lblLoi);

        btnThoat = new JButton("Thoát");
        btnThoat.setPreferredSize(new Dimension(100, 50));
        btnThoat.addActionListener(btnThoat_Click());
        MaterialDesign.materialButton(btnThoat);
        btnThoat.setBackground(MaterialDesign.COLOR_ERROR);
        panel.add(btnThoat);

        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.addActionListener(btnDangNhap_Click());
        MaterialDesign.materialButton(btnDangNhap);
        btnDangNhap.setPreferredSize(btnThoat.getPreferredSize());
        panel.add(btnDangNhap);


        //========== BOTTOM PANEL ==========//
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(MaterialDesign.COLOR_PRIMARY);
        bottomPanel.setPreferredSize(new Dimension(100, 40));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        lblBanQuyen = new JLabel("Copyright Buffalo Coder");
        MaterialDesign.materialLabel(lblBanQuyen);
        lblBanQuyen.setForeground(Color.WHITE);
        lblBanQuyen.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(lblBanQuyen, BorderLayout.CENTER);

        // set button mặc định khi nhấn enter
        JRootPane rootPane = SwingUtilities.getRootPane(rootComponent);
        rootPane.setDefaultButton(btnDangNhap);

        // Khôi phục tài khoản đã ghi nhớ lần trước
        khoiPhucTaiKhoan();
    }

    private void khoiPhucTaiKhoan(){
        if(ql.getRemember() != 0)
        {
            cbGhiNho.setSelected(true);
            txtTenNguoiDung.setText(ql.getTextContentTK());
            txtMatKhau.setText(ql.getTextContentPW());
        }
    }

    private void errorInput(JTextField txt, String message){
        txt.setBorder(MaterialDesign.BORDER_ERROR);
        txt.requestFocus();
        txt.selectAll();

        lblLoi.setText(message);
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

    private void ghiNhoTaiKhoan(){
        if (cbGhiNho.isSelected()) {
            try {
                ql.setRemember(1);
                ql.ghiNhoAccount(txtTenNguoiDung.getText(), txtMatKhau.getText());
            } catch (TransformerException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else {
            try {
                ql.setRemember(0);
                ql.xoaXML();
            } catch (TransformerException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Thông báo",
                        "Bạn có muốn thoát chương trình không ?",
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    System.exit(0);
                }
            }
        };
    }

    private ActionListener btnDangNhap_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // lấy tên đăng nhập và mật khẩu
                String tenTaiKhoan = txtTenNguoiDung.getText().trim();
                String matKhau = txtMatKhau.getText().trim();

                // kiểm tra dữ liệu
                if (tenTaiKhoan.isEmpty()){
                    errorInput(txtTenNguoiDung, "Vui lòng nhập tên người dùng");
                    return;
                }else if (matKhau.isEmpty()){
                    errorInput(txtMatKhau, "Vui lòng nhập mật khẩu");
                    return;
                }

                // lấy tài khoản từ db lên
                try{
                    taiKhoan = taiKhoanDAO.getTaiKhoan(tenTaiKhoan);
                }catch (Exception e1){
                    thongBaoLoi(e1.getMessage());
                    return;
                }

                // kiểm tra username, password có đúng không
                // nếu đúng thì vào form quản lý
                // nếu sai thì thông báo
                // ghi nhớ tài khoản (nếu có check)
                if (tenTaiKhoan.equals(taiKhoan.getTenTaiKhoan()) && matKhau.equals(taiKhoan.getMatKhau())){
                    ghiNhoTaiKhoan();
                    rootComponent.setVisible(false);
                    new MainForm();
                }else{
                    thongBao("Sai tên đăng nhập hoặc mật khẩu");
                    txtTenNguoiDung.requestFocus();
                    txtTenNguoiDung.selectAll();
                }
            }
        };
    }

    private KeyListener txtTenNguoiDung_Changed(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            /**
             * Khi người dùng nhấn phím thì bỏ lỗi
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (!lblLoi.getText().trim().isEmpty()){
                    MaterialDesign.materialTextField(txtTenNguoiDung);
                    lblLoi.setText("  ");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private KeyListener txtMatKhau_Changed(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            /**
             * Khi người dùng nhấn phím thì bỏ lỗi
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if (!lblLoi.getText().trim().isEmpty()){
                    MaterialDesign.materialTextField(txtMatKhau);
                    lblLoi.setText("  ");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    public DangNhap(){
        try{
            taiKhoanDAO = TaiKhoanDAO.getInstance();
        } catch (Exception e) {
            thongBaoLoi(e.getMessage());
            System.exit(1);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setUndecorated(true);
                setSize(600, 600);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);
                setLocationRelativeTo(null);
                setTitle("Đăng nhập");
                setVisible(true);
            }
        });
    }
}
