package org.ilite.util.gui.components;

import org.ilite.util.gui.components.layer.AbstractLayer;
import org.ilite.util.gui.components.layer.EDrawPriority;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class AlignmentCursorOverlay extends AbstractLayer{
	private final JPanel mPanel;
	private final JSlider mSlider;
	private Stroke mStroke = new BasicStroke(2);
	private Color mColor = Color.red;

	public AlignmentCursorOverlay(JPanel pParent, JSlider pControlSlider)
	{
		mPanel = pParent;
		mSlider = pControlSlider;
//		mSlider.addChangeListener(new ChangeListener()
//		{
//			@Override
//			public void stateChanged(ChangeEvent arg0) {
//				new RepaintClosure(mPanel);
//			}			
//		});
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		Dimension d = mPanel.getSize();
		int orient = mSlider.getOrientation();
		int value =	mSlider.getValue();
		int px = 0;
		
		
		g2.setColor(mColor);
		g2.setStroke(mStroke);
		
		if(orient == JSlider.HORIZONTAL)
		{	// Draw vertical line
		  px = (int)((double)value / (double)(mSlider.getMaximum() - mSlider.getMinimum()) * d.width);
			g2.drawLine(px, 0, px, d.height);
		}
		else
		{	// Draw Horizontal line -- little tricky since a vertical slider
			// hits the minimum at the bottom, while a panel's minimum (zero) is
			// at the top
		  px = (int)((double)value / (double)(mSlider.getMaximum() - mSlider.getMinimum()) * d.height);
			px = d.height - px;
			g2.drawLine(0, px, d.width, px);
		}
	}
	
	public void setLineStroke(Stroke pStroke)
	{
		mStroke = pStroke;
	}
	
	public void setColor(Color pColor)
	{
		mColor = pColor;
	}

	@Override
	public EDrawPriority getPriority() {
		return EDrawPriority.CURSORS;
	}
	
}
