package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import fonksiyonlar.Alerts;
import fonksiyonlar.EkranAc;
import fonksiyonlar.Kontrol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import util.Database;

public class YoneticiKaydi implements Initializable {

	@FXML
	private JFXTextField kulad;

	@FXML
	private JFXTextField kulsoyad;

	@FXML
	private JFXTextField kullaniciadi;

	@FXML
	private JFXTextField sifre;

	@FXML
	private JFXTextField sifretekrar;

	@FXML
	private JFXTextField email;
	public static String sorgu = "", ad = "", eposta = "", sifre1 = "", sifre2, kuladd, kulsoyadd;
	ButtonType tm = new ButtonType("Tamam");
	EkranAc ekran = new EkranAc();
	Alerts alert = new Alerts();
	Kontrol kontrol=new Kontrol();
	Database data=new Database();

	@FXML
	void backgiris(MouseEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/KullaniciGirisi.fxml"));
		Main.stage.getScene().setRoot(root);
	}

	@FXML
	void closekayit(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void kaydol(ActionEvent event) throws SQLException {
		kuladd = kulad.getText();
		kulsoyadd = kulsoyad.getText();
		ad = kullaniciadi.getText();
		eposta = email.getText();
		sifre1 = sifre.getText();
		if (kullaniciadi.getText().trim().isEmpty() || email.getText().trim().isEmpty()
				|| sifre.getText().trim().isEmpty() || sifretekrar.getText().trim().isEmpty()) {
			alert.showAlertButton(AlertType.INFORMATION, "Bilgilendirme Ekranı", null,
					"Lutfen Butun Bilgilerinizi Giriniz", tm);
		} else {
			if ((kontrol.isimkontrol(kullaniciadi.getText()) & kontrol.emailkontrol(email.getText())) == false) {
			} else {
				if (sifre.getText().equals(sifretekrar.getText())) {
					sorgu = "insert into yonetici (ad,soyad,girisad,girissifre,email) values ('" + kuladd + "','"
							+ kulsoyadd + "','" + ad + "','" + sifre1 + "','" + eposta + "')";
					data.Vtb();
					data.st.executeUpdate(sorgu);
					alert.showAlertButton(AlertType.INFORMATION, "Bilgilendirme Ekranı", null,
							"Kaydiniz Olusturulmustur", tm);

				} else {
					alert.showAlertButton(AlertType.INFORMATION, "Bilgilendirme Ekranı", null,
							"Sifrelerinizi Ayni Giriniz", tm);
				}
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
