package org.ilite.uitools.widget.gauge;

import javafx.scene.control.SkinBase;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

public class GaugeSkin extends SkinBase<Gauge> {

	private Polygon 	arrow;
	private Arc 		top;
	private Rotate   	arrowRotate;

	protected GaugeSkin(Gauge control) {
		super(control);
		initGraphics();
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
		resize();
		getChildren().addAll(top, arrow);
	}

	public void resize() {
		int width = (int) getSkinnable().sizeProperty().get();
		int radius = width / 2;
		top.setCenterX(radius);
		top.setCenterY(radius);
		top.setRadiusX(radius);
		top.setRadiusY(radius);
		
		arrow.getPoints().addAll((width / 40.0), 0.0, 0.0, (radius * -1.0), (width / -40.0), 0.0);
	}
	
	public void refreshArrow(){
		System.out.println("Event fired!");
		double max 		= getSkinnable().maxProperty().get();
		double min 		= getSkinnable().minProperty().get();
		double current	= getSkinnable().valueProperty().get();
		
		arrowRotate.setAngle((current / (max - min)) * 180 - 90);
	}
	
	public void defineListeners(){
		getSkinnable().sizeProperty().addListener(observable -> resize());
		getSkinnable().valueProperty().addListener(observable -> refreshArrow());
	}

}
