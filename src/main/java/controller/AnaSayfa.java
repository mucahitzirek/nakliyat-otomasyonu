package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import fonksiyonlar.DosyaYol;
import fonksiyonlar.EkranAc;
import fonksiyonlar.Pieler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.KullaniciData;

public class AnaSayfa implements Initializable {
	public static Stage stage = null;
	@FXML
	private Label kullaniciadi;
	@FXML
	private VBox anaekranvbox;
	@FXML
	private PieChart pie;
	private URL url;
	EkranAc ekran=new EkranAc();
	Pieler pieac=new Pieler();

	@FXML
	void soforbilgigit(MouseEvent event) {
		ekran.yeniEkranici(anaekranvbox, DosyaYol.soforBilgileri);
	}

	@FXML
	void aracgit(MouseEvent event) {
	ekran.yeniEkranici(anaekranvbox, DosyaYol.aracBilgileri);
	}

    @FXML
    void haritagit(MouseEvent event) {
		ekran.yeniEkranUrlAcBuyuyen(DosyaYol.harita, "Harita");
    }

	@FXML
	void pieclick(MouseEvent event) {
		pieac.netKazanc(pie,2);
	}

	@FXML
	void YeninotbtnAna(ActionEvent event) {
		ekran.yeniEkranUrlAc(DosyaYol.notEkle,"Not Ekle");
	}

	@FXML
	void anamenugit(MouseEvent event) {
		ekran.yeniEkranici(anaekranvbox, DosyaYol.anasaydaDon);
	}

	@FXML
	void islemler(MouseEvent event) {
	ekran.yeniEkranUrlAcBuyuyen(DosyaYol.hesaplar, "Hesaplar");
	}

	@FXML
	void firmabilgigit(MouseEvent event) {
	ekran.yeniEkranici(anaekranvbox, DosyaYol.firmaBilgileri);

	}

	@FXML
	void hesapgit(ActionEvent event) {
		ekran.yeniEkranUrlAc(DosyaYol.hesapMakinesi, "Hesap Makinesi");
	}

	@FXML
	void notlarimgit(MouseEvent event) {
		ekran.yeniEkranici(anaekranvbox, DosyaYol.notlar);
	}

	@FXML
	void yeniseferolustur(MouseEvent event) {
		ekran.yeniEkranUrlAc(DosyaYol.yeniseferOlustur,"Yeni Sefer Olustur");
	}


	@SuppressWarnings("static-access")
	@FXML
	void yoneticigit(MouseEvent event) {
		try {
			url = new File("src/main/java/view/YoneticiGirisi.fxml").toURI().toURL();
			Parent fxml = FXMLLoader.load(url);
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(fxml);
			stage.sizeToScene();
			stage.setResizable(false);
			stage.setTitle("Yonetici Giris Ekrani");
			stage.setScene(scene);
			this.stage = stage;
			stage.show();
		} catch (IOException e) {
			System.out.println("Yonetici Girisi :" + e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		kullaniciadi.setText(KullaniciData.add+ " " +KullaniciData.soyadd);
	}
}
