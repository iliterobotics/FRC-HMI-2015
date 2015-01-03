package org.ilite.util.lang;


public interface IEncodable
{
  public byte[] encode();
  
  public void decode(byte[] pBytes);
}
