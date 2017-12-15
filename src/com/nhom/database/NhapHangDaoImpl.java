/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.nhom.models.PhieuNhap;
import com.nhom.models.SanPham;
import com.nhom.models.SanPhamInforChi;

/**
 * @author Mr Thanh Dec 10, 2017
 *
 */
public class NhapHangDaoImpl extends ConectDataBase {

	/**
	 * @param serchSP
	 * @return
	 */
	public ArrayList<SanPham> getSanPham(String serchSP) {
		ArrayList<SanPham> listSP = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select DISTINCT s.MaSanPham,s.TenSanPham,s.LoaiSanPham,s.HangSanXuat,s.GiaNhap,s.GiaBan,s.TonKho,s.GiaBan,"
					+ "s.Image,s.TrangThai,s.ChuThich,s.Size " + "FROM SANPHAM as s LEFT JOIN HANGSANXUAT as h "
					+ "ON h.MaHangSanXuat = s.HangSanXuat WHERE TenSanPham like ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + serchSP + "%");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int maSanPham = rs.getInt("MaSanPham");
				String tenSanPham = rs.getString("TenSanPham");
				int loaiSanPham = rs.getInt("LoaiSanPham");
				int hangSanXuat = rs.getInt("HangSanXuat");
				int giaNhap = rs.getInt("GiaNhap");
				int giaBan = rs.getInt("GiaBan");
				int tonKho = rs.getInt("TonKho");
				int trangThai = rs.getInt("TrangThai");
				String image = rs.getString("Image");
				String chuThich = rs.getString("ChuThich");
				int size = rs.getInt("Size");
				SanPham sp = new SanPham(maSanPham, tenSanPham, loaiSanPham, hangSanXuat, giaNhap, giaBan, tonKho,
						trangThai, image, chuThich, size);
				listSP.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listSP;
	}

	/**
	 * @param tenHangSanXuat
	 * @return
	 */
	public int getMaNhaPhanPhoi(String tenHangSanXuat) {
		int maNhaPhanPhoi = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MaNhaPhanPhoi FROM NhaPhanPhoi WHERE TenNhaPhanPhoi = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tenHangSanXuat);

			pst.executeQuery();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				maNhaPhanPhoi = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maNhaPhanPhoi;
	}

	/**
	 * @param maNhaPhanPhoi
	 * @param maNhanVien
	 * @return
	 */
	public int taoPhieuNhap(ArrayList<SanPham> listSPGD, int maNhanVien) {
		int key = 0;
		int tongTien = 0;
		for (SanPham sp : listSPGD) {
			tongTien += sp.getTonKho() * sp.getGiaBan();
		}
		try {
			conn = getConnection();
			String sql = "INSERT INTO PhieuNhap(MaNhanVien,TongTien) values(?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, maNhanVien);
			pst.setInt(2, tongTien);
			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {
				key = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return key;
	}

	/**
	 * @return
	 */
	public ArrayList<PhieuNhap> getPhieuNhap() {
		ArrayList<PhieuNhap> listPhieuNhap = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT *FROM PhieuNhap";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int i = 1;
				int maPhieuNhap = rs.getInt(i++);
				int maNhanVien = rs.getInt(i++);
				int tongTien = rs.getInt(i++);
				Date ngayNhap = rs.getDate(i++);
				String chuThich = rs.getString(i++);
				PhieuNhap phieuNhap = new PhieuNhap(maPhieuNhap, maNhanVien, tongTien, ngayNhap, chuThich);
				listPhieuNhap.add(phieuNhap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}

		return listPhieuNhap;
	}

	/**
	 * @param maNhanVien
	 * @return
	 */
	public String getTenNhanVien(int maNhanVien) {
		String tenNhanVien = "";
		String sql = "SELECT TenNhanVien FROM NhanVien Where MaNhanVien = (?)";
		try {
			conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, maNhanVien);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tenNhanVien = rs.getString("TenNhanVien");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return tenNhanVien;
	}

	/**
	 * @param listSPGiaoDich
	 * @param maPhieuNhap
	 */
	public void taoChiTietPhieuNhap(ArrayList<SanPham> listSPGD, int maPhieuNhap) {
		try {
			for (SanPham sp : listSPGD) {
				conn = getConnection();
				String sql = "INSERT INTO ChiTietPhieuNhap(MaPhieuNhap,MaSanPham,SoLuong,TongTien) VALUES (?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, maPhieuNhap);
				pst.setInt(2, sp.getMaSanPham());
				pst.setInt(3, sp.getTonKho());
				pst.setInt(4, sp.getGiaBan() * sp.getTonKho());
				pst.addBatch();
				pst.executeBatch();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	/**
	 * @param dateTimKiem
	 * @return
	 */
	public ArrayList<Integer> getListPhieuNhap(String dateTimKiem) {
		ArrayList<Integer> listPhieuNhap = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT MaPhieuNhap from PhieuNhap where NgayNhap like " + dateTimKiem;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int maPhieuNhap = rs.getInt(1);
				listPhieuNhap.add(maPhieuNhap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listPhieuNhap;
	}

	/**
	 * @param listPhieuNhap
	 * @return
	 */
	public ArrayList<SanPhamInforChi> getListSPChi(ArrayList<Integer> listPhieuNhap) {
		ArrayList<SanPhamInforChi> listSPChi = new ArrayList<>();
		try {
			conn = getConnection();
			for (Integer maPhieuNhap : listPhieuNhap) {
				String sql = "SELECT s.TenSanPham,s.MaSanPham,p.NgayNhap,s.GiaNhap,c.SoLuong,c.TongTien "
						+ "FROM PhieuNhap as p INNER JOIN ChiTietPhieuNhap as c ON p.MaPhieuNhap = c.MaPhieuNhap "
						+ "INNER JOIN SanPham as s ON s.MaSanPham = c.MaSanPham " + "WHERE p.MaPhieuNhap = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, maPhieuNhap);
				pst.addBatch();
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int i = 1;
					String tenMatHang = rs.getString(i++);
					int maMatHang = rs.getInt(i++);
					Date ngayNhap = rs.getDate(i++);
					int giaNhap = rs.getInt(i++);
					int soLuong = rs.getInt(i++);
					int tongTien = rs.getInt(i++);
					SanPhamInforChi sp = new SanPhamInforChi(tenMatHang, maMatHang, ngayNhap, giaNhap, soLuong,
							tongTien);
					listSPChi.add(sp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listSPChi;
	}

	/**
	 * @param maPhieuNhap
	 * @return
	 */
	public boolean xuatPhieuNhap(int maPhieuNhap) {
		boolean check = true;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF file (.pdf)", "pdf");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Chọn vị trí lưu phiếu nhập");
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			String Location = chooser.getSelectedFile().toString();

			if (Location.endsWith("pdf")) {
				try {
					Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
					PdfWriter.getInstance(doc, new FileOutputStream(Location));
					doc.open();
					// doc.addTitle(deThi.getTenDeThi());
					BaseFont bf = BaseFont.createFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
					Paragraph tenCuaHang = new Paragraph("CỬA HÀNG BÁN GIÀY DÉP", new Font(bf, 16, Font.BOLD));
					tenCuaHang.setAlignment(Element.ALIGN_CENTER);
					tenCuaHang.setSpacingAfter(15);
					Paragraph title = new Paragraph("PHIẾU NHẬP HÀNG", new Font(bf, 15, Font.BOLD));
					title.setAlignment(Element.ALIGN_CENTER);
					title.setSpacingAfter(15);
					doc.add(tenCuaHang);
					doc.add(title);

					try {
						int stt = 1;
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String date = "";
						String nhanVien = "";
						String tong = "";
						conn = getConnection();
						String sql = "select nv.tennhanvien, sp.TenSanPham, sp.Size, ctpn.SoLuong, sp.GiaNhap, "
								+ "ctpn.TongTien as ThanhTien, pn.TongTien as Tong, pn.NgayNhap from phieunhap as pn inner join nhanvien as nv "
								+ "on pn.MaNhanVien=nv.MaNhanVien inner join ChiTietPhieuNhap as ctpn on ctpn.MaPhieuNhap ="
								+ " pn.MaPhieuNhap inner join sanpham as sp on sp.MaSanPham=ctpn.MaSanPham where "
								+ "pn.MaPhieuNhap= " + maPhieuNhap;
						PreparedStatement pst = conn.prepareStatement(sql);
						ResultSet rs = pst.executeQuery();

						PdfPTable t = new PdfPTable(6);
						t.setTotalWidth(500);
						t.setLockedWidth(true);
						t.setSpacingBefore(25);
						t.setSpacingAfter(25);
						t.setWidths(new int[] { 30, 150, 40, 40, 100, 100 });
						PdfPCell c1 = new PdfPCell(new Phrase("STT", new Font(bf, 14, Font.BOLD)));
						c1.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c1);
						PdfPCell c2 = new PdfPCell(new Phrase("Tên Hàng", new Font(bf, 14, Font.BOLD)));
						c2.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c2);
						PdfPCell c3 = new PdfPCell(new Phrase("Size", new Font(bf, 14, Font.BOLD)));
						c3.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c3);
						PdfPCell c4 = new PdfPCell(new Phrase("SL", new Font(bf, 14, Font.BOLD)));
						c4.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c4);
						PdfPCell c5 = new PdfPCell(new Phrase("Giá", new Font(bf, 14, Font.BOLD)));
						c5.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c5);
						PdfPCell c6 = new PdfPCell(new Phrase("Thành Tiền", new Font(bf, 14, Font.BOLD)));
						c6.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c6);

						while (rs.next()) {
							date = formatter.format(rs.getDate("NgayNhap"));
							nhanVien = rs.getNString("tennhanvien");
							tong = String.format("%,.0f", rs.getDouble("Tong"));
							t.addCell("" + stt);
							t.addCell(new PdfPCell(new Phrase(rs.getNString("TenSanPham"), new Font(bf, 14))));
							t.addCell(rs.getString("Size"));
							t.addCell("" + rs.getInt("SoLuong"));
							PdfPCell giaNhap = new PdfPCell(
									new Phrase(String.format("%,.0fđ", rs.getDouble("GiaNhap")), new Font(bf, 14)));
							giaNhap.setHorizontalAlignment(Element.ALIGN_RIGHT);
							t.addCell(giaNhap);
							PdfPCell thanhTien = new PdfPCell(
									new Phrase(String.format("%,.0fđ", rs.getDouble("ThanhTien")), new Font(bf, 14)));
							thanhTien.setHorizontalAlignment(Element.ALIGN_RIGHT);
							t.addCell(thanhTien);
							stt++;
						}

						Paragraph ngayNhap = new Paragraph("Ngày nhập: " + date, new Font(bf, 14, Font.ITALIC));
						ngayNhap.setAlignment(Element.ALIGN_CENTER);
						Paragraph ma = new Paragraph("Mã phiếu nhập: " + maPhieuNhap, new Font(bf, 14, Font.ITALIC));
						ma.setAlignment(Element.ALIGN_LEFT);
						Paragraph tenNhanVien = new Paragraph("Nhân viên nhập hàng: " + nhanVien,
								new Font(bf, 14, Font.ITALIC));
						tenNhanVien.setAlignment(Element.ALIGN_LEFT);
						doc.add(ngayNhap);
						doc.add(ma);
						doc.add(tenNhanVien);

						doc.add(t);
						Paragraph tongTien = new Paragraph("Tổng cộng: " + tong + "đ", new Font(bf, 14, Font.BOLD));
						tongTien.setAlignment(Element.ALIGN_RIGHT);
						tongTien.setSpacingAfter(15);
						doc.add(tongTien);
						Paragraph nguoiLap = new Paragraph("Người Lập Phiếu", new Font(bf, 14, Font.BOLD));
						nguoiLap.setAlignment(Element.ALIGN_RIGHT);
						nguoiLap.setSpacingAfter(40);
						doc.add(nguoiLap);

					} catch (Exception e) {
						e.printStackTrace();
						check = false;
					} finally {
						closeConnection();
					}

					doc.close();
					JOptionPane.showMessageDialog(null, "Đã xuất file thành công!", "Thông báo", 1);
				} catch (IOException ex) {
					check = false;
					Logger.getLogger(NhapHangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
					// System.out.println(ex);
				} catch (DocumentException e1) {
					check = false;
					e1.printStackTrace();
				}
			} else {
				check = false;
				JOptionPane.showMessageDialog(null, "Tên file phải kết thúc là .pdf", "Lỗi Xuất File",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return check;
	}

}
