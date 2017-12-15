/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.GiaoDichDaoImpl;
import com.nhom.database.NhapHangDaoImpl;
import com.nhom.database.SanPhamDaoImpl;
import com.nhom.models.PhieuNhap;
import com.nhom.models.SanPham;

/**
 * @author Mr Thanh Dec 10, 2017
 *
 */
public class ControllerNhapHang {
	private GiaoDichDaoImpl giaoDichDaoImpl;
	private NhapHangDaoImpl nhapHangDaoImpl;

	public ControllerNhapHang() {
		giaoDichDaoImpl = new GiaoDichDaoImpl();
		nhapHangDaoImpl = new NhapHangDaoImpl();
	}

	/**
	 * @return
	 */
	public ArrayList<String> getSize() {
		return giaoDichDaoImpl.getSize();
	}

	/**
	 * @return
	 */
	public ArrayList<String> getMaSP() {
		return giaoDichDaoImpl.getMaSP();
	}

	/**
	 * @param serchSP
	 * @return
	 */
	public ArrayList<SanPham> getSanPham(String serchSP) {
		return nhapHangDaoImpl.getSanPham(serchSP);
	}

	/**
	 * @param hangSanXuat
	 * @return
	 */
	public String getTenHangSanXuat(int hangSanXuat) {
		return new SanPhamDaoImpl().getTenHangSanXuat(hangSanXuat);
	}

	/**
	 * @param tenHangSanXuat
	 * @return
	 */
	public int getMaNhaPhanPhoi(String tenHangSanXuat) {
		return nhapHangDaoImpl.getMaNhaPhanPhoi(tenHangSanXuat);
	}

	/**
	 * @param maNhaPhanPhoi
	 * @param maNhanVien
	 * @return
	 */
	public int taoPhieuNhap(ArrayList<SanPham> listSPDG, int maNhanVien) {
		return nhapHangDaoImpl.taoPhieuNhap(listSPDG, maNhanVien);
	}

	/**
	 * @return
	 */
	public ArrayList<PhieuNhap> getPhieuNhap() {
		return nhapHangDaoImpl.getPhieuNhap();
	}

	/**
	 * @param maNhanVien
	 * @return
	 */
	public String getTenNhanVien(int maNhanVien) {
		return nhapHangDaoImpl.getTenNhanVien(maNhanVien);
	}

	/**
	 * @param listSPGiaoDich
	 * @param maPhieuNhap
	 */
	public void taoChiTietPhieuNhap(ArrayList<SanPham> listSPGiaoDich, int maPhieuNhap) {
		nhapHangDaoImpl.taoChiTietPhieuNhap(listSPGiaoDich,maPhieuNhap);
	}

	/**
	 * @param maPhieuNhap
	 */
	public boolean xuatPhieuNhap(int maPhieuNhap) {
		return nhapHangDaoImpl.xuatPhieuNhap(maPhieuNhap);
	}

}
