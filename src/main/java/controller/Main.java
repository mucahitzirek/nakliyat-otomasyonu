package controller;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	public static Stage stage = null;

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/KullaniciGirisi.fxml"));
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			this.stage = primaryStage;
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("giris " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		launch(args);
	}

}
