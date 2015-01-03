package org.ilite.util.math.concepts.measurements;

public class Area 
{
  private double mAreaSqMeters = 0d;
  private Area()
  {
    
  }
  
  public double getSqMeters()
  {
    return mAreaSqMeters;
  }
  
  public double getSqInches()
  {
    return mAreaSqMeters / Length.METERS_PER_INCH / Length.METERS_PER_INCH;
  }
  
  public static Area fromSides(Length pSide1, Length pSide2)
  {
    Area a = new Area();
    a.mAreaSqMeters = pSide1.getMeters() * pSide2.getMeters();
    return a;
  }

  public static Area fromSqIn(double pSqIn)
  {
    Area a = new Area();
    a.mAreaSqMeters = pSqIn * Length.METERS_PER_INCH * Length.METERS_PER_INCH;
    return a;
  }
  
  public static Area fromCircleDiameter(Length pDiameter)
  {
    Area a = new Area();
    double r = pDiameter.getMeters() / 2d;
    a.mAreaSqMeters = r * r * Math.PI;
    return a;
  }

  public static Area fromMeters(double pAreaMeters)
  {
    Area a = new Area();
    a.mAreaSqMeters = pAreaMeters;
    return a;
  }

  public Area add(Area pOther)
  {
    mAreaSqMeters += pOther.mAreaSqMeters;
    return this;
  }

  public Area subtract(Area pOther)
  {
    mAreaSqMeters -= pOther.mAreaSqMeters;
    return this;
  }

  public Area multiplyBy(double pScalar)
  {
    mAreaSqMeters *= pScalar;
    return this;
  }

  public Area divideBy(double pScalar)
  {
    mAreaSqMeters /= pScalar;
    return this;
  }
}
