package org.ilite.telemetry.data;

public enum ETelemetryType
{
  ANALOG_IN,
  DIGITAL_IN,
  PWM_OUT,
  PNEUMATIC(Boolean.class),
  RELAY_OUT(Integer.class);
  
  private Class<?> mFieldClass;
  
  public Class<?> getFieldClass()
  {
    return mFieldClass;
  }
  
  private <Type> ETelemetryType(Class<Type> pFieldClass)
  {
    mFieldClass = pFieldClass;
  }
  
  private ETelemetryType()
  {
    this(Float.class);
  }
}
