package com.nhom.giaodien.trangchu.doanhthu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import com.nhom.controller.ControllerDoanhThu;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.SanPhamInforChi;
import com.nhom.models.SanPhamInforThu;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DoanhThuTabbed extends JPanel implements ICommon, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ACTION_THANG = "Thang";
	private static final int TABLE_CHI_SOCOT = 6;
	private static final int SOCOT_SP_BAN = 7;
	private static final String ACTION_XUATFILECHI = "Chi";
	private static final String ACTION_XUATFILETHU = "Thu";
	private JTable tableChi;
	private JComboBox<Integer> cbbThang;
	private JTable tableThu;
	private JLabel labelYear;
	private JLabel labelChi;
	private JLabel labelThu;
	private JLabel labelDoanhThuThang;
	private ControllerDoanhThu controllerDoanhThu;

	private String dateTimKiem;
	private int year;
	private JButton btnXuatFileChi;
	private JButton btnXutFileThu;

	public DoanhThuTabbed() {
		init();
		setStyle();
		addComps();
		showCbbThang();
	}

	/**
	 * 
	 */
	private void showCbbThang() {
		for (int i = 1; i <= 12; i++) {
			cbbThang.addItem(i);
		}
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		cbbThang.setSelectedItem(month);
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		controllerDoanhThu = new ControllerDoanhThu();

		JLabel lblNewLabel = new JLabel("Bảng tổng kết doanh thu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(438, 13, 385, 28);
		add(lblNewLabel);

		JLabel lblThng = new JLabel("Tháng");
		lblThng.setBounds(136, 49, 56, 16);
		add(lblThng);

		cbbThang = new JComboBox<>();
		cbbThang.setForeground(Color.BLUE);
		cbbThang.setBounds(179, 46, 74, 22);
		add(cbbThang);
		cbbThang.setActionCommand(ACTION_THANG);
		cbbThang.addActionListener(this);

		JLabel lblNm = new JLabel("Năm");
		lblNm.setBounds(265, 49, 56, 16);
		add(lblNm);

		labelYear = new JLabel("");
		labelYear.setForeground(Color.BLUE);
		labelYear.setBounds(312, 49, 91, 19);
		add(labelYear);
		year = Calendar.getInstance().get(Calendar.YEAR);
		labelYear.setText(year + "");

		JLabel lblNewLabel_1 = new JLabel("Bảng Chi");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(204, 0, 255));
		lblNewLabel_1.setBounds(146, 78, 99, 16);
		add(lblNewLabel_1);

		JLabel lblBngThu = new JLabel("Bảng Thu");
		lblBngThu.setForeground(new Color(204, 0, 255));
		lblBngThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBngThu.setBounds(136, 285, 99, 28);
		add(lblBngThu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(136, 107, 897, 165);
		add(scrollPane);

		tableChi = new JTable();
		tableChi.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "T\u00EAn m\u1EB7t h\u00E0ng", "M\u00E3 MH", "Ng\u00E0y nh\u1EADp", "Gi\u00E1 nh\u1EADp",
						"S\u1ED1 l\u01B0\u1EE3ng", "T\u1ED5ng ti\u1EC1n" }));
		scrollPane.setViewportView(tableChi);

		JLabel lblTngTin = new JLabel("Tổng tiền");
		lblTngTin.setForeground(new Color(204, 0, 255));
		lblTngTin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTngTin.setBounds(370, 285, 99, 16);
		add(lblTngTin);

		JLabel lblVnd = new JLabel("VND");
		lblVnd.setForeground(new Color(204, 0, 255));
		lblVnd.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVnd.setBounds(606, 285, 99, 16);
		add(lblVnd);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(136, 326, 897, 165);
		add(scrollPane_1);

		tableThu = new JTable();
		tableThu.setModel(
				new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
						new String[] { "T\u00EAn m\u1EB7t h\u00E0ng", "M\u00E3 MH", "Ng\u00E0y b\u00E1n",
								"Gi\u00E1 b\u00E1n", "Chi\u1EBFt kh\u1EA5u", "S\u1ED1 l\u01B0\u1EE3ng",
								"T\u1ED5ng ti\u1EC1n" }));
		scrollPane_1.setViewportView(tableThu);

		JLabel label = new JLabel("Tổng tiền");
		label.setForeground(new Color(204, 0, 255));
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(370, 518, 99, 16);
		add(label);

		JLabel label_1 = new JLabel("VND");
		label_1.setForeground(new Color(204, 0, 255));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(606, 514, 99, 16);
		add(label_1);

		JLabel lblTngDoanhThu = new JLabel("Tổng doanh thu tháng ");
		lblTngDoanhThu.setForeground(new Color(204, 0, 255));
		lblTngDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTngDoanhThu.setBounds(138, 552, 171, 28);
		add(lblTngDoanhThu);

		JLabel label_2 = new JLabel("VND");
		label_2.setForeground(new Color(204, 0, 255));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(459, 559, 99, 16);
		add(label_2);

		labelChi = new JLabel("");
		labelChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelChi.setForeground(Color.BLUE);
		labelChi.setBounds(470, 273, 113, 40);
		add(labelChi);

		labelThu = new JLabel("");
		labelThu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelThu.setForeground(Color.BLUE);
		labelThu.setBounds(456, 506, 138, 40);
		add(labelThu);

		labelDoanhThuThang = new JLabel("");
		labelDoanhThuThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelDoanhThuThang.setForeground(Color.BLUE);
		labelDoanhThuThang.setBounds(321, 536, 113, 60);
		add(labelDoanhThuThang);

		btnXuatFileChi = new JButton("Xuất File Chi");
		btnXuatFileChi.setBounds(809, 288, 154, 25);
		add(btnXuatFileChi);
		btnXuatFileChi.addActionListener(this);
		btnXuatFileChi.setActionCommand(ACTION_XUATFILECHI);

		btnXutFileThu = new JButton("Xuất File Thu");
		btnXutFileThu.setBounds(809, 504, 154, 25);
		add(btnXutFileThu);
		btnXutFileThu.addActionListener(this);
		btnXutFileThu.setActionCommand(ACTION_XUATFILETHU);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		int thang = Integer.parseInt(cbbThang.getSelectedItem().toString());
		String thangString = "";
		if (thang < 10) {
			thangString = "0" + thang;
		} else {
			thangString = thang + "";
		}
		dateTimKiem = "N'" + year + "-" + thangString + "-%'";
		switch (actionCommand) {
		case ACTION_THANG:
			if (dateTimKiem != null) {
				ArrayList<Integer> listPhieuNhap = controllerDoanhThu.getListPhieuNhap(dateTimKiem);
				ArrayList<SanPhamInforChi> listSPInforChi = controllerDoanhThu.getListSPChi(listPhieuNhap);
				loadTableChi(listSPInforChi);

				//
				ArrayList<Integer> listMaHoaDon = controllerDoanhThu.getListMaHoaDon(dateTimKiem);
				ArrayList<SanPhamInforThu> listSPInforBan = controllerDoanhThu.getListSPBan(listMaHoaDon);
				int tienThu = loadTableThu(listSPInforBan);
				int tienChi = controllerDoanhThu.tinhTienMua(listMaHoaDon);
				int thuNhap = tienThu - tienChi;
				labelDoanhThuThang.setText(thuNhap + "");
			}
			break;
//		case ACTION_XUATFILECHI:
//                        ArrayList<Integer> listPhieuNhap = controllerDoanhThu.getListPhieuNhap(dateTimKiem);
//                        ArrayList<SanPhamInforChi> listSPInforChi = controllerDoanhThu.getListSPChi(listPhieuNhap);
//                        if(controllerDoanhThu.xuatFileChi(listSPInforChi, thang, year)){
//                            JOptionPane.showMessageDialog(null, "Đã xuất file!" , "Thông báo", 1);
//                        }else JOptionPane.showMessageDialog(null, "Chưa xuất file!" , "Thông báo", 2);
//			break;
		case ACTION_XUATFILETHU:
			ArrayList<Integer> listPhieuNhap = controllerDoanhThu.getListPhieuNhap(dateTimKiem);
                        ArrayList<SanPhamInforChi> listSPInforChi = controllerDoanhThu.getListSPChi(listPhieuNhap);
                        ArrayList<Integer> listMaHoaDon = controllerDoanhThu.getListMaHoaDon(dateTimKiem);
                        ArrayList<SanPhamInforThu> listSPInforThu = controllerDoanhThu.getListSPBan(listMaHoaDon);
                        
                        if(controllerDoanhThu.xuatFileDoanhThu(listSPInforChi,listSPInforThu, thang, year)){
                            JOptionPane.showMessageDialog(null, "Đã xuất file!" , "Thông báo", 1);
                        }else JOptionPane.showMessageDialog(null, "Chưa xuất file!" , "Thông báo", 2);
			break;
		default:
			break;
		}
	}

	/**
	 * @param listSPInforBan
	 */
	private int loadTableThu(ArrayList<SanPhamInforThu> listSPInforBan) {
		int tongTienBan = 0;
		DefaultTableModel model = (DefaultTableModel) tableThu.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SOCOT_SP_BAN];
		for (SanPhamInforThu sp : listSPInforBan) {
			int i = 0;
			row[i++] = sp.getTenMatHang();
			row[i++] = sp.getMaMatHang();
			row[i++] = sp.getNgayBan();
			row[i++] = sp.getGiaBan();
			row[i++] = sp.getChietKhau();
			row[i++] = sp.getSoLuong();
			row[i++] = sp.getTongTien();
			tongTienBan += sp.getTongTien();

			model.addRow(row);
		}
		labelThu.setText(tongTienBan + "");
		return tongTienBan;
	}

	/**
	 * @param listSPInforChi
	 */
	private void loadTableChi(ArrayList<SanPhamInforChi> listSPInforChi) {
		DefaultTableModel model = (DefaultTableModel) tableChi.getModel();
		model.setRowCount(0);
		Object[] row = new Object[TABLE_CHI_SOCOT];
		long tongTienChi = 0;
		for (SanPhamInforChi sp : listSPInforChi) {
			int i = 0;
			row[i++] = sp.getTenMatHang();
			row[i++] = sp.getMaMatHang();
			row[i++] = sp.getNgayNhap();
			row[i++] = sp.getGiaNhap();
			row[i++] = sp.getSoLuong();
			row[i++] = sp.getTongTien();
			tongTienChi += sp.getTongTien();
			model.addRow(row);
		}
		labelChi.setText(tongTienChi + "");
	}
}
