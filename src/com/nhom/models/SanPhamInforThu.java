/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.models;

import java.sql.Date;

/**
 * @author Mr Thanh Dec 10, 2017
 *
 */
public class SanPhamInforThu {
	private String tenMatHang;
	private int maMatHang;
	private Date ngayBan;
	private int giaBan;
	private int chietKhau;
	private int soLuong;
	private int tongTien;

	public SanPhamInforThu(String tenMatHang, int maMatHang, Date ngayBan, int giaBan, int chietKhau, int soLuong,
			int tongTien) {
		this.tenMatHang = tenMatHang;
		this.maMatHang = maMatHang;
		this.ngayBan = ngayBan;
		this.giaBan = giaBan;
		this.chietKhau = chietKhau;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}

	public String getTenMatHang() {
		return tenMatHang;
	}

	public void setTenMatHang(String tenMatHang) {
		this.tenMatHang = tenMatHang;
	}

	public int getMaMatHang() {
		return maMatHang;
	}

	public void setMaMatHang(int maMatHang) {
		this.maMatHang = maMatHang;
	}

	public Date getNgayBan() {
		return ngayBan;
	}

	public void setNgayBan(Date ngayBan) {
		this.ngayBan = ngayBan;
	}

	public int getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}

	public int getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(int chietKhau) {
		this.chietKhau = chietKhau;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

}
