package org.ilite.util.image;

import org.ilite.util.lang.Delegator;
import org.ilite.util.lang.IProvider;
import org.ilite.util.lang.IUpdate;
import org.ilite.util.logging.ILog;
import org.ilite.util.logging.Logger;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.imageio.ImageIO;

public class ImageDecoder implements IUpdate<byte[]>, IProvider<BufferedImage> {
	private final ILog mLog = Logger.createLog(ImageDecoder.class);
	private Delegator<BufferedImage> mImageUpdateListeners = new Delegator<BufferedImage>();
	private final ExecutorService mReadExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
		@Override
		public Thread newThread(Runnable arg0) {
			return new Thread(arg0, "IMAGE DECODER THREAD");
		}
	});
	private LinkedList<BufferedImage> mUnprocessedImages = new LinkedList<BufferedImage>();
	
	public ImageDecoder()
	{
		
	}
	
	public ImageDecoder(IProvider<byte[]> pDataProvider)
	{
		pDataProvider.addListener(this);
	}
	
	@Override
	public void update(final byte[] pObject) {
		mReadExecutor.submit(new Runnable() {			
			@Override
			public void run() {
				try
				{
					BufferedImage image = ImageIO.read(new ByteArrayInputStream(pObject));
					mLog.debug("Image dimension: ", image.getWidth(), "x", image.getHeight());
					mUnprocessedImages.add(image);
					mImageUpdateListeners.update(image);
				} 
				catch (IOException e)
				{
					mLog.exception(e);
					mUnprocessedImages.add(null);
				}
			}
		});
	}

  @Override
  public void addListener(IUpdate<BufferedImage> pListener)
  {
    mImageUpdateListeners.addListener(pListener);    
  }

  @Override
  public void removeListener(IUpdate<BufferedImage> pListener)
  {
    mImageUpdateListeners.removeListener(pListener);
  }
  
  @Override
  public BufferedImage getLatest()
  {
    return mImageUpdateListeners.getLatest();
  }
}
