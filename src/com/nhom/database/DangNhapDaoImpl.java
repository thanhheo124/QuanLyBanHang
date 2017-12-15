package com.nhom.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DangNhapDaoImpl extends ConectDataBase {

	public boolean dangNhap(String username, String pass) {
		try {
			conn = getConnection();
			String sql = "select MaNhanVien from Users where TenDangNhap = (?) and Password = (?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return false;
	}

	/**
	 * @param username
	 * @return
	 */
	public int getMaNhanVien(String username) {
		int maNhanVien = 0;
		String sql = "SELECT MaNhanVien FROM Users Where TenDangNhap = (?)";
		try {
			conn =getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				maNhanVien = rs.getInt("MaNhanVien");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return maNhanVien;
	}
}
