package org.ilite.ui.y2015;

import javafx.scene.layout.VBox;

import org.ilite.uitools.widget.toteLiftState.ToteLiftState;

public class WidgetBar 
{
	private static VBox widgetBar = new VBox(15);
	
	public static VBox generateWidgets()
	{
		widgetBar.setMinHeight(ViewBuilder.TOTAL_HEIGHT);
		widgetBar.setMaxHeight(ViewBuilder.TOTAL_HEIGHT);
		widgetBar.setMinWidth(ViewBuilder.TOTAL_WIDTH-MainView.PANEL_WIDTH-AlignmentView.PANEL_WIDTH);
		widgetBar.setMaxWidth(ViewBuilder.TOTAL_WIDTH-MainView.PANEL_WIDTH-AlignmentView.PANEL_HEIGHT);
		widgetBar.setStyle("-fx-background-color: #00AA22;");
		widgetBar.getChildren().addAll(ManagerDriver.getManagerList().get(0).buildWidgets(100, 100)[0], new ToteLiftState(null, 100, true), new ToteLiftState(null, 100, false));
		return widgetBar;
	}
	
	//TODO set width of widget panel based off size of camera image panel
}
