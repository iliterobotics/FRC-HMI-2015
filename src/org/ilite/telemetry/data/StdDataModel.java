package org.ilite.telemetry.data;

import org.ilite.util.lang.ICsvData;
import org.ilite.util.lang.IEncodable;

import java.nio.ByteBuffer;
import java.util.Arrays;


/**
 * @author JesseK
 * Data structure that represents all standard inputs & outputs available on
 * the FRC control system.  To modify the quantity, update the static constants
 * in FrcConstants (also in this package).
 * 
 * <p>Each team and game should extend this class with get() methods to get
 * what each represents (e.g. if drive train motor left is mPwmOutputs[0], then
 * a sub class should take care of that).  That way the indexing can be handled
 * independently of how this class is serialized/deserialized across an
 * interface
 * 
 * <p>This class also provides a way to output the data structure to a comma-
 * separated line, so it can be put into a .csv file.  During a competition,
 * this is a great way use a spreadsheet to quickly read and analyze data from 
 * a match prior to the next match. 
 */
public class StdDataModel
implements IEncodable, ICsvData
{  
  private static Long sFirstStartTime = null;
  
  public final long mLocalTime;
  
  public Integer mTimestamp;
  
  public Float mBatteryVoltage;
  
  public Float[] mPwmOutputs = new Float[FrcConstants.sNUM_PWM_OUTPUTS];
  
  public Integer[] mRelayOutputs = new Integer[FrcConstants.sNUM_RELAY_OUTPUTS];
  
  public Float[] mDigitalInputs = new Float[FrcConstants.sNUM_DIGITAL_INPUTS];
  
  public Float[] mAnalogInputs = new Float[FrcConstants.sNUM_ANALOG_INPUTS];
  
  public Boolean[] mPneumatics = new Boolean[FrcConstants.sNUM_PNEUMATIC_OUTPUTS];
  
  public DriverInput[] mDriverInputs = null;
  
  /**
   * Standard constructor.  All member variables are public, so access them
   * directly.  All member variables are created with default values, defined
   * in FrcConstants.
   */
  public StdDataModel()
  {
    mLocalTime = System.currentTimeMillis();
    mTimestamp = FrcConstants.sDEFAULT_TIMESTAMP;
    mBatteryVoltage = FrcConstants.sDEFAULT_BATTERY_VOLTAGE;
    Arrays.fill(mPwmOutputs, FrcConstants.sDEFAULT_PWM_OUTPUT);
    Arrays.fill(mRelayOutputs, FrcConstants.sDEFAULT_RELAY_OUT);
    Arrays.fill(mDigitalInputs, FrcConstants.sDEFAULT_DIG_INPUT);
    Arrays.fill(mAnalogInputs, FrcConstants.sDEFAULT_ANALOG_INPUT);
    Arrays.fill(mPneumatics, FrcConstants.sDEFAULT_PNEUMATIC_OUT);
  }
  
  public StdDataModel(DriverInput[] pDriverInputs)
  {
    this();
    mDriverInputs = pDriverInputs;
  }
  
  /**
   * @return a String representing this data structure via comma-separated
   * values.  A 'newline' character is NOT appended to the end. 
   */
  @Override
  public String toCsvString()
  {
    int i;
    StringBuilder csv = new StringBuilder();
    
    
    csv.append(mLocalTime).append(sCOMMA);
    csv.append(mTimestamp).append(sCOMMA);
    csv.append(mBatteryVoltage).append(sCOMMA);
    
    for(i = 0; i < mPwmOutputs.length; i++)
    {
      csv.append(mPwmOutputs[i]).append(sCOMMA);
    }
    for(i = 0; i < mRelayOutputs.length; i++)
    {
      csv.append(mRelayOutputs[i]).append(sCOMMA);
    }
    for(i = 0; i < mDigitalInputs.length; i++)
    {
      csv.append(mDigitalInputs[i]).append(sCOMMA);
    }
    for(i = 0; i < mAnalogInputs.length; i++)
    {
      csv.append(mAnalogInputs[i]).append(sCOMMA);
    }
    for(i = 0; i < mPneumatics.length; i++)
    {
      csv.append(mPneumatics[i]).append(sCOMMA);
    }
    if(mDriverInputs != null)
    {
      for(i = 0; i < mDriverInputs.length; i++)
      {
        csv.append(mDriverInputs[i].toCsvString());
      }
    }
    
    String sub = getSubclassCsv();
    if(sub != null && sub.isEmpty() == false)
    {
      csv.append(sub);
    }
    return csv.toString();
  }
  
  /**
   * @return the column names of a CSV line.  Each column name is also
   * separated by columns.  This may be overwritten by a subclass to give
   * more meaningful names.  A call to get subclass-specific columns is also
   * made.
   */
  @Override
  public String getCsvColumnNames()
  {
    int i;
    StringBuilder cols = new StringBuilder();

    cols.append("DS_TIME").append(sCOMMA);
    cols.append("SYS_TIME").append(sCOMMA);
    cols.append("BAT VOLTAGE").append(sCOMMA);

    for(i = 0; i < FrcConstants.sNUM_PWM_OUTPUTS; i++)
    {
      cols.append("PWM[").append(i).append("]").append(sCOMMA);
    }
    for(i = 0; i < FrcConstants.sNUM_RELAY_OUTPUTS; i++)
    {
      cols.append("RELAY[").append(i).append("]").append(sCOMMA);
    }
    for(i = 0; i < FrcConstants.sNUM_DIGITAL_INPUTS; i++)
    {
      cols.append("DIG_IN[").append(i).append("]").append(sCOMMA);
    }
    for(i = 0; i < FrcConstants.sNUM_ANALOG_INPUTS; i++)
    {
      cols.append("ANALOG[").append(i).append("]").append(sCOMMA);
    }
    for(i = 0; i < FrcConstants.sNUM_PNEUMATIC_OUTPUTS; i++)
    {
      cols.append("PNEUMATIC[").append(i).append("]").append(sCOMMA);
    }
    if(mDriverInputs != null)
    {
      for(i = 0; i < mDriverInputs.length; i++)
      {
        cols.append(mDriverInputs[i].getCsvColumnNames());
      }
    }
    
    String subclass = getSubclassCsvColumns();
    if(subclass != null && subclass.isEmpty() == false)
    {
      cols.append(subclass);
    }
    
    return cols.toString();
  }
  
  /**
   * TODO - once the network order is confirmed by the robot side
   * @return
   */
  @Override
  public byte[] encode()
  {
    return null;
  }
  
  /**
   * @param pData
   * @return
   */
  @Override
  public void decode(byte[] pData)
  {
    int i, b;

    ByteBuffer dis = ByteBuffer.wrap(pData);

    mTimestamp = dis.getInt();

    for (i = 0; i < mPwmOutputs.length; ++i) {
      mPwmOutputs[i] = dis.getFloat();
    }

    for (i = 0; i < mRelayOutputs.length; ++i) {
      mRelayOutputs[i] = dis.getInt();
    }

    for (i = 0; i < mDigitalInputs.length; ++i) {
      mDigitalInputs[i] = dis.getFloat();
    }

    for (i = 0; i < mAnalogInputs.length; ++i) {
      mAnalogInputs[i] = dis.getFloat();
    }

    for (i = 0; i < mPneumatics.length; ++i) {
      mPneumatics[i] = dis.getInt() == 0 ? false : true;
    }

    if(mDriverInputs != null)
    {
      for (i = 0; i < mDriverInputs.length; ++i) {
        for(b = 0; b < mDriverInputs[i].getNumberOfAxes(); b++)
        {
          mDriverInputs[i].setAxisValue(b, dis.getFloat());
        }
        for(b = 0; b < mDriverInputs[i].getNumberOfButtons(); b++)
        {
          mDriverInputs[i].setButtonValue(b, dis.getInt() == 0 ? false : true);
        }
      }
    }
  }
  
  /**
   * Provides a mechanism for a subclass to append extra data on a line.
   * Override to do so.  The line does not need to begin with a comma.
   * @return
   */
  protected String getSubclassCsv()
  {
    return null;
  }
  
  protected String getSubclassCsvColumns()
  {
    return null;
  }
}
