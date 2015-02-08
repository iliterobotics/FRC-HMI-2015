package org.ilite.ui.y2015;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.ilite.uitools.managers.Manager;
import org.ilite.uitools.managers.MotorManager;
import org.ilite.uitools.widget.toggle.ToggleButton;

public class SideBar extends VBox{
	
	public List<Manager> elements;
	
	private UIManager master;

	public SideBar(UIManager master){
		elements = new ArrayList<Manager>();
		
		elements.add(new MotorManager());
		this.master = master;
		build();
	}
	
	public void build(){
		for(Manager m : elements){
			getChildren().add(buildTableElement(m));
		}
	}
	
	public Node buildTableElement(Manager m){
		Text text = new Text("Motor 1");
		text.setFont(new Font("Monaco", 40));
		ToggleButton button = new ToggleButton(text);
		button.getActivatedValue().addListener(observable -> updatePanelStatus(m, button.getActivatedValue().get()));
		return button;
	}
	
	
	
	private void updatePanelStatus(Manager manager, boolean active){
		if(active)
			master.addWidgetPanel(manager);
		else
			master.
	}

}
