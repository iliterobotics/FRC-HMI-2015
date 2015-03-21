package org.ilite.telemetry.data;

import javafx.application.Platform;

import org.ilite.ui.y2015.DriverStationDriver;
import org.usfirst.frc.team1885.robot.comms.RobotInfoMessage;

public interface TelemetryMessageListener 
{	
	public static void receivedMessage(RobotInfoMessage msg)
	{
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				DriverStationDriver.update(msg);
				
			}
			
		});
		
	}
}
