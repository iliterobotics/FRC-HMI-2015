package org.ilite.ui.y2015;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SectionPanel extends GridPane 
{
	public SectionPanel(Text title, Text description)
	{
		add(title, 0, 0);
		add(description, 0, 3);
	}
	
	public SectionPanel(Text title)
	{
		add(title, 0, 0);
	}
}
