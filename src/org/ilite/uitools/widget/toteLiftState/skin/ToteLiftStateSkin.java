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

	private static final String totePicIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/totePic.png";
	private static final String recycleIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/recycle.png";
	
	private static final String stopIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/stop.png";
	private static final String resetIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/reset.png";
	private static final String toteIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/tote.png";
	private static final String raiseIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/up.png";
	private static final String lowerIconPath = "/org/ilite/uitools/widget/toteLiftState/icons/down.png";
	private static final String[] PATHS = {stopIconPath, resetIconPath, toteIconPath, raiseIconPath, lowerIconPath};
	
	private StackPane mainPane;
	
	private ImageView currentIcon, backIcon;
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
		currentIcon.setFitHeight(height * (2.0 / 5));
		currentIcon.setFitWidth(width  * (2.0 / 5));
		
		backIcon = new ImageView(new Image( ((ToteLiftState)getSkinnable()).isTote() ? totePicIconPath : recycleIconPath));
		backIcon.setFitHeight(height);
		backIcon.setFitWidth(width);
		
		mainPane.getChildren().addAll(backIcon, currentIcon);
		getChildren().add(mainPane);
		updateImage(0);
	}
	
	public void updateImage(int state){
		String path = (state < PATHS.length && state >= 0 ) ? PATHS[state] : "/org/ilite/uitools/widget/img/noimg.png";
		currentIcon.setImage(new Image(path));
	}

}
