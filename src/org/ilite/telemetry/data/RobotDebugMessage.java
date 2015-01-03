package org.ilite.telemetry.data;

import org.ilite.util.lang.ICsvData;

public class RobotDebugMessage implements ICsvData
{
  public final String mMessage;
  public final int mRobotTimestamp;
  public final long mDisplayTimestamp = System.currentTimeMillis();
  
  private static final String sCOLS = "Robot Timestamp, Display Timestamp, Debug Message";
  
  public RobotDebugMessage(int pRobotTimestamp, String pMsg)
  {
    mMessage = pMsg;
    mRobotTimestamp = pRobotTimestamp;
  }

  @Override
  public String toCsvString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(mRobotTimestamp).append(sCOMMA);
    sb.append(mDisplayTimestamp).append(sCOMMA);
    sb.append(mMessage);
    return sb.toString();
  }

  @Override
  public String getCsvColumnNames()
  {
    return sCOLS;
  }
}
