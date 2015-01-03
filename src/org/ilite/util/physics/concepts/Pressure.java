package org.ilite.util.physics.concepts;

public class Pressure
{
  private double mPSI = 0d; 
  
  private Pressure()
  {
    
  }
  
  public double getPSI()
  {
    return mPSI;
  }

  public static Pressure fromPSI(double pPSI)
  {
    Pressure p = new Pressure();
    p.mPSI = pPSI;
    return p;
  }
}
