package org.ilite.util.physics.concepts;

public class Time
{
  private float mSeconds;

  public float seconds()
  {
    return mSeconds;
  }

  public Time(Time pClone)
  {
    mSeconds = pClone.mSeconds;
  }

  private Time()
  {

  }

  public static Time fromSeconds(float pSeconds)
  {
    Time t = new Time();
    t.mSeconds = pSeconds;
    return t;
  }
}
