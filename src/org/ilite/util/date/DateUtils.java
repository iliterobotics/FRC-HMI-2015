package org.ilite.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public abstract class DateUtils{
   public static final DateFormat sGooGformat = new SimpleDateFormat("dd-MMM-yy");

   
   public static Date[] getListOfDates(Date pStartDate, int pNumDates, EDate pIntervalType){
      if(pNumDates < 1)
         throw new IllegalArgumentException("Received an invalid number of dates " + pNumDates);      
      Date[] results = new Date[pNumDates];
      Calendar cal = Calendar.getInstance();
      cal.setTime(pStartDate);
      for(int i = 0; i < pNumDates; i++){
         cal.add(pIntervalType.cvalue(), 1);
         results[i] = cal.getTime();         
      }      
      return results;
   }
   
   static public int getNumIntervals(Date pStartDate, Date pEndDate, EDate pIntervalType){
      Calendar cal = Calendar.getInstance();
      cal.setTime(pStartDate);
      boolean found = false;
      int numIntervals = 0;
      while(!found){
         numIntervals++;
         cal.add(pIntervalType.cvalue(), 1);
         found = (cal.getTime().after(pEndDate));
      }
      return numIntervals;
   }
   
   static public int getNumIntervals(long pStartDate, long pEndDate, EDate pIntervalType){
      return getNumIntervals(
            date(pStartDate, pIntervalType.millisPer()), 
            date(pEndDate, pIntervalType.millisPer()), 
            pIntervalType);
   }
   
   static public long index(EMonth pMonth, int pDayInMonth, int pYear,
                            DateConstants pConversion){
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.MONTH, pMonth.getMonthOfYear());
      cal.set(Calendar.DAY_OF_MONTH, pDayInMonth);
      cal.set(Calendar.YEAR, pYear);
      return index(cal.getTime(), pConversion);
   }
   
   static public long index(Date pDate, long pConversionIndex){
      return pDate.getTime() / pConversionIndex;
   }
   
   static public long index(Date pDate, DateConstants pConversion){
      return index(pDate,  pConversion.conv());
   }
   
   static public Date date(long pIndex, long pConversionIndex){
      return new Date(pIndex * pConversionIndex);
   }
   
   static public Date date(long pIndex, DateConstants pConversion){
      return date(pIndex, pConversion.conv());
   }
   
   static public Date getDayInWeek(Date pDate, EWeekDay pTargetDay){
      Calendar cal = Calendar.getInstance();
      cal.setTime(pDate);
      EWeekDay day = EWeekDay.getFromCalInt(cal.get(EDate.WEEKDAY.cvalue()));
      cal.add(EDate.WEEKDAY.cvalue(), pTargetDay.ordinal() - day.ordinal());
      return cal.getTime();
   }
   
   static public List<Long> getDateRange(long pStartDate, long pEndDate, EDate pTypeOfGet, int pInterval){
      List<Long> results = new ArrayList<Long>();      
      List<Date> dates = 
         getDateRange(date(pStartDate, pTypeOfGet.millisPer()), date(pEndDate, pTypeOfGet.millisPer()), pTypeOfGet, pInterval);
      for(Date d : dates){
         results.add(index(d, pTypeOfGet.millisPer()));
      }
      return results;
   }
   
   static public List<Date> getDateRange(Date pStartDate, Date pEndDate, EDate pType, int pInterval){
      List<Date> results = new ArrayList<Date>();
      
      Calendar cal = Calendar.getInstance();
      cal.setTime(pStartDate);
      Date curdate = pStartDate;
      while(curdate.before(pEndDate)){
         results.add(curdate);
         cal.add(pType.cvalue(), pInterval);
         curdate = cal.getTime();
      }
      return results;
   }
   
   static public long getFutureDate(long pStartDate, EDate pTypeOfGet, int pNumberOfIntervals){
      return 
      index(
            getFutureDate(
                  date(
                        pStartDate, pTypeOfGet.millisPer()
                  ), pTypeOfGet, pNumberOfIntervals
            )
      , pTypeOfGet.millisPer());
   }
   
   static public Date getFutureDate(Date pStartDate, EDate pTypeOfGet, int pNumberOfIntervals){
      Calendar cal = Calendar.getInstance();
      cal.setTime(pStartDate);
      cal.add(pTypeOfGet.cvalue(), pNumberOfIntervals);
      return cal.getTime();
   }

   /**
    * @return # of years since 1970
    */
   static public int getTodayYear(){
      return (int)getTodayIndex(DateConstants.INDEX_YEAR) + 1970;
   }
   
   static public long getTodayIndex(DateConstants pConversion){
      Date now = new Date(System.currentTimeMillis());
      return index(now, pConversion); 
   }
   
   static public String getNow(){
      return getFormattedTime(System.currentTimeMillis());
   }
   
   static public int getTodayDayInMonth(){
      Date now = new Date(System.currentTimeMillis());
      Calendar cal = Calendar.getInstance();
      cal.setTime(now);
      return cal.get(Calendar.DAY_OF_MONTH);
   }
   
   static public String getFormattedTime(long pTime){
      return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date(pTime));
   }
   
   static public long getDayInMonthOf(String pDate) throws ParseException{
      return get(pDate, Calendar.DAY_OF_MONTH);
   }
   
   static public long getMonthOf(String pDate) throws ParseException{
      return get(pDate, Calendar.MONTH);
   }
   
   static public long getYearOf(String pDate) throws ParseException{
      return get(pDate, Calendar.YEAR);
   }
   
   static public Date getDate(String pDate, DateFormat pFormat) throws ParseException{
      Date d = null;
      synchronized(sGooGformat) {
         DateFormat format = (pFormat == null ? sGooGformat : pFormat);
         d = format.parse(pDate);
      }
      return d;
   }
   
   static public EMonth getTodayMonth(){
      return EMonth.bestGuess(sGooGformat.format(new Date(System.currentTimeMillis())));
   }
   
   static public long get(String pDate, int pCalendarConstant) throws ParseException{
      Date d = null;
      synchronized (sGooGformat) {
         d = sGooGformat.parse(pDate);
      }
      Calendar cal = Calendar.getInstance();
      cal.setTime(d);
      return cal.get(pCalendarConstant);
   }
   
   static public long getIndexFromFormat(
     String pDate, DateConstants pIndexConst, DateFormat pFormat) 
   throws ParseException, NumberFormatException
   {
      Date d = getDate(pDate, pFormat);
      if(d == null) return -1;
      return index(d, pIndexConst);
   }
   
   protected DateUtils(){}
}
