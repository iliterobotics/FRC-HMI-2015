package org.ilite.util.math.concepts.measurements;

public class Volume
{
  private double mVolumeCubicMeters = 0d;
  
  public double getVolumeCubicMeters()
  {
    return mVolumeCubicMeters;
  }
  
  public double getVolumeCubicInches()
  {
    return mVolumeCubicMeters / Math.pow(Length.METERS_PER_INCH, 3d);
  }
  
  private Volume()
  {
    
  }
  
  public static Volume fromAreaAndLength(Area pCrossSection, Length pHeight)
  {
    Volume v = new Volume();
    v.mVolumeCubicMeters = pCrossSection.getSqMeters() * pHeight.getMeters();
    return v;
  }

}
