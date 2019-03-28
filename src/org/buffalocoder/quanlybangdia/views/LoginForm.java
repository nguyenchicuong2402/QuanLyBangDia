package org.buffalocoder.quanlybangdia.views;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class LoginForm extends JFrame {

    private void prepareUI(){
        JPanel Jp1= new JPanel();
        JPanel Jp2= new JPanel();
        JPanel Jp3= new JPanel();
        JPanel Jpsubmit= new JPanel();
        JLabel login =new JLabel("LOGIN");
        JLabel loginsubmit =new JLabel("LOGIN");
        JLabel passworf =new JLabel("PASSWORD");
        JTextField txtLogin =new JTextField()  ;
        JTextField txtPass = new JTextField() ;
        JButton JLOGIN =new JButton();

        JPanel Jplogin= new JPanel();


        JLabel label =new JLabel("LOGIN");
        label.setForeground(Color.decode("#ffffff"));
        Font fp =new Font("Times new Roman",Font.BOLD, 20);
        label.setFont(fp);




        Jp1.setBackground(Values.COLOR_PRIMARY_LOGIN);
        Jp1.setPreferredSize(new Dimension(50, 50));
        Jp1.add(label);


        Jp2.setLayout(null);
        login.setFont(fp);
        passworf.setFont(fp);
        Jplogin.setBackground(Color.decode("#455a64"));
        Jplogin.setBounds(0, 0, 450, 60);
        Jplogin.setLayout(null);
        label.setBounds(20, 10, 70, 50);
        Jplogin.add(label);
        Jp2.add(Jplogin);
        login.setBounds(40, 100, 100, 50);
        txtLogin.setBounds(45, 145, 300, 25);
        txtLogin.setBorder(new MatteBorder(0,0,2,0,Color.decode("#3d5afe")));
        //txtLogin.setBackground(Color.decode("#009999"));
        passworf.setBounds(40, 200, 150, 50);
        txtPass.setBounds(45, 245, 300, 25);
        txtPass.setBorder(new MatteBorder(0,0,2,0,Color.decode("#3d5afe")));
//        Jpsubmit.setBackground(Color.decode("#3d5afe"));
//        Jpsubmit.setBounds(60, 300,300, 40);
//        Jpsubmit.add(loginsubmit);


        JLOGIN = new JButton("lOGIN");
        JLOGIN.setPreferredSize(new Dimension(90, 40));
        MaterialDesign.materialButton(JLOGIN);
        JLOGIN.setBounds(60, 300,300, 40);



        Jp2.setBackground(Color.WHITE);
        Jp2.add(login);
        Jp2.add(txtLogin);
        Jp2.add(passworf);
        Jp2.add(txtPass);
        Jp2.add(JLOGIN);



        Jp3.setBackground(Color.decode("#455a64"));
        Jp3.setPreferredSize(new Dimension(40, 40));
        JLabel footer =new JLabel("Copy Right @ Buffalo Coder");
        footer.setForeground(Color.decode("#ffffff"));
        footer.setFont(fp);
        Jp3.add(footer);

        add(Jp1,BorderLayout.NORTH);
        add(Jp2,BorderLayout.CENTER);
        add(Jp3,BorderLayout.SOUTH);


    }

    /**
     * Constructor
     */
    public LoginForm(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setSize(450, 540);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setResizable(false);
                setLocationRelativeTo(null);
                setTitle("Đăng nhập");
                setVisible(true);
            }
        });
    }

//    public static void main(String[] args) {
//       new LoginForm();
//    }

}
