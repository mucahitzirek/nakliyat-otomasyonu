package fonksiyonlar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Kontrol {
	Alerts alert=new Alerts();
	ButtonType tm = new ButtonType("Tamam");


	public boolean isimkontrol(String getText) {
		Pattern az = Pattern.compile("[a-zA-Z]+");
		Matcher al = az.matcher(getText);
		if (al.find() && al.group().equals(getText)) {
			return true;
		} else {
			alert.showAlertButton(AlertType.ERROR, "Bilgilendirme Ekranı", null,
					"Lutfen Kullanici Adinizi Yalnızca harflerden Olusturunuz.\nTurkce Karakterler Kullanmayınız(ü,ı,ş,ç,ö)\nOrnek -> Mucahit", tm);
			return false;
		}
	}

	public boolean emailkontrol(String getText) {
		Pattern az = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher al = az.matcher(getText);
		if (al.find() && al.group().equals(getText)) {
			return true;
		} else {
			alert.showAlertButton(AlertType.ERROR, "Bilgilendirme Ekranı", null,
					"Lutfen Gercerli Bir Email Adresi Yaziniz.\nTurkce harfleri Kullanmayiniz(ü,ı,ş,ç,ö)\nornek@gmail.com", tm);
			return false;
		}
	}
}
