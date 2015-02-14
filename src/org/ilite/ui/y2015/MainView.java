package org.ilite.ui.y2015;

import java.awt.Color;
import java.awt.Dimension;

import javafx.embed.swing.SwingNode;
import javafx.scene.layout.StackPane;

import javax.swing.JPanel;

public class MainView 
{
	private static SwingNode node = new SwingNode();
	public static final int PANEL_HEIGHT = 600;
	public static final int PANEL_WIDTH = 800;
	
	public static StackPane generateMainView()
	{
		StackPane pane = new StackPane();
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		
		node.setContent(panel);
		
		pane.setMaxHeight(PANEL_HEIGHT);
		pane.setMaxWidth(PANEL_WIDTH);
		pane.setMinHeight(PANEL_HEIGHT);
		pane.setMinWidth(PANEL_WIDTH);
		
		pane.getChildren().add(node);
	
		return pane;
	}
}
