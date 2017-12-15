/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhom.models.DoiTac;

/**
 * @author Mr Thanh Dec 9, 2017
 *
 */
public class DoiTacDaoImpl extends ConectDataBase {

	/**
	 * @return
	 */
	public ArrayList<DoiTac> getListDoiTac() {
		ArrayList<DoiTac> listDT = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT *FROM NhaPhanPhoi";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int i = 1;
				int maNhaPhanPhoi = rs.getInt(i++);
				String tenNhaPhanPhoi = rs.getString(i++);
				String diaChi = rs.getString(i++);
				String sdt = rs.getString(i++);
				String email = rs.getString(i++);
				String ghiChu = rs.getString(i++);

				DoiTac doiTac = new DoiTac(maNhaPhanPhoi, tenNhaPhanPhoi, diaChi, sdt, email, ghiChu);
				listDT.add(doiTac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return listDT;
	}

	/**
	 * @param doiTac
	 * @return
	 */
	public int themDoiTac(DoiTac doiTac) {
		int key = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO NhaPhanPhoi(TenNhaPhanPhoi,DiaChi,SDT,Email,ChuThich) values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pst.setString(i++, doiTac.getTenNhaPhanPhoi());
			pst.setString(i++, doiTac.getDiaChi());
			pst.setString(i++, doiTac.getSdt());
			pst.setString(i++, doiTac.getEmail());
			pst.setString(i++, doiTac.getGhiChu());
			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return key;
	}

	/**
	 * @param doiTac
	 */
	public void suaThongTinDoiTac(DoiTac doiTac) {
		try {
			conn = getConnection();
			String sql = "UPDATE NhaPhanPhoi SET TenNhaPhanPhoi = ? ,DiaChi = ?,SDT = ?,Email = ?,ChuThich = ? WHERE MaNhaPhanPhoi = ?";
			PreparedStatement pst = conn.prepareStatement(sql);

			int i = 1;
			pst.setString(i++, doiTac.getTenNhaPhanPhoi());
			pst.setString(i++, doiTac.getDiaChi());
			pst.setString(i++, doiTac.getSdt());
			pst.setString(i++, doiTac.getEmail());
			pst.setString(i++, doiTac.getGhiChu());
			pst.setInt(i++, doiTac.getMaNhaPhanPhoi());

			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

}
