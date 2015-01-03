package org.ilite.telemetry.data;

import org.ilite.util.lang.ICsvData;

import java.util.Arrays;

public class DriverInput implements ICsvData
{
  public static DriverInput generateLogitechAttack3Joystick(int pIndex)
  {
    return new DriverInput(3, 11, "JOY" + pIndex);
  }
  
  /**
   * @return an implementation of the 
   */
  public static DriverInput generateLogitechFTCGamepad(int pIndex)
  {
    return new DriverInput(3, 10, "GPAD" + pIndex);
  }
  
  public final Float[] mAxes;
  public final Boolean[] mButtons;
  public final String mName;
  
  public DriverInput(int pNumAxes, int pNumButtons, String pName)
  {
    mName = pName;
    mAxes = new Float[pNumAxes];
    mButtons = new Boolean[pNumButtons];
    Arrays.fill(mAxes, null);
    Arrays.fill(mButtons, Boolean.FALSE);
  }
  
  public int getNumberOfButtons()
  {
    return mButtons.length;
  }
  
  public int getNumberOfAxes()
  {
    return mAxes.length;
  }
  
  public Boolean getButtonValue(int pButtonIndex)
  {
    return mButtons[pButtonIndex];
  }
  
  public void setButtonValue(int pButtonIndex, Boolean pValue)
  {
    mButtons[pButtonIndex] = pValue;
  }
  
  public Float getAxisValue(int pAxisIndex)
  {
    return mAxes[pAxisIndex];
  }
  
  public void setAxisValue(int pAxisIndex, Float pValue)
  {
    mAxes[pAxisIndex] = pValue;
  }
  
  public String getName()
  {
    return mName;
  }

  @Override
  public String toCsvString()
  {
    int i;
    StringBuilder sb = new StringBuilder();
    for(i = 0; i < mAxes.length; i++)
    {
      sb.append(mAxes[i]).append(sCOMMA);
    }
    for(i = 0; i < mButtons.length; i++)
    {
      sb.append(mButtons[i]).append(sCOMMA);
    }
    return sb.toString();
  }

  @Override
  public String getCsvColumnNames()
  {
    int i;
    StringBuilder sb = new StringBuilder();
    for(i = 0; i < mAxes.length; i++)
    {
      sb.append(mName).append("_").append("AXIS").append(i).append(sCOMMA);
    }
    for(i = 0; i < mButtons.length; i++)
    {
      sb.append(mName).append("_").append("B").append(i).append(sCOMMA);
    }
    return sb.toString();
  }
}
