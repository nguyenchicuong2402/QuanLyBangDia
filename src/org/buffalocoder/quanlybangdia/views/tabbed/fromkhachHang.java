package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;

public class fromkhachHang extends JFrame {
    JLabel cMND;
    JLabel  hoTen;
    JLabel gioiTinh;
    JLabel soDienThoai;
    JLabel diaChi;
    JLabel ngaySinh,makh,ngayhethan;
    JTextField txtcMND,txthoTen,txtsoDienThoai,txtdiaChi,txtngaySinh,txtkh,txtngayhethan;
    JButton btnthêm;
    JRadioButton sex;
    public fromkhachHang(){

        setLayout(null);
        //setBackground();
        cMND=new JLabel("nhập CMND");
        hoTen= new JLabel("nhập Họ Tên ");
        gioiTinh=new JLabel("nhập giới tính");
        soDienThoai=new JLabel("nhập số điện thoại");
        diaChi=new JLabel("nhập địa chỉ");
        ngaySinh= new JLabel("nhập ngày sinh");
        makh=new JLabel("nhập mã khách hàng");
        ngayhethan=new JLabel("nhập ngày hết hạn");
        gioiTinh = new JLabel("gioi tính");
        btnthêm= new JButton("thêm");
        sex = new JRadioButton("nữ");


        cMND.setBounds(20,20,100,25);
        add(cMND);
        txtcMND = new JTextField();
        MaterialDesign.materialTextField(txtcMND);
        txtcMND.setBounds(150,20,250,25);
        hoTen.setBounds(20,60,100,25);
        add(txtcMND);
        add(hoTen);
        txthoTen=new JTextField();
        MaterialDesign.materialTextField(txthoTen);
        txthoTen.setBounds(150,60,250,25);
        add(txthoTen);

        soDienThoai.setBounds(10,100,150,25);
        add(soDienThoai);
        txtsoDienThoai=new JTextField();
        MaterialDesign.materialTextField(txtsoDienThoai);
        txtsoDienThoai.setBounds(150,100,250,25);
        add(txtsoDienThoai);


        diaChi.setBounds(10,140,150,25);
        add(diaChi);
        txtdiaChi=new JTextField();
        MaterialDesign.materialTextField(txtdiaChi);
        txtdiaChi.setBounds(150,140,250,25);
        add(txtdiaChi);


        ngaySinh.setBounds(10,180,150,25);
        add(ngaySinh);
        txtngaySinh=new JTextField();
        MaterialDesign.materialTextField(txtngaySinh);
        txtngaySinh.setBounds(150,180,250,25);
        add(txtngaySinh);


        makh.setBounds(10,220,150,25);
        add(makh);
        txtkh=new JTextField();
        MaterialDesign.materialTextField(txtkh);
        txtkh.setBounds(150,220,250,25);
        add(txtkh);


        ngayhethan.setBounds(10,260,150,25);
        add(ngayhethan);
        txtngayhethan=new JTextField();
        MaterialDesign.materialTextField(txtngayhethan);
        txtngayhethan.setBounds(150,260,250,25);
        add(txtngayhethan);



        gioiTinh.setBounds(10,300,150,25);
        add(gioiTinh);
        sex.setBounds(150,300,250,25);
        add(sex);


        MaterialDesign.materialButton(btnthêm);
        btnthêm.setBounds(150,360,120,40);
        add(btnthêm);




        setVisible(true);
        setSize(450,540);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 300);
    }

    public static void main(String[] args) {
        new fromkhachHang();
    }
}
