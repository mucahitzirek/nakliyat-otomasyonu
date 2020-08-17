package model;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

public class YoneticininKullanicilari {
	Database data = new Database();
	String sorgu;
	public int gunlukissayi, gunlukkazanctoplam, ayliksayi, aylikkazanilan, gungider, aygider, netgider, netgelir;
	public ObservableList<GiderData> giderler = FXCollections.observableArrayList();
	public ObservableList<String> kullaniciadlari = FXCollections.observableArrayList();
	private String sorguIc=" kullaniciid in(select idkullanici from kullanici where ad='";

	public void kullaniciAdlari(int yonetid) {
		sorgu = "select ad from kullanici where kullanicidurum=1 and yoneticiid=" + yonetid + ";";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				kullaniciadlari.add(data.rs.getString("ad"));
			}
		} catch (SQLException e) {
			System.out.println("Kullanici adlari alinamadi :" + e.getMessage());
		}
	}

	public void gunlukSayi(LocalDate localDate, String secilenad) {
		sorgu = "select count(idarac) sayi from arac where yuklemetarihi='" + localDate
				+ "' and "+ sorguIc + secilenad + "'); ";
		try {
			data.Vtb();
			while (data.rs.next()) {
				data.rs = data.st.executeQuery(sorgu);
				gunlukissayi = data.rs.getInt("sayi");
			}
		} catch (SQLException e) {
			System.out.println("gunlukSayi Alinamadi : " + e.getMessage());
		}
	}

	public void gunlukKazanc(LocalDate localDate, String secilenad) {
		sorgu = "select sum(komisyon) gunlukkazanc from arac where yuklemetarihi='" + localDate
				+ "' and "+sorguIc + secilenad + "');";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				gunlukkazanctoplam = data.rs.getInt("gunlukkazanc");
			}
		} catch (SQLException e) {
			System.out.println("gunlukKazanc Alinamadi : " + e.getMessage());
		}
	}

	public void aylikIs(LocalDate localDate2, LocalDate localDate, String secilenad) {
		sorgu = "select count(idarac) ayliksayi from arac where yuklemetarihi between '" + localDate2 + "'and '"
				+ localDate + "' and "+sorguIc + secilenad + "');";

		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				ayliksayi = data.rs.getInt("ayliksayi");
			}
		} catch (Exception e) {
			System.out.println("aylikIs alinamadi : " + e.getMessage());
		}
	}

	public void aylikKazanc(LocalDate localDate2, LocalDate localDate, String secilenad) {
		sorgu = "select sum(komisyon) aylikkazanc from arac where yuklemetarihi between '" + localDate2 + "'and '"
				+ localDate + "'and"+sorguIc + secilenad + "');";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				aylikkazanilan = data.rs.getInt("aylikkazanc");
			}
		} catch (Exception e) {
			System.out.println("aylikKazanc alinamadi : " + e.getMessage());
		}
	}

	public void gunlukGider(LocalDate localDate, String secilenad) {
		sorgu = "select sum(gider) gungider from gider where tarih='" + localDate
				+ "' and "+sorguIc + secilenad + "');";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				gungider = data.rs.getInt("gungider");
			}
		} catch (Exception e) {
			System.out.println("gunlukGider alinamadi : " + e.getMessage());
		}
	}

	public void aylikGider(LocalDate localDate2, LocalDate localDate, String secilenad) {
		sorgu = "select sum(gider) aygider from gider where tarih between '" + localDate2 + "' and '" + localDate
				+ "'and "+sorguIc + secilenad + "');";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				aygider = data.rs.getInt("aygider");
			}
		} catch (Exception e) {
			System.out.println("aylikGider alinamadi : " + e.getMessage());
		}
	}

	public void giderTablo(String secilenad) {
		sorgu = "Select aciklama,gider,tarih from gider where "+sorguIc+ secilenad + "');";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				giderler.add(new GiderData(data.rs.getString("aciklama"), data.rs.getInt("gider"),
						data.rs.getDate("tarih")));
			}
		} catch (SQLException e) {
			System.out.println("Giderler Listenmedi : " + e.getMessage());
		}
	}

	public void netGider(String secilenad) {
		sorgu = "select sum(gider) gider from gider where"+sorguIc+ secilenad + "');";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				netgider = data.rs.getInt("gider");
			}
		} catch (SQLException e) {
			System.out.println("Gider getirilemedi : " + e.getMessage());
		}
	}

	public void netGelir(String secilenad) {
		sorgu = "select sum(komisyon) gelir from arac where "+sorguIc+ secilenad + "');";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				netgelir = data.rs.getInt("gelir");
			}
		} catch (SQLException e) {
			System.out.println("Gelir getirilemedi : " + e.getMessage());
		}
	}

}
