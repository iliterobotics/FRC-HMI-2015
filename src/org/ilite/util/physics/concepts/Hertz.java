package org.ilite.util.physics.concepts;

public class Hertz
{
  private float mPerSecond;
  private Time mPeriod;
  
  public float perSecond(){ return mPerSecond; }
  public Time period(){ return mPeriod; }
  
  private Hertz()
  {
    
  }
  
  public static Hertz fromPeriod(Time pPeriod)
  {
    Hertz h = new Hertz();
    h.mPerSecond = 1f / pPeriod.seconds();
    h.mPeriod = new Time(pPeriod);
    return h;
  }
  
  public static Hertz fromRate(float pHertz)
  {
    Hertz h = new Hertz();
    h.mPerSecond = pHertz;
    h.mPeriod = Time.fromSeconds(1f / pHertz);
    return h;
  }
}
