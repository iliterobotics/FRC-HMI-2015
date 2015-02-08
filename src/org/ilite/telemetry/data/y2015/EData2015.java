package org.ilite.telemetry.data.y2015;

import static org.ilite.telemetry.data.ETelemetryType.ANALOG_IN;
import static org.ilite.telemetry.data.ETelemetryType.ANALOG_OUT;
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
	DIGIN1_ENC1A(0, DIGITAL_IN, "Encoder 1 Channel A"),
	DIGIN2_ENC1B(1, DIGITAL_IN, "Encoder 1 Channel B"),
	DIGIN3_ENC2A(2, DIGITAL_IN, "Encoder 2 Channel A"),
	DIGIN4_ENC2B(3, DIGITAL_IN, "Encoder 2 Channel B"),
	DIGIN5_ENC3A(4, DIGITAL_IN),
	DIGIN6_ENC3B(5, DIGITAL_IN),
	DIGIN7(6, DIGITAL_IN),
	DIGIN8(7, DIGITAL_IN),
	DIGIN9(8, DIGITAL_IN),
	DIGIN10(9, DIGITAL_IN),
	DIGIN11(10, DIGITAL_IN),
	DIGIN12(11, DIGITAL_IN),
	DIGIN13(12, DIGITAL_IN),
	DIGIN14(13, DIGITAL_IN),
	DIGIN15(14, DIGITAL_IN),
	DIGIN16(15, DIGITAL_IN),
	DIGIN17(16, DIGITAL_IN),
	DIGIN18(17, DIGITAL_IN),
	DIGIN19(18, DIGITAL_IN),
	DIGIN20(19, DIGITAL_IN),

	DIGOUT1_PWM_RIGHT_DRIVETRAIN_1(10, PWM_OUT, "Right Drive Train 1"),
	DIGOUT2_PWM_RIGHT_DRIVETRAIN_2(11, PWM_OUT, "Right Drive Train 2"),
	DIGOUT3_PWM_LEFT_DRIVETRAIN_1(12, PWM_OUT, "Left Drive Train 1"),
	DIGOUT4_PWM_LEFT_DRIVETRAIN_2(13, PWM_OUT, "Left Drive Train 2"),
	DIGOUT5_PWM5(14, PWM_OUT),
	DIGOUT6_PWM6(15, PWM_OUT),
	DIGOUT7_PWM7(16, PWM_OUT),
	DIGOUT8_PWM8(17, PWM_OUT),
	DIGOUT9_PWM9(18, PWM_OUT),
	DIGOUT10_PWM10(19, PWM_OUT),

	SOL_PNEU_P1(0, PNEUMATIC),
	SOL_PNEU_P2(1, PNEUMATIC),
	SOL_PNEU_P3(2, PNEUMATIC),
	SOL_PNEU_P4(3, PNEUMATIC),
	SOL_PNEU_P5(4, PNEUMATIC),
	SOL_PNEU_P6(5, PNEUMATIC),
	SOL_PNEU_P7(6, PNEUMATIC),
	SOL_PNEU_P8(7, PNEUMATIC),
	
	ANAIN1(0, ANALOG_IN),
	ANAIN2(1, ANALOG_IN),
	ANAIN3(2, ANALOG_IN),
	ANAIN4(3, ANALOG_IN),
	ANAIN5(4, ANALOG_IN),
	ANAIN6(5, ANALOG_IN),
	ANAIN7(6, ANALOG_IN),
	ANAIN8(7, ANALOG_IN),
	ANAIN9(8, ANALOG_IN),
	ANAIN10(9, ANALOG_IN),
	
	ANAOUT1(1, ANALOG_OUT),
	ANAOUT2(2, ANALOG_OUT),
	
	RELAY1(0, RELAY_OUT),
	RELAY2(1, RELAY_OUT),
	RELAY3(2, RELAY_OUT),
	RELAY4(3, RELAY_OUT);	

	public ETelemetryType getTelemetryType()	{ return mType; }
	
	
	private ETelemetryType mType;
	private String mDisplayLabel;
	private int mPortNumber;

	private Class<? extends IRenderer> mRenderingClass;

	private EData2015(int pPortNumber, ETelemetryType pType, String pDisplayLabel)
	{
		this(pPortNumber, pType, JLabelRenderer.class, pDisplayLabel);
	}

	private EData2015(int pPortNumber, ETelemetryType pType)
	{
		this(pPortNumber, pType, JLabelRenderer.class, null);
	}

	private EData2015(int pPortNumber, ETelemetryType pType, Class<? extends IRenderer> pRenderingClass, String pDisplayLabel)
	{
		mType = pType;
		mRenderingClass = pRenderingClass;
		mDisplayLabel = pDisplayLabel;
		mPortNumber = pPortNumber;
	}
	
	public ETelemetryType getType()
	{
		return mType;
	}

	public int getPortNumber()
	{
		return mPortNumber;
	}

	public boolean isUsed()
	{
		return !(mDisplayLabel == null);
	}

	@Override
	public String getDisplayLabel()
	{
		if(mDisplayLabel == null)
		{
			return name();
		}
		else
		{
			return mDisplayLabel;
		}
	}

	@Override
	public Class<?> getFieldClass()
	{
		return mType.getFieldClass();
	}

	@Override
	public Class<? extends IRenderer> getRenderingClass()
	{
		return mRenderingClass;
	}
}