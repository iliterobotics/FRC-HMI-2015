package org.ilite.uitools.managers;

import javafx.scene.Node;

<<<<<<< HEAD
import org.ilite.telemetry.data.ETelemetryType;
=======
>>>>>>> 1bdb5335d2951eefa71e32741016dc5045d99f58
import org.ilite.telemetry.data.y2015.EData2015;

public abstract class Manager {
	private EData2015 dataType;
	
<<<<<<< HEAD
=======
	public Manager(EData2015 eData){
		dataType = eData;
	}
	
	public EData2015 getDataType(){
		return dataType;
	}
	
>>>>>>> 1bdb5335d2951eefa71e32741016dc5045d99f58
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

