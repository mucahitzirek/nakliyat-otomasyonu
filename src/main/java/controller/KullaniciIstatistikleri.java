package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.GiderData;
import model.YoneticininKullanicilari;

public class KullaniciIstatistikleri implements Initializable {

	@FXML
	private Label gunlukis;

	@FXML
	private Label gunlukgider;

	@FXML
	private Label gunlukkazanc;

	@FXML
	private Label aylikis;

	@FXML
	private Label aylikgider;

	@FXML
	private Label aylikkazanc;

	@FXML
	private JFXComboBox<String> combokullanicilar;

	@FXML
	private PieChart pie;

	@FXML
	private TableView<GiderData> tableviewkullanici;

	@FXML
	private TableColumn<GiderData, String> tbwaciklama;

	@FXML
	private TableColumn<GiderData, Integer> tbwgider;

	@FXML
	private TableColumn<GiderData, Date> tbwtarih;

	YoneticininKullanicilari yonetkul = new YoneticininKullanicilari();
	String secilenad;
	LocalDate dat = LocalDate.now();
	int  netkazanc;
	LocalDate dat2 = dat.minusMonths(1);

	@FXML
	void Goruntule(ActionEvent event) throws SQLException {
		secilenad = combokullanicilar.getValue();
		// -------------------------------- // --------------------------------
		yonetkul.gunlukSayi(dat, secilenad);
		gunlukis.setText(String.valueOf(yonetkul.gunlukissayi));
		// -------------------------------- // --------------------------------
		yonetkul.gunlukKazanc(dat, secilenad);
		gunlukkazanc.setText(String.valueOf(yonetkul.gunlukkazanctoplam));
		// -------------------------------- // --------------------------------
		yonetkul.aylikIs(dat2, dat, secilenad);
		aylikis.setText(String.valueOf(yonetkul.ayliksayi));
		// -------------------------------- // --------------------------------
		yonetkul.aylikKazanc(dat2, dat, secilenad);
		aylikkazanc.setText(String.valueOf(yonetkul.aylikkazanilan));
		// -------------------------------- // --------------------------------
		yonetkul.gunlukGider(dat, secilenad);
		gunlukgider.setText(String.valueOf(yonetkul.gungider));
		// -------------------------------- // --------------------------------
		yonetkul.aylikGider(dat2, dat, secilenad);
		aylikgider.setText(String.valueOf(yonetkul.aygider));
		// -------------------------------- // --------------------------------
		tableviewkullanici.getItems().clear();
		tableviewkullanici.setItems(yonetkul.giderler);
		// -------------------------------- // --------------------------------
		yonetkul.giderTablo(secilenad);
		// -------------------------------- // --------------------------------
		yonetkul.netGider(secilenad);
		yonetkul.netGelir(secilenad);
		netkazanc = yonetkul.netgelir - yonetkul.netgider;
		ObservableList<PieChart.Data> pielist = FXCollections.observableArrayList(new PieChart.Data("Gelir", yonetkul.netgelir),
				new PieChart.Data("Gider", yonetkul.netgider), new PieChart.Data("NetKazanc", netkazanc));
		pie.setData(pielist);
		System.out.println(netkazanc + "    +   " + yonetkul.netgelir + "   +   "   +  "  +    "  + yonetkul.netgider);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		yonetkul.kullaniciAdlari(YoneticiGirisi.idyonetici);
		combokullanicilar.setItems(yonetkul.kullaniciadlari);
		tbwaciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
		tbwgider.setCellValueFactory(new PropertyValueFactory<>("gider"));
		tbwtarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));

	}
}
