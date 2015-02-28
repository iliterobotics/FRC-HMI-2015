package org.ilite.uitools.widget.toteLiftState;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import org.ilite.uitools.widget.toteLiftState.skin.ToteLiftStateSkin;

public class ToteLiftState extends Control{
	public static final int STATE_STOP = 0;
	public static final int STATE_RESET = 1;
	public static final int STATE_TOTE = 2;
	public static final int STATE_RAISING = 3;
	public static final int STATE_LOWERING = 4;
	
	private IntegerProperty stateValue;
	
	private double size;
	
	public ToteLiftState(IntegerProperty data, double size){
		stateValue = data;
		this.size = size;
		stateValue.addListener(observable -> updateSkin());
		setOnMouseClicked(observable -> stateValue.set(stateValue.get() + 1));
	}
	
	private void updateSkin(){
		((ToteLiftStateSkin)getSkin()).updateImage(stateValue.get());
	}
	
	public Skin<ToteLiftState> createDefaultSkin(){
		return new ToteLiftStateSkin(this, size, size);
	}

}
