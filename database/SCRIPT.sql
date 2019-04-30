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
    CMND      VARCHAR(20)  NOT NULL,
    HOTEN     NVARCHAR(50) NOT NULL,
    DIENTHOAI VARCHAR(20),
    DIACHI    NVARCHAR(100),
    GIOITINH  INT          NOT NULL,
    NGAYSINH  DATE,
    CONSTRAINT THONGTINCANHAN_CK_GIOITINH CHECK (GIOITINH IN (0, 1)),
    CONSTRAINT THONGTINCANHAN_PK PRIMARY KEY (CMND)
)
GO

CREATE TABLE KHACHHANG
(
    MAKH       VARCHAR(10) NOT NULL,
    CMND       VARCHAR(20) NOT NULL,
    NGAYHETHAN DATE,
    CONSTRAINT KHACHHANG_PK PRIMARY KEY (MAKH),
    CONSTRAINT KHACHHANG_FK_THONGTINCANHAN FOREIGN KEY (CMND) REFERENCES THONGTINCANHAN (CMND) ON DELETE CASCADE
)
GO

CREATE TABLE NHANVIEN
(
    MANV VARCHAR(10) NOT NULL,
    CMND VARCHAR(20) NOT NULL,
    MOTA NVARCHAR(100),
    CONSTRAINT NHANVIEN_PK PRIMARY KEY (MANV),
    CONSTRAINT NHANVIEN_FK_THONGTINCANHAN FOREIGN KEY (CMND) REFERENCES THONGTINCANHAN (CMND) ON DELETE CASCADE
)
GO

CREATE TABLE TAIKHOAN
(
    TENTAIKHOAN VARCHAR(30)   NOT NULL,
    MATKHAU     NVARCHAR(128) NOT NULL,
    LOAITK      SMALLINT      NOT NULL,
    MANV        VARCHAR(10)   NOT NULL,
    CONSTRAINT TAIKHOAN_PK PRIMARY KEY (TENTAIKHOAN),
    CONSTRAINT TAIKHOAN_FK_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN (MANV) ON DELETE CASCADE
)
GO

CREATE TABLE BANGDIA
(
    MABD        NVARCHAR(10) NOT NULL,
    TENBD       NVARCHAR(50) NOT NULL,
    HANGSANXUAT NVARCHAR(50) NOT NULL,
    GHICHU      NVARCHAR(100),
    DONGIA      MONEY        NOT NULL,
    TINHTRANG   INT,
    THELOAI     NVARCHAR(30) NOT NULL,
    SOLUONGTON  INT          NOT NULL,
    CONSTRAINT BANGDIA_PK PRIMARY KEY (MABD),
    CONSTRAINT BANGDIA_CK_TINHTRANG CHECK (TINHTRANG IN (0, 1))
)
GO

CREATE TABLE HOADON
(
    MAHD    VARCHAR(10) NOT NULL,
    NGAYLAP DATE,
    MAKH    VARCHAR(10) NOT NULL,
    CONSTRAINT HOADON_PK PRIMARY KEY (MAHD),
    CONSTRAINT HOADON_FK_KHACHHANG FOREIGN KEY (MAKH) REFERENCES KHACHHANG (MAKH)
)
GO

CREATE TABLE CHITIETHOADON
(
    MAHD           VARCHAR(10)  NOT NULL,
    MABD           NVARCHAR(10) NOT NULL,
    SONGAYDUOCMUON INT          NOT NULL,
    SOLUONG        INT          NOT NULL,
    TINHTRANG      INT          NOT NULL,
    CONSTRAINT CHITIETHOADON_PK PRIMARY KEY (MAHD),
    CONSTRAINT CHITIETHOADON_FK_BANGDIA FOREIGN KEY (MABD) REFERENCES BANGDIA (MABD),
    CONSTRAINT CHITIETHOADON_FK_HOADON FOREIGN KEY (MAHD) REFERENCES HOADON (MAHD) ON DELETE CASCADE
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
SELECT T.CMND,
       K.MAKH,
       HOTEN,
       DIENTHOAI,
       DIACHI,
       GIOITINH,
       NGAYSINH,
       NGAYHETHAN
FROM THONGTINCANHAN T
         INNER JOIN KHACHHANG K
                    ON T.CMND = K.CMND
GO

CREATE VIEW VIEW_THONGTINNHANVIEN AS
SELECT T.CMND,
       N.MANV,
       HOTEN,
       DIENTHOAI,
       DIACHI,
       GIOITINH,
       NGAYSINH,
       MOTA
FROM THONGTINCANHAN T
         INNER JOIN NHANVIEN N
                    ON T.CMND = N.CMND
GO

CREATE VIEW VIEW_HOADON AS
SELECT H.MAHD, H.MAKH, H.NGAYLAP, C.MABD, C.SONGAYDUOCMUON, C.SOLUONG, C.TINHTRANG
FROM HOADON H
         INNER JOIN CHITIETHOADON C
                    ON H.MAHD = C.MAHD
GO


--========== TRIGGER ==========--
CREATE TRIGGER TRG_THEMHOADON
    ON CHITIETHOADON
    AFTER INSERT AS
BEGIN
    UPDATE BANGDIA
    SET SOLUONGTON = SOLUONGTON - (
        SELECT SOLUONG
        FROM inserted
        WHERE MABD = B.MABD
    )
    FROM BANGDIA B
             JOIN inserted I
                  ON B.MABD = I.MABD
END
GO

CREATE TRIGGER TRG_CAPNHATHOADON
    ON CHITIETHOADON
    AFTER UPDATE AS
BEGIN
    UPDATE BANGDIA
    SET SOLUONGTON = SOLUONGTON -
                     (SELECT SOLUONG FROM inserted WHERE MABD = B.MABD) +
                     (SELECT SOLUONG FROM deleted WHERE MABD = B.MABD)
    FROM BANGDIA B
             JOIN deleted D
                  ON B.MABD = D.MABD
end
GO

CREATE TRIGGER TRG_XOAHOADON
    ON CHITIETHOADON
    FOR DELETE AS
BEGIN
    UPDATE BANGDIA
    SET SOLUONGTON = SOLUONGTON + (
        SELECT SUM(SOLUONG)
        FROM deleted
        WHERE MABD = B.MABD)
    FROM BANGDIA B
             JOIN deleted D
                  ON B.MABD = D.MABD
END
GO

--========== FUNCTION ==========--
CREATE PROCEDURE RESET_DATABASE AS
BEGIN
    DELETE FROM CHITIETHOADON
    DELETE FROM HOADON
    DELETE FROM BANGDIA
    DELETE FROM TAIKHOAN WHERE TENTAIKHOAN != 'admin'
    DELETE
    FROM NHANVIEN
    WHERE MANV != (
        SELECT MANV
        FROM TAIKHOAN
        WHERE TENTAIKHOAN = 'admin'
    )
    DELETE FROM KHACHHANG
    DELETE
    FROM THONGTINCANHAN
    WHERE CMND != (
        SELECT CMND
        FROM TAIKHOAN T
                 JOIN NHANVIEN N ON T.MANV = N.MANV
        WHERE TENTAIKHOAN = 'admin'
    )
END
GO

CREATE PROCEDURE THANHTOAN_HOADON(@maHoaDon VARCHAR(10)) AS
BEGIN
    UPDATE CHITIETHOADON
    SET TINHTRANG = 1
    WHERE MAHD = @maHoaDon

    UPDATE BANGDIA
    SET SOLUONGTON = SOLUONGTON + (SELECT SOLUONG FROM CHITIETHOADON WHERE MAHD = @maHoaDon)
    WHERE MABD = (SELECT MABD FROM CHITIETHOADON WHERE MAHD = @maHoaDon)
END
GO

--========== THÊM DỮ LIỆU ==========--
INSERT INTO THONGTINCANHAN (CMND, HOTEN, DIENTHOAI, DIACHI, GIOITINH, NGAYSINH)
VALUES ('111111111', N'Buffalo Coder', '0982505701', N'IUH', 1, '1999-02-24')
GO

INSERT INTO NHANVIEN (MANV, CMND, MOTA)
VALUES ('NV00001', '111111111', N'ADMIN')
GO

INSERT INTO TAIKHOAN (TENTAIKHOAN, MATKHAU, LOAITK, MANV)
VALUES ('admin', 'admin', 1, 'NV00001')
GO