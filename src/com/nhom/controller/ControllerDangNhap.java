package com.nhom.controller;


import com.nhom.database.DangNhapDaoImpl;

public class ControllerDangNhap {
	private DangNhapDaoImpl dangNhapDaoImpl;
	
	public ControllerDangNhap() {
		dangNhapDaoImpl = new DangNhapDaoImpl();
	}
	
	public boolean dangNhap(String username, String pass){
		return dangNhapDaoImpl.dangNhap(username,pass);
	}

	/**
	 * @param username
	 * @return
	 */
	public int getMaNhanVienByUserName(String username) {
		return dangNhapDaoImpl.getMaNhanVien(username);
	}
	
	
}
