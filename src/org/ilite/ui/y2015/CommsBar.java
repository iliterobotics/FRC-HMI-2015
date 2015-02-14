package org.ilite.ui.y2015;

import javafx.scene.layout.HBox;

public class CommsBar 
{
	private static HBox commBar = new HBox();
	public static final int BAR_HEIGHT = 75;
	
	public static HBox generateCommsBar()
	{
		commBar.setMinHeight(BAR_HEIGHT);
		commBar.setMaxHeight(BAR_HEIGHT);
		commBar.setMinWidth(MainView.PANEL_WIDTH + AlignmentView.PANEL_WIDTH);
		commBar.setMaxWidth(MainView.PANEL_WIDTH + AlignmentView.PANEL_WIDTH);
		
		commBar.setStyle("-fx-background-color: #FF69B4;");
		
		return commBar;
	}
}
