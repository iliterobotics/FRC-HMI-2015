package org.ilite.ui.y2015;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ViewBuilder {


	public static final double TOTAL_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
	public static final double TOTAL_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
	
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
		stage.setX(0);
		stage.setY(0);
		// stage.setFullScreen(true);
		scene.setFill(Color.TRANSPARENT);
	}

	public static void buildBorderPane() {
		borderPane = new BorderPane();
		
		BorderPane mainPane = new BorderPane();
//		StackPane mainView = MainView.generateMainView();
//		StackPane alignmentView = AlignmentView.generateAlignmentView();
		
		mainPane.setLeft(MainView.generateMainView());
		mainPane.setRight(AlignmentView.generateAlignmentView());
		
		borderPane.setCenter(mainPane);
		borderPane.setLeft(WidgetBar.generateWidgets());
		
		StackPane pane = new StackPane();
		HBox box = CommsBar.generateCommsBar();
		pane.getChildren().add(box);		
		StackPane.setAlignment(box, Pos.CENTER_RIGHT);
		
		borderPane.setBottom(pane);
		borderPane.setMinSize(TOTAL_WIDTH, TOTAL_HEIGHT);
		borderPane.setMaxSize(TOTAL_WIDTH, TOTAL_HEIGHT);
	}
}
