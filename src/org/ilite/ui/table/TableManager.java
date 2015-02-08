package org.ilite.ui.table;

import java.util.ArrayList;
import java.util.List;

import org.ilite.telemetry.data.ETelemetryType;
import org.ilite.telemetry.data.y2015.EData2015;
import org.usfirst.frc.team1885.robot.comms.TelemetryMessage;

public class TableManager {

	public static void getUpdate(TelemetryMessage msg) {
		List<Double> values = new ArrayList<Double>();
		int counter = 0;
		for (EData2015 data : EData2015.values()) {
			if (data.getType().equals(ETelemetryType.PNEUMATIC)) {
				values.set(counter, msg.getSolenoids(data.getPortNumber()));
			} else if (data.getType().equals(ETelemetryType.RELAY_OUT)) {
				values.set(counter, msg.getRelays(data.getPortNumber()));
			} else if (data.getType().equals(ETelemetryType.ANALOG_IN)) {
				values.set(counter, msg.getAnalogInputs(data.getPortNumber()));
			} else if (data.getType().equals(ETelemetryType.DIGITAL_IN)) {
				values.set(counter, msg.getDigitalInputs(data.getPortNumber()));
			} else if (data.getType().equals(ETelemetryType.PWM_OUT)) {
				values.set(counter, msg.getDigitalOutputs(data.getPortNumber()));
			}
		}
		Table.setValues(values);
		Table.refreshTable();
	}

}
