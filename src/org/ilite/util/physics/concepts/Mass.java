package org.ilite.util.physics.concepts;

import static org.ilite.util.math.MathConstants.INVALID_DOUBLE;
import org.ilite.util.physics.ConversionFactors;

public class Mass
{
  private double kilograms = INVALID_DOUBLE;
  public double kilograms(){ return kilograms; }
  
  public double lbs = INVALID_DOUBLE;
  public double lbs(){ return lbs; }
  
  public static Mass fromKilograms(double pKilograms)
  {
    Mass result = new Mass();
    result.kilograms = pKilograms;
    result.lbs = pKilograms / ConversionFactors.KG_PER_LB;
    return result;
  }
  
  public static Mass fromLbs(double pLbs)
  {
    Mass result = new Mass();
    result.lbs = pLbs;
    result.kilograms = pLbs * ConversionFactors.KG_PER_LB;
    return result;
  }
}
