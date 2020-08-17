package fonksiyonlar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EkranAc {

	public URL url;

	public void yeniEkranici(Pane pane, String dosyaYol) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource(dosyaYol));
			pane.getChildren().removeAll();
			pane.getChildren().setAll(fxml);
		} catch (IOException e) {
			System.out.println("Ekran Gelmedi : " + e.getMessage());
		}
	}

	public void yeniEkranUrlAc(String dosyaYol, String stageTitle) {
		try {
			URL url = new File(dosyaYol).toURI().toURL();
			Parent fxml = FXMLLoader.load(url);
			Stage stage = new Stage();
			Scene scene = new Scene(fxml);
			stage.setTitle(stageTitle);
			stage.sizeToScene();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.out.println("Stabil Ekran Acılmadı : " + e.getMessage());
		}
	}

	public void yeniEkranUrlAcBuyuyen(String dosyaYol, String stageBaslik) {
		try {
			URL url = new File(dosyaYol).toURI().toURL();
			Parent fxml = FXMLLoader.load(url);
			Stage stage = new Stage();
			Scene scene = new Scene(fxml, 1200, 700);
			stage.setTitle(stageBaslik);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			System.out.println("Ekran Acilmadi : " + e.getMessage());
		}
	}
}
