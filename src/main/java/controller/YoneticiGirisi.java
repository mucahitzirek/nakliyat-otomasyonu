package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import fonksiyonlar.Alerts;
import fonksiyonlar.DosyaYol;
import fonksiyonlar.EkranAc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import util.Database;

public class YoneticiGirisi implements Initializable {
	@FXML
	private JFXTextField txtad;
	@FXML
	private JFXTextField txtsifre;
	public static String adyon, soyadyon;
	public static int idyonetici;
	String ad, sifre, sorgu, sorguyon;

	Alerts alert = new Alerts();
	EkranAc ekran = new EkranAc();
	Database data = new Database();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void backgiris(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/KullaniciGirisi.fxml"));
		Main.stage.getScene().setRoot(root);
	}

	@FXML
	void closekayit(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void giris(ActionEvent event) {
		data.Vtb();
		if (Giriss().equals("basarili")) {
			ad = txtad.getText();
			sifre = txtsifre.getText();
			sorguyon = "select idyonetici,ad,soyad from yonetici where girisad='" + ad + "'and girissifre='" + sifre
					+ "';";
			try {
				data.rs = data.st.executeQuery(sorguyon);
				while (data.rs.next()) {
					adyon = data.rs.getString("ad");
					soyadyon = data.rs.getString("soyad");
					idyonetici = data.rs.getInt("idyonetici");
				}
			} catch (SQLException e) {
				System.out.println("kullaniciid alinamadi : " + e.getMessage());
			}
			ekran.yeniEkranUrlAcBuyuyen(DosyaYol.yoneticiSayfasi, "Yonetici Sayfasi");
			if (AnaSayfa.stage == null) {
				Main.stage.close();
			} else {
				AnaSayfa.stage.close();
			}
		} else {
			ButtonType tm = new ButtonType("Tamam");
			alert.showAlertButton(AlertType.WARNING, "Bilgilendirme Ekranı", null,
					"Lutfen Kullanici Adinizi Ve Sifrenizi Kontrol Ediniz", tm);
		}
	}

	public String Giriss() {
		ad = txtad.getText().toString();
		sifre = txtsifre.getText().toString();
		sorgu = "select * from yonetici WHERE girisad= ? and girissifre= ?";
		try {
			data.pst = data.Vtb().prepareStatement(sorgu);
			data.pst.setString(1, ad);
			data.pst.setString(2, sifre);
			data.rs =data.pst.executeQuery();
			if (!data.rs.next()) {
				return "hatali";
			} else {
				return "basarili";
			}
		} catch (Exception e) {
			System.out.println("alinamadi : " + e.getMessage());
			return "Baglanilamadi";
		}
	}

}
