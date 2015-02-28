package org.ilite.uitools.widget.toteLiftState;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;

import org.ilite.uitools.widget.toteLiftState.skin.ToteLiftStateSkin;

public class ToteLiftState extends Control{
	public static final int STATE_STOP = 0;
	public static final int STATE_RESET = 1;
	public static final int STATE_TOTE = 2;
	public static final int STATE_RAISING = 3;
	public static final int STATE_LOWERING = 4;
	
	private IntegerProperty stateValue;
	
	private double size;
	private boolean tote;
	
	public ToteLiftState(IntegerProperty data, double size, boolean tote){
		if(data == null)
			data = new SimpleIntegerProperty();
		stateValue = data;
		this.size = size;
		this.tote = tote;
		stateValue.addListener(observable -> updateSkin());
	}
	
	private void updateSkin(){
		((ToteLiftStateSkin)getSkin()).updateImage(stateValue.get());
	}
	
	public boolean isTote(){
		return tote;
	}
	
	public Skin<ToteLiftState> createDefaultSkin(){
		return new ToteLiftStateSkin(this, size, size);
	}

}
