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
import com.nhom.controller.ControllerSanPham;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.nhom.models.SanPham;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Mr Thanh Dec 9, 2017
 *
 */
public class SanPhamDaoImpl extends ConectDataBase {
	/**
	 * @return
	 */
	public ArrayList<SanPham> getListSP() {
		ArrayList<SanPham> listSP = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT *FROM SanPham";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int i = 1;
				int maSanPham = rs.getInt(i++);
				String tenSanPham = rs.getString(i++);
				int loaiSanPham = rs.getInt(i++);
				int hangSanXuat = rs.getInt(i++);
				int giaNhap = rs.getInt(i++);
				int giaBan = rs.getInt(i++);
				int tonKho = rs.getInt(i++);
				int trangThai = rs.getInt(i++);
				String image = rs.getString(i++);
				String chuThich = rs.getString(i++);
				int size = rs.getInt(i++);
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
	 * @param hangSanXuat
	 * @return
	 */
	public String getTenHangSanXuat(int hangSanXuat) {
		String tenHangSanXuat = "";
		try {
			conn = getConnection();
			String sql = "SELECT TenHangSanXuat FROM HangSanXuat where MaHangSanXuat = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, hangSanXuat);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tenHangSanXuat = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return tenHangSanXuat;
	}

	/**
	 * @param loaiSanPham
	 * @return
	 */
	public String getTenLoaiSP(int loaiSanPham) {
		String tenLoaiSP = "";
		try {
			conn = getConnection();
			String sql = "SELECT TenLoaiSanPham FROM LoaiSanPham where MaLoaiSanPham = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, loaiSanPham);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				tenLoaiSP = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return tenLoaiSP;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListLoaiSP() {
		ArrayList<String> listLoaiSP = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT TenLoaiSanPham From LoaiSanPham";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tenLoaiSP = rs.getString(1);
				listLoaiSP.add(tenLoaiSP);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listLoaiSP;
	}

	/**
	 * @return
	 */
	public ArrayList<String> getListHangSanXuat() {
		ArrayList<String> listTenHangSanXuat = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "SELECT TenHangSanXuat From HangSanXuat";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String tenHangSanXuat = rs.getString(1);
				listTenHangSanXuat.add(tenHangSanXuat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return listTenHangSanXuat;
	}

	/**
	 * @param tenLoaiSP
	 * @return
	 */
	public int getMaLoaiSP(String tenLoaiSP) {
		int maLoaiSP = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MaLoaiSanPham FROM LoaiSanPham where TenLoaiSanPham = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tenLoaiSP);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				maLoaiSP = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maLoaiSP;
	}

	/**
	 * @param tenHangSanXuat
	 * @return
	 */
	public int getMaHangSanXuat(String tenHangSanXuat) {
		int maHangSanXuat = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MaHangSanXuat FROM HangSanXuat where TenHangSanXuat = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, tenHangSanXuat);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				maHangSanXuat = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return maHangSanXuat;
	}

	/**
	 * @param sp
	 */
	public int addSanPham(SanPham sp) {
		int key = 0;
		try {
			conn = getConnection();
			String sql = "INSERT INTO SanPham(TenSanPham,LoaiSanPham,HangSanXuat,GiaNhap,GiaBan,TonKho,TrangThai,Image,ChuThich,Size)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			pst.setString(i++, sp.getTenSanPham());
			pst.setInt(i++, sp.getLoaiSanPham());
			pst.setInt(i++, sp.getHangSanXuat());
			pst.setInt(i++, sp.getGiaNhap());
			pst.setInt(i++, sp.getGiaBan());
			pst.setInt(i++, sp.getTonKho());
			pst.setInt(i++, sp.getTrangThai());
			pst.setString(i++, sp.getImage());
			pst.setString(i++, sp.getChuThich());
			pst.setInt(i++, sp.getSize());
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
	 * @param sp
	 */
	public void suaSanPham(SanPham sp) {
		try {
			conn = getConnection();
			String sql = "UPDATE SanPham SET TenSanPham = ?,LoaiSanPham = ?,HangSanXuat = ?,GiaNhap = ?,"
					+ "GiaBan = ?,TonKho = ?,TrangThai = ?,Image = ?,ChuThich = ?,Size = ? WHERE MaSanPham = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, sp.getTenSanPham());
			pst.setInt(i++, sp.getLoaiSanPham());
			pst.setInt(i++, sp.getHangSanXuat());
			pst.setInt(i++, sp.getGiaNhap());
			pst.setInt(i++, sp.getGiaBan());
			pst.setInt(i++, sp.getTonKho());
			pst.setInt(i++, sp.getTrangThai());
			pst.setString(i++, sp.getImage());
			pst.setString(i++, sp.getChuThich());
			pst.setInt(i++, sp.getSize());
			pst.setInt(i++, sp.getMaSanPham());
			pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

        
        public ArrayList<SanPham> nhapSanPhamTuExcel(String excelFilePath) throws IOException {
                ArrayList<SanPham> listSP = new ArrayList<>();
                FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
                Workbook workbook = getWorkbook(inputStream, excelFilePath);
                Sheet firstSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = firstSheet.iterator();

                while (iterator.hasNext()) {
                    Row nextRow = iterator.next();
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    SanPham sp = new SanPham();
                    try {
                        while (cellIterator.hasNext()) {
                            Cell nextCell = cellIterator.next();
                            if(nextCell.getRowIndex() != 0){
                                int columnIndex = nextCell.getColumnIndex();
                                switch (columnIndex) {
                                    case 1:
                                        sp.setTenSanPham(nextCell.getStringCellValue());
                                        break;
                                    case 2:
                                        sp.setLoaiSanPham((int) nextCell.getNumericCellValue());
                                        break;
                                    case 3:
                                        sp.setHangSanXuat((int) nextCell.getNumericCellValue());
                                        break;
                                    case 4:
                                        sp.setGiaNhap((int) nextCell.getNumericCellValue());
                                        break;
                                    case 5:
                                        sp.setGiaBan((int) nextCell.getNumericCellValue());
                                        break;
                                    case 6:
                                        sp.setTonKho((int) nextCell.getNumericCellValue());
                                        break;
                                    case 7:
                                        sp.setSize((int) nextCell.getNumericCellValue());
                                        break;
                                    case 8:
                                        sp.setImage(nextCell.getStringCellValue());
                                        break;
                                    case 9:
                                        sp.setChuThich(nextCell.getStringCellValue());
                                        break;
                                }
                            }
                        }
                    } catch (Exception e) {
                            System.out.println("Lỗi định dạng thông tin trong file excel!");
                            System.out.println(e);
                    }
                    if(sp.getTenSanPham()!=null){
                        listSP.add(sp);
                    }
                }
                workbook.close();
                inputStream.close();
                return listSP;
    }
	
    //Sử dụng để đọc được cả định dạng .xls và xlsx
    private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
            Workbook workbook = null;
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("Định dạng file đã chọn không phải excel!");
            }
            return workbook;
    }
    
    public int xuatBaoGia(ArrayList<SanPham> listSP, String tenKH){
            int check = 0;
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
                                    Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
                                    PdfWriter.getInstance(doc, new FileOutputStream(Location));
                                    doc.open();
                                    BaseFont bf = BaseFont.createFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                                    Paragraph tenCuaHang = new Paragraph("CỬA HÀNG BÁN GIÀY DÉP TVTV", new Font(bf, 16, Font.BOLD));
                                    tenCuaHang.setAlignment(Element.ALIGN_CENTER);
                                    tenCuaHang.setSpacingAfter(15);
                                    Paragraph title = new Paragraph("BÁO GIÁ SẢN PHẨM", new Font(bf, 15, Font.BOLD));
                                    title.setAlignment(Element.ALIGN_CENTER);
                                    title.setSpacingAfter(15);
                                    doc.add(tenCuaHang);
                                    doc.add(title);
                                    if(!tenKH.isEmpty()){
                                        Paragraph kh = new Paragraph("Kính gửi anh/chị: "+ tenKH + " danh sách báo giá sản phẩm", new Font(bf, 14, Font.ITALIC));
                                        kh.setAlignment(Element.ALIGN_LEFT);
                                        doc.add(kh);
                                    }
                                    try {
                                            PdfPTable t = new PdfPTable(6);
                                            t.setTotalWidth(500);
                                            t.setLockedWidth(true);
                                            t.setSpacingBefore(25);
                                            t.setSpacingAfter(15);
                                            t.setWidths(new int[] {  50, 150, 120, 120, 50, 130});

                                            PdfPCell c2 = new PdfPCell(new Phrase("MÃ SP", new Font(bf, 14, Font.BOLD)));
                                            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c2);
                                            PdfPCell c3 = new PdfPCell(new Phrase("SẢN PHẨM", new Font(bf, 14, Font.BOLD)));
                                            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c3);
                                            PdfPCell c4 = new PdfPCell(new Phrase("LOẠI", new Font(bf, 14, Font.BOLD)));
                                            c4.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c4);
                                            PdfPCell c5 = new PdfPCell(new Phrase("NHÀ SẢN XUẤT", new Font(bf, 14, Font.BOLD)));
                                            c5.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c5);
                                            PdfPCell c6 = new PdfPCell(new Phrase("SIZE", new Font(bf, 14, Font.BOLD)));
                                            c6.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c6);
                                            PdfPCell c7 = new PdfPCell(new Phrase("GIÁ BÁN", new Font(bf, 14, Font.BOLD)));
                                            c7.setHorizontalAlignment(Element.ALIGN_CENTER);
                                            t.addCell(c7);

                                            for(SanPham sp: listSP) {
                                                    t.addCell("" + sp.getMaSanPham());
                                                    t.addCell(new PdfPCell(new Phrase(sp.getTenSanPham(), new Font(bf, 14))));
                                                    String loaiSP = getTenLoaiSP(sp.getLoaiSanPham());
                                                    String tenHangSX = getTenHangSanXuat(sp.getHangSanXuat());
                                                    t.addCell(new PdfPCell(new Phrase(loaiSP, new Font(bf, 14))));
                                                    t.addCell(new PdfPCell(new Phrase(tenHangSX, new Font(bf, 14))));
                                                    t.addCell(""+ sp.getSize());
                                                    PdfPCell giaBan = new PdfPCell(new Phrase(String.format("%,dđ", sp.getGiaBan()), new Font(bf, 14)));
                                                    giaBan.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                                    t.addCell(giaBan);
                                            }
                                            doc.add(t);
                                            
                                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                            java.util.Date ngayLap = new java.util.Date();
                                            Paragraph ngay = new Paragraph("Hà Nội, "+formatter.format(ngayLap),new Font(bf, 14, Font.ITALIC));
                                            ngay.setAlignment(Element.ALIGN_RIGHT);
                                            Paragraph chuKy = new Paragraph("NGƯỜI LẬP PHIẾU",new Font(bf, 14, Font.BOLD));
                                            chuKy.setAlignment(Element.ALIGN_RIGHT);
                                            chuKy.setSpacingAfter(40);
                                            doc.add(ngay);
                                            doc.add(chuKy);
                                            doc.close();
                                            check = 2;
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
                    } else check = 1; 
            }
            return check;
        }
}
