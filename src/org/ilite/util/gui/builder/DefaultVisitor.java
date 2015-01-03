package org.ilite.util.gui.builder;


public class DefaultVisitor 
<E extends Enum<E> & IDisplayBuilderEnum>
{
  /**
   * The specific enumeration value this particular visitor represents
   */
  protected E mcField;

  /**
   * How to render the data field
   */
  protected IRenderer mcRenderer;

  /**
   * The data model which can retrieve the requested field, and can
   * guarantee that the type of the value matches mcField.getFieldClass()
   */
  protected IRenderedDataModel<E> mcDataModel;
  
  /**
   * Calls the data model to generate a value for the renderer.
   * Put into a method so it can be overridden when necessary
   * @return
   */
  private <T> Object getValue()
  {
    return mcDataModel.getFieldValue(mcField, mcField.getFieldClass());
  }
  
  /**
   * Visits the render with an updated value
   */
  public void visit()
  {
    mcRenderer.render(getValue());
  }
  
  /**
   * Sets particular fields of the visitor
   * @param pDataModel
   * @param pField
   */
  public void initialize(IRenderedDataModel<E> pDataModel, E pField)
  {
    mcField = pField;
    mcDataModel = pDataModel;
    try
    {
      mcRenderer = mcField.getRenderingClass().newInstance();
    } catch (InstantiationException | IllegalAccessException e)
    {
      e.printStackTrace();
    }
  }
}
