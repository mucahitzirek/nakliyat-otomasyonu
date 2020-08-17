
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import fonksiyonlar.Alerts;
import fonksiyonlar.DosyaYol;
import fonksiyonlar.EkranAc;
import fonksiyonlar.OpaklikVer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.KullaniciData;
import util.Database;

public class KullaniciGirisi implements Initializable {

	@FXML
	private Pane anapane;
	@FXML
	private AnchorPane GirisAnchor;
	@FXML
	private JFXTextField kullaniciadi;
	@FXML
	private JFXTextField kullanicisifresi;
	public static String ad, soyad;
	String kullaniciad, kullanicisifre, sorgu;

	boolean deger;
	EkranAc ekran = new EkranAc();
	Alerts alert = new Alerts();
	OpaklikVer opaklik = new OpaklikVer();
	Database data = new Database();
	KullaniciData kullan = new KullaniciData();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		opaklik.Opaklik(GirisAnchor);
	}

	@FXML
	void yonetyicigirisi(ActionEvent event) {
		ekran.yeniEkranici(anapane, DosyaYol.yoneticiGirisi);
	}

	@FXML
	void giris(ActionEvent event) {
		kullaniciad = kullaniciadi.getText();
		kullanicisifre = kullanicisifresi.getText();
		kullan.girisBag(kullaniciad, kullanicisifre);
		if (kullan.getDurum() == true) {
			System.out.println("girdi");
			try {
				kullan.girisAdSoyadAl(kullaniciad, kullanicisifre);
				ekran.yeniEkranUrlAcBuyuyen(DosyaYol.anaSayfa, "Nakliyat Otomasyonu Ana Sayfa");
				Main.stage.close();
			} catch (Exception e) {
				System.out.println("Giris Button : " + e.getMessage());
			}
		} else {
			ButtonType tm = new ButtonType("Tamam");
			alert.showAlertButton(AlertType.WARNING, "Bilgilendirme Ekranı", null,
					"Lutfen kullanici adinizi ve sifrenizi kontrol ediniz", tm);
		}
	}

	@FXML
	void sifrehatirlama(MouseEvent event) throws IOException {
		ekran.yeniEkranUrlAc(DosyaYol.sifreHatirlama, "Sifre Hatırlama");
	}

	@FXML
	void yoneticikaydi(MouseEvent event) {
		ekran.yeniEkranici(anapane, DosyaYol.yoneticiKaydi);
	}

	@FXML
	void kullanicikaydi(MouseEvent event) {
		ekran.yeniEkranici(anapane, DosyaYol.kullaniciKaydi);
	}

	@FXML
	void closeapp(MouseEvent event) {
		System.exit(0);
	}

}
