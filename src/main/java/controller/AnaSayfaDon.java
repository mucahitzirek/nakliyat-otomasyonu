package controller;

import java.net.URL;
import java.util.ResourceBundle;
import fonksiyonlar.Pieler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class AnaSayfaDon implements Initializable{

    @FXML
    private VBox anadonvbox;

    @FXML
    private PieChart pie;
    Pieler piegoster=new  Pieler();
    @FXML
    void pieclick(MouseEvent event) {
    	piegoster.netKazanc(pie,2);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		piegoster.netKazanc(pie,2);
	}
}
