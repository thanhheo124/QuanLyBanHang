package com.nhom.giaodien.trangchu.chungtoi;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.interfacegiaodien.ICommon;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class ChungToiTabbed extends JPanel implements ICommon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChungToiTabbed() {
		setLayout(null);
		
		JTextArea txtrNhmKS = new JTextArea();
		txtrNhmKS.setFont(new Font("Courier New", Font.ITALIC, 25));
		txtrNhmKS.setForeground(new Color(0, 0, 139));
		txtrNhmKS.setBackground(SystemColor.control);
		txtrNhmKS.setText("Nhóm Kỹ sư Văn Bằng 2 - CNTT - ĐHBKHN\r\n\r\n1. Nguyễn Đức Thành\r\n2. Nguyễn Thị Huyền Trang\r\n3. Quang Vinh\r\n4. Lê Ngọc Thành Vinh\r\n");
		txtrNhmKS.setBounds(123, 70, 827, 423);
		add(txtrNhmKS);
		init();
		setStyle();
		addComps();
	}
	
	@Override
	public void init() {
		
		@SuppressWarnings("unused")
		JTextArea textArea = new JTextArea();
	}

	@Override
	public void addComps() {
		
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
