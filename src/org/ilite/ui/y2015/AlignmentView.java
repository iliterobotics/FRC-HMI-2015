package org.ilite.ui.y2015;

import javafx.scene.layout.StackPane;

public class AlignmentView 
{
	public static final int PANEL_WIDTH = 320;
	public static final int PANEL_HEIGHT = 240;
	
	private static StackPane alignmentView = new StackPane();
	
	public static StackPane generateAlignmentView()
	{
		alignmentView.setMinHeight(PANEL_HEIGHT);
		alignmentView.setMaxHeight(PANEL_HEIGHT);
		alignmentView.setMinWidth(PANEL_WIDTH);
		alignmentView.setMaxWidth(PANEL_WIDTH);
		
		alignmentView.setStyle("-fx-background-color: #DD4351;");
		
		return alignmentView;
	}

}
