package org.ilite.ui.y2015;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class DataButton extends Button
{
	private Button internal;
	private boolean state;
	
	public DataButton(String displayName, Boolean status)
	{
		internal = new Button(displayName);
		internal.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event) 
			{
				onClick();				
			}
		});
		state = status;
	}
	
	public void setStatus(boolean active)
	{
		state = active;
	}
	
	public boolean getStatus()
	{
		return state;
	}
	
	public void onClick()
	{
		
	}
	
	
}
