package com.nhom.giaodien.trangchu;

import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.nhom.giaodien.trangchu.chungtoi.ChungToiTabbed;
import com.nhom.giaodien.trangchu.doanhthu.DoanhThuTabbed;
import com.nhom.giaodien.trangchu.doitac.DoiTacTabbed;
import com.nhom.giaodien.trangchu.giaodich.GiaoDichTabbeb;
import com.nhom.giaodien.trangchu.khachhang.KhachHangTabbed;
import com.nhom.giaodien.trangchu.nhanvien.NhanVienTabbed;
import com.nhom.giaodien.trangchu.sanpham.SanPhamTabbed;
import com.nhom.giaodien.trangchu.thongke.ThongKeTabbed;
import com.nhom.interfacegiaodien.ICommon;
import javax.swing.ImageIcon;

public class TabbebRoot extends JTabbedPane implements ICommon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GiaoDichTabbeb tabbedGiaoDich;
	private SanPhamTabbed tabbedSanPham;
	private KhachHangTabbed tabbedKhachHang;
	private NhanVienTabbed tabbedNhanVien;
	private DoiTacTabbed tabbedDoiTac;
	private DoanhThuTabbed tabbedDoanhThu;
	private ChungToiTabbed tabbedChungToi;
	private ThongKeTabbed tabbedThongKe;

	public TabbebRoot() {
		init();
		setStyle();
		addComps();
	}

	@Override
	public void init() {
		setTabPlacement(LEFT);
		setBounds(12, 25, 1290, 660);
		setVisible(true);
		setAutoscrolls(false);
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	@Override
	public void addComps() {

		tabbedKhachHang = new KhachHangTabbed();
		addTab("Khách hàng", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/khachhang.png")),
				tabbedKhachHang, null);

		tabbedSanPham = new SanPhamTabbed();
		addTab("Sản Phẩm", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/sanpham.png")), tabbedSanPham,
				null);

		tabbedDoiTac = new DoiTacTabbed();
		addTab("Đối tác", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/doitac.png")), tabbedDoiTac,
				null);

		tabbedGiaoDich = new GiaoDichTabbeb();
		addTab("Giao Dịch", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/giaodich.png")),
				tabbedGiaoDich, null);

		tabbedDoanhThu = new DoanhThuTabbed();
		addTab("Doanh Thu", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/doanhthu.png")),
				tabbedDoanhThu, null);

		tabbedThongKe = new ThongKeTabbed();
		addTab("Thống Kê", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/thongke.png")), tabbedThongKe,
				null);
	
		tabbedNhanVien = new NhanVienTabbed();
		addTab("Nhân viên", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/nhanvien.png")),
				tabbedNhanVien, null);

		tabbedChungToi = new ChungToiTabbed();
		addTab("About Ours", new ImageIcon(TabbebRoot.class.getResource("/com/nhom/images/me.png")), tabbedChungToi,
				null);
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
