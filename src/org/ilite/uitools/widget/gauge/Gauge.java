package org.ilite.uitools.widget.gauge;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Stop;

public class Gauge extends Control {

	
	double value;
	DoubleProperty valueProperty;
	double size;
	DoubleProperty sizeProperty;
	double max;
	DoubleProperty maxProperty;
	double min;
	DoubleProperty minProperty;
	
	private Stop[] colorStops;

	public Gauge(DoubleProperty valueProp, int size, double min, double max) {
		this.size = size;
		sizeProperty = new SimpleDoubleProperty(size);
		
		this.max = max;
		maxProperty = new SimpleDoubleProperty(max);
		
		this.min = min;
		minProperty = new SimpleDoubleProperty(min);
		
		valueProperty = new SimpleDoubleProperty(min);
		
		valueProp.addListener(observable -> setValue(valueProp.get()));

		init();
	}
	public Gauge(DoubleProperty valueProp, int size, double min, double max, Stop[] stops) {
		this(valueProp, size, min, max);
		colorStops = stops;
	}

	private void init() {
//		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent arg0) {
//				setValue( valueProperty.get() + 0.1 );
//				System.out.println(valueProperty.get());
//			}
//
//		});

		

	}

	public Skin<Gauge> createDefaultSkin() {

		return new GaugeSkin(this);

	}

	public double getMinValue() {
		return minProperty.get();
	}

	public double getValue() {
		return valueProperty.get();
	}

	public double getMaxValue() {
		return maxProperty.get();
	}
	
	public double getSize(){
		return sizeProperty.get();
	}
	
	public DoubleProperty getMinValueProperty() {
		return minProperty;
	}

	public DoubleProperty getValueProperty() {
		return valueProperty;
	}

	public DoubleProperty getMaxValueProperty() {
		return maxProperty;
	}
	
	public DoubleProperty getSizeProperty(){
		return sizeProperty;
	}
	public void setValue(double d) {
		valueProperty.set(clamp(min, max, d));
	}
	
	public void setSize(double d){
		sizeProperty.set(d);
	}
	
	private double clamp(final double MIN_VALUE, final double MAX_VALUE,
			final double VALUE) {
		if (VALUE < MIN_VALUE)
			return MIN_VALUE;
		else if (VALUE > MAX_VALUE)
			return MAX_VALUE;
		else return VALUE;
	}
	public Stop[] getColorStops() {
		return colorStops;
	}
}
