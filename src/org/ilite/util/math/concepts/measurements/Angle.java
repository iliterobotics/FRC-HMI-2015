package org.ilite.util.math.concepts.measurements;

import static org.ilite.util.math.MathConstants.INVALID_DOUBLE;

public class Angle
{
  private double radians = INVALID_DOUBLE;
  public double radians(){ return radians; }
  
  private double degrees = INVALID_DOUBLE;
  public double degrees(){ return degrees; }
  
  public Angle(Angle pClone)
  {
    radians = pClone.radians;
    degrees = pClone.degrees;
  }
  
  private Angle()
  {
    
  }
  
  
  private void update()
  {
    if(radians == INVALID_DOUBLE)
    {
      radians = Math.toRadians(degrees);
    }
    else
    {
      degrees = Math.toDegrees(radians);
    }
  }
  
  public static Angle fromRadians(double pRadians)
  {
    Angle result = new Angle();
    result.radians = pRadians;
    result.update();
    return result;
  }
}
