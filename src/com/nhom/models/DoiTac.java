/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.models;

/**
 * @author Mr Thanh Dec 9, 2017
 *
 */
public class DoiTac {
	private int maNhaPhanPhoi;
	private String tenNhaPhanPhoi;
	private String diaChi;
	private String sdt;
	private String email;
	private String ghiChu;
	
	public DoiTac(int maNhaPhanPhoi, String tenNhaPhanPhoi, String diaChi, String sdt,String email, String ghiChu) {
		this.maNhaPhanPhoi = maNhaPhanPhoi;
		this.tenNhaPhanPhoi = tenNhaPhanPhoi;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.setEmail(email);
		this.ghiChu = ghiChu;
	}
	
	public int getMaNhaPhanPhoi() {
		return maNhaPhanPhoi;
	}
	public void setMaNhaPhanPhoi(int maNhaPhanPhoi) {
		this.maNhaPhanPhoi = maNhaPhanPhoi;
	}
	public String getTenNhaPhanPhoi() {
		return tenNhaPhanPhoi;
	}
	public void setTenNhaPhanPhoi(String tenNhaPhanPhoi) {
		this.tenNhaPhanPhoi = tenNhaPhanPhoi;
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
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
