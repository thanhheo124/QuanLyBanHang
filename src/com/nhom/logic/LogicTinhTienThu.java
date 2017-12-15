/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.logic;

import java.util.ArrayList;
import java.util.Calendar;

import com.nhom.controller.ControllerDoanhThu;
import com.nhom.models.SanPhamInforThu;

/**
 * @author Mr Thanh Dec 11, 2017
 *
 */
public class LogicTinhTienThu {
	private ControllerDoanhThu controllerDoanhThu;

	/**
	 * @return
	 */
	/**
	 * 
	 */
	public LogicTinhTienThu() {
		controllerDoanhThu = new ControllerDoanhThu();
	}

	public ArrayList<Long> getListThuNhapTheoThang() {

		ArrayList<Long> listThuNhapThang = new ArrayList<>();

		ArrayList<String> listDateTimKiem = getListDateTimKiem();

		for (String dateTimKiem : listDateTimKiem) {
			if (dateTimKiem != null) {
				ArrayList<Integer> listMaHoaDon = controllerDoanhThu.getListMaHoaDon(dateTimKiem);
				long thuNhap = 0;
				if (listMaHoaDon != null) {
					ArrayList<SanPhamInforThu> listSPInforBan = controllerDoanhThu.getListSPBan(listMaHoaDon);
					int tienThu = tinhtienThu(listSPInforBan);
					int tienChi = controllerDoanhThu.tinhTienMua(listMaHoaDon);
					thuNhap = tienThu - tienChi;
				}
				listThuNhapThang.add(thuNhap);
			}
		}
		return listThuNhapThang;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListDateTimKiem() {
		ArrayList<String> listDateTimKiem = new ArrayList<>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 1; i <= 12; i++) {
			String thangString = "";
			if (i < 10) {
				thangString = "0" + i;
			} else {
				thangString = i + "";
			}
			String dateTimKiem = "N'" + year + "-" + thangString + "-%'";
			listDateTimKiem.add(dateTimKiem);
		}
		return listDateTimKiem;
	}

	/**
	 * @param listSPInforBan
	 * @return
	 */
	public int tinhtienThu(ArrayList<SanPhamInforThu> listSPInforBan) {
		int tongTienBan = 0;
		for (SanPhamInforThu sp : listSPInforBan) {
			tongTienBan += sp.getTongTien();
		}
		return tongTienBan;
	}

	/**
	 * @return
	 */
	public ArrayList<Float> getTien() {
		ArrayList<Float> listThuNhapTheoThangConVert = new ArrayList<>();
		ArrayList<Long> listThuNhapTheoThang = getListThuNhapTheoThang();
		for (Long thuNhap : listThuNhapTheoThang) {
			listThuNhapTheoThangConVert.add((float) thuNhap / 1000000);
		}
		return listThuNhapTheoThangConVert;
	}
}
