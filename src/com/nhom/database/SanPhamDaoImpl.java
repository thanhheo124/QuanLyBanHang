/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhom.models.SanPham;

/**
 * @author Mr Thanh Dec 9, 2017
 *
 */
public class SanPhamDaoImpl extends ConectDataBase {

	/**
	 * @return
	 */
	public ArrayList<SanPham> getListSP() {
		ArrayList<SanPham> listSP = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT *FROM SanPham";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int i = 1;
				int maSanPham = rs.getInt(i++);
				String tenSanPham = rs.getString(i++);
				int loaiSanPham = rs.getInt(i++);
				int hangSanXuat = rs.getInt(i++);
				int giaNhap = rs.getInt(i++);
				int giaBan = rs.getInt(i++);
				int tonKho = rs.getInt(i++);
				int trangThai = rs.getInt(i++);
				String image = rs.getString(i++);
				String chuThich = rs.getString(i++);
				int size = rs.getInt(i++);
				SanPham sp = new SanPham(maSanPham, tenSanPham, loaiSanPham, hangSanXuat, giaNhap, giaBan, tonKho,
						trangThai, image, chuThich, size);
				listSP.add(sp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listSP;
	}

	/**
	 * @param hangSanXuat
	 * @return
	 */
	public String getTenHangSanXuat(int hangSanXuat) {
		String tenHangSanXuat = "";
		try {
			conn = getConnection();
			String sql = "SELECT TenHangSanXuat FROM HangSanXuat where MaHangSanXuat = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, hangSanXuat);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tenHangSanXuat = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return tenHangSanXuat;
	}

	/**
	 * @param loaiSanPham
	 * @return
	 */
	public String getTenLoaiSP(int loaiSanPham) {
		String tenLoaiSP = "";
		try {
			conn = getConnection();
			String sql = "SELECT TenLoaiSanPham FROM LoaiSanPham where MaLoaiSanPham = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, loaiSanPham);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tenLoaiSP = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return tenLoaiSP;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListLoaiSP() {
		ArrayList<String> listLoaiSP = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT TenLoaiSanPham From LoaiSanPham";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tenLoaiSP = rs.getString(1);
				listLoaiSP.add(tenLoaiSP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listLoaiSP;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListHangSanXuat() {
		ArrayList<String> listTenHangSanXuat = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT TenHangSanXuat From HangSanXuat";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tenHangSanXuat = rs.getString(1);
				listTenHangSanXuat.add(tenHangSanXuat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listTenHangSanXuat;
	}

	/**
	 * @param tenLoaiSP
	 * @return
	 */
	public int getMaLoaiSP(String tenLoaiSP) {
		int maLoaiSP = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MaLoaiSanPham FROM LoaiSanPham where TenLoaiSanPham = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tenLoaiSP);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				maLoaiSP = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maLoaiSP;
	}

	/**
	 * @param tenHangSanXuat
	 * @return
	 */
	public int getMaHangSanXuat(String tenHangSanXuat) {
		int maHangSanXuat = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MaHangSanXuat FROM HangSanXuat where TenHangSanXuat = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tenHangSanXuat);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				maHangSanXuat = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maHangSanXuat;
	}

	/**
	 * @param sp
	 */
	public int addSanPham(SanPham sp) {
		int key = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO SanPham(TenSanPham,LoaiSanPham,HangSanXuat,GiaNhap,GiaBan,TonKho,TrangThai,Image,ChuThich,Size)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pst.setString(i++, sp.getTenSanPham());
			pst.setInt(i++, sp.getLoaiSanPham());
			pst.setInt(i++, sp.getHangSanXuat());
			pst.setInt(i++, sp.getGiaNhap());
			pst.setInt(i++, sp.getGiaBan());
			pst.setInt(i++, sp.getTonKho());
			pst.setInt(i++, sp.getTrangThai());
			pst.setString(i++, sp.getImage());
			pst.setString(i++, sp.getChuThich());
			pst.setInt(i++, sp.getSize());
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
	 * @param sp
	 */
	public void suaSanPham(SanPham sp) {
		try {
			conn = getConnection();
			String sql = "UPDATE SanPham SET TenSanPham = ?,LoaiSanPham = ?,HangSanXuat = ?,GiaNhap = ?,"
					+ "GiaBan = ?,TonKho = ?,TrangThai = ?,Image = ?,ChuThich = ?,Size = ? WHERE MaSanPham = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, sp.getTenSanPham());
			pst.setInt(i++, sp.getLoaiSanPham());
			pst.setInt(i++, sp.getHangSanXuat());
			pst.setInt(i++, sp.getGiaNhap());
			pst.setInt(i++, sp.getGiaBan());
			pst.setInt(i++, sp.getTonKho());
			pst.setInt(i++, sp.getTrangThai());
			pst.setString(i++, sp.getImage());
			pst.setString(i++, sp.getChuThich());
			pst.setInt(i++, sp.getSize());
			pst.setInt(i++, sp.getMaSanPham());
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

}
