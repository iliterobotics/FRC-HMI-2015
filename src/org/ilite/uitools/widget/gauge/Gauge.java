package org.ilite.uitools.widget.gauge;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;

public class Gauge extends Control {

	DoubleProperty value;
	double _value;
	DoubleProperty size;
	double _size;
	DoubleProperty max;
	double _max;
	DoubleProperty min;
	double _min;

	public Gauge() {
		_size = 200;
		_max = 1000;
		_min = 0;
		init();
	}
	
	private void init(){
		this.setOnMousePressed(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				valueProperty().set(valueProperty().get() + 50);
				System.out.println(valueProperty().get());
			}
			
		});
		
		value                    = new DoublePropertyBase(_value) {
            @Override protected void invalidated() {
                set(clamp(getMinValue(), getMaxValue(), get()));
            }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "value"; }
        };
        min                    = new DoublePropertyBase(_min) {
            @Override protected void invalidated() {
                set(getMinValue());
            }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "minValue"; }
        };
        max                    = new DoublePropertyBase(_max) {
            @Override protected void invalidated() {
                set(getMaxValue());
            }
            @Override public Object getBean() { return this; }
            @Override public String getName() { return "maxValue"; }
        };
        
	}

	public Skin<Gauge> createDefaultSkin() {

		return new GaugeSkin(this);

	}

	public DoubleProperty valueProperty() {
		if (value == null) {
			value = new SimpleDoubleProperty(this, "value", _value);
		}
		return value;
	}
	public DoubleProperty sizeProperty() {
		if (size == null) {
			size = new SimpleDoubleProperty(this, "size", _size);
		}
		return size;
	}
	
	public DoubleProperty maxProperty() {
		if (max == null) {
			max = new SimpleDoubleProperty(this, "max", _max);
		}
		return max;
	}
	public DoubleProperty minProperty() {
		if (min == null) {
			min = new SimpleDoubleProperty(this, "min", _min);
		}
		return min;
	}
	
	public double getMinValue(){
		return minProperty().get();
	}
	public double getValue(){
		return valueProperty().get();
	}
	public double getMaxValue(){
		return maxProperty().get();
	}
	// ***TEMPORARY***
	public void setValue(double d){
		_value = d;
	}
	
	private double clamp(final double MIN_VALUE, final double MAX_VALUE, final double VALUE) {
        if (VALUE < MIN_VALUE) return MIN_VALUE;
        if (VALUE > MAX_VALUE) return MAX_VALUE;
        return VALUE;
    }
}
