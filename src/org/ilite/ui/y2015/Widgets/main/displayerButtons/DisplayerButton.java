package org.ilite.ui.y2015.Widgets.main.displayerButtons;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class DisplayerButton extends Control{
	public DisplayerButton(){
		
	}
	
	public Skin<DisplayerButton> createDefaultSkin(){
		return new DisplayerButtonSkin(this);
	}
}
