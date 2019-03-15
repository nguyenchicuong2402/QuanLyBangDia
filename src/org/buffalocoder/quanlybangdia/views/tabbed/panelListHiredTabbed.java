package org.buffalocoder.quanlybangdia.views.tabbed;


import org.buffalocoder.quanlybangdia.models.HiredCustomer;
import org.buffalocoder.quanlybangdia.models.HiredCustomerTableModel;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class panelListHiredTabbed extends JPanel {
    private JTable tblHired;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem, btnInfo;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;
    public panelListHiredTabbed() {
        this.setLayout(new BorderLayout());
        this.setFont(Values.FONT_PLAIN_DEFAULT);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Values.COLOR_BACKGROUND);

        /*========== TOP PANEL ==========*/
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Values.COLOR_BACKGROUND);
        this.add(topPanel, BorderLayout.NORTH);

        // chức năng
        funcPanel = new JPanel();
        funcPanel.setBackground(Values.COLOR_BACKGROUND);
        topPanel.add(funcPanel, BorderLayout.WEST);

        btnThem = new JButton("Thêm");
        btnThem.setBackground(Values.COLOR_PRIMARY);
        btnThem.setForeground(Values.COLOR_TEXT);
        btnThem.setBorder(BorderFactory.createEmptyBorder());
        btnThem.setPreferredSize(new Dimension(90, 40));
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setBackground(Values.COLOR_PRIMARY);
        btnSua.setForeground(Values.COLOR_TEXT);
        btnSua.setBorder(BorderFactory.createEmptyBorder());
        btnSua.setPreferredSize(btnThem.getPreferredSize());
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        btnXoa.setBackground(Values.COLOR_PRIMARY);
        btnXoa.setForeground(Values.COLOR_TEXT);
        btnXoa.setBorder(BorderFactory.createEmptyBorder());
        btnXoa.setPreferredSize(btnThem.getPreferredSize());
        funcPanel.add(btnXoa);

        btnInfo = new JButton("Xem thông tin");
        btnInfo.setBackground(Values.COLOR_PRIMARY);
        btnInfo.setForeground(Values.COLOR_TEXT);
        btnInfo.setBorder(BorderFactory.createEmptyBorder());
        btnInfo.setPreferredSize(btnThem.getPreferredSize());
        funcPanel.add(btnInfo);
//      Tìm kiếm
        searchPanel = new JPanel();
        searchPanel.setBackground(Values.COLOR_BACKGROUND);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTuKhoa = new JTextField();
        txtTuKhoa.getDocument().addDocumentListener(btnTimUpdate());
        txtTuKhoa.setPreferredSize(new Dimension(300, 40));
        txtTuKhoa.setFont(Values.FONT_PLAIN_DEFAULT);
        txtTuKhoa.setBackground(Values.COLOR_BACKGROUND);
        txtTuKhoa.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        txtTuKhoa.setMargin(new Insets(0, 0, 100, 0));
        searchPanel.add(txtTuKhoa);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setBackground(Values.COLOR_PRIMARY);
        btnTimKiem.setForeground(Values.COLOR_TEXT);
        btnTimKiem.setBorder(BorderFactory.createEmptyBorder());
        btnTimKiem.setPreferredSize(btnThem.getPreferredSize());
        searchPanel.add(btnTimKiem);

        this.add(topPanel, BorderLayout.NORTH);

        ArrayList<HiredCustomer> hiredList = new ArrayList<HiredCustomer>();

        HiredCustomerTableModel hiredTableModel = new HiredCustomerTableModel(hiredList);
        tblHired = new JTable(hiredTableModel );
        tblHired.setRowHeight(30);
        tblHired.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblHired.setAutoscrolls(true);
        this.add(new JScrollPane(tblHired), BorderLayout.CENTER);

        sorter = new TableRowSorter<TableModel>(tblHired.getModel());
        tblHired.setRowSorter(sorter);

        hiredList.add(new HiredCustomer("112", "Trường", 12, "12", "13"));
        hiredList.add(new HiredCustomer("1123", "Trường", 13, "1", "11"));
        hiredList.add(new HiredCustomer("11212", "Trường", 22, "2", "43"));
        hiredList.add(new HiredCustomer("212", "Trường", 1, "20", "33"));
    }
    public DocumentListener btnTimUpdate() {
        return new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                // TODO Auto-generated method stub
                update(arg0);
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                // TODO Auto-generated method stub
                update(arg0);
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                // TODO Auto-generated method stub
                update(arg0);
            }

            private void update(DocumentEvent arg0) {
                filterTableNhanVien(txtTuKhoa.getText());
            }
        };
    }
    private void filterTableNhanVien(String key_word){
        if (key_word.isEmpty())
            sorter.setRowFilter(null);
        else
            sorter.setRowFilter(RowFilter.regexFilter(key_word));
    }

}
