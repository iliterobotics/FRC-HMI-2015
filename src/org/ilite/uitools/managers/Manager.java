package org.ilite.uitools.managers;

import javafx.scene.Node;

import org.ilite.telemetry.data.y2015.EData2015;

public abstract class Manager {
	private EData2015 dataType;
	
	public Manager(EData2015 eData){
		dataType = eData;
	}
	
	public EData2015 getDataType(){
		return dataType;
	}
	
	public abstract Node[] buildWidgets(int size);
	public abstract String getName();
	public abstract String getDesc();
}

