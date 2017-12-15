package com.nhom.giaodien.trangchu;

import javax.swing.JFrame;

import com.nhom.interfacegiaodien.ICommon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class TrangChuFrame extends JFrame implements ICommon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblDangXuat;
	private TabbebRoot tabbebRoot;

	public TrangChuFrame() {
		init();
		setStyle();
		addComps();
	}

	@Override
	public void init() {
		setBounds(100, 100, 1290, 750);
		getContentPane().setLayout(null);

		setTitle("Trang Chủ");
		setLocationRelativeTo(null);
		setResizable(false);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void addComps() {
		lblDangXuat = new JLabel("<html><u>Đăng xuất</u></html>");
		lblDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDangXuat.setForeground(Color.BLUE);
		lblDangXuat.setBounds(1171, 13, 72, 26);
		getContentPane().add(lblDangXuat);
		tabbebRoot = new TabbebRoot();
		getContentPane().add(tabbebRoot);

	}

	@Override
	public void setStyle() {

	}

	public JLabel getLblDangXuat() {
		return lblDangXuat;
	}

}
