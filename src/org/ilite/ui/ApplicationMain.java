package org.ilite.ui;
import org.ilite.ui.y2015.UIBuilder;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationMain extends Application
{
	public void start(Stage stage)
	{
		UIBuilder.generateUI(stage);
	}
	
}
