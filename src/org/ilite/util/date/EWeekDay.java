package org.ilite.util.date;


public enum EWeekDay {
   SUNDAY,
   MONDAY,
   TUESDAY,
   WEDNESDAY,
   THURSDAY,
   FRIDAY,
   SATURDAY;
   
   public int getCalendarInt(){
      return ordinal() + 1;
   }
   
   static public EWeekDay getFromCalInt(int pInt){
      if(pInt < 1 || pInt > 7){
         throw new IllegalArgumentException();
      }
      for(EWeekDay ewd : EWeekDay.values()){
         if(pInt - 1 == ewd.ordinal())
            return ewd;
      }
      return SUNDAY;
   }

}
