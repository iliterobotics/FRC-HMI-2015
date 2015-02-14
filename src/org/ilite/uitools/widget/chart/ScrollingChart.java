package org.ilite.uitools.widget.chart;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class ScrollingChart extends Control {

	private List<Double> xVals;
	private List<Double> yVals;

	private DoubleProperty property;

	private double max;
	private double min;
	private int width;
	private int height;

	public ScrollingChart(DoubleProperty property, int width, int height, int min, int max) {
		this.property = property;
		this.max = max;
		this.min = min;
		this.width = width;
		this.height = height;
		xVals = new ArrayList<Double>();
		yVals = new ArrayList<Double>();
		init();
	}

	private void init() {

	}

	public List<Double> getxVals() {
		return xVals;
	}

	public List<Double> getyVals() {
		return yVals;
	}
	
	public double getMaxX(){
		return max;
	}
	public double getMinY(){
		return min;
	}

	public Skin<ScrollingChart> createDefaultSkin(){
		return new DataChartSkin(this, width, height);
	}
	
	public void addPoint(double x, double y){
		if(getSkin() == null){
			setSkin(createDefaultSkin());
		}
		xVals.add(x);
		yVals.add(clamp(min, max, y) * -1.0);
		((DataChartSkin)getSkin()).updateLine();
	}
	
	private double clamp(final double MIN_VALUE, final double MAX_VALUE,
			final double VALUE) {
		if (VALUE < MIN_VALUE)
			return MIN_VALUE;
		else if (VALUE > MAX_VALUE)
			return MAX_VALUE;
		else return VALUE;
	}
}
