package org.ilite.ui.y2015;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

import org.ilite.uitools.managers.Manager;
import org.ilite.uitools.widget.notification.NotificationHolder;
/**
 * Grand master lord Oberfeldwebel GUI 
 * @author Michael
 *
 */
public class UIManager {

	private StackPane mainPane;
	private BorderPane frontalPane;
	private VBox selectionHolder;
	private GridPane focusPane;
	private NotificationHolder notifications;

	private int fadeTime;
	
	private WidgetPanel[] widgetPanels = new WidgetPanel[9];

	public static final String DEFAULT = "mainBorderPane";

	public UIManager() {
		buildMainPane();
	}

	public void addWidgetPanel(Manager m) {
		focusPane.add(new WidgetPanel(m), 0, 0);
	}

	public StackPane buildMainPane() {
		Rectangle2D screen = Screen.getPrimary().getBounds();
		mainPane = new StackPane();
		mainPane.getStyleClass().setAll(DEFAULT);
		mainPane.setMinSize(screen.getWidth(), screen.getHeight());
		mainPane.getChildren().addAll(buildFrontalPane(),
				buildNotificationHolder());
		return mainPane;
	}

	public BorderPane buildFrontalPane() {
		frontalPane = new BorderPane();
		frontalPane.getStyleClass().setAll(DEFAULT);
		StackPane.setAlignment(frontalPane, Pos.CENTER);
		frontalPane.setLeft(buildSelectionHolder());
		frontalPane.setCenter(buildFocusPane());
		return frontalPane;
	}

	public VBox buildSelectionHolder() {
		selectionHolder = new SideBar(this);
		selectionHolder.setMinWidth(200);
		selectionHolder.getStyleClass().setAll(DEFAULT);
		// TODO add list of selections
		return selectionHolder;
	}

	public NotificationHolder buildNotificationHolder() {
		if (fadeTime <= 0)
			fadeTime = 1000;
		notifications = new NotificationHolder();
		notifications.setFadeTime(fadeTime);
		notifications.setMaxSize(0, 0);
		StackPane.setAlignment(notifications, Pos.BOTTOM_RIGHT);
		return notifications;
	}

	public GridPane buildFocusPane() {
		focusPane = new GridPane();
		focusPane.getStyleClass().setAll(DEFAULT);
		// TODO add list of pinned info cards
		return focusPane;
	}

	public Pane getMainPane() {
		return mainPane;
	}
	
	private void updateWidgetPanels(){
		
	}
}
