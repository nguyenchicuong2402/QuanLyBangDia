﻿use QUANLYBANGDIA

INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH)
VALUES (1111, 'CHÓ KHOA', '123456789', 'TP. HCM', 1);
INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH)
VALUES (1112, 'CHÓ CƯỜNG', '987654321', 'KIÊN GIANG', 0);
INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH)
VALUES (1113, 'CHÓ TRƯỜNG', '789456123', 'BÌNH DƯƠNG', 0);
INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH)
VALUES (1114, 'KHOA', '321654987', 'TP. HCM', 1);
INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH)
VALUES (1115, 'CƯỜNG', '321654987', 'KIÊN GIANG', 1);




INSERT INTO KHACHHANG (MAKH, CMND)
VALUES (1, 1111);
INSERT INTO KHACHHANG (MAKH, CMND)
VALUES (2, 1112)
INSERT INTO KHACHHANG (MAKH, CMND)
VALUES (3, 1113)
INSERT INTO KHACHHANG (MAKH, CMND)
VALUES (4, 1114)
INSERT INTO KHACHHANG (MAKH, CMND)
VALUES (5, 1115)



INSERT INTO NHANVIEN (MANV, CMND, MOTA)
VALUES (1, 1111, 'CHÓ KHOA')

INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI)
VALUES (111, N'CÂU CHIỆN LÀM NGÀNH CỦA KHOA', N'NGÀNH ENTERTAIMENT', N'CẤM TRẺ EM TRÊN 18+', 5000, 1, N'PHIM TRẺ EM')

INSERT INTO HOADON (MAHD, MAKH)
VALUES (112, 1)
INSERT INTO HOADON (MAHD, MAKH)
VALUES (113, 2)
INSERT INTO HOADON (MAHD, MAKH)
VALUES (114, 3)
INSERT INTO HOADON (MAHD, MAKH)
VALUES (115, 4)
INSERT INTO HOADON (MAHD, MAKH)
VALUES (116, 5)


INSERT INTO CHITIETHOADON (MAHD, MABD, SONGAYDUOCMUON, SOLUONG)
VALUES (112, 111, 10, 1)