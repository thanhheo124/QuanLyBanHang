package com.nhom.models;

public class SanPham {
	
	private int maSanPham;
	private String tenSanPham;
	private int loaiSanPham;
	private int hangSanXuat;
	private int giaNhap;
	private int giaBan;
	private int tonKho;
	private int trangThai;
	private String image;
	private String chuThich;
	private int size;
	public SanPham(int maSanPham, String tenSanPham, int loaiSanPham, int hangSanXuat, int giaNhap, int giaBan,
			int tonKho, int trangThai, String image, String chuThich, int size) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.loaiSanPham = loaiSanPham;
		this.hangSanXuat = hangSanXuat;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.tonKho = tonKho;
		this.trangThai = trangThai;
		this.image = image;
		this.chuThich = chuThich;
		this.size = size;
	}
	public SanPham() {
		super();
	}
	public int getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public int getHangSanXuat() {
		return hangSanXuat;
	}
	public void setHangSanXuat(int hangSanXuat) {
		this.hangSanXuat = hangSanXuat;
	}
	public int getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(int giaNhap) {
		this.giaNhap = giaNhap;
	}
	public int getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(int giaBan) {
		this.giaBan = giaBan;
	}
	public int getTonKho() {
		return tonKho;
	}
	public void setTonKho(int tonKho) {
		this.tonKho = tonKho;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getChuThich() {
		return chuThich;
	}
	public void setChuThich(String chuThich) {
		this.chuThich = chuThich;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getLoaiSanPham() {
		return loaiSanPham;
	}
	public void setLoaiSanPham(int loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}
	
	
	
	

}
