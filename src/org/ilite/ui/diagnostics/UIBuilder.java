package org.ilite.ui.diagnostics;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UIBuilder 
{
	public static void generateUI(Stage stage) 
	{
		Scene scene = new Scene(new Group());
		scene.getStylesheets().addAll("/org/ilite/uitools/css/main.css", "/org/ilite/ui/css/UIManager.css");
		
		stage.setTitle("RECYCLE RUSH 2015 UI");
		stage.setWidth(800);
		stage.setHeight(800);
		UIManager uiManager = new UIManager();
		((Group) scene.getRoot()).getChildren().add(uiManager.getMainPane());

		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);
		scene.setFill(Color.TRANSPARENT);
	}

/*	private static void MikalzTestMethod(Pane parent) {
		NotificationHolder notifier = new NotificationHolder();
		notifier.setFadeTime(1000);
		notifier.notify("hmmmm");
		notifier.notify("who, r u?");
		parent.getChildren()
				.addAll(notifier,
						new ToggleButton("face.png"),
						new Gauge());
	} */
}
