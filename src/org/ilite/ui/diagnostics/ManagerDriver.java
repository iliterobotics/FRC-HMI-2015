package org.ilite.ui.diagnostics;

import java.util.ArrayList;
import java.util.List;

import org.ilite.uitools.managers.Manager;
import org.ilite.uitools.managers.MotorManager;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public class ManagerDriver {
	
	private static List<Manager> elements = new ArrayList<Manager>();
	
	public static void init(){
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
	}
	
	public static void update(TelemetryMessage msg)
	{
		for(Manager m : elements)
		{
			m.getUpdate(msg);
		}
	}
	
	public static List<Manager> getManagerList(){
		return elements;
	}

}
