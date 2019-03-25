package org.buffalocoder.quanlybangdia.views;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DangNhap extends JFrame {
    private JPanel mainPanel, headerPanel, contentPanel, bottomPanel;
    private JLabel lblTieuDe, lblTenNguoiDung, lblMatKhau, lblBanQuyen, lblLoi;
    private JTextField txtTenNguoiDung;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap, btnThoat;
    private Component rootComponent = this;

    private void prepareUI(){
        mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Values.COLOR_PRIMARY);
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 150));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        lblTieuDe = new JLabel("Đăng nhập");
        lblTieuDe.setFont(Values.FONT_TITLE);
        lblTieuDe.setForeground(Values.COLOR_TEXT);
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(lblTieuDe, BorderLayout.CENTER);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        contentPanel.setPreferredSize(new Dimension(300, 400));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        Box box = Box.createVerticalBox();
        box.setBackground(Values.COLOR_BACKGROUND);

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
        box.add(Box.createVerticalStrut(50));

        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        Box bx3 = Box.createHorizontalBox();
        box.add(bx3);
        box.add(Box.createVerticalStrut(20));
        bx3.add(panel);

        Box bx4 = Box.createVerticalBox();
        box.add(bx4);

        lblTenNguoiDung = new JLabel("Tên người dùng");
        lblTenNguoiDung.setFont(Values.FONT_PLAIN_DEFAULT);
        bx1.add(lblTenNguoiDung);
        bx1.add(Box.createVerticalStrut(10));

        txtTenNguoiDung = new JTextField();
        MaterialDesign.materialTextField(txtTenNguoiDung);
        txtTenNguoiDung.addKeyListener(txtTenNguoiDung_Changed());
        bx1.add(txtTenNguoiDung);

        lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setFont(Values.FONT_PLAIN_DEFAULT);
        bx2.add(lblMatKhau);
        bx2.add(Box.createVerticalStrut(10));

        txtMatKhau = new JPasswordField();
        txtMatKhau.addKeyListener(txtMatKhau_Changed());
        MaterialDesign.materialTextField(txtMatKhau);
        bx2.add(txtMatKhau);

        btnThoat = new JButton("Thoát");
        btnThoat.setPreferredSize(new Dimension(100, 50));
        btnThoat.addActionListener(btnThoat_Click());
        MaterialDesign.materialButton(btnThoat);
        panel.add(btnThoat);

        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.addActionListener(btnDangNhap_Click());
        MaterialDesign.materialButton(btnDangNhap);
        btnDangNhap.setPreferredSize(btnThoat.getPreferredSize());
        panel.add(btnDangNhap);

        lblLoi = new JLabel("");
        lblLoi.setForeground(Values.COLOR_ERROR);
        lblLoi.setFont(Values.FONT_PLAIN_DEFAULT);
        lblLoi.setPreferredSize(new Dimension(this.getWidth(), 30));
        lblLoi.setHorizontalAlignment(SwingConstants.CENTER);
        bx4.add(lblLoi);

        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Values.COLOR_PRIMARY);
        bottomPanel.setPreferredSize(new Dimension(100, 30));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        lblBanQuyen = new JLabel("Copyright Buffalo Coder");
        lblBanQuyen.setBorder(BorderFactory.createEmptyBorder());
        lblBanQuyen.setFont(Values.FONT_PLAIN_DEFAULT);
        lblBanQuyen.setForeground(Values.COLOR_TEXT);
        lblBanQuyen.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(lblBanQuyen, BorderLayout.CENTER);
    }

    private void inputError(JTextField txt, String message){
        txt.setBorder(BorderFactory.createLineBorder(Values.COLOR_ERROR, 3));
        txt.requestFocus();
        txt.selectAll();

        lblLoi.setText(message);
    }

    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int select = JOptionPane.showConfirmDialog(
                        rootComponent,
                        "Bạn có muốn thoát chương trình không ?",
                        "Cảnh báo",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (select == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        };
    }

    private ActionListener btnDangNhap_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtTenNguoiDung.getText().trim();
                String password = txtMatKhau.getText().trim();

                if (username.isEmpty()){
                    inputError(txtTenNguoiDung, "Vui lòng nhập tên người dùng");
                    return;
                }else if (password.isEmpty()){
                    inputError(txtMatKhau, "Vui lòng nhập mật khẩu");
                    return;
                }

                // TODO kiểm tra đăng nhập
                if (username.equals("admin") && password.equals("123")){
                    rootComponent.setVisible(false);
                    new MainForm();
                }
            }
        };
    }

    private KeyListener txtTenNguoiDung_Changed(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!lblLoi.getText().trim().isEmpty()){
                    MaterialDesign.materialTextField(txtTenNguoiDung);
                    lblLoi.setText("");
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

            @Override
            public void keyPressed(KeyEvent e) {
                if (!lblLoi.getText().trim().isEmpty()){
                    MaterialDesign.materialTextField(txtMatKhau);
                    lblLoi.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    public DangNhap(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setSize(600, 580);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);
                setLocationRelativeTo(null);
                setTitle("Đăng nhập");
                setVisible(true);
            }
        });
    }
}
