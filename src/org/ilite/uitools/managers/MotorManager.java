package org.ilite.uitools.managers;

import org.ilite.uitools.widget.chart.DataChart;
import org.ilite.uitools.widget.gauge.Gauge;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

public class MotorManager extends Manager {
	private DoubleProperty data = new SimpleDoubleProperty();

	public MotorManager() {
		data.setValue(0);
	}

	public DoubleProperty getData() {
		return data;
	}

	public void setData(double newData) {
		data.setValue(newData);
	}

	@Override
	public Node[] buildWidgets(int size) {
		Node[] nodes = new Node[2];
		nodes[0] = new Gauge(data, size, 0.0, 1.0);
		nodes[1] = new DataChart(data, size);
		return nodes;
	}
	public String getName(){
		return "TEMP_NAME";
	}
	public String getDesc(){
		return "TEMP_DESC";
	}
}
