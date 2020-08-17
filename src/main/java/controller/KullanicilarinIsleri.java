package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import fonksiyonlar.Alerts;
import fonksiyonlar.ExcelYedegi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AracBilgileriData;
import model.KullaniciData;
import util.Database;

public class KullanicilarinIsleri implements Initializable {

	@FXML
	private TableView<AracBilgileriData> Tableviewarac;

	@FXML
	private TableColumn<AracBilgileriData, Integer> tbwkullanicidi;

	@FXML
	private TableColumn<AracBilgileriData, String> tbwplakano;

	@FXML
	private TableColumn<AracBilgileriData, String> tbwaraccinsi;

	@FXML
	private TableColumn<AracBilgileriData, String> tbwyukcinsi;

	@FXML
	private TableColumn<AracBilgileriData, String> tbwyuklemeyeri;

	@FXML
	private TableColumn<AracBilgileriData, Date> tbwyuklemetarihi;

	@FXML
	private TableColumn<AracBilgileriData, String> tbwbosaltmayeri;

	@FXML
	private TableColumn<AracBilgileriData, Date> tbwbosaltmatarihi;

	@FXML
	private TableColumn<AracBilgileriData, Float> tbwnetmiktar;

	@FXML
	private TableColumn<AracBilgileriData, String> tbwyukfiyati;

	@FXML
	private TableColumn<AracBilgileriData, Integer> tbwkomisyon;

	@FXML
	private JFXComboBox<String> kullaniciadlari;

	String secilenad = "";
	ResultSet rs, rs2;
	ButtonType tm = new ButtonType("Tamam");

	ExcelYedegi ExelKayit = new ExcelYedegi();
	Alerts alert = new Alerts();
	AracBilgileriData arac = new AracBilgileriData();
	KullaniciData kuldat = new KullaniciData();
	public ObservableList<AracBilgileriData> araclar = FXCollections.observableArrayList();
	Database data = new Database();

	@FXML
	void kullanicilistele(ActionEvent event) {
		Tableviewarac.getItems().clear();
		secilenad = kullaniciadlari.getValue();
		String sorgukullaniciListele = "Select kullaniciid,plaka, araccins, yukcins, yukmiktar, yukfiyat, yuklemeyeri, yuklemetarihi, bosaltmayeri, bosaltmatarihi, komisyon from  arac where kullaniciid in(select idkullanici from kullanici where kullanicidurum=1 and ad='"
				+ secilenad + "' and yoneticiid in(select idyonetici from yonetici where idyonetici=" + YoneticiGirisi.idyonetici
				+ "))";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgukullaniciListele);
			while (data.rs.next()) {
				araclar.add(new AracBilgileriData(data.rs.getInt(1), data.rs.getString(2), data.rs.getString(3),
						data.rs.getString(4), data.rs.getFloat(5), data.rs.getString(6), data.rs.getString(7),
						data.rs.getDate(8), data.rs.getString(9), data.rs.getDate(10), data.rs.getInt(11)));
			}
		} catch (SQLException e) {
			System.out.println("kullanici araclari Listelenmedi : " + e.getMessage());
		}
	}

	@FXML
	void exelYedegi(ActionEvent event) throws SQLException {
		secilenad = kullaniciadlari.getValue().toString();
		kuldat.kullaniciIdAl(secilenad);
		ExelKayit.exelYedegi(KullaniciData.kullaniciidal);
		alert.showAlertButton(AlertType.INFORMATION, "Bilgilendirme Ekrani", null,
				"Kullaniciya ait isler exele aktarildi.\nYeni yedek olusturmadan lütfen eski dosyayı saklayınız !", tm);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		kuldat.yoneticininKullaniciAdlari(YoneticiGirisi.idyonetici);
		kullaniciadlari.setItems(kuldat.kullaniciadlari);

		String sorgukullaniciIsleri = "Select kullaniciid,plaka, araccins, yukcins, yukmiktar, yukfiyat, yuklemeyeri, yuklemetarihi, bosaltmayeri, bosaltmatarihi, komisyon from  arac where kullaniciid in(select idkullanici from kullanici where kullanicidurum=1 and yoneticiid in(select idyonetici from yonetici where idyonetici="
				+ YoneticiGirisi.idyonetici + "))";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgukullaniciIsleri);
			while (data.rs.next()) {
				araclar.add(new AracBilgileriData(data.rs.getInt(1), data.rs.getString(2), data.rs.getString(3),
						data.rs.getString(4), data.rs.getFloat(5), data.rs.getString(6), data.rs.getString(7),
						data.rs.getDate(8), data.rs.getString(9), data.rs.getDate(10), data.rs.getInt(11)));
			}
		} catch (Exception e) {
			System.out.println("Tum araclar listelenmedi : " + e.getMessage());
		}
		tbwkullanicidi.setCellValueFactory(new PropertyValueFactory<>("kullaniciid"));
		tbwplakano.setCellValueFactory(new PropertyValueFactory<>("plaka"));
		tbwaraccinsi.setCellValueFactory(new PropertyValueFactory<>("araccins"));
		tbwnetmiktar.setCellValueFactory(new PropertyValueFactory<>("yukmiktar"));
		tbwyukcinsi.setCellValueFactory(new PropertyValueFactory<>("yukcins"));
		tbwyukfiyati.setCellValueFactory(new PropertyValueFactory<>("yukfiyat"));
		tbwyuklemeyeri.setCellValueFactory(new PropertyValueFactory<>("yuklemeyeri"));
		tbwyuklemetarihi.setCellValueFactory(new PropertyValueFactory<>("yuklemetarihi"));
		tbwbosaltmayeri.setCellValueFactory(new PropertyValueFactory<>("bosaltmayeri"));
		tbwbosaltmatarihi.setCellValueFactory(new PropertyValueFactory<>("bosaltmatarihi"));
		tbwkomisyon.setCellValueFactory(new PropertyValueFactory<>("komisyon"));
		Tableviewarac.setItems(araclar);
	}
}
