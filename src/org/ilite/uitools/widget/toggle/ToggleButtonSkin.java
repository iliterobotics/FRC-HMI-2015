package org.ilite.uitools.widget.toggle;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ToggleButtonSkin extends SkinBase<ToggleButton>{
	
	public static final Color START_COLOR 			= Color.web("#A0A0A0");
	public static final Color MID_COLOR				= Color.web("#BABABA");
	public static final Color END_COLOR				= Color.web("#A0A0A0");
	
	public static final Color START_COLOR_ACTIVE 	= Color.web("#999999");
	public static final Color MID_COLOR_ACTIVE		= Color.web("#666666");
	public static final Color END_COLOR_ACTIVE		= Color.web("#999999");

	private int width;
	private int height;
	
	private StackPane contents;
	private Rectangle backIcon;
	private Node frontIcon;
	private Circle iconLight;
	
	protected ToggleButtonSkin(ToggleButton e) {
		super(e);
		initGraphics();
	}
	

	private void initGraphics() {
		contents = new StackPane();
		contents.setMaxSize(getSkinnable().getWidth(), getSkinnable().getHeight());
		width = (int) getSkinnable().getWidth();
		height = (int) getSkinnable().getHeight();
		
		frontIcon = getSkinnable().getIcon();
		backIcon = new Rectangle(width + 10, height + 10);
		backIcon.setArcHeight(10);
		backIcon.setArcWidth(10);
		iconLight = new Circle(5);
		StackPane.setAlignment(iconLight, Pos.TOP_RIGHT);
		
		
		contents.getChildren().addAll(backIcon, frontIcon, iconLight);
		getChildren().addAll(contents);
		setActivated(getSkinnable().isActivated());
	}
	
	public void setActivated(boolean state){
		LinearGradient fill;
		if(state){
			fill = new LinearGradient(0, 0, backIcon.getWidth(), backIcon.getHeight(), false, CycleMethod.NO_CYCLE, new Stop[]{
				new Stop(0.0, START_COLOR),
				//new Stop(0.5, MID_COLOR),
				new Stop(1.0, END_COLOR)
			});
			iconLight.setFill(MID_COLOR_ACTIVE);
			iconLight.setEffect(null);
		}
		else{
			fill = new LinearGradient(0, 0, backIcon.getWidth(), backIcon.getHeight(), false, CycleMethod.NO_CYCLE, new Stop[]{
				new Stop(0.0, START_COLOR_ACTIVE),
				//new Stop(0.5, MID_COLOR_ACTIVE),
				new Stop(1.0, END_COLOR_ACTIVE)
			});
			iconLight.setFill(Color.GREEN);
			iconLight.setEffect(new GaussianBlur());
		}
		backIcon.setFill(fill);
	}
	
	

}
