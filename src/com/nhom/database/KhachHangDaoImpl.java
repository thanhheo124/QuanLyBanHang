/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhom.models.KhachHang;

/**
 * @author Mr Thanh Dec 8, 2017
 *
 */
public class KhachHangDaoImpl extends ConectDataBase {

	/**
	 * @return
	 */
	public ArrayList<String> getListLoaiKhachHang() {
		ArrayList<String> listLoaiKH = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT DISTINCT TenLoaiKhachHang FROM LoaiKhachHang";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tenLoaiKhachHang = rs.getString(1);
				listLoaiKH.add(tenLoaiKhachHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listLoaiKH;
	}

	/**
	 * @param tenKhachHang
	 * @param tuoiKH
	 * @param loaiKH
	 * @return
	 */
	public ArrayList<KhachHang> getListKH(String textSearch) {
		ArrayList<KhachHang> listKH = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT *FROM KhachHang WHERE ");
			sql.append("lower(CONCAT(MaKhachHang,TenKhachHang,LoaiKhachHang)) LIKE lower(?)");
			conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			int i = 1;
			pst.setString(i++, "%" + textSearch + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int k = 1;
				int maKhachHang = rs.getInt(k++);
				String tenKhachHang = rs.getString(k++);
				Date ngaySinh = rs.getDate(k++);
				int gioiTinh = rs.getInt(k++);
				String diaChi = rs.getString(k++);
				String sdt = rs.getString(k++);
				int loaiKhachHang = rs.getInt(k++);
				String ghiChu = rs.getString(k++);

				KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, ngaySinh, gioiTinh, diaChi, sdt, loaiKhachHang,
						ghiChu);
				listKH.add(kh);
			}

			conn = getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listKH;
	}

	/**
	 * @param loaiKH
	 * @return
	 */
	public int getMaKhachHang(String loaiKH) {
		int maKhachHang = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MaLoaiKhachHang FROM LoaiKhachHang WHERE TenLoaiKhachHang = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, loaiKH);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				maKhachHang = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maKhachHang;
	}

	/**
	 * @param loaiKhachHang
	 * @return
	 */
	public Object getLoaiKHString(int maKhachHang) {
		String loaiKH = "";
		try {
			conn = getConnection();
			String sql = "SELECT TenLoaiKhachHang FROM LoaiKhachHang WHERE MaLoaiKhachHang = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, maKhachHang);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				loaiKH = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return loaiKH;
	}

	/**
	 * @param kh
	 */
	public int addKH(KhachHang kh) {
		int key = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO KhachHang(TenKhachHang,Ngaysinh,GioiTinh,DiaChi,SDT,LoaiKhachHang,GhiChu) values(?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			pst.setString(i++, kh.getTenKhachHang());
			pst.setDate(i++, kh.getNgaySinh());
			pst.setInt(i++, kh.getGioiTinh());
			pst.setString(i++, kh.getDiaChi());
			pst.setString(i++, kh.getSdt());
			pst.setInt(i++, kh.getLoaiKhachHang());
			pst.setString(i++, kh.getGhiChu());
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
	 * @param kh
	 */
	public void editKH(KhachHang kh) {
		try {
			conn = getConnection();
			String sql = "UPDATE KhachHang "
					+ "SET TenKhachHang = ?, Ngaysinh = ?,GioiTinh = ? , DiaChi = ?,SDT = ?, LoaiKhachHang = ?, GhiChu = ? "
					+ "WHERE MaKhachHang = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, kh.getTenKhachHang());
			pst.setDate(i++, kh.getNgaySinh());
			pst.setInt(i++, kh.getGioiTinh());
			pst.setString(i++, kh.getDiaChi());
			pst.setString(i++, kh.getSdt());
			pst.setInt(i++, kh.getLoaiKhachHang());
			pst.setString(i++, kh.getGhiChu());
			pst.setInt(i++, kh.getMaKhachHang());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

}
