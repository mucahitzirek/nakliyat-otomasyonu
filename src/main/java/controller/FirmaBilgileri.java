package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import fonksiyonlar.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.FirmabilgileriData;
import util.Database;

public class FirmaBilgileri implements Initializable {

	@FXML
	private TextField aramatext;

	@FXML
	private FontAwesomeIcon ara;

	@FXML
	private JFXTextField ftxtfirmaadi;

	@FXML
	private JFXTextField ftxtfirmasahibi;

	@FXML
	private JFXTextField ftxtfirmaadresi;

	@FXML
	private JFXTextField ftxtfirmayuk;

	@FXML
	private JFXTextField ftxtfirmatelefon;

	@FXML
	private JFXTextField ftxtfirmaemail;

	@FXML
	private JFXTextField ftxtfirmafax;

	@FXML
	private TableView<FirmabilgileriData> TableviewFirma;

	@FXML
	private TableColumn<FirmabilgileriData, String> tbwfirmaadi;

	@FXML
	private TableColumn<FirmabilgileriData, String> tbwfirmasahibi;

	@FXML
	private TableColumn<FirmabilgileriData, String> tbwfirmaadresi;

	@FXML
	private TableColumn<FirmabilgileriData, String> tbwfirmayuk;

	@FXML
	private TableColumn<FirmabilgileriData, String> tbwfirmatelefon;

	@FXML
	private TableColumn<FirmabilgileriData, String> tbwfirmaemail;

	@FXML
	private TableColumn<FirmabilgileriData, String> tbwfirmafax;

	String sorgu,adtik="";
	Alerts alert = new Alerts();
	FirmabilgileriData firma = new FirmabilgileriData();
	Database data = new Database();

	@FXML
	void arabtn(MouseEvent event) {
		TableviewFirma.getItems().clear();
		String isim;
		isim = aramatext.getText();
		sorgu = "select *from firma where ad like'%" + isim + "%'";
		// ResultSet rs = Database.listele(sorgu);
		// try {
		// while (rs.next()) {
		// ad = rs.getString("adi");
		// adres = rs.getString("adres");
		// yuk = rs.getInt("yuk");
		// telno = rs.getString("tel");
		// email = rs.getString("email");
		// fax = rs.getString("fax");
		// FirmabilgileriData ara = new FirmabilgileriData(ad, adres, yuk,
		// telno, email, fax);
		// TableviewFirma.getItems().add(ara);
		// }
		// } catch (SQLException e) {
		// System.out.println("arabtun :" + e.getMessage());
		// }
	}

	@FXML
	public void firmalistelebtn(ActionEvent event) {
		TableviewFirma.getItems().clear();
		firma.listeleFirma();
		TableviewFirma.setItems(firma.firmaliste);
	}

	@FXML
	void tableFirmasec(MouseEvent event) {
		FirmabilgileriData secim = TableviewFirma.getSelectionModel().getSelectedItem();
		if (secim == null) {
		} else {
			ftxtfirmaadi.setText(secim.getAd());
			ftxtfirmayuk.setText(String.valueOf(secim.getYuk()));
			ftxtfirmaadresi.setText(secim.getAdres());
			ftxtfirmatelefon.setText(secim.getTel());
			ftxtfirmaemail.setText(secim.getEmail());
			ftxtfirmafax.setText(secim.getFax());
		}
	}

	@FXML
	void firmaeklebtn(ActionEvent event) {
		try {
			firma.ekleFirma(ftxtfirmaadi.getText(), ftxtfirmaadresi.getText(), Integer.parseInt(ftxtfirmayuk.getText()),ftxtfirmatelefon.getText(), ftxtfirmaemail.getText(), ftxtfirmafax.getText());
			firmalistelebtn(event);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@FXML
	void firmasilbtn(ActionEvent event) {
		FirmabilgileriData secim = TableviewFirma.getSelectionModel().getSelectedItem();
		ButtonType sil = new ButtonType("Sil");
		ButtonType silme = new ButtonType("Silme");
		ButtonType cik = new ButtonType("Cik");
		if ((secim == null)) {
			alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Lutfen bir kayit seciniz",
					cik);
		} else {
			Optional<ButtonType> secilen = alert.showAlertButtonSil(AlertType.INFORMATION, "Bilgilendirme Ekrani", null,
					"Silmek istediginize emin misiniz?", sil, silme);
			if (secilen.get() == sil) {
				try {
					sorgu = "Delete from firma where ad=? and adres=? and yuk=? and tel=? and email=? and fax=?";
					data.pst = data.Vtb().prepareStatement(sorgu);
					data.pst.setString(1, secim.getAd());
					data.pst.setString(2, secim.getAdres());
					data.pst.setInt(3, secim.getYuk());
					data.pst.setString(4, secim.getTel());
					data.pst.setString(5, secim.getEmail());
					data.pst.setString(6, secim.getFax());
					data.pst.executeUpdate();
					firmalistelebtn(event);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("Silinmedi");
			}
		}
	}

	@FXML
	void firmaguncellebtn(ActionEvent event) {
		try {
			FirmabilgileriData secim=TableviewFirma.getSelectionModel().getSelectedItem();
			adtik=secim.getAd();
			firma.firmaIdAl(adtik);
			sorgu = "UPDATE firma set ad=?,adres=?,yuk=?,tel=?,email=?,fax=? where idfirma=?";
			data.pst = data.Vtb().prepareStatement(sorgu);
			data.pst.setString(1, ftxtfirmaadi.getText());
			data.pst.setString(2, ftxtfirmaadresi.getText());
			data.pst.setInt(3, Integer.parseInt(ftxtfirmayuk.getText()));
			data.pst.setString(4, ftxtfirmatelefon.getText());
			data.pst.setString(5, ftxtfirmaemail.getText());
			data.pst.setString(6, ftxtfirmafax.getText());
			data.pst.setInt(7, FirmabilgileriData.firmaid);
			data.pst.executeUpdate();
			firmalistelebtn(event);
		} catch (SQLException e) {
			System.out.println("Guncellenmedi" + e.getMessage());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tbwfirmaadi.setCellValueFactory(new PropertyValueFactory<>("ad"));
		tbwfirmaadresi.setCellValueFactory(new PropertyValueFactory<>("adres"));
		tbwfirmayuk.setCellValueFactory(new PropertyValueFactory<>("yuk"));
		tbwfirmatelefon.setCellValueFactory(new PropertyValueFactory<>("tel"));
		tbwfirmaemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tbwfirmafax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		TableviewFirma.setItems(firma.firmaliste);
	}

}
