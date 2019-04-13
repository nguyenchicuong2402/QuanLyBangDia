package org.buffalocoder.quanlybangdia.utils;

import java.text.SimpleDateFormat;

public class Formats {
    // Format ngày theo định dạng Ngày - Tháng - Năm (người dùng)
    public static final SimpleDateFormat DATE_FORMAT        = new SimpleDateFormat("dd/MM/yyyy");

    // Format ngày theo định dạng Năm - Tháng - Ngày (lưu vào SQL)
    public static final SimpleDateFormat DATE_FORMAT_SQL    = new SimpleDateFormat("yyyy-MM-dd");
}
