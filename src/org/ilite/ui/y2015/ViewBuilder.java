package org.ilite.ui.y2015;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ViewBuilder 
{
	public static void generateUI(Stage stage)
	{
		Scene scene = new Scene(new Group());
		scene.getStylesheets().addAll("/org/ilite/uitools/css/main.css", "/org/ilite/ui/css/UIManager.css");
		
		Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
		
		stage.setTitle("RECYCLE RUSH 2015");
		stage.setWidth(screenSize.getWidth());
		stage.setHeight(screenSize.getHeight()-215);

		stage.setScene(scene);
		stage.show();
//		stage.setFullScreen(true);
		scene.setFill(Color.TRANSPARENT);
	}
}
