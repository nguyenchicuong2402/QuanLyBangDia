package org.buffalocoder.quanlybangdia.views.dialog;

import com.toedter.calendar.JDateChooser;
import org.buffalocoder.quanlybangdia.dao.HoaDonDAO;
import org.buffalocoder.quanlybangdia.models.*;
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

public class ChoThueDialog extends JDialog {
    private HoaDon hoaDon;
    private String tieuDe;
    private boolean isChinhSua;
    private DanhSachKhachHang danhSachKhachHang;
    private DanhSachBangDia danhSachBangDia;
    private HoaDonDAO hoaDonDAO;

    private JPanel mainPanel, headerPanel, contentPanel, bottomPanel;
    private JButton btnThoat, btnLuu;
    private JLabel lblTieuDe, lblMaHoaDon, lblMaKhachHang, lblMaBangDia, lblSoNgayDuocMuon, lblSoLuong,lblLoi,
                        lblNgayThue;
    private JTextField txtMaHoaDon, txtSoNgayDuocMuon, txtSoLuong;
    private JComboBox<String> cbMaKhachHang, cbMaBangDia;
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
        box.add(Box.createVerticalStrut(30));

        Box bx7 = Box.createHorizontalBox();
        box.add(bx7);
        box.add(Box.createVerticalStrut(20));

        lblMaHoaDon = new JLabel("Mã hoá đơn");
        lblMaHoaDon.setPreferredSize(new Dimension(150, 30));
        MaterialDesign.materialLabel(lblMaHoaDon);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaHoaDon);

        txtMaHoaDon = new JTextField(getMaHoaDonMoi());
        MaterialDesign.materialTextField(txtMaHoaDon);
        txtMaHoaDon.setEditable(false);
        if (isChinhSua) txtMaHoaDon.setText(hoaDon.getMaHoaDon());
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
        if (isChinhSua)
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
            if (bangDia.getSoLuongTon() > 0)
                cbMaBangDia.addItem(String.format("[%s] %s", bangDia.getMaBangDia(), bangDia.getTenBangDia()));

        if (isChinhSua){
            cbMaBangDia.addItem(String.format("[%s] %s",
                    hoaDon.getBangDia().getMaBangDia(), hoaDon.getBangDia().getTenBangDia()));

            cbMaBangDia.setSelectedItem(String.format("[%s] %s",
                    hoaDon.getBangDia().getMaBangDia(), hoaDon.getBangDia().getTenBangDia()));
        }

        bx3.add(cbMaBangDia);
        bx3.add(Box.createHorizontalStrut(20));

        lblNgayThue = new JLabel("Ngày thuê");
        lblNgayThue.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblNgayThue);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblNgayThue);

        dateChooser = new JDateChooser(Formats.DATE_FORMAT.toPattern(), "##/##/####", '_');
        MaterialDesign.materialDateChooser(dateChooser);
        if (isChinhSua) dateChooser.setDate(hoaDon.getNgayLap());
        else dateChooser.setDate(new java.util.Date());
        bx4.add(dateChooser);
        bx4.add(Box.createHorizontalStrut(20));

        lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblSoLuong);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblSoLuong);

        txtSoLuong = new JTextField();
        MaterialDesign.materialTextField(txtSoLuong);
        if (isChinhSua) txtSoLuong.setText(String.valueOf(hoaDon.getSoLuong()));
        txtSoLuong.addKeyListener(txtSoLuong_KeyListener());
        bx5.add(txtSoLuong);
        bx5.add(Box.createHorizontalStrut(20));

        lblSoNgayDuocMuon = new JLabel("Số ngày mượn");
        lblSoNgayDuocMuon.setPreferredSize(lblMaHoaDon.getPreferredSize());
        MaterialDesign.materialLabel(lblSoNgayDuocMuon);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblSoNgayDuocMuon);

        txtSoNgayDuocMuon = new JTextField();
        MaterialDesign.materialTextField(txtSoNgayDuocMuon);
        if (isChinhSua) txtSoNgayDuocMuon.setText(String.valueOf(hoaDon.getSoNgayDuocMuon()));
        txtSoNgayDuocMuon.addKeyListener(txtSoNgayDuocMuon_KeyListener());
        bx6.add(txtSoNgayDuocMuon);
        bx6.add(Box.createHorizontalStrut(20));

        lblLoi = new JLabel("      ");
        MaterialDesign.materialLabel(lblLoi);
        lblLoi.setForeground(MaterialDesign.COLOR_ERROR);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblLoi);
        bx7.add(Box.createHorizontalGlue());

        // BOTTOM PANEL
        bottomPanel = new JPanel(new GridLayout(1, 2, 1, 10));
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

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
     * Kiểm tra nhập dữ liệu
     * @return
     */
    private boolean validateData(){
        Pattern pattern = null;

        /**
         * Kiểm tra số lượng
         * Rule: không được rỗng, phải là số nguyên dương lớn hơn 0,  giới hạn 6 chữ số
         */
        pattern = pattern.compile(PatternRegexs.REGEX_SO);
        if (txtSoLuong.getText().trim().isEmpty()){
            errorInput(txtSoLuong, "Vui lòng nhập số lượng");
            return false;
        }else if (!pattern.matcher(txtSoLuong.getText().trim()).matches()){
            errorInput(txtSoLuong, "Số lượng phải là số");
            return false;
        }else if (Integer.parseInt(txtSoLuong.getText().trim()) <= 0){
            errorInput(txtSoLuong, "Số lượng phải lớn hơn 0");
            return false;
        }else if(txtSoLuong.getText().trim().length() >= 6){
            errorInput(txtSoLuong,"Số lượng quá lớn");
            return false;
        }

        /**
         * Kiểm tra số ngày mượn
         * Rule: không được rỗng, phải là số nguyên dương trong khoảng từ 1 - 120
         */
        pattern = Pattern.compile(PatternRegexs.REGEX_SO);
        if (txtSoNgayDuocMuon.getText().trim().isEmpty()){
            errorInput(txtSoNgayDuocMuon, "Vui lòng nhập số số ngày mượn");
            return false;
        }else if (!pattern.matcher(txtSoNgayDuocMuon.getText().trim()).matches()){
            errorInput(txtSoNgayDuocMuon, "Số ngày mượn phải là số");
            return false;
        }else if(Integer.parseInt(txtSoNgayDuocMuon.getText().trim()) <= 0){
            errorInput(txtSoNgayDuocMuon,"Số ngày mượn phải lớn hơn 0");
            return false;
        }else if(Integer.parseInt(txtSoNgayDuocMuon.getText().trim()) > 120){
            errorInput(txtSoNgayDuocMuon,"Số ngày mượn phải nhỏ hơn 120");
            return false;
        }

        return true;
    }


    /**
     * Generate mã hoá đơn mới
     * @return
     */
    private String getMaHoaDonMoi(){
        String lastID = "";
        String newID = "";

        // lấy mã hoá đơn cuối trong DB
        try {
            lastID = hoaDonDAO.getMaHoaDonCuoi();
        } catch (Exception e) {
        }

        // Nếu chưa có hoá đơn nào trong DB thì trả về mã mặc định
        if (lastID.isEmpty()){
            return "HD00001";
        }

        // generate mã
        Pattern pattern = Pattern.compile(PatternRegexs.REGEX_MAHOADON);
        Matcher matcher = pattern.matcher(lastID);
        if (matcher.find()){
            int number = Integer.parseInt(matcher.group(1));
            number++;

            newID = String.format("HD%05d", number);
        }

        return newID;
    }


    /**
     * Sự kiện khi nhập text số lượng
     * @return
     */
    private KeyListener txtSoLuong_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtSoLuong);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Sự kiện nhập text số ngày được mượn
     * @return
     */
    private KeyListener txtSoNgayDuocMuon_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtSoNgayDuocMuon);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }


    /**
     * Sự kiện nút Thoát
     * @return
     */
    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hoaDon = null;
                ChoThueDialog.this.dispose();
            }
        };
    }


    /**
     * Sự kiện nút Lưu
     * @return
     */
    private ActionListener btnLuu_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kiểm tra dữ liệu nhập
                if (!validateData())
                    return;

                BangDia bangDia = null;
                KhachHang khachHang = null;
                Pattern pattern = null;
                Matcher matcher = null;

                // lấy dữ liệu băng đĩa
                pattern = Pattern.compile("(BD\\d.*)]", Pattern.MULTILINE);
                matcher = pattern.matcher(String.valueOf(cbMaBangDia.getSelectedItem()));

                if (matcher.find())
                    bangDia = danhSachBangDia.getAll().get(danhSachBangDia.tim(matcher.group(1)));

                // lấy dữ liệu khách hàng
                pattern = Pattern.compile("(KH\\d.*)]", Pattern.MULTILINE);
                matcher = pattern.matcher(cbMaKhachHang.getSelectedItem().toString());

                if (matcher.find())
                    khachHang = danhSachKhachHang.getAll().get(danhSachKhachHang.tim(matcher.group(1)));

                // tạo thông tin hoá đơn
                hoaDon = new HoaDon(
                        bangDia,
                        Integer.parseInt(txtSoNgayDuocMuon.getText().trim()),
                        Integer.parseInt(txtSoLuong.getText().trim()),
                        txtMaHoaDon.getText().trim(),
                        khachHang,
                        Date.valueOf(Formats.DATE_FORMAT_SQL.format(dateChooser.getDate()))
                );

                // đóng dialog
                dispose();
            }
        };
    }

    /**
     * Trả về hoá đơn đã được thêm/chỉnh sửa
     * @return
     */
    public HoaDon getHoaDon() {
        return hoaDon;
    }


    /**
     * Constructor
     * @param frame
     * @param hoaDon
     */
    public ChoThueDialog(JFrame frame, HoaDon hoaDon) throws Exception {
        super(frame, true);
        this.hoaDon = hoaDon;

        // Tạo kết nối đến db
        try {
            hoaDonDAO = HoaDonDAO.getInstance();
            danhSachKhachHang = new DanhSachKhachHang();
            danhSachBangDia = new DanhSachBangDia();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        /**
         * set tiêu đề cho dialog
         * Nếu param hoaDon == null > Cho thuê
         * Nếu param hoaDon != null > Cập nhật thông tin cho thuê
         */
        if (hoaDon == null){
            this.tieuDe = "Cho thuê";
            isChinhSua = false;
        }else{
            this.tieuDe = "Cập nhật thông tin cho thuê";
            isChinhSua = true;
        }

        // Tạo GUI
        prepareDialog();

        // Button mặc định khi bấm Enter
        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        // cấu hình cho dialog
        setResizable(false);
        setSize(600, 480);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
