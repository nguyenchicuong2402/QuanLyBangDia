package org.buffalocoder.quanlybangdia.views.tabbed;

import javax.swing.*;
import java.util.Date;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
public class FromThemNhanVien extends JFrame {


    JLabel cMND;
    JLabel  hoTen;
    JLabel gioiTinh;
    JLabel soDienThoai;
    JLabel diaChi;
    JLabel ngaySinh,maNV,mota,loaiNV;
    JTextField txtcMND,txthoTen,txtsoDienThoai,txtdiaChi,txtngaySinh,txtmaNV,txtMoTa,txtLoaiNV;
    JButton btnthêm;
    JRadioButton sex;
    public FromThemNhanVien(){
        setLayout(null);
        //setBackground();
        cMND=new JLabel("nhập CMND");
        hoTen= new JLabel("nhập Họ Tên ");
        gioiTinh=new JLabel("nhập giới tính");
        soDienThoai=new JLabel("nhập số điện thoại");
        diaChi=new JLabel("nhập địa chỉ");
        ngaySinh= new JLabel("nhập ngày sinh");
        maNV=new JLabel("nhập loại nhân viên");
        mota=new JLabel("nhập mô tả");
        loaiNV=new JLabel("loại nhân viên");
        btnthêm= new JButton("thêm");
        gioiTinh=new JLabel("giới tính");
        sex= new JRadioButton("nữ");

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


        maNV.setBounds(10,220,150,25);
        add(maNV);
        txtmaNV=new JTextField();
        MaterialDesign.materialTextField(txtmaNV);
        txtmaNV.setBounds(150,220,250,25);
        add(txtmaNV);


        mota.setBounds(10,260,150,25);
        add(mota);
        txtMoTa=new JTextField();
        MaterialDesign.materialTextField(txtMoTa);
        txtMoTa.setBounds(150,260,250,25);
        add(txtMoTa);

        loaiNV.setBounds(10,300,150,25);
        add(loaiNV);
        txtLoaiNV=new JTextField();
        MaterialDesign.materialTextField(txtLoaiNV);
        txtLoaiNV.setBounds(150,300,250,25);
        add(txtLoaiNV);


        gioiTinh.setBounds(10,340,150,25);
        add(gioiTinh);
        sex.setBounds(150,340,250,25);
        add(sex);

        MaterialDesign.materialButton(btnthêm);
        btnthêm.setBounds(150,380,120,40);
        add(btnthêm);



        setVisible(true);
        setSize(450,540);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 300);

    }

    public static void main(String[] args) {
        new FromThemNhanVien();
    }
}
