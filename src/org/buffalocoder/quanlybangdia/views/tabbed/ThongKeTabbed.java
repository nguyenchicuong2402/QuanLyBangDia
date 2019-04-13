package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachChoThue;
import org.buffalocoder.quanlybangdia.models.HoaDon;
import org.buffalocoder.quanlybangdia.models.tablemodel.ChoThueTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ThongKeTabbed extends JPanel {
    private static DanhSachChoThue danhSachChoThue;
    private static DanhSachBangDia danhSachBangDia;

    private JPanel doanhThuPanel, thueQuaHanPanel, tinhTrangPanel, leftPanel, rightPanel;
    private JLabel lblTieuDeDoanhThu, lblTieuDeThueQuaHan, lblTieuDeTinhTrang, lblDoanhThu,
                lblTongSoBangDia_1, lblTongSoBangDia_2, lblTongSoBangDiaDaThue_1, lblTongSoBangDiaDaThue_2,
                lblTongSoBangDiaHong_1, lblTongSoBangDiaHong_2;
    private ChoThueTableModel choThueTableModel;
    private JTable tblChoThue;


    private void prepareUI(){
        this.setLayout(new BorderLayout());
        MaterialDesign.materialPanel(this);

        leftPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        leftPanel.setPreferredSize(new Dimension(400, 500));
        MaterialDesign.materialPanel(leftPanel);
        this.add(leftPanel, BorderLayout.WEST);

        rightPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(rightPanel);
        this.add(rightPanel, BorderLayout.CENTER);

        JPanel seperatorPanel = new JPanel();
        MaterialDesign.materialPanel(seperatorPanel);
        rightPanel.add(seperatorPanel, BorderLayout.WEST);

        // doanh thu panel
        doanhThuPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(doanhThuPanel);
        doanhThuPanel.setBackground(MaterialDesign.COLOR_CARD);
        leftPanel.add(doanhThuPanel, BorderLayout.NORTH);

        lblTieuDeDoanhThu = new JLabel("Doanh thu tuần", JLabel.CENTER);
        MaterialDesign.materialLabel(lblTieuDeDoanhThu);
        lblTieuDeDoanhThu.setFont(MaterialDesign.FONT_TITLE_1);
        doanhThuPanel.add(lblTieuDeDoanhThu, BorderLayout.NORTH);

        lblDoanhThu = new JLabel();
        lblDoanhThu.setHorizontalAlignment(JLabel.CENTER);
        MaterialDesign.materialLabel(lblDoanhThu);
        lblDoanhThu.setFont(MaterialDesign.FONT_TITLE_1);
        doanhThuPanel.add(lblDoanhThu, BorderLayout.CENTER);

        // tình trạng kho panel
        tinhTrangPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(tinhTrangPanel);
        tinhTrangPanel.setBackground(MaterialDesign.COLOR_CARD);
        leftPanel.add(tinhTrangPanel, BorderLayout.SOUTH);

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

        Box bx3 = Box.createHorizontalBox();
        boxTinhTrang.add(bx3);

        lblTongSoBangDia_1 = new JLabel("Tổng số băng đĩa: ");
        MaterialDesign.materialLabel(lblTongSoBangDia_1);
        lblTongSoBangDia_1.setFont(MaterialDesign.FONT_TITLE_2);
        lblTongSoBangDia_1.setPreferredSize(new Dimension(300, 30));
        bx1.add(lblTongSoBangDia_1);

        lblTongSoBangDia_2 = new JLabel();
        MaterialDesign.materialLabel(lblTongSoBangDia_2);
        lblTongSoBangDia_2.setFont(MaterialDesign.FONT_TITLE_2);
        bx1.add(lblTongSoBangDia_2);
        bx1.add(Box.createHorizontalGlue());

        lblTongSoBangDiaDaThue_1 = new JLabel("Số băng đĩa đã thuê: ");
        MaterialDesign.materialLabel(lblTongSoBangDiaDaThue_1);
        lblTongSoBangDiaDaThue_1.setFont(MaterialDesign.FONT_TITLE_2);
        lblTongSoBangDiaDaThue_1.setPreferredSize(lblTongSoBangDia_1.getPreferredSize());
        bx2.add(lblTongSoBangDiaDaThue_1);

        lblTongSoBangDiaDaThue_2 = new JLabel();
        MaterialDesign.materialLabel(lblTongSoBangDiaDaThue_2);
        lblTongSoBangDiaDaThue_2.setFont(MaterialDesign.FONT_TITLE_2);
        bx2.add(lblTongSoBangDiaDaThue_2);
        bx2.add(Box.createHorizontalGlue());

        lblTongSoBangDiaHong_1 = new JLabel("Số băng đĩa đã hỏng: ");
        MaterialDesign.materialLabel(lblTongSoBangDiaHong_1);
        lblTongSoBangDiaHong_1.setFont(MaterialDesign.FONT_TITLE_2);
        lblTongSoBangDiaHong_1.setPreferredSize(lblTongSoBangDia_1.getPreferredSize());
        bx3.add(lblTongSoBangDiaHong_1);

        lblTongSoBangDiaHong_2 = new JLabel();
        MaterialDesign.materialLabel(lblTongSoBangDiaHong_2);
        lblTongSoBangDiaHong_2.setFont(MaterialDesign.FONT_TITLE_2);
        bx3.add(lblTongSoBangDiaHong_2);
        bx3.add(Box.createHorizontalGlue());

        // thuê quá hạn panel
        thueQuaHanPanel = new JPanel(new BorderLayout());
        MaterialDesign.materialPanel(thueQuaHanPanel);
        thueQuaHanPanel.setBackground(MaterialDesign.COLOR_CARD);
        rightPanel.add(thueQuaHanPanel, BorderLayout.CENTER);

        lblTieuDeThueQuaHan = new JLabel("Thuê quá hạn", JLabel.CENTER);
        MaterialDesign.materialLabel(lblTieuDeThueQuaHan);
        lblTieuDeThueQuaHan.setFont(MaterialDesign.FONT_TITLE_1);
        lblTieuDeThueQuaHan.setPreferredSize(new Dimension(300, 100));
        thueQuaHanPanel.add(lblTieuDeThueQuaHan, BorderLayout.NORTH);

        choThueTableModel = new ChoThueTableModel(danhSachChoThue.getAll());

        tblChoThue = new JTable(choThueTableModel);
        MaterialDesign.materialTable(tblChoThue);

        JScrollPane scrollPane = new JScrollPane(tblChoThue);
        MaterialDesign.materialScrollPane(scrollPane);
        thueQuaHanPanel.add(scrollPane, BorderLayout.CENTER);

        refresh();
    }

    public void refresh(){
        try {
            danhSachBangDia.loadData();
            danhSachChoThue.loadData();

            lblTongSoBangDia_2.setText(String.valueOf(danhSachBangDia.tongSoBangDiaTon() +
                    danhSachChoThue.soLuongBangDiaDaThue()));

            lblTongSoBangDiaDaThue_2.setText(String.valueOf(danhSachChoThue.soLuongBangDiaDaThue()));

            lblTongSoBangDiaHong_2.setText(String.valueOf(danhSachBangDia.tongSoBangDiaHong()));

            lblDoanhThu.setText(String.valueOf(danhSachChoThue.tongDoanhThu()));

            ArrayList<HoaDon> hoaDons = new ArrayList<>();
            for (HoaDon hoaDon : danhSachChoThue.getAll())
                if (!hoaDon.isTinhTrangThue())
                    hoaDons.add(hoaDon);

            choThueTableModel.setModel(hoaDons);
            tblChoThue.setModel(choThueTableModel);

            tblChoThue.revalidate();
            tblChoThue.repaint();


        } catch (Exception e) {
            e.printStackTrace();
        }
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
