package fonksiyonlar;

import controller.Main;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OpaklikVer {
	private double xOffset = 0;
	private double yOffset = 0;

	public void Opaklik(AnchorPane ancorpane) {

		ancorpane.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		ancorpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Main.stage.setX(event.getScreenX() - xOffset);
				Main.stage.setY(event.getScreenY() - yOffset);
				Main.stage.setOpacity(0.7f);
			}
		});
		ancorpane.setOnDragDone((e) -> {
			Main.stage.setOpacity(1.0f);
		});
		ancorpane.setOnMouseReleased((e) -> {
			Main.stage.setOpacity(1.0f);
		});
	}
}
