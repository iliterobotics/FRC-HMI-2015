package org.ilite.util.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public abstract class ExpandingPanel {
	
	final protected JPanel mPanel = new JPanel();
	final protected JPanel mPopupPanel = new JPanel();
	final private JFrame mPopup = new InternalWindow();
	final private EPanelOrientation  mOrientation;
	
	final protected static int INSET = 5;
	private static final Border sLOWERED_BORDER = BorderFactory.createLoweredBevelBorder();
	private static final Border sRAISED_BORDER = BorderFactory.createRaisedBevelBorder();	
	
	public ExpandingPanel(EPanelOrientation pOrientation)
	{
		mOrientation = pOrientation;
		
		mPopup.setAlwaysOnTop(true);					
		mPopup.setContentPane(mPopupPanel);
		mPopupPanel.setBorder(sRAISED_BORDER);
		mPanel.setBorder(sRAISED_BORDER);

				
		mPanel.addMouseListener(new MouseAdapter(){
			@Override public void mouseClicked(MouseEvent me)
			{
				boolean setvisible = !mPopup.isVisible();
				if(setvisible)
				{
					mPanel.setBorder(sLOWERED_BORDER);
					mPopupPanel.revalidate();
					mPopupPanel.setPreferredSize(getPopupPreferredSize());
					mPopup.pack();
					mPopup.setVisible(true);
				}
				else
				{
					hidePopup();
				}
			}
		});
		
	}
	
	/**
	 * Provides a mechanism to get the popup panel.  This allows ExpandingPanels
	 * to be developed for legacy internal panels while still providing legacy
	 * features to use the old panels.
	 * @return the internal panel
	 */
	public JPanel getInternalPanel()
	{
	  return mPopupPanel;
	}
	
	public void setBackground(Color pColor)
	{
		mPanel.setBackground(pColor);
		mPopupPanel.setBackground(pColor);
		mPopup.setBackground(pColor);
	}
	
	public final JPanel getPanel()
	{
		return mPanel;
	}
	
	protected void revalidate()
	{
	  if(!SwingUtilities.isEventDispatchThread())
	  {
	    SwingUtilities.invokeLater(new Runnable()
      {
        @Override public void run()
        {
          revalidate();
        }
      });
	  }
	  mPanel.validate();
	  mPopupPanel.validate();
	  mPanel.repaint();
	  mPopupPanel.repaint();
	}
	
	protected final void hidePopup()
	{
		mPopup.setVisible(false);
		mPanel.setBorder(sRAISED_BORDER);
	}
	
	protected abstract Dimension getPopupPreferredSize();
		
	private class InternalWindow extends JFrame
	{
		private InternalWindow()
		{
			setUndecorated(true);
		}
		
		@Override public void setVisible(boolean pVisible)
		{
			if(pVisible)
			{
				checkLocation();
			}
			super.setVisible(pVisible);
		}
		
		private void checkLocation()
		{
			Dimension panelSize = mPanel.getSize();
			Dimension popupSize = getPopupPreferredSize();
			Point panelLocation = mPanel.getLocationOnScreen();
			Point popupLocation = new Point();
			
			switch(mOrientation)
			{
			case UP_CENTER:
				popupLocation.x = panelLocation.x + panelSize.width/2 - popupSize.width/2;
				popupLocation.y = panelLocation.y - popupSize.height;
				popupSize.width = Math.max(panelSize.width, popupSize.width);
				break;
			case UP_LEFT:
				popupSize.width = Math.max(panelSize.width, popupSize.width);
				popupLocation.x = panelLocation.x + panelSize.width - popupSize.width;
				popupLocation.y = panelLocation.y - popupSize.height;
				break;
			case UP_RIGHT:
				popupLocation.x = panelLocation.x;
				popupLocation.y = panelLocation.y - popupSize.height;
				popupSize.width = Math.max(panelSize.width, popupSize.width);
				break;
			case DOWN_CENTER:
				popupLocation.x = panelLocation.x + panelSize.width/2 - popupSize.width/2;
				popupLocation.y = panelLocation.y + panelSize.height;
				popupSize.width = Math.max(panelSize.width, popupSize.width);
				break;
			case DOWN_LEFT:
				popupSize.width = Math.max(panelSize.width, popupSize.width);
				popupLocation.x = panelLocation.x + panelSize.width - popupSize.width;
				popupLocation.y = panelLocation.y + panelSize.height;
				break;
			case DOWN_RIGHT:
				popupLocation.x = panelLocation.x;
				popupLocation.y = panelLocation.y + panelSize.height;
				popupSize.width = Math.max(panelSize.width, popupSize.width);
				break;
			case LEFT_CENTER:
				popupLocation.x = panelLocation.x - popupSize.width;
				popupLocation.y = panelLocation.y + panelSize.height/2 - popupSize.height/2;
				popupSize.height = Math.max(panelSize.height, popupSize.height);
				break;
			case RIGHT_CENTER:
				popupLocation.x = panelSize.width;
				popupLocation.y = panelLocation.y + panelSize.height/2 - popupSize.height/2;
				popupSize.height = Math.max(panelSize.height, popupSize.height);
				break;
			case CENTERED:
				popupLocation.x = panelSize.width/2 - popupSize.width/2 + panelLocation.x;
				popupLocation.y = panelSize.height/2 - popupSize.height/2 + panelLocation.y;
				popupSize.width = Math.max(panelSize.width, popupSize.width);
				popupSize.height = Math.max(panelSize.height, popupSize.height);
				break;
			}
			
			mPopup.setPreferredSize(popupSize);
			mPopup.setSize(popupSize);
			mPopup.setLocationRelativeTo(mPanel);
			mPopup.setLocation(popupLocation);
		}
	}
}
