/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.TaiKhoanDaoImpl;
import com.nhom.models.User;

/**
 * @author Mr Thanh Dec 7, 2017
 *
 */
public class ControllerTaiKhoan {

	/**
	 * @return
	 */
	public ArrayList<User> getListTK() {
		return new TaiKhoanDaoImpl().getListTK();
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListQuyen() {
		return new TaiKhoanDaoImpl().getListQuyen();
	}

	/**
	 * @param user
	 */
	public void sua(User user) {
		new TaiKhoanDaoImpl().editUser(user);
	}
	
}
