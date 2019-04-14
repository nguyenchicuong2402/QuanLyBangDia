package org.buffalocoder.quanlybangdia.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.custom.CustomTabbedPanelUI;
import org.buffalocoder.quanlybangdia.views.dialog.ThongBaoDialog;
import org.buffalocoder.quanlybangdia.views.tabbed.*;

public class MainForm extends JFrame {
    //========== CONSTANT ==========//
    private final int WIDTH = 1300;
    private final int HEIGHT = 800;
    private final String TITLE = "Quản lý băng đĩa";
    private final boolean IS_ADMIN = DangNhap.taiKhoan.getLoaiTaiKhoan() == 1;

    //========= UI ===========//
    private JPanel mainPanel, topPanel, contentPanel, menuPanel, logoutPanel;
    private JTabbedPane menuTabbed;
    private CustomTabbedPanelUI customTabbedPanelUI;
    private JLabel lblTitle;
    private JButton btnDangXuat;

    private TrangChuTabbed trangChuTabbed;
    private QuanLyChoThueTabbed quanLyChoThueTabbed;
    private QuanLyBangDiaTabbed quanLyBangDiaTabbed;
    private QuanLyKhachHangTabbed quanLyKhachHangTabbed;
    private QuanLyNhanVienTabbed quanLyNhanVienTabbed;
    private ThongKeTabbed thongKeTabbed;
    private CaiDatTabbed caiDatTabbed;

    /**
     * Tạo GUI
     */
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
        btnDangXuat.setToolTipText("Đăng xuất");
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


    /**
     * Sự kiện button đăng xuất
     * @return
     */
    private ActionListener btnDangXuat_Click(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // dialog xác nhận đăng xuất
                ThongBaoDialog thongBaoDialog = new ThongBaoDialog(
                        new JFrame(),
                        "Cảnh báo",
                        "Bạn có muốn đăng xuất khỏi phiên người dùng này không ?",
                        ThongBaoDialog.OK_CANCLE_OPTION
                );

                // nếu người dùng đồng ý đang xuất
                if (thongBaoDialog.getKetQua() == ThongBaoDialog.OK_OPTION){
                    setVisible(false);
                    new DangNhap();
                }
            }
        };
    }


    /**
     * Sự kiện khi chọn 1 tab
     * @return
     */
    private ChangeListener menuTabbed_Change(){
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try{
                    quanLyChoThueTabbed.refresh();
                    quanLyBangDiaTabbed.refresh();
                    quanLyKhachHangTabbed.refresh();
                    thongKeTabbed.refresh();
                    trangChuTabbed.refresh();

                    if (IS_ADMIN)
                        quanLyNhanVienTabbed.refresh();
                }catch (Exception ex){
                    return;
                }

                menuTabbed.revalidate();
                menuTabbed.repaint();
            }
        };
    }


    /**
     * Constructor
     */
    public MainForm(){
        // hiển thị form
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                prepareUI();

                setSize(WIDTH, HEIGHT);
                setMinimumSize(new Dimension(WIDTH, HEIGHT));
                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setTitle(TITLE);
                setVisible(true);
            }
        });
    }
}
