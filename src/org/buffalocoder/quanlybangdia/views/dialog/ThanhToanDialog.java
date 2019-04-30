package org.buffalocoder.quanlybangdia.views.dialog;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.PatternRegexs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

public class ThanhToanDialog extends JDialog {
    private static ThongBaoDialog thongBaoDialog;
    private int soLuong;
    private String tenKhachHang;
    private String tenBangDia;

    private JPanel mainPanel, headerPanel, contentPanel, bottomPanel;
    private JLabel lblLoi, lblSoLuongHienTai, lblSoLuongMuonThanhToan, lblTieuDe, lblSoLuongConLai,
            lblTenKhachHang, lblTenBangDia;
    private JTextField txtSoLuongHienTai, txtSoLuongMuonThanhToan, txtSoLuongConLai, txtTenKhachHang, txtTenBangDia;
    private JButton btnThanhToan, btnThanhToanHet, btnHuy;


    /**
     * Tạo GUI
     */
    private void prepareDialog() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(MaterialDesign.BORDER_DIALOG);
        MaterialDesign.materialPanel(mainPanel);
        getContentPane().add(mainPanel);

        //========== HEADER PANEL ==========//
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), 60));
        headerPanel.setBackground(MaterialDesign.COLOR_PRIMARY);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        lblTieuDe = new JLabel("Thanh toán");
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


        lblTenKhachHang = new JLabel("Tên khách hàng");
        lblTenKhachHang.setPreferredSize(new Dimension(180, 30));
        MaterialDesign.materialLabel(lblTenKhachHang);
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblTenKhachHang);

        txtTenKhachHang = new JTextField(tenKhachHang);
        MaterialDesign.materialTextField(txtTenKhachHang);
        txtTenKhachHang.setPreferredSize(new Dimension(150, 30));
        txtTenKhachHang.setEditable(false);
        bx1.add(txtTenKhachHang);
        bx1.add(Box.createHorizontalStrut(20));

        lblTenBangDia = new JLabel("Tên băng đĩa");
        lblTenBangDia.setPreferredSize(lblTenKhachHang.getPreferredSize());
        MaterialDesign.materialLabel(lblTenBangDia);
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblTenBangDia);

        txtTenBangDia = new JTextField(tenBangDia);
        MaterialDesign.materialTextField(txtTenBangDia);
        txtTenBangDia.setPreferredSize(txtTenKhachHang.getPreferredSize());
        txtTenBangDia.setEditable(false);
        bx2.add(txtTenBangDia);
        bx2.add(Box.createHorizontalStrut(20));

        lblSoLuongHienTai = new JLabel("Số lượng hiện tại");
        lblSoLuongHienTai.setPreferredSize(lblTenKhachHang.getPreferredSize());
        MaterialDesign.materialLabel(lblSoLuongHienTai);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblSoLuongHienTai);

        txtSoLuongHienTai = new JTextField(String.valueOf(soLuong));
        MaterialDesign.materialTextField(txtSoLuongHienTai);
        txtSoLuongHienTai.setPreferredSize(txtTenKhachHang.getPreferredSize());
        txtSoLuongHienTai.setEditable(false);
        bx3.add(txtSoLuongHienTai);
        bx3.add(Box.createHorizontalStrut(20));

        lblSoLuongMuonThanhToan = new JLabel("Số lượng thanh toán");
        lblSoLuongMuonThanhToan.setPreferredSize(lblTenKhachHang.getPreferredSize());
        MaterialDesign.materialLabel(lblSoLuongMuonThanhToan);
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblSoLuongMuonThanhToan);

        txtSoLuongMuonThanhToan = new JTextField();
        MaterialDesign.materialTextField(txtSoLuongMuonThanhToan);
        txtSoLuongMuonThanhToan.setPreferredSize(txtTenKhachHang.getPreferredSize());
        txtSoLuongMuonThanhToan.addKeyListener(txtSoLuongMuonXoa_KeyListener());
        bx4.add(txtSoLuongMuonThanhToan);
        bx4.add(Box.createHorizontalStrut(20));

        lblSoLuongConLai = new JLabel("Số lượng còn lại");
        lblSoLuongConLai.setPreferredSize(lblTenKhachHang.getPreferredSize());
        MaterialDesign.materialLabel(lblSoLuongConLai);
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblSoLuongConLai);

        txtSoLuongConLai = new JTextField(String.valueOf(soLuong));
        txtSoLuongConLai.setEditable(false);
        txtSoLuongConLai.setPreferredSize(txtTenKhachHang.getPreferredSize());
        MaterialDesign.materialTextField(txtSoLuongConLai);
        bx5.add(txtSoLuongConLai);
        bx5.add(Box.createHorizontalStrut(20));

        lblLoi = new JLabel(" ");
        MaterialDesign.materialLabel(lblLoi);
        lblLoi.setForeground(MaterialDesign.COLOR_ERROR);
        bx6.add(lblLoi);


        //========== BOTTOM PANEL ==========//
        bottomPanel = new JPanel(new GridLayout(1, 3, 2, 0));
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        btnHuy = new JButton("Huỷ");
        btnHuy.setPreferredSize(new Dimension(250, 50));
        MaterialDesign.materialButton(btnHuy);
        btnHuy.setBackground(MaterialDesign.COLOR_ERROR);
        btnHuy.addActionListener(btnHuy_Click());
        bottomPanel.add(btnHuy);

        btnThanhToanHet = new JButton("Thanh toán hết");
        MaterialDesign.materialButton(btnThanhToanHet);
        btnThanhToanHet.addActionListener(btnThanhToanHet_Click());
        bottomPanel.add(btnThanhToanHet);

        btnThanhToan = new JButton("Thanh toán");
        MaterialDesign.materialButton(btnThanhToan);
        btnThanhToan.addActionListener(btnThanhToan_Click());
        bottomPanel.add(btnThanhToan);
    }


    /**
     * Thông báo lỗi khi nhập sai
     *
     * @param textField
     * @param message
     */
    private void errorInput(JTextField textField, String message) {
        textField.setBorder(MaterialDesign.BORDER_ERROR);
        textField.requestFocus();
        textField.selectAll();

        lblLoi.setText(message);
    }


    /**
     * Tắt thông báo lỗi khi nhập sai
     *
     * @param textField
     */
    private void unErrorInput(JTextField textField) {
        if (!lblLoi.getText().isEmpty()) {
            MaterialDesign.materialTextField(textField);
            lblLoi.setText(" ");
        }
    }


    private boolean validateData() {
        Pattern pattern = null;
        /**
         * Kiểm tra số lượng
         * Rule: Số lượng không được rỗng, phải là số nguyên dương > 0, và giới hạn là 6 số
         */
        pattern = Pattern.compile(PatternRegexs.REGEX_SO);
        if (txtSoLuongMuonThanhToan.getText().trim().isEmpty()) {
            errorInput(txtSoLuongMuonThanhToan, "Vui lòng nhập số lượng");
            return false;
        } else if (!pattern.matcher(txtSoLuongMuonThanhToan.getText().trim()).matches()) {
            errorInput(txtSoLuongMuonThanhToan, "Số lượng phải là số nguyên");
            return false;
        } else if (txtSoLuongMuonThanhToan.getText().trim().length() >= 6) {
            errorInput(txtSoLuongMuonThanhToan, "Số lượng quá lớn");
            return false;
        } else if (Integer.parseInt(txtSoLuongMuonThanhToan.getText().trim()) <= 0) {
            errorInput(txtSoLuongMuonThanhToan, "Số lượng phải lớn hơn 0");
            return false;
        } else if (Integer.parseInt(txtSoLuongMuonThanhToan.getText().trim()) > soLuong) {
            errorInput(txtSoLuongMuonThanhToan, "Số lượng muốn xoá lớn hơn số lượng tồn");
            return false;
        }

        return true;
    }


    /**
     * Sự kiện button Huỷ
     *
     * @return
     */
    private ActionListener btnHuy_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soLuong = -1;
                dispose();
            }
        };
    }


    /**
     * Sự kiện button Đồng ý
     *
     * @return
     */
    private ActionListener btnThanhToan_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // kiểm tra dữ liệu
                if (!validateData())
                    return;

                thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        String.format("Bạn có muốn thanh toán %s băng đĩa trong hoá đơn này không ?", txtSoLuongMuonThanhToan.getText().trim()),
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION) {
                    soLuong = soLuong - Integer.parseInt(txtSoLuongMuonThanhToan.getText().trim());
                    dispose();
                }
            }
        };
    }


    /**
     * Sự kiện button Đồng ý
     *
     * @return
     */
    private ActionListener btnThanhToanHet_Click() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        "Bạn có muốn thanh toán hết hoá đơn này không ?",
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION) {
                    soLuong = 0;
                    dispose();
                }
            }
        };
    }


    /**
     * Sự kiện khi nhập text số lượng
     *
     * @return
     */
    private KeyListener txtSoLuongMuonXoa_KeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                unErrorInput(txtSoLuongMuonThanhToan);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    int soLuongMuonXoa = Integer.parseInt(txtSoLuongMuonThanhToan.getText().trim());

                    if (!validateData()) {
                        txtSoLuongConLai.setText(String.valueOf(soLuong));
                        return;
                    }

                    txtSoLuongConLai.setText(String.valueOf(soLuong - soLuongMuonXoa));
                } catch (Exception ex) {
                    txtSoLuongConLai.setText(String.valueOf(soLuong));
                }
            }
        };
    }


    /**
     * Lấy kết quả người dùng chọn
     *
     * @return
     */
    public int getKetQua() {
        return soLuong;
    }


    /**
     * Constructor
     *
     * @param frame
     * @param soLuong
     */
    public ThanhToanDialog(JFrame frame, String tenKhachHang, String tenBangDia, int soLuong) {
        super(frame, true);

        this.tenKhachHang = tenKhachHang;
        this.tenBangDia = tenBangDia;
        this.soLuong = soLuong;

        prepareDialog();

        setResizable(false);
        setSize(550, 430);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
