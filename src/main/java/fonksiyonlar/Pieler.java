package fonksiyonlar;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import util.Database;

public class Pieler {

	Database data=new Database();

		public void netKazanc(PieChart pie,int idkar){
			int gelir = 0, gider = 0, netkazanc = 0;
			String sorgu = "select * from karzarar where idkarzarar="+idkar+";";
			try {
				data.Vtb();
				data.rs=data.st.executeQuery(sorgu);
				while (data.rs.next()) {
					gelir = data.rs.getInt("gelir");
					gider = data.rs.getInt("gider");
					netkazanc = data.rs.getInt("kar");
				}
			} catch (SQLException e) {
				System.out.println("Kar zararlar getirilemedi : " + e.getMessage());
			}
			ObservableList<PieChart.Data> pielist = FXCollections.observableArrayList(new PieChart.Data("Gelir", gelir),
					new PieChart.Data("Gider", gider), new PieChart.Data("NetKazanc", netkazanc));
			pie.setData(pielist);
		}
}
