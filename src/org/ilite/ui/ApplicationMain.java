package org.ilite.ui;
import javafx.application.Application;
import javafx.stage.Stage;

import org.ilite.ui.table.Table;

public class ApplicationMain extends Application
{
	@Override
	public void start(Stage stage)
	{
		//UIBuilder.generateUI(stage);
		Table.buildSimpleTable(stage);
	}
	
	
}
