/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.ChucVuDaoImpl;
import com.nhom.models.ChucVu;

/**
 * @author Mr Thanh Nov 30, 2017
 *
 */
public class ControllerChucVu {
	private ChucVuDaoImpl chucVuDaoImpl = new ChucVuDaoImpl();

	/**
	 * @return
	 */
	public ArrayList<ChucVu> getListChucVu() {
		return chucVuDaoImpl.getListChucVu();
	}

	/**
	 * @param maChucVu
	 */
	public boolean suaChucVu(ChucVu cv) {
		return chucVuDaoImpl.suaChucVu(cv);
	}

	/**
	 * @param cv
	 */
	public int addChucVu(ChucVu cv) {
		return chucVuDaoImpl.addChucVu(cv); 
	}
	
}
