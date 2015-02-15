package org.ilite.uitools.widget.gauge;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GaugeSkin extends SkinBase<Gauge> {

	private Polygon arrow;
	private Arc top;
	private Rotate arrowRotate;
	private StackPane mainPane;
	
	private List<Arc> overlays;
	
	private Text min;
	private Text max;
	
	private Timeline rotationAnimationTimeline;

	protected GaugeSkin(Gauge control) {
		super(control);
		initGraphics();
		addListeners();
	}

	private void initGraphics() {
		mainPane = new StackPane();
		top = new Arc();
		top.setStartAngle(180.0f);
		top.setLength(-180.0f);
		top.setType(ArcType.ROUND);
		top.getStyleClass().setAll("gauge");

		arrowRotate = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);

		arrow = new Polygon();
		arrow.getStyleClass().setAll("arrow");
		arrow.getPoints().addAll(2.0, 0.0, 0.0, -20.0, -2.0, 0.0);
		arrow.getTransforms().add(arrowRotate);
		
		min = new Text(getSkinnable().valueProperty.get() + "");
		StackPane.setAlignment(top, Pos.CENTER);
		mainPane.getChildren().add(top);
		Stop[] stops = ((Gauge)getSkinnable()).getColorStops();
		overlays = new ArrayList<Arc>();
		if(stops != null && stops.length > 0){
			for(int i = stops.length - 1; i >= 0; i --){
				Arc arc = new Arc();
				arc.setStartAngle(top.getStartAngle());
				arc.setLength((stops[i].getOffset()) * -180);
				arc.setType(ArcType.ROUND);
				arc.getStyleClass().setAll("gaugeTest");
				StackPane.setAlignment(arc, Pos.BOTTOM_LEFT);
				arc.setFill(stops[i].getColor());
				overlays.add(arc);
			}
		}
		mainPane.getChildren().addAll(overlays);
		mainPane.getChildren().addAll(arrow, min);
		getChildren().add(mainPane);
		
		rotationAnimationTimeline = new Timeline();
		
		resize();
		refreshArrow();
	}

	public void resize() {
		int width = (int) getSkinnable().getSize();
		int radius = width / 2;
		top.setCenterX(radius);
		top.setCenterY(radius);
		top.setRadiusX(radius);
		top.setRadiusY(radius);
		mainPane.setMaxHeight(radius);
		for(Arc arc : overlays){
			arc.setCenterX(radius);
			arc.setCenterY(radius);
			arc.setRadiusX(radius);
			arc.setRadiusY(radius);
			System.out.println(arc.getCenterX());
		}

		arrow.getPoints().addAll((width / 40.0), 0.0, 0.0, (radius * -1.0),
				(width / -40.0), 0.0);
	}

	public void refreshArrow() {
		rotationAnimationTimeline.stop();
		rotationAnimationTimeline.getKeyFrames().removeAll(rotationAnimationTimeline.getKeyFrames());
		double max = getSkinnable().getMaxValue();
		double min = getSkinnable().getMinValue();
		double current = getSkinnable().getValue();
		
		double newAngle =  (current / (max - min)) * 180 - 90;
		
		rotationAnimationTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(arrowRotate.angleProperty(), newAngle)));
		rotationAnimationTimeline.play();
		
		String currentString = current + "";
		this.min.setText(currentString.substring(0, currentString.length() > 5 ? 5 : currentString.length()));
	}
	
	private void addListeners(){
		getSkinnable().getValueProperty().addListener(observable -> refreshArrow());
		getSkinnable().getMinValueProperty().addListener(observable -> refreshArrow());
		getSkinnable().getMaxValueProperty().addListener(observable -> refreshArrow());
		getSkinnable().getSizeProperty().addListener(observable -> resize());
		
	}

}
