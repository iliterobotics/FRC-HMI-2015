package org.ilite.uitools.widget.notification;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class NotificationSkin extends SkinBase<Notification>{
	
	private FadeTransition fin, ft, fout;
	private StackPane contents;

	protected NotificationSkin(Notification not) {
		super(not);
		initGraphics();
	}

	private void initGraphics() {
		contents = new StackPane();
		Text text = new Text(getSkinnable().getText());
		
		fin = new FadeTransition(Duration.millis(1000));
		fin.setFromValue(0);
		fin.setToValue(1);
		fin.setNode(getSkinnable());
		
		ft = new FadeTransition(Duration.millis(500));
		ft.setFromValue(1);
		ft.setToValue(0.8);
		ft.setNode(getSkinnable());
		ft.setAutoReverse(true);
		ft.setCycleCount((int)(getSkinnable().getDuration() / 500.0));
		
		fout = new FadeTransition(Duration.millis(500));
		fout.setFromValue(1);
		fout.setToValue(0);
		fout.setNode(getSkinnable());
		
		Button xout = new Button();
		xout.setText("X");
		xout.getStyleClass().add("button1");
		StackPane.setAlignment(xout, Pos.TOP_RIGHT);
		xout.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	                fin.stop();
	                ft.stop();
	                fadeOut();
	            }
	        });
		Rectangle backdrop  = new Rectangle();
		backdrop.setWidth(20 + text.getLayoutBounds().getWidth() + 20);
		backdrop.setHeight(50);
		backdrop.setFill(Color.web("#A0A0A0"));
		
		contents.getChildren().addAll(backdrop, text, xout);
		contents.setMaxSize(backdrop.getWidth(), backdrop.getHeight());
		getChildren().add(contents);
		
		fadeIn();
	}
	
	public void fadeIn(){
		
	 
		fin.play();
		
		fin.setOnFinished(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	                ft.play();
	            }
	        });
		ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                fadeOut();
            }
        });
		
		
	}
	
	public void fadeOut(){
	 
		fout.play();
		
		fout.setOnFinished(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Group group = (Group) getSkinnable().getParent();
                group.getChildren().remove(getSkinnable());
            }
        });
	}

}
