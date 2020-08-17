package model;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

public class FirmabilgileriData {

	Database data = new Database();
	private int idfirma;
	private String ad;
	private String adres;
	private int yuk;
	private String tel,email,fax;
	String  sorguListele, sorguEkle, sorgufirmaID, sorgufirmaAd,sorgufirmaYuk,sorgufirmagidenYuk;
	public ObservableList<FirmabilgileriData> firmaliste = FXCollections.observableArrayList();
	public static int firmaid,firmanetyuk,firmagidenyuk;
	public ObservableList<String> firmalar = FXCollections.observableArrayList();


	public void firmayukAl(String firmasecilenad){
		sorgufirmaYuk="select yuk as firmanetyuk from firma where ad='"+firmasecilenad+"';";
		try {
			data.Vtb();
			data.rs=data.st.executeQuery(sorgufirmaYuk);
			while(data.rs.next()){
				firmanetyuk=data.rs.getInt("firmanetyuk");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void firmagidenYuk(String firmasecilenad){
		sorgufirmagidenYuk="select sum(yukmiktar) as firmagidenyuk from arac where firmaid in (select idfirma from firma where ad='"+firmasecilenad+"');";
		try {
			data.Vtb();
			data.rs=data.st.executeQuery(sorgufirmagidenYuk);
			while(data.rs.next()){
				firmagidenyuk=data.rs.getInt("firmagidenyuk");
			}
		} catch (Exception e) {
			System.out.println("Firma Giden Yukleri alinamadi : " + e.getMessage());
		}
	}

	public void firmaAdAl() {
		sorgufirmaAd = "select ad from firma";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgufirmaAd);
			while (data.rs.next()) {
				firmalar.add(data.rs.getString("ad"));
			}
		} catch (Exception e) {
			System.out.println("Firma adlari alinamadi : " + e.getMessage());
		}
	}

	public void firmaIdAl(String firma) {
		data.Vtb();
		sorgufirmaID = "select idfirma from firma where ad='" + firma + "';";
		try {
			data.rs = data.st.executeQuery(sorgufirmaID);
			while (data.rs.next()) {
				firmaid = data.rs.getInt("idfirma");
			}
		} catch (SQLException e) {
			System.out.println("firma id alinamadi : " + e.getMessage());

		}
	}

	public void listeleFirma() {
		data.Vtb();
		sorguListele = "select ad,adres,yuk,tel,email,fax from firma";
		try {
			data.rs = data.st.executeQuery(sorguListele);
			while (data.rs.next()) {
				firmaliste.add(new FirmabilgileriData(data.rs.getString(1), data.rs.getString(2), data.rs.getInt(3),
						data.rs.getString(4), data.rs.getString(5), data.rs.getString(6)));
			}
		} catch (SQLException e) {
			System.out.println("Hatali Firma Listeleme :" + e.getMessage());
		}
	}

	public void ekleFirma(String ad, String adres, int yuk, String tel, String email, String fax) {
		data.Vtb();
		sorguEkle = "INSERT INTO firma (ad,adres,yuk,tel,email,fax) VALUES" + "('" + ad + "','" + adres + "','" + yuk
				+ "','" + tel + "','" + email + "','" + fax + "')";
		try {
			data.st.executeUpdate(sorguEkle);
		} catch (SQLException e) {
			System.out.println("Firma Eklenemedi : " + e.getMessage());
		}
	}

	public FirmabilgileriData() {
	}

	public FirmabilgileriData(String ad, String adres, int yuk, String tel, String email, String fax) {
		super();
		this.ad = ad;
		this.adres = adres;
		this.yuk = yuk;
		this.tel = tel;
		this.email = email;
		this.fax = fax;
	}

	public int getIdfirma() {
		return idfirma;
	}

	public String getAd() {
		return ad;
	}

	public String getAdres() {
		return adres;
	}

	public int getYuk() {
		return yuk;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

}
