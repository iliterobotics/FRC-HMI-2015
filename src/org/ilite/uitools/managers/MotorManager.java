package org.ilite.uitools.managers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;

import org.ilite.telemetry.data.y2015.EData2015;
import org.ilite.uitools.widget.chart.ScrollingChart;
import org.ilite.uitools.widget.gauge.Gauge;

public class MotorManager extends Manager {
	private DoubleProperty data;

	public MotorManager(EData2015 dataType) {
		super(dataType);
		data = new SimpleDoubleProperty(0);
	}

	public DoubleProperty getData() {
		return data;
	}

	public Node[] buildWidgets(int width, int height) {
		Node[] nodes = new Node[2];
		Stop[] stops = {new Stop(0.33, Color.GREEN), new Stop(0.66, Color.YELLOW), new Stop(1.0, Color.RED)};
		nodes[0] = new Gauge(data, height, 0.0, 1.0, stops);
		nodes[1] = new ScrollingChart(data, width - height - 10, height, 0, 1);
		
		setData(0.5);
		return nodes;
	}
	public String getName(){
		if(dataType == null || dataType.getDisplayLabel() == null){
			return "Motor NULL NAME";
		}
		return "Motor " + dataType.getDisplayLabel();
	}
	public String getDesc(){
		return "TEMP_DESC";
	}

	@Override
	public void setData(Double newData)
	{
		data.setValue(newData);
	}
}
