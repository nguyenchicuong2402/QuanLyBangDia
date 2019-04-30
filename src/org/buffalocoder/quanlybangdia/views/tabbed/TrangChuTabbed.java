package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;
import org.buffalocoder.quanlybangdia.models.NhanVien;
import org.buffalocoder.quanlybangdia.utils.Formats;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.views.DangNhap;
import org.buffalocoder.quanlybangdia.views.custom.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Random;

public class TrangChuTabbed extends JPanel {

    private static final String PATH = "resources/images/";
    private static final String IMAGES[] =
            {"cd001.jpg", "cd002.jpg", "cd003.jpg", "cd004.jpg", "cd005.jpg", "cd006.png", "cd007.jpg"};

    private static NhanVienDAO nhanVienDAO;
    private static NhanVien nhanVien;
    private Random random = new Random();


    private JLabel lblLoiChao, lblLoiChuc, lblMaNV_1, lblMaNV_2, lblTenNguoiDung_1,
            lblTenNguoiDung_2, lblHoTen_1, lblHoTen_2, lblGioiTinh_1, lblGioiTinh_2,
            lblNgaySinh_1, lblNgaySinh_2, lblCMND_1, lblCMND_2, lblSoDienThoai_1,
            lblSoDienThoai_2, lblDiaChi_1, lblDiaChi_2, lblMoTa_1, lblMoTa_2,
            lblTieuDeThongTin;
    private JPanel headerPanel, leftPanel, rightPanel;
    private ImagePanel imagePanel;


    /**
     * Tạo GUI
     */
    private void prepareUI() {
        this.setLayout(new BorderLayout());
        MaterialDesign.materialPanel(this);

        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(headerPanel);
        this.add(headerPanel, BorderLayout.NORTH);

        JPanel innerHeaderPanel = new JPanel();
        innerHeaderPanel.setLayout(new BoxLayout(innerHeaderPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(innerHeaderPanel);
        innerHeaderPanel.setBackground(MaterialDesign.COLOR_CARD);
        headerPanel.add(Box.createHorizontalStrut(10));
        headerPanel.add(innerHeaderPanel);
        headerPanel.add(Box.createHorizontalStrut(10));

        Box boxLoiChao = Box.createVerticalBox();
        innerHeaderPanel.add(boxLoiChao);

        Box boxLoiChao1 = Box.createHorizontalBox();
        boxLoiChao.add(Box.createVerticalStrut(50));
        boxLoiChao.add(boxLoiChao1);

        Box boxLoiChao2 = Box.createHorizontalBox();
        boxLoiChao.add(Box.createVerticalStrut(20));
        boxLoiChao.add(boxLoiChao2);
        boxLoiChao.add(Box.createVerticalStrut(50));


        lblLoiChao = new JLabel();
        lblLoiChao.setHorizontalAlignment(JLabel.LEFT);
        MaterialDesign.materialLabel(lblLoiChao);
        lblLoiChao.setFont(MaterialDesign.FONT_TITLE_2);
        boxLoiChao1.add(Box.createHorizontalStrut(10));
        boxLoiChao1.add(lblLoiChao);

        String loiChuc = "Chúc 1 ngày làm việc vui vẻ";
        lblLoiChuc = new JLabel(loiChuc, JLabel.LEFT);
        MaterialDesign.materialLabel(lblLoiChuc);
        lblLoiChuc.setFont(MaterialDesign.FONT_DEFAULT_ITALIC);
        boxLoiChao2.add(Box.createHorizontalStrut(10));
        boxLoiChao2.add(lblLoiChuc);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(leftPanel);
        leftPanel.setBackground(MaterialDesign.COLOR_BACKGROUND);
        leftPanel.setPreferredSize(new Dimension(700, 500));
        leftPanel.setMaximumSize(new Dimension(700, 500));
        this.add(leftPanel, BorderLayout.WEST);

        JPanel innerLeftPanel = new JPanel();
        innerLeftPanel.setLayout(new BoxLayout(innerLeftPanel, BoxLayout.Y_AXIS));
        MaterialDesign.materialPanel(innerLeftPanel);
        innerLeftPanel.setBackground(MaterialDesign.COLOR_CARD);
        innerLeftPanel.setPreferredSize(new Dimension(700, 500));
        innerLeftPanel.setMaximumSize(new Dimension(700, 500));
        leftPanel.add(Box.createHorizontalStrut(10));
        leftPanel.add(innerLeftPanel);
        leftPanel.add(Box.createHorizontalStrut(10));

        Box box = Box.createVerticalBox();
        innerLeftPanel.add(Box.createVerticalStrut(10));
        innerLeftPanel.add(box);
        innerLeftPanel.add(Box.createVerticalStrut(10));

        Box boxTieuDe = Box.createHorizontalBox();
        box.add(boxTieuDe);
        box.add(Box.createVerticalStrut(20));

        Box boxThongTin = Box.createVerticalBox();
        box.add(boxThongTin);

        Box bx1 = Box.createHorizontalBox();
        boxThongTin.add(bx1);

        Box bx2 = Box.createHorizontalBox();
        boxThongTin.add(bx2);

        Box bx3 = Box.createHorizontalBox();
        boxThongTin.add(bx3);

        Box bx4 = Box.createHorizontalBox();
        boxThongTin.add(bx4);

        Box bx5 = Box.createHorizontalBox();
        boxThongTin.add(bx5);

        Box bx6 = Box.createHorizontalBox();
        boxThongTin.add(bx6);

        Box bx7 = Box.createHorizontalBox();
        boxThongTin.add(bx7);

        Box bx8 = Box.createHorizontalBox();
        boxThongTin.add(bx8);

        Box bx9 = Box.createHorizontalBox();
        boxThongTin.add(bx9);

        lblTieuDeThongTin = new JLabel("Thông tin nhân viên");
        MaterialDesign.materialLabel(lblTieuDeThongTin);
        lblTieuDeThongTin.setFont(MaterialDesign.FONT_TITLE_2);
        boxTieuDe.add(Box.createHorizontalGlue());
        boxTieuDe.add(lblTieuDeThongTin);
        boxTieuDe.add(Box.createHorizontalGlue());

        lblMaNV_1 = new JLabel("Mã nhân viên:");
        MaterialDesign.materialLabel(lblMaNV_1);
        lblMaNV_1.setPreferredSize(new Dimension(200, 30));
        lblMaNV_1.setMaximumSize(new Dimension(200, 30));
        bx1.add(Box.createHorizontalStrut(20));
        bx1.add(lblMaNV_1);

        lblMaNV_2 = new JLabel(nhanVien.getMaNhanVien());
        MaterialDesign.materialLabel(lblMaNV_2);
        bx1.add(lblMaNV_2);
        bx1.add(Box.createHorizontalGlue());

        lblTenNguoiDung_1 = new JLabel("Tên người dùng: ");
        MaterialDesign.materialLabel(lblTenNguoiDung_1);
        lblTenNguoiDung_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblTenNguoiDung_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx2.add(Box.createHorizontalStrut(20));
        bx2.add(lblTenNguoiDung_1);

        lblTenNguoiDung_2 = new JLabel(DangNhap.taiKhoan.getTenTaiKhoan());
        MaterialDesign.materialLabel(lblTenNguoiDung_2);
        bx2.add(lblTenNguoiDung_2);
        bx2.add(Box.createHorizontalGlue());

        lblHoTen_1 = new JLabel("Họ tên: ");
        MaterialDesign.materialLabel(lblHoTen_1);
        lblHoTen_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblHoTen_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx3.add(Box.createHorizontalStrut(20));
        bx3.add(lblHoTen_1);

        lblHoTen_2 = new JLabel(nhanVien.getHoTen());
        MaterialDesign.materialLabel(lblHoTen_2);
        bx3.add(lblHoTen_2);
        bx3.add(Box.createHorizontalGlue());

        lblGioiTinh_1 = new JLabel("Giới tính: ");
        MaterialDesign.materialLabel(lblGioiTinh_1);
        lblGioiTinh_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblGioiTinh_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx4.add(Box.createHorizontalStrut(20));
        bx4.add(lblGioiTinh_1);

        lblGioiTinh_2 = new JLabel(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
        MaterialDesign.materialLabel(lblGioiTinh_2);
        bx4.add(lblGioiTinh_2);
        bx4.add(Box.createHorizontalGlue());

        lblNgaySinh_1 = new JLabel("Ngày sinh: ");
        MaterialDesign.materialLabel(lblNgaySinh_1);
        lblNgaySinh_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblNgaySinh_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx5.add(Box.createHorizontalStrut(20));
        bx5.add(lblNgaySinh_1);

        lblNgaySinh_2 = new JLabel(Formats.DATE_FORMAT.format(nhanVien.getNgaySinh()));
        MaterialDesign.materialLabel(lblNgaySinh_2);
        bx5.add(lblNgaySinh_2);
        bx5.add(Box.createHorizontalGlue());

        lblCMND_1 = new JLabel("CMND: ");
        MaterialDesign.materialLabel(lblCMND_1);
        lblCMND_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblCMND_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx6.add(Box.createHorizontalStrut(20));
        bx6.add(lblCMND_1);

        lblCMND_2 = new JLabel(nhanVien.getcMND());
        MaterialDesign.materialLabel(lblCMND_2);
        bx6.add(lblCMND_2);
        bx6.add(Box.createHorizontalGlue());

        lblSoDienThoai_1 = new JLabel("Số điện thoại: ");
        MaterialDesign.materialLabel(lblSoDienThoai_1);
        lblSoDienThoai_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblSoDienThoai_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx7.add(Box.createHorizontalStrut(20));
        bx7.add(lblSoDienThoai_1);

        lblSoDienThoai_2 = new JLabel(nhanVien.getSoDienThoai());
        MaterialDesign.materialLabel(lblSoDienThoai_2);
        bx7.add(lblSoDienThoai_2);
        bx7.add(Box.createHorizontalGlue());

        lblDiaChi_1 = new JLabel("Địa chỉ: ");
        MaterialDesign.materialLabel(lblDiaChi_1);
        lblDiaChi_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblDiaChi_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx8.add(Box.createHorizontalStrut(20));
        bx8.add(lblDiaChi_1);

        lblDiaChi_2 = new JLabel(nhanVien.getDiaChi());
        MaterialDesign.materialLabel(lblDiaChi_2);
        bx8.add(lblDiaChi_2);
        bx8.add(Box.createHorizontalGlue());

        lblMoTa_1 = new JLabel("Mô tả: ");
        MaterialDesign.materialLabel(lblMoTa_1);
        lblMoTa_1.setPreferredSize(lblMaNV_1.getPreferredSize());
        lblMoTa_1.setMaximumSize(lblMaNV_1.getMinimumSize());
        bx9.add(Box.createHorizontalStrut(20));
        bx9.add(lblMoTa_1);

        lblMoTa_2 = new JLabel(nhanVien.getMoTa());
        MaterialDesign.materialLabel(lblMoTa_2);
        bx9.add(lblMoTa_2);
        bx9.add(Box.createHorizontalGlue());

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(rightPanel);
        rightPanel.setBackground(MaterialDesign.COLOR_BACKGROUND);
        this.add(rightPanel, BorderLayout.CENTER);

        imagePanel = new ImagePanel(generateImagePath());
        imagePanel.setPreferredSize(new Dimension(500, 500));
        imagePanel.setMaximumSize(new Dimension(500, 500));
        MaterialDesign.materialPanel(imagePanel);
        imagePanel.setBackground(MaterialDesign.COLOR_BACKGROUND);
        rightPanel.add(imagePanel);

        refresh();
    }


    /**
     * Refresh dữ liệu
     */
    public void refresh() {
        // load dữ liệu DB
        try {
            nhanVien = nhanVienDAO.getNhanVien(DangNhap.taiKhoan.getMaNhanVien());
        } catch (Exception e) {
            return;
        }

        // hiển thị thông tin nhân viên
        lblMaNV_2.setText(nhanVien.getMaNhanVien());
        lblTenNguoiDung_2.setText(DangNhap.taiKhoan.getTenTaiKhoan());
        lblHoTen_2.setText(nhanVien.getHoTen());
        lblGioiTinh_2.setText(nhanVien.isGioiTinh() ? "Nam" : "Nữ");
        lblNgaySinh_2.setText(Formats.DATE_FORMAT.format(nhanVien.getNgaySinh()));
        lblCMND_2.setText(nhanVien.getcMND());
        lblSoDienThoai_2.setText(nhanVien.getSoDienThoai());
        lblDiaChi_2.setText(nhanVien.getDiaChi());
        lblMoTa_2.setText(nhanVien.getMoTa());

        // cập nhật lời chào theo thời gian hệ thống
        int hour = LocalTime.now().getHour();
        String loiChao = "Chào " + DangNhap.taiKhoan.getTenTaiKhoan();

        if (hour >= 6 && hour <= 10) {
            loiChao = "Chào buổi sáng ";
        } else if (hour >= 11 && hour <= 12) {
            loiChao = "Chào buổi trưa ";
        } else if (hour >= 13 && hour <= 17) {
            loiChao = "Chào buổi chiều ";
        } else {
            loiChao = "Chào buổi tối ";
        }

        lblLoiChao.setText(loiChao + nhanVien.getHoTen());

        imagePanel.setFilePath(generateImagePath());
    }


    private String generateImagePath() {
        return PATH + IMAGES[random.nextInt(IMAGES.length)];
    }


    public TrangChuTabbed() {
        try {
            nhanVienDAO = NhanVienDAO.getInstance();
            nhanVien = nhanVienDAO.getNhanVien(DangNhap.taiKhoan.getMaNhanVien());
        } catch (Exception e) {
        }

        prepareUI();
    }
}
