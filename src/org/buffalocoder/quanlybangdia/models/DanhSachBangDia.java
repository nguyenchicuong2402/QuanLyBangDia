package org.buffalocoder.quanlybangdia.models;

import org.buffalocoder.quanlybangdia.dao.BangDiaDAO;

import java.util.ArrayList;

public class DanhSachBangDia {
    private ArrayList<BangDia> bangDias = null;
    private static BangDiaDAO bangDiaDAO;

    public DanhSachBangDia() throws Exception {
        bangDiaDAO = BangDiaDAO.getInstance();
        loadData();
    }

    /**
     * Load dữ liệu từ database
     *
     * @throws Exception
     */
    public void loadData() throws Exception {
        bangDias = bangDiaDAO.getBangDias();
    }


    /**
     * Lấy danh sách băng đĩa
     *
     * @return
     */
    public ArrayList<BangDia> getAll() {
        return bangDias;
    }


    /**
     * Thêm 1 băng đĩa mới (không cho thêm băng đĩa bị trùng mã)
     * Lưu băng đĩa vào DB
     *
     * @param bangDia
     * @return
     * @throws Exception
     */
    public boolean them(BangDia bangDia) throws Exception {
        if (bangDia == null || bangDias.contains(bangDia))
            return false;

        return (bangDias.add(bangDiaDAO.themBangDia(bangDia)));
    }


    /**
     * Xoá băng đĩa bằng mã băng đĩa
     * Xoá băng đĩa tương ứng trong DB
     *
     * @param maBangDia
     * @return
     * @throws Exception
     */
    public boolean xoa(String maBangDia) throws Exception {
        BangDia bangDia = bangDias.get(tim(maBangDia));

        if (bangDia == null)
            return false;

        return (bangDiaDAO.xoaBangDia(maBangDia) && bangDias.remove(bangDia));
    }


    /**
     * Cập nhật thông tin băng đĩa
     * Cập nhật thông tin băng đĩa tương ứng trong DB
     *
     * @param bangDia
     * @return
     * @throws Exception
     */
    public boolean sua(BangDia bangDia) throws Exception {
        return bangDias.set(tim(bangDia.getMaBangDia()), bangDiaDAO.suaBangDia(bangDia)) != null;
    }


    /**
     * Tìm vị trí của băng đĩa
     *
     * @param maBangDia
     * @return
     */
    public int tim(String maBangDia) {
        for (int i = 0; i < bangDias.size(); i++)
            if (bangDias.get(i).getMaBangDia().equals(maBangDia))
                return i;

        return -1;
    }


    /**
     * Xoá băng đỉa hỏng trong danh sách
     * Xoá băng đĩa tương ứng trong DB
     */
    public void xoaBangDiaHong() {
        ArrayList<String> dsXoa = new ArrayList<>();

        for (BangDia bangDia : bangDias) {
            if (!bangDia.isTinhTrang()) {
                dsXoa.add(bangDia.getMaBangDia());
            }
        }

        for (String maBangDia : dsXoa) {
            try {
                xoa(maBangDia);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Lấy tổng số băng đia tồn trong kho
     *
     * @return
     */
    public int tongSoBangDiaTon() {
        int tong = 0;

        for (BangDia bangDia : bangDias)
            tong += bangDia.getSoLuongTon();

        return tong;
    }


    /**
     * Lấy tổng số băng đĩa hỏng
     *
     * @return
     */
    public int tongSoBangDiaHong() {
        int count = 0;

        for (BangDia bangDia : bangDias)
            if (!bangDia.isTinhTrang())
                count++;

        return count;
    }
}
