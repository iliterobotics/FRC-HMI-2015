package org.ilite.ui.table;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.ilite.telemetry.data.y2015.EData2015;

public class Table {
	
	private static GridPane pane;
	
	private static List<Double> values = new ArrayList<Double>();

	public static void buildSimpleTable(Stage stage){
		for(int i = 0; i < values.size(); i++){
			values.set(i, 0.0);
		}
		pane = new GridPane();
		pane.setHgap(20);
		
		refreshTable();
		
		Scene scene = new Scene(new Group());
		scene.getStylesheets().addAll("/org/ilite/uitools/css/main.css", "/org/ilite/ui/css/UIManager.css");
		stage.setTitle("RECYCLE RUSH 2015 UI");
		stage.setWidth(800);
		stage.setHeight(800);
		((Group) scene.getRoot()).getChildren().add(pane);

		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);
		scene.setFill(Color.TRANSPARENT);
		
		
		
	}
	
	public static void setValues(List<Double> vals){
		values = vals;
	}
	
	public static void refreshTable(){
		pane.setHgap(20);
		int counter = 0;
		for(EData2015 data : EData2015.values()){
			Text textNamePort = new Text("Port " + data.getPortNumber() + " " + (data.getDisplayLabel() != null ? data.getDisplayLabel() : data.getTelemetryType().name()));
			Text textValue = new Text(values.get(counter).doubleValue() + "");
			pane.add(textNamePort, 0, counter);
			pane.add(textValue, 1, counter);
			counter++;
		}
		
	}
}
