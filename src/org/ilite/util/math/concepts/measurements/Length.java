package org.ilite.util.math.concepts.measurements;

public class Length
{
  public static final double METERS_PER_INCH = 0.0254;
  
  private double mLengthMeters = 0d;
  
  private Length()
  {
    
  }
  
  public double getMeters()
  {
    return mLengthMeters;
  }
  
  public double getInches()
  {
    return mLengthMeters / METERS_PER_INCH;
  }

  public static Length fromInches(double pInches)
  {
    Length l = new Length();
    l.mLengthMeters = pInches * METERS_PER_INCH;
    return l;
  }
  
  public Length add(Length pOther)
  {
    mLengthMeters += pOther.mLengthMeters;
    return this;
  }

  public Length subtract(Length pOther)
  {
    mLengthMeters -= pOther.mLengthMeters;
    return this;
  }

  public Length multiplyBy(double pScalar)
  {
    mLengthMeters *= pScalar;
    return this;
  }

  public Length divideBy(double pScalar)
  {
    mLengthMeters /= pScalar;
    return this;
  }
}
