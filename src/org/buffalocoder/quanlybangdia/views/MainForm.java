package org.buffalocoder.quanlybangdia.views;

import java.awt.*;

import javax.swing.*;

import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.utils.Values;
import org.buffalocoder.quanlybangdia.views.tabbed.*;

public class MainForm extends JFrame {
    //========== CONSTANT ==========//
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    private final String TITLE = "Quản lý băng đĩa";
    private final boolean IS_ADMIN = DangNhap.taiKhoan.getLoaiTaiKhoan() == 1;

    //========= UI ===========//
    private JPanel mainPanel, topPanel, contentPanel, userPanel, menuPanel;
    private JTabbedPane menuTabbed;
    private JTextField txtSearch;
    private CustomTabbedPanelUI customTabbedPanelUI;
    private JMenuBar menuBar;
    private JMenu menuFile, menuEdit, menuView;


    /**
     * Tạo GUI
     */
    private void prepareUI(){
        /*========== MAIN PANEL =========*/
        mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);

        /*========== TOP PANEL ==========*/
        topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(this.getWidth(), 40));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // menu panel
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder());
        topPanel.add(menuPanel);

        menuBar = new JMenuBar();
        menuBar.setBackground(Values.COLOR_PRIMARY);
        topPanel.add(menuBar);

        menuFile = new JMenu("File");
        menuFile.setForeground(Values.COLOR_TEXT);
        menuFile.setPreferredSize(new Dimension(60, topPanel.getHeight()));
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuEdit.setForeground(Values.COLOR_TEXT);
        menuEdit.setPreferredSize(menuFile.getPreferredSize());
        menuBar.add(menuEdit);

        menuView = new JMenu("View");
        menuView.setForeground(Values.COLOR_TEXT);
        menuView.setPreferredSize(menuFile.getPreferredSize());
        menuBar.add(menuView);

        /*========== MENU PANEL =========*/
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Values.COLOR_SECONDARY);
        contentPanel.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // custom tabbed panel
        customTabbedPanelUI = new CustomTabbedPanelUI();
        customTabbedPanelUI.setWidth(120);
        customTabbedPanelUI.setHeight(80);
        customTabbedPanelUI.setMargin(10, 0);
        customTabbedPanelUI.setColorSelected(Values.COLOR_PRIMARY);
        customTabbedPanelUI.setColorDeselected(Values.COLOR_DARK);

        // tabbed panel
        menuTabbed = new JTabbedPane(JTabbedPane.LEFT);
        menuTabbed.setUI(customTabbedPanelUI);
        menuTabbed.setFont(Values.FONT_PLAIN_DEFAULT);
        menuTabbed.addTab("Trang chủ", new TrangChuTabbed());
        menuTabbed.addTab("Cho thuê", new QuanLyChoThueTabbed())  ;
        menuTabbed.addTab(IS_ADMIN ? "Quản lý băng đĩa" : "Danh sách băng đĩa", new QuanLyBangDiaTabbed());
        menuTabbed.addTab("Quản lý khách hàng", new QuanLyKhachHangTabbed());

        if (IS_ADMIN)
            menuTabbed.addTab("Quản lý nhân viên", new QuanLyNhanVienTabbed());

        menuTabbed.addTab("Thống kê", new ThongKeTabbed());
        menuTabbed.addTab("Cài đặt", new CaiDatTabbed());

        contentPanel.add(menuTabbed);
    }

    /**
     * Constructor empty
     */
    public MainForm(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();
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
