package org.ilite.ui.y2015;

import java.util.ArrayList;
import java.util.List;

import org.ilite.telemetry.data.y2015.EData2015;
import org.ilite.uitools.managers.Manager;
import org.ilite.uitools.managers.MotorManager;
import org.ilite.uitools.managers.SensorManager;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public class ManagerDriver {
	
	private static List<Manager> elements = new ArrayList<Manager>();
	
	public static void init(){
		elements.add(new MotorManager(EData2015.ANAIN1));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new MotorManager(null));
		elements.add(new SensorManager(null, 0, 10));
		elements.add(new SensorManager(null, 0, 10));
		elements.add(new SensorManager(null, 0, 10));
		elements.add(new SensorManager(null, 0, 10));
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
