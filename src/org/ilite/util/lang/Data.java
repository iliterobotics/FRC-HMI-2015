package org.ilite.util.lang;

public class Data<Name, Value>
{
  private final Name mName;
  private final Value mValue;
  
  public Data(Name pName, Value pValue)
  {
    mName = pName;
    mValue= pValue;
  }
  
  public Name getName(){ return mName; }
  public Value getValue(){ return mValue; }
}
