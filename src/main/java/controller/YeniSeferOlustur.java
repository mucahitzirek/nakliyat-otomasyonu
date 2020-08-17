package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.FirmabilgileriData;
import model.KullaniciData;
import model.SoforbilgileriData;
import util.Database;

public class YeniSeferOlustur implements Initializable {

	@FXML
	private AnchorPane yenisefer;

	@FXML
	private JFXComboBox<String> comboad;

	@FXML
	private JFXComboBox<String> combotel;

	@FXML
	private JFXTextField txtehlyet;

	@FXML
	private JFXTextField txtruhsat;

	@FXML
	private JFXTextField txthesap;

	@FXML
	private JFXTextField txtplakano1;

	@FXML
	private JFXComboBox<String> comboaraccins1;

	@FXML
	private JFXComboBox<String> combofirma;
	@FXML
	private JFXTextField txtyukcinsi1;

	@FXML
	private JFXTextField txtyuklemeyeri1;

	@FXML
	private JFXTextField txtbosaltmayeri1;

	@FXML
	private JFXTextField txtyukfiyat1;

	@FXML
	private JFXTextField txtyukmiktar1;

	@FXML
	private JFXTextField txtkomisyon1;

	@FXML
	private DatePicker dateyuklemetarih1;

	@FXML
	private HBox arachbox;

	@FXML
	private DatePicker datebosaltmatarih1;

	String adtelal = "";
	ObservableList<String> araccinsleri = FXCollections.observableArrayList("13.60 Duz", "Kisa", "Damperli", "Tenteli",
			"Tenteli Mega", "Tenteli Optima", "Tenteli Maxima", "Jumbo");
	String sorgu, sorgu2, sorgu3, sorgu4, tad, telefon, firma;
	int soforid, firmaid;
	FirmabilgileriData firmadat = new FirmabilgileriData();
	SoforbilgileriData sofor = new SoforbilgileriData();
	Database data = new Database();

	@FXML
	void soforkaytdet(ActionEvent event) {
		sofor.soforEkle(comboad.getValue(), combotel.getValue(), txtehlyet.getText(), txtruhsat.getText(),
				txthesap.getText());
		arachbox.getStyleClass().add("borderyak");
	}

	@FXML
	void soforadsec(ActionEvent event) {
		combotel.getItems().clear();
		adtelal = comboad.getSelectionModel().getSelectedItem().toString();
		sofor.soforadTelAl(adtelal);
	}

	@FXML
	void arackaytdet(ActionEvent event) throws SQLException {
		tad = comboad.getValue();
		telefon = combotel.getValue();
		firma = combofirma.getValue();
		sofor.soforidAl(tad, telefon);
		firmadat.firmaIdAl(firma);
		sorgu3 = "insert into arac (plaka,araccins,yukcins,yukmiktar,yukfiyat,yuklemeyeri,yuklemetarihi,bosaltmayeri,bosaltmatarihi,komisyon,soforid,firmaid,kullaniciid) values "
				+ "('" + txtplakano1.getText() + "','" + comboaraccins1.getValue() + "','" + txtyukcinsi1.getText()
				+ "'," + txtyukmiktar1.getText() + ",'" + txtyukfiyat1.getText() + "','" + txtyuklemeyeri1.getText()
				+ "','" + dateyuklemetarih1.getValue() + "','" + txtbosaltmayeri1.getText() + "','"
				+ datebosaltmatarih1.getValue() + "'," + txtkomisyon1.getText() + "," + SoforbilgileriData.soforidsi
				+ "," + FirmabilgileriData.firmaid + "," + KullaniciData.iddkullanici + ")";
		data.Vtb();
		data.st.executeUpdate(sorgu3);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboaraccins1.setItems(araccinsleri);
		sofor.soforadAl();
		firmadat.firmaAdAl();
		combofirma.setItems(firmadat.firmalar);
		comboad.setItems(sofor.soforadlari);
		TextFields.bindAutoCompletion(comboad.getEditor(), comboad.getItems());
		combotel.setItems(sofor.sofortelleri);
		TextFields.bindAutoCompletion(combotel.getEditor(), combotel.getItems());
		TextFields.bindAutoCompletion(comboaraccins1.getEditor(), comboaraccins1.getItems());
	}
}