package org.buffalocoder.quanlybangdia.views.tabbed;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;
import com.toedter.plaf.JCalendarTheme;
import org.buffalocoder.quanlybangdia.utils.Colors;
import org.buffalocoder.quanlybangdia.utils.Fonts;
import org.buffalocoder.quanlybangdia.utils.Formats;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;
import java.awt.*;

public class CaiDatTabbed extends JPanel {

    private JPanel contentPanel, bottomPanel, chuDePanel, doiMatKhauPanel, xoaDatabasePanel;
    private JButton btnLuu, btnXoaDatabase;
    private JLabel lblChuDe, lblSubChuDe, lblDoiMatKhau, lblMatKhauHienTai, lblMatKhauMoi,
            lblNhapLaiMatKhau, lblLoiDoiMatKhau, lblXoaDatabase, lblSubXoaDatabase;
    private JComboBox<String> cbChuDe;
    private JPasswordField txtMatKhauHienTai, txtMatKhauMoi, txtNhapLaiMatKhau;


    public CaiDatTabbed(){
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
        chuDePanel.setBackground(Colors.CARD);
        contentPanel.add(chuDePanel);
        contentPanel.add(Box.createVerticalStrut(10));

        JPanel subChuDePanel = new JPanel();
        subChuDePanel.setLayout(new BoxLayout(subChuDePanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(subChuDePanel);
        subChuDePanel.setBackground(Colors.CARD);
        chuDePanel.add(subChuDePanel, BorderLayout.CENTER);

        Box boxSubChuDe = Box.createVerticalBox();
        subChuDePanel.add(Box.createHorizontalStrut(20));
        subChuDePanel.add(boxSubChuDe);

        lblChuDe = new JLabel("Chủ đề");
        MaterialDesign.materialLabel(lblChuDe);
        lblChuDe.setFont(Fonts.TITLE_2);
        boxSubChuDe.add(Box.createVerticalStrut(20));
        boxSubChuDe.add(lblChuDe);

        lblSubChuDe = new JLabel("Thao tác này cần phải khởi động lại ứng dụng");
        MaterialDesign.materialLabel(lblSubChuDe);
        lblSubChuDe.setFont(Fonts.SMALL_ITALIC);
        boxSubChuDe.add(Box.createVerticalStrut(10));
        boxSubChuDe.add(lblSubChuDe);

        Box boxChuDe = Box.createVerticalBox();
        chuDePanel.add(boxChuDe, BorderLayout.EAST);

        cbChuDe = new JComboBox<>(new String[]{"Red Material", "Pink Material", "Purple Material",
                                            "Deep Purple Material", "Indigo Material", "Blue Material",
                                            "Light Blue Material", "Cyan Material", "Teal Material",
                                            "Green Material", "Light Green Material", "Lime Material",
                                            "Yellow Material", "Amber Material", "Orange Material",
                                            "Deep Orange Material", "Brown Material", "Grey Material",
                                            "Blue Grey Material"});
        MaterialDesign.materialComboBox(cbChuDe);
        cbChuDe.setPreferredSize(new Dimension(400, 30));
        boxChuDe.add(Box.createVerticalStrut(30));
        boxChuDe.add(cbChuDe);
        boxChuDe.add(Box.createVerticalStrut(30));

        // thay đổi mật khẩu
        doiMatKhauPanel = new JPanel();
        doiMatKhauPanel.setLayout(new BoxLayout(doiMatKhauPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(doiMatKhauPanel);
        doiMatKhauPanel.setBackground(Colors.CARD);
        contentPanel.add(doiMatKhauPanel);
        contentPanel.add(Box.createVerticalStrut(10));

        Box boxDoiMatKhau = Box.createHorizontalBox();
        doiMatKhauPanel.add(boxDoiMatKhau);

        lblDoiMatKhau = new JLabel("Thay đổi mật khẩu");
        MaterialDesign.materialLabel(lblDoiMatKhau);
        lblDoiMatKhau.setFont(Fonts.TITLE_2);
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

        lblMatKhauHienTai = new JLabel("Mật khẩu hiện tại");
        MaterialDesign.materialLabel(lblMatKhauHienTai);
        lblMatKhauHienTai.setPreferredSize(new Dimension(200, 30));
        boxMKHienTai.add(Box.createHorizontalStrut(200));
        boxMKHienTai.add(lblMatKhauHienTai);

        txtMatKhauHienTai = new JPasswordField();
        MaterialDesign.materialTextField(txtMatKhauHienTai);
        boxMKHienTai.add(txtMatKhauHienTai);
        boxMKHienTai.add(Box.createHorizontalStrut(200));

        lblMatKhauMoi = new JLabel("Mật khẩu mới");
        MaterialDesign.materialLabel(lblMatKhauMoi);
        lblMatKhauMoi.setPreferredSize(lblMatKhauHienTai.getPreferredSize());
        boxMKMoi.add(Box.createHorizontalStrut(200));
        boxMKMoi.add(lblMatKhauMoi);

        txtMatKhauMoi = new JPasswordField();
        MaterialDesign.materialTextField(txtMatKhauMoi);
        boxMKMoi.add(txtMatKhauMoi);
        boxMKMoi.add(Box.createHorizontalStrut(200));

        lblNhapLaiMatKhau = new JLabel("Nhập lại mật khẩu");
        MaterialDesign.materialLabel(lblNhapLaiMatKhau);
        lblNhapLaiMatKhau.setPreferredSize(lblMatKhauHienTai.getPreferredSize());
        boxNhapLai.add(Box.createHorizontalStrut(200));
        boxNhapLai.add(lblNhapLaiMatKhau);

        txtNhapLaiMatKhau = new JPasswordField();
        MaterialDesign.materialTextField(txtNhapLaiMatKhau);
        boxNhapLai.add(txtNhapLaiMatKhau);
        boxNhapLai.add(Box.createHorizontalStrut(200));

        lblLoiDoiMatKhau = new JLabel(" ");
        MaterialDesign.materialLabel(lblLoiDoiMatKhau);
        lblLoiDoiMatKhau.setForeground(Colors.ERROR);
        boxLoi.add(lblLoiDoiMatKhau);

        // xoá database
        xoaDatabasePanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(xoaDatabasePanel);
        xoaDatabasePanel.setBackground(Colors.CARD);
        contentPanel.add(xoaDatabasePanel);
        contentPanel.add(Box.createVerticalStrut(10));

        JPanel subXoaDatabasePanel = new JPanel();
        subXoaDatabasePanel.setLayout(new BoxLayout(subXoaDatabasePanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(subXoaDatabasePanel);
        subXoaDatabasePanel.setBackground(Colors.CARD);
        xoaDatabasePanel.add(subXoaDatabasePanel, BorderLayout.CENTER);

        Box boxXoaDatabase = Box.createVerticalBox();
        subXoaDatabasePanel.add(Box.createHorizontalStrut(20));
        subXoaDatabasePanel.add(boxXoaDatabase);

        lblXoaDatabase = new JLabel("Xoá database");
        MaterialDesign.materialLabel(lblXoaDatabase);
        lblXoaDatabase.setFont(Fonts.TITLE_2);
        boxXoaDatabase.add(Box.createVerticalStrut(20));
        boxXoaDatabase.add(lblXoaDatabase);

        lblSubXoaDatabase = new JLabel("Thao tác này sẽ xoá tất cả dữ liệu của bạn");
        MaterialDesign.materialLabel(lblSubXoaDatabase);
        lblSubXoaDatabase.setFont(Fonts.SMALL_ITALIC);
        boxXoaDatabase.add(Box.createVerticalStrut(10));
        boxXoaDatabase.add(lblSubXoaDatabase);

        btnXoaDatabase = new JButton("Xoá database");
        MaterialDesign.materialButton(btnXoaDatabase);
        btnXoaDatabase.setBackground(Colors.ERROR);
        btnXoaDatabase.setPreferredSize(new Dimension(300, 30));
        xoaDatabasePanel.add(btnXoaDatabase, BorderLayout.EAST);

        contentPanel.add(Box.createVerticalStrut(100));

        //========== BOTTOM PANEL ==========//
        bottomPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(bottomPanel);
        this.add(bottomPanel, BorderLayout.SOUTH);

        btnLuu = new JButton("Lưu");
        btnLuu.setPreferredSize(new Dimension(100, 50));
        MaterialDesign.materialButton(btnLuu);
        bottomPanel.add(btnLuu, BorderLayout.EAST);
    }
}
