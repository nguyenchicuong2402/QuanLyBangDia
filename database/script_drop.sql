USE [QUANLYBANGDIA]
GO
/****** Object:  Trigger [TRG_XOADONHAN]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TRIGGER [dbo].[TRG_XOADONHAN]
GO
/****** Object:  Trigger [TRG_THEMHOADON]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TRIGGER [dbo].[TRG_THEMHOADON]
GO
/****** Object:  Trigger [TRG_CAPNHATHOADON]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TRIGGER [dbo].[TRG_CAPNHATHOADON]
GO
/****** Object:  StoredProcedure [dbo].[THANHTOAN_HOADON]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP PROCEDURE [dbo].[THANHTOAN_HOADON]
GO
/****** Object:  StoredProcedure [dbo].[RESET_DATABASE]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP PROCEDURE [dbo].[RESET_DATABASE]
GO
ALTER TABLE [dbo].[THONGTINCANHAN] DROP CONSTRAINT [THONGTINCANHAN_CK_GIOITINH]
GO
ALTER TABLE [dbo].[BANGDIA] DROP CONSTRAINT [BANGDIA_CK_TINHTRANG]
GO
ALTER TABLE [dbo].[TAIKHOAN] DROP CONSTRAINT [TAIKHOAN_FK]
GO
ALTER TABLE [dbo].[NHANVIEN] DROP CONSTRAINT [NHANVIEN_FK]
GO
ALTER TABLE [dbo].[KHACHHANG] DROP CONSTRAINT [KHACHHANG_FK]
GO
ALTER TABLE [dbo].[HOADON] DROP CONSTRAINT [HOADON_FK_KHACHHANG]
GO
ALTER TABLE [dbo].[CHITIETHOADON] DROP CONSTRAINT [CHITIETHOADON_FK_BANGDIA]
GO
ALTER TABLE [dbo].[KHACHHANG] DROP CONSTRAINT [KHACHHANG_DF_NGAYHETHAN]
GO
ALTER TABLE [dbo].[HOADON] DROP CONSTRAINT [HOADON_DF_NGAYLAP]
GO
ALTER TABLE [dbo].[BANGDIA] DROP CONSTRAINT [BANGDIA_DF_TINHTRANG]
GO
/****** Object:  Table [dbo].[TAIKHOAN]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TABLE [dbo].[TAIKHOAN]
GO
/****** Object:  Table [dbo].[BANGDIA]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TABLE [dbo].[BANGDIA]
GO
/****** Object:  View [dbo].[VIEW_HOADON]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP VIEW [dbo].[VIEW_HOADON]
GO
/****** Object:  Table [dbo].[CHITIETHOADON]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TABLE [dbo].[CHITIETHOADON]
GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TABLE [dbo].[HOADON]
GO
/****** Object:  View [dbo].[VIEW_THONGTINNHANVIEN]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP VIEW [dbo].[VIEW_THONGTINNHANVIEN]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TABLE [dbo].[NHANVIEN]
GO
/****** Object:  View [dbo].[VIEW_THONGTINKHACHHANG]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP VIEW [dbo].[VIEW_THONGTINKHACHHANG]
GO
/****** Object:  Table [dbo].[KHACHHANG]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TABLE [dbo].[KHACHHANG]
GO
/****** Object:  Table [dbo].[THONGTINCANHAN]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP TABLE [dbo].[THONGTINCANHAN]
GO
USE [master]
GO
/****** Object:  Database [QUANLYBANGDIA]    Script Date: 4/13/2019 2:45:47 PM ******/
DROP DATABASE [QUANLYBANGDIA]
GO