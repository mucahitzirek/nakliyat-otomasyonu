package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDatePicker;
import fonksiyonlar.Alerts;
import fonksiyonlar.DosyaYol;
import fonksiyonlar.EkranAc;
import fonksiyonlar.ExcelYedegi;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AracBilgileriData;
import model.KullaniciData;
import model.SoforbilgileriData;
import util.Database;

public class AracBilgileri implements Initializable {

	@FXML
	private TableView<AracBilgileriData> Tableviewarac;

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
	private JFXDatePicker date1;

	@FXML
	private JFXDatePicker date2;
	@FXML
	private TextField aramatextarac;
	SoforbilgileriData secildi;


	Alerts alert = new Alerts();
	ExcelYedegi exel = new ExcelYedegi();
	EkranAc ekran = new EkranAc();
	AracBilgileriData arac = new AracBilgileriData();
	Database data = new Database();

	@FXML
	void araclistele(ActionEvent event) {
		Tableviewarac.getItems().clear();
		arac.aracListele(KullaniciData.iddkullanici);
		Tableviewarac.setItems(arac.yeniarac);
	}

	@FXML
	void tarihlistele(ActionEvent event) {
		Tableviewarac.getItems().clear();
		arac.aractarihegöreListele(KullaniciData.iddkullanici, date1.getValue(), date2.getValue());
		Tableviewarac.setItems(arac.yeniarac);
	}

	@FXML
	void ekle(ActionEvent event) {
		ekran.yeniEkranUrlAc(DosyaYol.yeniseferOlustur, "Yeni Sefer Olustur");
	}



	@FXML
	void exeleaktar(ActionEvent event) {
		exel.exelYedegi(KullaniciData.iddkullanici);
		ButtonType tm = new ButtonType("Tamam");
		alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Kullaniciya ait isler exele aktarildi.\nYeni yedek olusturmadan lütfen eski dosyayı saklayınız !", tm);
	}

	public void ara() {
		try {
			FilteredList<AracBilgileriData> filter = new FilteredList<AracBilgileriData>(arac.yeniarac, b -> true);
			aramatextarac.textProperty().addListener((observable, eski, yeni) -> {
				filter.setPredicate(araci -> {
					if (yeni == null || yeni.isEmpty()) {
						return true;
					}
					String filtrem = yeni.toLowerCase();
					if (araci.getPlaka().toLowerCase().indexOf(filtrem) != -1) {
						return true;
					} else if (araci.getAraccins().toLowerCase().indexOf(filtrem) != -1) {
						return true;
					} else if (araci.getYuklemeyeri().toLowerCase().indexOf(filtrem) != -1) {
						return true;
					} else if (araci.getBosaltmayeri().toLowerCase().indexOf(filtrem) != -1) {
						return true;
					} else if (String.valueOf(araci.getYukmiktar()).toLowerCase().indexOf(filtrem) != -1) {
						return true;
					} else
						return false;
				});
			});
			SortedList<AracBilgileriData> sort2 = new SortedList<AracBilgileriData>(filter);
			sort2.comparatorProperty().bind(Tableviewarac.comparatorProperty());
			Tableviewarac.setItems(sort2);
		} catch (Exception e) {
			System.out.println("ss: " + e.getMessage());
		}
	}

	@FXML
	void sil(ActionEvent event) {
		ButtonType sil = new ButtonType("Sil");
		ButtonType silme = new ButtonType("Silme");
		ButtonType cik = new ButtonType("Cik");
		AracBilgileriData secim = Tableviewarac.getSelectionModel().getSelectedItem();
		if ((secim == null)) {
			alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Lutfen bir kayit seciniz",
					cik);
		} else {
			Optional<ButtonType> secilen = alert.showAlertButtonSil(AlertType.INFORMATION, "Bilgilendirme Ekrani", null,
					"Silmek istediginize emin misiniz?", sil, silme);
			if (secilen.get() == sil) {
				try {
					String sorgu = "delete from arac where plaka=? and araccins=? and yukcins=? "
							+ "and yukmiktar=? and yukfiyat=? and yuklemeyeri=? and yuklemetarihi=? and bosaltmayeri=? and bosaltmatarihi=? and kullaniciid="
							+KullaniciData.iddkullanici + ";";
					data.pst = data.Vtb().prepareStatement(sorgu);
					data.pst.setString(1, secim.getPlaka());
					data.pst.setString(2, secim.getAraccins());
					data.pst.setString(3, secim.getYukcins());
					data.pst.setFloat(4, secim.getYukmiktar());
					data.pst.setString(5, secim.getYukfiyat());
					data.pst.setString(6, secim.getYuklemeyeri());
					data.pst.setDate(7, secim.getYuklemetarihi());
					data.pst.setString(8, secim.getBosaltmayeri());
					data.pst.setDate(9, secim.getBosaltmatarihi());
					data.pst.executeUpdate();
					araclistele(event);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Silinmedi");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		Tableviewarac.setItems(arac.yeniarac);
		ara();
	}
}
