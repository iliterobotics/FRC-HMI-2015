package org.ilite.telemetry.drivers;

public interface IDataDriver <Data>
{
  public void start();
  
  public void cancel();

  public Data generate(float pRelativeTimeSeconds);
}
