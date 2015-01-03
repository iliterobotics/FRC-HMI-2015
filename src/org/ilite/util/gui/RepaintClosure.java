package org.ilite.util.gui;

import java.awt.Component;

import javax.swing.SwingUtilities;

public class RepaintClosure {
  private final Component mComponent; 
	public RepaintClosure(final Component pComponent)
	{
	  this(pComponent, true);
	}
	
	public RepaintClosure(Component pComponent, boolean pExecuteNow)
	{
    mComponent = pComponent;
    if(pExecuteNow) repaint();	  
	}
	
	public final void repaint()
	{
    SwingUtilities.invokeLater(new Runnable() {
      
      @Override
      public void run() {
        mComponent.repaint();
      }
    });
	}
}
