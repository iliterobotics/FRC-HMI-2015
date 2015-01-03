package org.ilite.telemetry.data.y2014;

import ilite.display.interfaces.net.AbstractMagicNumberMsgDecoder;

public class TelemetryDeocoder2014 extends AbstractMagicNumberMsgDecoder<Data2014>
{
  // "Data Feed"
  public static final int sDATA_MSG_BYTE_TAG = 0xDA7AFEED;
  
  public TelemetryDeocoder2014()
  {
    super(sDATA_MSG_BYTE_TAG);
    mLatest = new Data2014();
  }
  
//  @Override
  public Data2014 decodeImpl(byte[] pBytes, int pLength)
  {
    Data2014 data = new Data2014();
    data.decode(pBytes);
    return data;
  }
}
