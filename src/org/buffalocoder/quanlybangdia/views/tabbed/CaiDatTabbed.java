package org.buffalocoder.quanlybangdia.views.tabbed;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;
import com.toedter.plaf.JCalendarTheme;
import org.buffalocoder.quanlybangdia.utils.Formats;

import javax.swing.*;
import java.awt.*;

public class CaiDatTabbed extends JPanel {

    public CaiDatTabbed(){
        this.setLayout(new BorderLayout());

        JDateChooser dateChooser = new JDateChooser(Formats.DATE_FORMAT.toPattern(), "##/##/####", '_');
        add(dateChooser);
    }
}
