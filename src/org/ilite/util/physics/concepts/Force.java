package org.ilite.util.physics.concepts;

public class Force
{
  public static final double NEWTONS_PER_POUND = 0.22481 ;
  private double mForceNewtons = 0d;
  
  private Force()
  {
    
  }
  
  public double getNewtons()
  {
    return mForceNewtons;
  }
  
  public double getPounds()
  {
    return mForceNewtons / NEWTONS_PER_POUND;
  }
  
  public static Force fromNewtons(double pForceNewtons)
  {
    Force f = new Force();
    f.mForceNewtons = pForceNewtons;
    return f;
  }
  
  public static Force fromPounds(double pForcePounds)
  {
    Force f = new Force();
    f.mForceNewtons = pForcePounds * NEWTONS_PER_POUND;
    return f;
  }
}
