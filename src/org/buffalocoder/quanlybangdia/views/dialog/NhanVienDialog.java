package org.buffalocoder.quanlybangdia.views.dialog;

import com.toedter.calendar.JDateChooser;
import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;
import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.utils.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhanVienDialog extends JDialog {
    private String tieuDe;
    private NhanVien nhanVien;
    private TaiKhoan taiKhoan;
    private boolean isEdit;
    private NhanVienDAO nhanVienDAO;

    private JPanel mainPanel, contentPanel, headerPanel, bottomPanel, infoPanel, accountPanel;
    private JLabel lblTieuDe, lblMaNV, lblCMND, lblHoTen, lblGioiTinh, lblSoDienThoai,
            lblDiaChi, lblNgaySinh, lblMoTa, lblTenTaiKhoan, lblMatKhau, lblNhapLaiMatKhau, lblLoaiTaiKhoan,lblLoi;
    private JButton btnThoat, btnLuu;
    private JTextField txtMaNV, txtCMND, txtHoTen, txtSoDienThoai, txtDiaChi, txtNgaySinh, txtMoTa,
                        txtTenTaiKhoan;
    private JPasswordField txtMatKhau, txtNhapLaiMatKhau;
    private JComboBox<String> cbGioiTinh, cbLoaiTaiKhoan;
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

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(infoPanel);
        infoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Colors.PRIMARY, 1),
                "Thông tin nhân viên"));
        contentPanel.add(infoPanel);

        Box box_info = Box.createVerticalBox();
        infoPanel.add(box_info);
        box_info.add(Box.createVerticalStrut(20));

        Box bx1 = Box.createHorizontalBox();
        box_info.add(bx1);
        box_info.add(Box.createVerticalStrut(10));

        Box bx2 = Box.createHorizontalBox();
        box_info.add(bx2);
        box_info.add(Box.createVerticalStrut(10));

        Box bx3 = Box.createHorizontalBox();
        box_info.add(bx3);
        box_info.add(Box.createVerticalStrut(10));

        Box bx4 = Box.createHorizontalBox();
        box_info.add(bx4);
        box_info.add(Box.createVerticalStrut(10));

        Box bx5 = Box.createHorizontalBox();
        box_info.add(bx5);
        box_info.add(Box.createVerticalStrut(10));

        Box bx6 = Box.createHorizontalBox();
        box_info.add(bx6);
        box_info.add(Box.createVerticalStrut(10));

        Box bx7 = Box.createHorizontalBox();
        box_info.add(bx7);
        box_info.add(Box.createVerticalStrut(10));

        Box bx8 = Box.createHorizontalBox();
        box_info.add(bx8);
        box_info.add(Box.createVerticalStrut(10));

        lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaNV);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaNV);

        txtMaNV = new JTextField(getMaNhanVienMoi());
        MaterialDesign.materialTextField(txtMaNV);
        txtMaNV.setEditable(false);
        if (isEdit) txtMaNV.setText(nhanVien.getMaNhanVien());
        bx1.add(txtMaNV);
        bx1.add(Box.createHorizontalStrut(20));

        lblHoTen = new JLabel("Họ và tên");
        lblHoTen.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblHoTen);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblHoTen);

        txtHoTen = new JTextField();
        MaterialDesign.materialTextField(txtHoTen);
        if (isEdit) txtHoTen.setText(nhanVien.getHoTen());
        bx2.add(txtHoTen);
        bx2.add(Box.createHorizontalStrut(20));

        lblCMND = new JLabel("Số CMND");
        lblCMND.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblCMND);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblCMND);

        txtCMND = new JTextField();
        MaterialDesign.materialTextField(txtCMND);
        if (isEdit) {
            txtCMND.setText(nhanVien.getcMND());
            txtCMND.setEditable(false);
        }
        bx3.add(txtCMND);
        bx3.add(Box.createHorizontalStrut(20));

        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblGioiTinh);

        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        if (isEdit) cbGioiTinh.setSelectedItem(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
        bx4.add(cbGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));

        lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblNgaySinh);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblNgaySinh);

        dateChooser = new JDateChooser(Formats.DATE_FORMAT.toPattern(), "##/##/####", '_');
        MaterialDesign.materialDateChooser(dateChooser);
        if (isEdit) dateChooser.setDate(nhanVien.getNgaySinh());
        else dateChooser.setDate(new java.util.Date());
        bx5.add(dateChooser);
        bx5.add(Box.createHorizontalStrut(20));

        lblSoDienThoai = new JLabel("Số điện thoại");
        lblSoDienThoai.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblSoDienThoai);

        txtSoDienThoai = new JTextField();
        MaterialDesign.materialTextField(txtSoDienThoai);
        if (isEdit) txtSoDienThoai.setText(nhanVien.getSoDienThoai());
        bx6.add(txtSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));

        lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblDiaChi);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblDiaChi);

        txtDiaChi = new JTextField();
        MaterialDesign.materialTextField(txtDiaChi);
        if (isEdit) txtDiaChi.setText(nhanVien.getDiaChi());
        bx7.add(txtDiaChi);
        bx7.add(Box.createHorizontalStrut(20));

        lblMoTa = new JLabel("Mô tả");
        lblMoTa.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblMoTa);
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblMoTa);

        txtMoTa = new JTextField();
        MaterialDesign.materialTextField(txtMoTa);
        if (isEdit) txtMoTa.setText(nhanVien.getMoTa());
        bx8.add(txtMoTa);
        bx8.add(Box.createHorizontalStrut(20));

        accountPanel = new JPanel();
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(accountPanel);
        accountPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Colors.PRIMARY, 1),
                "Tài khoản"));
        contentPanel.add(accountPanel);

        Box box_account = Box.createVerticalBox();
        accountPanel.add(box_account);
        box_account.add(Box.createVerticalStrut(20));

        Box bx9 = Box.createHorizontalBox();
        box_account.add(bx9);
        box_account.add(Box.createVerticalStrut(10));

        Box bx10 = Box.createHorizontalBox();
        box_account.add(bx10);
        box_account.add(Box.createVerticalStrut(10));

        Box bx11 = Box.createHorizontalBox();
        box_account.add(bx11);
        box_account.add(Box.createVerticalStrut(10));

        Box bx12= Box.createHorizontalBox();
        box_account.add(bx12);
        box_account.add(Box.createVerticalStrut(20));


        Box bx13 = Box.createHorizontalBox();
        box_account.add(bx13);
        box_account.add(Box.createVerticalStrut(20));

        lblTenTaiKhoan = new JLabel("Tên tài khoản");
        lblTenTaiKhoan.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblTenTaiKhoan);
        bx9.add(Box.createHorizontalStrut(20));
        bx9.add(lblTenTaiKhoan);

        txtTenTaiKhoan = new JTextField();
        MaterialDesign.materialTextField(txtTenTaiKhoan);
        if (isEdit) {
            txtTenTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
            txtTenTaiKhoan.setEditable(false);
        }
        bx9.add(txtTenTaiKhoan);
        bx9.add(Box.createHorizontalStrut(20));

        lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblMatKhau);
        bx10.add(Box.createHorizontalStrut(20));
        bx10.add(lblMatKhau);

        txtMatKhau = new JPasswordField();
        MaterialDesign.materialTextField(txtMatKhau);
        bx10.add(txtMatKhau);
        bx10.add(Box.createHorizontalStrut(20));

        lblNhapLaiMatKhau = new JLabel("Nhập lại mật khẩu");
        lblNhapLaiMatKhau.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblNhapLaiMatKhau);
        bx11.add(Box.createHorizontalStrut(20));
        bx11.add(lblNhapLaiMatKhau);

        txtNhapLaiMatKhau = new JPasswordField();
        MaterialDesign.materialTextField(txtNhapLaiMatKhau);
        bx11.add(txtNhapLaiMatKhau);
        bx11.add(Box.createHorizontalStrut(20));

        lblLoaiTaiKhoan = new JLabel("Loại tài khoản");
        lblLoaiTaiKhoan.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblLoaiTaiKhoan);
        bx12.add(Box.createHorizontalStrut(20));
        bx12.add(lblLoaiTaiKhoan);

        cbLoaiTaiKhoan = new JComboBox<>(new String[]{"ADMIN", "NHÂN VIÊN"});
        if (isEdit) cbLoaiTaiKhoan.setSelectedItem(taiKhoan.getLoaiTaiKhoan() == 1 ? "ADMIN" : "NHÂN VIÊN");
        bx12.add(cbLoaiTaiKhoan);
        bx12.add(Box.createHorizontalStrut(20));


        lblLoi = new JLabel("      ");
        MaterialDesign.materialLabel(lblLoi);
        lblLoi.setForeground(Colors.ERROR);
        bx13.add(Box.createHorizontalStrut(20));
        bx13.add(lblLoi);
        bx13.add(Box.createHorizontalGlue());

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


    private void errorInput(JTextField textField, String message){
        lblLoi.setText(message);
        textField.setBorder(MaterialDesign.BORDER_ERROR);
        textField.requestFocus();
        textField.selectAll();
    }

    private void unErrorInput(JTextField textField){
        MaterialDesign.materialTextField(textField);
        lblLoi.setText("    ");
    }


    private boolean validateData(){

//        // tên
//        String regexten="[a-zA-Z_0-9]{1,50}";
//        String kiemtraten=txtHoTen.getText();
//        Pattern pattern = Pattern.compile(regexten);
//        Matcher matcherten = pattern.matcher(kiemtraten);
//
//        //CMND
//        String regexCMND="[0-9]{1,20}";
//        String kiemtraCMND=txtCMND.getText();
//        pattern = Pattern.compile(regexCMND);
//        Matcher matcherCMND = pattern.matcher(kiemtraCMND);
//
//        //SDT
//        String regexSDT="[0-9]{1,20}";
//        String kiemtraSDT=txtCMND.getText();
//        pattern = Pattern.compile(regexSDT);
//        Matcher matcherSDT = pattern.matcher(kiemtraSDT);
//
//
//        //địa chỉ
//        String regexdiachi="[a-zA-Z_0-9]{1,100}";
//        String kiemtradiachi=txtCMND.getText();
//        pattern = Pattern.compile(regexdiachi);
//        Matcher matcherdiachi = pattern.matcher(kiemtradiachi);
//
//        // mô tả
//        String regexmota="[a-zA-Z]{1,100}";
//        String kiemtramota=txtCMND.getText();
//        pattern = Pattern.compile(regexmota);
//        Matcher matchermota = pattern.matcher(kiemtramota);
//
//        // tên tài khoản
//        String regextentk="[a-zA-Z_0-9]{1,30}";
//        String kiemtratentk=txtTenTaiKhoan.getText();
//        pattern = Pattern.compile(regextentk);
//        Matcher matchertentk = pattern.matcher(kiemtratentk);
//
//        //password
//        String regextpass="[a-zA-Z_0-9]{1,128}";
//        String kiemtrapass=txtMatKhau.getText();
//        pattern = Pattern.compile(regextpass);
//        Matcher matcherpass = pattern.matcher(kiemtrapass);
//
//
//        String kiemtrarepass=txtNhapLaiMatKhau.getText();
////        if(matcherten.matches()&&matcherCMND.matches()) {
////
////            return true;
////
////        }
//        if(!matcherten.matches())
//            return false;
//
//        if(!matcherCMND.matches())
//            return false;
//
//        if(!matcherSDT.matches())
//            return false;
//
//        if(!matcherdiachi.matches())
//            return false;
//        if(!matchermota.matches())
//            return false;
//        if(!matchertentk.matches())
//            return false;
//        if(!matcherpass.matches())
//            return false;




        Pattern pattern = null;

        // Kiểm tra họ tên
        if (txtHoTen.getText().trim().isEmpty()){
            errorInput(txtHoTen, "Vui lòng nhập họ tên");
            return false;
        }else if (txtHoTen.getText().trim().length() > 50){
            errorInput(txtHoTen, "Không nhập họ tên quá 50 kí tự");
            return false;
        }

        // Kiểm tra CMND
        pattern = pattern.compile(PatternRegexs.REGEX_CMND);
        if (txtCMND.getText().trim().isEmpty()){
            errorInput(txtCMND, "Vui lòng nhập CMND");
            return false;
        }else if (!pattern.matcher(txtCMND.getText().trim()).matches()){
            errorInput(txtCMND, "CMND phải là số (không quá 20 số)");
            return false;
        }

        // Kiểm tra số diện thoại
        pattern = Pattern.compile(PatternRegexs.REGEX_SODIENTHOAI);
        if (txtSoDienThoai.getText().trim().isEmpty()){
            errorInput(txtSoDienThoai, "Vui lòng nhập số điện thoại");
            return false;
        }else if (!pattern.matcher(txtSoDienThoai.getText().trim()).matches()){
            errorInput(txtSoDienThoai, "Số điện thoại phải là số (không quá 20 số)");
            return false;
        }

        // Kiểm tra địa chỉ
        if (txtDiaChi.getText().trim().isEmpty()){
            errorInput(txtDiaChi, "Vui lòng nhập địa chỉ");
            return false;
        }else if (txtDiaChi.getText().trim().length() > 100){
            errorInput(txtDiaChi, "Địa chỉ không quá 100 kí tự");
            return false;
        }

        if (txtMoTa.getText().trim().isEmpty()){
            errorInput(txtMoTa, "Vui lòng nhập mô tả");
            return false;
        }else if (txtMoTa.getText().trim().length() > 100){
            errorInput(txtMoTa, "mô tả không quá 100 kí tự");
            return false;
        }

        if (txtMatKhau.getText().trim().isEmpty()){
            errorInput(txtMatKhau, "Vui lòng nhập nhập mật khẩu");
            return false;
        }else if (txtMatKhau.getText().trim().length() > 128){
            errorInput(txtMatKhau, "mật khẩu không quá 128 kí tự");
            return false;
        }

        if (txtNhapLaiMatKhau.getText().trim().isEmpty()){
            errorInput(txtNhapLaiMatKhau, "Vui lòng nhập nhập lại mật khẩu");

        }else if (txtNhapLaiMatKhau.getText().trim().length() > 128){
            errorInput(txtNhapLaiMatKhau, "mật khẩu không quá 128 kí tự");

        }else if (!txtMatKhau.getText().equals(txtNhapLaiMatKhau.getText())){
            errorInput(txtNhapLaiMatKhau, "mật khẩu không trùng với nhập lại");
        }


        return true;
    }


    private KeyListener txtHoTen_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtHoTen);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private KeyListener txtCMND_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtCMND);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private KeyListener txtSoDienThoai_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtSoDienThoai);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }
    private  KeyListener txtMoTa_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtMoTa);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

  private  KeyListener txttxtMatKhau_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtMatKhau);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
  }


    private  KeyListener txtNhapLaiMatKhau_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtNhapLaiMatKhau);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }










    private String getMaNhanVienMoi(){
        String lastID = "";
        String newID = "";

        try {
            lastID = nhanVienDAO.getMaNhanVienCuoi();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lastID.isEmpty()){
            return "NV00001";
        }

        Pattern pattern = Pattern.compile(PatternRegexs.REGEX_MANHANVIEN);
        Matcher matcher = pattern.matcher(lastID);
        if (matcher.find()){
            int number = Integer.parseInt(matcher.group(1));
            number++;

            newID = String.format("NV%05d", number);
        }

        return newID;
    }

    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanVien = null;
                taiKhoan = null;
                NhanVienDialog.this.dispose();
            }
        };
    }

    private ActionListener btnLuu_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateData()){
                    System.out.println("false");
                    return;
                }

                nhanVien = new NhanVien(
                        txtCMND.getText().trim(),
                        txtHoTen.getText().trim(),
                        cbGioiTinh.getSelectedItem().equals("Nam"),
                        txtSoDienThoai.getText().trim(),
                        txtDiaChi.getText().trim(),
                        Date.valueOf(Formats.DATE_FORMAT_SQL.format(dateChooser.getDate())),
                        txtMaNV.getText().trim(),
                        txtMoTa.getText().trim()
                );

                taiKhoan = new TaiKhoan(
                        txtTenTaiKhoan.getText().trim(),
                        txtMatKhau.getText().trim(),
                        cbLoaiTaiKhoan.getSelectedItem().equals("ADMIN") ? 1 : 0,
                        txtMaNV.getText().trim()
                );

                dispose();
            }
        };
    }

    public NhanVienDialog(JFrame frame, NhanVien nhanVien, TaiKhoan taiKhoan){
        super(frame, true);
        this.nhanVien = nhanVien;
        this.taiKhoan = taiKhoan;

        try {
            nhanVienDAO = NhanVienDAO.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nhanVien == null){
            tieuDe = "Thêm Nhân viên";
            isEdit = false;
        }else{
            tieuDe = "Sửa thông tin nhân viên";
            isEdit = true;
        }

        prepareDialog();

        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        setResizable(false);
        setSize(600, 750);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public NhanVien getNhanVien(){
        return nhanVien;
    }

    public TaiKhoan getTaiKhoan() { return taiKhoan; }
}
