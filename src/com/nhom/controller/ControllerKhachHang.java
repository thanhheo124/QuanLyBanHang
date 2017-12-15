/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.KhachHangDaoImpl;
import com.nhom.models.KhachHang;

/**
 * @author Mr Thanh Dec 8, 2017
 *
 */
public class ControllerKhachHang {

	/**
	 * @return
	 */
	public ArrayList<String> getListLoaiKhachHang() {
		return new KhachHangDaoImpl().getListLoaiKhachHang();
	}

	/**
	 * @param tenKhachHang
	 * @param tuoiKH
	 * @param loaiKH
	 * @return
	 */
	public ArrayList<KhachHang> getListKH(String tenKhachHang) {
		return new KhachHangDaoImpl().getListKH(tenKhachHang);
	}

	/**
	 * @param loaiKhachHang
	 * @return
	 */
	public Object getLoaiKHString(int loaiKhachHang) {
		return new KhachHangDaoImpl().getLoaiKHString(loaiKhachHang);
	}

	/**
	 * @return
	 */
	public int getMaLoaiKH(String tenLoaiKH) {
		return new KhachHangDaoImpl().getMaKhachHang(tenLoaiKH);
	}

	/**
	 * @param kh
	 */
	public void addKH(KhachHang kh) {
		new KhachHangDaoImpl().addKH(kh);
	}

	/**
	 * @param kh
	 */
	public void editKH(KhachHang kh) {
		new KhachHangDaoImpl().editKH(kh);
	}

}
