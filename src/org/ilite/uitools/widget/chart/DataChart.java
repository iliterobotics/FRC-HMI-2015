package org.ilite.uitools.widget.chart;

import javafx.beans.property.DoubleProperty;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataChart extends LineChart<Number, Number> {

	private static Axis<Number> xAxis = new NumberAxis();
	private static Axis<Number> yAxis = new NumberAxis();
	@SuppressWarnings("rawtypes")
	XYChart.Series series;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataChart(DoubleProperty pinger, int size) {
		super(xAxis, yAxis);
		this.setMaxSize(size, size);
		this.setMinSize(size, size);
		xAxis.setLabel("Time");
		yAxis.setLabel("Value");
		series = new XYChart.Series();
		pinger.addListener(observable -> addData(pinger.get()));
		getData().add(series);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addData(double value) {
		double timeSignature = System.currentTimeMillis();
		series.getData().add(new XYChart.Data(value, timeSignature));
	}

}
