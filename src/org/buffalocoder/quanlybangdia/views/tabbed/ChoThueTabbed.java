package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.utils.Values;
import org.buffalocoder.quanlybangdia.views.CustomTabbedPanelUI;

import javax.swing.*;
import java.awt.*;

public class ChoThueTabbed extends JPanel {
    private JTabbedPane tabbedPane;
    public ChoThueTabbed(){
        this.setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        tabbedPane.addTab("Danh sách khách hàng cho thuê", new panelListHiredTabbed());
        tabbedPane.addTab("Thêm băng đĩa cần thuê", new panelAddTabbed());
        this.add(tabbedPane);
    }
}
