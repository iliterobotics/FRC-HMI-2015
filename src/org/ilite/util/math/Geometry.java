package org.ilite.util.math;

import java.awt.Dimension;

public class Geometry
{
  public enum QUADRANT
  {
    Q1, Q2, Q3, Q4;
    
    public QUADRANT next()
    {
      int ord = ordinal() + 1;
      if(ord >= values().length) ord = 0;
      return values()[ord];
    }
    
    public QUADRANT prev()
    {
      int ord = ordinal() - 1;
      if(ord < 0) ord = values().length - 1;
      return values()[ord];
    }
  }
  
  public static final double HALF_PI = Math.PI / 2.0;
  public static final double THREE_HALF_PI = 3.0 * Math.PI / 2.0;
  public static final double TWO_PI = 2.0 * Math.PI;
  public static final double PI = Math.PI;
  public static final double sINCREMENT = Math.PI / 720;
  
  public static double getAngleRangeDegrees(double pStartAngDegrees, double pEndAngleDegrees)
  {
    double deg = Math.toDegrees(
        getAngleRangeRadians(Math.toRadians(pStartAngDegrees), Math.toRadians(pEndAngleDegrees)));
    return deg;
  }
  
  public static double getAngleRangeRadians(double pStartAngleRads, double pEndAngleRads)
  {
    double result = pEndAngleRads - pStartAngleRads;
    return result;
  }

  public static QUADRANT getQuadrantOfAngle(double pAngleRads)
  {
    double angle = pAngleRads;
    while(angle >= TWO_PI)
    {
      angle -= TWO_PI;
    }
    
    while(angle < 0)
    {
      angle += TWO_PI;
    }
    
    if(angle >= 0 && angle <= HALF_PI)
      return QUADRANT.Q1;
    
    if(angle >= HALF_PI && angle <= PI)
      return QUADRANT.Q2;
    
    if(angle >= PI && angle <= THREE_HALF_PI)
      return QUADRANT.Q3;
    
    return QUADRANT.Q4;
  }
  
  public static int round(double pDouble)
  {
    return (int)Math.round(pDouble);
  }
  
  public static Dimension getMaxDimensionWithOrigin(double pStartAngle, double pEndAngle, double pRadius)
  {
    double max_x = 0.0, max_y = 0.0;
    double min_x = 0.0, min_y = 0.0;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double x = Math.cos(idx) * pRadius;
      double y = Math.sin(idx) * pRadius;
      max_x = Math.max(max_x, x);
      min_x = Math.min(min_x, x);
      max_y = Math.max(max_y, y);
      min_y = Math.min(min_y, y);
    }
    
    return new Dimension((int)(max_x - min_x), (int)(max_y - min_y));
  }

  public static Dimension getMaxDimension(double pStartAngle, double pEndAngle, double pRadius)
  {
    double max_x = 0.0, max_y = 0.0;
    double min_x = Double.MAX_VALUE, min_y = Double.MAX_VALUE;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double x = Math.cos(idx) * pRadius;
      double y = Math.sin(idx) * pRadius;
      max_x = Math.max(max_x, x);
      min_x = Math.min(min_x, x);
      max_y = Math.max(max_y, y);
      min_y = Math.min(min_y, y);
    }
    
    return new Dimension((int)(max_x - min_x), (int)(max_y - min_y));
  }
  
  public static double getMinX(double pStartAngle, double pEndAngle, double pRadius)
  {
    double min_x = 0.0;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double x = Math.cos(idx) * pRadius;
      min_x = Math.min(min_x, x);
    }
    
    return min_x;
  }
  
  public static double getMaxX(double pStartAngle, double pEndAngle, double pRadius)
  {
    double max_x = 0.0;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double x = Math.cos(idx) * pRadius;
      max_x = Math.max(max_x, x);
    }
    
    return max_x;
  }

  public static double getMinY(double pStartAngle, double pEndAngle, double pRadius)
  {
    double min_y = Double.MAX_VALUE;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double y = Math.sin(idx) * pRadius;
      min_y = Math.min(min_y, y);
    }
    
    return min_y;
  }
  
  public static double getMaxY(double pStartAngle, double pEndAngle, double pRadius)
  {
    double max_y = 0.0;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double y = Math.sin(idx) * pRadius;
      max_y = Math.max(max_y, y);
    }
    
    return max_y;
  }
  
  public static int getMaxWidth(double pStartAngle, double pEndAngle, double pRadius)
  {
    double max_x = 0.0;
    double min_x = Double.MAX_VALUE;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double x = Math.cos(idx) * pRadius;
      max_x = Math.max(max_x, x);
      min_x = Math.min(min_x, x);
    }
    
    return (int)(max_x - min_x);
  }
  
  public static int getMaxHeight(double pStartAngle, double pEndAngle, double pRadius)
  {
    double max_x = 0.0;
    double min_x = Double.MAX_VALUE;
    for(double idx = Math.min(pStartAngle, pEndAngle); idx < Math.max(pStartAngle, pEndAngle); idx+=sINCREMENT)
    {
      double x = Math.sin(idx) * pRadius;
      max_x = Math.max(max_x, x);
      min_x = Math.min(min_x, x);
    }
    
    return (int)(max_x - min_x);
  }
  
  public static void main(String[] pArgs)
  {
    int radius = 100; // px
    double start = 0; // radians
    double end = PI; // radians
    
    int maxheight = getMaxHeight(start, end, radius);
    int maxwidth = getMaxWidth(start, end, radius);
    System.out.println("Max Height=" + maxheight + " Max Width=" + maxwidth);
    int maxx = (int)getMaxX(start, end, radius);
    int minx = (int)getMinX(start, end, radius);
    System.out.println("MaxX=" + maxx + " MinX=" + minx);
    int maxy = (int)getMaxY(start, end, radius);
    int miny = (int)getMinY(start, end, radius);
    System.out.println("MaxY=" + maxy + " MinY=" + miny);
  }
}
