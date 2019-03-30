package org.buffalocoder.quanlybangdia.views;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.buffalocoder.quanlybangdia.utils.Colors;
import org.buffalocoder.quanlybangdia.utils.Fonts;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.custom.CustomTabbedPanelUI;
import org.buffalocoder.quanlybangdia.views.tabbed.*;

public class MainForm extends JFrame {
    //========== CONSTANT ==========//
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    private final String TITLE = "Quản lý băng đĩa";
    private final boolean IS_ADMIN = DangNhap.taiKhoan.getLoaiTaiKhoan() == 1;

    //========= UI ===========//
    private JPanel mainPanel, topPanel, contentPanel, userPanel, menuPanel, ExitPanel;
    private JTabbedPane menuTabbed;
    private JTextField txtSearch;
    private CustomTabbedPanelUI customTabbedPanelUI;
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuView;
    private JLabel lblTitle, lblExit;

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
        //Exit Panel
        ExitPanel = new JPanel(null);
        ExitPanel.setBackground(Colors.COLOR_DARK);
        ExitPanel.setPreferredSize(new Dimension(50, 50));
        topPanel.add(ExitPanel, BorderLayout.EAST);
//       lblExit
        lblExit = new JLabel("X");
        MaterialDesign.materialLabel(lblExit);
        lblExit.setForeground(Color.red);
        lblExit.setBounds(9, 10, 30, 30);
        lblExit.addMouseListener(lblExit());
        ExitPanel.add(lblExit);
//      lblTitle
        lblTitle = new JLabel("IUH CODER");
        lblTitle.setPreferredSize(new Dimension(294, topPanel.getHeight()));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(Colors.COLOR_DARK);
        MaterialDesign.materialLabel(lblTitle);
        lblTitle.setForeground(Colors.COLOR_TEXT);
        topPanel.add(lblTitle, BorderLayout.WEST);
        // menu panel
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(menuPanel);

        menuBar = new JMenuBar();
        menuBar.setBackground(Colors.COLOR_DARK);
        topPanel.add(menuBar);

        menuFile = new JMenu("File");
        menuFile.setForeground(Colors.COLOR_TEXT);
        menuFile.setPreferredSize(new Dimension(60, topPanel.getHeight()));
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuEdit.setForeground(Colors.COLOR_TEXT);
        menuEdit.setPreferredSize(menuFile.getPreferredSize());
        menuBar.add(menuEdit);

        menuView = new JMenu("View");
        menuView.setForeground(Colors.COLOR_TEXT);
        menuView.setPreferredSize(menuFile.getPreferredSize());
        menuBar.add(menuView);

        /*========== MENU PANEL =========*/
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Colors.COLOR_DARK);
        contentPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // custom tabbed panel
        customTabbedPanelUI = new CustomTabbedPanelUI();
        customTabbedPanelUI.setWidth(120);
        customTabbedPanelUI.setHeight(80);
        customTabbedPanelUI.setMargin(10, 0);
        customTabbedPanelUI.setColorSelected(Colors.COLOR_SELECTED);
        customTabbedPanelUI.setColorDeselected(Colors.COLOR_DARK);

        // tabbed panel


        menuTabbed = new JTabbedPane(JTabbedPane.LEFT);
        menuTabbed.setUI(customTabbedPanelUI);
        menuTabbed.setFont(Fonts.DEFAULT);
        menuTabbed.addTab("Trang chủ", trangChuTabbed = new TrangChuTabbed());
        menuTabbed.addTab("Cho thuê", quanLyChoThueTabbed = new QuanLyChoThueTabbed())  ;
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

    private MouseListener lblExit(){
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }
    private ChangeListener menuTabbed_Change(){
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

            }
        };
    }

    public MainForm(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setUndecorated(true);
                setSize(WIDTH, HEIGHT);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setTitle(TITLE);
//                setUndecorated(true);
                setVisible(true);
            }
        });
    }
}
