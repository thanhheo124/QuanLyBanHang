/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhom.models.ChucVu;

/**
 * @author Mr Thanh Nov 30, 2017
 *
 */
public class ChucVuDaoImpl extends ConectDataBase {

	/**
	 * @return
	 */
	public ArrayList<ChucVu> getListChucVu() {
		ArrayList<ChucVu> listCV = new ArrayList<>();
		try {
			conn = getConnection();

			String sql = "SELECT *FROM ChucVu";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int i = 1;
				int maChucVu = rs.getInt(i++);
				String tenChucVu = rs.getString(i++);
				String ghiChu = rs.getString(i++);
				ChucVu chucVu = new ChucVu(maChucVu, tenChucVu, ghiChu);
				listCV.add(chucVu);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return listCV;
	}

	/**
	 * @param maChucVu
	 * @return
	 */
	public boolean suaChucVu(ChucVu cv) {
		boolean check = false;
		try {
			conn = getConnection();
			String sql = "UPDATE ChucVu SET TenChucVu = ?, GhiChu = ? where MaChucVu = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, cv.getTenChucVu());
			pst.setString(i++, cv.getGhiChu());
			pst.setInt(i, cv.getMaChucVu());
			if (pst.executeUpdate() != 0) {
				check = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return check;
	}

	/**
	 * @param cv
	 * @return
	 */
	public int addChucVu(ChucVu cv) {
		int generatedKey = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO ChucVu(TenChucVu,GhiChu) VALUES(?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pst.setString(i++, cv.getTenChucVu());
			pst.setString(i++, cv.getGhiChu());
			pst.execute();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedKey;
	}

}
