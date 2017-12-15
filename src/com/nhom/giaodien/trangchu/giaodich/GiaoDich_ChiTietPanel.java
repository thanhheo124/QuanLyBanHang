package com.nhom.giaodien.trangchu.giaodich;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerGiaoDich;
import com.nhom.giaodien.GuiRoot;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.DanhSachMuaHang;
import com.nhom.models.KhachHang;
import com.nhom.models.SanPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXSearchField;
import com.toedter.components.JSpinField;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GiaoDich_ChiTietPanel extends JPanel implements ICommon, KeyListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ACTION_SEARCH_KH = "SearchKH";
	private static final int TABLEKH_SOCOT = 4;
	private static final int TABLEKH_SOCOTDSMH = 6;
	private static final int TABLESP_SOCOT = 1;
	private static final String ACTION_SEARCH_SP = "SearchSP";
	private static final int TABLE_GIOHANG_SOCOT = 6;
	private static final String ACTION_BO_KHOI_GIO_HANG = "DeleteFromGioHang";
	private static final String ACTION_MUA = "Mua";
	private static final String ACTION_XUATHOADON = "XUATHOADON";
	private static final String ACTION_HOADON = "TIMHOADON";
	private JTable tableKH;
	private JTable tableSP;
	private JTable tableGioHang;
	private JTable tableDSMH;

	private JTextField textFGia;
	private JTextField textChuThich;
	private JTextField textFHang;
	private JTextField textFTenSP;
	private JTextField textFiChietKhau;
	private JXSearchField searchKH;
	private JXSearchField searchSP;
	private JButton btnThemKH;
	private JButton btnXuatHD;
	private JComboBox<String> cbbMaLoaiSP;
	private JButton btnThemVoGioHang;
	private JButton btnBoKhoiGioHang;
	private JComboBox<String> cbbSize;
	private JButton btnMua;
	private ControllerGiaoDich controllerGiaoDich;
	private JLabel lblKhachMuaHang;
	private JSpinField spinSoLuong;
	protected int maHoaDon = 0;
	protected int maKhachHang = 0;
	private ArrayList<SanPham> listSPGD;
	private ArrayList<DanhSachMuaHang> listHD;
	private JXSearchField searchFieldHD;
	private JLabel lblHan;

	public GiaoDich_ChiTietPanel() {

		init();
		setStyle();
		addComps();
		showCbbMaSP();
		showCbbSize();
		loadtableKH("");
		loadtableHD("");
		loadtableSP("");
	}

	/**
	 * show combobox size
	 */
	private void showCbbSize() {
		ArrayList<String> listSize = controllerGiaoDich.getSize();
		for (String size : listSize) {
			cbbSize.addItem(size);
		}
	}

	/**
	 * show combobox maSP
	 */
	private void showCbbMaSP() {
		ArrayList<String> listMaSP = controllerGiaoDich.getMaSP();
		for (String maSP : listMaSP) {
			cbbMaLoaiSP.addItem(maSP);
		}
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		listSPGD = new ArrayList<>();

		controllerGiaoDich = new ControllerGiaoDich();

		JLabel lblNewLabel = new JLabel("Thông tin khách hàng");
		lblNewLabel.setBounds(12, 13, 160, 16);
		add(lblNewLabel);

		JLabel lblThngTinMua = new JLabel("Thông tin sản phẩm");
		lblThngTinMua.setBounds(434, 13, 160, 16);
		add(lblThngTinMua);

		JLabel label = new JLabel("%");
		label.setBounds(1064, 172, 69, 16);
		add(label);

		JLabel lblloaiSp = new JLabel("Loại SP");
		lblloaiSp.setBounds(608, 79, 69, 16);
		add(lblloaiSp);

		JLabel lblMuSc = new JLabel("Chú thích");
		lblMuSc.setBounds(608, 217, 69, 16);
		add(lblMuSc);

		JLabel lblHng = new JLabel("Hãng");
		lblHng.setBounds(608, 172, 69, 16);
		add(lblHng);

		JLabel lblGi = new JLabel("Giá");
		lblGi.setBounds(608, 123, 69, 16);
		add(lblGi);

		JLabel lblTnSp = new JLabel("Tên SP");
		lblTnSp.setBounds(888, 79, 69, 16);
		add(lblTnSp);

		JLabel lblGiHng = new JLabel("Giỏ hàng");
		lblGiHng.setForeground(new Color(139, 69, 19));
		lblGiHng.setBounds(12, 363, 160, 16);
		add(lblGiHng);

		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(888, 123, 69, 16);
		add(lblSize);

		JLabel lblChitKhu = new JLabel("Chiết khấu");
		lblChitKhu.setBounds(888, 172, 69, 16);
		add(lblChitKhu);

		JLabel lblDsMuaHang = new JLabel("Danh sách mua hàng");
		lblDsMuaHang.setForeground(new Color(139, 69, 19));
		lblDsMuaHang.setBounds(551, 363, 160, 16);
		add(lblDsMuaHang);

		JLabel lblSize_1 = new JLabel("Số lượng");
		lblSize_1.setBounds(888, 217, 69, 16);
		add(lblSize_1);

		searchKH = new JXSearchField();
		searchKH.setBounds(12, 33, 386, 32);
		add(searchKH);
		searchKH.setName(ACTION_SEARCH_KH);
		searchKH.addKeyListener(this);

		searchSP = new JXSearchField();
		searchSP.setBounds(434, 33, 148, 32);
		add(searchSP);
		searchSP.setName(ACTION_SEARCH_SP);
		searchSP.addKeyListener(this);

		btnThemKH = new JButton("Khách mua hàng");
		btnThemKH.setBounds(12, 327, 148, 25);
		add(btnThemKH);

		btnXuatHD = new JButton("Xuất hóa đơn");
		btnXuatHD.setBounds(982, 359, 148, 25);
		add(btnXuatHD);
		btnXuatHD.setActionCommand(ACTION_XUATHOADON);
		btnXuatHD.addActionListener(this);

		btnMua = new JButton("Mua");
		btnMua.setBounds(407, 359, 109, 25);
		add(btnMua);
		btnMua.setActionCommand(ACTION_MUA);
		btnMua.addActionListener(this);

		btnThemVoGioHang = new JButton("Thêm vào giỏ hàng");
		btnThemVoGioHang.setBounds(608, 293, 148, 25);
		add(btnThemVoGioHang);

		btnBoKhoiGioHang = new JButton("Bỏ khỏi giỏ hàng");
		btnBoKhoiGioHang.setBounds(888, 293, 148, 25);
		add(btnBoKhoiGioHang);
		btnBoKhoiGioHang.setActionCommand(ACTION_BO_KHOI_GIO_HANG);
		btnBoKhoiGioHang.addActionListener(this);

		textFGia = new JTextField();
		textFGia.setColumns(10);
		textFGia.setBounds(702, 120, 133, 22);
		add(textFGia);

		textChuThich = new JTextField();
		textChuThich.setColumns(10);
		textChuThich.setBounds(702, 214, 133, 22);
		add(textChuThich);

		textFHang = new JTextField();
		textFHang.setColumns(10);
		textFHang.setBounds(703, 169, 132, 22);
		add(textFHang);

		textFTenSP = new JTextField();
		textFTenSP.setColumns(10);
		textFTenSP.setBounds(967, 76, 140, 22);
		add(textFTenSP);

		textFiChietKhau = new JTextField();
		textFiChietKhau.setColumns(10);
		textFiChietKhau.setBounds(967, 169, 69, 22);
		add(textFiChietKhau);

		cbbMaLoaiSP = new JComboBox<>();
		cbbMaLoaiSP.setBounds(702, 76, 133, 22);
		add(cbbMaLoaiSP);

		cbbSize = new JComboBox<>();
		cbbSize.setBounds(967, 120, 140, 22);
		add(cbbSize);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(434, 79, 148, 239);
		add(scrollPane_1);

		tableSP = new JTable();
		tableSP.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00EAn s\u1EA3n ph\u1EA9m" }));
		scrollPane_1.setViewportView(tableSP);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 392, 504, 235);
		add(scrollPane_2);

		tableGioHang = new JTable();
		tableGioHang.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 SP", "T\u00EAn SP",
				"Size", "Chi\u1EBFt kh\u1EA5u", "Gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng" }));
		scrollPane_2.setViewportView(tableGioHang);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(551, 392, 591, 235);
		add(scrollPane_3);

		tableDSMH = new JTable();
		tableDSMH.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "M\u00E3 H\u0110", "Kh\u00E1ch h\u00E0ng",
						"Nh\u00E2n vi\u00EAn", "Ng\u00E0y l\u1EADp H\u0110", "T\u1ED5ng ti\u1EC1n", "Ghi ch\u00FA" }));
		scrollPane_3.setViewportView(tableDSMH);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 79, 386, 239);
		add(scrollPane);

		tableKH = new JTable();
		tableKH.setModel(new DefaultTableModel(new Object[][] { { "", "", null, null }, },
				new String[] { "M\u00E3 KH", "T\u00EAn KH", "Lo\u1EA1i KH", "SDT" }));
		tableKH.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableKH.getColumnModel().getColumn(2).setPreferredWidth(54);
		scrollPane.setViewportView(tableKH);

		spinSoLuong = new JSpinField();
		spinSoLuong.setBounds(967, 211, 69, 22);
		add(spinSoLuong);

		lblKhachMuaHang = new JLabel("");
		lblKhachMuaHang.setBounds(217, 327, 172, 25);
		add(lblKhachMuaHang);
		
		searchFieldHD = new JXSearchField();
		searchFieldHD.setBounds(813, 352, 124, 32);
		add(searchFieldHD);
		searchFieldHD.setName(ACTION_HOADON);
		searchFieldHD.addKeyListener(this);
		
		lblHan = new JLabel("Hóa Đơn");
		lblHan.setBounds(734, 363, 67, 16);
		add(lblHan);

	}

	/**
	 * @param event
	 */
	protected void actionTableKH(ArrayList<KhachHang> listKH) {
		tableKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableKH.getSelectedRow();
				switch (listKH.get(i).getLoaiKhachHang()) {
				case 1:
					textFiChietKhau.setText(10 + "");
					break;
				case 2:
					textFiChietKhau.setText(0 + "");
					break;
				case 3:
					textFiChietKhau.setText(5 + "");
					break;
				default:
					break;
				}
			}
		});
		btnThemKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableKH.getSelectedRow();
				lblKhachMuaHang.setText(listKH.get(i).getTenKhachHang());
				DefaultTableModel model = (DefaultTableModel) tableGioHang.getModel();
				model.setRowCount(0);
				maKhachHang = listKH.get(i).getMaKhachHang();
			}
		});
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
	public void keyPressed(KeyEvent event) {
		String search = (String) ((Component) event.getSource()).getName();

		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (search) {
			case ACTION_SEARCH_KH:
				String searchKHx = searchKH.getText();
				loadtableKH(searchKHx);
				break;
			case ACTION_SEARCH_SP:
				String searchSPx = searchSP.getText();
				loadtableSP(searchSPx);
				break;
			case ACTION_HOADON:
				System.out.println("aA");
				String searchHDx = searchFieldHD.getText();
				loadtableHD(searchHDx);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Khi chọn vào tên 1 SP thì các giá trị khác của form đươc hiển thị các giá
	 * trị tương ứng.
	 * 
	 * @param listSP
	 */
	private void actionTableSanPham(ArrayList<SanPham> listSP) {
		tableSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				int i = tableSP.getSelectedRow();
				cbbMaLoaiSP.setSelectedItem(listSP.get(i).getLoaiSanPham() + "");
				cbbSize.setSelectedItem(listSP.get(i).getSize() + "");
				textFGia.setText(listSP.get(i).getGiaBan() + "");
				textFTenSP.setText(listSP.get(i).getTenSanPham());
				spinSoLuong.setValue(listSP.get(i).getTonKho());
				textFHang.setText(controllerGiaoDich.getTenHangSanXuat(listSP.get(i).getHangSanXuat()));
				textChuThich.setText(listSP.get(i).getChuThich());
			}
		});
		btnThemVoGioHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblKhachMuaHang.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Hãy chọn khách mua hàng");
				} else {
					int i = tableSP.getSelectedRow();
					SanPham sp = listSP.get(i);
					sp.setTonKho((spinSoLuong.getValue())); // set Số lượng mua
					int chietKhau = Integer.parseInt(textFiChietKhau.getText());
					sp.setGiaBan(sp.getGiaBan() * (100 - chietKhau) / 100);
					listSPGD.add(sp);
					showTableGioHang();
					actionTableGioHang();
				}
			}
		});
	}

	/**
	 * @param listSPGD2
	 */
	protected void actionTableGioHang() {
		tableGioHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	/**
	 * @param sp
	 */
	protected void showTableGioHang() {
		DefaultTableModel model = (DefaultTableModel) tableGioHang.getModel();
		Object[] row = new Object[TABLE_GIOHANG_SOCOT];
		model.setRowCount(0);
		for (SanPham sp : listSPGD) {
			row[0] = sp.getMaSanPham();
			row[1] = sp.getTenSanPham();
			row[2] = sp.getSize();
			row[3] = textFiChietKhau.getText();
			row[4] = sp.getGiaBan();
			row[5] = sp.getTonKho();
			model.addRow(row);
		}
	}

	/**
	 * @param searchKHx
	 */
	private void loadtableKH(String searchKHx) {
		ArrayList<KhachHang> listKH = controllerGiaoDich.getKhachHang(searchKHx);
		DefaultTableModel model = (DefaultTableModel) tableKH.getModel();
		model.setRowCount(0);
		Object[] row = new Object[TABLEKH_SOCOT];
		for (KhachHang khachHang : listKH) {
			row[0] = khachHang.getMaKhachHang();
			row[1] = khachHang.getTenKhachHang();
			row[2] = khachHang.getLoaiKhachHang();
			row[3] = khachHang.getSdt();
			model.addRow(row);
		}
		actionTableKH(listKH);
	}

	/**
	 * loadTable SP
	 */
	private void loadtableSP(String serchSP) {
		ArrayList<SanPham> listSP = controllerGiaoDich.getSanPham(serchSP);
		DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
		model.setRowCount(0);
		Object[] row = new Object[TABLESP_SOCOT];
		for (SanPham sp : listSP) {
			row[0] = sp.getTenSanPham();
			model.addRow(row);
		}
		actionTableSanPham(listSP);
	}

	private void loadtableHD(String stringSearch) {
		listHD = controllerGiaoDich.getHoaDon(stringSearch);
		DefaultTableModel model = (DefaultTableModel) tableDSMH.getModel();
		model.setRowCount(0);
		Object[] row = new Object[TABLEKH_SOCOTDSMH];
		for (DanhSachMuaHang ds : listHD) {
			row[0] = ds.getMaHoaDon();
			row[1] = ds.getTenKhachHang();
			row[2] = ds.getTenNhanVien();
			row[3] = ds.getNgayLap();
			row[4] = ds.getTongTien();
			row[5] = ds.getGhiChu();
			model.addRow(row);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		switch (action) {
		case ACTION_BO_KHOI_GIO_HANG:
			int posDelete = tableGioHang.getSelectedRow();
			listSPGD.remove(posDelete);
			showTableGioHang();
			break;
		case ACTION_MUA:
			if (lblKhachMuaHang.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Hãy chọn khách mua hàng");
			} else {
				int tongTien = tinhTien();
				maHoaDon = controllerGiaoDich.createHoaDonMuaHang(maKhachHang, GuiRoot.maNhanVien, tongTien);
				taoChiTietPhieuMua();
				loadtableHD("");
			}
			break;
		case ACTION_XUATHOADON:
			int posXuatHoaDon = tableDSMH.getSelectedRow();
			if (posXuatHoaDon != -1) {
				DanhSachMuaHang dsMuaHang = listHD.get(posXuatHoaDon);
				int maHoaDonXuatFile = dsMuaHang.getMaHoaDon();
				if (controllerGiaoDich.xuatHoaDon(maHoaDonXuatFile) == 2) {
					JOptionPane.showMessageDialog(null, "Xuất file thành công");
				} else if (controllerGiaoDich.xuatHoaDon(maHoaDonXuatFile) == 1) {
					JOptionPane.showMessageDialog(null, "Tên file phải kết thúc là .pdf", "Lỗi Xuất File",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Chương trình gặp lỗi xuất file", "Lỗi Xuất File",
							JOptionPane.ERROR_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(null, "Hãy chọn hóa đơn để xuất");
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void taoChiTietPhieuMua() {
		controllerGiaoDich.createChiTietHoaDon(maHoaDon, listSPGD);
	}

	/**
	 * tính tiền Hóa Đơn
	 * 
	 * @return tongTien
	 */
	private int tinhTien() {
		int tongTien = 0;
		for (int i = 0; i < listSPGD.size(); i++) {
			int tien = listSPGD.get(i).getGiaBan() * listSPGD.get(i).getTonKho();
			tongTien += tien;
		}
		return tongTien;
	}
}
