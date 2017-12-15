package com.nhom.giaodien.trangchu.khachhang;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerKhachHang;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.KhachHang;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXSearchField;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;

public class KhachHangTabbed extends JPanel implements ICommon, ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SOCOT_TABLE_KHACHHANG = 8;
	private static final String ACTION_THEM = "Thêm";
	private static final String ACTION_SUA = "Sửa";
	private static final String ACTION_RESET = "Resets";
	private static final String ACTION_NAM = "Nam";
	private static final String ACTIONH_NU = "Nữ";
	private static final String ACTION_TIMKIEM = "Timkiem";
	private static final String ACTION_XUATFILE = "XuatFile";
	private static final String ACTION_THEMKHTUFILE = "Them File";
	private JTable tableKhachHang;
	private JTextField textFMaKH;
	private JTextField textFTenKhachHang;
	private JTextField textFSDT;
	private JTextField textFDiaChi;
	private JXSearchField textFTen;
	private JDateChooser dateChooserNgaySinh;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	private JComboBox<String> cbbLoaiKH;
	private JTextArea textAreaGhiChu;
	private JButton btnThem;
	private JButton btnReset;
	private JButton btnSua;
	private ControllerKhachHang controllerKhachHang;
	private ArrayList<KhachHang> listKH;
	private int pos;
	private JButton buttonXuatFile;
	private JButton btnThemKhachHangTuFile;

	public KhachHangTabbed() {

		init();
		setStyle();
		addComps();
		showCbbLoaiKhachHangTiemKiem();
		actionTableKH();
		showTableKhachHang("");
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		listKH = new ArrayList<>();
		controllerKhachHang = new ControllerKhachHang();
		JLabel lblNewLabel = new JLabel("Khách hàng");
		lblNewLabel.setBounds(485, 13, 121, 23);
		add(lblNewLabel);

		JLabel lblMKh = new JLabel("Mã KH");
		lblMKh.setBounds(37, 367, 85, 16);
		add(lblMKh);

		JLabel lblTnKh = new JLabel("Tên KH");
		lblTnKh.setBounds(37, 416, 85, 16);
		add(lblTnKh);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setBounds(37, 462, 85, 16);
		add(lblNgySinh);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setBounds(37, 515, 85, 16);
		add(lblGiiTnh);

		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setBounds(315, 367, 85, 16);
		add(lblaCh);

		JLabel lblTn = new JLabel("Tên");
		lblTn.setBounds(746, 370, 85, 16);
		add(lblTn);

		JLabel lblSdt = new JLabel("SDT");
		lblSdt.setBounds(315, 416, 85, 16);
		add(lblSdt);

		JLabel lblLoiKh = new JLabel("Loại KH");
		lblLoiKh.setBounds(315, 462, 85, 16);
		add(lblLoiKh);

		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setBounds(315, 515, 85, 16);
		add(lblGhiCh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 65, 1115, 280);
		add(scrollPane);

		tableKhachHang = new JTable();
		tableKhachHang
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
						new String[] { "M\u00E3 KH", "T\u00EAn KH", "Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh",
								"\u0110\u1ECBa ch\u1EC9", "SDT", "Lo\u1EA1i KH", "Ch\u00FA th\u00EDch" }));
		scrollPane.setViewportView(tableKhachHang);

		textFMaKH = new JTextField();
		textFMaKH.setBounds(120, 364, 145, 22);
		add(textFMaKH);
		textFMaKH.setColumns(10);
		textFMaKH.setEditable(false);

		textFTenKhachHang = new JTextField();
		textFTenKhachHang.setColumns(10);
		textFTenKhachHang.setBounds(120, 413, 145, 22);
		add(textFTenKhachHang);

		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setBounds(121, 462, 144, 22);
		add(dateChooserNgaySinh);

		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(121, 511, 55, 25);
		add(rdbtnNam);

		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(205, 511, 55, 25);
		add(rdbtnNu);

		cbbLoaiKH = new JComboBox<>();
		cbbLoaiKH.setBounds(380, 459, 145, 22);
		add(cbbLoaiKH);

		textFSDT = new JTextField();
		textFSDT.setColumns(10);
		textFSDT.setBounds(380, 413, 145, 22);
		add(textFSDT);

		textFDiaChi = new JTextField();
		textFDiaChi.setColumns(10);
		textFDiaChi.setBounds(380, 364, 145, 22);
		add(textFDiaChi);

		textAreaGhiChu = new JTextArea();
		textAreaGhiChu.setBounds(380, 512, 145, 68);
		add(textAreaGhiChu);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(578, 363, 85, 25);
		add(btnThem);
		btnThem.setActionCommand(ACTION_THEM);
		btnThem.addActionListener(this);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(578, 436, 85, 25);
		add(btnSua);
		btnSua.setActionCommand(ACTION_SUA);
		btnSua.addActionListener(this);

		btnReset = new JButton("Reset");
		btnReset.setBounds(578, 511, 85, 25);
		add(btnReset);
		btnReset.setActionCommand(ACTION_RESET);
		btnReset.addActionListener(this);

		rdbtnNam.setActionCommand(ACTION_NAM);
		rdbtnNam.addActionListener(this);
		rdbtnNu.setActionCommand(ACTIONH_NU);
		rdbtnNu.addActionListener(this);

		textFTen = new JXSearchField();
		textFTen.setColumns(10);
		textFTen.setBounds(826, 367, 145, 22);
		add(textFTen);
		textFTen.setName(ACTION_TIMKIEM);
		textFTen.addKeyListener(this);

		buttonXuatFile = new JButton("Xuất file thông tin khách hàng");
		buttonXuatFile.setActionCommand("Thêm");
		buttonXuatFile.setBounds(826, 436, 218, 25);
		add(buttonXuatFile);
		buttonXuatFile.setActionCommand(ACTION_XUATFILE);
		buttonXuatFile.addActionListener(this);
		
		btnThemKhachHangTuFile = new JButton("Thêm khách hàng từ File");
		btnThemKhachHangTuFile.setActionCommand("Thêm");
		btnThemKhachHangTuFile.setBounds(826, 25, 218, 25);
		add(btnThemKhachHangTuFile);
		btnThemKhachHangTuFile.setActionCommand(ACTION_THEMKHTUFILE);
		btnThemKhachHangTuFile.addActionListener(this);
	}

	/**
	 * 
	 */
	private void showCbbLoaiKhachHangTiemKiem() {
		ArrayList<String> listLoaiKH = new ArrayList<>();
		listLoaiKH = controllerKhachHang.getListLoaiKhachHang();
		for (String tenLoaiKH : listLoaiKH) {
			cbbLoaiKH.addItem(tenLoaiKH);
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
	 * @param string
	 * @param string2
	 * @param string3
	 */
	private void showTableKhachHang(String tenKhachHang) {
		listKH = controllerKhachHang.getListKH(tenKhachHang);
		DefaultTableModel model = (DefaultTableModel) tableKhachHang.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SOCOT_TABLE_KHACHHANG];

		for (KhachHang kh : listKH) {
			int i = 0;
			row[i++] = kh.getMaKhachHang();
			row[i++] = kh.getTenKhachHang();
			row[i++] = kh.getNgaySinh();
			if (kh.getGioiTinh() == 1) {
				row[i++] = "Nam";
			} else {
				row[i++] = "Nữ";
			}
			row[i++] = kh.getSdt();
			row[i++] = kh.getDiaChi();
			row[i++] = controllerKhachHang.getLoaiKHString(kh.getLoaiKhachHang());
			row[i++] = kh.getGhiChu();

			model.addRow(row);
		}
	}

	/**
	 * 
	 */
	private void actionTableKH() {
		tableKhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pos = tableKhachHang.getSelectedRow();
				KhachHang khachHang = listKH.get(pos);
				setTextForForm(khachHang);
			}
		});
	}

	/**
	 * @param khachHang
	 */
	protected void setTextForForm(KhachHang khachHang) {
		textFMaKH.setText(khachHang.getMaKhachHang() + "");
		textFTenKhachHang.setText(khachHang.getTenKhachHang());
		dateChooserNgaySinh.setDate(khachHang.getNgaySinh());
		if (khachHang.getGioiTinh() == 1) {
			rdbtnNam.setSelected(true);
			rdbtnNu.setSelected(false);
		} else {
			rdbtnNam.setSelected(false);
			rdbtnNu.setSelected(true);
		}
		textFDiaChi.setText(khachHang.getDiaChi());
		textFSDT.setText(khachHang.getSdt());
		String loaiKH = (String) controllerKhachHang.getLoaiKHString(khachHang.getLoaiKhachHang());
		cbbLoaiKH.setSelectedItem(loaiKH);
		textAreaGhiChu.setText(khachHang.getGhiChu());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		switch (action) {
		case ACTION_THEM:
			KhachHang kh = getInforKHForm();
			controllerKhachHang.addKH(kh);
			showTableKhachHang("");
			break;
		case ACTION_SUA:
			kh = getInforKHForm();
			controllerKhachHang.editKH(kh);
			showTableKhachHang("");
			break;
		case ACTION_RESET:
			showTableKhachHang("");
			break;
		case ACTION_NAM:
			rdbtnNam.setSelected(true);
			rdbtnNu.setSelected(false);
			break;
		case ACTIONH_NU:
			rdbtnNam.setSelected(false);
			rdbtnNu.setSelected(true);
			break;
		case ACTION_XUATFILE:
			int anhVinh = 0;
			// Xuất file ở đây nhé a.
			break;
		case ACTION_THEMKHTUFILE:
			int anhVinh2 = 2;
			// Anh làm cái browse File nhé!!.
			break;
		default:
			break;
		}
	}

	/**
	 * @return
	 */
	private KhachHang getInforKHForm() {
		int maKhachHang = Integer.parseInt(textFMaKH.getText());
		String tenKhachHang = textFTenKhachHang.getText();
		Date ngaySinh = new java.sql.Date(dateChooserNgaySinh.getDate().getTime());

		int gioiTinh = 0;
		if (rdbtnNam.isSelected()) {
			gioiTinh = 1;
		}
		String diaChi = textFDiaChi.getText();
		int loaiKhachHang = controllerKhachHang.getMaLoaiKH(cbbLoaiKH.getSelectedItem().toString());
		String sdt = textFSDT.getText();
		String ghiChu = textAreaGhiChu.getText();

		KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, ngaySinh, gioiTinh, diaChi, sdt, loaiKhachHang, ghiChu);
		return kh;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		String search = (String) ((Component) event.getSource()).getName();

		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (search) {
			case ACTION_TIMKIEM:
				String searchKH = textFTen.getText();
				showTableKhachHang(searchKH);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
