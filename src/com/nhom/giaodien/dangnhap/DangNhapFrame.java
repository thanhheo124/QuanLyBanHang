package com.nhom.giaodien.dangnhap;

import javax.swing.JFrame;

import com.nhom.interfacegiaodien.ICommon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;

/**
 * @author Mr Thanh
 *
 */
public class DangNhapFrame extends JFrame implements ICommon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFUsername;
	private JPasswordField textFPass;
	private JButton btnDangNhap;
	
	public DangNhapFrame() {
		init();
		setStyle();
		addComps();
	}
	@Override
	public void init() {
		setSize(405,300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
	}

	@Override
	public void addComps() {
		textFUsername = new JTextField();
		textFUsername.setBounds(118, 51, 194, 32);
		getContentPane().add(textFUsername);
		textFUsername.setColumns(10);
		
		textFPass = new JPasswordField();
		textFPass.setColumns(10);
		textFPass.setBounds(118, 125, 194, 32);
		getContentPane().add(textFPass);
		
		setBtnDangNhap(new JButton("Đăng nhập"));
		getBtnDangNhap().setBounds(152, 185, 114, 32);
		getContentPane().add(getBtnDangNhap());
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 59, 83, 24);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(12, 133, 83, 24);
		getContentPane().add(lblPassword);
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
	public JButton getBtnDangNhap() {
		return btnDangNhap;
	}
	public void setBtnDangNhap(JButton btnDangNhap) {
		this.btnDangNhap = btnDangNhap;
	}
	public JTextField getTextFUsername() {
		return textFUsername;
	}
	public void setTextFUsername(JTextField textFUsername) {
		this.textFUsername = textFUsername;
	}
	public JTextField getTextFPass() {
		return textFPass;
	}
	public void setTextFPass(JPasswordField textFPass) {
		this.textFPass = textFPass;
	}
	
}
