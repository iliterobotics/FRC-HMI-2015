package org.ilite.util.physics.concepts;

import static org.ilite.util.math.MathConstants.INVALID_DOUBLE;
import static org.ilite.util.physics.ConversionFactors.INCHES_PER_FOOT;
import static org.ilite.util.physics.ConversionFactors.NEWTON_METERS_PER_FOOTLB;
import static org.ilite.util.physics.ConversionFactors.OUNCES_PER_POUND;

public class Torque
{
  private double newton_meters = INVALID_DOUBLE;
  public double newton_meters() { return newton_meters; }
  
  private double foot_lbs = INVALID_DOUBLE;
  public double foot_lbs() { return foot_lbs; }
  
  private double ounce_inches = INVALID_DOUBLE;
  public double ounce_inches() { return ounce_inches; }

  private Torque() { }
  
  private void update()
  {
    if(foot_lbs == INVALID_DOUBLE && ounce_inches == INVALID_DOUBLE)
    {
      foot_lbs = newton_meters / NEWTON_METERS_PER_FOOTLB;
      ounce_inches = foot_lbs * OUNCES_PER_POUND / INCHES_PER_FOOT;
    }
    else if (foot_lbs == INVALID_DOUBLE && newton_meters == INVALID_DOUBLE)
    {
      foot_lbs = ounce_inches / OUNCES_PER_POUND / INCHES_PER_FOOT;
      newton_meters = foot_lbs * NEWTON_METERS_PER_FOOTLB;
    }
    else
    {
      newton_meters = foot_lbs * NEWTON_METERS_PER_FOOTLB;
      ounce_inches = foot_lbs * OUNCES_PER_POUND / INCHES_PER_FOOT;
    }
  }
  
  public static Torque fromNewtonMeters(double pNewtonMeters)
  {
    Torque result = new Torque();
    result.newton_meters = pNewtonMeters;
    result.update();
    return result;
  }
  
  public static Torque fromFootLbs(double pFootlbs)
  {
    Torque result = new Torque();
    result.foot_lbs = pFootlbs;
    result.update();
    return result;
  }
  
  public static Torque fromOunceInches(double pOunceInches)
  {
    Torque result = new Torque();
    result.ounce_inches = pOunceInches;
    result.update();
    return result;
  }
}
