package com.nhom.giaodien.trangchu.thongke;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.nhom.interfacegiaodien.ICommon;
import com.nhom.logic.LogicTinhTienThu;

import javax.swing.JButton;

public class ThongKeTabbed extends JPanel implements ICommon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ThongKeTabbed() {

		init();
		addComps();
		setStyle();
	}

	@Override
	public void init() {
		setLayout(null);
	}

	@Override
	public void addComps() {
		JButton btnNewButton = new JButton("Hiện bảng thống kê");
		btnNewButton.setBounds(30, 25, 143, 36);
		add(btnNewButton);
		ArrayList<Float> listThuNhapTheoThangConVert = new LogicTinhTienThu().getTien();
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		dcd.setValue(listThuNhapTheoThangConVert.get(0), "Thu nhập", "Tháng" + 1);
		dcd.setValue(listThuNhapTheoThangConVert.get(1), "Thu nhập", "Tháng" + 2);
		dcd.setValue(listThuNhapTheoThangConVert.get(2), "Thu nhập", "Tháng" + 3);
		dcd.setValue(listThuNhapTheoThangConVert.get(3), "Thu nhập", "Tháng" + 4);
		dcd.setValue(listThuNhapTheoThangConVert.get(4), "Thu nhập", "Tháng" + 5);
		dcd.setValue(listThuNhapTheoThangConVert.get(5), "Thu nhập", "Tháng" + 6);
		dcd.setValue(listThuNhapTheoThangConVert.get(6), "Thu nhập", "Tháng" + 7);
		dcd.setValue(listThuNhapTheoThangConVert.get(7), "Thu nhập", "Tháng" + 8);
		dcd.setValue(listThuNhapTheoThangConVert.get(8), "Thu nhập", "Tháng" + 9);
		dcd.setValue(listThuNhapTheoThangConVert.get(9), "Thu nhập", "Tháng" + 10);
		dcd.setValue(listThuNhapTheoThangConVert.get(10), "Thu nhập", "Tháng" + 11);
		dcd.setValue(listThuNhapTheoThangConVert.get(11), "Thu nhập", "Tháng" + 12);

		JFreeChart jChart = ChartFactory.createBarChart(
				"Thống kê Doanh Thu năm " + Calendar.getInstance().get(Calendar.YEAR), "Tháng", "Số Tiền", dcd,
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot plot = jChart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.BLACK);
		ChartFrame chartFrame = new ChartFrame("Báo cáo Doanh Thu", jChart, true);
		chartFrame.setVisible(false);
		chartFrame.setLocation(400, 300);
		chartFrame.setSize(1000, 500);
		chartFrame.getChartPanel().setLayout(null);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chartFrame.setVisible(true);
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
}
