/**
 * @Copyright Group 2 - OOP - KS2.K37
 */
package com.nhom.database;

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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhom.models.NhanVien;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Mr Thanh Nov 29, 2017
 *
 */
public class NhanVienDaoImpl extends ConectDataBase {

	/**
	 * @return
	 */
	public ArrayList<NhanVien> getNhanVien(String name, int maNhanVien) {
		ArrayList<NhanVien> listNV = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "";
			PreparedStatement pst;
			if (maNhanVien == 0) {
				sql = "SELECT n.MaNhanVien, n.TenNhanVien, n.NgaySinh, n.GioiTinh, n.NgayVaoLam, c.TenChucVu, n.DiaChi,"
						+ " n.SoDT, n.GhiChu, n.TrangThai  FROM NhanVien as n LEFT JOIN ChucVu as c "
						+ "ON n.ChucVu = c.MaChucVu" + " WHERE TenNhanVien LIKE (?)";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + name + "%");
			} else {
				sql = "SELECT n.MaNhanVien, n.TenNhanVien, n.NgaySinh, n.GioiTinh, n.NgayVaoLam, c.TenChucVu, n.DiaChi,"
						+ " n.SoDT, n.GhiChu, n.TrangThai  FROM NhanVien as n LEFT JOIN ChucVu as c "
						+ "ON n.ChucVu = c.MaChucVu" + " WHERE MaNhanVien LIKE (?)";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, maNhanVien);
			}
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int i = 1;
				int maNV = rs.getInt(i++);
				String tenNhanVien = rs.getString(i++);
				Date ngaySinh = rs.getDate(i++);
				int gioiTinh = rs.getInt(i++);
				Date ngayVaoLam = rs.getDate(i++);
				String chucVu = rs.getString(i++);
				String diaChi = rs.getString(i++);
				String sdt = rs.getString(i++);
				String ghiChu = rs.getString(i++);
				int trangThai = rs.getInt(i);
				NhanVien nv = new NhanVien(maNV, tenNhanVien, ngaySinh, gioiTinh, ngayVaoLam, chucVu, diaChi, sdt,
						ghiChu, trangThai);
				listNV.add(nv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listNV;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getChucVu() {
		ArrayList<String> listChucVu = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT DISTINCT TenChucVu from ChucVu";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listChucVu.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listChucVu;
	}

	/**
	 * @param nv
	 * @return
	 */
	public boolean suaNhanVien(NhanVien nv) {
		boolean check = false;
		try {
			int ChucVu = getChucVuByName(nv.getChucVu());
			conn = getConnection();
			String sql = "UPDATE NhanVien SET TenNhanVien = ? , NgaySinh = ?, GioiTinh = ?, NgayVaoLam = ?, ChucVu = ?,"
					+ " DiaChi = ?, SoDT = ?, GhiChu = ?, TrangThai = ? WHERE MaNhanVien = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, nv.getTenNhanVien());
			pst.setDate(i++, nv.getNgaySinh());
			pst.setInt(i++, nv.getGioiTinh());
			pst.setDate(i++, nv.getNgayVaoLam());
			pst.setInt(i++, ChucVu);
			pst.setString(i++, nv.getDiaChi());
			pst.setString(i++, nv.getSoDT());
			pst.setString(i++, nv.getGhiChu());
			pst.setInt(i++, nv.getTrangThai());
			pst.setInt(i++, nv.getMaNhanVien());

			if (pst.executeUpdate() != 0) {
				check = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return check;
	}

	/**
	 * @param chucVu
	 * @return
	 */
	public int getChucVuByName(String chucVu) {
		int maChucVu = 0;
		try {
			conn = getConnection();
			String sql = "SELECT c.MaChucVu FROM ChucVu as c where c.TenChucVu = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, chucVu);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				maChucVu = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maChucVu;
	}

	/**
	 * @param nv2
	 */
	public int addNhanVien(NhanVien nv2) {
		int generatedKey = 0;
		int chucVu = getChucVuByName(nv2.getChucVu());
		try {
			conn = getConnection();
			String sql = "INSERT INTO NhanVien(TenNhanVien,NgaySinh,GioiTinh,NgayVaoLam,ChucVu,DiaChi,SoDT,GhiChu,TrangThai) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pst.setString(i++, nv2.getTenNhanVien());
			pst.setDate(i++, nv2.getNgaySinh());
			pst.setInt(i++, nv2.getGioiTinh());
			pst.setDate(i++, nv2.getNgayVaoLam());
			pst.setInt(i++, chucVu);
			pst.setString(i++, nv2.getDiaChi());
			pst.setString(i++, nv2.getSoDT());
			pst.setString(i++, nv2.getGhiChu());
			pst.setInt(i, nv2.getTrangThai());
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
        public boolean xuatFileNhanVien(ArrayList<NhanVien> listNV, String search){
            boolean check = false;
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF file (.pdf)", "pdf");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Chọn vị trí lưu file ");
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String Location = chooser.getSelectedFile().toString();
                    if (Location.endsWith("pdf")) {
                            try {
                                    Document doc = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
                                    PdfWriter.getInstance(doc, new FileOutputStream(Location));
                                    doc.open();
                                    BaseFont bf = BaseFont.createFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                                    Paragraph tenCuaHang = new Paragraph("CỬA HÀNG BÁN GIÀY DÉP TVTV", new Font(bf, 16, Font.BOLD));
                                    tenCuaHang.setAlignment(Element.ALIGN_CENTER);
                                    tenCuaHang.setSpacingAfter(15);
                                    Paragraph title = new Paragraph("DANH SÁCH NHÂN VIÊN", new Font(bf, 15, Font.BOLD));
                                    title.setAlignment(Element.ALIGN_CENTER);
                                    title.setSpacingAfter(15);
                                    doc.add(tenCuaHang);
                                    doc.add(title);
                                    if(!search.isEmpty()){
                                        Paragraph timKiem = new Paragraph("Tìm kiếm theo từ khóa: "+ search, new Font(bf, 14, Font.ITALIC));
                                        timKiem.setAlignment(Element.ALIGN_LEFT);
                                        doc.add(timKiem);
                                    }
                                    try {
                                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                                            PdfPTable t = new PdfPTable(9);
                                            t.setTotalWidth(750);
                                            t.setLockedWidth(true);
                                            t.setSpacingBefore(25);
                                            t.setSpacingAfter(15);
                                            t.setWidths(new int[] { 30, 150, 100, 50, 100,  100, 150, 100, 80 });

                                            PdfPCell c1 = new PdfPCell(new Phrase("MÃ", new Font(bf, 14, Font.BOLD)));
                                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c1);
                                            PdfPCell c2 = new PdfPCell(new Phrase("HỌ TÊN", new Font(bf, 14, Font.BOLD)));
                                            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c2);
                                            PdfPCell c3 = new PdfPCell(new Phrase("NGÀY SINH", new Font(bf, 14, Font.BOLD)));
                                            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c3);
                                            PdfPCell c4 = new PdfPCell(new Phrase("GIỚI TÍNH", new Font(bf, 14, Font.BOLD)));
                                            c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c4);
                                            PdfPCell c5 = new PdfPCell(new Phrase("NGÀY VÀO", new Font(bf, 14, Font.BOLD)));
                                            c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c5);
                                            PdfPCell c6 = new PdfPCell(new Phrase("CHỨC VỤ", new Font(bf, 14, Font.BOLD)));
                                            c6.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c6);
                                            PdfPCell c7 = new PdfPCell(new Phrase("ĐỊA CHỈ", new Font(bf, 14, Font.BOLD)));
                                            c7.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c7);
                                            PdfPCell c8 = new PdfPCell(new Phrase("SỐ ĐIỆN THOẠI", new Font(bf, 14, Font.BOLD)));
                                            c8.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c8);
                                            PdfPCell c9 = new PdfPCell(new Phrase("TRẠNG THÁI", new Font(bf, 14, Font.BOLD)));
                                            c9.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c9);

                                            for(NhanVien nv: listNV) {
                                                    t.addCell("" + nv.getMaNhanVien());
                                                    t.addCell(new PdfPCell(new Phrase(nv.getTenNhanVien(), new Font(bf, 14))));
                                                    t.addCell(formatter.format(nv.getNgaySinh()));
                                                    t.addCell((nv.getGioiTinh()==1?"Nam":"Nữ"));
                                                    t.addCell(formatter.format(nv.getNgayVaoLam()));
                                                    t.addCell(new PdfPCell(new Phrase(nv.getChucVu(), new Font(bf, 14))));
                                                    t.addCell(new PdfPCell(new Phrase(nv.getDiaChi(), new Font(bf, 14))));
                                                    t.addCell(nv.getSoDT());
                                                    t.addCell(new PdfPCell(new Phrase((nv.getTrangThai()==1?"Đang làm":"Đã nghỉ"), new Font(bf, 14))));
                                            }
                                            doc.add(t);

                                            java.util.Date ngayLap = new java.util.Date();
                                            Paragraph ngay = new Paragraph("Hà Nội, "+formatter.format(ngayLap),new Font(bf, 14, Font.ITALIC));
                                            ngay.setAlignment(Element.ALIGN_RIGHT);
                                            Paragraph chuKy = new Paragraph("NGƯỜI LẬP PHIẾU",new Font(bf, 14, Font.BOLD));
                                            chuKy.setAlignment(Element.ALIGN_RIGHT);
                                            chuKy.setSpacingAfter(40);
                                            doc.add(ngay);
                                            doc.add(chuKy);
                                            doc.close();
                                            check = true;
                                    } catch (Exception e) {
                                            e.printStackTrace();
                                    } finally {
                                            closeConnection();
                                    }
                                    doc.close();
                            } catch (IOException ex) {
                                    Logger.getLogger(NhanVienDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (DocumentException e1) {
                                    e1.printStackTrace();
                            }
                    } 
            }
            return check;
        }
}
