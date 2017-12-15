/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.GiaoDichDaoImpl;
import com.nhom.database.SanPhamDaoImpl;
import com.nhom.models.SanPham;

/**
 * @author Mr Thanh Dec 9, 2017
 *
 */
public class ControllerSanPham {
	private SanPhamDaoImpl sanPhamDaoImpl = new SanPhamDaoImpl();

	/**
	 * @return
	 */
	public ArrayList<SanPham> getListSP() {
		return sanPhamDaoImpl.getListSP();
	}

	/**
	 * @param loaiSanPham
	 * @return
	 */
	public String getTenLoaiSP(int loaiSanPham) {
		return sanPhamDaoImpl.getTenLoaiSP(loaiSanPham);
	}

	/**
	 * @param hangSanXuat
	 * @return
	 */
	public String getTenHangSanXuat(int hangSanXuat) {
		return sanPhamDaoImpl.getTenHangSanXuat(hangSanXuat);
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListLoaiSP() {
		return sanPhamDaoImpl.getListLoaiSP();
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListHangSanXuat() {
		return sanPhamDaoImpl.getListHangSanXuat();
	}

	/**
	 * @param string
	 * @return
	 */
	public int getMaLoaiSP(String tenLoaiSP) {
		return sanPhamDaoImpl.getMaLoaiSP(tenLoaiSP);
	}

	/**
	 * @param string
	 * @return
	 */
	public int getMaHangSanXuat(String tenHangSanXuat) {
		return sanPhamDaoImpl.getMaHangSanXuat(tenHangSanXuat);
	}

	/**
	 * @return
	 */
	public ArrayList<String> getSize() {
		return new GiaoDichDaoImpl().getSize();
	}

	/**
	 * @param sp
	 */
	public void addSanPham(SanPham sp) {
		sanPhamDaoImpl.addSanPham(sp);
	}

	/**
	 * @param sp
	 */
	public void suaSanPham(SanPham sp) {
		sanPhamDaoImpl.suaSanPham(sp);
	}
}
