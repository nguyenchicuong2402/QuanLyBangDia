package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;

public class FromThemBangDia extends JFrame {
    JLabel maBangDia;
    JLabel tenBangDia;
    JLabel theLoai;
    JLabel tinhTrang;
    JLabel hangSanXuat;
    JLabel ghiChu;
    JLabel donGia;
    JButton btnadd;
    JTextField txtmaBangDia,txttenBangDia,txttheLoai,txttinhTrang,txthangSanXuat,txtghiChu,txtdonGia;
     public FromThemBangDia(){

         maBangDia=new JLabel("nhập mã đìa");
         tenBangDia= new JLabel("nhập tên đĩa");
         theLoai=new JLabel("nhập thể loại");
         tinhTrang= new JLabel("nhập tình trạng");
         hangSanXuat= new JLabel("nhập hãng sản xuất");
         ghiChu=new JLabel("nhập ghi chú");
         donGia= new JLabel("nhập ghi chú");

         setLayout(null);
         maBangDia.setBounds(20,20,100,25);
         add(maBangDia);
         txtmaBangDia = new JTextField();
         MaterialDesign.materialTextField(txtmaBangDia);
         txtmaBangDia.setBounds(150,20,250,25);
         add(txtmaBangDia);

         tenBangDia.setBounds(20,60,100,25);
         add(tenBangDia);
         txttenBangDia=new JTextField();
         MaterialDesign.materialTextField(txttenBangDia);
         txttenBangDia.setBounds(150,60,250,25);
         add(txttenBangDia);

         theLoai.setBounds(10,100,150,25);
         add(theLoai);
         txttheLoai=new JTextField();
         MaterialDesign.materialTextField(txttheLoai);
         txttheLoai.setBounds(150,100,250,25);
         add(txttheLoai);


         tinhTrang.setBounds(10,140,150,25);
         add(tinhTrang);
         txttinhTrang=new JTextField();
         MaterialDesign.materialTextField(txttinhTrang);
         txttinhTrang.setBounds(150,140,250,25);
         add(txttinhTrang);


         hangSanXuat.setBounds(10,180,150,25);
         add(hangSanXuat);
         txthangSanXuat=new JTextField();
         MaterialDesign.materialTextField(txthangSanXuat);
         txthangSanXuat.setBounds(150,180,250,25);
         add(txthangSanXuat);


         ghiChu.setBounds(10,220,150,25);
         add(ghiChu);
         txtghiChu=new JTextField();
         MaterialDesign.materialTextField(txtghiChu);
         txtghiChu.setBounds(150,220,250,25);
         add(txtghiChu);


         donGia.setBounds(10,260,150,25);
         add(donGia);
         txtdonGia=new JTextField();
         MaterialDesign.materialTextField(txtdonGia);
         txtdonGia.setBounds(150,260,250,25);
         add(txtdonGia);

         btnadd=new JButton("thêm");
         MaterialDesign.materialButton(btnadd);
         btnadd.setBounds(150,360,120,40);
         add(btnadd);

         setVisible(true);
         setSize(450,540);
         setVisible(true);
         setResizable(false);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocation(500, 300);
     }

    public static void main(String[] args) {
        new FromThemBangDia();
    }
}
