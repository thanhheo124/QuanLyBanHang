/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.DoiTacDaoImpl;
import com.nhom.models.DoiTac;

/**
 * @author Mr Thanh Dec 9, 2017
 *
 */
public class ControllerDoiTac {
	private DoiTacDaoImpl doiTacDaoImpl = new DoiTacDaoImpl();

	/**
	 * @return
	 */
	public ArrayList<DoiTac> getDoiTac() {
		return doiTacDaoImpl.getListDoiTac();
	}

	/**
	 * 
	 */
	public int themDoiTac(DoiTac doiTac) {
		return doiTacDaoImpl.themDoiTac(doiTac);
	}

	/**
	 * @param doiTac
	 */
	public void suaThongTinDoiTac(DoiTac doiTac) {
		doiTacDaoImpl.suaThongTinDoiTac(doiTac);
	}

}
