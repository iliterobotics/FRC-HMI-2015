package org.ilite.telemetry.data.y2015;

import static org.ilite.telemetry.data.ETelemetryType.ANALOG_IN;
import static org.ilite.telemetry.data.ETelemetryType.DIGITAL_IN;
import static org.ilite.telemetry.data.ETelemetryType.PNEUMATIC;
import static org.ilite.telemetry.data.ETelemetryType.PWM_OUT;
import static org.ilite.telemetry.data.ETelemetryType.RELAY_OUT;

import org.ilite.telemetry.data.ETelemetryType;
import org.ilite.util.gui.builder.IDisplayBuilderEnum;
import org.ilite.util.gui.builder.IRenderer;
import org.ilite.util.gui.builder.RenderingVisitorClassName;
import org.ilite.util.gui.builder.components.JLabelRenderer;

@RenderingVisitorClassName("org.ilite.util.gui.builder.DefaultVisitor")
public enum EData2015 implements IDisplayBuilderEnum
{
	;
	
	@Override
	public String getDisplayLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getFieldClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends IRenderer> getRenderingClass() {
		// TODO Auto-generated method stub
		return null;
	}
	//contains no data : confused by roboRIO port list, didn't know how to interpret.
}