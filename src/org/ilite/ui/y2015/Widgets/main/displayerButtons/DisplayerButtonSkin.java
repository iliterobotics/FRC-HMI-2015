package org.ilite.ui.y2015.Widgets.main.displayerButtons;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;

public class DisplayerButtonSkin extends SkinBase<DisplayerButton>{
	
	private StackPane contents = new StackPane();

	public DisplayerButtonSkin(DisplayerButton control) {
		super(control);
		initGraphics();
	}

	private void initGraphics() {
		
		contents.setPrefSize(200, 50);
		
	}
	
}
