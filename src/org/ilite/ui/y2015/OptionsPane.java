package org.ilite.ui.y2015;

import java.util.ArrayList;
import javafx.scene.control.ScrollPane;
import org.ilite.ui.y2015.DataButton;

public class OptionsPane extends ScrollPane
{
	private ArrayList<DataButton> components;
	
	public OptionsPane()
	{
		components = new ArrayList<DataButton>();
	}

	public ArrayList<DataButton> getComponents()
	{
		return components;
	}
	
	
}
