/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.controller;

import java.util.ArrayList;

import com.nhom.database.GiaoDichDaoImpl;
import com.nhom.database.NhapHangDaoImpl;
import com.nhom.models.SanPhamInforChi;
import com.nhom.models.SanPhamInforThu;

/**
 * @author Mr Thanh Dec 10, 2017
 *
 */
public class ControllerDoanhThu {
	private NhapHangDaoImpl nhapHangDaoImpl;
	private GiaoDichDaoImpl giaoDichDaoImpl;
	

	public ControllerDoanhThu() {
		
		nhapHangDaoImpl = new NhapHangDaoImpl();
		giaoDichDaoImpl = new GiaoDichDaoImpl();
	}

	/**
	 * @param dateTimKiem
	 * @return
	 */
	public ArrayList<Integer> getListPhieuNhap(String dateTimKiem) {
		return nhapHangDaoImpl.getListPhieuNhap(dateTimKiem);
	}

	/**
	 * @param listPhieuNhap
	 * @return
	 */
	public ArrayList<SanPhamInforChi> getListSPChi(ArrayList<Integer> listPhieuNhap) {
		return nhapHangDaoImpl.getListSPChi(listPhieuNhap);
	}

	/**
	 * @param dateTimKiem
	 * @return
	 */
	public ArrayList<Integer> getListMaHoaDon(String dateTimKiem) {
		return giaoDichDaoImpl.getListMaHoaDon(dateTimKiem);
	}

	/**
	 * @param listMaHoaDon
	 * @return
	 */
	public ArrayList<SanPhamInforThu> getListSPBan(ArrayList<Integer> listMaHoaDon) {
		return giaoDichDaoImpl.getListSPBan(listMaHoaDon);
	}

	/**
	 * @param listMaHoaDon
	 * @return
	 */
	public int tinhTienMua(ArrayList<Integer> listMaHoaDon) {
		return giaoDichDaoImpl.tinhTienMua(listMaHoaDon);
	}
        
//        public boolean xuatFileChi(ArrayList<SanPhamInforChi> listSPInforChi, int thang, int nam){
//            return giaoDichDaoImpl.xuatFileChi(listSPInforChi, thang, nam);
//        }
        
        public boolean xuatFileDoanhThu(ArrayList<SanPhamInforChi> listSPInforChi,ArrayList<SanPhamInforThu> listSPInforThu, int thang, int nam){
            return giaoDichDaoImpl.xuatFileDoanhThu(listSPInforChi,listSPInforThu, thang, nam);
        }
}
