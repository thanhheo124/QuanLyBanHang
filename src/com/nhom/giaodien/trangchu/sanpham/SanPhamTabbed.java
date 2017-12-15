package com.nhom.giaodien.trangchu.sanpham;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.interfacegiaodien.ICommon;

public class SanPhamTabbed extends JTabbedPane implements ICommon {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SanPhamPanel sanPhamPanel;
	private PhieuNhapHang phieuNhapHang;

	public SanPhamTabbed() {
		init();
		setStyle();
		addComps();
	}
	
	@Override
	public void init() {
		setTabPlacement(TOP);
	}

	@Override
	public void addComps() {
		sanPhamPanel = new SanPhamPanel();
		addTab("Sản phẩm", sanPhamPanel);
		
		phieuNhapHang = new PhieuNhapHang();
		addTab("Phiếu nhập hàng",phieuNhapHang);
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
