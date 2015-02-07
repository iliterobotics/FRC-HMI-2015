package org.ilite.ui.y2015;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import org.ilite.uitools.widget.gauge.Gauge;
import org.ilite.uitools.widget.notification.NotificationHolder;
import org.ilite.uitools.widget.toggle.ToggleButton;
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
	
	private int numbPanels;

	private int fadeTime;

	private static final String DEFAULT = "mainBorderPane";

	public UIManager() {
		numbPanels = 0;
		buildMainPane();
		Gauge gauge = new Gauge();
		gauge.setSize(50);
		addWidgetPanel(buildWidgetHolder(800, 200, "hey", "hey", null));
	}

	public void addWidgetPanel(Node n) {
		focusPane.add(n, 0, 0);
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
		selectionHolder = new VBox();
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

	

	public static GridPane buildWidget(Node widget) {
		GridPane selection = new GridPane();
		selection.add(widget, 0, 0);
		Button widgetPin = new Button("", new ImageView(new Image(
				"/org/ilite/uitools/widget/img/WidgetPinImage.png")));
		widgetPin.setMinWidth(15);
		widgetPin.setMinHeight(15);
		selection.add(widgetPin, 1, 0);
		//Button widgetRemove = new Button("", new ImageView(new Image(
		//		"/org/ilite/uitools/widget/img/WidgetRemoveImage.png")));
		//widgetRemove.setMinWidth(15);
		//widgetRemove.setMinHeight(15);
		ToggleButton widgetRemove = new ToggleButton();
		selection.add(widgetRemove, 1, 1);
		return selection;
	}

	public Pane buildWidgetHolder(int width, int height, String title,
			String description, Node[] widgets) {
		VBox main = new VBox();
		BorderPane top = new BorderPane();
		GridPane widgetBox = new GridPane();
		Text titleText = new Text();
		titleText.setText(title);
		titleText.setFont(new Font(null, height >> 2));
		Text descriptionText = new Text();
		descriptionText.setText(description);
		descriptionText.setFont(new Font(null, height >> 2));
		ImageView pinImg = new ImageView(
				"/org/ilite/uitools/widget/img/PinImage.png");
		ImageView removeImg = new ImageView(
				"/org/ilite/uitools/widget/img/RemoveImage.png");
		pinImg.fitHeightProperty().set(height >> 2);
		pinImg.fitWidthProperty().set(height >> 2);
		removeImg.fitHeightProperty().set(height >> 2);
		removeImg.fitWidthProperty().set(height >> 2);
		Button remove = new Button("", removeImg);
		ToggleButton pin = new ToggleButton("PinImage.png");
		HBox buttons = new HBox();
		buttons.getChildren().addAll(pin, remove);

		widgetBox.setMinHeight((height >> 4) * 5);
		widgetBox.setMinWidth(width);
		widgetBox.setVgap(5);
		widgetBox.setHgap(5);
		if (widgets != null) {
			for (int i = 0; i < widgets.length; i++) {
				widgetBox.add(widgets[i], i % 3, i / 3);
			}
		}

		top.setCenter(titleText);
		top.setRight(buttons);
		
		main.setMaxSize(width, height);

		main.getStyleClass().setAll(DEFAULT);
		top.getStyleClass().setAll(DEFAULT);
		widgetBox.getStyleClass().setAll(DEFAULT);
		main.getChildren().addAll(top, widgetBox, descriptionText);

		return main;
	}

	public Pane getMainPane() {
		return mainPane;
	}
}
