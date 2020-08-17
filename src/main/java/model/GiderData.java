package model;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

public class GiderData {
	private int idgider, gider, kullaniciid;
	private String aciklama;
	private Date tarih;
	private String sorgugiderEkle, sorgugiderListele, sorguId, sorguSil;
	public ObservableList<GiderData> giderliste = FXCollections.observableArrayList();
	public static int giderid;
	Database data = new Database();

	public void giderListele(int kulid) {
		sorgugiderListele = "select aciklama,gider,tarih from gider where kullaniciid='" + kulid + "'";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgugiderListele);
			while (data.rs.next()) {
				giderliste.add(new GiderData(data.rs.getString(1), data.rs.getInt(2), data.rs.getDate(3)));
			}
		} catch (SQLException e) {
			System.out.println("Gider Listelenemedi : " + e.getMessage());
		}
	}

	public void giderSil() {
		sorguSil = "Delete  from gider where idgider=?";
		try {
			data.Vtb();
			data.pst = data.Vtb().prepareStatement(sorguSil);
			data.pst.setInt(1, GiderData.giderid);
			data.pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Gider Silinemedi : " + e.getMessage());
		}
	}

	public void giderId(String aciklama) {
		sorguId = "select * from gider where aciklama='" + aciklama + "';";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorguId);
			while (data.rs.next()) {
				giderid = data.rs.getInt("idgider");
			}
		} catch (SQLException e) {
			System.out.println("idgider Alinamadi : " + e.getMessage());
		}
	}

	public void giderEkle(String aciklama, String gider, LocalDate localDate, int kulid) {
		sorgugiderEkle = "insert into gider (aciklama,gider,tarih,kullaniciid) values" + "('" + aciklama + "'," + gider
				+ ",'" + localDate + "'," + kulid + ")";

		try {
			data.Vtb();
			data.st.executeUpdate(sorgugiderEkle);
		} catch (SQLException e) {
			System.out.println("Gider Eklenmedi : " + e.getMessage());
		}

	}

	public GiderData() {
	}

	public GiderData(String aciklama, int gider, Date tarih) {
		super();
		this.aciklama = aciklama;
		this.gider = gider;
		this.tarih = tarih;
	}

	public int getIdgider() {
		return idgider;
	}

	public String getAciklama() {
		return aciklama;
	}

	public int getGider() {
		return gider;
	}

	public Date getTarih() {
		return tarih;
	}

	public int getKullaniciid() {
		return kullaniciid;
	}

}
