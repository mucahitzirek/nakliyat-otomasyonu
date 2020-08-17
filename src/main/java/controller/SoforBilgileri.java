package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import fonksiyonlar.Alerts;
import fonksiyonlar.DosyaYol;
import fonksiyonlar.EkranAc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.SoforbilgileriData;
import util.Database;

public class SoforBilgileri implements Initializable {

	@FXML
	private TableView<SoforbilgileriData> tableviewsofor;

	@FXML
	private TableColumn<SoforbilgileriData, String> tbwsoforadi;

	@FXML
	private TableColumn<SoforbilgileriData, String> tbwsofortelefon;

	@FXML
	private TableColumn<SoforbilgileriData, String> tbwsoforehliyetno;

	@FXML
	private TableColumn<SoforbilgileriData, String> tbwsoforruhsatno;

	@FXML
	private TableColumn<SoforbilgileriData, String> tbwsoforhesapno;
	@FXML
	private JFXTextField txtad;

	@FXML
	private JFXTextField txttel;

	@FXML
	private JFXTextField txtehliyet;

	@FXML
	private JFXTextField txtruhsat;

	@FXML
	private JFXTextField txthesap;

	@FXML
	private TextField aramatext;

	@FXML
	private FontAwesomeIcon ara;
	public static ObservableList<String> yenisofor2 = FXCollections.observableArrayList();
	public static String sorgu, adtik = "", teltik = "", ehliyettik = "", ruhsattik = "", hesapnotik = "";
	EkranAc ekran = new EkranAc();
	Alerts alert = new Alerts();
	SoforbilgileriData sofor = new SoforbilgileriData();
	Database data = new Database();
	@FXML
	void listelebtn(ActionEvent event) {
		clearr();
		tableviewsofor.getItems().clear();
		sofor.soforListele();
		tableviewsofor.setItems(sofor.yenisofor);
	}

	@FXML
	void tableviewsec(MouseEvent event) {
		SoforbilgileriData secim = tableviewsofor.getSelectionModel().getSelectedItem();
		if (secim == null) {
		} else {
			txtad.setText(secim.getAd());
			txttel.setText(secim.getTel());
			txtehliyet.setText(secim.getEhliyetno());
			txtruhsat.setText(secim.getRuhsatno());
			txthesap.setText(secim.getHesapno());
		}
	}

	@FXML
	void ekle(ActionEvent event) {
		sofor.soforEkle(txtad.getText(), txttel.getText(), txtehliyet.getText(), txtruhsat.getText(),
				txthesap.getText());
		listelebtn(event);
	}

	@FXML
	void yukbilgileri(ActionEvent event) {
		SoforbilgileriData secim = tableviewsofor.getSelectionModel().getSelectedItem();
		if (secim == null) {
		} else {
			adtik = secim.getAd();
			teltik = secim.getTel();
			ehliyettik = secim.getEhliyetno();
			ruhsattik = secim.getRuhsatno();
			hesapnotik = secim.getHesapno();
			sofor.soforidAl(adtik, teltik);
			ekran.yeniEkranUrlAcBuyuyen(DosyaYol.aracinSoforu, "Soforun Gittigi Seferler");
		}
	}

	@FXML
	void guncelle(ActionEvent event) {
		SoforbilgileriData secim = tableviewsofor.getSelectionModel().getSelectedItem();
		try {
			adtik = secim.getAd();
			teltik = secim.getTel();
			sofor.soforidAl(adtik, teltik);
			sorgu = "UPDATE sofor set ad=?,tel=?,ehliyetno=?,ruhsatno=?,hesapno=? where idsofor=?";
			data.pst = data.Vtb().prepareStatement(sorgu);
			data.pst.setString(1, txtad.getText());
			data.pst.setString(2, txttel.getText());
			data.pst.setString(3, txtehliyet.getText());
			data.pst.setString(4, txtruhsat.getText());
			data.pst.setString(5, txthesap.getText());
			data.pst.setInt(6, SoforbilgileriData.soforidsi);
			data.pst.executeUpdate();
			listelebtn(event);
		} catch (Exception e) {
			System.out.println("Guncellenmedi" + e.getMessage());
		}
	}

	@FXML
	void sil(ActionEvent event) {
		SoforbilgileriData secim = tableviewsofor.getSelectionModel().getSelectedItem();
		ButtonType sil = new ButtonType("Sil");
		ButtonType silme = new ButtonType("Silme");
		if ((secim == null)) {
			ButtonType cik = new ButtonType("cik");
			alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Lutfen bir kayit seciniz",
					cik);
		} else {
			Optional<ButtonType> secilen = alert.showAlertButtonSil(AlertType.INFORMATION, "Bilgilendirme Ekrani", null,
					"Silmek istediginize emin misiniz?", sil, silme);
			if (secilen.get() == sil) {
				try {
					sorgu = "Delete  from sofor where ad=? and tel=? and ehliyetno=? and ruhsatno=? and hesapno=?";
					data.pst = data.Vtb().prepareStatement(sorgu);
					data.pst.setString(1, secim.getAd());
					data.pst.setString(2, secim.getTel());
					data.pst.setString(3, secim.getEhliyetno());
					data.pst.setString(4, secim.getRuhsatno());
					data.pst.setString(5, secim.getHesapno());
					data.pst.executeUpdate();
					listelebtn(event);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Silinmedi");
			}
		}
	}

	@FXML
	void arabtn(MouseEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbwsoforadi.setCellValueFactory(new PropertyValueFactory<>("ad"));
		tbwsofortelefon.setCellValueFactory(new PropertyValueFactory<>("tel"));
		tbwsoforehliyetno.setCellValueFactory(new PropertyValueFactory<>("ehliyetno"));
		tbwsoforruhsatno.setCellValueFactory(new PropertyValueFactory<>("ruhsatno"));
		tbwsoforhesapno.setCellValueFactory(new PropertyValueFactory<>("hesapno"));
		tableviewsofor.setItems(sofor.yenisofor);

		FilteredList<SoforbilgileriData> filter = new FilteredList<>(sofor.yenisofor, b -> true);
		aramatext.textProperty().addListener((observable, eski, yeni) -> {
			filter.setPredicate(araci -> {
				if (yeni == null || yeni.isEmpty()) {
					return true;
				}
				String filtrem = yeni.toLowerCase();
				if (araci.getAd().toLowerCase().indexOf(filtrem) != -1) {
					return true;
				} else if (String.valueOf(araci.getTel()).toLowerCase().indexOf(filtrem) != -1) {
					return true;
				} else
					return false;
			});
		});
		SortedList<SoforbilgileriData> sort = new SortedList<>(filter);
		sort.comparatorProperty().bind(tableviewsofor.comparatorProperty());
		tableviewsofor.setItems(sort);

	}

	public void clearr() {
		txtad.setText(null);
		txttel.setText(null);
		txtehliyet.setText(null);
		txtruhsat.setText(null);
		txthesap.setText(null);
	}
}
