package org.ilite.ui.y2015;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ViewBuilder {


	public static final double TOTAL_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
	public static final double TOTAL_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight() - 215;
	
	private static BorderPane borderPane;
	
	
	public static void generateUI(Stage stage) {
		buildBorderPane();
		Scene scene = new Scene(borderPane);
		scene.getStylesheets().addAll("/org/ilite/uitools/css/main.css",
				"/org/ilite/ui/css/UIManager.css");

		stage.setTitle("RECYCLE RUSH 2015");
		stage.setWidth(TOTAL_WIDTH);
		stage.setHeight(TOTAL_HEIGHT);

		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
		// stage.setFullScreen(true);
		scene.setFill(Color.TRANSPARENT);
	}

	public static void buildBorderPane() {
		borderPane = new BorderPane();
		borderPane.setMinSize(TOTAL_WIDTH, TOTAL_HEIGHT);
		borderPane.setMaxSize(TOTAL_WIDTH, TOTAL_HEIGHT);
	}
}
