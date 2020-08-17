package model;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

public class KullaniciData {

	private int idkullanici, yoneticiid;
	private String ad, soyad, kullaniciad, kullanicisifre, kullaniciemail, yonetkullanAdlari, sorguyonetID,
			 sorgukullaniciEkle, sorguyoneticiAdlari, sorguyonetKullanicilari;
	private Boolean kullanicidurum;
	private boolean durum = false;
	public static String add, soyadd, yoneticiMail;
	public static int iddkullanici, yoneticiId, kullaniciidal;
	Database data = new Database();
	public ObservableList<String> yoneticiler = FXCollections.observableArrayList();
	public ObservableList<String> kullaniciadlari = FXCollections.observableArrayList();
	public ObservableList<KullaniciData> kullanicilar = FXCollections.observableArrayList();

	public void yoneticininkullaniclariniGoruntule(int yonetid) {
		sorguyonetKullanicilari = "Select * from kullanici where kullanicidurum=1 and yoneticiid=" + yonetid + ";";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorguyonetKullanicilari);
			while (data.rs.next()) {
				kullanicilar.add(new KullaniciData(data.rs.getInt(1), data.rs.getString(2), data.rs.getString(3),
						data.rs.getString(4), data.rs.getString(5), data.rs.getString(6)));
			}
		} catch (SQLException e) {
			System.out.println("Kullanicilar Getirilemedi : " + e.getMessage());
		}
	}

	public KullaniciData() {
	}

	public void kullaniciIdAl(String secilenad) {
		try {
			data.Vtb();
			data.rs = data.st.executeQuery("select idkullanici from kullanici where ad='" + secilenad + "';");
			while (data.rs.next()) {
				kullaniciidal = data.rs.getInt("idkullanici");
			}
		} catch (SQLException e) {
			System.out.println("Kullanici id alinamadi : " + e.getMessage());
		}
	}

	public void girisAdSoyadAl(String kullaniciad, String kullanicisifre) {
		data.Vtb();
		try {
			data.rs = data.st.executeQuery("select idkullanici,ad,soyad from kullanici where kullaniciad='"
					+ kullaniciad + "' and kullanicisifre='" + kullanicisifre + "';");
			while (data.rs.next()) {
				add = data.rs.getString("ad");
				soyadd = data.rs.getString("soyad");
				iddkullanici = data.rs.getInt("idkullanici");
			}
		} catch (SQLException e) {
			System.out.println("kullaniciid alinamadi : " + e.getMessage());
		}
	}

	public void yoneticininKullaniciAdlari(int idyonet) {
		yonetkullanAdlari = "select ad from kullanici where kullanicidurum=1 and yoneticiid in (select idyonetici from yonetici where idyonetici="
				+ idyonet + ");";
		data.Vtb();
		try {
			data.rs = data.st.executeQuery(yonetkullanAdlari);
			while (data.rs.next()) {
				kullaniciadlari.add(data.rs.getString("ad"));
			}
		} catch (SQLException e) {
			System.out.println("Kullanici adlari alinamadi :" + e.getMessage());
		}
	}

	public void yoneticiMailIdAl(String idyonet) {
		data.Vtb();
		sorguyonetID = "select idyonetici,email from yonetici where ad='" + idyonet + "';";
		try {
			data.rs = data.st.executeQuery(sorguyonetID);
			while (data.rs.next()) {
				yoneticiMail = data.rs.getString("email");
				yoneticiId = data.rs.getInt("idyonetici");
			}
		} catch (SQLException e) {
			System.out.println("idyonetici alinamadi : " + e.getMessage());
		}
	}

	public void kullaniciEkle(String ad, String soyad, String kullaniciad, String kullanicisifre, String kullaniciemail,
			int yoneticiid) {
		data.Vtb();
		sorgukullaniciEkle = "insert into kullanici (ad,soyad,kullaniciad,kullanicisifre,kullaniciemail,yoneticiid,kullanicidurum) values ('"
				+ ad + "','" + soyad + "','" + kullaniciad + "','" + kullanicisifre + "','" + kullaniciemail + "',"
				+ yoneticiid + ", 1)";
		try {
			data.st.executeUpdate(sorgukullaniciEkle);
		} catch (Exception e) {
			System.out.println("Kullanici Eklenmedi : " + e.getMessage());
		}
	}

	public void yoneticiAdlariAl() {
		data.Vtb();
		sorguyoneticiAdlari = "select * from yonetici";
		try {
			data.rs = data.st.executeQuery(sorguyoneticiAdlari);
			while (data.rs.next()) {
				yoneticiler.add(data.rs.getString("ad"));
			}
		} catch (Exception e) {
			System.out.println("Yonetici adlari alinamadi : " + e.getMessage());
		}
	}

	public void girisBag(String kullaniciad, String kullanicisifre) {
		try {
			int row = 0;
			data.Vtb();
			try {
				data.rs = data.st.executeQuery(
						"select idkullanici,ad,soyad,kullaniciad,kullanicisifre from kullanici where kullaniciad='"
								+ kullaniciad + "' and kullanicisifre='" + kullanicisifre + "';");
			} catch (Exception e) {
				System.out.println("sorgu hatali");
			}
			while (data.rs.next()) {
				row = data.rs.getRow();
				this.idkullanici = data.rs.getInt("idkullanici");
				this.ad = data.rs.getString("ad");
				this.soyad = data.rs.getString("soyad");
				this.kullaniciad = data.rs.getString("kullaniciad");
				this.kullanicisifre = data.rs.getString("kullanicisifre");
			}
			if (row == 1) {
				durum = true;
			} else {
				durum = false;
			}
		} catch (Exception e) {
			System.out.println("sikinti");
		}
	}

	public KullaniciData(int idkullanici, String ad, String soyad, String kullaniciad, String kullanicisifre,
			String kullaniciemail) {
		super();
		this.idkullanici = idkullanici;
		this.ad = ad;
		this.soyad = soyad;
		this.kullaniciad = kullaniciad;
		this.kullanicisifre = kullanicisifre;
		this.kullaniciemail = kullaniciemail;
	}

	public boolean getDurum() {
		return durum;
	}

	public int getIdkullanici() {
		return idkullanici;
	}

	public String getAd() {
		return ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public String getKullaniciad() {
		return kullaniciad;
	}

	public String getKullanicisifre() {
		return kullanicisifre;
	}

	public String getKullaniciemail() {
		return kullaniciemail;
	}

	public int getYoneticiid() {
		return yoneticiid;
	}

	public Boolean getKullanicidurum() {
		return kullanicidurum;
	}

}
