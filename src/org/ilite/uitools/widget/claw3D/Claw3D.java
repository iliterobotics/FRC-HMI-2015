package org.ilite.uitools.widget.claw3D;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class Claw3D extends Control {
	
	public Claw3D(DoubleProperty x, DoubleProperty y, DoubleProperty z){
		
	}
	
	public Skin<Claw3D> createDefaultSkin(){
		return new Claw3DSkin(this);
	}

}
