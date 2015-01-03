package org.ilite.util.date;


public enum EMonth {
   
   JANUARY("Jan"),
   FEBRUARY("Feb"),
   MARCH("Mar"),
   APRIL("Apr"),
   MAY("May"),
   JUNE("Jun"),
   JULY("Jul"),
   AUGUST("Aug"),
   SEPTEMBER("Sep"),
   OCTOBER("Oct"),
   NOVEMBER("Nov"),
   DECEMBER("Dec");
   
   public int getMonthOfYear(){
      return ordinal() + 1;
   }
   
   public static EMonth fromMonthOfYear(int pMonthOfYear){
      EMonth result = EMonth.JANUARY;
      for(EMonth m : EMonth.values()){
         if(m.getMonthOfYear() == pMonthOfYear){
            result = m;
            break;
         }
      }
      return result;
   }

   public static EMonth bestGuess(String pString)
   {
      String test = new String(pString).toLowerCase().replace("ber", "")
            .replace("/", "")
            .replace("-", "")
            .replace("0", "")
            .replace("1", "")
            .replace("2", "")
            .replace("3", "")
            .replace("4", "")
            .replace("5", "")
            .replace("6", "")
            .replace("7", "")
            .replace("8", "")
            .replace("9", "").trim();
      
      EMonth result = EMonth.DECEMBER;
      for(EMonth m : EMonth.values()){
         if(m.name().toLowerCase().contains(test)){
            result = m;
            break;
         }
      }
      return result;
   }
   
   public static EMonth fromShortName(String pString)
   {
      EMonth result = EMonth.DECEMBER;
      for(EMonth m : EMonth.values()){
         if(m.getShortName().equalsIgnoreCase(pString)){
            result = m;
            break;
         }
      }
      return result;
   }
   
   public static EMonth fromString(String pString)
   {
      EMonth result = EMonth.DECEMBER;
      for(EMonth m : EMonth.values()){
         if(m.name().equalsIgnoreCase(pString)){
            result = m;
            break;
         }
      }
      return result;
   }
   
   public String toString(){
      return getShortName();
   }
   
   public String getShortName(){ return mShortName; }
   private EMonth(String pShortName){
      mShortName = pShortName;
   }
   private String mShortName;

}
