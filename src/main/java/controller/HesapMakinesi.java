package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HesapMakinesi implements Initializable {
	@FXML
	private TextField txthesap;
	private int decimal = 0;
	private String islemler;
	private double ilksayı;
	String sayı;
	String öncekisayi;
	String yenisayi;

	@FXML
	void islemler(ActionEvent event) {
		islemler = ((Button) event.getSource()).getText();
		switch (islemler) {
		case "AC":
			txthesap.setText("");
			decimal = 0;
			break;
		case "+/-":
			double isaret = Double.parseDouble(String.valueOf(txthesap.getText()));
			isaret = isaret * (-1);
			txthesap.setText(String.valueOf(isaret));
			break;
		case "+":
		case "-":
		case "*":
		case "/":
		case "%":String simdikiislem = txthesap.getText();
			ilksayı = Double.parseDouble(simdikiislem);
			txthesap.setText("");
			decimal = 0;
			break;
		default:
		}
	}

	@FXML
	void sayilar(ActionEvent event) {
		 sayı = ((Button) event.getSource()).getText();
		 öncekisayi = txthesap.getText();
		 yenisayi = öncekisayi + sayı;
		txthesap.setText(yenisayi);
	}

	@FXML
	void decimal(ActionEvent event) {
		if (decimal == 0) {
			String decimalsayı = ((Button) event.getSource()).getText();
			 öncekisayi = txthesap.getText();
			 yenisayi = öncekisayi + decimalsayı;
			txthesap.setText(yenisayi);
			decimal = 1;
		}
	}

	@FXML
	void esittir(ActionEvent event) {
		double sonrakisayı;
		double sonuc = 0;
		String sonsonuc = txthesap.getText();
		sonrakisayı = Double.parseDouble(sonsonuc);
		switch (islemler) {
		case "+":sonuc = ilksayı + sonrakisayı;break;
		case "-":sonuc = ilksayı - sonrakisayı;break;
		case "*":sonuc = ilksayı * sonrakisayı;break;
		case "/":sonuc = ilksayı / sonrakisayı;break;
		case "%":sonuc = ((ilksayı*sonrakisayı)/100);
		default:
		}
		String format = String.format("%.1f", sonuc);
		txthesap.setText(format);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
