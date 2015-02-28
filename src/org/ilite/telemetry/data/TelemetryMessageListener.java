package org.ilite.telemetry.data;

import org.ilite.ui.y2015.DriverStationDriver;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public interface TelemetryMessageListener 
{	
	public static void receivedMessage(TelemetryMessage msg)
	{
		DriverStationDriver.update(msg);
	}
}
