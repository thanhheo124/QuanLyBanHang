package com.nhom.giaodien.trangchu.nhanvien;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.interfacegiaodien.ICommon;

public class NhanVienTabbed extends JTabbedPane implements ICommon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NhanVienPanel nhanVienPanel;
	private NhanVienTaiKhoan	taiKhoanNhanVien;
	private NhanVienChucVu chucVuNhanVien;
	
	public NhanVienTabbed() {
		init();
		setStyle();
		addComps();
	}
	
	public NhanVienPanel getNhanVienPanel() {
		return nhanVienPanel;
	}

	public void setNhanVienPanel(NhanVienPanel nhanVienPanel) {
		this.nhanVienPanel = nhanVienPanel;
	}

	public NhanVienTaiKhoan getTaiKhoanNhanVien() {
		return taiKhoanNhanVien;
	}

	public void setTaiKhoanNhanVien(NhanVienTaiKhoan taiKhoanNhanVien) {
		this.taiKhoanNhanVien = taiKhoanNhanVien;
	}

	public NhanVienChucVu getChucVuNhanVien() {
		return chucVuNhanVien;
	}

	public void setChucVuNhanVien(NhanVienChucVu chucVuNhanVien) {
		this.chucVuNhanVien = chucVuNhanVien;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void init() {
		setTabPlacement(TOP);
	}

	@Override
	public void addComps() {
		nhanVienPanel = new NhanVienPanel();
		addTab("Nhân viên", nhanVienPanel);
		
		taiKhoanNhanVien = new NhanVienTaiKhoan();
		addTab("Tài khoản", taiKhoanNhanVien);
		
		chucVuNhanVien = new NhanVienChucVu();
		addTab("Chức vụ", chucVuNhanVien);
	}

	@Override
	public void setStyle() {
		try {
			UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
