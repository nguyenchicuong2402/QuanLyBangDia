package org.buffalocoder.quanlybangdia;

import org.buffalocoder.quanlybangdia.dao.DBConnection;
import org.buffalocoder.quanlybangdia.views.MainForm;

public class MainProgram {
    public static void main(String args[]){
        DBConnection.getConnection();

        new MainForm();
    }
}
