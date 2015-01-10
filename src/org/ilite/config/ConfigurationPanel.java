package org.ilite.config;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class ConfigurationPanel {  
  private JPanel mContainer = new JPanel();
  private JComponent mScrollPane;
  private final ISystemConfiguration mConfig;
  
  public ConfigurationPanel(SystemConfiguration pConfig)
  {
    mConfig = pConfig;
    mContainer.setPreferredSize(new Dimension(1024, 600));
    JTable table = new JTable(pConfig);
    JScrollPane jsp = new JScrollPane(table);
    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
    JButton button = new JButton("Write Configuration To File");
    SpringLayout sl = new SpringLayout();
    mContainer.setLayout(sl);
    mContainer.add(jsp);
    mContainer.add(button);
    
    int inset = 40;
    sl.putConstraint(SpringLayout.SOUTH, button, -inset/2, SpringLayout.SOUTH, mContainer);
    sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, button, 0, SpringLayout.HORIZONTAL_CENTER, mContainer);
    
    sl.putConstraint(SpringLayout.WEST, jsp, inset, SpringLayout.WEST, mContainer);
    sl.putConstraint(SpringLayout.NORTH, jsp, inset, SpringLayout.NORTH, mContainer);
    sl.putConstraint(SpringLayout.SOUTH,  jsp, -inset/2, SpringLayout.NORTH, button);
    sl.putConstraint(SpringLayout.EAST, jsp, -inset, SpringLayout.EAST, mContainer);
    table.setRowHeight(inset);
    
    
    mScrollPane = jsp;
    
    button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        applyConfiguration();
      }
    });
  }
  
  public JComponent getPanel()
  {
    return mContainer;
  }
  
  private void applyConfiguration()
  {
    mConfig.writeToFile();
  }
}
