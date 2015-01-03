package org.ilite.telemetry.data;

public class FrcConstants
{ 
  /**
   * Number of digital inputs on the FRC control system
   */
  public static int sNUM_DIGITAL_INPUTS = 14;
  public static Float sMIN_DIG_INPUT = 0f;
  public static Float sDEFAULT_DIG_INPUT = null;
  public static Float sMAX_DIG_INPUT = 1f;
  
  /**
   * Number of PWM outputs on the FRC control system
   */
  public static int sNUM_PWM_OUTPUTS = 10;
  public static Float sMIN_PWM_OUT = -1f;
  public static Float sDEFAULT_PWM_OUTPUT = null;
  public static Float sMAX_PWM_OUT = 1f;

  /**
   * Number of relay outputs on the FRC control system
   */
  public static int sNUM_RELAY_OUTPUTS = 8;
  public static Integer sMIN_RELAY_OUT = -1;
  public static Integer sDEFAULT_RELAY_OUT = null;
  public static Integer sMAX_RELAY_OUT = 1;

  /**
   * Number of analog inputs on the FRC control system
   */
  public static int sNUM_ANALOG_INPUTS = 8;
  public static Float sMIN_ANALOG_INPUT = 0f;
  public static Float sDEFAULT_ANALOG_INPUT = null;
  public static Float sMAX_ANALOG_INPUT = 5f;

  /**
   * Number of pneumatic outputs on the FRC control system
   */
  public static int sNUM_PNEUMATIC_OUTPUTS = 8;
  public static Boolean sDEFAULT_PNEUMATIC_OUT = null;

  /**
   * Default driver's station timestamp
   */
  public static Integer sDEFAULT_TIMESTAMP = null;
  
  public static Float sDEFAULT_BATTERY_VOLTAGE = null;
}
