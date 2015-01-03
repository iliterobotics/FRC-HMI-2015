package org.ilite.util.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;
import org.ilite.util.lang.reflection.DataStructureReader;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;

public class SimpleDataPrinter
{
  private final DataStructureReader mReader;
  private final JPanel mPanel;
  private final JTextArea mTextArea;
  private static final Executor sTHREAD = Executors.newSingleThreadExecutor();
  private final JLabel mMemLabel = new JLabel();
  private boolean mKeepHistory = false;
  private static final NumberFormat sMEMFORMAT = new DecimalFormat(" -- #,000.0 MB");
  private static final NumberFormat sUSAGEFORMAT = new DecimalFormat(" 00.0%");
  private static final double BYTES_PER_MB = 1024*1024;
  
  public SimpleDataPrinter(DataStructureReader pReader)
  {
    mReader = pReader;
    SpringLayout sl = new SpringLayout();
    mPanel = new JPanel(sl);
    mTextArea = new JTextArea();
    mTextArea.setEditable(false);
    mTextArea.setFont(new Font("Monospace", Font.PLAIN, 10));
    
    JScrollPane jsp = new JScrollPane(mTextArea);
    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
    final JToggleButton hist = new JToggleButton("KEEP HISTORY");
    hist.addActionListener(new ActionListener()
    {      
      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        mKeepHistory = hist.isSelected();
      }
    });
    
    int inset = 5;
    int height = hist.getPreferredSize().height + 2 * inset;
    
    mPanel.add(jsp);
//    mPanel.add(hist);
    mPanel.add(mMemLabel);
    
    sl.putConstraint(WEST, jsp, inset, WEST, mPanel);
    sl.putConstraint(NORTH, jsp, inset, NORTH, mPanel);
    sl.putConstraint(EAST, jsp, -inset, EAST, mPanel);
    sl.putConstraint(SOUTH, jsp, -height, SOUTH, mPanel);
    
    sl.putConstraint(NORTH, hist, inset, SOUTH, jsp);
    sl.putConstraint(NORTH, mMemLabel, inset, SOUTH, jsp);
    sl.putConstraint(WEST, mMemLabel, inset, WEST, mPanel);
    sl.putConstraint(EAST, hist, -inset, EAST, mPanel);
  }
  
  public SimpleDataPrinter()
  {
    this(new DataStructureReader());
  }
  
  public JPanel getPanel()
  {
    return mPanel;
  }
  
  public void printObject(final Object pInstance)
  {
//    sTHREAD.execute(new Runnable()
//    {      
//      @Override
//      public void run()
//      {
        String result = null;
//        synchronized(mReader)
//        {
          mReader.read(pInstance);
          result = mReader.getResult();
//        }
        setData(result);
//      }
//    });
  }
  
  private void setData(final String pText)
  {
    if(mKeepHistory)
    {
      mTextArea.setText(mTextArea.getText() + pText + '\n');
    }
    else
    {
      mTextArea.setText(pText);
    }        

    double max = Runtime.getRuntime().maxMemory();
    double total = Runtime.getRuntime().totalMemory();
    double free = Runtime.getRuntime().freeMemory();
    mMemLabel.setText("Mem: " + 
        sUSAGEFORMAT.format(free / max) + 
        sMEMFORMAT.format((total - free)/BYTES_PER_MB));
  }
  
}
