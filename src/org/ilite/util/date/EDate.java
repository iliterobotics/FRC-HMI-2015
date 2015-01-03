package org.ilite.util.date;

import java.util.Calendar;

public enum EDate{
   DAY(Calendar.DAY_OF_YEAR, 1, DateConstants.INDEX_DAY.conv()),
   WEEKDAY(Calendar.DAY_OF_WEEK, 1, DateConstants.INDEX_DAY.conv()),
   WEEK(Calendar.WEEK_OF_YEAR, 1, DateConstants.INDEX_WEEK.conv()),
   BI_WEEKLY(Calendar.WEEK_OF_YEAR, 2, DateConstants.INDEX_WEEK.conv()*2),
   MONTH(Calendar.MONTH, 1, DateConstants.INDEX_MONTH.conv()),
   SINGULAR(-1, -1, 1);
   
   public int interval(){ return mSpacing; }
   public int cvalue(){ return mValue; }
   public long millisPer(){ return mMillisPer; }
   private EDate(int pCalendarMapping, int pSpacing, long pMillisPer){
      mValue = pCalendarMapping;
      mSpacing = pSpacing;
      mMillisPer = pMillisPer;
   }
   private int mSpacing;
   private int mValue;
   private long mMillisPer;
}