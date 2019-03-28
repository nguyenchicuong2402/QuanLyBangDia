﻿USE master
GO

--========== TẠO DATABASE  ==========--
CREATE DATABASE QUANLYBANGDIA
GO

USE QUANLYBANGDIA
GO

--========== TẠO BẢNG ==========--
CREATE TABLE THONGTINCANHAN
(
  CMND VARCHAR(20) NOT NULL,
  HOTEN NVARCHAR(50) NOT NULL,
  DIENTHOAI VARCHAR(20),
  DIACHI NVARCHAR(100),
  GIOITINH INT NOT NULL,
  NGAYSINH DATE,
  CONSTRAINT THONGTINCANHAN_CK_GIOITINH CHECK (GIOITINH IN (0, 1)),
  CONSTRAINT THONGTINCANHAN_PK PRIMARY KEY (CMND)
)
GO

CREATE TABLE KHACHHANG
(
  MAKH VARCHAR(10) NOT NULL,
  CMND VARCHAR(20) NOT NULL,
  NGAYHETHAN DATE,
  CONSTRAINT KHACHHANG_PK PRIMARY KEY (MAKH),
  CONSTRAINT KHACHHANG_FK FOREIGN KEY (CMND) REFERENCES THONGTINCANHAN (CMND)
)
GO

CREATE TABLE NHANVIEN
(
  MANV VARCHAR(10) NOT NULL,
  CMND VARCHAR(20) NOT NULL,
  MOTA NVARCHAR(100),
  CONSTRAINT NHANVIEN_PK PRIMARY KEY (MANV),
  CONSTRAINT NHANVIEN_FK FOREIGN KEY (CMND) REFERENCES THONGTINCANHAN (CMND)
)
GO

CREATE TABLE TAIKHOAN
(
  TENTAIKHOAN VARCHAR(30) NOT NULL,
  MATKHAU NVARCHAR(128) NOT NULL,
  LOAITK SMALLINT NOT NULL,
  MANV VARCHAR(10) NOT NULL,
  CONSTRAINT TAIKHOAN_PK PRIMARY KEY (TENTAIKHOAN),
  CONSTRAINT TAIKHOAN_FK FOREIGN KEY (MANV) REFERENCES NHANVIEN(MANV)
)
GO

CREATE TABLE BANGDIA
(
  MABD NVARCHAR(10) NOT NULL,
  TENBD NVARCHAR(50) NOT NULL,
  HANGSANXUAT NVARCHAR(50) NOT NULL,
  GHICHU NVARCHAR(100),
  DONGIA MONEY NOT NULL,
  TINHTRANG INT,
  THELOAI NVARCHAR(30) NOT NULL,
  SOLUONGTON INT NOT NULL,
  CONSTRAINT BANGDIA_PK PRIMARY KEY (MABD),
  CONSTRAINT BANGDIA_CK_TINHTRANG CHECK (TINHTRANG IN (0, 1))
)
GO

CREATE TABLE HOADON
(
  MAHD VARCHAR(10) NOT NULL,
  NGAYLAP DATE,
  MAKH VARCHAR(10) NOT NULL,
  CONSTRAINT HOADON_PK PRIMARY KEY (MAHD),
  CONSTRAINT HOADON_FK_KHACHHANG FOREIGN KEY (MAKH) REFERENCES KHACHHANG(MAKH)
)
GO

CREATE TABLE CHITIETHOADON
(
  MAHD VARCHAR(10) NOT NULL,
  MABD NVARCHAR(10) NOT NULL,
  SONGAYDUOCMUON INT NOT NULL,
  SOLUONG INT NOT NULL,
  CONSTRAINT CHITIETHOADON_PK PRIMARY KEY (MAHD),
  CONSTRAINT CHITIETHOADON_FK_BANGDIA FOREIGN KEY (MABD) REFERENCES BANGDIA(MABD)
)
GO

ALTER TABLE KHACHHANG
  ADD CONSTRAINT KHACHHANG_DF_NGAYHETHAN DEFAULT DATEADD(YEAR, 3, GETDATE()) FOR NGAYHETHAN
GO

ALTER TABLE BANGDIA
  ADD CONSTRAINT BANGDIA_DF_TINHTRANG DEFAULT 1 FOR TINHTRANG
GO

ALTER TABLE HOADON
  ADD CONSTRAINT HOADON_DF_NGAYLAP DEFAULT GETDATE() FOR NGAYLAP
GO


--========== VIEW --==========
CREATE VIEW VIEW_THONGTINKHACHHANG AS
SELECT	T.CMND, K.MAKH, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH, NGAYHETHAN
FROM	THONGTINCANHAN T INNER JOIN KHACHHANG K
                                  ON T.CMND = K.CMND
GO

CREATE VIEW VIEW_THONGTINNHANVIEN AS
SELECT	T.CMND, N.MANV, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH, MOTA
FROM	THONGTINCANHAN T INNER JOIN NHANVIEN N
                                  ON T.CMND = N.CMND
GO

CREATE VIEW VIEW_HOADON AS
SELECT	H.MAHD, H.MAKH, H.NGAYLAP, C.MABD, C.SONGAYDUOCMUON, C.SOLUONG
FROM	HOADON H INNER JOIN CHITIETHOADON C
                          ON H.MAHD = C.MAHD
GO


--========== TRIGGER ==========--
CREATE TRIGGER TRIGGER_INSERT_HOADON
  ON VIEW_HOADON
  INSTEAD OF INSERT
  AS
BEGIN
  UPDATE BANGDIA
  SET SOLUONGTON = SOLUONGTON - (
    SELECT	SOLUONG
    FROM	inserted
    WHERE	MABD = BANGDIA.MABD
  )
END
GO

CREATE TRIGGER TRIGGER_UPDATE_HOADON
  ON VIEW_HOADON
  INSTEAD OF UPDATE
  AS
BEGIN
  UPDATE BANGDIA
  SET SOLUONGTON = SOLUONGTON -
                   (SELECT SOLUONG FROM inserted WHERE MABD = BANGDIA.MABD) +
                   (SELECT SOLUONG FROM deleted WHERE MABD = BANGDIA.MABD)
  FROM	BANGDIA INNER JOIN deleted
                           ON BANGDIA.MABD = deleted.MABD
END
GO

CREATE TRIGGER TRIGGER_DELETE_HOADON
  ON VIEW_HOADON
  INSTEAD OF DELETE
  AS
BEGIN
  UPDATE	BANGDIA
  SET SOLUONGTON = SOLUONGTON +
                   (SELECT SOLUONG  FROM deleted WHERE MABD = BANGDIA.MABD)
  FROM	BANGDIA INNER JOIN deleted
                           ON BANGDIA.MABD = deleted.MABD
END
GO


--========== THÊM DỮ LIỆU ==========--
INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH)
VALUES ('111111111', N'Nguyễn Chí Cường', '0982505701', N'Lê Lợi, P.3, Q. Gò Vấp, TP. Hồ Chí Minh', 1, '1999-02-24')

INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH)
VALUES ('111111112', N'Đặng Lê Minh Trường', '0909113112', N'Bình Dương', 0, '1999-12-21')

INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH)
VALUES ('111111113', N'Phạm Minh Khoa', '0904567234', N'CMT8, P.15, Q.10, TP. Hồ Chí Minh', 0, '1999-01-13')

INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH)
VALUES ('111111114', N'Nhan Vĩ Nam', '0456234561', N'Trường ĐH Công Nghiệp TP.HCM', 1, '1999-03-21')

--------------------------------------------------------------------------------------------------------
INSERT INTO KHACHHANG (MAKH, CMND)
VALUES ('KH00001', '111111114')

--------------------------------------------------------------------------------------------------------
INSERT INTO NHANVIEN (MANV, CMND, MOTA)
VALUES ('NV00001', '111111111', N'ADMIN')

INSERT INTO NHANVIEN (MANV, CMND, MOTA)
VALUES ('NV00002', '111111112', N'Nhân viên cho thuê')

INSERT INTO NHANVIEN (MANV, CMND, MOTA)
VALUES ('NV00003', '111111113', N'Nhân viên kế toán')

--------------------------------------------------------------------------------------------------------
INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, LOAITK, MANV)
VALUES ('admin', '123456', 1, 'NV00001')

INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, LOAITK, MANV)
VALUES ('truong', 'truong', 0, 'NV00002')

INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, LOAITK, MANV)
VALUES ('khoa', 'khoa', 0, 'NV00003')

--------------------------------------------------------------------------------------------------------
INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON)
VALUES ('BD00001', N'Hậu duệ mặt trời', N'Hàn Quốc', N'Phim dài tập', 200000, 1, N'Phim tình cảm', 10)

INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON)
VALUES ('BD00002', N'Marvel Captain', N'Marvel Studio', N'Phim siêu anh hùng', 300000, 0, N'Phim viễn tưởng', 5)

INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON)
VALUES ('BD00003', N'Iron Man', N'Marvel Studio', N'Phim siêu anh hùng', 250000, 1, N'Phim viễn tưởng', 20)

INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON)
VALUES ('BD00004', N'Tom and Jerry', N'William Hanna', N'Phim hoạt hình', 100000, 0, N'Phim hoạt hình', 5)

INSERT INTO BANGDIA (MABD, TENBD, HANGSANXUAT, GHICHU, DONGIA, TINHTRANG, THELOAI, SOLUONGTON)
VALUES ('BD00005', N'Đôraêmon', N'Nhật Bản', N'Phim hoạt hình', 200000, 1, N'Phim hoạt hình', 9)

----------------------------------------------------------------------------------------------------------

INSERT INTO HOADON (MAHD, MAKH)
VALUES ('HD00001', 'KH00001')

INSERT INTO HOADON (MAHD, MAKH)
VALUES ('HD00002', 'KH00001')

-----------------------------------------------------------------------------------------------------------

INSERT INTO CHITIETHOADON (MAHD, MABD, SONGAYDUOCMUON, SOLUONG)
VALUES ('HD00001', 'BD00001', 10, 1)

INSERT INTO CHITIETHOADON (MAHD, MABD, SONGAYDUOCMUON, SOLUONG)
VALUES ('HD00002', 'BD00005', 5, 5)