package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import fonksiyonlar.DosyaYol;
import fonksiyonlar.EkranAc;
import fonksiyonlar.Pieler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.HesaplarData;
import model.KullaniciData;

public class Hesaplar implements Initializable {
	@FXML
	private Label gunlukis;
	@FXML
	private Label gunlukkazanc;
	@FXML
	private Label aylikis;
	@FXML
	private Label aylikkazanc;
	@FXML
	private BarChart<String, Integer> chart;
	@FXML
	private PieChart pie;
	LocalDate dat = LocalDate.now();
	String sorgu;
	EkranAc ekran = new EkranAc();
	Pieler piegoster = new Pieler();
	HesaplarData hesap = new HesaplarData();

	@FXML
	void defaultgoster(MouseEvent event) {
		piegoster.netKazanc(pie, 1);
	}

	@FXML
	void gider(ActionEvent event) {
		ekran.yeniEkranUrlAc(DosyaYol.giderEkle, "Gider Ekle");
	}

	@FXML
	void istatistikgoster(ActionEvent event) {
		piegoster.netKazanc(pie, 1);

	}

	@FXML
	void sehiregore(ActionEvent event) {
		LocalDate dat3 = dat.minusMonths(1);
		sorgu = "SELECT bosaltmayeri,count(yuklemetarihi) sehirsayi FROM arac  where yuklemetarihi between '" + dat3
				+ "' and '" + dat + "' group by bosaltmayeri;";
		hesap.barChart(chart, sorgu, "Aylik sehire göre is");
	}

	@FXML
	void soforegore(ActionEvent event) {
		chart.getData().clear();
		sorgu = "Select ad, count(arac.yuklemetarihi) yukadet from sofor INNER JOIN arac On arac.soforid=sofor.idsofor group by arac.soforid,sofor.ad;";
		hesap.barChart(chart, sorgu, "Soforlerin Yaptigi is Sayisi");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LocalDate dat2 = dat.minusMonths(1);
		hesap.gunlukisSayi(dat2, KullaniciData.iddkullanici);
		hesap.gunlukKazanc(dat2, KullaniciData.iddkullanici);
		hesap.aylikisSayi(KullaniciData.iddkullanici, dat2, dat);
		hesap.aylikKazanc(KullaniciData.iddkullanici, dat2, dat);
		gunlukis.setText(String.valueOf(HesaplarData.gunlukiSayi));
		gunlukkazanc.setText(String.valueOf(HesaplarData.gunluknetKazanc));
		aylikis.setText(String.valueOf(HesaplarData.aylikSayi));
		aylikkazanc.setText(String.valueOf(HesaplarData.ayliknetKar));
	}
}
