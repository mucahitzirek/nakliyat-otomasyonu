package fonksiyonlar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.Database;

public class ExcelYedegi {
	Database data=new Database();

	public void exelYedegi(int kulid) {
		// Kullaniciya ait yukler. Dosyayi kaydettikten sonra dosyayı yedekleyip
		// baska bir kullanicinin bilgileri saklanabilir.
		try {
			String sorgu = "Select plaka, araccins, yukcins, yukmiktar,yukfiyat,yuklemeyeri, yuklemetarihi, bosaltmayeri, bosaltmatarihi, komisyon from arac where kullaniciid="
					+ kulid;
			data.Vtb();
			data.rs=data.st.executeQuery(sorgu);
			@SuppressWarnings("resource")
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Arac Bilgileri");
			XSSFRow baslik = sheet.createRow(0);
			baslik.createCell(0).setCellValue("Plaka");
			baslik.createCell(1).setCellValue("Arac cins");
			baslik.createCell(2).setCellValue("Yuk cins");
			baslik.createCell(3).setCellValue("Yuk miktar");
			baslik.createCell(4).setCellValue("Yuk fiyat");
			baslik.createCell(5).setCellValue("Yuklemeyeri");
			baslik.createCell(6).setCellValue("Yukleme tarihi");
			baslik.createCell(7).setCellValue("Bosaltmayeri");
			baslik.createCell(8).setCellValue("Bosaltma tarihi");
			baslik.createCell(9).setCellValue("Komisyon");
			sheet.setColumnWidth(0, 256 * 15);
			sheet.setColumnWidth(1, 256 * 15);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.setColumnWidth(6, 256 * 15);
			sheet.setColumnWidth(7, 256 * 15);
			sheet.setColumnWidth(8, 256 * 15);
			sheet.setZoom(150);
			int artis = 1;
			while (data.rs.next()) {
				XSSFRow row = sheet.createRow(artis);
				row.createCell(0).setCellValue(data.rs.getString("plaka"));
				row.createCell(1).setCellValue(data.rs.getString("araccins"));
				row.createCell(2).setCellValue(data.rs.getString("yukcins"));
				row.createCell(3).setCellValue(data.rs.getString("yukmiktar"));
				row.createCell(4).setCellValue(data.rs.getString("yukfiyat"));
				row.createCell(5).setCellValue(data.rs.getString("yuklemeyeri"));
				row.createCell(6).setCellValue(data.rs.getString("yuklemetarihi"));
				row.createCell(7).setCellValue(data.rs.getString("bosaltmayeri"));
				row.createCell(8).setCellValue(data.rs.getString("bosaltmatarihi"));
				row.createCell(9).setCellValue(data.rs.getString("komisyon"));
				artis++;
			}
			FileOutputStream fileOut = new FileOutputStream("AracBilgileri.xlsx");
			wb.write(fileOut);
			fileOut.close();
		} catch (SQLException e) {
			System.out.println("Exele yazilamadi : " + e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("Dosya Bulunamadi : " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Aktarildi");
	}

}
