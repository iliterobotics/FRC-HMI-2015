package org.ilite.ui.y2015;

import java.io.IOException;

import javafx.embed.swing.SwingNode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import javax.swing.SwingUtilities;

import org.ilite.uitools.widget.toteLiftState.ToteLiftState;
import org.ilite.vision.api.system.IVisionSystem;
import org.ilite.vision.api.system.ImageBlender;
import org.ilite.vision.api.system.VisionSystemAPI;
import org.ilite.vision.constants.ECameraType;

public class AlignmentView {
//	public static final int PANEL_WIDTH = 320;
//	public static final int PANEL_HEIGHT = 240;

	public static final int PANEL_WIDTH = 640;
	public static final int PANEL_HEIGHT = 480;
	
	private static StackPane alignmentView = new StackPane();

	public static StackPane generateAlignmentView(double width, double height) {
		alignmentView.setMinHeight(width);
		alignmentView.setMaxHeight(height);
		alignmentView.setMinWidth(width);
		alignmentView.setMaxWidth(height);
		
		GridPane icons = new GridPane();

		alignmentView.setBackground(new Background(new BackgroundFill(Color
				.web("#FFFF00"), CornerRadii.EMPTY, Insets.EMPTY)));

		//add vision panel
		IVisionSystem alignmentSystem = VisionSystemAPI.getVisionSystem(ECameraType.ALIGNMENT_CAMERA);
		try {
			ImageBlender alignmentOverlay = VisionSystemAPI.getImageBlender(alignmentSystem);
			final SwingNode swingPanel = new SwingNode();
			
			SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                	swingPanel.setContent(alignmentOverlay);
                }
            });
			alignmentView.getChildren().add(swingPanel);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ToteLiftState toteState = new ToteLiftState(DriverStationDriver.getToteStateProperty(), width / 5, true);
		ToteLiftState recycleState = new ToteLiftState(DriverStationDriver.getRecycleStateProperty(), width / 5, false);

//		StackPane.setAlignment(toteState, Pos.TOP_RIGHT);
//		StackPane.setAlignment(recycleState, Pos.BOTTOM_LEFT);

		icons.add(toteState, 0, 0);
		icons.add(recycleState, 1, 0);
		StackPane.setAlignment(icons, Pos.TOP_RIGHT);
		alignmentView.getChildren().addAll(icons);

		return alignmentView;
	}

}
