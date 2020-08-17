package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.gmailapi.GmailApi;
import com.gmailapi.GmailMesajAyarlari;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import fonksiyonlar.Alerts;
import fonksiyonlar.Kontrol;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MimeMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.KullaniciData;

public class KullaniciKaydi implements Initializable {

	@FXML
	private JFXTextField kullaniciadi;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField sifre;
	@FXML
	private JFXTextField kulad;
	@FXML
	private JFXTextField kulsoyad;
	@FXML
	private JFXComboBox<String> comboyonetici;
	@FXML
	private JFXTextField sifretekrar;
	public static String sorgu = "", ad = "", eposta = "", sifre1 = "", sifre2, kuladd, kulsoyadd, epostayon;
	ButtonType tm = new ButtonType("Tamam");
	int idyonetici;
	Kontrol kontrol = new Kontrol();
	KullaniciData kuldata = new KullaniciData();
	Alerts alert = new Alerts();

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
	void kaydol(ActionEvent event) {
		String idyonet = comboyonetici.getSelectionModel().getSelectedItem().toString();
		kuldata.yoneticiMailIdAl(idyonet);
		kuladd = kulad.getText();
		kulsoyadd = kulsoyad.getText();
		ad = kullaniciadi.getText();
		eposta = email.getText();
		sifre1 = sifre.getText();
		if (kullaniciadi.getText().trim().isEmpty() || email.getText().trim().isEmpty()
				|| sifre.getText().trim().isEmpty() || sifretekrar.getText().trim().isEmpty()) {
			alert.showAlertButton(AlertType.INFORMATION, "Bilgilendirme Ekranı", null,"Lutfen Butun Bilgilerinizi Giriniz", tm);
		} else {
			if (kontrol.isimkontrol(kullaniciadi.getText()) & kontrol.emailkontrol(email.getText()) == false) {
			} else {
				if (sifre.getText().equals(sifretekrar.getText())) {
						kuldata.kullaniciEkle(kulad.getText(), kulsoyad.getText(), kullaniciadi.getText(), sifre.getText(), email.getText(), KullaniciData.yoneticiId);
					try {
						Gmail service = GmailApi.getGmailService();
						GmailMesajAyarlari gmail = new GmailMesajAyarlari();
						@SuppressWarnings("static-access")
						MimeMessage Mimemessage = gmail.createEmail(epostayon, "me",
								"NAKLİYAT OTOMASYONUNUZDA YENİ KAYIT OLUŞTURULDU", "Kullanıcı Adı: " + ad
										+ "\n\nKullanıcı Eposta:" + eposta + "\n\nKullanıcı Şifresi: " + sifre1);
						@SuppressWarnings("static-access")
						Message message = gmail.createMessageWithEmail(Mimemessage);
						message = service.users().messages().send("me", message).execute();
					} catch (Exception ex) {
						Logger.getLogger(KullaniciKaydi.class.getName()).log(Level.SEVERE, null, ex);
					}
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Kaydiniz Olusturulmustur", tm);
					alert2.setHeaderText(null);
					alert2.showAndWait();
				} else {
					Alert alert3 = new Alert(Alert.AlertType.ERROR, "Sifrelerinizi Ayni Giriniz", tm);
					alert3.setHeaderText(null);
					alert3.showAndWait();
					System.out.println("Şifreler aynı değil");
				}
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboyonetici.setItems(kuldata.yoneticiler);
	}

}
