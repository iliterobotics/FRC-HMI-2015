package org.ilite.uitools.widget.toteLiftState.skin;

import javafx.animation.Animation;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import org.ilite.uitools.widget.toteLiftState.ToteLiftState;

public class ToteLiftStateSkin extends SkinBase<ToteLiftState> implements Skin<ToteLiftState>{
	
	private static final String stopIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/stop.png";
	private static final String resetIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/stop.png";
	private static final String toteIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/stop.png";
	private static final String raiseIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/stop.png";
	private static final String lowerIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/stop.png";
	private static final String[] PATHS = {stopIconPath, resetIconPath, toteIconPath, raiseIconPath, lowerIconPath};
	
	private StackPane mainPane;
	
	private ImageView currentIcon;
	private Animation[] currentAnimations;
	private Effect[] currentEffects;
	
	public ToteLiftStateSkin(ToteLiftState parent, double width, double height) {
		super(parent);
		initGraphics(width, height);
	}
	
	private void initGraphics(double width, double height){
		mainPane = new StackPane();
		mainPane.setMaxSize(width, height);
		mainPane.setMinSize(width, height);
		currentIcon = new ImageView(new Image(stopIconPath));
		currentIcon.setFitHeight(height);
		currentIcon.setFitWidth(width);
		
		mainPane.getChildren().add(currentIcon);
		getChildren().add(mainPane);
		updateImage(99);
	}
	
	public void updateImage(int state){
		String path = (state < PATHS.length && state > 0 ) ? PATHS[state] : "/org/ilite/uitools/widget/img/noimage.png";
		currentIcon.setImage(new Image(path));
	}

}
