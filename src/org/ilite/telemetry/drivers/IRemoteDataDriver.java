package org.ilite.telemetry.drivers;

public interface IRemoteDataDriver extends IDataDriver
{
  public void startServer();
  
  public void stopServer();
  
  public void simulateDisconnect();
}
