package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.models.BangDia;
import org.buffalocoder.quanlybangdia.models.tablemodel.BangDiaTableModel;
import org.buffalocoder.quanlybangdia.utils.MaterialDesign;
import org.buffalocoder.quanlybangdia.utils.Values;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class QuanLyBangDiaTabbed extends JPanel {
    private JTable tblBangDia;
    private JPanel topPanel, funcPanel, searchPanel;
    private JButton btnThem, btnXoa, btnSua, btnTimKiem;
    private JTextField txtTuKhoa;
    private TableRowSorter<TableModel> sorter;

    public QuanLyBangDiaTabbed(){
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
        MaterialDesign.materialButton(btnThem, new Dimension(90, 40));
        funcPanel.add(btnThem);

        btnSua = new JButton("Sửa");
        MaterialDesign.materialButton(btnSua, btnThem.getPreferredSize());
        funcPanel.add(btnSua);

        btnXoa = new JButton("Xoá");
        MaterialDesign.materialButton(btnXoa, btnThem.getPreferredSize());
        funcPanel.add(btnXoa);

        // tìm kiếm
        searchPanel = new JPanel();
        searchPanel.setBackground(Values.COLOR_BACKGROUND);
        topPanel.add(searchPanel, BorderLayout.EAST);

        txtTuKhoa = new JTextField();
        MaterialDesign.materialTextField(txtTuKhoa, new Dimension(300, 40));
        searchPanel.add(txtTuKhoa);

        btnTimKiem = new JButton("Tìm kiếm");
        MaterialDesign.materialButton(btnTimKiem, btnThem.getPreferredSize());
        searchPanel.add(btnTimKiem);
    }

    private void filterTableNhanVien(String key_word) {
        if (key_word.isEmpty())
            sorter.setRowFilter(null);
        else {
            try{
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    @Override
                    public boolean include(Entry<?, ?> entry) {
                        return  entry.getValue(0).equals(key_word.trim());
                    }
                };
                sorter.setRowFilter(filter);
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(QuanLyBangDiaTabbed.this,
                        "Vui lòng nhập số",
                        "Thông báo",
                        JOptionPane.ERROR_MESSAGE);

                txtTuKhoa.selectAll();
            }

        }
    }
}
