/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.GiaoDichDaoImpl;
import com.nhom.database.SanPhamDaoImpl;
import com.nhom.models.DanhSachMuaHang;
import com.nhom.models.KhachHang;
import com.nhom.models.SanPham;

/**
 * @author Mr Thanh Nov 25, 2017
 *
 */
public class ControllerGiaoDich {
	private GiaoDichDaoImpl giaoDichDaoImpl;
	private SanPhamDaoImpl sanPhamDaoImpl;

	/**
	 * Contructor default
	 */
	public ControllerGiaoDich() {
		giaoDichDaoImpl = new GiaoDichDaoImpl();
		sanPhamDaoImpl = new SanPhamDaoImpl();
	}

	/**
	 * get list KhachHang from database
	 * 
	 * @return list KhachHang
	 */
	public ArrayList<KhachHang> getKhachHang() {
		return giaoDichDaoImpl.getKhachHang();
	}

	/**
	 * get list HoaDon from database
	 * 
	 * @return list HoaDon
	 */
	public ArrayList<DanhSachMuaHang> getHoaDon(String searchHD) {
		return giaoDichDaoImpl.getHoaDon(searchHD);
	}

	/**
	 * @param searchKHx
	 * @return
	 */
	public ArrayList<KhachHang> getKhachHang(String searchKHx) {
		return giaoDichDaoImpl.getKhachHang(searchKHx);
	}

	/**
	 * @return
	 */
	public ArrayList<SanPham> getSanPham(String searchSP) {
		return giaoDichDaoImpl.getSanPham(searchSP);
	}

	/**
	 * @return listMSP DISTINCT
	 */
	public ArrayList<String> getMaSP() {
		return giaoDichDaoImpl.getMaSP();
	}

	/**
	 * @return
	 */
	public ArrayList<String> getSize() {
		return giaoDichDaoImpl.getSize();
	}

	/**
	 * @param maKhachHang
	 * @param maNhanVien
	 * @return
	 */
	public int createHoaDonMuaHang(int maKhachHang, int maNhanVien,int tongTien) {
		return giaoDichDaoImpl.createHoaDonMuaHang(maKhachHang,maNhanVien,tongTien);
	}

	/**
	 * @param maHoaDon
	 * @param listSPGD
	 * @return 
	 */
	public void createChiTietHoaDon(int maHoaDon, ArrayList<SanPham> listSPGD) {
		giaoDichDaoImpl.createChiTietHoaDon(maHoaDon,listSPGD);
	}

	/**
	 * @param hangSanXuat
	 * @return
	 */
	public String getTenHangSanXuat(int hangSanXuat) {
		return sanPhamDaoImpl.getTenHangSanXuat(hangSanXuat);
	}

	/**
	 * @param maHoaDonXuatFile
	 */
	public int xuatHoaDon(int maHoaDon) {
		return giaoDichDaoImpl.xuatHoaDon(maHoaDon);
	}

}
