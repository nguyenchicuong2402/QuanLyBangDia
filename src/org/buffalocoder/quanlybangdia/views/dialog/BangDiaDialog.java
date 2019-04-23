package org.buffalocoder.quanlybangdia.views.dialog;

import org.buffalocoder.quanlybangdia.dao.BangDiaDAO;
import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.PatternRegexs;

import javax.swing.*;
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


    /**
     * tạo GUI
     */
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
        cbTinhTrang.setEnabled(false);
        if (isChinhSua) {
            cbTinhTrang.setEnabled(true);
            cbTinhTrang.setSelectedItem(bangDia.isTinhTrang() ? "Mới" : "Hư hỏng");
        }
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


    /**
     * Thông báo lỗi khi nhập sai
     * @param textField
     * @param message
     */
    private void errorInput(JTextField textField, String message){
        textField.setBorder(MaterialDesign.BORDER_ERROR);
        textField.requestFocus();
        textField.selectAll();

        lblLoi.setText(message);
    }


    /**
     * Tắt thông báo lỗi khi nhập sai
     * @param textField
     */
    private void unErrorInput(JTextField textField){
        if (!lblLoi.getText().isEmpty()){
            MaterialDesign.materialTextField(textField);
            lblLoi.setText(" ");
        }
    }


    /**
     * Kiểm tra dữ liệu nhập
     * @return
     */
    private boolean validateData(){
        Pattern pattern = null;

        /**
         * Kiểm tra tên băng đĩa
         * Rule: tên băng đĩa không được rỗng, không quá 50 kí tự
         */
        if (txtTenBangDia.getText().isEmpty()){
            errorInput(txtTenBangDia, "Vui lòng nhập tên băng đĩa");
            return false;
        }else if (txtTenBangDia.getText().trim().length() > 50){
            errorInput(txtTenBangDia, "Tên băng đĩa không quá 50 kí tự");
            return false;
        }

        /**
         * Kiểm tra thể loại
         * Rule: thể loại không được rỗng, không quá 30 kí tự
         */
        if (txtTheLoai.getText().trim().isEmpty()){
            errorInput(txtTheLoai, "Vui lòng nhập thể loại");
            return false;
        }else if (txtTheLoai.getText().trim().length() > 30){
            errorInput(txtTheLoai, "Thể loại không quá 30 kí tự");
            return false;
        }

        /**
         * Kiểm tra hãng sản xuất
         * Rule: hãng sản xuất không được rỗng, không quá 50 kí tự
         */
        if (txtHangSanXuat.getText().trim().isEmpty()){
            errorInput(txtHangSanXuat, "Vui lòng nhập hãng sản xuất");
            return false;
        }else if (txtHangSanXuat.getText().trim().length() > 50){
            errorInput(txtHangSanXuat, "Hãng sản xuất không vượt quá 50 kí tự");
            return false;
        }

        /**
         * Kiểm tra đơn giá
         * Rule: đơn giá không được rỗng, phải là số thực lớn hơn 0
         */
        pattern = Pattern.compile(PatternRegexs.REGEX_SOTHUC);
        if (txtDonGia.getText().trim().isEmpty()){
            errorInput(txtDonGia, "Vui lòng nhập đơn giá");
            return false;
        }else if (!pattern.matcher(txtDonGia.getText().trim()).matches()){
            errorInput(txtDonGia, "Đơn giá phải là số");
            return false;
        }else if (Double.parseDouble(txtDonGia.getText().trim()) <= 0){
            errorInput(txtDonGia, "Đơn giá phải lớn hơn 0");
            return false;
        }

        /**
         * Kiểm tra số lượng
         * Rule: Số lượng không được rỗng, phải là số nguyên dương > 0, và giới hạn là 6 số
         */
        pattern = Pattern.compile(PatternRegexs.REGEX_SO);
        if (txtSoLuong.getText().trim().isEmpty()){
            errorInput(txtSoLuong, "Vui lòng nhập số lượng");
            return false;
        }else if (!pattern.matcher(txtSoLuong.getText().trim()).matches()){
            errorInput(txtSoLuong, "Số lượng phải là số nguyên");
            return false;
        }else if(txtSoLuong.getText().trim().length() >= 6){
            errorInput(txtSoLuong,"Số lượng quá lớn");
            return false;
        }else if (Integer.parseInt(txtSoLuong.getText().trim()) <= 0){
            errorInput(txtSoLuong, "Số lượng phải lớn hơn 0");
            return false;
        }

        return true;
    }


    /**
     * Generate mã băng đãi mới
     * @return
     */
    private String getMaBangDiaMoi(){
        String lastID = "";
        String newID = "";

        // lấy mã băng đĩa cuối trong DB
        try {
            lastID = bangDiaDAO.getMaBangDiaCuoi();
        } catch (Exception e) {
        }

        // nếu chưa có băng đĩa nào trong DB thì trả về mã mặc định đầu tiên
        if (lastID.isEmpty()){
            return "BD00001";
        }

        // generate mã
        Pattern pattern = Pattern.compile(PatternRegexs.REGEX_MABANGDIA);
        Matcher matcher = pattern.matcher(lastID);
        if (matcher.find()){
            int number = Integer.parseInt(matcher.group(1));
            number++;

            newID = String.format("BD%05d", number);
        }

        return newID;
    }


    /**
     * Sự kiện khi nhập text tên băng đĩa
     * Nếu có lỗi thì sẽ xoá lỗi
     * @return
     */
    private KeyListener txtTenBangDia_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtTenBangDia);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Sự kiện khi nhập text thể loại
     * Nếu có lỗi thì sẽ xoá lỗi
     * @return
     */
    private KeyListener txtTheLoai_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtTheLoai);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Sự kiện khi nhập text Hãng sản xuất
     * Nếu có lỗi thì sẽ xoá lỗi
     * @return
     */
    private KeyListener txtHangSanXuat_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtHangSanXuat);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Sự kiện khi nhập text dơn giá
     * Nếu có lỗi thì sẽ xoá lỗi
     * @return
     */
    private KeyListener txtDonGia_KeyListener(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtDonGia);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };
    }


    /**
     * Sự kiện khi nhập text số lượng
     * Nếu có lỗi thì sẽ xoá lỗi
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
     * Sự kiện button thoát > đóng dialog
     * @return
     */
    private ActionListener btnThoat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bangDia = null;
                BangDiaDialog.this.dispose();
            }
        };
    }


    /**
     * Sự kiện button Lưu
     * @return
     */
    private ActionListener btnLuu_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * Kiểm tra dữ liệu
                 * nếu có lỗi thì thông báo
                 */
                if (!validateData())
                    return;

                // lấy thông tin băng đĩa đã nhập
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

                // đóng dialog
                dispose();
            }
        };
    }


    /**
     * Lấy băn đĩa đã được thêm/chỉnh sửa
     * @return
     */
    public BangDia getBangDia(){
        return bangDia;
    }


    /**
     * Constructor
     * @param frame
     * @param bangDia
     * @throws Exception
     */
    public BangDiaDialog(JFrame frame, BangDia bangDia) throws Exception {
        super(frame, true);
        this.bangDia = bangDia;

        // lấy instance kết nối với db (table BangDia)
        try{
            bangDiaDAO = BangDiaDAO.getInstance();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        /**
         * Đặt tiêu đề cho dialog
         * Nếu param bangDia = null > Thêm băng đĩa
         * Nếu param bangDia != null > Cập nhật thông tin băng đĩa
         */
        if (bangDia == null){
            tieuDe = "Thêm băng đĩa";
            isChinhSua = false;
        }else{
            tieuDe = "Cập nhật thông tin băng đĩa";
            isChinhSua = true;
        }

        // tạo GUI
        prepareDialog();

        // đặt button mặc định khi bấm Enter
        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        // cấu hình cho dialog
        setResizable(false);
        setSize(600, 620);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
