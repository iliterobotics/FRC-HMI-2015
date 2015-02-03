package org.ilite.uitools.widget.chart;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DataChart extends LineChart<Number, Number> {

	public static Axis<Number> xAxis = new NumberAxis();
	public static Axis<Number> yAxis = new NumberAxis();
	@SuppressWarnings("rawtypes")
	XYChart.Series series;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataChart() {
		super(xAxis, yAxis);
		xAxis.setLabel("Time");
		yAxis.setLabel("Value");
		series = new XYChart.Series();
		getData().add(series);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addData(int timeSigniture, int value) {
		series.getData().add(new XYChart.Data(value, timeSigniture));
	}

}
