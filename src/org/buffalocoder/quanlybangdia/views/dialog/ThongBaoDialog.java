package org.buffalocoder.quanlybangdia.views.dialog;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThongBaoDialog extends JDialog{
    public static final int OK_CANCLE_OPTION = 1;
    public static final int OK_OPTION = 2;
    public static final int CANCLE_OPTION = 3;

    private String tieuDe;
    private String noiDung;
    private int kieuThongBao;
    private int ketQua;

    private JPanel mainPanel, headerPanel, contentPanel, bottomPanel;
    private JLabel lblTieuDe, lblNoiDung;
    private JButton btnDongY, btnHuy;

    private void prepareDialog() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(MaterialDesign.BORDER_DIALOG);
        MaterialDesign.materialPanel(mainPanel);
        this.setContentPane(mainPanel);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(headerPanel);
        headerPanel.setBackground(MaterialDesign.COLOR_PRIMARY);
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 40));
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        Box bx1 = Box.createVerticalBox();
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(bx1);
        headerPanel.add(Box.createHorizontalGlue());

        lblTieuDe = new JLabel(tieuDe);
        MaterialDesign.materialLabel(lblTieuDe);
        lblTieuDe.setFont(MaterialDesign.FONT_TITLE_ALERT);
        lblTieuDe.setForeground(MaterialDesign.COLOR_TEXT);
        bx1.add(Box.createVerticalStrut(10));
        bx1.add(lblTieuDe);
        bx1.add(Box.createVerticalStrut(20));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(contentPanel);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        Box bx3 = Box.createHorizontalBox();
        contentPanel.add(bx3);

        lblNoiDung = new JLabel(noiDung);
        MaterialDesign.materialLabel(lblNoiDung);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblNoiDung);
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(Box.createHorizontalGlue());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(bottomPanel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        Box bx2 = Box.createHorizontalBox();
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(bx2);
        bottomPanel.add(Box.createVerticalStrut(10));

        bx2.add(Box.createHorizontalGlue());

        btnHuy = new JButton("Huỷ");
        btnHuy.setPreferredSize(new Dimension(100, 40));
        btnHuy.setMaximumSize(new Dimension(100, 40));
        MaterialDesign.materialButton(btnHuy);
        btnHuy.setBackground(MaterialDesign.COLOR_ERROR);
        btnHuy.addActionListener(btnHuy_Click());
        if (kieuThongBao == 1){
            bx2.add(btnHuy);
            bx2.add(Box.createHorizontalStrut(10));
        }

        btnDongY = new JButton("Đồng ý");
        btnDongY.setPreferredSize(btnHuy.getPreferredSize());
        MaterialDesign.materialButton(btnDongY);
        btnDongY.setBackground(MaterialDesign.COLOR_PRIMARY);
        btnDongY.addActionListener(btnDongY_Click());
        btnDongY.setMaximumSize(new Dimension(100, 40));
        bx2.add(btnDongY);
        bx2.add(Box.createHorizontalStrut(20));
    }

    private ActionListener btnHuy_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ketQua = CANCLE_OPTION;
                dispose();
            }
        };
    }

    private ActionListener btnDongY_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ketQua = OK_OPTION;
                dispose();
            }
        };
    }

    public int getKetQua() {
        return ketQua;
    }

    public ThongBaoDialog(JFrame frame, String tieuDe, String noiDung, int kieuThongBao){
        super(frame, true);

        this.tieuDe = tieuDe;
        this.kieuThongBao = kieuThongBao;

        this.noiDung = String.format("<html>%s</html>", noiDung);
        this.noiDung = this.noiDung.replaceAll("\n", "<br>");

        prepareDialog();

        JRootPane rootPane = SwingUtilities.getRootPane(this);
        rootPane.setDefaultButton(btnDongY);

        setResizable(false);
        setSize(400, 220);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

