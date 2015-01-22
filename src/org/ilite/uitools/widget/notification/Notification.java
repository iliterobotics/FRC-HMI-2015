package org.ilite.uitools.widget.notification;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class Notification extends Control{

	private String text;
	private int duration;
	
	public Notification(String text, int milisDuration){
		this.text = text;
		duration = milisDuration;
	}
	public Skin<Notification> createDefaultSkin(){
		return new NotificationSkin(this); 
	}
	
	public String getText(){
		return text;
	}
	
	public int getDuration(){
		return duration;
	}
	
}
