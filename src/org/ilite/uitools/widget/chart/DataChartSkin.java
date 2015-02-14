package org.ilite.uitools.widget.chart;

import java.util.List;

import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

public class DataChartSkin extends SkinBase<ScrollingChart> implements
		Skin<ScrollingChart> {

	private int height;
	private int width;

	private StackPane mainPane;
	private Polyline polyline;
	private Rectangle background;

	protected DataChartSkin(ScrollingChart chart, int width, int height) {
		super(chart);

		this.height = height;
		this.width = width;

		initGraphics();
	}

	private void initGraphics() {
		mainPane = new StackPane();
		mainPane.setMinSize(width, height);
		mainPane.setMaxSize(width, height);

		background = new Rectangle(width, height);

		background.setFill(Color.web("#111111"));

		polyline = new Polyline();
		polyline.setStroke(Color.GREEN);
		polyline.setStrokeWidth(2);
		
		mainPane.getChildren().addAll(background, polyline);
		getChildren().add(mainPane);
		updateLine();
	}

	public void updateLine() {
		List<Double> xVals = ((ScrollingChart)getSkinnable()).getxVals();
		List<Double> yVals = ((ScrollingChart) getSkinnable()).getyVals();
		int xIndex = (int) (background.getWidth() / yVals.size());
		polyline.getPoints().removeAll(polyline.getPoints());
		for (int i = 0; i < yVals.size(); i++) {
			polyline.getPoints().add((double) (xVals.get(i) * xIndex));
			polyline.getPoints().add((yVals.get(i)/ ((ScrollingChart)getSkinnable()).getMaxX())* height);
		}
	}
}
