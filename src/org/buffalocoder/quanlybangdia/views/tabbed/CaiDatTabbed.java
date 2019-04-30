package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.XML.QuanLyXML;
import org.buffalocoder.quanlybangdia.dao.DataBaseUtils;
import org.buffalocoder.quanlybangdia.dao.TaiKhoanDAO;
import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.DangNhap;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaiDatTabbed extends JPanel {

    private QuanLyXML quanLyXML = new QuanLyXML();
    private static TaiKhoanDAO taiKhoanDAO;
    private static DataBaseUtils dataBaseUtils;
    private ThongBaoDialog thongBaoDialog;

    private JPanel contentPanel, bottomPanel, chuDePanel, doiMatKhauPanel, xoaDatabasePanel;
    private JButton btnThayDoiMatKhau, btnXoaDatabase, btnLamRong;
    private JLabel lblChuDe, lblSubChuDe, lblDoiMatKhau, lblMatKhauHienTai, lblMatKhauMoi,
            lblNhapLaiMatKhau, lblLoiDoiMatKhau, lblXoaDatabase, lblSubXoaDatabase;
    private JComboBox<String> cbChuDe;
    private JPasswordField txtMatKhauHienTai, txtMatKhauMoi, txtNhapLaiMatKhau;


    /**
     * Tạo GUI
     */
    private void prepareUI() {
        this.setLayout(new BorderLayout());
        MaterialDesign.materialPanel(this);

        //========== CONTENT PANEL ==========//
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(contentPanel);
        this.add(contentPanel, BorderLayout.CENTER);
        contentPanel.add(Box.createVerticalStrut(10));

        // chủ đề
        chuDePanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(chuDePanel);
        chuDePanel.setBackground(MaterialDesign.COLOR_CARD);
        contentPanel.add(chuDePanel);
        contentPanel.add(Box.createVerticalStrut(10));

        JPanel subChuDePanel = new JPanel();
        subChuDePanel.setLayout(new BoxLayout(subChuDePanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(subChuDePanel);
        subChuDePanel.setBackground(MaterialDesign.COLOR_CARD);
        chuDePanel.add(subChuDePanel, BorderLayout.CENTER);

        Box boxSubChuDe = Box.createVerticalBox();
        subChuDePanel.add(Box.createHorizontalStrut(20));
        subChuDePanel.add(boxSubChuDe);

        lblChuDe = new JLabel("Chủ đề");
        MaterialDesign.materialLabel(lblChuDe);
        lblChuDe.setFont(MaterialDesign.FONT_TITLE_2);
        boxSubChuDe.add(Box.createVerticalStrut(20));
        boxSubChuDe.add(lblChuDe);

        lblSubChuDe = new JLabel("Thao tác này cần phải khởi động lại ứng dụng");
        MaterialDesign.materialLabel(lblSubChuDe);
        lblSubChuDe.setFont(MaterialDesign.FONT_SMALL_ITALIC);
        boxSubChuDe.add(Box.createVerticalStrut(10));
        boxSubChuDe.add(lblSubChuDe);

        Box boxChuDe = Box.createHorizontalBox();
        chuDePanel.add(boxChuDe, BorderLayout.EAST);

        cbChuDe = new JComboBox<>(new String[]{"Red Material", "Pink Material", "Purple Material",
                "Deep Purple Material", "Indigo Material", "Blue Material",
                "Light Blue Material", "Cyan Material", "Teal Material",
                "Green Material", "Light Green Material", "Lime Material",
                "Yellow Material", "Amber Material", "Orange Material",
                "Deep Orange Material", "Brown Material", "Grey Material",
                "Blue Grey Material"});
        MaterialDesign.materialComboBox(cbChuDe);
        cbChuDe.setPreferredSize(new Dimension(300, 50));
        cbChuDe.setMaximumSize(new Dimension(300, 50));
        cbChuDe.addActionListener(cbChuDe_Change());
        boxChuDe.add(cbChuDe);
        boxChuDe.add(Box.createHorizontalStrut(20));

        // thay đổi mật khẩu
        doiMatKhauPanel = new JPanel();
        doiMatKhauPanel.setLayout(new BoxLayout(doiMatKhauPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(doiMatKhauPanel);
        doiMatKhauPanel.setBackground(MaterialDesign.COLOR_CARD);
        contentPanel.add(doiMatKhauPanel);
        contentPanel.add(Box.createVerticalStrut(DangNhap.taiKhoan.getLoaiTaiKhoan() == 1 ? 10 : 120));

        Box boxDoiMatKhau = Box.createHorizontalBox();
        doiMatKhauPanel.add(boxDoiMatKhau);

        lblDoiMatKhau = new JLabel("Thay đổi mật khẩu");
        MaterialDesign.materialLabel(lblDoiMatKhau);
        lblDoiMatKhau.setFont(MaterialDesign.FONT_TITLE_2);
        boxDoiMatKhau.add(Box.createHorizontalStrut(20));
        boxDoiMatKhau.add(lblDoiMatKhau);
        boxDoiMatKhau.add(Box.createHorizontalGlue());

        Box boxNhapMatKhau = Box.createVerticalBox();
        doiMatKhauPanel.add(Box.createVerticalStrut(50));
        doiMatKhauPanel.add(boxNhapMatKhau);

        Box boxMKHienTai = Box.createHorizontalBox();
        boxNhapMatKhau.add(boxMKHienTai);
        boxNhapMatKhau.add(Box.createVerticalStrut(10));

        Box boxMKMoi = Box.createHorizontalBox();
        boxNhapMatKhau.add(boxMKMoi);
        boxNhapMatKhau.add(Box.createVerticalStrut(10));

        Box boxNhapLai = Box.createHorizontalBox();
        boxNhapMatKhau.add(boxNhapLai);
        boxNhapMatKhau.add(Box.createVerticalStrut(10));

        Box boxLoi = Box.createHorizontalBox();
        boxNhapMatKhau.add(boxLoi);
        boxNhapMatKhau.add(Box.createVerticalStrut(10));

        Box boxButton = Box.createHorizontalBox();
        boxNhapMatKhau.add(boxButton);
        boxNhapMatKhau.add(Box.createVerticalStrut(10));

        lblMatKhauHienTai = new JLabel("Mật khẩu hiện tại");
        MaterialDesign.materialLabel(lblMatKhauHienTai);
        lblMatKhauHienTai.setPreferredSize(new Dimension(200, 30));
        boxMKHienTai.add(Box.createHorizontalStrut(200));
        boxMKHienTai.add(lblMatKhauHienTai);

        txtMatKhauHienTai = new JPasswordField();
        txtMatKhauHienTai.addKeyListener(txtMatKhauHienTai_KeyListener());
        MaterialDesign.materialTextField(txtMatKhauHienTai);
        boxMKHienTai.add(txtMatKhauHienTai);
        boxMKHienTai.add(Box.createHorizontalStrut(200));

        lblMatKhauMoi = new JLabel("Mật khẩu mới");
        MaterialDesign.materialLabel(lblMatKhauMoi);
        lblMatKhauMoi.setPreferredSize(lblMatKhauHienTai.getPreferredSize());
        boxMKMoi.add(Box.createHorizontalStrut(200));
        boxMKMoi.add(lblMatKhauMoi);

        txtMatKhauMoi = new JPasswordField();
        txtMatKhauMoi.addKeyListener(txtMatKhauMoi_KeyListener());
        MaterialDesign.materialTextField(txtMatKhauMoi);
        boxMKMoi.add(txtMatKhauMoi);
        boxMKMoi.add(Box.createHorizontalStrut(200));

        lblNhapLaiMatKhau = new JLabel("Nhập lại mật khẩu");
        MaterialDesign.materialLabel(lblNhapLaiMatKhau);
        lblNhapLaiMatKhau.setPreferredSize(lblMatKhauHienTai.getPreferredSize());
        boxNhapLai.add(Box.createHorizontalStrut(200));
        boxNhapLai.add(lblNhapLaiMatKhau);

        txtNhapLaiMatKhau = new JPasswordField();
        txtNhapLaiMatKhau.addKeyListener(txtNhapLaiMatKhau_KeyListener());
        MaterialDesign.materialTextField(txtNhapLaiMatKhau);
        boxNhapLai.add(txtNhapLaiMatKhau);
        boxNhapLai.add(Box.createHorizontalStrut(200));

        lblLoiDoiMatKhau = new JLabel(" ");
        MaterialDesign.materialLabel(lblLoiDoiMatKhau);
        lblLoiDoiMatKhau.setForeground(MaterialDesign.COLOR_ERROR);
        boxLoi.add(lblLoiDoiMatKhau);

        btnLamRong = new JButton("Làm mới");
        btnLamRong.setPreferredSize(new Dimension(200, 50));
        btnLamRong.setMaximumSize(new Dimension(200, 50));
        btnLamRong.addActionListener(btnLamMoi_Click());
        MaterialDesign.materialButton(btnLamRong);
        boxButton.add(btnLamRong);
        boxButton.add(Box.createHorizontalStrut(30));

        btnThayDoiMatKhau = new JButton("Thay đổi mật khẩu");
        btnThayDoiMatKhau.setPreferredSize(new Dimension(200, 50));
        btnThayDoiMatKhau.setMaximumSize(new Dimension(200, 50));
        btnThayDoiMatKhau.addActionListener(btnThayDoiMatKhau_Click());
        MaterialDesign.materialButton(btnThayDoiMatKhau);
        boxButton.add(btnThayDoiMatKhau);

        // xoá database
        xoaDatabasePanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(xoaDatabasePanel);
        xoaDatabasePanel.setBackground(MaterialDesign.COLOR_CARD);

        if (DangNhap.taiKhoan.getLoaiTaiKhoan() == 1)
            contentPanel.add(xoaDatabasePanel);
        contentPanel.add(Box.createVerticalStrut(10));

        JPanel subXoaDatabasePanel = new JPanel();
        subXoaDatabasePanel.setLayout(new BoxLayout(subXoaDatabasePanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(subXoaDatabasePanel);
        subXoaDatabasePanel.setBackground(MaterialDesign.COLOR_CARD);
        xoaDatabasePanel.add(subXoaDatabasePanel, BorderLayout.CENTER);

        Box boxXoaDatabase = Box.createVerticalBox();
        subXoaDatabasePanel.add(Box.createHorizontalStrut(20));
        subXoaDatabasePanel.add(boxXoaDatabase);

        lblXoaDatabase = new JLabel("Xoá dữ liệu");
        MaterialDesign.materialLabel(lblXoaDatabase);
        lblXoaDatabase.setFont(MaterialDesign.FONT_TITLE_2);
        boxXoaDatabase.add(Box.createVerticalStrut(20));
        boxXoaDatabase.add(lblXoaDatabase);

        lblSubXoaDatabase = new JLabel("Thao tác này sẽ xoá tất cả dữ liệu của bạn");
        MaterialDesign.materialLabel(lblSubXoaDatabase);
        lblSubXoaDatabase.setFont(MaterialDesign.FONT_SMALL_ITALIC);
        boxXoaDatabase.add(Box.createVerticalStrut(10));
        boxXoaDatabase.add(lblSubXoaDatabase);

        JPanel btnXoaDBPanel = new JPanel();
        btnXoaDBPanel.setLayout(new BoxLayout(btnXoaDBPanel, BoxLayout.X_AXIS));
        btnXoaDBPanel.setBackground(MaterialDesign.COLOR_CARD);
        xoaDatabasePanel.add(btnXoaDBPanel, BorderLayout.EAST);

        btnXoaDatabase = new JButton("Xoá dữ liệu");
        MaterialDesign.materialButton(btnXoaDatabase);
        btnXoaDatabase.setBackground(MaterialDesign.COLOR_ERROR);
        btnXoaDatabase.setVerticalAlignment(SwingConstants.CENTER);
        btnXoaDatabase.setPreferredSize(new Dimension(300, 70));
        btnXoaDatabase.setMaximumSize(new Dimension(300, 70));
        btnXoaDatabase.addActionListener(btnXoaDatabase_Click());
        btnXoaDBPanel.add(btnXoaDatabase);
        btnXoaDBPanel.add(Box.createHorizontalStrut(20));

        contentPanel.add(Box.createVerticalStrut(100));

        //========== BOTTOM PANEL ==========//
    }


    /**
     * Thông báo lỗi nhập text
     *
     * @param textField
     * @param message
     */
    private void errorInput(JTextField textField, String message) {
        lblLoiDoiMatKhau.setText(message);
        textField.setBorder(MaterialDesign.BORDER_ERROR);
        textField.requestFocus();
        textField.selectAll();
    }


    /**
     * Tắt thông báo lỗi nhập text
     *
     * @param textField
     */
    private void unErrorInput(JTextField textField) {
        if (!lblLoiDoiMatKhau.getText().isEmpty()) {
            MaterialDesign.materialTextField(textField);
            lblLoiDoiMatKhau.setText("    ");
        }
    }


    /**
     * Sự kiện button xoá database
     *
     * @return
     */
    private ActionListener btnXoaDatabase_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // thông báo cho người dùng
                thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        "Thao tác này sẽ xoá toàn bộ dữ liệu của bạn\nBạn có muốn tiếp tục không ?",
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                // nếu người dùng đồng ý thì xoá dữ liệu
                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION) {
                    try {
                        dataBaseUtils.resetDatabase();

                        thongBaoDialog = new ThongBaoDialog(
                                new JFrame(),
                                "Thông báo",
                                "Xoá dữ liệu thành công\nVui lòng khởi động lại phần mềm",
                                ThongBaoDialog.OK_OPTION
                        );

                        quanLyXML.ghiNhoTaiKhoan(null);
                        System.exit(0);
                    } catch (Exception ex) {
                        thongBaoDialog = new ThongBaoDialog(
                                new JFrame(),
                                "Cảnh báo",
                                ex.getMessage(),
                                ThongBaoDialog.OK_CANCLE_OPTION
                        );
                    }
                }
            }
        };
    }


    /**
     * Sự kiện thay đổi chủ đề
     *
     * @return
     */
    private ActionListener cbChuDe_Change() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pattern pattern = Pattern.compile("^(.*) material$");
                Matcher matcher = pattern.matcher(String.valueOf(cbChuDe.getSelectedItem()).toLowerCase());

                // Lấy ID Theme và lưu vào Propertise
                if (matcher.find()) {
                    String id_color = matcher.group(1);
                    quanLyXML.setIDColor(id_color);
                }

                // Thông báo khởi động lại phần mềm
                thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Thông báo",
                        "Vui lòng khởi động lại phần mềm để cập nhật thay đổi",
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                // Nếu người dùng đồng ý khỡi động lại phần mềm
                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION)
                    System.exit(0);
            }
        };
    }


    /**
     * Sự kiện button làm mới thay đổi mật khẩu
     *
     * @return
     */
    private ActionListener btnLamMoi_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMatKhauHienTai.setText("");
                txtMatKhauMoi.setText("");
                txtNhapLaiMatKhau.setText("");

                unErrorInput(txtMatKhauHienTai);
                unErrorInput(txtMatKhauMoi);
                unErrorInput(txtNhapLaiMatKhau);
            }
        };
    }


    /**
     * Sự kiện button thay đổi mật khẩu
     *
     * @return
     */
    private ActionListener btnThayDoiMatKhau_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaiKhoan taiKhoan = DangNhap.taiKhoan;

                /**
                 * Kiểm tra mật khẩu hiện tại
                 * Rule: không được rỗng, không quá 128 kí tự, phải khớp với mật khẩu của user hiện tại
                 */
                if (String.valueOf(txtMatKhauHienTai.getPassword()).trim().isEmpty()) {
                    errorInput(txtMatKhauHienTai, "Vui lòng nhập mật khẩu");
                    return;
                } else if (String.valueOf(txtMatKhauHienTai.getPassword()).length() > 128) {
                    errorInput(txtMatKhauHienTai, "Mật khẩu không quá 128 kí tự");
                    return;
                } else if (!String.valueOf(txtMatKhauHienTai.getPassword()).equals(taiKhoan.getMatKhau())) {
                    errorInput(txtMatKhauHienTai, "Mật khẩu không đúng");
                    return;
                }

                /**
                 * Kiểm tra mật khẩu mới
                 * Rule: Không được rỗng, không quá 128 kí tự
                 */
                if (String.valueOf(txtMatKhauMoi.getPassword()).trim().isEmpty()) {
                    errorInput(txtMatKhauMoi, "Vui lòng nhập mật khẩu mới");
                    return;
                } else if (String.valueOf(txtMatKhauMoi.getPassword()).length() > 128) {
                    errorInput(txtMatKhauMoi, "Mật khẩu không quá 128 kí tự");
                    return;
                }

                /**
                 * Kiểm tra nhập lại mật khẩu mới
                 * Rule: Không được rỗng, không quá 128 kí tự, phải trùng với mật khẩu mới
                 */
                if (String.valueOf(txtNhapLaiMatKhau.getPassword()).trim().isEmpty()) {
                    errorInput(txtNhapLaiMatKhau, "Vui lòng nhập lại mật khẩu");
                    return;
                } else if (String.valueOf(txtNhapLaiMatKhau.getPassword()).length() > 128) {
                    errorInput(txtNhapLaiMatKhau, "Mật khẩu không quá 128 kí tự");
                    return;
                } else if (!String.valueOf(txtNhapLaiMatKhau.getPassword()).equals(
                        String.valueOf(txtNhapLaiMatKhau.getPassword()))) {
                    errorInput(txtNhapLaiMatKhau, "Mật khẩu không trùng khớp");
                    return;
                }

                // Lưu thay đổi mật khẩu vào db
                taiKhoan.setMatKhau(String.valueOf(txtMatKhauMoi.getPassword()));
                try {

                    // Nếu thay đổi thành công thì thông báo
                    if (taiKhoanDAO.suaTaiKhoan(taiKhoan) != null) {
                        DangNhap.taiKhoan = taiKhoan;
                        thongBaoDialog = new ThongBaoDialog(
                                new JFrame(),
                                "Thông báo",
                                "Thay đổi mật khẩu thành công",
                                ThongBaoDialog.OK_OPTION
                        );

                        txtNhapLaiMatKhau.setText("");
                        txtMatKhauMoi.setText("");
                        txtMatKhauHienTai.setText("");
                    }
                } catch (Exception ex) {
                    thongBaoDialog = new ThongBaoDialog(
                            new JFrame(),
                            "Lỗi",
                            ex.getMessage(),
                            ThongBaoDialog.OK_OPTION
                    );
                }
            }
        };
    }


    /**
     * Sự kiện nhập text mật khẩu hiện tại
     * Nếu có lỗi thì xoá
     *
     * @return
     */
    private KeyListener txtMatKhauHienTai_KeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtMatKhauHienTai);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }


    /**
     * Sự kiện nhập text mật khẩu mới
     * Nếu có lỗi thì xoá
     *
     * @return
     */
    private KeyListener txtMatKhauMoi_KeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtMatKhauMoi);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }


    /**
     * Sự kiện nhập text mật khẩu hiện tại
     * Nếu có lỗi thì xoá
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
     * Constructor
     */
    public CaiDatTabbed() {
        // tạo kết nối db
        try {
            taiKhoanDAO = TaiKhoanDAO.getInstance();
            dataBaseUtils = DataBaseUtils.getInstance();
        } catch (Exception e) {
            ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                    new JFrame(),
                    "Lỗi",
                    e.getMessage(),
                    ThongBaoDialog.OK_OPTION
            );
        }

        // Tạo GUI
        prepareUI();
    }
}
