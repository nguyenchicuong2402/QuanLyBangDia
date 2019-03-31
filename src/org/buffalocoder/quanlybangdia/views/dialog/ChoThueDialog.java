package org.buffalocoder.quanlybangdia.views.dialog;

import com.toedter.calendar.JDateChooser;
import org.buffalocoder.quanlybangdia.models.*;
import org.buffalocoder.quanlybangdia.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChoThueDialog extends JDialog {
    private HoaDon hoaDon;
    private String tieuDe;
    private boolean isEdit;
    private DanhSachKhachHang danhSachKhachHang;
    private DanhSachBangDia danhSachBangDia;

    private JPanel mainPanel, headerPanel, contentPanel, bottomPanel;
    private JButton btnThoat, btnLuu;
    private JLabel lblTieuDe, lblMaHoaDon, lblMaKhachHang, lblMaBangDia, lblSoNgayDuocMuon, lblSoLuong,
                        lblNgayThue;
    private JTextField txtMaHoaDon, txtSoNgayDuocMuon, txtSoLuong;
    private JComboBox<String> cbMaKhachHang, cbMaBangDia;
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
        box.add(Box.createVerticalStrut(50));

        lblMaHoaDon = new JLabel("Mã hoá đơn");
        lblMaHoaDon.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaHoaDon);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaHoaDon);

        txtMaHoaDon = new JTextField("HD00004");
        MaterialDesign.materialTextField(txtMaHoaDon);
        txtMaHoaDon.setEditable(false);
        if (isEdit) txtMaHoaDon.setText(hoaDon.getMaHoaDon());
        bx1.add(txtMaHoaDon);
        bx1.add(Box.createHorizontalStrut(20));

        lblMaKhachHang = new JLabel("Mã khách hàng");
        lblMaKhachHang.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblMaKhachHang);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblMaKhachHang);

        cbMaKhachHang = new JComboBox<String>();
        MaterialDesign.materialComboBox(cbMaKhachHang);
        for (KhachHang khachHang : danhSachKhachHang.getAll())
            cbMaKhachHang.addItem(String.format("[%s] %s", khachHang.getMaKH(), khachHang.getHoTen()));
        if (isEdit)
            cbMaKhachHang.setSelectedItem(String.format("[%s] %s",
                    hoaDon.getKhachHang().getMaKH(),
                    hoaDon.getKhachHang().getHoTen()));
        bx2.add(cbMaKhachHang);
        bx2.add(Box.createHorizontalStrut(20));

        lblMaBangDia = new JLabel("Mã băng đĩa");
        lblMaBangDia.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblMaBangDia);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblMaBangDia);

        cbMaBangDia = new JComboBox<String>();
        MaterialDesign.materialComboBox(cbMaBangDia);
        for (BangDia bangDia : danhSachBangDia.getAll())
            cbMaBangDia.addItem(String.format("[%s] %s", bangDia.getMaBangDia(), bangDia.getTenBangDia()));
        if (isEdit)
            cbMaBangDia.setSelectedItem(String.format("[%s] %s",
                    hoaDon.getBangDia().getMaBangDia(), hoaDon.getBangDia().getTenBangDia()));
        bx3.add(cbMaBangDia);
        bx3.add(Box.createHorizontalStrut(20));

        lblNgayThue = new JLabel("Ngày thuê");
        lblNgayThue.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblNgayThue);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblNgayThue);

        dateChooser = new JDateChooser(Formats.DATE_FORMAT.toPattern(), "##/##/####", '_');
        MaterialDesign.materialDateChooser(dateChooser);
        if (isEdit) dateChooser.setDate(hoaDon.getNgayLap());
        else dateChooser.setDate(new Date());
        bx4.add(dateChooser);
        bx4.add(Box.createHorizontalStrut(20));

        lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblSoLuong);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblSoLuong);

        txtSoLuong = new JTextField();
        MaterialDesign.materialTextField(txtSoLuong);
        if (isEdit) txtSoLuong.setText(String.valueOf(hoaDon.getSoLuong()));
        bx5.add(txtSoLuong);
        bx5.add(Box.createHorizontalStrut(20));

        lblSoNgayDuocMuon = new JLabel("Số ngày mượn");
        lblSoNgayDuocMuon.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblSoNgayDuocMuon);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblSoNgayDuocMuon);

        txtSoNgayDuocMuon = new JTextField();
        MaterialDesign.materialTextField(txtSoNgayDuocMuon);
        if (isEdit) txtSoNgayDuocMuon.setText(String.valueOf(hoaDon.getSoNgayDuocMuon()));
        bx6.add(txtSoNgayDuocMuon);
        bx6.add(Box.createHorizontalStrut(20));

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

    private boolean validateData(){

        String regex="\\d{1,2}-\\d{1,2}-\\d{4}";
//        String kiemtra=txtNgaySinh.getText();
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(kiemtra);
//        if(matcher.matches())
//         return true;
//        return false;

        return true;
    }

    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChoThueDialog.this.dispose();
            }
        };
    }

    private ActionListener btnLuu_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateData())
                    return;

                BangDia bangDia = null;
                KhachHang khachHang = null;

                String regex = "(BD\\\\d.*)\\\\]";

                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(cbMaBangDia.getSelectedItem().toString());

                if (matcher.find())
                    bangDia = danhSachBangDia.getAll().get(danhSachBangDia.tim(matcher.group(1)));

                regex = "(KH\\\\d.*)\\\\]";

                pattern = Pattern.compile(regex, Pattern.MULTILINE);
                matcher = pattern.matcher(cbMaKhachHang.getSelectedItem().toString());

                if (matcher.find())
                    khachHang = danhSachKhachHang.getAll().get(danhSachKhachHang.tim(matcher.group(1)));

                hoaDon = new HoaDon(
                        bangDia,
                        Integer.parseInt(txtSoNgayDuocMuon.getText().trim()),
                        Integer.parseInt(txtSoLuong.getText().trim()),
                        txtMaHoaDon.getText().trim(),
                        khachHang
                );

                dispose();
            }
        };
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public ChoThueDialog(JFrame frame, HoaDon hoaDon){
        super(frame, true);
        this.hoaDon = hoaDon;

        if (hoaDon == null){
            this.tieuDe = "Cho thuê";
            isEdit = false;
        }else{
            this.tieuDe = "Cập nhật thông tin cho thuê";
            isEdit = true;
        }

        try{
            danhSachKhachHang = new DanhSachKhachHang();
            danhSachBangDia = new DanhSachBangDia();
        }catch (Exception e){

        }

        prepareDialog();

        JRootPane rootPane = SwingUtilities.getRootPane(this);
//        rootPane.setDefaultButton(btnLuu);

        setResizable(false);
        setSize(600, 500);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
