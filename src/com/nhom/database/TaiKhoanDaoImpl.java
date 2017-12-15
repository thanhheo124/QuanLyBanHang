/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nhom.models.User;

/**
 * @author Mr Thanh Dec 7, 2017
 *
 */
public class TaiKhoanDaoImpl extends ConectDataBase {

	/**
	 * @return
	 */
	public ArrayList<User> getListTK() {
		ArrayList<User> listTK = new ArrayList<>();
		try {
			String sql = "SELECT u.ID,n.MaNhanVien,n.TenNhanVien,u.TenDangNhap,u.Password,q.TenQuyen,u.ChuThich"
					+ " FROM NhanVien as n LEFT JOIN Users as u ON n.MaNhanVien = u.MaNhanVien"
					+ " LEFT JOIN Quyen as q ON u.Quyen = q.MaQuyen;";
			conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				int i = 1;
				int ID = rs.getInt(i++);
				int maNhanVien = rs.getInt(i++);
				String tenNhanVien = rs.getString(i++);
				String tenDangNhap =  rs.getString(i++);
				String pass =  rs.getString(i++);
				String quyen =  rs.getString(i++);
				String ghiChu =  rs.getString(i++);
				User user = new User(ID,maNhanVien, tenNhanVien, tenDangNhap, pass, quyen, ghiChu);
				listTK.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listTK;
	}

	/**
	 * @return listQuyen
	 */
	public ArrayList<String> getListQuyen() {
		ArrayList<String> listQuyen = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT DISTINCT TenQuyen FROM Quyen;";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String quyen = rs.getString(1);
				listQuyen.add(quyen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return listQuyen;
	}

	/**
	 * 
	 */
	public void editUser(User user) {
		int quyen = getMaQuyen(user.getQuyen());
		try {
			conn = getConnection();
			String sql = "UPDATE Users SET TenDangNhap = ?, Password = ?, Quyen = ?, ChuThich = ? where ID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, user.getTenDangNhap());
			pst.setString(i++, user.getPassword());
			pst.setInt(i++, quyen);
			pst.setString(i++, user.getGhiChu());
			pst.setInt(i++, user.getID());
			
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
	}

	/**
	 * @param quyen
	 * @return
	 */
	private int getMaQuyen(String tenQuyen) {
		int maQuyen = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MaQuyen From Quyen Where TenQuyen = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tenQuyen);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				maQuyen = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		return maQuyen;
	}
}
