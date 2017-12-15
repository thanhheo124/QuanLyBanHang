package com.nhom.giaodien;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusStyle;

import com.nhom.controller.ControllerDangNhap;
import com.nhom.giaodien.dangnhap.DangNhapFrame;
import com.nhom.giaodien.trangchu.TrangChuFrame;
import com.nhom.interfacegiaodien.ICommon;

public class GuiRoot implements ICommon{
	private TrangChuFrame trangChu;
	private DangNhapFrame dangNhap;
	private ControllerDangNhap controller;
	public static int maNhanVien;
	public GuiRoot() {
		init();
//		setStyle();
		addComps();
		xuLy();
	}
	
	private void xuLy() {
		dangNhap.getBtnDangNhap().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = dangNhap.getTextFUsername().getText();
				String pass = dangNhap.getTextFPass().getText();
				if("".equals(username)||"".equals(pass)){
					JOptionPane.showMessageDialog(null, "Username hoặc pass không được trống");
				}
				else if(controller.dangNhap(username, pass)){ // đăng nhập thành công
					maNhanVien = controller.getMaNhanVienByUserName(username);
					dangNhap.setVisible(false);
					trangChu.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "User name hoặc pass sai");
				}
			}
		});
		trangChu.getLblDangXuat().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dangNhap.setVisible(true);
				trangChu.setVisible(false);
			}
		});
	}

	@Override
	public void init() {
	}

	@Override
	public void addComps() {
		controller = new ControllerDangNhap();
		dangNhap = new DangNhapFrame();
		dangNhap.setVisible(true);
		
		trangChu = new TrangChuFrame();
//		trangChu.setVisible(true);
		
	}

	@Override
	public void setStyle() {
		try {
			UIManager.setLookAndFeel(NimbusStyle.class.getName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
		
}
