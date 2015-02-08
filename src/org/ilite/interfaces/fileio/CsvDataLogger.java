package org.ilite.interfaces.fileio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.ilite.util.lang.ICsvData;
import org.ilite.util.lang.IUpdate;
import org.ilite.util.logging.ILog;
import org.ilite.util.logging.Logger;

public class CsvDataLogger <Data extends ICsvData>
implements IUpdate<Data>
{
  private final ILog mLog = Logger.createLog(CsvDataLogger.class);
  private BufferedWriter mWriter;
  private boolean mHeaderWritten = false;
  private final String mOutputPath, mOutputFile;
  
  public CsvDataLogger(String pFilePath, String pFileName)
  {
    mOutputPath = pFilePath;
    mOutputFile = pFileName;
  }
  
  public void init() throws IOException
  {
    File dir = new File(mOutputPath);
    if(!dir.exists())
    {
      dir.mkdirs();
    }
    File outputFile = new File(mOutputPath, mOutputFile+".csv");
    if(!outputFile.exists())
    {
      outputFile.createNewFile();
    }
    mWriter = new BufferedWriter(new FileWriter(outputFile));
  }
  
  /**
   * @return the location of this csv file
   */
  public String getOutputPath()
  {
    return mOutputPath;
  }

  @Override
  public void update(Data pObject)
  {
    try
    {
      if(!mHeaderWritten)
      {
        mWriter.write(pObject.getCsvColumnNames());
        mWriter.newLine();
        mHeaderWritten = true;
      }
      mWriter.write(pObject.toCsvString());
      mWriter.newLine();
    } catch (IOException e)
    {
      mLog.exception(e);
      close();
    }
  }

  public void close()
  {
    if(mWriter != null)
    {
      try
      {
        mWriter.close();
      }
      catch(IOException e)
      {
        mLog.exception(e);
      }
    }
  }
}
