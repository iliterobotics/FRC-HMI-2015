package org.ilite.uitools.widget.chart;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class DataChart extends StackPane {
	
	private LineChart<Number, Number> chart;
	@SuppressWarnings("rawtypes")
	private XYChart.Series series;
	int timeSig;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DataChart(DoubleProperty pinger, int width, int height) {
		Axis<Number> xAxis = new NumberAxis();
		Axis<Number> yAxis = new NumberAxis();
		chart = new LineChart(xAxis, xAxis);
		chart.setMaxSize(width, height);
		chart.setMinSize(width, height);
		chart.setAlternativeColumnFillVisible(false);
		chart.setAlternativeRowFillVisible(false);
		xAxis.setLabel("Time");
		yAxis.setLabel("Value");
		series = new XYChart.Series();
		pinger.addListener(observable -> addData(pinger.get()));
		chart.getData().add(series);
		
		chart.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				pinger.set( pinger.get() + 0.1 );
			}

		});
		this.getChildren().addAll(chart);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addData(double value) {
		timeSig ++;
		series.getData().add(new XYChart.Data(timeSig, value));
	}

}
