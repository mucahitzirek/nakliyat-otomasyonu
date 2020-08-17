package fonksiyonlar;


import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

    public void showAlert(AlertType type, String title, String header, String text){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
    public void showAlertButton(AlertType type, String title, String header, String text,ButtonType button){
        Alert alert = new Alert(type,text,button);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();
    }
    public Optional<ButtonType> showAlertButtonSil(AlertType type, String title, String header, String text,ButtonType sil, ButtonType silme){
        Alert alert = new Alert(type,text,sil,silme);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        return alert.showAndWait();
    }
}
