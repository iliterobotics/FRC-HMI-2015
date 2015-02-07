package org.ilite.ui.y2015;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.ilite.uitools.managers.Manager;
import org.ilite.uitools.widget.toggle.ToggleButton;

public class WidgetPanel extends Pane{

	private Manager manager;
	private int height;
	
	public WidgetPanel(Manager m){
		manager = m;
	}
	
	public void reMake(int width, int height){
		getChildren().removeAll(getChildren());
		
		VBox main = new VBox();
		BorderPane top = new BorderPane();
		HBox widgetBox = new HBox();
		Text titleText = new Text(manager.getName());
		titleText.setFont(new Font(null, height >> 2));
		Text descriptionText = new Text(manager.getDesc());
		descriptionText.setFont(new Font(null, height >> 2));

		ImageView removeImg = new ImageView(
				"/org/ilite/uitools/widget/img/RemoveImage.png");
		removeImg.fitHeightProperty().set(height >> 2);
		removeImg.fitWidthProperty().set(height >> 2);
		Button remove = new Button("", removeImg);
		ToggleButton pin = new ToggleButton("PinImage.png");
		HBox buttons = new HBox();
		buttons.getChildren().addAll(pin, remove);

		widgetBox.setSpacing(5);

		top.setCenter(titleText);
		top.setRight(buttons);
		
		main.setMaxSize(width, height);

		main.getStyleClass().setAll(UIManager.DEFAULT);
		top.getStyleClass().setAll(UIManager.DEFAULT);
		widgetBox.getStyleClass().setAll(UIManager.DEFAULT);
		main.getChildren().addAll(top, widgetBox, descriptionText);

		this.height = height;

		getChildren().add(main);
		
		buildWidgets(widgetBox);
	}
	
	public void buildWidgets(HBox box){
		box.getChildren().addAll(manager.buildWidgets(height));
	}

}
