package com.nhom.models;

import java.sql.Date;

public class KhachHang {
	
	private int maKhachHang;
	private String tenKhachHang;
	private Date ngaySinh;
	private int gioiTinh;
	private String diaChi;
	private String sdt;
	private int loaiKhachHang;
	private String ghiChu;
	public KhachHang(int maKhachHang, String tenKhachHang, Date ngaySinh, int gioiTinh, String diaChi, String sdt,
			int loaiKhachHang, String ghiChu) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.loaiKhachHang = loaiKhachHang;
		this.ghiChu = ghiChu;
	}
	public KhachHang() {
		super();
	}
	public int getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public int getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public int getLoaiKhachHang() {
		return loaiKhachHang;
	}
	public void setLoaiKhachHang(int loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	

}
