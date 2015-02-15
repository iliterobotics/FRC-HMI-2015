package org.ilite.ui.diagnostics;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.ilite.ui.y2015.ManagerDriver;
import org.ilite.uitools.managers.Manager;
import org.ilite.uitools.widget.toggle.ToggleButton;

public class SideBar extends VBox{
	
	private UIManager master;

	public SideBar(UIManager master){
		ManagerDriver.init();
		this.master = master;
		build();
	}
	
	public void build(){
		for(Manager m : ManagerDriver.getManagerList()){
			getChildren().add(buildTableElement(m));
		}
	}
	
	public Node buildTableElement(Manager m){
		Text text = new Text(m.getName());
		text.setFont(new Font("Monaco", 15));
		ToggleButton button = new ToggleButton(text);
		button.getActivatedValue().addListener(observable -> updatePanelStatus(m, button.getActivatedValue().get(), button));
		return button;
	}
	
	
	
	private void updatePanelStatus(Manager manager, boolean active, ToggleButton button){
		if(active)
			button.forceChange(master.addWidgetPanel(manager));
		else
			master.removeWidgetPanel(manager);
	}

}
