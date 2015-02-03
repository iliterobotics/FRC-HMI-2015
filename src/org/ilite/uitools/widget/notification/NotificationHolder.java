package org.ilite.uitools.widget.notification;

import javafx.scene.layout.VBox;

public class NotificationHolder extends VBox{
	
	private int fadeTime;
	
	public void setFadeTime(int t){
		fadeTime = t;
	}
	
	public void notify(String notification){
		if(fadeTime <= 0) fadeTime = 1000;
		getChildren().add(new Notification(notification, fadeTime));
	}
	
	public void suicide(Notification n){
		getChildren().remove(n);
	}

}
