package org.ilite.telemetry.data.y2014;

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
public enum EData2014 implements IDisplayBuilderEnum
{
  DIGIN1_ENC1A(1, DIGITAL_IN, "Encoder 1 Channel A"),
  DIGIN2_ENC1B(2, DIGITAL_IN, "Encoder 1 Channel B"),
  DIGIN3_ENC2A(3, DIGITAL_IN, "Encoder 2 Channel A"),
  DIGIN4_ENC2B(4, DIGITAL_IN, "Encoder 2 Channel B"),
  DIGIN5(5, DIGITAL_IN),
  DIGIN6(6, DIGITAL_IN),
  DIGIN7(7, DIGITAL_IN),
  DIGIN8(8, DIGITAL_IN),
  DIGIN9_COMPRESSOR_PRESSURE_SWITCH(9, DIGITAL_IN, "Pneumatic Pressure Switch"),
  DIGIN10_WINCH_LIMIT_SWITCH(10, DIGITAL_IN, "Winch Limit Switch"),
  DIGIN11(11, DIGITAL_IN),
  DIGIN12(12, DIGITAL_IN),
  DIGIN13(13, DIGITAL_IN),
  DIGIN14(14, DIGITAL_IN),

  PWM_RIGHT_DRIVETRAIN_1(1, PWM_OUT, "Right Drive Train 1"),
  PWM_RIGHT_DRIVETRAIN_2(2, PWM_OUT, "Right Drive Train 2"),
  PWM_LEFT_DRIVETRAIN_1(3, PWM_OUT, "Left Drive Train 1"),
  PWM_LEFT_DRIVETRAIN_2(4, PWM_OUT, "Left Drive Train 2"),
  PWM_INTAKE_1(5, PWM_OUT, "Intake Motor (Unverified)"),
  PWM_INTAKE_2(6, PWM_OUT),
  PWM_DELIVERY_1(7, PWM_OUT, "Winch Motor (Unverified)"),
  PWM_DELIVERY_2(8, PWM_OUT),
  PWM_SERVO_1(9, PWM_OUT),
  PWM_SERVO_2(10, PWM_OUT),

  PNEU_P1(1, PNEUMATIC, "Extend Intake"),
  PNEU_P2(2, PNEUMATIC, "Retract Intake"),
  PNEU_P3(3, PNEUMATIC, "High Goal Fire"),
  PNEU_P4(4, PNEUMATIC, "Engage Winch"),
  PNEU_P5(5, PNEUMATIC, "Log Goal Retract"),
  PNEU_P6(6, PNEUMATIC, "Low Goal Fire"),
  PNEU_P7(7, PNEUMATIC),
  PNEU_P8(8, PNEUMATIC),

  RELAY_COMPRESSOR(1, RELAY_OUT, "Compressor"),
  RELAY_SIGNAL_1(2, RELAY_OUT),
  RELAY_SIGNAL_2(3, RELAY_OUT),
  RELAY_WINDOW_MOTOR(4, RELAY_OUT),
  RELAY_SPARE_COMPRESSOR(5, RELAY_OUT),
  RELAY_SPARE_WINDOW_MOTOR(6, RELAY_OUT),
  RELAY_OPEN_1(7, RELAY_OUT),
  RELAY_OPEN_2(8, RELAY_OUT),

  ANAIN1_GYRO(1, ANALOG_IN, "Gyro"),
  ANAIN2_COMPRESSOR_PRESSURE(2, ANALOG_IN, "Pneumatic Pressure"),
  ANAIN3(3, ANALOG_IN),
  ANAIN4(4, ANALOG_IN),
  ANAIN5(5, ANALOG_IN),
  ANAIN6(6, ANALOG_IN),
  ANAIN7(7, ANALOG_IN),
  ANAIN8(8, ANALOG_IN);
  
  public ETelemetryType getTelemetryType(){ return mType; }
  private ETelemetryType mType;
  private String mDisplayLabel;
  private int mPortNumber;
  
  private Class<? extends IRenderer> mRenderingClass;
  
  private EData2014(int pPortNumber, ETelemetryType pType, String pDisplayLabel)
  {
    this(pPortNumber, pType, JLabelRenderer.class, pDisplayLabel);
  }
  
  private EData2014(int pPortNumber, ETelemetryType pType)
  {
    this(pPortNumber, pType, JLabelRenderer.class, null);
  }
  
  private EData2014(int pPortNumber, ETelemetryType pType, Class<? extends IRenderer> pRenderingClass, String pDisplayLabel)
  {
    mType = pType;
    mRenderingClass = pRenderingClass;
    mDisplayLabel = pDisplayLabel;
    mPortNumber = pPortNumber;
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
