package org.ilite.telemetry.data;

import org.ilite.ui.diagnostics.ManagerDriver;
import org.ilite.ui.table.TableManager;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public interface TelemetryMessageListener 
{	
	public static void receivedMessage(TelemetryMessage msg)
	{
		ManagerDriver.update(msg);
		TableManager.getUpdate(msg); 
	}
}
