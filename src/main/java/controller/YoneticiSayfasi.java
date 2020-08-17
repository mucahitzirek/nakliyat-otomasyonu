package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import fonksiyonlar.Alerts;
import fonksiyonlar.DosyaYol;
import fonksiyonlar.EkranAc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FirmabilgileriData;
import model.KullaniciData;
import util.Database;

public class YoneticiSayfasi implements Initializable {

	@FXML
	private TableView<KullaniciData> tableviewKullanici;

	@FXML
	private TableColumn<KullaniciData, Integer> tbwid;

	@FXML
	private TableColumn<KullaniciData, String> tbwad;

	@FXML
	private TableColumn<KullaniciData, String> tbwsoyad;

	@FXML
	private TableColumn<KullaniciData, String> tbwkullaniciad;

	@FXML
	private TableColumn<KullaniciData, String> tbwkullanicisifre;

	@FXML
	private TableColumn<KullaniciData, String> tbwemail;

	@FXML
	private Label yoneticiad;

	int idkullanici;
	String sorgu, kuladtik = "", kulsifretik = "", kulad, kulsifre;
	Alerts alert = new Alerts();
	EkranAc ekran = new EkranAc();
	Database data = new Database();
	KullaniciData kuldat = new KullaniciData();
	FirmabilgileriData firma = new FirmabilgileriData();

	@FXML
	void KullaniciGoruntule(ActionEvent event) {
		tableviewKullanici.getItems().clear();
		kuldat.yoneticininkullaniclariniGoruntule(YoneticiGirisi.idyonetici);
		tableviewKullanici.setItems(kuldat.kullanicilar);
	}

	@FXML
	void KullaniciEngelle(ActionEvent event) {
		ButtonType engelle = new ButtonType("Engelle");
		ButtonType iptal = new ButtonType("Iptal");
		Optional<ButtonType> secilen = alert.showAlertButtonSil(AlertType.CONFIRMATION, "Bilgilendirme Ekrani", null,
				"Engellemek istediginize emin misiniz ?\nKullaniciya ait tüm bilgiler silinecektir..!", engelle, iptal);
		if (secilen.get() == engelle) {
			KullaniciData secim = tableviewKullanici.getSelectionModel().getSelectedItem();
			if (secim == null) {
				alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null,
						"Lutfen bir kullanıcı seciniz", iptal);
			} else {
				kuladtik = secim.getKullaniciad();
				kulsifretik = secim.getKullanicisifre();
			}
			sorgu = "update kullanici set kullanicidurum=false where kullaniciad='" + kuladtik
					+ "' and kullanicisifre='" + kulsifretik + "' and yoneticiid=" + YoneticiGirisi.idyonetici + ";";
			try {
				data.pst = data.Vtb().prepareStatement(sorgu);
				data.pst.executeUpdate(sorgu);
			} catch (SQLException e) {
				System.out.println("Kullanici engellenemedi : " + e.getMessage());
			}
			KullaniciGoruntule(event);
		} else {
			System.out.println("Engelleme Iptal Edildi");
		}
	}

	@FXML
	void Kullaniciis(ActionEvent event) {
		ekran.yeniEkranUrlAcBuyuyen(DosyaYol.kullanicilarinIsleri, "Kullanicinin Yaptıgı Isler");
	}

	@FXML
	void KullanicilarinIstatistikleri(ActionEvent event) {
		ekran.yeniEkranUrlAcBuyuyen(DosyaYol.kullaniciIstatistikleri, "Kullanici Istatistikleri");
	}

	@FXML
	void firmalarinYükleri(ActionEvent event) {
		ekran.yeniEkranUrlAc(DosyaYol.firmaYukleri,  "Firma Yuk Bilgileri");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		yoneticiad.setText(YoneticiGirisi.adyon + " " + YoneticiGirisi.soyadyon);
		tbwid.setCellValueFactory(new PropertyValueFactory<>("idkullanici"));
		tbwad.setCellValueFactory(new PropertyValueFactory<>("ad"));
		tbwsoyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
		tbwkullaniciad.setCellValueFactory(new PropertyValueFactory<>("kullaniciad"));
		tbwkullanicisifre.setCellValueFactory(new PropertyValueFactory<>("kullanicisifre"));
		tbwemail.setCellValueFactory(new PropertyValueFactory<>("kullaniciemail"));
		tableviewKullanici.setItems(kuldat.kullanicilar);
	}
}
