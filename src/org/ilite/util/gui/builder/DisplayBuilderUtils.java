package org.ilite.util.gui.builder;

import java.lang.annotation.Annotation;

public class DisplayBuilderUtils
{


  public static <T extends Annotation> T getAnnotation(Class<T> pAnnoClass, Enum<?> pEnum)
  {
    T ann = null;
    try
    {
      ann = pEnum.getClass().getField(pEnum.name()).getAnnotation(pAnnoClass);
    } catch (NoSuchFieldException | SecurityException e)
    {
      e.printStackTrace();
    }
    if(ann == null) ann = pEnum.getClass().getAnnotation(pAnnoClass);
    if(ann == null) ann = pEnum.getDeclaringClass().getAnnotation(pAnnoClass);
    return ann;
  }
}
