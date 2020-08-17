package controller;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import com.gmailapi.GmailApi;
import com.gmailapi.GmailMesajAyarlari;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import fonksiyonlar.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import model.KullaniciData;
import javafx.scene.control.Alert.AlertType;
import util.Database;

public class SifreHatirlatma implements Initializable {
	@FXML
	private JFXComboBox<String> yoneticiadlari;
	@FXML
	private JFXTextField email;
	String sorgu, ad2, sifre2, email2;
	Alerts alert=new Alerts();
	KullaniciData kuldat=new KullaniciData();
	Database data=new Database();

	@FXML
	void tamam(ActionEvent event) throws SQLException, MessagingException, IOException, GeneralSecurityException {
		ButtonType tm = new ButtonType("Tamam");
		if ( email.getText().trim().isEmpty()) {
			alert.showAlertButton(AlertType.INFORMATION, "Bilgilendirme Ekranı", null, "Lutfen Emailinizi Giriniz.!", tm);
		} else {
			String idyonet = yoneticiadlari.getValue();
			kuldat.yoneticiMailIdAl(idyonet);
			data.Vtb();
			sorgu = "Select * from kullanici where kullaniciemail='" + email.getText() + "';";
			data.rs=data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				ad2 = data.rs.getString(4);
				sifre2 = data.rs.getString(5);
				email2 = data.rs.getString(6);
			}
			Gmail service = GmailApi.getGmailService();
			GmailMesajAyarlari gmail = new GmailMesajAyarlari();
			@SuppressWarnings("static-access")
			MimeMessage Mimemessage = gmail.createEmail(KullaniciData.yoneticiMail, "me",
					"GİRİŞ BİLGİLERİNİ UNUTAN ÇALIŞANINIZIN BİLGİLERİ",
					"Kullanıcı Adı: " + ad2 + "\n\nKullanıcı Şifresi: " + sifre2 + "\n\nKullanıcı Eposta: " + email2);
			@SuppressWarnings("static-access")
			Message message = gmail.createMessageWithEmail(Mimemessage);
			message = service.users().messages().send("me", message).execute();
			alert.showAlert(AlertType.INFORMATION, "Bilgilendirme Ekranı", null, "Yöneticiniz ile Görüsünüz.");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kuldat.yoneticiAdlariAl();
		yoneticiadlari.setItems(kuldat.yoneticiler);
	}
}
