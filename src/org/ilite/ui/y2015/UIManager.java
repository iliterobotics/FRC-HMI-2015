package org.ilite.ui.y2015;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import org.ilite.uitools.widget.notification.NotificationHolder;

public class UIManager {
	
	private static StackPane mainPane;
	private static BorderPane frontalPane;
	private static VBox selectionHolder;
	private static GridPane focusPane;
	private static NotificationHolder notifications;
	
	private static int fadeTime;

	public static StackPane buildMainPane(){
		mainPane = new StackPane();
		mainPane.getStyleClass().setAll("PUT STYLE CLASS HERE");
		mainPane.getChildren().addAll(buildFrontalPane(), buildNotificationHolder());
		return mainPane;
	}
	
	public static BorderPane buildFrontalPane(){
		frontalPane = new BorderPane();
		frontalPane.getStyleClass().setAll("PUT STYLE CLASS HERE");
		StackPane.setAlignment(frontalPane, Pos.CENTER);
		frontalPane.setLeft(buildSelectionHolder());
		frontalPane.setCenter(buildFocusPane());
		return frontalPane;
	}
	
	public static VBox buildSelectionHolder(){
		selectionHolder = new VBox();
		selectionHolder.getStyleClass().setAll("PUT STYLE CLASS HERE");
		//TODO add list of selections
		return selectionHolder;
	}
	
	public static NotificationHolder buildNotificationHolder(){
		if( fadeTime <= 0 ) fadeTime = 1000;
		notifications = new NotificationHolder();
		notifications.setFadeTime(fadeTime);
		StackPane.setAlignment(notifications, Pos.BOTTOM_RIGHT);
		return notifications;
	}
	
	public static GridPane buildFocusPane(){
		focusPane = new GridPane();
		focusPane.getStyleClass().setAll("PUT STYLE CLASS HERE");
		//TODO add list of pinned info cards
		return focusPane;
	}
}
