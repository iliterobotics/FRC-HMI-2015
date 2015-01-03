package org.ilite.util.math;

import org.ilite.util.lang.FixedSizeQueue;

public class RunningAverage <N extends Number>
{
  protected double mValue = 0.0;
  protected double mMaxAvg = 0.0;
  protected double mMinAvg = 0.0;
  protected double mMaxValue = 100.0;
  protected double mMinValue = 0.0;
  private FixedSizeQueue<N> mAveragedValues = new FixedSizeQueue<>(1);

  public RunningAverage()
  {
    
  }
  
  public double getMaxAvg()
  {
    return mMaxAvg;
  }
  
  public double getMinAvg()
  {
    return mMinAvg;
  }
  
  public double getRunningAverage()
  {
    return mValue;
  }
  
  public N getLatest()
  {
    return mAveragedValues.getFirst();
  }
  
  public void setMax(double pMax)
  {
    mMaxValue = pMax;
    mMinAvg = pMax;
  }
  
  public void setMin(double pMin)
  {
    mMinValue = pMin;
    mMaxAvg = pMin;
  }
  
  public void setRunningAverage(int pNumToAverage)
  {
    int num = Math.max(pNumToAverage, 1);
    mAveragedValues.setQueueSize(num);    
  }
  
  public final void resetMinMaxAverages()
  {
    mMinAvg = mMaxValue;
    mMaxAvg = mMinValue;
  }

  public void update(N pObject)
  {
    mAveragedValues.addLast(pObject);
    double avg = 0.0;
    for(int i = 0; i < mAveragedValues.size(); i++)
    {
      double d = mAveragedValues.get(i).doubleValue();
      avg += d;
    }
    avg /= mAveragedValues.size();
    mValue = avg;
    mMinAvg = Math.min(mMinAvg, mValue);
    mMaxAvg = Math.max(mMaxAvg, mValue);
  }
}
