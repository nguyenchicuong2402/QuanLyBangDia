package org.buffalocoder.quanlybangdia.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.custom.CustomTabbedPanelUI;
import org.buffalocoder.quanlybangdia.views.tabbed.*;

public class MainForm extends JFrame {
    //========== CONSTANT ==========//
    private final int WIDTH = 1300;
    private final int HEIGHT = 800;
    private final String TITLE = "Quản lý băng đĩa";
    private final boolean IS_ADMIN = DangNhap.taiKhoan.getLoaiTaiKhoan() == 1;

    //========= UI ===========//
    private JPanel mainPanel, topPanel, contentPanel, userPanel, menuPanel, logoutPanel;
    private JTabbedPane menuTabbed;
    private CustomTabbedPanelUI customTabbedPanelUI;
    private JMenuBar menuBar;
    private JMenu menuCaiDat;
    private JLabel lblTitle, lblDangXuat;
    private JButton btnDangXuat;

    private TrangChuTabbed trangChuTabbed;
    private QuanLyChoThueTabbed quanLyChoThueTabbed;
    private QuanLyBangDiaTabbed quanLyBangDiaTabbed;
    private QuanLyKhachHangTabbed quanLyKhachHangTabbed;
    private QuanLyNhanVienTabbed quanLyNhanVienTabbed;
    private ThongKeTabbed thongKeTabbed;
    private CaiDatTabbed caiDatTabbed;

    private void prepareUI(){
        /*========== MAIN PANEL =========*/
        mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        /*========== TOP PANEL ==========*/
        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(this.getWidth(), 80));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // TITLE
        lblTitle = new JLabel("BUFFALO CODER");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setPreferredSize(new Dimension(294, topPanel.getHeight()));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(MaterialDesign.COLOR_DARK);
        MaterialDesign.materialLabel(lblTitle);
        lblTitle.setForeground(MaterialDesign.COLOR_TEXT);
        lblTitle.setFont(MaterialDesign.FONT_TITLE_2);
        topPanel.add(lblTitle, BorderLayout.WEST);

        // menu panel
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder());
        MaterialDesign.materialPanel(menuPanel);
        menuPanel.setBackground(MaterialDesign.COLOR_DARK);
        topPanel.add(menuPanel, BorderLayout.CENTER);

        menuBar = new JMenuBar();
        menuBar.setBackground(MaterialDesign.COLOR_DARK);
        menuBar.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(menuBar);

        menuCaiDat = new JMenu("Cài đặt");
        menuCaiDat.setForeground(MaterialDesign.COLOR_TEXT);
        menuCaiDat.setPreferredSize(new Dimension(80, 20));
        menuCaiDat.setHorizontalTextPosition(SwingConstants.CENTER);
        menuBar.add(menuCaiDat);

        // logout panel
        logoutPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(logoutPanel);
        logoutPanel.setBackground(MaterialDesign.COLOR_DARK);
        topPanel.add(logoutPanel, BorderLayout.EAST);

        btnDangXuat = new JButton(MaterialDesign.ICON_DANGXUAT);
        MaterialDesign.materialButton(btnDangXuat);
        btnDangXuat.setBackground(MaterialDesign.COLOR_DARK);
        btnDangXuat.setPreferredSize(new Dimension(80, 50));
        btnDangXuat.addActionListener(btnDangXuat_Click());
        logoutPanel.add(btnDangXuat, BorderLayout.CENTER);

        /*========== MENU PANEL =========*/
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(MaterialDesign.COLOR_SECONDARY);
        contentPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // custom tabbed panel
        customTabbedPanelUI = new CustomTabbedPanelUI();
        customTabbedPanelUI.setWidth(120);
        customTabbedPanelUI.setHeight(80);
        customTabbedPanelUI.setMargin(10, 0);
        customTabbedPanelUI.setColorSelected(MaterialDesign.COLOR_PRIMARY);
        customTabbedPanelUI.setColorDeselected(MaterialDesign.COLOR_SECONDARY);

        // tabbed panel
        menuTabbed = new JTabbedPane(JTabbedPane.LEFT);
        menuTabbed.setUI(customTabbedPanelUI);
        menuTabbed.setFont(MaterialDesign.FONT_DEFAULT);
        menuTabbed.addTab("Trang chủ", trangChuTabbed = new TrangChuTabbed());
        menuTabbed.addTab("Danh sách đang thuê", quanLyChoThueTabbed = new QuanLyChoThueTabbed())  ;
        menuTabbed.addTab(IS_ADMIN ? "Quản lý băng đĩa" : "Danh sách băng đĩa",
                quanLyBangDiaTabbed = new QuanLyBangDiaTabbed());
        menuTabbed.addTab("Quản lý khách hàng",
                quanLyKhachHangTabbed = new QuanLyKhachHangTabbed());

        if (IS_ADMIN)
            menuTabbed.addTab("Quản lý nhân viên",
                    quanLyNhanVienTabbed = new QuanLyNhanVienTabbed());

        menuTabbed.addTab("Thống kê", thongKeTabbed = new ThongKeTabbed());
        menuTabbed.addTab("Cài đặt", caiDatTabbed = new CaiDatTabbed());
        menuTabbed.addChangeListener(menuTabbed_Change());

        contentPanel.add(menuTabbed);
    }

    private ActionListener btnDangXuat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DangNhap();
            }
        };
    }

    private ChangeListener menuTabbed_Change(){
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                quanLyChoThueTabbed.refreshTable();
                quanLyBangDiaTabbed.refreshTable();
                quanLyKhachHangTabbed.refreshTable();

                if (IS_ADMIN)
                    quanLyNhanVienTabbed.refreshTable();

                menuTabbed.revalidate();
                menuTabbed.repaint();
            }
        };
    }

    public MainForm(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setSize(WIDTH, HEIGHT);
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setTitle(TITLE);
                setVisible(true);
            }
        });
    }
}
