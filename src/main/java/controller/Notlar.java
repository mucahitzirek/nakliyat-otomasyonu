package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import fonksiyonlar.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.KullaniciData;
import model.NotlarData;
import util.Database;

public class Notlar implements Initializable {

	@FXML
	private JFXListView<NotlarData> listnotad;
	@FXML
	private JFXTextArea textnotdetay;
	@FXML
	private Label nottarihi;
	String sorgu;
	NotlarData basliksec;
	Alerts alert=new Alerts();
	NotlarData not=new NotlarData();
	Database data=new Database();
	@FXML
	void notbasliksec(MouseEvent event) {
		basliksec = listnotad.getSelectionModel().getSelectedItem();
		if (!(basliksec == null)) {
			textnotdetay.setText(basliksec.getDetay());
			nottarihi.setText(basliksec.getTarih());
		}
	}

	@FXML
	void notlarilistele(ActionEvent event) {
		listnotad.getItems().clear();
		textnotdetay.clear();
		not.notListele(KullaniciData.iddkullanici);
		listnotad.setItems(not.notliste);
	}

	@FXML
	void notkaydet(ActionEvent event) {

	}


	@FXML
	void notsil(ActionEvent event) {
		ButtonType sil = new ButtonType("Sil");
		ButtonType silme = new ButtonType("Silme");
		sorgu = " Delete from notlar where idnot=?";
		basliksec = listnotad.getSelectionModel().getSelectedItem();
		if ((basliksec == null)) {
			ButtonType cik=new ButtonType("Cik");
			alert.showAlertButton(AlertType.CONFIRMATION, "Bilgilendirme Ekranı", null, "Lutfen bir kayit seciniz", cik);
		}else {
			Optional<ButtonType> secilen= alert.showAlertButtonSil(AlertType.INFORMATION, "Bilgilendirme Ekrani", null, "Silmek istediginize emin misiniz?", sil, silme);
			if (secilen.get() == sil) {
				try {
					data.pst = data.Vtb().prepareStatement(sorgu);
					data.pst.setInt(1, basliksec.getIdnot());
					data.pst.executeUpdate();
					notlarilistele(event);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			} else {System.out.println("Silinmedi");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
