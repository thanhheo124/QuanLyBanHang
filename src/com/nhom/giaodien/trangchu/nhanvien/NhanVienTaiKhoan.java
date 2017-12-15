package com.nhom.giaodien.trangchu.nhanvien;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerTaiKhoan;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.User;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class NhanVienTaiKhoan extends JPanel implements ICommon, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int Table_SoCot = 6;
	private static final String ACTION_REFRESH = "R";
	private static final String ACTION_SUA = "S";
	private static final String ACTION_XOA = "X";
	private JTable table;
	private JTextField textPass;
	private JComboBox<String> cbbQuyen;
	private JTextArea textAreaGhiChu;
	private JButton btnRefresh;
	private JButton btnSua;
	private JButton btnXoa;
	private JTextField textUsername;
	private ArrayList<User> listTK;
	private ControllerTaiKhoan controllerTK;

	private int pos;

	public NhanVienTaiKhoan() {

		init();
		setStyle();
		addComps();
		addActionTable();
		showCbbQuyen();
		loadTableTaiKhoan();
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		controllerTK = new ControllerTaiKhoan();
		listTK = new ArrayList<>();

		JLabel lblNewLabel = new JLabel("Tài khoản");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(324, 13, 93, 27);
		add(lblNewLabel);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(793, 132, 66, 16);
		add(lblUsername);

		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(793, 171, 66, 16);
		add(lblPass);

		JLabel lblQuyn = new JLabel("Quyền");
		lblQuyn.setBounds(793, 218, 66, 16);
		add(lblQuyn);

		JLabel lblChThch = new JLabel("Chú thích");
		lblChThch.setBounds(793, 264, 66, 16);
		add(lblChThch);

		textPass = new JTextField();
		textPass.setColumns(10);
		textPass.setBounds(868, 168, 131, 22);
		add(textPass);

		cbbQuyen = new JComboBox<>();
		cbbQuyen.setBounds(871, 215, 128, 22);
		add(cbbQuyen);

		textAreaGhiChu = new JTextArea();
		textAreaGhiChu.setBounds(871, 261, 214, 110);
		add(textAreaGhiChu);

		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(807, 396, 85, 25);
		add(btnRefresh);
		btnRefresh.setActionCommand(ACTION_REFRESH);
		btnRefresh.addActionListener(this);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(967, 396, 85, 25);
		add(btnSua);
		btnSua.setActionCommand(ACTION_SUA);
		btnSua.addActionListener(this);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(807, 469, 85, 25);
		add(btnXoa);
		btnXoa.setActionCommand(ACTION_XOA);
		btnSua.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 48, 734, 388);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, "", null, null, null, null }, },
				new String[] { "M\u00E3NV", "T\u00EAn NV", "Username", "pass", "Quy\u1EC1n", "Ch\u00FA th\u00EDch" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		scrollPane.setViewportView(table);

		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(868, 129, 131, 22);
		add(textUsername);
	}

	/**
	 * add Action forTable => get post when click;
	 */
	private void addActionTable() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pos = table.getSelectedRow();

				textUsername.setText(listTK.get(pos).getTenDangNhap());
				textPass.setText(listTK.get(pos).getPassword());
				cbbQuyen.setSelectedItem(listTK.get(pos).getQuyen());
			}
		});
	}

	/**
	 * show table
	 */
	private void loadTableTaiKhoan() {
		listTK = controllerTK.getListTK();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[Table_SoCot];
		for (User user : listTK) {
			int i = 0;
			row[i++] = user.getMaNhanVien();
			row[i++] = user.getTenNhanVien();
			row[i++] = user.getTenDangNhap();
			row[i++] = user.getPassword();
			row[i++] = user.getQuyen();
			row[i++] = user.getGhiChu();
			model.addRow(row);
		}
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

	/**
	 * 
	 */
	private void showCbbQuyen() {
		ArrayList<String> listQuyen = new ArrayList<>();
		listQuyen = controllerTK.getListQuyen();
		for (String quyen : listQuyen) {
			cbbQuyen.addItem(quyen);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		switch (command) {
		case ACTION_REFRESH:
			loadTableTaiKhoan();
			break;
		case ACTION_SUA:
			User user = getUser();
			controllerTK.sua(user);
			loadTableTaiKhoan();
			break;
		case ACTION_XOA:

			break;

		default:
			break;
		}
	}

	/**
	 * @return
	 */
	private User getUser() {
		User user = listTK.get(pos);
		user.setTenDangNhap(textUsername.getText());
		user.setPassword(textPass.getText());
		user.setGhiChu(textAreaGhiChu.getText());
		user.setQuyen(cbbQuyen.getSelectedItem().toString());
		return user;
	}
}
