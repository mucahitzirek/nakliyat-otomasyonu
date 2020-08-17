package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDatePicker;
import fonksiyonlar.Alerts;
import fonksiyonlar.ExcelYedegi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AracBilgileriData;
import model.KullaniciData;
import model.SoforbilgileriData;
import util.Database;

public class AracinSoforu implements Initializable{

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
    private Label soforadi;

    @FXML
    private JFXDatePicker date1;

    @FXML
    private JFXDatePicker date2;

	ExcelYedegi ExelKayit = new ExcelYedegi();
	Alerts alert=new Alerts();
	AracBilgileriData arac = new AracBilgileriData();
	Database data = new Database();

    @FXML
	void exeleAktar(ActionEvent event) throws SQLException {
		ExelKayit.exelYedegi(KullaniciData.iddkullanici);
		ButtonType tm = new ButtonType("Tamam");
		alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Kullaniciya ait isler exele aktarildi.\nYeni yedek olusturmadan lütfen eski dosyayı saklayınız !", tm);
	}

    @FXML
    void tarihlistele(ActionEvent event) {
    	Tableviewarac.getItems().clear();
    	arac.aracınsofortarihListele(KullaniciData.iddkullanici, SoforbilgileriData.soforidsi, date1.getValue(), date2.getValue());
		Tableviewarac.setItems(arac.yeniarac);
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {
		soforadi.setText("Sofor Adi: "+ SoforBilgileri.adtik +"\tEhliyetNo: "+SoforBilgileri.ehliyettik
						+"\tRuhsatNo: "+ SoforBilgileri.ruhsattik+"\tHesapNo: "+SoforBilgileri.hesapnotik);
		Tableviewarac.getItems().clear();
		arac.aracsoforListele(KullaniciData.iddkullanici, SoforbilgileriData.soforidsi);
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
    }
}
