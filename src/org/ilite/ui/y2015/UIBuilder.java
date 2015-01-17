package org.ilite.ui.y2015;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIBuilder
{
	public static void generateUI(Stage stage)
	{
		Scene scene=new Scene(new Group());
		stage.setTitle("RECYCLE RUSH 2015 UI");
		stage.setWidth(800);
		stage.setHeight(800);
		VBox nodeList=new VBox();
		((Group) scene.getRoot()).getChildren().add(nodeList);
		stage.setScene(scene);
		stage.show();
	}
}
