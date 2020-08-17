package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Harita implements Initializable{
    @FXML
    private WebView webview;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File file =new File("GmapsHtml.html");
		WebEngine engine=webview.getEngine();
		try {
			engine.load(file.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
