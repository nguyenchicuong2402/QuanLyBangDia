package org.buffalocoder.quanlybangdia.views.dialog;

import org.buffalocoder.quanlybangdia.dao.BangDiaDAO;
import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.PatternRegexs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BangDiaDialog extends JDialog{
    private String tieuDe;
    private BangDia bangDia;
    private boolean isChinhSua;
    private BangDiaDAO bangDiaDAO;

    private JPanel mainPanel, contentPanel, headerPanel, bottomPanel;
    private JLabel lblTieuDe, lblMaBangDia, lblTenBangDia, lblTheLoai, lblTinhTrang, lblHangSanXuat,
            lblGhiChu, lblDonGia, lblSoLuong, lblLoi;
    private JButton btnThoat, btnLuu;
    private JTextField txtMaBangDia, txtTenBangDia, txtTheLoai, txtHangSanXuat, txtDonGia, txtSoLuong;
    private JTextArea txtGhiChu;
    private JComboBox<String> cbTinhTrang;

    private void prepareDialog(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(MaterialDesign.BORDER_DIALOG);
        MaterialDesign.materialPanel(mainPanel);
        getContentPane().add(mainPanel);

        //========== HEADER PANEL ==========//
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 60));
        headerPanel.setBackground(MaterialDesign.COLOR_PRIMARY);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        lblTieuDe = new JLabel(tieuDe);
        MaterialDesign.materialLabel(lblTieuDe);
        lblTieuDe.setForeground(Color.WHITE);
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(MaterialDesign.FONT_TITLE_2);
        headerPanel.add(lblTieuDe);

        //========== CONTENT PANEL ==========//
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(MaterialDesign.COLOR_BACKGROUND);
        MaterialDesign.materialPanel(contentPanel);
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
        box.add(Box.createVerticalStrut(20));

        lblMaBangDia = new JLabel("Mã băng đĩa");
        lblMaBangDia.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaBangDia);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaBangDia);

        txtMaBangDia = new JTextField(getMaBangDiaMoi());
        MaterialDesign.materialTextField(txtMaBangDia);
        txtMaBangDia.setEditable(false);
        if (isChinhSua) txtMaBangDia.setText(bangDia.getMaBangDia());
        bx1.add(txtMaBangDia);
        bx1.add(Box.createHorizontalStrut(20));

        lblTenBangDia = new JLabel("Tên băng đĩa");
        lblTenBangDia.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblTenBangDia);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblTenBangDia);

        txtTenBangDia = new JTextField();
        MaterialDesign.materialTextField(txtTenBangDia);
        if (isChinhSua) txtTenBangDia.setText(bangDia.getTenBangDia());
        txtTenBangDia.addKeyListener(txtTenBangDia_KeyListener());
        bx2.add(txtTenBangDia);
        bx2.add(Box.createHorizontalStrut(20));

        lblTheLoai = new JLabel("Thể loại");
        lblTheLoai.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblTheLoai);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblTheLoai);

        txtTheLoai = new JTextField();
        MaterialDesign.materialTextField(txtTheLoai);
        if (isChinhSua) txtTheLoai.setText(bangDia.getTheLoai());
        txtTheLoai.addKeyListener(txtTheLoai_KeyListener());
        bx3.add(txtTheLoai);
        bx3.add(Box.createHorizontalStrut(20));

        lblTinhTrang = new JLabel("Tình trạng");
        lblTinhTrang.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblTinhTrang);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblTinhTrang);

        cbTinhTrang = new JComboBox<>(new String[]{"Mới", "Hư hỏng"});
        MaterialDesign.materialComboBox(cbTinhTrang);
        if (isChinhSua) cbTinhTrang.setSelectedItem(bangDia.isTinhTrang() ? "Mới" : "Hư hỏng");
        bx4.add(cbTinhTrang);
        bx4.add(Box.createHorizontalStrut(20));

        lblHangSanXuat = new JLabel("Hãng sản xuất");
        lblHangSanXuat.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblHangSanXuat);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblHangSanXuat);

        txtHangSanXuat = new JTextField();
        MaterialDesign.materialTextField(txtHangSanXuat);
        if (isChinhSua) txtHangSanXuat.setText(bangDia.getHangSanXuat());
        txtHangSanXuat.addKeyListener(txtHangSanXuat_KeyListener());
        bx5.add(txtHangSanXuat);
        bx5.add(Box.createHorizontalStrut(20));

        lblDonGia = new JLabel("Đơn giá");
        lblDonGia.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblDonGia);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblDonGia);

        txtDonGia = new JTextField();
        MaterialDesign.materialTextField(txtDonGia);
        if (isChinhSua) txtDonGia.setText(bangDia.getDonGia().toString());
        txtDonGia.addKeyListener(txtDonGia_KeyListener());
        bx6.add(txtDonGia);
        bx6.add(Box.createHorizontalStrut(20));

        lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblSoLuong);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblSoLuong);

        txtSoLuong = new JTextField();
        MaterialDesign.materialTextField(txtSoLuong);
        if (isChinhSua) txtSoLuong.setText(String.valueOf(bangDia.getSoLuongTon()));
        txtSoLuong.addKeyListener(txtSoLuong_KeyListener());
        bx7.add(txtSoLuong);
        bx7.add(Box.createHorizontalStrut(20));

        lblGhiChu = new JLabel("Ghi chú");
        lblGhiChu.setPreferredSize(lblMaBangDia.getPreferredSize());
        MaterialDesign.materialLabel(lblGhiChu);
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblGhiChu);

        txtGhiChu = new JTextArea(5, 20);
        if (isChinhSua) txtGhiChu.setText(bangDia.getGhiChu());
        MaterialDesign.materialTextArea(txtGhiChu);
        bx8.add(txtGhiChu);
        bx8.add(Box.createHorizontalStrut(20));

        lblLoi = new JLabel(" ");
        MaterialDesign.materialLabel(lblLoi);
        lblLoi.setForeground(MaterialDesign.COLOR_ERROR);
        bx9.add(lblLoi);


        //========== BOTTOM PANEL ==========//
        bottomPanel = new JPanel(new GridLayout(1, 2));
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        btnThoat = new JButton("Đóng");
        MaterialDesign.materialButton(btnThoat);
        btnThoat.setPreferredSize(new Dimension(250, 50));
        btnThoat.addActionListener(btnThoat_Click());
        btnThoat.setBackground(MaterialDesign.COLOR_ERROR);
        bottomPanel.add(btnThoat);

        btnLuu = new JButton(isChinhSua ? "Lưu" : "Thêm");
        MaterialDesign.materialButton(btnLuu);
        btnLuu.addActionListener(btnLuu_Click());
        bottomPanel.add(btnLuu);

        txtTenBangDia.requestFocus();
    }


    private void errorInput(JTextField textField, String message){
        textField.setBorder(MaterialDesign.BORDER_ERROR);
        textField.requestFocus();
        textField.selectAll();

        lblLoi.setText(message);
    }

    private void unErrorInput(JTextField textField){
        MaterialDesign.materialTextField(textField);
        lblLoi.setText(" ");
    }

    private boolean validateData(){
        Pattern pattern = null;

        // Kiểm tra tên băng đĩa
        if (txtTenBangDia.getText().isEmpty()){
            errorInput(txtTenBangDia, "Vui lòng nhập tên băng đĩa");
            return false;
        }else if (txtTenBangDia.getText().trim().length() > 50){
            errorInput(txtTenBangDia, "Tên băng đĩa không quá 50 kí tự");
            return false;
        }

        // Kiểm tra thể loại
        if (txtTheLoai.getText().trim().isEmpty()){
            errorInput(txtTheLoai, "Vui lòng nhập thể loại");
            return false;
        }else if (txtTheLoai.getText().trim().length() > 30){
            errorInput(txtTheLoai, "Thể loại không quá 30 kí tự");
            return false;
        }

        // Kiểm tra hãng sản xuất
        if (txtHangSanXuat.getText().trim().isEmpty()){
            errorInput(txtHangSanXuat, "Vui lòng nhập hãng sản xuất");
            return false;
        }else if (txtHangSanXuat.getText().trim().length() > 50){
            errorInput(txtHangSanXuat, "Hãng sản xuất không vượt quá 50 kí tự");
            return false;
        }

        // Kiểm tra đơn giá
        pattern = Pattern.compile(PatternRegexs.REGEX_SOTHUC);
        if (txtDonGia.getText().trim().isEmpty()){
            errorInput(txtDonGia, "Vui lòng nhập đơn giá");
            return false;
        }else if (!pattern.matcher(txtDonGia.getText().trim()).matches()){
            errorInput(txtDonGia, "Đơn giá phải là số");
            return false;
        }

        // kiểm tra số lượng
        pattern = Pattern.compile(PatternRegexs.REGEX_SO);
        if (txtSoLuong.getText().trim().isEmpty()){
            errorInput(txtSoLuong, "Vui lòng nhập số lượng");
            return false;
        }else if (!pattern.matcher(txtSoLuong.getText().trim()).matches()){
            errorInput(txtSoLuong, "Số lượng phải là số");
            return false;
        }else if(txtSoLuong.getText().trim().length() > 20){
            errorInput(txtSoLuong,"nhập số quá lớn");
            return false;
        }

        return true;
    }

    private String getMaBangDiaMoi(){
        String lastID = "";
        String newID = "";

        try {
            lastID = bangDiaDAO.getMaBangDiaCuoi();
        } catch (Exception e) {
        }

        if (lastID.isEmpty()){
            return "BD00001";
        }

        Pattern pattern = Pattern.compile(PatternRegexs.REGEX_MABANGDIA);
        Matcher matcher = pattern.matcher(lastID);
        if (matcher.find()){
            int number = Integer.parseInt(matcher.group(1));
            number++;

            newID = String.format("BD%05d", number);
        }

        return newID;
    }

    private KeyListener txtTenBangDia_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtTenBangDia);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private KeyListener txtTheLoai_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtTheLoai);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private KeyListener txtHangSanXuat_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtHangSanXuat);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private KeyListener txtDonGia_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtDonGia);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private KeyListener txtSoLuong_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtSoLuong);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BangDiaDialog.this.dispose();
            }
        };
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

        try{
            bangDiaDAO = BangDiaDAO.getInstance();
        }catch (Exception e){

        }

        if (bangDia == null){
            tieuDe = "Thêm băng đĩa";
            isChinhSua = false;
        }else{
            tieuDe = "Sửa thông tin băng đĩa";
            isChinhSua = true;
        }

        prepareDialog();

        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        setResizable(false);
        setSize(600, 620);
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
