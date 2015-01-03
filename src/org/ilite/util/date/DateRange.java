package org.ilite.util.date;

import java.util.Calendar;
import java.util.Date;

public enum DateRange {
   Wk1("1 Week", 5),
   Wk2("2 Weeks", 5),
   Wk3("3 Weeks", 3),
   Mo1("1 Month", 4),
   Mo2("2 Month", 2),
   Mo3("3 Months", 3),
   Mo6("6 Months", 6),
   Mo9("9 Months", 9),
   Yr1("1 Year", 12);
   
   public String toString(){
      return mTitle;
   }
   
   public Date goBackFrom(Date pDate){
      int calendarconst = 1;
      int value = 0;
      switch(this){
      case Mo1:
         calendarconst = Calendar.MONTH;
         value = 1;
         break;
      case Mo2:
         calendarconst = Calendar.MONTH;
         value = 2;
         break;
      case Mo3:
         calendarconst = Calendar.MONTH;
         value = 3;
         break;
      case Mo6:
         calendarconst = Calendar.MONTH;
         value = 6;
         break;
      case Mo9:
         calendarconst = Calendar.MONTH;
         value = 9;
         break;
      case Wk1:
         calendarconst = Calendar.DAY_OF_MONTH;
         value = 7;
         break;
      case Wk2:
         calendarconst = Calendar.DAY_OF_MONTH;
         value = 14;
         break;
      case Wk3:
         calendarconst = Calendar.DAY_OF_MONTH;
         value = 21;
         break;
      case Yr1:
         calendarconst = Calendar.YEAR;
         value = 1;
         break;
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(pDate);
      cal.add(calendarconst, -1*value);
      return cal.getTime();
   }
   
   public int getNumTicksToShow(){
      return mNumTicks;
   }
   
   private DateRange(String pTitle, int pNumTicksToShow){
      mTitle = pTitle;
      mNumTicks = pNumTicksToShow;
   }
   private String mTitle;
   private int mNumTicks;
}
