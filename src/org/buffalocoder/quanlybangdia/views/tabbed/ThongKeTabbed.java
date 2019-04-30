package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.DanhSachBangDia;
import org.buffalocoder.quanlybangdia.models.DanhSachChoThue;
import org.buffalocoder.quanlybangdia.models.HoaDon;
import org.buffalocoder.quanlybangdia.models.tablemodel.ChoThueTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThongKeTabbed extends JPanel {
    private static DanhSachChoThue danhSachChoThue;
    private static DanhSachBangDia danhSachBangDia;
    private static LocalDate currentDate = LocalDate.now();

    private JPanel doanhThuPanel, thueQuaHanPanel, tinhTrangPanel, leftPanel, rightPanel;
    private JLabel lblTieuDeDoanhThu, lblTieuDeThueQuaHan, lblTieuDeTinhTrang, lblDoanhThu,
            lblTongSoBangDia_1, lblTongSoBangDia_2, lblTongSoBangDiaDaThue_1, lblTongSoBangDiaDaThue_2,
            lblTongSoBangDiaHong_1, lblTongSoBangDiaHong_2, lblThoiGian;
    private ChoThueTableModel choThueTableModel;
    private JTable tblChoThue;
    private JComboBox<String> cbThang, cbNam;


    /**
     * Tạo GUI
     */
    private void prepareUI() {
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

        JPanel filterDoanhThu = new JPanel();
        filterDoanhThu.setLayout(new BoxLayout(filterDoanhThu, BoxLayout.X_AXIS));
        MaterialDesign.materialPanel(filterDoanhThu);
        filterDoanhThu.setBackground(MaterialDesign.COLOR_CARD);
        doanhThuPanel.add(filterDoanhThu, BorderLayout.NORTH);

        Box boxFilterDoanhThu = Box.createVerticalBox();
        filterDoanhThu.add(boxFilterDoanhThu);

        Box boxTitleDoanhThu = Box.createHorizontalBox();
        boxFilterDoanhThu.add(Box.createHorizontalGlue());
        boxFilterDoanhThu.add(Box.createVerticalStrut(10));
        boxFilterDoanhThu.add(boxTitleDoanhThu);
        boxFilterDoanhThu.add(Box.createVerticalStrut(10));

        Box boxThoiGian = Box.createHorizontalBox();
        boxFilterDoanhThu.add(Box.createHorizontalGlue());
        boxFilterDoanhThu.add(boxThoiGian);
        boxFilterDoanhThu.add(Box.createVerticalStrut(10));

        Box boxFilter = Box.createHorizontalBox();
        boxFilterDoanhThu.add(boxFilter);

        lblTieuDeDoanhThu = new JLabel("Doanh thu");
        MaterialDesign.materialLabel(lblTieuDeDoanhThu);
        lblTieuDeDoanhThu.setFont(MaterialDesign.FONT_TITLE_1);
        boxTitleDoanhThu.add(lblTieuDeDoanhThu);

        lblThoiGian = new JLabel("das");
        MaterialDesign.materialLabel(lblThoiGian);
        lblThoiGian.setFont(MaterialDesign.FONT_TITLE_2);
        boxThoiGian.add(lblThoiGian);

        lblDoanhThu = new JLabel();
        lblDoanhThu.setHorizontalAlignment(JLabel.CENTER);
        MaterialDesign.materialLabel(lblDoanhThu);
        lblDoanhThu.setFont(MaterialDesign.FONT_TITLE_1);
        doanhThuPanel.add(lblDoanhThu, BorderLayout.CENTER);

        cbThang = new JComboBox<>();
        cbThang.addActionListener(cbThang_Selected());
        MaterialDesign.materialComboBox(cbThang);
        boxFilter.add(Box.createHorizontalStrut(20));
        boxFilter.add(cbThang);

        cbNam = new JComboBox<>();
        MaterialDesign.materialComboBox(cbNam);
        cbNam.addActionListener(cbNam_Selected());
        boxFilter.add(Box.createHorizontalStrut(10));
        boxFilter.add(cbNam);
        boxFilter.add(Box.createHorizontalStrut(20));

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

        // generate dữ liệu năm (5 năm gần đây)
        cbNam.addItem("Tất cả");
        for (int i = 0; i < 5; i++)
            cbNam.addItem(String.valueOf(currentDate.getYear() - i));

        refresh();
    }


    /**
     * Cập nhật giao diện khi dữ liệu thay đổi
     */
    public void refresh() {
        final Pattern pattern = Pattern.compile("^Tháng (\\d.*)");

        try {
            // load data mới
            danhSachBangDia.loadData();
            danhSachChoThue.loadData();

            // Card doanh thu
            int nam = String.valueOf(cbNam.getSelectedItem()).equalsIgnoreCase("Tất cả") ? 0 :
                    Integer.parseInt(String.valueOf(cbNam.getSelectedItem()));
            int thang = 0;
            final Matcher matcher = pattern.matcher(String.valueOf(cbThang.getSelectedItem()));
            if (matcher.find())
                thang = Integer.valueOf(matcher.group(1));

            // hiển thị doanh thu theo định dạng tiền việt nam
            Locale locale_vn = new Locale("vi", "VN");
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale_vn);
            lblDoanhThu.setText(numberFormat.format(danhSachChoThue.tongDoanhThu(thang, nam)));

            // Thay đổi title khi người dùng chọn thống kê theo tháng, năm
            if (thang != 0 && nam != 0) {
                lblTieuDeDoanhThu.setText("Doanh thu");
                lblThoiGian.setText(String.format("Tháng %d/%d", thang, nam));
            } else if (thang == 0 && nam != 0) {
                lblTieuDeDoanhThu.setText("Doanh thu");
                lblThoiGian.setText(String.format("Năm %d", nam));
            } else {
                lblThoiGian.setText("");
                lblTieuDeDoanhThu.setText("Tổng doanh thu");
            }

            // tình trạng kho
            lblTongSoBangDia_2.setText(String.valueOf(danhSachBangDia.tongSoBangDiaTon() +
                    danhSachChoThue.soLuongBangDiaDaThue()));

            lblTongSoBangDiaDaThue_2.setText(String.valueOf(danhSachChoThue.soLuongBangDiaDaThue()));

            lblTongSoBangDiaHong_2.setText(String.valueOf(danhSachBangDia.tongSoBangDiaHong()));


            // cập nhật bảng thuê quá hạn
            ArrayList<HoaDon> hoaDons = new ArrayList<>();
            for (HoaDon hoaDon : danhSachChoThue.getAll())
                if (!hoaDon.isThueQuaHan() && !hoaDon.isTinhTrang())
                    hoaDons.add(hoaDon);

            choThueTableModel.setModel(hoaDons);
            tblChoThue.setModel(choThueTableModel);

            tblChoThue.revalidate();
            tblChoThue.repaint();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Sự kiện khi chọn item combo box Năm
     *
     * @return
     */
    private ActionListener cbNam_Selected() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * nếu như người dùng muốn xem tổng doanh thu thì xoá tất cả item tháng
                 * item tháng sẽ phụ thuộc theo năm (nếu chọn năm hiện tại thì tháng không vươt quá tháng hiện tại)
                 */
                if (String.valueOf(cbNam.getSelectedItem()).equalsIgnoreCase("Tất cả")) {
                    cbThang.removeAllItems();
                    cbThang.addItem("Tất cả");
                } else {
                    // generate dữ liệu tháng theo năm
                    cbThang.removeAllItems();
                    int nam = Integer.parseInt(String.valueOf(cbNam.getSelectedItem()));
                    for (int i = 0; i <= (nam == currentDate.getYear() ? currentDate.getMonthValue() : 12); i++)
                        cbThang.addItem(i == 0 ? "Tất cả" : String.format("Tháng %d", i));
                }

                refresh();
            }
        };
    }


    /**
     * Sự kiện khi chọn item combo box tháng
     *
     * @return
     */
    private ActionListener cbThang_Selected() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        };
    }


    public ThongKeTabbed() {
        try {
            danhSachChoThue = new DanhSachChoThue();
            danhSachBangDia = new DanhSachBangDia();
        } catch (Exception e) {
        }

        prepareUI();
    }
}
