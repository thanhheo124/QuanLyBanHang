/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.models;

import java.sql.Date;

/**
 * @author Mr Thanh Dec 10, 2017
 *
 */
public class SanPhamInforChi {
	private String tenMatHang;
	private int maMatHang;
	private Date ngayNhap;
	private int giaNhap;
	private int soLuong;
	private int tongTien;

	/**
	 * 
	 */
	public SanPhamInforChi(String tenMatHang, int maMatHang, Date ngayNhap, int giaNhap, int soLuong, int tongTien) {
		this.tenMatHang = tenMatHang;
		this.maMatHang = maMatHang;
		this.ngayNhap = ngayNhap;
		this.giaNhap = giaNhap;
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

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public int getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(int giaNhap) {
		this.giaNhap = giaNhap;
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
