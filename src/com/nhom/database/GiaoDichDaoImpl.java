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
import com.nhom.models.DanhSachMuaHang;
import com.nhom.models.KhachHang;
import com.nhom.models.SanPham;
import com.nhom.models.SanPhamInforThu;

public class GiaoDichDaoImpl extends ConectDataBase {

	public ArrayList<KhachHang> getKhachHang() {
		ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
		try {
			conn = getConnection();
			String sql = "select *from KhachHang";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int maKhachHang = rs.getInt("MaKhachHang");
				String tenKhachHang = rs.getString("TenKhachHang");
				Date ngaySinh = rs.getDate("NgaySinh");
				int gioiTinh = rs.getInt("GioiTinh");
				String diaChi = rs.getString("DiaChi");
				String sdt = rs.getString("SDT");
				int loaiKhachHang = rs.getInt("LoaiKhachHang");
				String ghiChu = rs.getString("GhiChu");

				KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, ngaySinh, gioiTinh, diaChi, sdt,
						loaiKhachHang, ghiChu);
				listKH.add(khachHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listKH;
	}

	/**
	 * @return
	 */
	public ArrayList<DanhSachMuaHang> getHoaDon(String searchHD) {
		ArrayList<DanhSachMuaHang> listHD = new ArrayList<>();
		String sql = "SELECT h.MaHoaDon,k.TenKhachHang,n.TenNhanVien,h.NgayLapHoaDon,h.TongTien, h.GhiChu "
				+ "from HoaDon as h " + "LEFT JOIN NhanVien as n on (n.MaNhanVien = h.MaNhanVien) "
				+ "LEFT JOIN KhachHang as k on (k.MaKhachHang = h.MaKhachHang) "
				+ "WHERE LOWER(CONCAT(MaHoaDon,TenKhachHang,TenNhanVien)) LIKE lower(?)";
		try {
			conn = getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+searchHD+"%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int maHoaDon = rs.getInt("MaHoaDon");
				String tenKhachHang = rs.getString("TenKhachHang");
				String tenNhanVien = rs.getString("TenNhanVien");
				Date ngayLapHoaDon = rs.getDate("NgayLapHoaDon");
				int tongTien = rs.getInt("TongTien");
				String ghiChu = rs.getString("GhiChu");
				DanhSachMuaHang ds = new DanhSachMuaHang(maHoaDon, tenKhachHang, tenNhanVien, ngayLapHoaDon, tongTien,
						ghiChu);
				listHD.add(ds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listHD;
	}

	/**
	 * search KH
	 * 
	 * @param searchKHx
	 *            : String nameKH
	 * @return listKH
	 */
	public ArrayList<KhachHang> getKhachHang(String searchKHx) {
		ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
		try {
			conn = getConnection();
			String sql = "select *from KhachHang where lower(CONCAT(TenKhachHang,MaKhachHang,SDT)) like lower(?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + searchKHx + "%");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int maKhachHang = rs.getInt("MaKhachHang");
				String tenKhachHang = rs.getString("TenKhachHang");
				Date ngaySinh = rs.getDate("NgaySinh");
				int gioiTinh = rs.getInt("GioiTinh");
				String diaChi = rs.getString("DiaChi");
				String sdt = rs.getString("SDT");
				int loaiKhachHang = rs.getInt("LoaiKhachHang");
				String ghiChu = rs.getString("GhiChu");

				KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, ngaySinh, gioiTinh, diaChi, sdt,
						loaiKhachHang, ghiChu);
				listKH.add(khachHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listKH;
	}

	/**
	 * getListSP
	 * 
	 * @return listSP
	 */
	public ArrayList<SanPham> getSanPham(String serchSP) {
		ArrayList<SanPham> listSP = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select DISTINCT s.MaSanPham,s.TenSanPham,s.LoaiSanPham,s.HangSanXuat,s.GiaNhap,s.GiaBan,s.TonKho,s.GiaBan,"
					+ "s.Image,s.TrangThai,s.ChuThich,s.Size "
					+ "FROM SANPHAM as s INNER JOIN ChiTietPhieuNhap as c ON c.MaSanPham = s.MaSanPham LEFT JOIN HANGSANXUAT as h "
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
	 * getList maSP
	 * 
	 * @return listMSP Distinct
	 */
	public ArrayList<String> getMaSP() {
		ArrayList<String> listMSP = new ArrayList<>();
		String sql = "SELECT DISTINCT MaLoaiSanPham from LoaiSanPham";
		conn = getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String maLoaiSanPham = rs.getInt("MaLoaiSanPham") + "";
				listMSP.add(maLoaiSanPham);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listMSP;
	}

	/**
	 * get listSize
	 * 
	 * @return listSize
	 */
	public ArrayList<String> getSize() {
		ArrayList<String> listSize = new ArrayList<>();
		String sql = "SELECT DISTINCT Size from Size";
		conn = getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String size = rs.getInt("Size") + "";
				listSize.add(size);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listSize;
	}

	/**
	 * @param maKhachHang
	 * @param maNhanVien
	 * @return
	 */
	public int createHoaDonMuaHang(int maKhachHang, int maNhanVien, int tongTien) {
		// tiền mới đầu bằng 0.
		int generatedKey = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO HoaDon(MaKhachHang,MaNhanVien,TongTien) values(?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			pst.setInt(i++, maKhachHang);
			pst.setInt(i++, maNhanVien);
			pst.setInt(i++, tongTien);
			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedKey;
	}

	/**
	 * @param maHoaDon
	 * @param listSPGD
	 * @return
	 */
	public void createChiTietHoaDon(int maHoaDon, ArrayList<SanPham> listSPGD) {
		try {
			for (SanPham sp : listSPGD) {
				conn = getConnection();
				String sql = "INSERT INTO ChiTietHoaDon(MaHoaDon,MaSanPham,SoLuong,TongTien) VALUES (?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, maHoaDon);
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
	public ArrayList<Integer> getListMaHoaDon(String dateTimKiem) {
		ArrayList<Integer> listMaHoaDon = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT MaHoaDon from HoaDon where NgayLapHoaDon like " + dateTimKiem;
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int maPhieuNhap = rs.getInt(1);
				listMaHoaDon.add(maPhieuNhap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listMaHoaDon;
	}

	/**
	 * @param listMaHoaDon
	 * @return
	 */
	public ArrayList<SanPhamInforThu> getListSPBan(ArrayList<Integer> listMaHoaDon) {
		ArrayList<SanPhamInforThu> listSPBan = new ArrayList<>();
		try {
			conn = getConnection();
			for (Integer maHoaDon : listMaHoaDon) {
				String sql = "SELECT s.TenSanPham,s.MaSanPham,h.NgayLapHoaDon,s.GiaBan,k.LoaiKhachHang,c.SoLuong,c.TongTien "
						+ "FROM ChiTietHoaDon as c INNER JOIN SanPham as s ON s.MaSanPham = c.MaSanPham "
						+ "INNER JOIN HoaDon as h ON h.MaHoaDon = c.MaHoaDon "
						+ "INNER JOIN KhachHang as k ON h.MaKhachHang = k.MaKhachHang " + "WHERE h.MaHoaDon = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, maHoaDon);
				pst.addBatch();
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					int i = 1;
					String tenMatHang = rs.getString(i++);
					int maMatHang = rs.getInt(i++);
					Date ngayBan = rs.getDate(i++);
					int giaBan = rs.getInt(i++);
					int loaiKhachHang = rs.getInt(i++);
					int chietKhau = timChietKhau(loaiKhachHang);
					int soLuong = rs.getInt(i++);
					int tongTien = rs.getInt(i++);
					SanPhamInforThu sanPhamInforThu = new SanPhamInforThu(tenMatHang, maMatHang, ngayBan, giaBan,
							chietKhau, soLuong, tongTien);
					listSPBan.add(sanPhamInforThu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listSPBan;
	}

	/**
	 * @param loaiKhachHang
	 * @return
	 */
	private int timChietKhau(int loaiKhachHang) {
		int chietKhau = 0;
		if (loaiKhachHang == 1) {
			chietKhau = 10;
		} else if (loaiKhachHang == 2) {
			chietKhau = 5;
		}
		return chietKhau;
	}

	/**
	 * @param listMaHoaDon
	 * @return
	 */
	public int tinhTienMua(ArrayList<Integer> listMaHoaDon) {
		int tienMua = 0;
		try {
			conn = getConnection();
			for (Integer maHoaDon : listMaHoaDon) {
				String sql = "SELECT s.GiaNhap FROM SanPham as s "
						+ "INNER JOIN ChiTietHoaDon as c ON c.MaSanPham = s.MaSanPham "
						+ "INNER JOIN HoaDon as h ON h.MaHoaDon = c.MaHoaDon " + "WHERE h.MaHoaDon = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1, maHoaDon);
				pst.addBatch();
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					tienMua += rs.getInt(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return tienMua;
	}

	/**
	 * @param maHoaDon
	 * @return
	 */
	public int xuatHoaDon(int maHoaDon) {
		int check = 0;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF file (.pdf)", "pdf");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Chọn vị trí lưu hóa đơn");
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
					Paragraph soHoaDon = new Paragraph("Hóa đơn số: " + maHoaDon, new Font(bf, 14, Font.ITALIC));
					soHoaDon.setAlignment(Element.ALIGN_RIGHT);
					// tenCuaHang.setSpacingAfter(15);
					Paragraph tenCuaHang = new Paragraph("CỬA HÀNG BÁN GIÀY DÉP", new Font(bf, 16, Font.BOLD));
					tenCuaHang.setAlignment(Element.ALIGN_CENTER);
					tenCuaHang.setSpacingAfter(15);
					Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG", new Font(bf, 15, Font.BOLD));
					title.setAlignment(Element.ALIGN_CENTER);
					title.setSpacingAfter(24);
					doc.add(soHoaDon);
					doc.add(tenCuaHang);
					doc.add(title);

					try {
						int stt = 1;
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String date = "";
						String nhanVien = "";
						String khachHang = "";
						String diaChi = "";
						String tong = "";
						conn = getConnection();
						String sql = "select kh.TenKhachHang,kh.DiaChi, hd.NgayLapHoaDon, hd.TongTien as tongcong,"
								+ " nv.TenNhanVien, cthd.SoLuong, cthd.TongTien as thanhtien,sp.TenSanPham, sp.Size, sp.GiaBan "
								+ "from hoadon as hd inner join khachhang as kh "
								+ "on kh.MaKhachHang= hd.MaKhachHang inner join nhanvien as nv "
								+ "on nv.MaNhanVien=hd.MaNhanVien inner join ChiTietHoaDon as cthd "
								+ "on cthd.MaHoaDon = hd.MaHoaDon inner join sanpham as sp "
								+ "on sp.MaSanPham = cthd.MaSanPham where hd.MaHoaDon = " + maHoaDon;
						PreparedStatement pst = conn.prepareStatement(sql);
						ResultSet rs = pst.executeQuery();

						PdfPTable t = new PdfPTable(6);
						t.setTotalWidth(500);
						t.setLockedWidth(true);
						t.setSpacingBefore(25);
						t.setSpacingAfter(15);
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
						PdfPCell c5 = new PdfPCell(new Phrase("Đơn giá", new Font(bf, 14, Font.BOLD)));
						c5.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c5);
						PdfPCell c6 = new PdfPCell(new Phrase("Thành Tiền", new Font(bf, 14, Font.BOLD)));
						c6.setHorizontalAlignment(Element.ALIGN_CENTER);
						t.addCell(c6);

						while (rs.next()) {
							date = formatter.format(rs.getDate("NgayLapHoaDon"));
							nhanVien = rs.getNString("tennhanvien");
							khachHang = rs.getNString("TenKhachHang");
							diaChi = rs.getNString("DiaChi");
							tong = String.format("%,.0f", rs.getDouble("tongcong"));
							t.addCell("" + stt);
							t.addCell(new PdfPCell(new Phrase(rs.getNString("TenSanPham"), new Font(bf, 14))));
							t.addCell(rs.getString("Size"));
							t.addCell("" + rs.getInt("SoLuong"));
							PdfPCell giaBan = new PdfPCell(
									new Phrase(String.format("%,.0fđ", rs.getDouble("GiaBan")), new Font(bf, 14)));
							giaBan.setHorizontalAlignment(Element.ALIGN_RIGHT);
							t.addCell(giaBan);
							PdfPCell thanhTien = new PdfPCell(
									new Phrase(String.format("%,.0fđ", rs.getDouble("thanhtien")), new Font(bf, 14)));
							thanhTien.setHorizontalAlignment(Element.ALIGN_RIGHT);
							t.addCell(thanhTien);
							stt++;
						}

						Paragraph tenKhachHang = new Paragraph("Tên khách hàng: " + khachHang,
								new Font(bf, 14, Font.ITALIC));
						tenKhachHang.setAlignment(Element.ALIGN_LEFT);
						Paragraph diaChiKH = new Paragraph("Địa chỉ: " + diaChi, new Font(bf, 14, Font.ITALIC));
						diaChiKH.setAlignment(Element.ALIGN_LEFT);
						Paragraph ngayMuaHang = new Paragraph("Ngày mua hàng: " + date, new Font(bf, 14, Font.ITALIC));
						ngayMuaHang.setAlignment(Element.ALIGN_LEFT);

						doc.add(tenKhachHang);
						doc.add(diaChiKH);
						doc.add(ngayMuaHang);
						doc.add(t);
						Paragraph tongTien = new Paragraph("Tổng cộng: " + tong + "đ", new Font(bf, 14, Font.BOLD));
						tongTien.setAlignment(Element.ALIGN_RIGHT);
						tongTien.setSpacingAfter(15);
						Paragraph chuKy = new Paragraph(
								"KHÁCH HÀNG                                                             "
										+ "NGƯỜI LẬP PHIẾU",
								new Font(bf, 14, Font.BOLD));
						chuKy.setAlignment(Element.ALIGN_RIGHT);
						chuKy.setSpacingAfter(90);

						// Paragraph khachHangKy = new Paragraph(khachHang, new
						// Font(bf, 14, Font.BOLD));
						// khachHangKy.setAlignment(Element.ALIGN_RIGHT);
						Paragraph nguoiLap = new Paragraph(nhanVien, new Font(bf, 14, Font.BOLD));
						nguoiLap.setAlignment(Element.ALIGN_RIGHT);

						doc.add(tongTien);
						doc.add(chuKy);
						// doc.add(khachHangKy);
						doc.add(nguoiLap);

					} catch (Exception e) {
						e.printStackTrace();
						check = 0;
					} finally {
						closeConnection();
					}

					doc.close();
					check = 2;
				} catch (IOException ex) {
					check = 0;
					Logger.getLogger(NhapHangDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
					// System.out.println(ex);
				} catch (DocumentException e1) {
					check = 0;
					e1.printStackTrace();
				}
			} else {
				check = 1;
			}
		}
		return check;
	}
}
