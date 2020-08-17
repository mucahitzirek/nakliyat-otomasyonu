package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import fonksiyonlar.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import model.KullaniciData;
import model.NotlarData;

public class NotEkle implements Initializable {

	@FXML
	private TextArea YeninotDetay;

	@FXML
	private JFXTextField YeninotBaslik;
	LocalDate loc;
	Alerts alert = new Alerts();
	NotlarData not=new NotlarData();
	@FXML
	void YeninotKaydetbtn(ActionEvent event) {
		ButtonType tm = new ButtonType("Tamam");
		alert.showAlertButton(AlertType.INFORMATION, "Bilgilendirme Ekranı", null, "Yeni Not Kaydedildi !", tm);
		loc = LocalDate.now();
		not.notEkle(YeninotBaslik.getText(), YeninotDetay.getText(), loc, KullaniciData.iddkullanici);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
