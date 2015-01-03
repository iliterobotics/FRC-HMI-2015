package org.ilite.util.gui;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class GuiFactory
{
  public static final GuiFactory INST = new GuiFactory();
  private GuiFactory(){}
  
  private Map<Component, Timer> mRepaintTimers = new HashMap<Component, Timer>();
  
  public static JFrame createanddisplayDefaultJFrame(String pTitle, Container pContentPane)
  {
    JFrame aFrame = new JFrame("TESTING");
    aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    aFrame.setContentPane(pContentPane);
    aFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    aFrame.pack();
    aFrame.setVisible(true);
    return aFrame;
  }
  
  public Timer createAutoRepaintTimer(double pFrameRate, final Component pParent)
  {
    Timer t = mRepaintTimers.get(pParent);
    
    if(t == null)
    {
      t = new Timer("REPAINT TIMER");
      TimerTask tt = new TimerTask()
      {      
        @Override
        public void run()
        {
        	pParent.repaint();
        }
      };
      long pSleepTime = (long)(1000.0/pFrameRate) - 1L;
      t.scheduleAtFixedRate(tt, 1000, pSleepTime);
      mRepaintTimers.put(pParent, t);
    }
    return t;
  }

}
