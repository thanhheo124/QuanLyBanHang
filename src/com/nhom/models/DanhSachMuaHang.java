/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.models;

import java.sql.Date;

/**
 * @author Mr Thanh Nov 25, 2017
 *
 */
public class DanhSachMuaHang {
	private int maHoaDon;
	private String tenKhachHang;
	private String tenNhanVien;
	private Date ngayLap;
	private int tongTien;
	private String ghiChu;
	
	/**
	 * contructor có tham số
	 */
	public DanhSachMuaHang(int maHoaDon,String tenKhachHang, String tenNhanVien, Date ngayLap, int tongTien,String ghiChu) {
		this.maHoaDon = maHoaDon;
		this.tenKhachHang = tenKhachHang;
		this.tenNhanVien = tenNhanVien;
		this.ngayLap = ngayLap;
		this.tongTien = tongTien;
		this.ghiChu = ghiChu;
	}
	
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	
}
