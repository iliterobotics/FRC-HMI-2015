package org.ilite.uitools.widget.gauge;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;

public class Gauge extends Control {

	
	double value;
	DoubleProperty valueProperty;
	double size;
	DoubleProperty sizeProperty;
	double max;
	DoubleProperty maxProperty;
	double min;
	DoubleProperty minProperty;

	public Gauge() {
		size = 200;
		sizeProperty = new SimpleDoubleProperty(size);
		
		max = 1000;
		maxProperty = new SimpleDoubleProperty(max);
		
		min = 0;
		minProperty = new SimpleDoubleProperty(min);
		
		valueProperty = new SimpleDoubleProperty(0);

		init();
	}

	private void init() {
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setValue(valueProperty.get() + 50);
				System.out.println(valueProperty.get());
			}

		});

		

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
		if (VALUE > MAX_VALUE)
			return MAX_VALUE;
		return VALUE;
	}
}
