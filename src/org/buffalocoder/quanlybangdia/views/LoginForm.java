package org.buffalocoder.quanlybangdia.views;

import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LoginForm extends JFrame {

    private JPanel contentPanel, headerPanel, bodyPanel, footerPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox cbRemember;
    private JLabel lblUsername, lblPassword, lblTitle;
    private JButton btnLogin, btnExit;

    private void prepareUI(){
        contentPanel = new JPanel(new BorderLayout());
        this.setContentPane(contentPanel);

        //========== HEADER PANEL =========//
        headerPanel = new JPanel();
        contentPanel.add(headerPanel, BorderLayout.NORTH);

        lblTitle = new JLabel("Đăng nhập");
        lblTitle.setFont(Values.FONT_TITLE);
        headerPanel.add(lblTitle);

        //========== BODY PANEL ==========//
        bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        contentPanel.add(bodyPanel, BorderLayout.CENTER);

        Box boxHor = Box.createHorizontalBox();
        boxHor.add(Box.createHorizontalStrut(50));
        Box box = Box.createVerticalBox();
        boxHor.add(box);
        boxHor.add(Box.createHorizontalStrut(50));
        Box bx1 = Box.createHorizontalBox();
        bx1.setBorder(BorderFactory.createEtchedBorder());
        box.add(bx1);
        Box bx2 = Box.createHorizontalBox();
        box.add(bx2);
        Box bx3 = Box.createHorizontalBox();
        box.add(bx3);
        Box bx4 = Box.createHorizontalBox();
        box.add(bx4);
        bodyPanel.add(boxHor);

        lblUsername = new JLabel("Tên đăng nhập");
        lblUsername.setPreferredSize(new Dimension(120, 30));
        bx1.add(lblUsername);

        txtUsername = new JTextField();
        bx1.add(txtUsername);

        lblPassword = new JLabel("Mật khẩu");
        lblPassword.setPreferredSize(lblUsername.getPreferredSize());
        bx2.add(lblPassword);

        txtPassword = new JPasswordField();
        bx2.add(txtPassword);

        cbRemember = new JCheckBox("Ghi nhớ đăng nhập");
        bx3.add(cbRemember);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setPreferredSize(new Dimension(300, 50));
        bx4.add(btnLogin);
        bx4.add(Box.createHorizontalStrut(30));

        btnExit = new JButton("Thoát");
        bx4.add(btnExit);
    }

    /**
     * Constructor
     */
    public LoginForm(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setSize(600, 500);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);
                setLocationRelativeTo(null);
                setTitle("Đăng nhập");
                setVisible(true);
            }
        });
    }
}
