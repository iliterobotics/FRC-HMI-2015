package org.ilite.util.gui.components.tabbedpane;

import org.ilite.util.lang.IUpdate;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

public class DynamicTabbedPane
{
  private static final String sADD_TAB_SYMBOL = " + ";

  private JTabbedPane mTabbedPane = new JTabbedPane();
  private final IDynamicTabProvider mTabProvider;
  
  public static void createAndFillTabs(final String pName, IDynamicTabProvider pProvider, JPanel pContainer)
  {
	  DynamicTabbedPane dtp = new DynamicTabbedPane(pName, pProvider);

	    if(pContainer != null)
	    {
	    	SpringLayout sl = new SpringLayout();
	    	pContainer.setLayout(sl);
	    	JComponent c = dtp.getDisplayComponent();
			pContainer.add(c);
			sl.putConstraint(SpringLayout.WEST, c, 5, SpringLayout.WEST, pContainer);
			sl.putConstraint(SpringLayout.NORTH, c, 5, SpringLayout.NORTH, pContainer);
			sl.putConstraint(SpringLayout.EAST, c, -5, SpringLayout.EAST, pContainer);
			sl.putConstraint(SpringLayout.SOUTH, c, -5, SpringLayout.SOUTH, pContainer);
	    }
  }

  public DynamicTabbedPane(final String pName, IDynamicTabProvider pProvider)
  {
	    mTabProvider = pProvider;
	    mTabbedPane.addTab(pName, mTabProvider.getDescriptionPanel());
	    mTabbedPane.addTab(sADD_TAB_SYMBOL, mTabProvider.getAddTabPanel());

	    mTabProvider.addAddTabDataListener(new IUpdate<AddTabData>()
	    {
	      @Override public void update(AddTabData pData)
	      {
	        addTab(pData);
	      }
	        });
  }
  
  public JComponent getDisplayComponent()
  {
	  return mTabbedPane;
  }

  private void addTab(AddTabData pData)
  {
    JPanel panel = mTabProvider.generatePanel(pData);
    String title = mTabProvider.getTabTitle(pData);
    int index = mTabbedPane.getTabCount()-1;
    mTabbedPane.insertTab(title, null, panel, title, index);
    mTabbedPane.setSelectedIndex(index);
  }
}
