package com.nhom.giaodien.trangchu.doitac;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerDoiTac;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.DoiTac;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class DoiTacTabbed extends JPanel implements ICommon, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SOCOT_TABLE_DOITAC = 6;
	private static final String ACTION_THEM = "theem";
	private static final String ACTION_RESET = "reset";
	private static final String ACTION_SUA = "sua";
	private JTable tableNhaPhanPhoi;
	private JTextField textFMaNPP;
	private JTextField textFTenNPP;
	private JTextField textFDiaChi;
	private JTextField textFSDT;
	private JTextField textFEmail;
	private JButton btnReset;
	private JTextArea textAreaChuThich;
	private JButton btnThem;
	private JButton btnSua;
	private ControllerDoiTac controllerDoiTac;
	ArrayList<DoiTac> listDoiTac;
	private int pos;

	public DoiTacTabbed() {

		init();
		setStyle();
		addComps();
		actionTableDoiTac();
		showTableDoiTac();
	}

	/**
	 * 
	 */
	private void actionTableDoiTac() {
		tableNhaPhanPhoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pos = tableNhaPhanPhoi.getSelectedRow();
				textAreaChuThich.setText(listDoiTac.get(pos).getGhiChu());
				textFDiaChi.setText(listDoiTac.get(pos).getDiaChi());
				textFEmail.setText(listDoiTac.get(pos).getEmail());
				textFMaNPP.setText(listDoiTac.get(pos).getMaNhaPhanPhoi() + "");
				textFTenNPP.setText(listDoiTac.get(pos).getTenNhaPhanPhoi());
				textFSDT.setText(listDoiTac.get(pos).getSdt());
			}
		});
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		listDoiTac = new ArrayList<>();

		controllerDoiTac = new ControllerDoiTac();

		JLabel lblNhPhnPhi = new JLabel("Nhà phân phối");
		lblNhPhnPhi.setForeground(Color.BLUE);
		lblNhPhnPhi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNhPhnPhi.setBounds(262, 13, 148, 27);
		add(lblNhPhnPhi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 62, 693, 378);
		add(scrollPane);

		JLabel lblNhPhnPhi_1 = new JLabel("Nhà phân phối sản phẩm");
		lblNhPhnPhi_1.setForeground(Color.BLUE);
		lblNhPhnPhi_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNhPhnPhi_1.setBounds(843, 13, 188, 27);
		add(lblNhPhnPhi_1);

		JLabel lblNewLabel = new JLabel("Mã NPP");
		lblNewLabel.setBounds(727, 86, 56, 16);
		add(lblNewLabel);

		JLabel lblTnNpp = new JLabel("Tên NPP");
		lblTnNpp.setBounds(727, 129, 56, 16);
		add(lblTnNpp);

		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setBounds(727, 176, 56, 16);
		add(lblaCh);

		JLabel lblSdt = new JLabel("SDT");
		lblSdt.setBounds(727, 221, 56, 16);
		add(lblSdt);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(727, 267, 56, 16);
		add(lblEmail);

		JLabel lblChThch = new JLabel("Chú thích");
		lblChThch.setBounds(727, 318, 56, 16);
		add(lblChThch);

		tableNhaPhanPhoi = new JTable();
		tableNhaPhanPhoi.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "M\u00E3", "T\u00EAn \u0111\u1ED1i t\u00E1c", "\u0110\u1ECBa ch\u1EC9", "SDT", "Email",
						"Ch\u00FA th\u00EDch" }));
		tableNhaPhanPhoi.getColumnModel().getColumn(0).setPreferredWidth(22);
		tableNhaPhanPhoi.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableNhaPhanPhoi.getColumnModel().getColumn(4).setPreferredWidth(91);
		scrollPane.setViewportView(tableNhaPhanPhoi);

		textFMaNPP = new JTextField();
		textFMaNPP.setBounds(815, 75, 216, 27);
		add(textFMaNPP);
		textFMaNPP.setColumns(10);
		textFMaNPP.setEditable(false);

		textFTenNPP = new JTextField();
		textFTenNPP.setColumns(10);
		textFTenNPP.setBounds(815, 126, 216, 27);
		add(textFTenNPP);

		textFDiaChi = new JTextField();
		textFDiaChi.setColumns(10);
		textFDiaChi.setBounds(815, 173, 216, 27);
		add(textFDiaChi);

		textFSDT = new JTextField();
		textFSDT.setColumns(10);
		textFSDT.setBounds(815, 218, 216, 27);
		add(textFSDT);

		textFEmail = new JTextField();
		textFEmail.setColumns(10);
		textFEmail.setBounds(815, 264, 216, 27);
		add(textFEmail);

		textAreaChuThich = new JTextArea();
		textAreaChuThich.setBounds(815, 315, 220, 130);
		add(textAreaChuThich);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(340, 514, 85, 25);
		add(btnThem);
		btnThem.setActionCommand(ACTION_THEM);
		btnThem.addActionListener(this);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(478, 514, 85, 25);
		add(btnSua);
		btnSua.setActionCommand(ACTION_SUA);
		btnSua.addActionListener(this);

		btnReset = new JButton("Reset");
		btnReset.setBounds(639, 514, 85, 25);
		add(btnReset);
		btnReset.setActionCommand(ACTION_RESET);
		btnReset.addActionListener(this);
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
	private void showTableDoiTac() {
		listDoiTac = controllerDoiTac.getDoiTac();
		DefaultTableModel model = (DefaultTableModel) tableNhaPhanPhoi.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SOCOT_TABLE_DOITAC];
		for (DoiTac doiTac : listDoiTac) {
			int i = 0;
			row[i++] = doiTac.getMaNhaPhanPhoi();
			row[i++] = doiTac.getTenNhaPhanPhoi();
			row[i++] = doiTac.getDiaChi();
			row[i++] = doiTac.getSdt();
			row[i++] = doiTac.getEmail();
			row[i++] = doiTac.getGhiChu();

			model.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		switch (action) {
		case ACTION_THEM:
			DoiTac doiTac = getDoiTacInForm();
			controllerDoiTac.themDoiTac(doiTac);
			showTableDoiTac();
			break;
		case ACTION_SUA:
			doiTac = getDoiTacInForm();
			controllerDoiTac.suaThongTinDoiTac(doiTac);
			showTableDoiTac();
			break;
		case ACTION_RESET:
			showTableDoiTac();
			break;
		default:
			break;
		}
	}

	/**
	 * @return
	 */
	private DoiTac getDoiTacInForm() {
		int maNhaPhanPhoi = Integer.parseInt(textFMaNPP.getText());
		String tenNhaPhanPhoi = textFTenNPP.getText();
		String diaChi = textFDiaChi.getText();
		String sdt = textFSDT.getText();
		String email = textFEmail.getText();
		String ghiChu = textAreaChuThich.getText();
		DoiTac doiTac = new DoiTac(maNhaPhanPhoi, tenNhaPhanPhoi, diaChi, sdt, email, ghiChu);
		return doiTac;
	}
}
