package org.ilite.util.gui.components.tabbedpane;

import org.ilite.util.lang.IUpdate;

import javax.swing.JPanel;

public interface IDynamicTabProvider
{
  public JPanel getDescriptionPanel();
  
  public JPanel getAddTabPanel();
  
  public void addAddTabDataListener(IUpdate<AddTabData> pListener);
  
  public JPanel generatePanel(AddTabData pData);
  
  public String getTabTitle(AddTabData pData);
}
