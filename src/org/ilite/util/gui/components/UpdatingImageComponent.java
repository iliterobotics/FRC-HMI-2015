package org.ilite.util.gui.components;

import org.ilite.util.gui.RepaintClosure;
import org.ilite.util.gui.components.layer.AbstractLayer;
import org.ilite.util.gui.components.layer.EDrawPriority;
import org.ilite.util.gui.components.layer.LayeredPanel;
import org.ilite.util.lang.IUpdate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class UpdatingImageComponent extends AbstractLayer implements IUpdate<BufferedImage>
{
	private BufferedImage mImage = null;
	private final int mWidth;
	private final int mHeight;
	private final JPanel mParent;
//	private final ILog mLog = Logger.createLog(UpdatingImageComponent.class);
	

	public UpdatingImageComponent(LayeredPanel pParent, int pWidth, int pHeight)
	{
		mWidth = pWidth;
		mHeight = pHeight;
		mParent = pParent;
		pParent.addDrawLayer(this);
	}

	public void update(BufferedImage pImage)
	{
		mImage = pImage;
		new RepaintClosure(mParent);
	}

	@Override
	public void paint(Graphics g)
	{
		if (mImage == null)
		{
			g.drawString("Image Not Found", 20, 20);
		} else
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
			g2.drawImage(mImage, 0, 0, mWidth, mHeight, null);
		}
	}

	@Override
	public EDrawPriority getPriority() {
		return EDrawPriority.LOWEST_LAYER;
	}
}