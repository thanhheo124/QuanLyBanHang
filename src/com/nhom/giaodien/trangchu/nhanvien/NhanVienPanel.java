package com.nhom.giaodien.trangchu.nhanvien;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerNhanVien;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.NhanVien;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import org.jdesktop.swingx.JXSearchField;

public class NhanVienPanel extends JPanel implements ICommon, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8951246038980186194L;
	private static final int SO_COT_TABLE_NV = 9;
	private static final String ACTION_RADIO_NAM = "IsNam";
	private static final String ACTION_RADIO_NU = "IsNu";
	private static final String ACTION_RADIO_MANV = "Search by MaNV";
	private static final String ACTION_RADIO_TENNV = "Search by TenNV";
	private static final String ACTION_CHECKBOX_TIMKIEM = "TimKiem";
	private static final String ACTION_BTNRESET = "BTNRESET";
	private static final String ACTION_SUA = "BTNSUA";
	private static final String ACTION_THEM = "BTNTHEM";
	private static final String ACTION_XUATFILE = "XUATFILE";
	private JTable tableNV;
	private JTextField textMaNV;
	private JTextField textTenNV;
	private JTextField textDiaChi;
	private JTextField textSDT;
	private JXSearchField searchField;
	private JDateChooser dateChooserNgaySinh;
	private JDateChooser dateNgayVaoLam;
	private JTextArea textAreaChuThich;
	private JComboBox<String> cbbChucVu;
	private JButton btnThem;
	private JButton btnSua;
	private JRadioButton rdbtnTnNv;
	private JCheckBox checkBoxTimKiem;
	private JButton btnReset;
	private JRadioButton rdbtnMNv;
	private ControllerNhanVien controllerNhanVien;
	private JRadioButton radioNam;
	private JRadioButton radioNu;
	private ArrayList<NhanVien> listNV;
	private JComboBox<String> cbbTrangThai;
	private JButton btnXuatFile;

	public NhanVienPanel() {
		init();
		setStyle();
		addComps();
		loadCbbChucVu();
		loadcbbTrangThai();
		loadTableNhanVien("", 0);
		actionTableNV();
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {

		controllerNhanVien = new ControllerNhanVien();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 35, 1093, 300);
		add(scrollPane);

		tableNV = new JTable();
		tableNV.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null, null }, },
						new String[] { "M\u00E3 NV", "T\u00EAn NV", "Ng\u00E0y sinh", "Gi\u1EDBi t\u00EDnh",
								"Ng\u00E0y v\u00E0o l\u00E0m", "Ch\u1EE9c v\u1EE5", "\u0110\u1ECBa ch\u1EC9", "SDT",
								"Tr\u1EA1ng th\u00E1i" }));
		scrollPane.setViewportView(tableNV);

		JLabel lblMNv = new JLabel("Mã NV");
		lblMNv.setBounds(26, 366, 85, 16);
		add(lblMNv);

		JLabel lblTnNv = new JLabel("Tên NV");
		lblTnNv.setBounds(25, 404, 85, 16);
		add(lblTnNv);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setBounds(26, 443, 85, 16);
		add(lblNgySinh);

		JLabel label = new JLabel("Giới tính");
		label.setBounds(25, 487, 85, 16);
		add(label);

		JLabel lblNgyVoLm = new JLabel("Ngày vào làm");
		lblNgyVoLm.setBounds(26, 536, 85, 16);
		add(lblNgyVoLm);

		textMaNV = new JTextField();
		textMaNV.setColumns(10);
		textMaNV.setBounds(136, 363, 145, 22);
		add(textMaNV);
		textMaNV.setEditable(false);

		textTenNV = new JTextField();
		textTenNV.setColumns(10);
		textTenNV.setBounds(136, 401, 145, 22);
		add(textTenNV);

		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setBounds(137, 443, 144, 22);
		add(dateChooserNgaySinh);

		dateNgayVaoLam = new JDateChooser();
		dateNgayVaoLam.setBounds(136, 536, 144, 22);
		add(dateNgayVaoLam);

		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setBounds(355, 366, 85, 16);
		add(lblChcV);

		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setBounds(355, 404, 85, 16);
		add(lblaCh);

		JLabel lblSt = new JLabel("SĐT");
		lblSt.setBounds(355, 443, 85, 16);
		add(lblSt);

		JLabel lblChThch = new JLabel("Chú thích");
		lblChThch.setBounds(355, 487, 85, 16);
		add(lblChThch);

		textDiaChi = new JTextField();
		textDiaChi.setColumns(10);
		textDiaChi.setBounds(433, 401, 145, 22);
		add(textDiaChi);

		textSDT = new JTextField();
		textSDT.setColumns(10);
		textSDT.setBounds(433, 443, 145, 22);
		add(textSDT);

		cbbChucVu = new JComboBox<>();
		cbbChucVu.setBounds(433, 363, 145, 22);
		add(cbbChucVu);

		textAreaChuThich = new JTextArea();
		textAreaChuThich.setBounds(433, 490, 145, 68);
		add(textAreaChuThich);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(640, 415, 85, 25);
		add(btnThem);
		btnThem.setActionCommand(ACTION_THEM);
		btnThem.addActionListener(this);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(640, 466, 85, 25);
		add(btnSua);
		btnSua.setActionCommand(ACTION_SUA);
		btnSua.addActionListener(this);

		btnReset = new JButton("Reset");
		btnReset.setBounds(640, 514, 85, 25);
		add(btnReset);
		btnReset.setActionCommand(ACTION_BTNRESET);
		btnReset.addActionListener(this);

		checkBoxTimKiem = new JCheckBox("Tìm kiếm");
		checkBoxTimKiem.setBounds(847, 400, 113, 25);
		add(checkBoxTimKiem);
		checkBoxTimKiem.setActionCommand(ACTION_CHECKBOX_TIMKIEM);
		checkBoxTimKiem.addActionListener(this);

		rdbtnTnNv = new JRadioButton("Tên NV");
		rdbtnTnNv.setBounds(823, 483, 97, 25);
		add(rdbtnTnNv);
		rdbtnTnNv.setActionCommand(ACTION_RADIO_TENNV);
		rdbtnTnNv.addActionListener(this);

		rdbtnMNv = new JRadioButton("Mã NV");
		rdbtnMNv.setBounds(953, 483, 85, 25);
		add(rdbtnMNv);
		rdbtnMNv.setActionCommand(ACTION_RADIO_MANV);
		rdbtnMNv.addActionListener(this);

		radioNam = new JRadioButton("Nam");
		radioNam.setBounds(136, 483, 55, 25);
		add(radioNam);
		radioNam.setActionCommand(ACTION_RADIO_NAM);
		radioNam.addActionListener(this);

		radioNu = new JRadioButton("Nữ");
		radioNu.setBounds(218, 483, 55, 25);
		add(radioNu);
		radioNu.setActionCommand(ACTION_RADIO_NU);
		radioNu.addActionListener(this);

		searchField = new JXSearchField();
		searchField.setBounds(823, 440, 186, 25);
		add(searchField);

		JLabel lblTrngThi = new JLabel("Trạng thái");
		lblTrngThi.setBounds(640, 366, 85, 16);
		add(lblTrngThi);

		cbbTrangThai = new JComboBox<String>();
		cbbTrangThai.setBounds(732, 363, 145, 22);
		add(cbbTrangThai);
		
		btnXuatFile = new JButton("Xuất File Nhân Viên");
		btnXuatFile.setBounds(953, 362, 152, 25);
		add(btnXuatFile);
		btnXuatFile.addActionListener(this);
		btnXuatFile.setActionCommand(ACTION_XUATFILE);
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

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		boolean searchByName = true;
		switch (action) {
		case ACTION_RADIO_TENNV:
			if (!checkBoxTimKiem.isSelected()) {
				JOptionPane.showMessageDialog(null, "Hãy click vào CheckBox tìm kiếm đã");
			} else {
				rdbtnMNv.setSelected(false);
				rdbtnTnNv.setSelected(true);
				actionSearch(searchByName);
			}
			break;
		case ACTION_RADIO_MANV:
			if (!checkBoxTimKiem.isSelected()) {
				JOptionPane.showMessageDialog(null, "Hãy click vào CheckBox tìm kiếm đã");
			} else {
				searchByName = false;// ko cho search theo ten nua => search
										// theo mã
				rdbtnMNv.setSelected(true);
				rdbtnTnNv.setSelected(false);
				actionSearch(searchByName);
			}

			break;
		case ACTION_BTNRESET:
			reset();
			break;
		case ACTION_SUA: // xóa trong bảng
			int i = tableNV.getSelectedRow();
			int trangThai = 0;
			if (cbbTrangThai.getSelectedItem().equals("Đang làm việc")) {
				trangThai = 1;
			}
			int gioiTinh = 0;
			if (radioNam.isSelected()) {
				gioiTinh = 1;
			}
			java.sql.Date dateNgaySinh = new java.sql.Date(dateChooserNgaySinh.getDate().getTime());
			java.sql.Date dateNgayLam = new java.sql.Date(dateNgayVaoLam.getDate().getTime());

			NhanVien nv = new NhanVien(listNV.get(i).getMaNhanVien(), textTenNV.getText(), dateNgaySinh, gioiTinh,
					dateNgayLam, cbbChucVu.getSelectedItem().toString(), textDiaChi.getText(), textSDT.getText(),
					textAreaChuThich.getText(), trangThai);
			controllerNhanVien.suaNhanVien(nv);
			loadTableNhanVien("", 0);
			break;
		case ACTION_RADIO_NAM:
			radioNam.setSelected(true);
			radioNu.setSelected(false);
			break;
		case ACTION_RADIO_NU:
			radioNu.setSelected(true);
			radioNam.setSelected(false);
			break;
		case ACTION_THEM:
			i = tableNV.getSelectedRow();
			trangThai = 0;
			if (cbbTrangThai.getSelectedItem().equals("Đang làm việc")) {
				trangThai = 1;
			}
			gioiTinh = 0;
			if (radioNam.isSelected()) {
				gioiTinh = 1;
			}
			dateNgaySinh = new java.sql.Date(dateChooserNgaySinh.getDate().getTime());
			dateNgayLam = new java.sql.Date(dateNgayVaoLam.getDate().getTime());
			
			NhanVien nv2 = new NhanVien(0, textTenNV.getText(), dateNgaySinh, gioiTinh,
					dateNgayLam, cbbChucVu.getSelectedItem().toString(), textDiaChi.getText(), textSDT.getText(),
					textAreaChuThich.getText(), trangThai);
			controllerNhanVien.addNhanVien(nv2);
			loadTableNhanVien("", 0);
			break;
		case ACTION_XUATFILE:
                        if(controllerNhanVien.xuatFileNhanVien(listNV, searchField.getText())){
                            JOptionPane.showMessageDialog(null, "Đã xuất file!" , "Thông báo", 1);
                        }else JOptionPane.showMessageDialog(null, "Chưa xuất file!" , "Thông báo", 2);
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void reset() {
		textAreaChuThich.setText("");
		textDiaChi.setText("");
		textMaNV.setText("");
		textSDT.setText("");
		textTenNV.setText("");
		radioNam.setSelected(false);
		radioNu.setSelected(false);
		dateChooserNgaySinh.setDate(null);
		dateNgayVaoLam.setDate(null);
		loadCbbChucVu();
		loadTableNhanVien("", 0);
	}

	/**
	 * @param searchByName
	 */
	private void actionSearch(boolean searchByName) {
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					if (searchByName) {
						String name = searchField.getText();
						loadTableNhanVien(name, 0);
					} else {
						try {
							int maNV = Integer.parseInt(searchField.getText());
							loadTableNhanVien("", maNV);
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số");
						}
					}
				}
			}
		});
	}

	/**
	 * 
	 */
	private void actionTableNV() {
		tableNV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (listNV.size() > 0) {
					int i = tableNV.getSelectedRow();
					NhanVien nv = listNV.get(i);
					textTenNV.setText(nv.getTenNhanVien());
					textMaNV.setText(nv.getMaNhanVien() + "");
					textAreaChuThich.setText(nv.getGhiChu());
					textDiaChi.setText(nv.getDiaChi());
					textSDT.setText(nv.getSoDT());
					dateChooserNgaySinh.setDate(nv.getNgaySinh());
					dateNgayVaoLam.setDate(nv.getNgayVaoLam());
					cbbChucVu.setSelectedItem(nv.getChucVu());
					if (nv.getGioiTinh() == 1) {
						radioNam.setSelected(true);
						radioNu.setSelected(false);
					} else {
						radioNu.setSelected(true);
						radioNam.setSelected(false);
					}
				}
			}
		});

	}

	/**
	 * 
	 */
	public void loadCbbChucVu() {
		ArrayList<String> listChucVu = controllerNhanVien.getChucVu();
		for (String chucVu : listChucVu) {
			cbbChucVu.addItem(chucVu);
		}
	}

	/**
	 * @param string
	 * @param string2
	 */
	public void loadTableNhanVien(String name, int maNhanVien) {
		// get listNhanVien
		listNV = new ArrayList<>();
		listNV = controllerNhanVien.getNhanVien(name, maNhanVien);

		DefaultTableModel model = (DefaultTableModel) tableNV.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SO_COT_TABLE_NV];
		for (NhanVien nv : listNV) {
			int i = 0;
			row[i++] = nv.getMaNhanVien();
			row[i++] = nv.getTenNhanVien();
			row[i++] = nv.getNgaySinh();
			if (nv.getGioiTinh() == 1) {
				row[i++] = "Nam";
			} else {
				row[i++] = "Nữ";
			}
			row[i++] = nv.getNgayVaoLam();
			row[i++] = nv.getChucVu();
			row[i++] = nv.getDiaChi();
			row[i++] = nv.getSoDT();
			if (nv.getTrangThai() == 1) {
				row[i++] = "Đang làm việc";
			} else {
				row[i++] = "Nghỉ làm";
			}
			model.addRow(row);
		}
	}

	/**
	 * 
	 */
	private void loadcbbTrangThai() {
		cbbTrangThai.addItem("Đang làm việc");
		cbbTrangThai.addItem("Nghỉ làm");
	}

}
