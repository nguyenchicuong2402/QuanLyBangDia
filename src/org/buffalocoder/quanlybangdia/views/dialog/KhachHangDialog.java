package org.buffalocoder.quanlybangdia.views.dialog;

import com.toedter.calendar.JDateChooser;
import org.buffalocoder.quanlybangdia.dao.KhachHangDAO;
import org.buffalocoder.quanlybangdia.models.KhachHang;
import org.buffalocoder.quanlybangdia.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KhachHangDialog extends JDialog {
    private String tieuDe;
    private KhachHang khachHang;
    private boolean isChinhSua;
    private KhachHangDAO khachHangDAO;

    private JPanel mainPanel, contentPanel, headerPanel, bottomPanel;
    private JLabel lblTieuDe, lblMaKH, lblCMND, lblHoTen, lblGioiTinh, lblSoDienThoai,
            lblDiaChi, lblNgaySinh, lblLoi;
    private JButton btnThoat, btnLuu;
    private JTextField txtMaKH, txtCMND, txtHoTen, txtSoDienThoai, txtDiaChi, txtNgaySinh;
    private JComboBox<String> cbGioiTinh;
    private JDateChooser dateChooser;


    /**
     * Tạo GUI
     */
    private void prepareDialog(){
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(MaterialDesign.BORDER_DIALOG);
        MaterialDesign.materialPanel(mainPanel);
        getContentPane().add(mainPanel);

        // HEADER PANEL
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 60));
        MaterialDesign.materialPanel(headerPanel);
        headerPanel.setBackground(MaterialDesign.COLOR_PRIMARY);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        lblTieuDe = new JLabel(tieuDe);
        MaterialDesign.materialLabel(lblTieuDe);
        lblTieuDe.setForeground(Color.WHITE);
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(MaterialDesign.FONT_TITLE_2);
        headerPanel.add(lblTieuDe);

        // CONTENT PANEL
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(contentPanel);
        contentPanel.setBackground(MaterialDesign.COLOR_BACKGROUND);
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

        lblMaKH = new JLabel("Mã khách hàng");
        lblMaKH.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaKH);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaKH);

        txtMaKH = new JTextField(getMaKhachHangMoi());
        MaterialDesign.materialTextField(txtMaKH);
        txtMaKH.setEditable(false);
        if (isChinhSua) txtMaKH.setText(khachHang.getMaKH());
        bx1.add(txtMaKH);
        bx1.add(Box.createHorizontalStrut(20));

        lblHoTen = new JLabel("Họ và tên");
        lblHoTen.setPreferredSize(lblMaKH.getPreferredSize());
        MaterialDesign.materialLabel(lblHoTen);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblHoTen);

        txtHoTen = new JTextField();
        MaterialDesign.materialTextField(txtHoTen);
        if (isChinhSua) txtHoTen.setText(khachHang.getHoTen());
        txtHoTen.addKeyListener(txtHoTen_KeyListener());
        bx2.add(txtHoTen);
        bx2.add(Box.createHorizontalStrut(20));

        lblCMND = new JLabel("Số CMND");
        lblCMND.setPreferredSize(lblMaKH.getPreferredSize());
        MaterialDesign.materialLabel(lblCMND);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblCMND);

        txtCMND = new JTextField();
        MaterialDesign.materialTextField(txtCMND);
        if (isChinhSua) {
            txtCMND.setText(khachHang.getcMND());
            txtCMND.setEditable(false);
        }
        txtCMND.addKeyListener(txtCMND_KeyListener());
        bx3.add(txtCMND);
        bx3.add(Box.createHorizontalStrut(20));

        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setPreferredSize(lblMaKH.getPreferredSize());
        MaterialDesign.materialLabel(lblGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblGioiTinh);

        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        MaterialDesign.materialComboBox(cbGioiTinh);
        if (isChinhSua) cbGioiTinh.setSelectedItem(khachHang.isGioiTinh() ? "Nam" : "Nữ");
        bx4.add(cbGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));

        lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setPreferredSize(lblMaKH.getPreferredSize());
        MaterialDesign.materialLabel(lblNgaySinh);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblNgaySinh);

        dateChooser = new JDateChooser(Formats.DATE_FORMAT.toPattern(), "##/##/####", '_');
        MaterialDesign.materialDateChooser(dateChooser);
        if (isChinhSua) dateChooser.setDate(khachHang.getNgaySinh());
        else dateChooser.setDate(new java.util.Date());
        bx5.add(dateChooser);
        bx5.add(Box.createHorizontalStrut(20));

        lblSoDienThoai = new JLabel("Số điện thoại");
        lblSoDienThoai.setPreferredSize(lblMaKH.getPreferredSize());
        MaterialDesign.materialLabel(lblSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblSoDienThoai);

        txtSoDienThoai = new JTextField();
        MaterialDesign.materialTextField(txtSoDienThoai);
        if (isChinhSua) txtSoDienThoai.setText(khachHang.getSoDienThoai());
        txtSoDienThoai.addKeyListener(txtSoDienThoai_KeyListener());
        bx6.add(txtSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));

        lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setPreferredSize(lblMaKH.getPreferredSize());
        MaterialDesign.materialLabel(lblDiaChi);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblDiaChi);

        txtDiaChi = new JTextField();
        MaterialDesign.materialTextField(txtDiaChi);
        if (isChinhSua) txtDiaChi.setText(khachHang.getDiaChi());
        txtDiaChi.addKeyListener(txtDiaChi_KeyListener());
        bx7.add(txtDiaChi);
        bx7.add(Box.createHorizontalStrut(20));

        lblLoi = new JLabel("      ");
        MaterialDesign.materialLabel(lblLoi);
        lblLoi.setForeground(MaterialDesign.COLOR_ERROR);
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblLoi);
        bx8.add(Box.createHorizontalGlue());

        // BOTTOM PANEL
        bottomPanel = new JPanel(new GridLayout(1, 2, 1, 10));
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        MaterialDesign.materialPanel(bottomPanel);

        btnThoat = new JButton("Đóng");
        MaterialDesign.materialButton(btnThoat);
        btnThoat.setPreferredSize(new Dimension(250, 50));
        btnThoat.setBackground(MaterialDesign.COLOR_ERROR);
        btnThoat.addActionListener(btnThoat_Click());
        bottomPanel.add(btnThoat);

        btnLuu = new JButton(isChinhSua ? "Lưu" : "Thêm");
        MaterialDesign.materialButton(btnLuu);
        btnLuu.addActionListener(btnLuu_Click());
        bottomPanel.add(btnLuu);
    }


    /**
     * Thông báo lỗi nhập text
     * @param textField
     * @param message
     */
    private void errorInput(JTextField textField, String message){
        lblLoi.setText(message);
        textField.setBorder(MaterialDesign.BORDER_ERROR);
        textField.requestFocus();
        textField.selectAll();
    }


    /**
     * Tắt thông báo lỗi nhập text
     * @param textField
     */
    private void unErrorInput(JTextField textField){
        if (!lblLoi.getText().isEmpty()){
            MaterialDesign.materialTextField(textField);
            lblLoi.setText("    ");
        }
    }


    /**
     * Generate mã khách hàng mới
     * @return
     */
    private String getMaKhachHangMoi(){
        String lastID = "";
        String newID = "";

        // lấy mã khách hàng cuối trong DB
        try {
            lastID = khachHangDAO.getMaKhachHangCuoi();
        } catch (Exception e) {
        }

        // Nếu chưa có khách hàng trong DB thì trả về mã mặc định
        if (lastID.isEmpty()){
            return "KH00001";
        }

        // generate mã khách hàng mới
        Pattern pattern = Pattern.compile(PatternRegexs.REGEX_MAKHACHHANG);
        Matcher matcher = pattern.matcher(lastID);
        if (matcher.find()){
            int number = Integer.parseInt(matcher.group(1));
            number++;

            newID = String.format("KH%05d", number);
        }

        return newID;
    }


    /**
     * Kiểm tra dữ liệu nhập
     * @return
     */
    private boolean validateData(){
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

        return true;
    }


    /**
     * Sự kiện nhập text Họ tên
     * Nếu có lỗi thì tắt lỗi
     * @return
     */
    private KeyListener txtHoTen_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtHoTen);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Kiểm tra nhập CMND
     * Nếu có lỗi thì tắt lỗi
     * @return
     */
    private KeyListener txtCMND_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtCMND);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Kiểm tra nhập số điện thoại
     * Nếu có lỗi thì tắt lỗi
     * @return
     */
    private KeyListener txtSoDienThoai_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtSoDienThoai);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Kiểm tra nhập địa chỉ
     * Nếu có lỗi thì tắt lỗi
     * @return
     */
    private KeyListener txtDiaChi_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtDiaChi);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Sự kiện khi nhấn button Thoát
     * @return
     */
    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                khachHang = null;
                KhachHangDialog.this.dispose();
            }
        };
    }


    /**
     * Sự kiện khi nhấn nút Lưu
     * @return
     */
    private ActionListener btnLuu_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // kiểm tra dữ liệu nhập
                if (!validateData())
                    return;

                // lấy thông tin khách hàng
                khachHang = new KhachHang(
                        txtCMND.getText().trim(),
                        txtHoTen.getText().trim(),
                        cbGioiTinh.getSelectedItem().equals("Nam"),
                        txtSoDienThoai.getText().trim(),
                        txtDiaChi.getText().trim(),
                        Date.valueOf(Formats.DATE_FORMAT_SQL.format(dateChooser.getDate())),
                        txtMaKH.getText().trim()
                );

                // đóng dialog
                dispose();
            }
        };
    }


    /**
     * Lấy thông tin khách hàng
     * @return
     */
    public KhachHang getKhachHang(){
        return khachHang;
    }


    public KhachHangDialog(JFrame frame, KhachHang khachHang){
        super(frame, true);
        this.khachHang = khachHang;

        try {
            khachHangDAO = KhachHangDAO.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (khachHang == null){
            tieuDe = "Thêm khách hàng";
            isChinhSua = false;
        }else{
            tieuDe = "Sửa thông tin khách hàng";
            isChinhSua = true;
        }

        prepareDialog();

        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        setResizable(false);
        setSize(600, 500);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
