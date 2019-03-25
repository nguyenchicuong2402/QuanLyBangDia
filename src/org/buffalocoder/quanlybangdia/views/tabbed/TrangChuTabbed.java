package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.buffalocoder.quanlybangdia.views.DangNhap;

import javax.swing.*;
import java.awt.*;

public class TrangChuTabbed extends JPanel {

    private JLabel lblTenNhanVien;

    public TrangChuTabbed(){

        this.setLayout(new BorderLayout());

        lblTenNhanVien = new JLabel(DangNhap.taiKhoan.getTenTaiKhoan());
        this.add(lblTenNhanVien, BorderLayout.NORTH);
    }
}
