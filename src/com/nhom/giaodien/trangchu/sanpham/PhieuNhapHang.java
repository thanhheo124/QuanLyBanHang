package com.nhom.giaodien.trangchu.sanpham;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerNhapHang;
import com.nhom.giaodien.GuiRoot;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.PhieuNhap;
import com.nhom.models.SanPham;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JSpinner;
import org.jdesktop.swingx.JXSearchField;

public class PhieuNhapHang extends JPanel implements ICommon, KeyListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TABLESP_SOCOT = 1;
	private static final String ACTION_SEARCH_SP = "Search";
	private static final String ACTION_BOKHOIPHIEUNHAP = "boikhoiphieunhap";
	private static final String ACTION_CHOVAOPHIEUNHAP = "themvaophieunhap";
	private static final String ACTION_NHAPHANG = "nhaphang";
	private static final String ACTION_XUATPHIEUNHAP = "xuất file";
	private static final String ACTION_TAOPHIEU = "Tao Phieu";
	private static final String ACTION_REFRESH = "refresh";
	private static final int SOCOT_CHITIETPHIEUNHAP = 6;
	private static final int SOCOT_TALBE_HOADON = 5;
	private JTable tablePhieuNhap;
	private JTable tableChiTietPhieuNhap;
	private JTable tableSP;
	private JTextField textFTenSP;
	private JTextField textFGia;
	private JTextField textHang;
	private JComboBox<String> cbbLoaiSP;
	private JComboBox<String> cbbSize;
	private JSpinner spinnerSoLuong;
	private JButton btnChoVaoPhieuNhap;
	private JButton btnBoKhoiPhieuNhap;
	private JButton btnNhpHng;
	private JButton btnXutPhiuNhp;
	private JXSearchField searchSP;
	private ControllerNhapHang controllerNhapHang;
	private ArrayList<SanPham> listSP;
	private int posListSP;
	private JLabel lblChThch;
	private JScrollPane scrollPane_3;
	private JTextArea textAreaChuThich;
	private JButton btnTaoPhieu;
	private int maPhieuNhap;
	private ArrayList<SanPham> listSPGiaoDich;
	private ArrayList<PhieuNhap> listPhieuNhap;
	private JButton buttonRefresh;

	public PhieuNhapHang() {

		init();
		setStyle();
		addComps();
		showCbbMaSP();
		showCbbSize();
		loadtableSP("");
		actionTableSP();
		showTablePhieuNhap();
		addactionforButton();
	}

	/**
	 * 
	 */
	private void showTablePhieuNhap() {
		listPhieuNhap = new ArrayList<>();
		listPhieuNhap = controllerNhapHang.getPhieuNhap();
		DefaultTableModel model = (DefaultTableModel) tablePhieuNhap.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SOCOT_TALBE_HOADON];
		for (PhieuNhap phieuNhap : listPhieuNhap) {
			int i = 0;
			row[i++] = phieuNhap.getMaPhieuNhap();
			row[i++] = controllerNhapHang.getTenNhanVien(phieuNhap.getMaNhanVien());
			row[i++] = phieuNhap.getNgayNhap();
			row[i++] = phieuNhap.getTongTien();
			row[i++] = phieuNhap.getChuThich();

			model.addRow(row);
		}
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		listSPGiaoDich = new ArrayList<>();
		listSP = new ArrayList<>();
		controllerNhapHang = new ControllerNhapHang();

		JLabel lblPhiuNhp = new JLabel("Phiếu nhập");
		lblPhiuNhp.setForeground(Color.BLUE);
		lblPhiuNhp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPhiuNhp.setBounds(713, 298, 120, 21);
		add(lblPhiuNhp);

		JLabel lblChiTitPhiu = new JLabel("Chi tiết phiếu nhập");
		lblChiTitPhiu.setForeground(Color.BLUE);
		lblChiTitPhiu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChiTitPhiu.setBounds(699, 29, 146, 21);
		add(lblChiTitPhiu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(473, 326, 608, 248);
		add(scrollPane);

		tablePhieuNhap = new JTable();
		tablePhieuNhap.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "M\u00E3 H\u0110", "Nh\u00E2n vi\u00EAn", "Ng\u00E0y l\u1EADp", "T\u1ED5ng ti\u1EC1n",
						"Ch\u00FA th\u00EDch" }));
		scrollPane.setViewportView(tablePhieuNhap);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(473, 61, 608, 224);
		add(scrollPane_1);

		tableChiTietPhieuNhap = new JTable();
		tableChiTietPhieuNhap.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, }, new String[] {
						"M\u00E3 SP", "T\u00EAn SP", "Size", "Gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "Ghi ch\u00FA" }));
		scrollPane_1.setViewportView(tableChiTietPhieuNhap);

		JLabel lblNewLabel = new JLabel("Thông tin sản phẩm");
		lblNewLabel.setBounds(29, 33, 151, 16);
		add(lblNewLabel);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(29, 92, 168, 224);
		add(scrollPane_2);

		tableSP = new JTable();
		tableSP.setModel(
				new DefaultTableModel(new Object[][] { { null }, }, new String[] { "T\u00EAn s\u1EA3n ph\u1EA9m" }));
		scrollPane_2.setViewportView(tableSP);

		JLabel lblLoiSp = new JLabel("Loại SP");
		lblLoiSp.setBounds(29, 329, 56, 16);
		add(lblLoiSp);

		cbbLoaiSP = new JComboBox<>();
		cbbLoaiSP.setBounds(82, 326, 129, 22);
		add(cbbLoaiSP);

		JLabel lblTnSp = new JLabel("Tên SP");
		lblTnSp.setBounds(234, 329, 56, 16);
		add(lblTnSp);

		textFTenSP = new JTextField();
		textFTenSP.setBounds(289, 326, 129, 22);
		add(textFTenSP);
		textFTenSP.setColumns(10);

		JLabel lblGi = new JLabel("Giá");
		lblGi.setBounds(29, 384, 56, 16);
		add(lblGi);

		textFGia = new JTextField();
		textFGia.setColumns(10);
		textFGia.setBounds(82, 381, 129, 22);
		add(textFGia);

		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(234, 384, 56, 16);
		add(lblSize);

		cbbSize = new JComboBox<>();
		cbbSize.setBounds(289, 381, 129, 22);
		add(cbbSize);

		JLabel lblHng = new JLabel("Hãng");
		lblHng.setBounds(29, 434, 56, 16);
		add(lblHng);

		textHang = new JTextField();
		textHang.setColumns(10);
		textHang.setBounds(82, 431, 129, 22);
		add(textHang);

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setBounds(234, 434, 56, 16);
		add(lblSLng);

		spinnerSoLuong = new JSpinner();
		spinnerSoLuong.setBounds(311, 431, 107, 22);
		add(spinnerSoLuong);

		btnChoVaoPhieuNhap = new JButton("Cho vào phiếu nhập");
		btnChoVaoPhieuNhap.setBounds(248, 164, 157, 25);
		add(btnChoVaoPhieuNhap);

		btnBoKhoiPhieuNhap = new JButton("Bỏ khỏi phiếu nhập");
		btnBoKhoiPhieuNhap.setBounds(248, 229, 157, 25);
		add(btnBoKhoiPhieuNhap);

		btnNhpHng = new JButton("Nhập hàng");
		btnNhpHng.setBounds(29, 549, 146, 25);
		add(btnNhpHng);

		btnXutPhiuNhp = new JButton("Xuất phiếu nhập");
		btnXutPhiuNhp.setBounds(248, 549, 146, 25);
		add(btnXutPhiuNhp);

		searchSP = new JXSearchField();
		searchSP.setBounds(29, 58, 168, 22);
		add(searchSP);
		searchSP.setName(ACTION_SEARCH_SP);
		searchSP.addKeyListener(this);

		lblChThch = new JLabel("Chú Thích");
		lblChThch.setBounds(12, 478, 73, 16);
		add(lblChThch);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(82, 478, 255, 54);
		add(scrollPane_3);

		textAreaChuThich = new JTextArea();
		scrollPane_3.setViewportView(textAreaChuThich);

		btnTaoPhieu = new JButton("Bắt đầu tạo phiếu mới");
		btnTaoPhieu.setActionCommand("themvaophieunhap");
		btnTaoPhieu.setBounds(248, 107, 157, 25);
		add(btnTaoPhieu);

		buttonRefresh = new JButton("Refresh");
		buttonRefresh.setActionCommand("themvaophieunhap");
		buttonRefresh.setBounds(248, 57, 157, 25);
		add(buttonRefresh);
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
			case ACTION_SEARCH_SP:
				String searchSPx = searchSP.getText();
				loadtableSP(searchSPx);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	/**
	 * 
	 */
	private void addactionforButton() {
		btnBoKhoiPhieuNhap.setActionCommand(ACTION_BOKHOIPHIEUNHAP);
		btnChoVaoPhieuNhap.setActionCommand(ACTION_CHOVAOPHIEUNHAP);
		btnNhpHng.setActionCommand(ACTION_NHAPHANG);
		btnXutPhiuNhp.setActionCommand(ACTION_XUATPHIEUNHAP);
		btnTaoPhieu.setActionCommand(ACTION_TAOPHIEU);
		buttonRefresh.setActionCommand(ACTION_REFRESH);

		btnBoKhoiPhieuNhap.addActionListener(this);
		btnChoVaoPhieuNhap.addActionListener(this);
		btnNhpHng.addActionListener(this);
		btnXutPhiuNhp.addActionListener(this);
		btnTaoPhieu.addActionListener(this);
		buttonRefresh.addActionListener(this);

	}

	/**
	 * 
	 */
	private void actionTableSP() {
		tableSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				posListSP = tableSP.getSelectedRow();
				cbbLoaiSP.setSelectedItem(listSP.get(posListSP).getLoaiSanPham() + "");
				cbbSize.setSelectedItem(listSP.get(posListSP).getSize() + "");
				textFGia.setText(listSP.get(posListSP).getGiaBan() + "");
				textFTenSP.setText(listSP.get(posListSP).getTenSanPham());
				spinnerSoLuong.setValue(listSP.get(posListSP).getTonKho());
				textHang.setText(controllerNhapHang.getTenHangSanXuat(listSP.get(posListSP).getHangSanXuat()));
				textAreaChuThich.setText(listSP.get(posListSP).getChuThich());
			}
		});
		tableChiTietPhieuNhap.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			}
		});
	}

	/**
	 * @param string
	 */
	private void loadtableSP(String serchSP) {
		listSP = controllerNhapHang.getSanPham(serchSP);
		DefaultTableModel model = (DefaultTableModel) tableSP.getModel();
		model.setRowCount(0);
		Object[] row = new Object[TABLESP_SOCOT];
		for (SanPham sp : listSP) {
			row[0] = sp.getTenSanPham();
			model.addRow(row);
		}
		actionTableSanPham(listSP);
	}

	/**
	 * @param listSP
	 */
	private void actionTableSanPham(ArrayList<SanPham> listSP) {
		//
	}

	/**
	 * 
	 */
	private void showCbbSize() {
		ArrayList<String> listSize = controllerNhapHang.getSize();
		for (String size : listSize) {
			cbbSize.addItem(size);
		}
	}

	/**
	 * 
	 */
	private void showCbbMaSP() {
		ArrayList<String> listMaSP = controllerNhapHang.getMaSP();
		for (String maSP : listMaSP) {
			cbbLoaiSP.addItem(maSP);
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
		String actionCommand = event.getActionCommand();
		switch (actionCommand) {
		case ACTION_TAOPHIEU:
			listSPGiaoDich = new ArrayList<>();
			showTableChiTietNhap();
			break;
		case ACTION_CHOVAOPHIEUNHAP:
			SanPham sp = listSP.get(posListSP);
			sp.setTonKho((int) spinnerSoLuong.getValue());
			listSPGiaoDich.add(sp);
			showTableChiTietNhap();
			break;
		case ACTION_BOKHOIPHIEUNHAP:
			int index = tableChiTietPhieuNhap.getSelectedRow();
			listSPGiaoDich.remove(index);
			showTableChiTietNhap();
			break;
		case ACTION_NHAPHANG:
			maPhieuNhap = controllerNhapHang.taoPhieuNhap(listSPGiaoDich, GuiRoot.maNhanVien);
			controllerNhapHang.taoChiTietPhieuNhap(listSPGiaoDich, maPhieuNhap);
			showTablePhieuNhap();
			break;
		case ACTION_XUATPHIEUNHAP:
			int pos = tablePhieuNhap.getSelectedRow();
			System.out.println(pos);
			if (pos != -1) {
				PhieuNhap phieuNhap = listPhieuNhap.get(pos);
				int maPhieuNhap = phieuNhap.getMaPhieuNhap();
				controllerNhapHang.xuatPhieuNhap(maPhieuNhap);
				JOptionPane.showMessageDialog(null, "Vào đây");
			} else {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn phiếu cần in!", "Thông báo", 2);
			}
			break;
		case ACTION_REFRESH:
			loadtableSP("");
			showTableChiTietNhap();
			showTablePhieuNhap();
			break;
		default:

			break;
		}

	}

	/**
	 * 
	 */
	private void showTableChiTietNhap() {
		DefaultTableModel model = (DefaultTableModel) tableChiTietPhieuNhap.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SOCOT_CHITIETPHIEUNHAP];
		for (SanPham sp : listSPGiaoDich) {
			int i = 0;
			row[i++] = sp.getMaSanPham();
			row[i++] = sp.getTenSanPham();
			row[i++] = sp.getSize();
			row[i++] = sp.getGiaBan();
			row[i++] = sp.getTonKho();
			row[i++] = sp.getChuThich();
			model.addRow(row);
		}
	}
}
