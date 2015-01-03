package org.ilite.util.gui.components.layer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;


public class LayeredPanel extends JPanel{
	private List<AbstractLayer> mObjectDrawers = Collections.synchronizedList(new ArrayList<AbstractLayer>());
	private final Comparator<AbstractLayer> mComparator = new DrawLayerComparator();
	private boolean mEnableAntiAlias = false;
	
	public void setAntiAliasEnabled(boolean pEnabled)
	{
	  mEnableAntiAlias = pEnabled;
	}
	
	public void addDrawLayer(AbstractLayer pLayer)
	{
		mObjectDrawers.add(pLayer);
		Collections.sort(mObjectDrawers, mComparator);
	}
	
	public void removeLastDrawLayer()
	{
		mObjectDrawers.remove(mObjectDrawers.size()-1);
		Collections.sort(mObjectDrawers, mComparator);
	}
	
	public void setPreferredSize(Dimension d)
	{
	  super.setPreferredSize(d);
	  for(AbstractLayer al : mObjectDrawers)
	  {
	    al.setPreferredSize(d);
	  }
	}
	
	public Component add(Component c)
	{
	  if(c instanceof AbstractLayer)
	  {
	    addDrawLayer((AbstractLayer)c);
	  }
	  else
	  {
	    throw new IllegalArgumentException("Cannot use add(Component) with a Layered Panel.  Use addDrawLayer(AbstractLayer) instead.");
	  }
	  return c;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		Object r = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		List<AbstractLayer> layers = new ArrayList<AbstractLayer>(mObjectDrawers);
		for(AbstractLayer layer : layers)
		{
			layer.paint(g);
		}
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, r);
	}
	
	private class DrawLayerComparator implements Comparator<AbstractLayer>
	{
		@Override
		public int compare(AbstractLayer o1, AbstractLayer o2) {
			return o1.getPriority().compareTo(o2.getPriority());
		}
	}
}
