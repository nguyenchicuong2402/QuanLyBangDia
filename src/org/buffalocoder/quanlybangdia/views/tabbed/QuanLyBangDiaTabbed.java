package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.BangDiaTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QuanLyBangDiaTabbed extends JPanel {
    private JTable tblBangDia;

    public QuanLyBangDiaTabbed(){
        this.setLayout(new BorderLayout());

        JComboBox<String> cbTinhTrang = new JComboBox<>(new String[]{"Mới", "Hư hỏng"});
        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(cbTinhTrang);


        ArrayList<BangDia> bangDiaList = new ArrayList<BangDia>();
        BangDiaTableModel bangDiaTableModel = new BangDiaTableModel(bangDiaList);
        tblBangDia = new JTable(bangDiaTableModel);
        tblBangDia.setRowHeight(30);
        tblBangDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBangDia.setAutoscrolls(true);
        tblBangDia.getColumnModel().getColumn(3).setCellEditor(defaultCellEditor);
        this.add(new JScrollPane(tblBangDia), BorderLayout.CENTER);
    }
}
