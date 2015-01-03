package org.ilite.util.gui.builder;

public interface IRenderedDataModel <E extends Enum<E>>
{
  /**
   * Return the existing value of the display building enumeration
   * @param pField
   * @param pFieldClass
   * @return
   */
  public <T> T getFieldValue(E pField, Class<T> pFieldClass);
}
