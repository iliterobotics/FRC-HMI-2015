package org.ilite.uitools.widget.gauge;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.SkinBase;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GaugeSkin extends SkinBase<Gauge> {

	private Polygon arrow;
	private Arc top;
	private Rotate arrowRotate;
	
	private Timeline rotationAnimationTimeline;

	protected GaugeSkin(Gauge control) {
		super(control);
		initGraphics();
		addListeners();
	}

	private void initGraphics() {
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
		getChildren().addAll(top, arrow);
		
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

		arrow.getPoints().addAll((width / 40.0), 0.0, 0.0, (radius * -1.0),
				(width / -40.0), 0.0);
	}

	public void refreshArrow() {
		rotationAnimationTimeline.stop();
		rotationAnimationTimeline.getKeyFrames().removeAll(rotationAnimationTimeline.getKeyFrames());
		double max = getSkinnable().getMaxValue();
		double min = getSkinnable().getMinValue();
		double current = getSkinnable().getValue();
		
		double newAngle = (current / (max - min)) * 180 - 90;
		
		rotationAnimationTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(arrowRotate.angleProperty(), newAngle)));
		rotationAnimationTimeline.play();
	}
	
	private void addListeners(){
		getSkinnable().getValueProperty().addListener(observable -> refreshArrow());
		getSkinnable().getSizeProperty().addListener(observable -> resize());
		
	}

}
