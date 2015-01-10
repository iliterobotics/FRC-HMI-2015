package org.ilite.config;

public class ConfigItem
{
  /*package*/ static final int
  NAME = 0,
  VALUE = 1,
  TYPE = 2,
  GROUP = 3 ;
  
  /*package*/ String mName;
  /*package*/ String mValue;
  /*package*/ String mType;
  /*package*/ String mGroup;
  
  /*package*/ ConfigItem()
  {
    
  }
  
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(mName).append("=").append(mValue).append(" <").append(mType).append(">");
    return sb.toString();
  }
  
  String value(int pIndex)
  {
    switch(pIndex)
    {
    case NAME: return mName;
    case VALUE: return mValue;
    case TYPE: return mType;
    case GROUP: return mGroup;
    default: return "";
    }
  }
  
  void set(int index, String value)
  {
    switch(index)
    {
    case NAME: mName = value; return;
    case VALUE: mValue = value; return;
    case TYPE: mType = value; return;
    case GROUP: mGroup = value; return;
    }
  }
  
  public boolean equals(Object that)
  {
    if(that instanceof ConfigItem == false) return false;
    if(this == that) return true;
    
    ConfigItem ci = (ConfigItem) that;
	  return this.mName.equalsIgnoreCase(ci.mName);
  }
  
  public boolean equivalentTo(ConfigItem that)
  {
	  return (this.equals(that) && this.mValue.equalsIgnoreCase(that.mValue));
  }
}
