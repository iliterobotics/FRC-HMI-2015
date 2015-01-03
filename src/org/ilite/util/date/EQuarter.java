package org.ilite.util.date;

public enum EQuarter {
   Q1,
   Q2,
   Q3,
   Q4;
   
   public int index(){ return ordinal() + 1; }
}
