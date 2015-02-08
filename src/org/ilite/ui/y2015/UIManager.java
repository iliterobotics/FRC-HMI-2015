package org.ilite.ui.y2015;

import java.util.ArrayList;
import java.util.List;

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
 * 
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

	private List<WidgetPanel> widgetPanels;

	public static final String DEFAULT = "mainBorderPane";

	public UIManager() {
		widgetPanels = new ArrayList<WidgetPanel>();
		buildMainPane();
	}

	public boolean addWidgetPanel(Manager m) {
		if (widgetPanels.size() < 9) {
			widgetPanels.add(new WidgetPanel(m));
			updateWidgetPanels();
			return true;
		} else
			return false;
	}

	public boolean removeWidgetPanel(Manager m) {
		for (WidgetPanel wp : widgetPanels) {
			if (wp.getManager().equals(m)) {
				widgetPanels.remove(wp);
				updateWidgetPanels();
				return true;
			}
		}
		return false;
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

	private void updateWidgetPanels() {
		focusPane.getChildren().removeAll(focusPane.getChildren());
		int size = widgetPanels.size();
		switch (size) {
		case 0:
			break;
		case 1:
			focusPane.add(widgetPanels.get(0), 0, 0);
			widgetPanels.get(0).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight());
			break;
		case 2:
			focusPane.add(widgetPanels.get(0), 0, 0);
			focusPane.add(widgetPanels.get(1), 0, 1);

			widgetPanels.get(0).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			widgetPanels.get(1).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			break;
		case 3:
			focusPane.add(widgetPanels.get(0), 0, 0);
			focusPane.add(widgetPanels.get(1), 0, 1);
			focusPane.add(widgetPanels.get(2), 1, 0);

			widgetPanels.get(0).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			widgetPanels.get(1).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			widgetPanels.get(2).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			break;
		case 4:
			focusPane.add(widgetPanels.get(0), 0, 0);
			focusPane.add(widgetPanels.get(1), 0, 1);
			focusPane.add(widgetPanels.get(2), 1, 0);
			focusPane.add(widgetPanels.get(3), 1, 1);

			widgetPanels.get(0).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			widgetPanels.get(1).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			widgetPanels.get(2).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			widgetPanels.get(3).reMake((int) focusPane.getWidth(),
					(int) focusPane.getHeight() >> 1);
			break;
		}
	}
}
