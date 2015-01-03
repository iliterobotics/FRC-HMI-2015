package org.ilite.telemetry.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

public class DataDriverToggleButton extends JToggleButton
{
  private static final String sSTART_TEXT = "Start Data Driver";
  private static final String sSTOP_TEXT = "Stop Data Driver";
  
  public DataDriverToggleButton(final IDataDriver pDataDriver)
  {
    super(sSTART_TEXT);
    addActionListener(new ActionListener()
    {      
      @Override
      public void actionPerformed(ActionEvent e)
      {
        switch(getText())
        {
        case sSTART_TEXT:
          pDataDriver.cancel();
          setText(sSTOP_TEXT);
          break;
        case sSTOP_TEXT:
          pDataDriver.start();
          setText(sSTART_TEXT);
          break;
        }
      }
    });
  }
}
