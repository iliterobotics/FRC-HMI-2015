package org.ilite.ui;
import javafx.application.Application;
import javafx.stage.Stage;

import org.ilite.telemetry.data.ServerConnector;
import org.ilite.ui.y2015.ViewBuilder;

public class ApplicationMain extends Application
{
	@Override
	public void start(Stage stage)
	{
		//UIBuilder.generateUI(stage);
//		Table.buildSimpleTable(stage);
		ViewBuilder.generateUI(stage);
		
		ServerConnector.getInstance();
	}
	
	
}
