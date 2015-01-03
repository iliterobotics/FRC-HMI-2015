package org.ilite.util.gui.components;

import org.ilite.util.gui.components.layer.AbstractLayer;

import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class NumberOverlay extends AbstractLayer
{
  protected NumberFormat mFormat = null;

  protected int mHeight, mWidth;
  protected final Font mFont = new Font("Arial", Font.BOLD, 50);
  protected final Font mSmallFont = new Font("Arial", Font.BOLD, 20);
  protected final Font mFontMod = new Font("Arial", Font.BOLD, 75);
  
  protected String mLastValue = "";
  protected String mCurrentValue = "";
  protected String mInputValue = "";
  
  public NumberOverlay(int pWidth, int pHeight)
  {
    mWidth = pWidth;
    mHeight = pHeight;
  }
  
  protected void setLastValue(double pValue)
  {
    createFormat();
    mLastValue = mFormat.format(pValue);
  }
  
  protected void setInputValue(double pValue)
  {
    createFormat();
    mInputValue = mFormat.format(pValue);
  }
  
  protected void setCurrentValue(double pValue)
  {
    createFormat();
    mCurrentValue = mFormat.format(pValue);
  }
  
  public abstract String getUnit();
  
  public abstract String getNumberFormat();
  
  private void createFormat()
  {
    if(mFormat == null)
    {
      mFormat = new DecimalFormat(getNumberFormat() + getUnit());
    }
  }
}
