package org.buffalocoder.quanlybangdia.dao;

import org.buffalocoder.quanlybangdia.models.HoaDon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChoThueDAO {
    private static ChoThueDAO _instance;

    public static ChoThueDAO getInstance() {
        if(_instance == null) {
            synchronized(ChoThueDAO.class) {
                if(null == _instance) {
                    _instance  = new ChoThueDAO();
                }
            }
        }
        return _instance;
    }

    public ArrayList<HoaDon> getHoaDons() {
        ArrayList<HoaDon> hoaDons = new ArrayList<HoaDon>();

        String sql = "SELECT * FROM VIEW_HOADON";

        try {
            ResultSet resultSet = DataBaseUtils.getInstance().excuteQueryRead(sql);

            while(resultSet.next()) {
                HoaDon bangDia = new HoaDon(
                        resultSet.getString("MAHD"),
                        KhachHangDAO.getInstance().getKhachHang(resultSet.getString("MAKH")),
                        resultSet.getDate("NGAYLAP"),
                        BangDiaDAO.getInstance().getBangDia(resultSet.getString("MABD")),
                        resultSet.getInt("SONGAYDUOCMUON"),
                        resultSet.getInt("SOLUONG")
                );

                hoaDons.add(bangDia);
            }
        } catch (SQLException e) {
        }

        return hoaDons;
    }
}
