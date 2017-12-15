/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.NhanVienDaoImpl;
import com.nhom.models.NhanVien;

/**
 * @author Mr Thanh Nov 29, 2017
 *
 */
public class ControllerNhanVien {
	private NhanVienDaoImpl nhanVienDaoImpl = new NhanVienDaoImpl();
	
	public ArrayList<NhanVien> getNhanVien(String name, int maNhanVien){
		return nhanVienDaoImpl.getNhanVien(name,maNhanVien);
	}

	/**
	 * @return
	 */
	public ArrayList<String> getChucVu() {
		return nhanVienDaoImpl.getChucVu();
	}

	/**
	 * @param nv
	 */
	public void suaNhanVien(NhanVien nv) {
		nhanVienDaoImpl.suaNhanVien(nv);
	}

	/**
	 * @param nv2
	 */
	public int addNhanVien(NhanVien nv2) {
		return nhanVienDaoImpl.addNhanVien(nv2);
	}

}
