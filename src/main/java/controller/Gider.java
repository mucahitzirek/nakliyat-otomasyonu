package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import fonksiyonlar.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GiderData;
import model.KullaniciData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.Database;

public class Gider implements Initializable {
	@FXML
	private JFXTextField txtgider;

	@FXML
	private TableView<GiderData> tableviewgider;

	@FXML
	private JFXTextArea aciklama;

	@FXML
	private TableColumn<GiderData, String> tbwaciklama;

	@FXML
	private TableColumn<GiderData, String> tbwgider;

	@FXML
	private TableColumn<GiderData, String> tbwtarih;

	LocalDate dat = LocalDate.now();
	GiderData giderdat = new GiderData();
	Alerts alert = new Alerts();
	Database data = new Database();

	@FXML
	void gider(ActionEvent event) {
		giderdat.giderEkle(aciklama.getText(), txtgider.getText(), dat, KullaniciData.iddkullanici);
		ButtonType tm = new ButtonType("Tamam");
		alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Gider Eklendi", tm);

	}

	@FXML
	void giderlistele(ActionEvent event) {
		tableviewgider.getItems().clear();
		giderdat.giderListele(KullaniciData.iddkullanici);
	}

	@FXML
	void gidersil(ActionEvent event) {
		GiderData secim = tableviewgider.getSelectionModel().getSelectedItem();
		ButtonType sil = new ButtonType("Sil");
		ButtonType silme = new ButtonType("Silme");
		ButtonType cik = new ButtonType("cik");
		if ((secim == null)) {
			alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Lutfen bir kayit seciniz",
					cik);
		} else {
			Optional<ButtonType> secilen = alert.showAlertButtonSil(AlertType.INFORMATION, "Bilgilendirme Ekrani", null,
					"Silmek istediginize emin misiniz?", sil, silme);
			if (secilen.get() == sil) {
				String ad = secim.getAciklama();
				giderdat.giderId(ad);
				giderdat.giderSil();
				giderlistele(event);
			} else {
				System.out.println("Silinmedi");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tbwaciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
		tbwgider.setCellValueFactory(new PropertyValueFactory<>("gider"));
		tbwtarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
		tableviewgider.setItems(giderdat.giderliste);

	}

}
