package org.ilite.util.gui;

import java.awt.Color;
import java.awt.Rectangle;

public class ColoredAwtRectangle extends Rectangle
{
  private final Color mColor;
  
  public ColoredAwtRectangle(Color pColor, int pX, int pY, int pWidth, int pHeight)
  {
    super(pX, pY, pWidth, pHeight);
    mColor = pColor;
  }
  
  public Color getColor()
  {
    return mColor;
  }
}
