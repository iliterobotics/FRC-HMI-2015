package org.ilite.uitools.managers;

import javafx.scene.Node;

import org.ilite.telemetry.data.ETelemetryType;

import org.ilite.telemetry.data.y2015.EData2015;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public abstract class Manager {
	private EData2015 dataType;

	public Manager(EData2015 eData){
		dataType = eData;
	}
	public abstract Node[] buildWidgets(int size);
	public abstract String getName();
	public abstract String getDesc();
	public abstract void setData(Double newData);
	
	public void getUpdate(TelemetryMessage msg)
	{
		if(dataType.getType().equals(ETelemetryType.PNEUMATIC))
		{
			setData(msg.getSolenoids(dataType.getPortNumber()));
		}
		else if(dataType.getType().equals(ETelemetryType.RELAY_OUT))
		{
			setData(msg.getRelays(dataType.getPortNumber()));
		}
		else if(dataType.getType().equals(ETelemetryType.ANALOG_IN))
		{
			setData(msg.getAnalogInputs(dataType.getPortNumber()));
		}
		else if(dataType.getType().equals(ETelemetryType.DIGITAL_IN))
		{
			setData(msg.getDigitalInputs(dataType.getPortNumber()));
		}
		else if(dataType.getType().equals(ETelemetryType.PWM_OUT))
		{
			setData(msg.getDigitalOutputs(dataType.getPortNumber()));
		}
	}

}

