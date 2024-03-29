package org.buffalocoder.quanlybangdia.views.dialog;

import com.toedter.calendar.JDateChooser;
import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;
import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.utils.Formats;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.PatternRegexs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhanVienDialog extends JDialog {
    private String tieuDe;
    private NhanVien nhanVien;
    private TaiKhoan taiKhoan;
    private boolean isChinhSua;
    private NhanVienDAO nhanVienDAO;

    private JPanel mainPanel, contentPanel, headerPanel, bottomPanel, infoPanel, accountPanel;
    private JLabel lblTieuDe, lblMaNV, lblCMND, lblHoTen, lblGioiTinh, lblSoDienThoai,
            lblDiaChi, lblNgaySinh, lblMoTa, lblTenTaiKhoan, lblMatKhau, lblNhapLaiMatKhau, lblLoaiTaiKhoan, lblLoi;
    private JButton btnThoat, btnLuu;
    private JTextField txtMaNV, txtCMND, txtHoTen, txtSoDienThoai, txtDiaChi, txtNgaySinh, txtMoTa,
            txtTenTaiKhoan;
    private JPasswordField txtMatKhau, txtNhapLaiMatKhau;
    private JComboBox<String> cbGioiTinh, cbLoaiTaiKhoan;
    private JDateChooser dateChooser;


    /**
     * Tạo GUI
     */
    private void prepareDialog() {
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
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(infoPanel);
        infoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MaterialDesign.COLOR_PRIMARY, 1),
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
        if (isChinhSua) txtMaNV.setText(nhanVien.getMaNhanVien());
        bx1.add(txtMaNV);
        bx1.add(Box.createHorizontalStrut(20));

        lblHoTen = new JLabel("Họ và tên");
        lblHoTen.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblHoTen);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblHoTen);

        txtHoTen = new JTextField();
        txtHoTen.requestFocus();
        MaterialDesign.materialTextField(txtHoTen);
        if (isChinhSua) txtHoTen.setText(nhanVien.getHoTen());
        txtHoTen.addKeyListener(txtHoTen_KeyListener());
        bx2.add(txtHoTen);
        bx2.add(Box.createHorizontalStrut(20));

        lblCMND = new JLabel("Số CMND");
        lblCMND.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblCMND);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblCMND);

        txtCMND = new JTextField();
        MaterialDesign.materialTextField(txtCMND);
        if (isChinhSua) {
            txtCMND.setText(nhanVien.getcMND());
            txtCMND.setEditable(false);
        }
        txtCMND.addKeyListener(txtCMND_KeyListener());
        bx3.add(txtCMND);
        bx3.add(Box.createHorizontalStrut(20));

        lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblGioiTinh);

        cbGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        MaterialDesign.materialComboBox(cbGioiTinh);
        if (isChinhSua) cbGioiTinh.setSelectedItem(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
        bx4.add(cbGioiTinh);
        bx4.add(Box.createHorizontalStrut(20));

        lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblNgaySinh);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblNgaySinh);

        dateChooser = new JDateChooser(Formats.DATE_FORMAT.toPattern(), "##/##/####", '_');
        MaterialDesign.materialDateChooser(dateChooser);
        java.util.Date currentDate = new java.util.Date();
        currentDate.setYear(currentDate.getYear() - 18);
        dateChooser.setMaxSelectableDate(currentDate);
        if (isChinhSua) dateChooser.setDate(nhanVien.getNgaySinh());
        else dateChooser.setDate(currentDate);
        bx5.add(dateChooser);
        bx5.add(Box.createHorizontalStrut(20));

        lblSoDienThoai = new JLabel("Số điện thoại");
        lblSoDienThoai.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblSoDienThoai);

        txtSoDienThoai = new JTextField();
        MaterialDesign.materialTextField(txtSoDienThoai);
        if (isChinhSua) txtSoDienThoai.setText(nhanVien.getSoDienThoai());
        txtSoDienThoai.addKeyListener(txtSoDienThoai_KeyListener());
        bx6.add(txtSoDienThoai);
        bx6.add(Box.createHorizontalStrut(20));

        lblDiaChi = new JLabel("Địa chỉ");
        lblDiaChi.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblDiaChi);
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblDiaChi);

        txtDiaChi = new JTextField();
        MaterialDesign.materialTextField(txtDiaChi);
        if (isChinhSua) txtDiaChi.setText(nhanVien.getDiaChi());
        txtDiaChi.addKeyListener(txtDiaChi_KeyListener());
        bx7.add(txtDiaChi);
        bx7.add(Box.createHorizontalStrut(20));

        lblMoTa = new JLabel("Mô tả");
        lblMoTa.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblMoTa);
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblMoTa);

        txtMoTa = new JTextField();
        MaterialDesign.materialTextField(txtMoTa);
        if (isChinhSua) txtMoTa.setText(nhanVien.getMoTa());
        bx8.add(txtMoTa);
        bx8.add(Box.createHorizontalStrut(20));

        accountPanel = new JPanel();
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(accountPanel);
        accountPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(MaterialDesign.COLOR_PRIMARY, 1),
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

        Box bx12 = Box.createHorizontalBox();
        box_account.add(bx12);
        box_account.add(Box.createVerticalStrut(20));

        lblTenTaiKhoan = new JLabel("Tên tài khoản");
        lblTenTaiKhoan.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblTenTaiKhoan);
        bx9.add(Box.createHorizontalStrut(20));
        bx9.add(lblTenTaiKhoan);

        txtTenTaiKhoan = new JTextField();
        MaterialDesign.materialTextField(txtTenTaiKhoan);
        if (isChinhSua) {
            txtTenTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
            txtTenTaiKhoan.setEditable(false);
        }
        txtTenTaiKhoan.addKeyListener(txtTenTaiKhoan_KeyListener());
        bx9.add(txtTenTaiKhoan);
        bx9.add(Box.createHorizontalStrut(20));

        lblMatKhau = new JLabel("Mật khẩu");
        lblMatKhau.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblMatKhau);
        bx10.add(Box.createHorizontalStrut(20));
        bx10.add(lblMatKhau);

        txtMatKhau = new JPasswordField();
        MaterialDesign.materialTextField(txtMatKhau);
        txtMatKhau.addKeyListener(txtMatKhau_KeyListener());
        bx10.add(txtMatKhau);
        bx10.add(Box.createHorizontalStrut(20));

        lblNhapLaiMatKhau = new JLabel("Nhập lại mật khẩu");
        lblNhapLaiMatKhau.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblNhapLaiMatKhau);
        bx11.add(Box.createHorizontalStrut(20));
        bx11.add(lblNhapLaiMatKhau);

        txtNhapLaiMatKhau = new JPasswordField();
        MaterialDesign.materialTextField(txtNhapLaiMatKhau);
        txtNhapLaiMatKhau.addKeyListener(txtNhapLaiMatKhau_KeyListener());
        bx11.add(txtNhapLaiMatKhau);
        bx11.add(Box.createHorizontalStrut(20));

        lblLoaiTaiKhoan = new JLabel("Loại tài khoản");
        lblLoaiTaiKhoan.setPreferredSize(lblMaNV.getPreferredSize());
        MaterialDesign.materialLabel(lblLoaiTaiKhoan);
        bx12.add(Box.createHorizontalStrut(20));
        bx12.add(lblLoaiTaiKhoan);

        cbLoaiTaiKhoan = new JComboBox<>(new String[]{"ADMIN", "NHÂN VIÊN"});
        cbLoaiTaiKhoan.setSelectedIndex(1);
        MaterialDesign.materialComboBox(cbLoaiTaiKhoan);
        if (isChinhSua) cbLoaiTaiKhoan.setSelectedItem(taiKhoan.getLoaiTaiKhoan() == 1 ? "ADMIN" : "NHÂN VIÊN");
        bx12.add(cbLoaiTaiKhoan);
        bx12.add(Box.createHorizontalStrut(20));

        JPanel loiPanel = new JPanel();
        loiPanel.setLayout(new BoxLayout(loiPanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(loiPanel);
        loiPanel.setBackground(MaterialDesign.COLOR_BACKGROUND);
        contentPanel.add(loiPanel);

        lblLoi = new JLabel("      ");
        MaterialDesign.materialLabel(lblLoi);
        lblLoi.setForeground(MaterialDesign.COLOR_ERROR);
        lblLoi.setPreferredSize(new Dimension(500, 30));
        lblLoi.setHorizontalAlignment(JLabel.CENTER);
        loiPanel.add(Box.createHorizontalGlue());
        loiPanel.add(lblLoi);
        loiPanel.add(Box.createHorizontalGlue());

        // BOTTOM PANEL
        bottomPanel = new JPanel(new GridLayout(1, 2, 1, 10));
        MaterialDesign.materialPanel(bottomPanel);
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
     * Thông báo nhập text lỗi
     *
     * @param textField
     * @param message
     */
    private void errorInput(JTextField textField, String message) {
        lblLoi.setText(message);
        textField.setBorder(MaterialDesign.BORDER_ERROR);
        textField.requestFocus();
        textField.selectAll();
    }


    /**
     * Tắt thông báo nhập lỗi
     *
     * @param textField
     */
    private void unErrorInput(JTextField textField) {
        if (!lblLoi.getText().isEmpty()) {
            MaterialDesign.materialTextField(textField);
            lblLoi.setText("    ");
        }
    }


    /**
     * Kiểm tra nhập dữ liệu
     *
     * @return
     */
    private boolean validateData() {
        Pattern pattern = null;

        /**
         * Kiểm tra họ tên
         * Rule: không được rỗng, không quá 50 kí tự
         */
        if (txtHoTen.getText().trim().isEmpty()) {
            errorInput(txtHoTen, "Vui lòng nhập họ tên");
            return false;
        } else if (txtHoTen.getText().trim().length() > 50) {
            errorInput(txtHoTen, "Không nhập họ tên quá 50 kí tự");
            return false;
        }

        /**
         * Kiểm tra CMND
         * Rule: không được rỗng, phải đúng chuẩn của pattern
         */
        pattern = pattern.compile(PatternRegexs.REGEX_CMND);
        if (txtCMND.getText().trim().isEmpty()) {
            errorInput(txtCMND, "Vui lòng nhập CMND");
            return false;
        } else if (!pattern.matcher(txtCMND.getText().trim()).matches()) {
            errorInput(txtCMND, "CMND phải là số (từ 9 đến 11 số)");
            return false;
        }

        /**
         * Kiểm tra ngày sinh
         * Rule: nhân viên phải trên 18 tuổi
         */
        java.util.Date currentDate = new java.util.Date();
        java.util.Date chooseDate = dateChooser.getDate();

        if (currentDate.getYear() - chooseDate.getYear() < 18) {
            lblLoi.setText("Nhân viên chưa đủ 18 tuổi");
            return false;
        } else {
            lblLoi.setText("      ");
        }

        /**
         * Kiểm tra số diện thoại
         * Rule: không được rỗng, phải đúng mẫu pattern
         */
        pattern = Pattern.compile(PatternRegexs.REGEX_SODIENTHOAI);
        if (txtSoDienThoai.getText().trim().isEmpty()) {
            errorInput(txtSoDienThoai, "Vui lòng nhập số điện thoại");
            return false;
        } else if (!pattern.matcher(txtSoDienThoai.getText().trim()).matches()) {
            errorInput(txtSoDienThoai, "Số điện thoại phải là số (không quá 20 số)");
            return false;
        }

        /**
         * Kiểm tra địa chỉ
         * Rule: không được rỗng, không quá 100 kí tự
         */
        if (txtDiaChi.getText().trim().isEmpty()) {
            errorInput(txtDiaChi, "Vui lòng nhập địa chỉ");
            return false;
        } else if (txtDiaChi.getText().trim().length() > 100) {
            errorInput(txtDiaChi, "Địa chỉ không quá 100 kí tự");
            return false;
        }

        /**
         * Kiểm tra tên tài khoản
         * Rule: không được rỗng, không quá 30 kí tự
         */
        if (txtTenTaiKhoan.getText().trim().isEmpty()) {
            errorInput(txtTenTaiKhoan, "Vui lòng nhập tên tài khoản");
            return false;
        } else if (txtTenTaiKhoan.getText().trim().length() > 30) {
            errorInput(txtTenTaiKhoan, "Tên tài khoản không quá 30 kí tự");
            return false;
        }

        /**
         * Nếu trạng thái là chỉnh sửa thì không thay đổi mật khẩu
         * Nếu trạng thái là không chỉnh sửa thì lưu lại mật khẩu
         */
        if (!isChinhSua) {
            /**
             * Kiểm tra mật khẩu
             * Rule: không được rỗng, không quá 128 kí tự
             */
            if (String.valueOf(txtMatKhau.getPassword()).trim().isEmpty()) {
                errorInput(txtMatKhau, "Vui lòng nhập nhập mật khẩu");
                return false;
            } else if (String.valueOf(txtMatKhau.getPassword()).trim().length() > 128) {
                errorInput(txtMatKhau, "Mật khẩu không quá 128 kí tự");
                return false;
            }

            /**
             * Kiểm tra nhập lại mật khẩu
             * Rule: không được rỗng, không quá 128 kí tự, phải trùng vs mật khẩu đã nhập
             */
            if (String.valueOf(txtNhapLaiMatKhau.getPassword()).trim().isEmpty()) {
                errorInput(txtNhapLaiMatKhau, "Vui lòng nhập nhập lại mật khẩu");
                return false;
            } else if (String.valueOf(txtNhapLaiMatKhau.getPassword()).trim().length() > 128) {
                errorInput(txtNhapLaiMatKhau, "Mật khẩu không quá 128 kí tự");
                return false;
            } else if (!String.valueOf(txtMatKhau.getPassword()).trim()
                    .equals(String.valueOf(txtNhapLaiMatKhau.getPassword()).trim())) {
                errorInput(txtNhapLaiMatKhau, "Mật khẩu không trùng khớp");
                return false;
            }
        }

        return true;
    }


    /**
     * Sự kiện khi nhập text địa chỉ
     * Nếu có lỗi thì tắt lỗi
     *
     * @return
     */
    private KeyListener txtDiaChi_KeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtDiaChi);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }


    /**
     * Sự kiện nhập text Họ tên
     * Nếu có lỗi thì tắt lỗi
     *
     * @return
     */
    private KeyListener txtHoTen_KeyListener() {
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


    /**
     * Sự kiện nhập text CMND
     * Nếu có lỗi thì tắt lỗi
     *
     * @return
     */
    private KeyListener txtCMND_KeyListener() {
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


    /**
     * Sự kiện nhập text số điện thoại
     * Nếu có lỗi thì tắt lỗi
     *
     * @return
     */
    private KeyListener txtSoDienThoai_KeyListener() {
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


    /**
     * Sự kiện nhập text tên tài khoản
     * Nếu có lỗi thì tắt lỗi
     *
     * @return
     */
    private KeyListener txtTenTaiKhoan_KeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtTenTaiKhoan);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }


    /**
     * Sự kiện nhập text Mật khẩu
     * Nếu có lỗi thì tắt lỗi
     *
     * @return
     */
    private KeyListener txtMatKhau_KeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtMatKhau);

                // Nếu người dùng chỉnh sữa mật khẩu
                if (String.valueOf(txtMatKhau.getPassword()).length() > 0)
                    isChinhSua = false;
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }


    /**
     * Sự kiện nhập text Nhập lại mật khẩu
     * Nếu có lỗi thì tắt lỗi
     *
     * @return
     */
    private KeyListener txtNhapLaiMatKhau_KeyListener() {
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


    /**
     * Generate mã nhân viên mới
     *
     * @return
     */
    private String getMaNhanVienMoi() {
        String lastID = "";
        String newID = "";

        // lấy mã nhân viên cuối trong DB
        try {
            lastID = nhanVienDAO.getMaNhanVienCuoi();
        } catch (Exception e) {
        }

        // Nếu mã nhân viên rỗng thì trả về mã mặc định
        if (lastID.isEmpty()) {
            return "NV00001";
        }

        // Generate mã nhân viên mới
        Pattern pattern = Pattern.compile(PatternRegexs.REGEX_MANHANVIEN);
        Matcher matcher = pattern.matcher(lastID);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            number++;

            newID = String.format("NV%05d", number);
        }

        return newID;
    }


    /**
     * Sự kiện nút Thoát
     *
     * @return
     */
    private ActionListener btnThoat_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhanVien = null;
                taiKhoan = null;
                NhanVienDialog.this.dispose();
            }
        };
    }


    /**
     * Sự kiện nút Lưu
     *
     * @return
     */
    private ActionListener btnLuu_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // kiểm tra dữ liệu nhập
                if (!validateData())
                    return;

                // lấy thông tin nhân viên
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

                // lấy thông tin tài khoản
                if (!isChinhSua) {
                    taiKhoan = new TaiKhoan(
                            txtTenTaiKhoan.getText().trim(),
                            String.valueOf(txtMatKhau.getPassword()),
                            cbLoaiTaiKhoan.getSelectedItem().equals("ADMIN") ? 1 : 0,
                            txtMaNV.getText().trim()
                    );
                } else taiKhoan = null;

                // đ1ong dialog
                dispose();
            }
        };
    }


    public NhanVien getNhanVien() {
        return nhanVien;
    }


    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }


    /**
     * Constructor
     *
     * @param frame
     * @param nhanVien
     * @param taiKhoan
     * @throws Exception
     */
    public NhanVienDialog(JFrame frame, NhanVien nhanVien, TaiKhoan taiKhoan) throws Exception {
        super(frame, true);
        this.nhanVien = nhanVien;
        this.taiKhoan = taiKhoan;

        // tạo kết nối db
        try {
            nhanVienDAO = NhanVienDAO.getInstance();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        /**
         * Set tiêu đề cho dialog
         * Nếu param nhanVien == null > Thêm nhân viên
         * Nếu param nhanVien != null > Cập nhật thông tin nhân viên
         */
        if (nhanVien == null) {
            tieuDe = "Thêm nhân viên";
            isChinhSua = false;
        } else {
            tieuDe = "Cập nhật thông tin nhân viên";
            isChinhSua = true;
        }

        // Tạo GUI
        prepareDialog();

        // button mặc định khi nhấn phím Enter
        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnLuu);

        // cấu hình cho dialog
        setResizable(false);
        setSize(600, 750);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
