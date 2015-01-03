package org.ilite.telemetry.drivers;

//Removed this import in favor of org.ilite import -- fromSeconds method supported
//import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.ilite.util.lang.Delegator;
import org.ilite.util.physics.concepts.Hertz;
import org.ilite.util.physics.concepts.Time;

public abstract class AbstractPeriodicDataDriver <T> 
extends Delegator<T> implements IDataDriver<T>
{
  private long mPeriodMs = 20;
  
  private Timer mDataTimer = new Timer();
  private TimerTask mCurrentTask = null;
  private final Executor mUpdateExecutor;
  
  /**
   * Provides a constructor to directly enforce a specific update rate in ms
   * 
   * @param pGenerator
   * @param pPeriodMs
   */
  public AbstractPeriodicDataDriver(long pPeriodMs)
  {
    mUpdateExecutor = Executors.newCachedThreadPool();
  }
  
  /**
   * Creates a data generator with a default update rate (50Hz, or 20ms)
   * @param pGenerator
   */
  public AbstractPeriodicDataDriver()
  {
    this(20l);
  }
  
  @Override
  public synchronized void cancel()
  {
    if(mCurrentTask != null)
    {
      mCurrentTask.cancel();
      mCurrentTask = null;
    }
  }
  
  /**
   * Starts the data generator.  If the 
   */
  @Override
  public synchronized void start()
  {
    cancel();
    
    mCurrentTask = new TimerTask()
    {
      private long mStartTime = System.currentTimeMillis();
      @Override
      public void run()
      {
        long timeMs = System.currentTimeMillis() - mStartTime;
        final T data = generate((float)((float)timeMs/1000f));
        mUpdateExecutor.execute(new Runnable()
        {          
          @Override
          public void run()
          {
            update(data);
          }
        });
      }
    };
    System.out.println(mPeriodMs);
    mDataTimer.scheduleAtFixedRate(mCurrentTask, mPeriodMs, mPeriodMs);
  }
  
  /**
   * Sets the generation rate in Hz.  Under the covers this is converted to a
   * truncated long (i.e. 16Hz = 6.6667ms, which is then 6ms).  So it's best to
   * use something that will round nicely.
   * @param pHz
   */
  public void setDataRate(Hertz pHz)
  {
    boolean restart = mCurrentTask != null;
    cancel();
    mPeriodMs = (long)(pHz.period().seconds() * 1000f);
    if(restart)
    {
      start();
    }
  }

  /**
   * @return the data rate after the resulting truncation.  
   * Useful for detecting rounding errors that seem to be throwing things off 
   */
  public Hertz getDataRate()
  {
    return Hertz.fromPeriod(Time.fromSeconds((float)(mPeriodMs / 1000f)));
  }
}
