package model;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import util.Database;

public class HesaplarData {
	Database data = new Database();
	public static int gunlukiSayi, gunluknetKazanc, aylikSayi, ayliknetKar;
	private String gunluksayiSorgu, gunlukKazancSorgu, ayliknetisSorgu, ayilknetKazancSorgu;
	private String gunlukisSayi = "select count(idarac) sayi from arac where yuklemetarihi='";
	private String gunlukKazanc = "select sum(komisyon) gunlukkazanc from arac where yuklemetarihi='";
	private String aylikisSayi = "select count(idarac) ayliksayi from arac where kullaniciid=";
	private String ayliknetKazanc = "select sum(komisyon) aylikkazanc from arac where kullaniciid=";
	private String yukFiltre = " and yuklemetarihi between '";

	public void gunlukisSayi(LocalDate localDate, int kulid) {
		gunluksayiSorgu = gunlukisSayi + localDate + "' and kullaniciid=" + kulid;
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(gunluksayiSorgu);
			while (data.rs.next()) {
				gunlukiSayi = data.rs.getInt("sayi");
			}
		} catch (SQLException e) {
			System.out.println("Gunluk is Bulunamadi : " + e.getMessage());
		}
	}

	public void gunlukKazanc(LocalDate localDate, int kulid) {
		gunlukKazancSorgu = gunlukKazanc + localDate + "' and kullaniciid=" + kulid + ";";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(gunlukKazancSorgu);
			while (data.rs.next()) {
				gunluknetKazanc = data.rs.getInt("gunlukkazanc");
			}
		} catch (SQLException e) {
			System.out.println("Gunluk Kazanc Bulunamadi" + e.getMessage());
		}
	}

	public void aylikisSayi(int kulid, LocalDate localDate2, LocalDate localDate) {
		ayliknetisSorgu = aylikisSayi + KullaniciData.iddkullanici + yukFiltre + localDate2 + "'and '" + localDate
				+ "';";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(ayliknetisSorgu);
			while (data.rs.next()) {
				aylikSayi = data.rs.getInt("ayliksayi");
			}
		} catch (SQLException e) {
			System.out.println("Aylik Kacanc Sayisi Bulunamadi " + e.getMessage());
		}
	}

	public void aylikKazanc(int kulid, LocalDate localDate2, LocalDate localDate) {
		ayilknetKazancSorgu = ayliknetKazanc + kulid + yukFiltre + localDate2 + "'and '" + localDate + "';";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(ayilknetKazancSorgu);
			while (data.rs.next()) {
				ayliknetKar = data.rs.getInt("aylikkazanc");
			}

		} catch (SQLException e) {
			System.out.println("Aylik Kazanc getirilemedi " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void barChart(@SuppressWarnings("rawtypes") BarChart chart, String sorgu, String baslik) {
		chart.getData().clear();
		chart.setTitle(baslik);
		XYChart.Series<String, Integer> barkayitlar = new XYChart.Series<>();
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				barkayitlar.getData().add(new XYChart.Data<>(data.rs.getString(1), data.rs.getInt(2)));
			}
			chart.getData().add(barkayitlar);
			data.rs.close();
		} catch (SQLException e) {
			System.out.println("Sehire Gore Yapilan is Getirilemedi " + e.getMessage());
		}
	}
}
