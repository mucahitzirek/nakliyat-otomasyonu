package model;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Database;

public class SoforbilgileriData {
	Database data = new Database();
	private int soforid;
	private String ad, tel, ehliyetno, ruhsatno, hesapno, sorgu;
	public static int soforidsi;
	public ObservableList<SoforbilgileriData> yenisofor = FXCollections.observableArrayList();
	public ObservableList<String> sofortelleri = FXCollections.observableArrayList();
	public ObservableList<String> soforadlari = FXCollections.observableArrayList();

	public void soforadTelAl(String adtelal) {
		sorgu = "select tel from sofor where ad='" + adtelal + "';";
		try {
			data.Vtb();
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				sofortelleri.add(data.rs.getString("tel"));
			}
		} catch (SQLException e) {
			System.out.println("Sofor Adindan Telefonlari alinamandi : ");
		}
	}

	public void soforidAl(String adtik, String teltik) {
		sorgu = "select * from sofor where ad='" + adtik + "' and tel='" + teltik + "';";
		data.Vtb();
		try {
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				soforidsi = data.rs.getInt("idsofor");
			}
		} catch (SQLException e) {
			System.out.println("idSofor alinamadi : " + e.getMessage());
		}
	}

	public void soforListele() {
		String sorgua = "Select ad,tel,ehliyetno,ruhsatno,hesapno from sofor";
		data.Vtb();
		try {
			data.rs = data.st.executeQuery(sorgua);
			while (data.rs.next()) {
				yenisofor.add(new SoforbilgileriData(data.rs.getString(1), data.rs.getString(2), data.rs.getString(3),
						data.rs.getString(4), data.rs.getString(5)));
			}
		} catch (SQLException e) {
			System.out.println("Sofor Listenmedi : " + e.getMessage());
		}
	}

	public void soforEkle(String ad, String tel, String ehliyetno, String ruhsatno, String hesapno) {
		data.Vtb();
		sorgu = "insert into sofor (ad,tel,ehliyetno,ruhsatno,hesapno) values ('" + ad + "','" + tel + "','" + ehliyetno
				+ "','" + ruhsatno + "','" + hesapno + "')";
		try {
			data.st.executeUpdate(sorgu);
		} catch (SQLException e) {
			System.out.println("Sofor Eklenmedi : " + e.getMessage());
		}

	}

	public void soforadAl() {
		sorgu = "select ad from sofor";
		data.Vtb();
		try {
			data.rs = data.st.executeQuery(sorgu);
			while (data.rs.next()) {
				soforadlari.add(data.rs.getString("ad"));
			}
		} catch (SQLException e) {
			System.out.println("Sofor adlari alinamadi :" + e.getMessage());
		}

	}

	public SoforbilgileriData() {
	}

	public SoforbilgileriData(int soforid, String ad, String tel, String ehliyetno, String ruhsatno, String hesapno) {
		super();
		this.soforid = soforid;
		this.ad = ad;
		this.tel = tel;
		this.ehliyetno = ehliyetno;
		this.ruhsatno = ruhsatno;
		this.hesapno = hesapno;
	}

	public SoforbilgileriData(String ad, String tel, String ehliyetno, String ruhsatno, String hesapno) {
		super();
		this.ad = ad;
		this.tel = tel;
		this.ehliyetno = ehliyetno;
		this.ruhsatno = ruhsatno;
		this.hesapno = hesapno;
	}

	public int getSoforid() {
		return soforid;
	}

	public String getAd() {
		return ad;
	}

	public String getTel() {
		return tel;
	}

	public String getEhliyetno() {
		return ehliyetno;
	}

	public String getRuhsatno() {
		return ruhsatno;
	}

	public String getHesapno() {
		return hesapno;
	}

}
