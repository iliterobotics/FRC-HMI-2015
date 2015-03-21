package org.ilite.ui.y2015;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewBuilder {

//	public static final double TOTAL_WIDTH = Screen.getPrimary()
//			.getVisualBounds().getWidth();
//	public static final double TOTAL_HEIGHT = Screen.getPrimary()
//			.getVisualBounds().getHeight();

	public static final double TOTAL_WIDTH = 800;
	public static final double TOTAL_HEIGHT = 600;
	
	private static BorderPane borderPane;

	public static void generateUI(Stage stage) {
		//ManagerDriver.init();
		buildBorderPane();
		Scene scene = new Scene(borderPane);
		scene.getStylesheets().addAll("/org/ilite/uitools/css/main.css",
				"/org/ilite/ui/css/UIManager.css");

		stage.setTitle("RECYCLE RUSH 2015");
		stage.setWidth(TOTAL_WIDTH * 0.7 + 15);
		stage.setHeight(TOTAL_WIDTH * 0.7 + 25);

		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
		//stage.setFullScreen(true);
		scene.setFill(Color.TRANSPARENT);
	}

	public static void buildBorderPane() {
		borderPane = new BorderPane();

		BorderPane mainPane = new BorderPane();
		// StackPane mainView = MainView.generateMainView();
		// StackPane alignmentView = AlignmentView.generateAlignmentView();

//		mainPane.setLeft(MainView.generateMainView());
//		mainPane.setRight(AlignmentView.generateAlignmentView());

//		borderPane.setCenter(mainPane);
		borderPane.setCenter(AlignmentView.generateAlignmentView(TOTAL_WIDTH * 0.7, TOTAL_WIDTH * 0.7));
//		borderPane.setLeft(WidgetBar.generateWidgets());

		StackPane pane = new StackPane();
		HBox box = CommsBar.generateCommsBar();
		pane.getChildren().add(box);
		StackPane.setAlignment(box, Pos.TOP_LEFT);

		//borderPane.setBottom(pane);
		borderPane.setMinSize(TOTAL_WIDTH * 0.7, TOTAL_WIDTH * 0.7);
		borderPane.setMaxSize(TOTAL_WIDTH * 0.7, TOTAL_WIDTH * 0.7);
	}
}
