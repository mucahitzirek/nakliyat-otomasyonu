package model;


import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

public class NotlarData {
	private int idnot;
	private String baslik;
	private String detay;
	private String tarih;
	private int kullaniciid;
	Database data = new Database();
	String sorgunotEkle,sorgunotListele;
	public ObservableList<NotlarData> notliste = FXCollections.observableArrayList();


	public void notEkle(String notbslk, String notdetay, LocalDate localDate, int kulid) {
		try {
			data.Vtb();
			sorgunotEkle = "INSERT INTO notlar (baslik,detay,tarih,kullaniciid) VALUES" + "('" + notbslk + "','"
					+ notdetay + "','" + localDate + "'," + kulid + ")";
			data.st.executeUpdate(sorgunotEkle);
		} catch (Exception e) {
			System.out.println("Ana Ekran Not Kaydet :" + e.getMessage());
		}
	}

	public void notListele(int kulid){
		sorgunotListele = "Select * from notlar where kullaniciid="+kulid+";";
			data.Vtb();
			try {
				data.rs=data.st.executeQuery(sorgunotListele);
				while (data.rs.next()) {
					notliste.add(new NotlarData(data.rs.getInt(1), data.rs.getString(2), data.rs.getString(3), data.rs.getString(4)));
				}
			} catch (SQLException e) {
				System.out.println("Hatali Not Listeleme" + e.getMessage());
			}
	}

	public NotlarData(int idnot, String baslik, String detay, String tarih) {
		super();
		this.idnot = idnot;
		this.baslik = baslik;
		this.detay = detay;
		this.tarih = tarih;
	}

	public NotlarData(String baslik, String detay, String tarih) {
		super();
		this.baslik = baslik;
		this.detay = detay;
		this.tarih = tarih;
	}

	public NotlarData() {
	}

	public int getIdnot() {
		return idnot;
	}

	public String getBaslik() {
		return baslik;
	}

	public String getDetay() {
		return detay;
	}

	public String getTarih() {
		return tarih;
	}

	public int getKullaniciid() {
		return kullaniciid;
	}

	@Override
	public String toString() {
		return getBaslik();
	}

}
