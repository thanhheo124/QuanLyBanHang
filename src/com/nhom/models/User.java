/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.models;

/**
 * @author Mr Thanh Dec 7, 2017
 *
 */
public class User {
	private int ID;
	private int maNhanVien;
	private String tenNhanVien;
	private String tenDangNhap;
	private String password;
	private String quyen;
	private String ghiChu;
	
	/**
	 * 
	 */
	public User() {
	}
	/**
	 * 
	 */
	public User(int ID,int maNhanVien, String tenNhanVien,String tenDangNhap, String pass, String quyen, String ghiChu) {
		this.ID = ID;
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.tenDangNhap = tenDangNhap;
		this.password = pass;
		this.quyen = quyen;
		this.ghiChu = ghiChu;
	}
	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}
	
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuyen() {
		return quyen;
	}
	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	
}
