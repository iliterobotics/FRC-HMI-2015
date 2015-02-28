package org.ilite.ui.y2015;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import org.ilite.uitools.widget.toteLiftState.ToteLiftState;

public class AlignmentView {
	public static final int PANEL_WIDTH = 320;
	public static final int PANEL_HEIGHT = 240;

	private static StackPane alignmentView = new StackPane();

	public static StackPane generateAlignmentView() {
		alignmentView.setMinHeight(PANEL_HEIGHT);
		alignmentView.setMaxHeight(PANEL_HEIGHT);
		alignmentView.setMinWidth(PANEL_WIDTH);
		alignmentView.setMaxWidth(PANEL_WIDTH);
		
		GridPane icons = new GridPane();
		icons.setMinSize(PANEL_WIDTH, PANEL_HEIGHT);

		alignmentView.setBackground(new Background(new BackgroundFill(Color
				.web("#FFFF00"), CornerRadii.EMPTY, Insets.EMPTY)));

		ToteLiftState toteState = new ToteLiftState(DriverStationDriver.getToteStateProperty(), 75, true);
		ToteLiftState recycleState = new ToteLiftState(DriverStationDriver.getRecycleStateProperty(), 75, false);

		StackPane.setAlignment(toteState, Pos.TOP_RIGHT);
		StackPane.setAlignment(recycleState, Pos.BOTTOM_LEFT);

		icons.add(toteState, 0, 0);
		icons.add(recycleState, 1, 0);
		alignmentView.getChildren().addAll(icons);

		return alignmentView;
	}

}
