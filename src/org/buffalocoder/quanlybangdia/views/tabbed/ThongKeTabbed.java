package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachChoThue;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;
import java.awt.*;

public class ThongKeTabbed extends JPanel {
    private static DanhSachChoThue danhSachChoThue;
    private static DanhSachBangDia danhSachBangDia;

    private JPanel doanhThuPanel, thueQuaHanPanel, bangDiaThueNhieuPanel, tinhTrangPanel,
            khachHangThanThietPanel;
    private JLabel lblTieuDeDoanhThu, lblTieuDeThueQuaHan, lblTieuDeBangDiaThueNhieu,
                lblTieuDeTinhTrang, lblDoanhThu, lblTongSoBangDia_1, lblTongSoBangDia_2,
                lblTongSoBangDiaDaThue_1, lblTongSoBangDiaDaThue_2, lblTieuDeKhachHangTT;
    private JList<String> lstBangDiaThueNhieu;


    private void prepareUI(){
        this.setLayout(new GridLayout(2, 3, 10, 10));
        MaterialDesign.materialPanel(this);

        // doanh thu panel
        doanhThuPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(doanhThuPanel);
        doanhThuPanel.setBackground(MaterialDesign.COLOR_CARD);
        this.add(doanhThuPanel);

        lblTieuDeDoanhThu = new JLabel("Doanh thu tuần", JLabel.CENTER);
        MaterialDesign.materialLabel(lblTieuDeDoanhThu);
        lblTieuDeDoanhThu.setFont(MaterialDesign.FONT_TITLE_1);
        doanhThuPanel.add(lblTieuDeDoanhThu, BorderLayout.NORTH);

        lblDoanhThu = new JLabel(String.valueOf(danhSachChoThue.tongDoanhThu()), JLabel.CENTER);
        MaterialDesign.materialLabel(lblDoanhThu);
        lblDoanhThu.setFont(MaterialDesign.FONT_TITLE_2);
        doanhThuPanel.add(lblDoanhThu, BorderLayout.CENTER);

        // tình trạng kho panel
        tinhTrangPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(tinhTrangPanel);
        tinhTrangPanel.setBackground(MaterialDesign.COLOR_CARD);
        this.add(tinhTrangPanel);

        lblTieuDeTinhTrang = new JLabel("Tình trạng kho", JLabel.CENTER);
        MaterialDesign.materialLabel(lblTieuDeTinhTrang);
        lblTieuDeTinhTrang.setFont(MaterialDesign.FONT_TITLE_1);
        tinhTrangPanel.add(lblTieuDeTinhTrang, BorderLayout.NORTH);

        JPanel innerTinhTrangPanel = new JPanel();
        MaterialDesign.materialPanel(innerTinhTrangPanel);
        innerTinhTrangPanel.setBackground(MaterialDesign.COLOR_CARD);
        innerTinhTrangPanel.setLayout(new BoxLayout(innerTinhTrangPanel, BoxLayout.X_AXIS));
        tinhTrangPanel.add(innerTinhTrangPanel, BorderLayout.CENTER);

        Box boxTinhTrang = Box.createVerticalBox();
        innerTinhTrangPanel.add(Box.createHorizontalStrut(10));
        innerTinhTrangPanel.add(boxTinhTrang);
        innerTinhTrangPanel.add(Box.createHorizontalStrut(10));

        Box bx1 = Box.createHorizontalBox();
        boxTinhTrang.add(bx1);

        Box bx2 = Box.createHorizontalBox();
        boxTinhTrang.add(bx2);

        lblTongSoBangDia_1 = new JLabel("Tổng số băng đĩa: ");
        MaterialDesign.materialLabel(lblTongSoBangDia_1);
        lblTongSoBangDia_1.setFont(MaterialDesign.FONT_TITLE_2);
        lblTongSoBangDia_1.setPreferredSize(new Dimension(300, 30));
        bx1.add(lblTongSoBangDia_1);

        lblTongSoBangDia_2 = new JLabel(String.valueOf(danhSachBangDia.tongSoBangDiaTon() +
                                    danhSachChoThue.soLuongBangDiaDaThue()));
        MaterialDesign.materialLabel(lblTongSoBangDia_2);
        lblTongSoBangDia_2.setFont(MaterialDesign.FONT_TITLE_2);
        bx1.add(lblTongSoBangDia_2);
        bx1.add(Box.createHorizontalGlue());

        lblTongSoBangDiaDaThue_1 = new JLabel("Số băng đĩa đã thuê: ");
        MaterialDesign.materialLabel(lblTongSoBangDiaDaThue_1);
        lblTongSoBangDiaDaThue_1.setFont(MaterialDesign.FONT_TITLE_2);
        lblTongSoBangDiaDaThue_1.setPreferredSize(lblTongSoBangDia_1.getPreferredSize());
        bx2.add(lblTongSoBangDiaDaThue_1);

        lblTongSoBangDiaDaThue_2 = new JLabel(String.valueOf(danhSachChoThue.soLuongBangDiaDaThue()));
        MaterialDesign.materialLabel(lblTongSoBangDiaDaThue_2);
        lblTongSoBangDiaDaThue_2.setFont(MaterialDesign.FONT_TITLE_2);
        bx2.add(lblTongSoBangDiaDaThue_2);
        bx2.add(Box.createHorizontalGlue());

        // khách hàng thân thiết
        khachHangThanThietPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(khachHangThanThietPanel);
        khachHangThanThietPanel.setBackground(MaterialDesign.COLOR_CARD);
        this.add(khachHangThanThietPanel);

        lblTieuDeKhachHangTT = new JLabel("Khách hàng thân thiết", JLabel.CENTER);
        MaterialDesign.materialLabel(lblTieuDeKhachHangTT);
        lblTieuDeKhachHangTT.setFont(MaterialDesign.FONT_TITLE_1);
        khachHangThanThietPanel.add(lblTieuDeKhachHangTT, BorderLayout.NORTH);

        // thuê quá hạn panel
        thueQuaHanPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(thueQuaHanPanel);
        thueQuaHanPanel.setBackground(MaterialDesign.COLOR_CARD);
        this.add(thueQuaHanPanel);

        lblTieuDeThueQuaHan = new JLabel("Thuê quá hạn", JLabel.CENTER);
        MaterialDesign.materialLabel(lblTieuDeThueQuaHan);
        lblTieuDeThueQuaHan.setFont(MaterialDesign.FONT_TITLE_1);
        thueQuaHanPanel.add(lblTieuDeThueQuaHan, BorderLayout.NORTH);

        // băng đĩa thuê nhiều panel
        bangDiaThueNhieuPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(bangDiaThueNhieuPanel);
        bangDiaThueNhieuPanel.setBackground(MaterialDesign.COLOR_CARD);
        this.add(bangDiaThueNhieuPanel);

        lblTieuDeBangDiaThueNhieu = new JLabel("Băng đĩa thuê nhiều", JLabel.CENTER);
        MaterialDesign.materialLabel(lblTieuDeBangDiaThueNhieu);
        lblTieuDeBangDiaThueNhieu.setFont(MaterialDesign.FONT_TITLE_1);
        bangDiaThueNhieuPanel.add(lblTieuDeBangDiaThueNhieu, BorderLayout.NORTH);
    }

    public ThongKeTabbed(){
        try {
            danhSachChoThue = new DanhSachChoThue();
            danhSachBangDia = new DanhSachBangDia();
        } catch (Exception e) {
        }

        prepareUI();
    }
}
