package org.ilite.ui.table;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.ilite.telemetry.data.y2015.EData2015;

public class Table {

	public static void buildSimpleTable(Stage stage){
		GridPane grid = new GridPane();
		grid.setHgap(20);
		int counter = 0;
		for(EData2015 data : EData2015.values()){
			Text textNamePort = new Text("Port " + data.getPortNumber() + " " + (data.getDisplayLabel() != null ? data.getDisplayLabel() : data.getTelemetryType().name()));
			Text textValue = new Text("VALUE");
			Text textAverage = new Text("AVERAGE");
			grid.add(textNamePort, 0, counter);
			grid.add(textValue, 1, counter);
			grid.add(textAverage, 2, counter);
			counter ++;
		}
		
		Scene scene = new Scene(new Group());
		scene.getStylesheets().addAll("/org/ilite/uitools/css/main.css", "/org/ilite/ui/css/UIManager.css");
		stage.setTitle("RECYCLE RUSH 2015 UI");
		stage.setWidth(800);
		stage.setHeight(800);
		((Group) scene.getRoot()).getChildren().add(grid);

		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);
		scene.setFill(Color.TRANSPARENT);
		
		
	}
}
