package org.ilite.util.gui;

import java.awt.Container;

import javax.swing.SwingUtilities;

public class RevalidationClosure
{
  public RevalidationClosure(final Container c)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        c.validate();
        c.repaint();
      }
    });
  }
}
