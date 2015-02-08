package org.ilite.uitools.managers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

public class SensorManager extends Manager {
	private DoubleProperty data = new SimpleDoubleProperty();

	public SensorManager() {
		data.setValue(0);
	}

	public DoubleProperty getData() {
		return data;
	}

	public void setData(double newData) {
		data.setValue(newData);
	}

	public Node[] buildWidgets() {
		return null;
	}

	@Override
	public Node[] buildWidgets(int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}
}
