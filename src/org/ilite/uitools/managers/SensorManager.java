package org.ilite.uitools.managers;

import org.ilite.telemetry.data.y2015.EData2015;
import org.ilite.uitools.widget.chart.DataChart;
import org.ilite.uitools.widget.chart.ScrollingChart;
import org.ilite.uitools.widget.gauge.Gauge;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;

public class SensorManager extends Manager {
	private DoubleProperty data = new SimpleDoubleProperty();
	
	private int min;
	private int max;

	public SensorManager(EData2015 dataType, int min, int max) {
		super(dataType);
		this.min = min;
		this.max = max;
		data.setValue(0);
	}

	public DoubleProperty getData() {
		return data;
	}
	
	@Override
	public String getName() {
		
		if(dataType == null || dataType.getDisplayLabel() == null){
			return "Sensor NULL NAME";
		}
		return "Sensor " + dataType.getDisplayLabel();
	}

	@Override
	public String getDesc() {
		return "A sensor";
	}

	@Override
	public void setData(Double newData) 
	{
		data.setValue(newData);
	}

	@Override
	public Node[] buildWidgets(int width, int height) {
		Node[] nodes = new Node[2];
		nodes[0] = new Gauge(data, height, min, max);
		nodes[1] = new ScrollingChart(data, width - height - 10, height, min, max);
		return nodes;
	}
}
