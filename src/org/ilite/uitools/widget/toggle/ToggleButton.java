package org.ilite.uitools.widget.toggle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ToggleButton extends Control{

	private boolean activated;
	private BooleanProperty activatedValue;

	private Node icon;

	public ToggleButton(Image icon) {
		
		this.icon = new ImageView(icon);
		this.setWidth(icon.getWidth() + 10);
		this.setHeight(icon.getHeight() + 10);
		init();

	}

	public ToggleButton(Text text) {
		this.icon = text;
		this.setWidth(icon.getLayoutBounds().getWidth() + 10);
		this.setHeight(icon.getLayoutBounds().getHeight() + 10);
		init();
	}

	public ToggleButton() {
		this(new Image("/org/ilite/uitools/widget/img/noimg.png"));
	}

	public ToggleButton(String string) {
		this(new Image("/org/ilite/uitools/widget/img/" + string));
	}

	private void init() {
		activatedValue = new SimpleBooleanProperty(false);
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				activatedValue.set(!activatedValue.get());
				((ToggleButtonSkin) getSkin()).setActivated(activatedValue.get());
			}
		});
	}
	
	public BooleanProperty getActivatedValue(){
		return activatedValue;
	}

	public Skin<ToggleButton> createDefaultSkin() {
		return new ToggleButtonSkin(this);
	}

	public Node getIcon() {
		return icon;
	}

	public boolean isActivated() {
		return activated;
	}

}
