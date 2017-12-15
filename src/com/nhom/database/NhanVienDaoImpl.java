/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhom.models.NhanVien;

/**
 * @author Mr Thanh Nov 29, 2017
 *
 */
public class NhanVienDaoImpl extends ConectDataBase {

	/**
	 * @return
	 */
	public ArrayList<NhanVien> getNhanVien(String name, int maNhanVien) {
		ArrayList<NhanVien> listNV = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "";
			PreparedStatement pst;
			if (maNhanVien == 0) {
				sql = "SELECT n.MaNhanVien, n.TenNhanVien, n.NgaySinh, n.GioiTinh, n.NgayVaoLam, c.TenChucVu, n.DiaChi,"
						+ " n.SoDT, n.GhiChu, n.TrangThai  FROM NhanVien as n LEFT JOIN ChucVu as c "
						+ "ON n.ChucVu = c.MaChucVu" + " WHERE TenNhanVien LIKE (?)";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + name + "%");
			} else {
				sql = "SELECT n.MaNhanVien, n.TenNhanVien, n.NgaySinh, n.GioiTinh, n.NgayVaoLam, c.TenChucVu, n.DiaChi,"
						+ " n.SoDT, n.GhiChu, n.TrangThai  FROM NhanVien as n LEFT JOIN ChucVu as c "
						+ "ON n.ChucVu = c.MaChucVu" + " WHERE MaNhanVien LIKE (?)";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, maNhanVien);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int i = 1;
				int maNV = rs.getInt(i++);
				String tenNhanVien = rs.getString(i++);
				Date ngaySinh = rs.getDate(i++);
				int gioiTinh = rs.getInt(i++);
				Date ngayVaoLam = rs.getDate(i++);
				String chucVu = rs.getString(i++);
				String diaChi = rs.getString(i++);
				String sdt = rs.getString(i++);
				String ghiChu = rs.getString(i++);
				int trangThai = rs.getInt(i);
				NhanVien nv = new NhanVien(maNV, tenNhanVien, ngaySinh, gioiTinh, ngayVaoLam, chucVu, diaChi, sdt,
						ghiChu, trangThai);
				listNV.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listNV;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getChucVu() {
		ArrayList<String> listChucVu = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT DISTINCT TenChucVu from ChucVu";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listChucVu.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listChucVu;
	}

	/**
	 * @param nv
	 * @return
	 */
	public boolean suaNhanVien(NhanVien nv) {
		boolean check = false;
		try {
			int ChucVu = getChucVuByName(nv.getChucVu());
			conn = getConnection();
			String sql = "UPDATE NhanVien SET TenNhanVien = ? , NgaySinh = ?, GioiTinh = ?, NgayVaoLam = ?, ChucVu = ?,"
					+ " DiaChi = ?, SoDT = ?, GhiChu = ?, TrangThai = ? WHERE MaNhanVien = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, nv.getTenNhanVien());
			pst.setDate(i++, nv.getNgaySinh());
			pst.setInt(i++, nv.getGioiTinh());
			pst.setDate(i++, nv.getNgayVaoLam());
			pst.setInt(i++, ChucVu);
			pst.setString(i++, nv.getDiaChi());
			pst.setString(i++, nv.getSoDT());
			pst.setString(i++, nv.getGhiChu());
			pst.setInt(i++, nv.getTrangThai());
			pst.setInt(i++, nv.getMaNhanVien());

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
	 * @param chucVu
	 * @return
	 */
	public int getChucVuByName(String chucVu) {
		int maChucVu = 0;
		try {
			conn = getConnection();
			String sql = "SELECT c.MaChucVu FROM ChucVu as c where c.TenChucVu = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, chucVu);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				maChucVu = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maChucVu;
	}

	/**
	 * @param nv2
	 */
	public int addNhanVien(NhanVien nv2) {
		int generatedKey = 0;
		int chucVu = getChucVuByName(nv2.getChucVu());
		try {
			conn = getConnection();
			String sql = "INSERT INTO NhanVien(TenNhanVien,NgaySinh,GioiTinh,NgayVaoLam,ChucVu,DiaChi,SoDT,GhiChu,TrangThai) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pst.setString(i++, nv2.getTenNhanVien());
			pst.setDate(i++, nv2.getNgaySinh());
			pst.setInt(i++, nv2.getGioiTinh());
			pst.setDate(i++, nv2.getNgayVaoLam());
			pst.setInt(i++, chucVu);
			pst.setString(i++, nv2.getDiaChi());
			pst.setString(i++, nv2.getSoDT());
			pst.setString(i++, nv2.getGhiChu());
			pst.setInt(i, nv2.getTrangThai());
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
