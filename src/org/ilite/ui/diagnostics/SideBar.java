package org.ilite.ui.diagnostics;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.ilite.uitools.managers.Manager;
import org.ilite.uitools.managers.MotorManager;
import org.ilite.uitools.widget.toggle.ToggleButton;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public class SideBar extends VBox{
	
	public static List<Manager> elements;
	
	private UIManager master;

	public SideBar(UIManager master){
		elements = new ArrayList<Manager>();
		
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
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
		button.getActivatedValue().addListener(observable -> updatePanelStatus(m, button.getActivatedValue().get(), button));
		return button;
	}
	
	public static void update(TelemetryMessage msg)
	{
		for(Manager m : elements)
		{
			m.getUpdate(msg);
		}
	}
	
	private void updatePanelStatus(Manager manager, boolean active, ToggleButton button){
		if(active)
			button.forceChange(master.addWidgetPanel(manager));
		else
			master.removeWidgetPanel(manager);
	}

}
