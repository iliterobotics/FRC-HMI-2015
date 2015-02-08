package org.ilite.uitools.managers;

import javafx.scene.Node;

import org.ilite.telemetry.data.ETelemetryType;
import org.ilite.telemetry.data.y2015.EData2015;

public abstract class Manager {
	private EData2015 dataType;
	
	public abstract Node[] buildWidgets(int size);
	public abstract String getName();
	public abstract String getDesc();
	public abstract void setData(Double newData);
	
	public void getUpdate()
	{
		if(dataType.getType().equals(ETelemetryType.PNEUMATIC))
		{
			setData(getSolenoid(data.getPortNumber()));
		}
	}
}

