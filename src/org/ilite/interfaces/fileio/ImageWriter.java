package org.ilite.interfaces.fileio;

import org.ilite.util.lang.IUpdate;
import org.ilite.util.logging.ILog;
import org.ilite.util.logging.Logger;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

public class ImageWriter implements IUpdate<BufferedImage>
{
  private final Executor mFileThread = Executors.newSingleThreadExecutor();
  private final String mImageBaseName;
  private final String mFilePath;
  private final String mImageFormat;
  private ILog mLog = Logger.createLog(ImageWriter.class);
  
  public ImageWriter(String pFilePath, String pBaseName, String pImageFormat)
  {
    mImageBaseName = pBaseName;
    mFilePath = pFilePath;
    mImageFormat = pImageFormat;
  }

  @Override
  public void update(final BufferedImage pImage)
  {    
    mFileThread.execute(new Runnable()
    {      
      @Override
      public void run()
      {
        try
        {
          String filename = mImageBaseName + "_" + System.currentTimeMillis();
          File f = new File(mFilePath, filename);
          ImageIO.write(pImage, mImageFormat, f);          
        } catch (IOException e)
        {
          mLog.exception(e);
        }
      }
    });
  }
	
}
