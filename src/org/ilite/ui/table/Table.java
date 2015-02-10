package org.ilite.ui.table;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.ilite.telemetry.data.y2015.EData2015;

public class Table {
	
	private static GridPane pane;
	
	private static List<Double> values = new ArrayList<Double>();

	public static void buildSimpleTable(Stage stage){
		for(int i = 0; i < EData2015.values().length; i++){
			values.add(0.0);
		}
		pane = new GridPane();
		pane.setHgap(20);
		
		refreshTable();
		
		Scene scene = new Scene(new Group());
		scene.getStylesheets().addAll("/org/ilite/uitools/css/main.css", "/org/ilite/ui/css/UIManager.css");
		stage.setTitle("RECYCLE RUSH 2015 UI");
		stage.setWidth(800);
		stage.setHeight(800);
		VBox contents = new VBox();
		ScrollPane s1 = new ScrollPane();
		Text label = new Text("This is a simple table GUI... ");
		label.setFont(new Font("Consolas", 50));
		s1.setContent(pane);
		s1.setFitToHeight(true);
		s1.setFitToWidth(true);
		s1.setMaxHeight(700);
		s1.setMinWidth(500);
		s1.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		contents.getChildren().addAll(label, s1);
		((Group) scene.getRoot()).getChildren().addAll(contents);

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
		pane.setVgap(4);
		int counter = 0;
		for(EData2015 data : EData2015.values()){
			Text textNamePort = new Text("Port " + data.getPortNumber() + " " + (data.getDisplayLabel() != null ? data.getDisplayLabel() : data.getTelemetryType().name()));
			Text textValue = new Text("   =   " + values.get(counter).doubleValue());
			pane.add(textNamePort, 0, counter);
			pane.add(textValue, 1, counter);
			counter++;
		}
		
	}
}
