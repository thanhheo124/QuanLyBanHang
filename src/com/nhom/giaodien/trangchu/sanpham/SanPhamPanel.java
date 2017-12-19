package com.nhom.giaodien.trangchu.sanpham;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerSanPham;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.SanPham;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SanPhamPanel extends JPanel implements ICommon, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SOCOT_TABLE_SP = 11;
	private static final String ACTION_THEM = "Theem";
	private static final String ACTION_SUA = "Sua";
	private static final String ACTION_RESET = "Reset";
	private static final String ACTION_XUATBAOGIA = "ACTION_XUATBAOGIA";
	private static final String ACTION_THEMTUFILE = "ACTION_THEMBAOGIA";
	private JTable tableSanPham;
	private JTextField textFMaSanPham;
	private JTextField textFTenSanPham;
	private JTextField textFGiaNhap;
	private JTextField textTonKho;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnReset;
	private JComboBox<String> cbbLoaiSP;
	private JComboBox<String> cbbHangSanXuat;
	private JComboBox<String> cbbSize;
	private JTextArea textAChuThich;
	private ArrayList<SanPham> listSP;
	private int pos;
	private ControllerSanPham controllerSanPham;
	private JTextField textFGiaBan;
	private JLabel lblSize;
	private JButton buttonThemSPTuFile;
	private JButton btnXuatBaoGia;
	private JTextField textTenKH;

	public SanPhamPanel() {

		init();
		setStyle();
		addComps();
		showCBBLoaiSP();
		showCBBHangSanXuat();
		loadTableSanPham();
		loadSize();
		actionTableSp();
	}

	/**
	 * 
	 */
	private void loadSize() {
		ArrayList<String> listSize = controllerSanPham.getSize();
		for (String size : listSize) {
			cbbSize.addItem(size);
		}
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		controllerSanPham = new ControllerSanPham();

		listSP = new ArrayList<>();

		JLabel lblChic = new JLabel("chiếc");
		lblChic.setBounds(592, 424, 86, 22);
		add(lblChic);

		JLabel lblNewLabel = new JLabel("Mã sản phẩm");
		lblNewLabel.setBounds(33, 376, 86, 22);
		add(lblNewLabel);

		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm");
		lblTnSnPhm.setBounds(33, 424, 86, 22);
		add(lblTnSnPhm);

		JLabel lblLoiSnPhm = new JLabel("Loại sản phẩm");
		lblLoiSnPhm.setBounds(33, 476, 86, 22);
		add(lblLoiSnPhm);

		JLabel lblGiNhp = new JLabel("Giá nhập");
		lblGiNhp.setBounds(33, 527, 86, 22);
		add(lblGiNhp);

		JLabel lblHngSnXut = new JLabel("Hãng sản xuất");
		lblHngSnXut.setBounds(371, 376, 86, 22);
		add(lblHngSnXut);

		JLabel lblTnKho = new JLabel("Tồn kho");
		lblTnKho.setBounds(371, 427, 86, 22);
		add(lblTnKho);

		JLabel lblChThch = new JLabel("Chú thích");
		lblChThch.setBounds(669, 376, 86, 22);
		add(lblChThch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 54, 1073, 289);
		add(scrollPane);

		tableSanPham = new JTable();
		tableSanPham.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 s\u1EA3n ph\u1EA9m", "T\u00EAn s\u1EA3n ph\u1EA9m",
						"Lo\u1EA1i s\u1EA3n ph\u1EA9m", "Gi\u00E1 nh\u1EADp", "Gi\u00E1 b\u00E1n",
						"Nh\u00E0 s\u1EA3n xu\u1EA5t", "T\u1ED3n kho", "Size", "Tr\u1EA1ng Th\u00E1i", "\u1EA2nh",
						"Ch\u00FA th\u00EDch" }));
		tableSanPham.getColumnModel().getColumn(0).setPreferredWidth(87);
		tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(88);
		tableSanPham.getColumnModel().getColumn(2).setPreferredWidth(87);
		tableSanPham.getColumnModel().getColumn(3).setPreferredWidth(85);
		tableSanPham.getColumnModel().getColumn(4).setPreferredWidth(87);
		tableSanPham.getColumnModel().getColumn(10).setPreferredWidth(103);
		scrollPane.setViewportView(tableSanPham);

		textFMaSanPham = new JTextField();
		textFMaSanPham.setBounds(122, 376, 188, 22);
		add(textFMaSanPham);
		textFMaSanPham.setColumns(10);
		textFMaSanPham.setEditable(false);

		textFTenSanPham = new JTextField();
		textFTenSanPham.setColumns(10);
		textFTenSanPham.setBounds(122, 424, 188, 22);
		add(textFTenSanPham);

		textFGiaNhap = new JTextField();
		textFGiaNhap.setColumns(10);
		textFGiaNhap.setBounds(122, 527, 188, 22);
		add(textFGiaNhap);

		cbbLoaiSP = new JComboBox<>();
		cbbLoaiSP.setBounds(122, 476, 188, 22);
		add(cbbLoaiSP);

		cbbHangSanXuat = new JComboBox<>();
		cbbHangSanXuat.setBounds(469, 376, 160, 22);
		add(cbbHangSanXuat);

		textTonKho = new JTextField();
		textTonKho.setColumns(10);
		textTonKho.setBounds(469, 424, 107, 22);
		add(textTonKho);

		textAChuThich = new JTextArea();
		textAChuThich.setBounds(764, 376, 188, 25);
		add(textAChuThich);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(658, 423, 97, 25);
		add(btnThem);
		btnThem.setActionCommand(ACTION_THEM);
		btnThem.addActionListener(this);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(802, 423, 97, 25);
		add(btnSua);
		btnSua.setActionCommand(ACTION_SUA);
		btnSua.addActionListener(this);

		btnReset = new JButton("Reset");
		btnReset.setBounds(928, 423, 97, 25);
		add(btnReset);
		btnReset.setActionCommand(ACTION_RESET);
		btnReset.addActionListener(this);

		textFGiaBan = new JTextField();
		textFGiaBan.setColumns(10);
		textFGiaBan.setBounds(469, 524, 160, 22);
		add(textFGiaBan);

		JLabel lblGiBn = new JLabel("Giá bán");
		lblGiBn.setBounds(371, 527, 86, 22);
		add(lblGiBn);

		lblSize = new JLabel("Size");
		lblSize.setBounds(371, 476, 86, 22);
		add(lblSize);

		cbbSize = new JComboBox<String>();
		cbbSize.setBounds(469, 476, 160, 22);
		add(cbbSize);
		
		buttonThemSPTuFile = new JButton("Thêm Sản Phẩm Từ File");
		buttonThemSPTuFile.setActionCommand("Theem");
		buttonThemSPTuFile.setBounds(814, 13, 260, 25);
		add(buttonThemSPTuFile);
		buttonThemSPTuFile.addActionListener(this);
		buttonThemSPTuFile.setActionCommand(ACTION_THEMTUFILE);
		
		btnXuatBaoGia = new JButton("Xuất Báo Giá");
		btnXuatBaoGia.setActionCommand("Theem");
		btnXuatBaoGia.setBounds(658, 526, 201, 25);
		add(btnXuatBaoGia);
		btnXuatBaoGia.setActionCommand(ACTION_XUATBAOGIA);
		btnXuatBaoGia.addActionListener(this);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng cần báo giá");
		lblTnKhchHng.setBounds(658, 479, 201, 22);
		add(lblTnKhchHng);
		
		textTenKH = new JTextField();
		textTenKH.setColumns(10);
		textTenKH.setBounds(865, 476, 160, 22);
		add(textTenKH);
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
	private void loadTableSanPham() {
		listSP = controllerSanPham.getListSP();
		DefaultTableModel model = (DefaultTableModel) tableSanPham.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SOCOT_TABLE_SP];
		for (SanPham sp : listSP) {
			int i = 0;
			row[i++] = sp.getMaSanPham();
			row[i++] = sp.getTenSanPham();
			row[i++] = controllerSanPham.getTenLoaiSP(sp.getLoaiSanPham());
			row[i++] = sp.getGiaNhap();
			row[i++] = sp.getGiaBan();
			row[i++] = controllerSanPham.getTenHangSanXuat(sp.getHangSanXuat());
			row[i++] = sp.getTonKho();
			row[i++] = sp.getSize();
			row[i++] = sp.getTrangThai();
			row[i++] = sp.getImage();
			row[i++] = sp.getChuThich();

			model.addRow(row);
		}
	}

	private void actionTableSp() {
		tableSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pos = tableSanPham.getSelectedRow();
				textFMaSanPham.setText(listSP.get(pos).getMaSanPham() + "");
				textFTenSanPham.setText(listSP.get(pos).getTenSanPham());
				cbbLoaiSP.setSelectedItem(controllerSanPham.getTenLoaiSP(listSP.get(pos).getLoaiSanPham()));
				textFGiaNhap.setText(listSP.get(pos).getGiaNhap() + "");
				cbbHangSanXuat.setSelectedItem(controllerSanPham.getTenHangSanXuat(listSP.get(pos).getHangSanXuat()));
				textFGiaBan.setText(listSP.get(pos).getGiaBan() + "");
				textTonKho.setText(listSP.get(pos).getTonKho() + "");
				textAChuThich.setText(listSP.get(pos).getChuThich());
			}
		});
	}

	private void showCBBHangSanXuat() {
		ArrayList<String> listHangSanXuat = controllerSanPham.getListHangSanXuat();
		for (String tenHangSanXuat : listHangSanXuat) {
			cbbHangSanXuat.addItem(tenHangSanXuat);
		}
	}

	private void showCBBLoaiSP() {
		ArrayList<String> listLoaiSP = controllerSanPham.getListLoaiSP();
		for (String tenLoaiSP : listLoaiSP) {
			cbbLoaiSP.addItem(tenLoaiSP);
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		switch (action) {
		case ACTION_THEM:
			SanPham sp = getSanPhanInForm();
			controllerSanPham.addSanPham(sp);
			loadTableSanPham();
			break;

		case ACTION_SUA:
			sp = getSanPhanInForm();
			controllerSanPham.suaSanPham(sp);
			loadTableSanPham();
			break;
		case ACTION_RESET:
			showCBBLoaiSP();
			showCBBHangSanXuat();
			loadTableSanPham();
			break;
		case ACTION_THEMTUFILE :
			JFileChooser fileChooser= new JFileChooser();
                        FileFilter filter = new FileNameExtensionFilter("Excel file", "xls", "xlsx");
                        fileChooser.setFileFilter(filter);
                        fileChooser.setMultiSelectionEnabled(false);
                        int actionOpen = fileChooser.showOpenDialog(this);
                        if(actionOpen == JFileChooser.APPROVE_OPTION){
                            String excelpath= fileChooser.getSelectedFile().getAbsolutePath();
                            ArrayList<SanPham> listSanPham;
                            try {
                                listSanPham = controllerSanPham.nhapSanPhamTuExcel(excelpath); 
                                //chú thành thực hiện lưu listSP vào DB nhé.                               
                            } catch (IOException ex) {
                                Logger.getLogger(SanPhamPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
			break;
		case ACTION_XUATBAOGIA : 
			String tenKH = textTenKH.getText();
                        if (controllerSanPham.xuatBaoGia(listSP, tenKH) == 2) {
                                JOptionPane.showMessageDialog(null, "Xuất file thành công");
                        } else if (controllerSanPham.xuatBaoGia(listSP, tenKH) == 1) {
                                JOptionPane.showMessageDialog(null, "Tên file phải kết thúc là .pdf", "Lỗi Xuất File",
                                                JOptionPane.ERROR_MESSAGE);
                        } else {
                                JOptionPane.showMessageDialog(null, "Chương trình gặp lỗi xuất file", "Lỗi Xuất File",
                                                JOptionPane.ERROR_MESSAGE);
                        }
		default:
			break;
		}
	}

	/**
	 * @return
	 */
	private SanPham getSanPhanInForm() {
		int maSanPham = Integer.parseInt(textFMaSanPham.getText());
		String tenSanPham = textFTenSanPham.getText();
		int loaiSanPham = controllerSanPham.getMaLoaiSP(cbbLoaiSP.getSelectedItem().toString());
		int hangSanXuat = controllerSanPham.getMaHangSanXuat(cbbHangSanXuat.getSelectedItem().toString());
		int giaBan = Integer.parseInt(textFGiaBan.getText());
		int giaNhap = Integer.parseInt(textFGiaNhap.getText());
		int tonKho = Integer.parseInt(textTonKho.getText());
		int trangThai = 0;
		if (tonKho > 0) {
			trangThai = 1;
		}
		String image = "";
		String chuThich = textAChuThich.getText();
		int size = Integer.parseInt(cbbSize.getSelectedItem().toString());
		SanPham sp = new SanPham(maSanPham, tenSanPham, loaiSanPham, hangSanXuat, giaNhap, giaBan, tonKho, trangThai,
				image, chuThich, size);
		return sp;

	}
}
