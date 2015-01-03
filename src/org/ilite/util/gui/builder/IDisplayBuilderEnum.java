package org.ilite.util.gui.builder;


public interface IDisplayBuilderEnum
{
  public String getDisplayLabel();  
  public Class<?> getFieldClass();
  public Class<? extends IRenderer> getRenderingClass();
}
