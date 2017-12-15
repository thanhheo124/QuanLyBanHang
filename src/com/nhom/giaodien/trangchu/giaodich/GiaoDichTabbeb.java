package com.nhom.giaodien.trangchu.giaodich;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.interfacegiaodien.ICommon;

public class GiaoDichTabbeb extends JTabbedPane implements ICommon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GiaoDich_ChiTietPanel chiTietGiaoDichPanel;
	public GiaoDichTabbeb() {
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
		chiTietGiaoDichPanel = new GiaoDich_ChiTietPanel();
		addTab("Chi tiết giao dịch", chiTietGiaoDichPanel);
		
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
