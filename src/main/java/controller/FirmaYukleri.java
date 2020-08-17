package controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.FirmabilgileriData;

public class FirmaYukleri implements Initializable {

	@FXML
	private JFXComboBox<String> firmaAdlariCombo;

	@FXML
	private Label firmayuk;

	@FXML
	private Label firmagiden;

	@FXML
	private Label firmakalan;

	FirmabilgileriData firmadata = new FirmabilgileriData();
	String firmasecilenad;

	@FXML
	void firmabilgileriGetir(ActionEvent event) {
		firmasecilenad = firmaAdlariCombo.getValue();
		firmadata.firmayukAl(firmasecilenad);
		firmadata.firmagidenYuk(firmasecilenad);
		firmayuk.setText(String.valueOf(FirmabilgileriData.firmanetyuk));
		firmagiden.setText(String.valueOf(FirmabilgileriData.firmagidenyuk));
		firmakalan.setText(String.valueOf(FirmabilgileriData.firmanetyuk - FirmabilgileriData.firmagidenyuk));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firmadata.firmaAdAl();
		firmaAdlariCombo.setItems(firmadata.firmalar);
		TextFields.bindAutoCompletion(firmaAdlariCombo.getEditor(), firmaAdlariCombo.getItems());
	}
}