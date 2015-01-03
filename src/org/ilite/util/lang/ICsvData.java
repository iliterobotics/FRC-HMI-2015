package org.ilite.util.lang;

public interface ICsvData
{
  public static final char sCOMMA = ',';

  public String toCsvString();
  
  public String getCsvColumnNames();
}
