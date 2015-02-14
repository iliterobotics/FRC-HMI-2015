package org.ilite.telemetry.data;

import org.ilite.ui.diagnostics.SideBar;
import org.ilite.ui.table.TableManager;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public interface TelemetryMessageListener 
{	
	public static void receivedMessage(TelemetryMessage msg)
	{
		SideBar.update(msg);
		TableManager.getUpdate(msg);
	}
}
