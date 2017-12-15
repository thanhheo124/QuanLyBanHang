package com.nhom.giaodien.trangchu.nhanvien;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.controller.ControllerChucVu;
import com.nhom.interfacegiaodien.ICommon;
import com.nhom.models.ChucVu;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class NhanVienChucVu extends JPanel implements ICommon, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SOCOT_TABLE = 3;
	private static final String ACTION_THEM = "Them";
	private static final String ACTION_SUA = "Sua";
	private static final String ACTION_RESET = "Xoa";
	private JTable tableChucVu;
	private JTextField textFMaCV;
	private JTextField textFTenChucVu;
	private JButton btnReset;
	private JButton btnSua;
	private JButton btnThem;
	private JTextArea textAreaGhiChu;

	private ControllerChucVu controllerChucVu;

	private ArrayList<ChucVu> listCV;

	public NhanVienChucVu() {

		init();
		setStyle();
		addComps();
		loadTableChucVu();
		actionTableCV();
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		controllerChucVu = new ControllerChucVu();
		listCV = new ArrayList<>();

		JLabel lblChcV = new JLabel("Chức vụ");
		lblChcV.setForeground(Color.BLUE);
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChcV.setBounds(256, 23, 93, 27);
		add(lblChcV);

		JLabel lblNewLabel = new JLabel("Mã CV");
		lblNewLabel.setBounds(691, 116, 73, 16);
		add(lblNewLabel);

		JLabel lblTnCv = new JLabel("Tên CV");
		lblTnCv.setBounds(691, 173, 73, 16);
		add(lblTnCv);

		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setBounds(691, 229, 73, 16);
		add(lblGhiCh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 98, 614, 384);
		add(scrollPane);

		tableChucVu = new JTable();
		tableChucVu.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Mã CV", "T\u00EAn Ch\u1EE9c v\u1EE5", "Ghi ch\u00FA" }));
		scrollPane.setViewportView(tableChucVu);

		textFMaCV = new JTextField();
		textFMaCV.setBounds(776, 114, 153, 27);
		add(textFMaCV);
		textFMaCV.setColumns(10);
		textFMaCV.setEditable(false);

		textFTenChucVu = new JTextField();
		textFTenChucVu.setColumns(10);
		textFTenChucVu.setBounds(776, 171, 153, 27);
		add(textFTenChucVu);

		textAreaGhiChu = new JTextArea();
		textAreaGhiChu.setBounds(779, 226, 233, 91);
		add(textAreaGhiChu);

		btnThem = new JButton("Thêm");
		btnThem.setBounds(689, 350, 85, 25);
		add(btnThem);
		btnThem.setActionCommand(ACTION_THEM);
		btnThem.addActionListener(this);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(824, 350, 85, 25);
		add(btnSua);
		btnSua.setActionCommand(ACTION_SUA);
		btnSua.addActionListener(this);

		btnReset = new JButton("Reset");
		btnReset.setBounds(968, 350, 85, 25);
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
	private void loadTableChucVu() {
		listCV = controllerChucVu.getListChucVu();
		DefaultTableModel model = (DefaultTableModel) tableChucVu.getModel();
		model.setRowCount(0);
		Object[] row = new Object[SOCOT_TABLE];
		for (ChucVu cv : listCV) {
			row[0] = cv.getMaChucVu();
			row[1] = cv.getTenChucVu();
			row[2] = cv.getGhiChu();

			model.addRow(row);
		}

	}

	/**
	 * 
	 */
	private void actionTableCV() {
		tableChucVu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableChucVu.getSelectedRow();
				textFMaCV.setText(listCV.get(index).getMaChucVu() + "");
				textAreaGhiChu.setText(listCV.get(index).getGhiChu());
				textFTenChucVu.setText(listCV.get(index).getTenChucVu());
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();

		switch (action) {
		case ACTION_RESET:
			textAreaGhiChu.setText("");
			textFMaCV.setText("");
			textFTenChucVu.setText("");
			break;
		case ACTION_SUA:
			int maChucVu = Integer.parseInt(textFMaCV.getText());
			ChucVu cv = new ChucVu(maChucVu, textFTenChucVu.getText(), textAreaGhiChu.getText());
			controllerChucVu.suaChucVu(cv);
			loadTableChucVu();
			actionNhanVienPanel();
			break;
		case ACTION_THEM:
			cv = new ChucVu(0, textFTenChucVu.getText(), textAreaGhiChu.getText());
			controllerChucVu.addChucVu(cv);
			loadTableChucVu();
			actionNhanVienPanel();
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 */
	private void actionNhanVienPanel() {
		Container parent = getParent();
		if (parent instanceof NhanVienTabbed) {
			NhanVienTabbed nvNhanVienTabbed = (NhanVienTabbed) parent;
			nvNhanVienTabbed.getNhanVienPanel().loadCbbChucVu();
			nvNhanVienTabbed.getNhanVienPanel().loadTableNhanVien("", 0);
		}
	}
}
