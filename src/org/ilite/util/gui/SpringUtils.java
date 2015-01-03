package org.ilite.util.gui;

import java.awt.Component;

import javax.swing.SpringLayout;

public class SpringUtils
{
  public static class SpringLayoutHelper
  {
    private final SpringLayout mSpringLayout;
    private final int mInset ;
    private final Component mParent;
    private Component mLastComponent = null;
    public SpringLayoutHelper(SpringLayout sl, int inset, Component pParent)
    {
      mSpringLayout = sl;
      mInset = inset;
      mParent = pParent;
    }
    
    public void sequenceTopToBottom(Component pComponent, int pInsetMultiplier)
    {
      if(mLastComponent == null)
      {
        mSpringLayout.putConstraint(SpringLayout.NORTH, pComponent, mInset*pInsetMultiplier, SpringLayout.NORTH, mParent);
      }
      else
      {
        mSpringLayout.putConstraint(SpringLayout.NORTH, pComponent, mInset*pInsetMultiplier, SpringLayout.SOUTH, mLastComponent);
      }
      mLastComponent = pComponent;
    }
    
    public void sequenceTopToBottom(Component pComponent)
    {
      sequenceTopToBottom(pComponent, 1);
    }
    
    public void stickToTopBottom(Component pComponent)
    {
      SpringUtils.stickToTopBottom(mSpringLayout, pComponent, mInset, mParent);
    }
    
    public void stickToSides(Component pComponent)
    {
      SpringUtils.stickToSides(mSpringLayout, pComponent, mInset, mParent);
    }
    
    public void centerHorzAndStick(Component pComponent)
    {
      SpringUtils.centerHorzAndStick(mSpringLayout, pComponent, mInset, mParent);
    }
  }
  
  public static void stickToAllSides(SpringLayout sl, Component c, int inset, Component p)
  {
    stickToSides(sl, c, inset, p);
    stickToTopBottom(sl, c, inset, p);
  }
  
  public static void centerHorzAndStick(SpringLayout sl, Component c, int inset, Component p)
  {
    sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, c, 0, SpringLayout.HORIZONTAL_CENTER, p);
    stickToSides(sl, c, inset, p);
  }
  public static void stickToSides(SpringLayout sl, Component c, int inset, Component p)
  {
    sl.putConstraint(SpringLayout.WEST, c, inset, SpringLayout.WEST, p);
    sl.putConstraint(SpringLayout.EAST, c, -inset, SpringLayout.EAST, p);
  }
  
  public static void stickToTopBottom(SpringLayout sl, Component c, int inset, Component p)
  {
    sl.putConstraint(SpringLayout.NORTH, c, inset, SpringLayout.NORTH, p);
    sl.putConstraint(SpringLayout.SOUTH, c, -inset, SpringLayout.SOUTH, p);
  }
}
