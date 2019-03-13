package org.buffalocoder.quanlybangdia.views.tabbed;

import org.buffalocoder.quanlybangdia.views.panelAdd;
import org.buffalocoder.quanlybangdia.views.panelListHired;

import javax.swing.*;
import java.awt.*;

public class ChoThueTabbed extends JPanel {
    public ChoThueTabbed(){
        this.setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        JComponent panelListHired = createPane();
        JComponent panelAdd = createPane();
        tabbedPane.addTab("Tap 1", null, new panelListHired(), "Click to show panel 1");
        tabbedPane.addTab("Tap 2", null, new panelAdd(), "Click to show panel 1");
        this.add(tabbedPane);
    }
    private JPanel createPane() {
        JPanel panel = new JPanel();
        panel.add(new Label("ƯKfn"));
        return panel;
    }
}
