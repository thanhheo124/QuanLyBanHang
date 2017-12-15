package com.nhom.models;

import java.sql.Date;

public class PhieuNhap {

	private int maPhieuNhap;
	private int maNhanVien;
	private int tongTien;
	private Date ngayNhap;
	private String chuThich;

	public int getMaPhieuNhap() {
		return maPhieuNhap;
	}

	public void setMaPhieuNhap(int maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public String getChuThich() {
		return chuThich;
	}

	public void setChuThich(String chuThich) {
		this.chuThich = chuThich;
	}


	public PhieuNhap(int maPhieuNhap, int maNhanVien, int tongTien, Date ngayNhap, String chuThich) {
		super();
		this.maPhieuNhap = maPhieuNhap;
		this.maNhanVien = maNhanVien;
		this.tongTien = tongTien;
		this.ngayNhap = ngayNhap;
		this.chuThich = chuThich;
	}

	public PhieuNhap() {
		super();
	}

}
