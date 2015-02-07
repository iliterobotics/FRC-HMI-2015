package org.ilite.ui.y2015;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.ilite.uitools.widget.toggle.ToggleButton;

public class WidgetPanel extends Pane{
	
	private String title;
	private String description;
	
	public WidgetPanel(){
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

		getChildren().add(main);
	}

}
