package org.ilite.util.date;

public class DateConstants {
   
   public static final int
      NUM_WEEKS_PER_YEAR = 52,
      NUM_MILLIS_PER_SECOND = 1000,
      NUM_SECS_PER_MIN = 60,
      NUM_MINS_PER_HR = 60,
      NUM_HRS_PER_DAY = 24,
      NUM_DAYS_PER_YR = 365,
      NUM_DAYS_PER_WEEK = 7,
      NUM_DAYS_PER_4YRS = NUM_DAYS_PER_YR*4 + 1
      ;

   
   //=========================================================================
   // Static classes for indices
   //=========================================================================

   public static final DateConstants      
   INDEX_DAY = new DateConstants(NUM_HRS_PER_DAY * NUM_MINS_PER_HR * 
         NUM_SECS_PER_MIN * NUM_MILLIS_PER_SECOND)
   
   ,         
   INDEX_WEEK = new DateConstants(INDEX_DAY.conv * NUM_DAYS_PER_WEEK)
   
   ,         
   INDEX_MONTH = new DateConstants(INDEX_WEEK.conv * 4),
   
   INDEX_YEAR = new DateConstants(INDEX_DAY.conv * 365);
   
   ;
   
   
   /*
    DAY(Calendar.DAY_OF_YEAR, 1),
   WEEKDAY(Calendar.DAY_OF_WEEK, 1),
   WEEK(Calendar.WEEK_OF_YEAR, 1),
   BI_WEEKLY(Calendar.WEEK_OF_YEAR, 2),
   MONTH(Calendar.MONTH, 1),
   SINGULAR(-1, -1);
    */
   

   //=========================================================================
   // The immutable
   //=========================================================================
   private final long conv;
   
   /* package */ long conv(){
      return conv;
   }
   
   private DateConstants(long pConversion){
      conv = pConversion;
   }

}
