package org.ilite.ui.diagnostics;

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
	private int width;
	
	public WidgetPanel(Manager m){
		manager = m;
	}
	
	public void reMake(int width, int height){
		getChildren().removeAll(getChildren());
		
		VBox main = new VBox();
		main.setMaxSize(width, height);
		main.setMinSize(width, height);
		BorderPane top = new BorderPane();
		top.setMaxSize(width, height / 5);
		HBox widgetBox = new HBox();
		widgetBox.setMaxSize(width, height / 5 * 3);
		widgetBox.setMinSize(width, height / 5 * 3);
		Text titleText = new Text(manager.getName());
		titleText.setFont(new Font(null, 20));
		Text descriptionText = new Text(manager.getDesc());
		descriptionText.setFont(new Font(null, height / 10));

		ImageView removeImg = new ImageView(
				"/org/ilite/uitools/widget/img/RemoveImage.png");
		removeImg.fitHeightProperty().set(height / 5);
		removeImg.fitWidthProperty().set(height / 5);
		Button remove = new Button("", removeImg);
		ToggleButton pin = new ToggleButton("PinImage.png");
		HBox buttons = new HBox();
		buttons.getChildren().addAll(pin, remove);

		top.setCenter(titleText);
		top.setRight(buttons);

		main.getStyleClass().setAll(UIManager.DEFAULT);
		top.getStyleClass().setAll(UIManager.DEFAULT);
		widgetBox.getStyleClass().setAll(UIManager.DEFAULT);
		main.getChildren().addAll(top, widgetBox, descriptionText);

		this.height = height;
		this.width = width;

		getChildren().add(main);
		
		buildWidgets(widgetBox);
	}
	
	public void buildWidgets(HBox box){
		box.getChildren().removeAll(box.getChildren());
		box.getChildren().addAll(manager.buildWidgets(width, height / 5 * 2));
	}
	
	public Manager getManager(){
		return manager;
	}

}
