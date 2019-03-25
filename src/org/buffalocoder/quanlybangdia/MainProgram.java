package org.buffalocoder.quanlybangdia;

import org.buffalocoder.quanlybangdia.dao.DBConnection;
import org.buffalocoder.quanlybangdia.dao.DataBaseUtils;
import org.buffalocoder.quanlybangdia.dao.KhachHangDAO;
import org.buffalocoder.quanlybangdia.dao.NhanVienDAO;
import org.buffalocoder.quanlybangdia.models.ThongTinCaNhan;
import org.buffalocoder.quanlybangdia.views.DangNhap;
import org.buffalocoder.quanlybangdia.views.MainForm;

public class MainProgram {
    public static void main(String args[]){
        DataBaseUtils.getInstance();

        new DangNhap();
    }
}
