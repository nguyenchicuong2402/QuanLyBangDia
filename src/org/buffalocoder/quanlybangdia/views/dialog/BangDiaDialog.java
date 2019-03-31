package org.buffalocoder.quanlybangdia.views.dialog;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.utils.Colors;
import org.buffalocoder.quanlybangdia.utils.Fonts;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class BangDiaDialog extends JDialog{
    private String tieuDe;
    private BangDia bangDia;
    private boolean isEdit;

    private JPanel mainPanel, contentPanel, headerPanel, bottomPanel;
    private JLabel lblTieuDe, lblMaBangDia, lblTenBangDia, lblTheLoai, lblTinhTrang, lblHangSanXuat,
            lblGhiChu, lblDonGia, lblSoLuong;
    private JButton btnThoat, btnLuu;
    private JTextField txtMaBangDia, txtTenBangDia, txtTheLoai, txtHangSanXuat, txtDonGia, txtSoLuong;
    private JTextArea txtGhiChu;
    private JComboBox<String> cbTinhTrang;

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
        box.add(Box.createVerticalStrut(20));

        lblMaBangDia = new JLabel("Mã băng đĩa");
        lblMaBangDia.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaBangDia);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaBangDia);

        txtMaBangDia = new JTextField("BD00006");
        MaterialDesign.materialTextField(txtMaBangDia);
        txtMaBangDia.setEditable(false);
        if (isEdit) txtMaBangDia.setText(bangDia.getMaBangDia());
        bx1.add(txtMaBangDia);
        bx1.add(Box.createHorizontalStrut(20));

        lblTenBangDia = new JLabel("Tên băng đĩa");
        lblTenBangDia.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblTenBangDia);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblTenBangDia);

        txtTenBangDia = new JTextField();
        MaterialDesign.materialTextField(txtTenBangDia);
        if (isEdit) txtTenBangDia.setText(bangDia.getTenBangDia());
        bx2.add(txtTenBangDia);
        bx2.add(Box.createHorizontalStrut(20));

        lblTheLoai = new JLabel("Thể loại");
        lblTheLoai.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblTheLoai);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblTheLoai);

        txtTheLoai = new JTextField();
        MaterialDesign.materialTextField(txtTheLoai);
        if (isEdit) txtTheLoai.setText(bangDia.getTheLoai());
        bx3.add(txtTheLoai);
        bx3.add(Box.createHorizontalStrut(20));

        lblTinhTrang = new JLabel("Tình trạng");
        lblTinhTrang.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblTinhTrang);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblTinhTrang);

        cbTinhTrang = new JComboBox<>(new String[]{"Mới", "Hư hỏng"});
        if (isEdit) cbTinhTrang.setSelectedItem(bangDia.isTinhTrang() ? "Mới" : "Hư hỏng");
        bx4.add(cbTinhTrang);
        bx4.add(Box.createHorizontalStrut(20));

        lblHangSanXuat = new JLabel("Hãng sản xuất");
        lblHangSanXuat.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblHangSanXuat);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblHangSanXuat);

        txtHangSanXuat = new JTextField();
        MaterialDesign.materialTextField(txtHangSanXuat);
        if (isEdit) txtHangSanXuat.setText(bangDia.getHangSanXuat());
        bx5.add(txtHangSanXuat);
        bx5.add(Box.createHorizontalStrut(20));

        lblDonGia = new JLabel("Đơn giá");
        lblDonGia.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblDonGia);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblDonGia);

        txtDonGia = new JTextField();
        MaterialDesign.materialTextField(txtDonGia);
        if (isEdit) txtDonGia.setText(bangDia.getDonGia().toString());
        bx6.add(txtDonGia);
        bx6.add(Box.createHorizontalStrut(20));

        lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblSoLuong);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblSoLuong);

        txtSoLuong = new JTextField();
        MaterialDesign.materialTextField(txtSoLuong);
        if (isEdit) txtSoLuong.setText(String.valueOf(bangDia.getSoLuongTon()));
        bx7.add(txtSoLuong);
        bx7.add(Box.createHorizontalStrut(20));

        lblGhiChu = new JLabel("Ghi chú");
        lblGhiChu.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblGhiChu);
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblGhiChu);

        txtGhiChu = new JTextArea(5, 20);
        if (isEdit) txtGhiChu.setText(bangDia.getGhiChu());
        MaterialDesign.materialTextArea(txtGhiChu);
        bx8.add(txtGhiChu);
        bx8.add(Box.createHorizontalStrut(20));

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
                BangDiaDialog.this.dispose();
            }
        };
    }

    private boolean validateData(){


       // String regex="\\d{1,2}-\\d{1,2}-\\d{4}";
//        String kiemtra=txtNgaySinh.getText();
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(kiemtra);
//        if(matcher.matches())
//         return true;
//        return false;
        return true;
    }

    private ActionListener btnLuu_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateData())
                    return;

                bangDia = new BangDia(
                        txtMaBangDia.getText().trim(),
                        txtTenBangDia.getText().trim(),
                        txtTheLoai.getText().trim(),
                        String.valueOf(cbTinhTrang.getSelectedItem()).equalsIgnoreCase("Mới"),
                        txtHangSanXuat.getText().trim(),
                        txtGhiChu.getText().trim(),
                        Double.parseDouble(txtDonGia.getText().trim()),
                        Integer.parseInt(txtSoLuong.getText().trim())
                );

                dispose();
            }
        };
    }

    public BangDiaDialog(JFrame frame, BangDia bangDia){
        super(frame, true);
        this.bangDia = bangDia;

        if (bangDia == null){
            tieuDe = "Thêm băng đĩa";
            isEdit = false;
        }else{
            tieuDe = "Sửa thông tin băng đĩa";
            isEdit = true;
        }

        prepareDialog();

        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        setResizable(false);
        setSize(600, 600);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public BangDia getBangDia(){
        return bangDia;
    }
}
