package org.ilite.uitools.widget.gauge;

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;

public class Gauge extends Control {

	
	double value;
	double size;
	double max;
	double min;

	public Gauge() {
		size = 200;
		max = 1000;
		min = 0;
		init();
	}

	private void init() {
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setValue(value + 50);
				System.out.println(value);
			}

		});

		

	}

	public Skin<Gauge> createDefaultSkin() {

		return new GaugeSkin(this);

	}

	public double getMinValue() {
		return min;
	}

	public double getValue() {
		return value;
	}

	public double getMaxValue() {
		return max;
	}
	
	public double getSize(){
		return size;
	}

	// ***TEMPORARY***
	public void setValue(double d) {
		value = clamp(min, max, d);
		((GaugeSkin) getSkin()).refreshArrow();
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
