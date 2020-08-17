package model;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

public class AracBilgileriData {

	private int idarac;
	private String plaka, araccins, yukcins, yukfiyat, yuklemeyeri, bosaltmayeri, sorgukullaniciListele;
	private float yukmiktar; // ton
	private Date yuklemetarihi, bosaltmatarihi;
	private int komisyon, soforid, firmaid, kullaniciid;
	private String sorgu = "Select plaka, araccins, yukcins, yukmiktar,yukfiyat,yuklemeyeri, yuklemetarihi, bosaltmayeri, bosaltmatarihi, komisyon from arac where kullaniciid=";
	private String sorgutarihFiltre = " and yuklemetarihi between '";
	private String sorguyuklemeterstenSirala = "' order by yuklemetarihi desc;";

	String sorguFiltre, sorguTarih, sorguaracSoforu, sorguaractarihSoforu;
	Database data = new Database();
	public ObservableList<AracBilgileriData> yeniarac = FXCollections.observableArrayList();
	public ObservableList<AracBilgileriData> arac = FXCollections.observableArrayList();

	public void aracListele(int kulid) {
		data.Vtb();
		sorguFiltre = sorgu + kulid + ";";
		try {
			data.rs = data.st.executeQuery(sorguFiltre);
			try {
				while (data.rs.next()) {
					yeniarac.add(new AracBilgileriData(data.rs.getString(1), data.rs.getString(2), data.rs.getString(3),
							data.rs.getFloat(4), data.rs.getString(5), data.rs.getString(6), data.rs.getDate(7),
							data.rs.getString(8), data.rs.getDate(9), data.rs.getInt(10)));
				}
			} catch (Exception e) {
				System.out.println("Arac listelenmedi : " + e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println("Arac bilgileri alinamadi : " + e.getMessage());
		}
	}

	public void aracsoforListele(int kulid, int soforid) {
		data.Vtb();
		sorguaracSoforu = sorgu + kulid + " and soforid=" + soforid + ";";
		try {
			data.rs = data.st.executeQuery(sorguaracSoforu);
			try {
				while (data.rs.next()) {
					yeniarac.add(new AracBilgileriData(data.rs.getString(1), data.rs.getString(2), data.rs.getString(3),
							data.rs.getFloat(4), data.rs.getString(5), data.rs.getString(6), data.rs.getDate(7),
							data.rs.getString(8), data.rs.getDate(9), data.rs.getInt(10)));
				}
			} catch (Exception e) {
				System.out.println("arac bilgileri alinamadi");
			}
		} catch (Exception e) {
			System.out.println("Arac listelenmedi : " + e.getMessage());
		}
	}

	public void aractarihegöreListele(int kulid, LocalDate localDate, LocalDate localDate2) {
		sorguTarih = sorgu + kulid + sorgutarihFiltre + localDate + "' and '" + localDate2 + sorguyuklemeterstenSirala;
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorguTarih);
			try {
				while (data.rs.next()) {
					yeniarac.add(new AracBilgileriData(data.rs.getString(1), data.rs.getString(2), data.rs.getString(3),
							data.rs.getFloat(4), data.rs.getString(5), data.rs.getString(6), data.rs.getDate(7),
							data.rs.getString(8), data.rs.getDate(9), data.rs.getInt(10)));
				}
			} catch (Exception e) {
				System.out.println("Arac listelenmedi : " + e.getMessage());
			}
		} catch (SQLException e) {
			System.out.println("Tarih siralanamadi");
		}
	}

	public void aracınsofortarihListele(int kulid, int soforid, LocalDate localDate, LocalDate localDate2) {
		sorguaractarihSoforu = sorgu + kulid + " and soforid=" + soforid +  sorgutarihFiltre + localDate + "' and '"
				+ localDate2 + sorguyuklemeterstenSirala;
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorguaractarihSoforu);
			try {
				while (data.rs.next()) {
					yeniarac.add(new AracBilgileriData(data.rs.getString(1), data.rs.getString(2), data.rs.getString(3),
							data.rs.getFloat(4), data.rs.getString(5), data.rs.getString(6), data.rs.getDate(7),
							data.rs.getString(8), data.rs.getDate(9), data.rs.getInt(10)));
				}
			} catch (Exception e) {
				System.out.println("Arac listelenmedi : " + e.getMessage());
			}
		} catch (Exception e) {
			System.out.println("Soforun arac bilgileri alinamadi : " + e.getMessage());
		}
	}

	public void kullaniciaracListele(String secilenad, int yoneticiid) {
		sorgukullaniciListele = "Select kullaniciid,plaka, araccins, yukcins, yukmiktar, yukfiyat, yuklemeyeri, yuklemetarihi, bosaltmayeri, bosaltmatarihi, komisyon from  arac where kullaniciid in(select idkullanici from kullanici where kullanicidurum=1 and ad='"
				+ secilenad + "' and yoneticiid in(select idyonetici from yonetici where idyonetici=" + yoneticiid
				+ "))";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgukullaniciListele);
			while (data.rs.next()) {
				arac.add(new AracBilgileriData(data.rs.getInt(1), data.rs.getString(2), data.rs.getString(3),
						data.rs.getString(4), data.rs.getFloat(5), data.rs.getString(6), data.rs.getString(7),
						data.rs.getDate(8), data.rs.getString(9), data.rs.getDate(10), data.rs.getInt(11)));
			}
		} catch (SQLException e) {
			System.out.println("kullanici araclari Listelenmedi : " + e.getMessage());
		}
	}

	public AracBilgileriData() {
	}

	public AracBilgileriData(int kullaniciid, String plaka, String araccins, String yukcins, float yukmiktar,
			String yukfiyat, String yuklemeyeri, Date yuklemetarihi, String bosaltmayeri, Date bosaltmatarihi,
			int komisyon) {
		super();
		this.kullaniciid = kullaniciid;
		this.plaka = plaka;
		this.araccins = araccins;
		this.yukcins = yukcins;
		this.yukfiyat = yukfiyat;
		this.yuklemeyeri = yuklemeyeri;
		this.bosaltmayeri = bosaltmayeri;
		this.yukmiktar = yukmiktar;
		this.yuklemetarihi = yuklemetarihi;
		this.bosaltmatarihi = bosaltmatarihi;
		this.komisyon = komisyon;
	}

	public AracBilgileriData(String plaka, String araccins, String yukcins, float yukmiktar, String yukfiyat,
			String yuklemeyeri, Date yuklemetarihi, String bosaltmayeri, Date bosaltmatarihi, int komisyon) {
		super();
		this.plaka = plaka;
		this.araccins = araccins;
		this.yukcins = yukcins;
		this.yukmiktar = yukmiktar;
		this.yukfiyat = yukfiyat;
		this.yuklemeyeri = yuklemeyeri;
		this.yuklemetarihi = yuklemetarihi;
		this.bosaltmayeri = bosaltmayeri;
		this.bosaltmatarihi = bosaltmatarihi;
		this.komisyon = komisyon;
	}

	public int getIdarac() {
		return idarac;
	}

	public String getPlaka() {
		return plaka;
	}

	public String getAraccins() {
		return araccins;
	}

	public String getYukcins() {
		return yukcins;
	}

	public float getYukmiktar() {
		return yukmiktar;
	}

	public String getYukfiyat() {
		return yukfiyat;
	}

	public String getYuklemeyeri() {
		return yuklemeyeri;
	}

	public Date getYuklemetarihi() {
		return yuklemetarihi;
	}

	public String getBosaltmayeri() {
		return bosaltmayeri;
	}

	public Date getBosaltmatarihi() {
		return bosaltmatarihi;
	}

	public int getKomisyon() {
		return komisyon;
	}

	public int getSoforid() {
		return soforid;
	}

	public int getFirmaid() {
		return firmaid;
	}

	public int getKullaniciid() {
		return kullaniciid;
	}

}
