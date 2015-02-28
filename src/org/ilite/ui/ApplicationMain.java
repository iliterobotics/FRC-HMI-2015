package org.ilite.ui;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import org.ilite.ui.diagnostics.UIBuilder;
import org.ilite.ui.table.Table;
import org.ilite.ui.y2015.ViewBuilder;
public class ApplicationMain extends Application
{
//	private static final AudioClip ALERT_AUDIOCLIP = new AudioClip("/beep.mp3");
	@Override
	public void start(Stage stage)
	{
		//UIBuilder.generateUI(stage);
		//Table.buildSimpleTable(stage);
		ViewBuilder.generateUI(stage);

//		UIBuilder.generateUI(stage);
		
//		AudioClip beeper = new AudioClip("file:///D:/FRC%20GUI/frc-hmi/src/beep.mp3");
//		beeper.setVolume(1.0);
////		beeper.setRate();
//		beeper.setCycleCount(5);
//		beeper.play();
		
//		ServerConnector.getInstance();
	}
	
	
}
