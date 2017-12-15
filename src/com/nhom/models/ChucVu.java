/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.models;

/**
 * @author Mr Thanh Nov 30, 2017
 *
 */
public class ChucVu {
	private int maChucVu;
	private String tenChucVu;
	private String ghiChu;
	
	/**
	 * 
	 */
	public ChucVu(int maChucVu, String tenChucVu, String ghiChu) {
		this.maChucVu = maChucVu;
		this.tenChucVu = tenChucVu;
		this.ghiChu = ghiChu;
	}
	
	public int getMaChucVu() {
		return maChucVu;
	}
	public void setMaChucVu(int maChucVu) {
		this.maChucVu = maChucVu;
	}
	public String getTenChucVu() {
		return tenChucVu;
	}
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	
}
