package org.ilite.util.gui.builder;

import org.ilite.util.gui.builder.components.JLabelRenderer;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RenderingVisitorExample
{

  @RenderingVisitorClassName("JTextFieldRenderingVisitor")
  public enum EField implements IDisplayBuilderEnum
  {
    enum1("LabelText1", Integer.class),
    enum2("LabelText2", Integer.class),

    @RenderingVisitorClassName("JProgressBarRenderingVisitor")
    enum3("LabelText3", Integer.class);

    final String mcLabel;
    final Class<?> mcFieldClass;
    final Class<? extends IRenderer> mcRenderingClass;

    //Constructor1
    <FieldType> EField(String pLabel, Class<FieldType> pFieldClass, Class<? extends IRenderer> pJComponent)
    {
      mcRenderingClass = pJComponent;
      mcLabel = pLabel;
      mcFieldClass = pFieldClass;
    }

    //Constructor2
    <FieldType> EField(String pLabel, Class<FieldType> pFieldClass)
    { 
      this(pLabel, pFieldClass, JLabelRenderer.class);
    }
    
    public static final List<EField> Set1 = Arrays.asList(enum2, enum3);

    // More sets if necessary

    @Override
    public String getDisplayLabel()
    {
      return mcLabel;
    }

    @Override
    public Class<?> getFieldClass()
    {
      return mcFieldClass;
    }


    @Override
    public Class<? extends IRenderer> getRenderingClass()
    {
      return mcRenderingClass;
    }
  }

  public abstract class RenderingVisitorImpl<RendererClass extends JComponent>
  {
    protected EField mcField;
    protected RendererClass mcRenderer;
    protected DataModel mcDataModel;
    protected DataView mcDataView;

    <T> Object getValue()
    {
      return mcDataModel.getFieldValue(
          mcField, mcField.mcFieldClass);
    }

    void visit()
    {
      render(getValue());
    }

    abstract <T> void render(T pValue);

    void initialize(EField pField)
    {
      mcField = pField;
      mcDataView.mcStatusLabels.put(pField, new JLabel(pField.mcLabel));
      try
      {
        mcRenderer = (RendererClass)mcField.mcRenderingClass.newInstance();
      } catch (InstantiationException | IllegalAccessException e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  class JTextFieldRenderingVisitor extends RenderingVisitorImpl<JTextField>
  {
    @Override <T> void render(T value) {

      //      if(value instanceof Date) mcRenderer.setText(DateFormatter.format((Date)value);
      //
      //      Else mcRenderer.setText(value.toString());
      //
      //      mcRenderer.setBorder(< some border color or title> );

      // Set background, font, etc, based upon value or state

    }

    @Override void initialize(EField pField)
    {
      super.initialize(pField);
      mcRenderer.setHorizontalAlignment(0);
      // set font, editable, etc
    }
  }

  class DataModel 
  {
    <T> T getFieldValue(EField pField, Class<T> pFieldClass) 
    {
      Object result = null;

      //      switch(pField)
      //      {
      //      case enum1:
      //
      //        result = mcData.get < some data>
      //
      //      case enum2:
      //
      ////        result = …
      //      }
      //    }

      return pFieldClass.cast(result);
    }
  }

  class DataView
  {
    DataView()
    {             

      for(EField field : EField.Set1)
      {
        String lcAnnotationValue = DisplayBuilderUtils.getAnnotation(RenderingVisitorClassName.class, field).value();
        String visitorclassname = RenderingVisitorExample.class.getCanonicalName() + "$" + lcAnnotationValue;
        RenderingVisitorImpl<? extends JComponent> visitor = null;
        try
        {
          visitor = (RenderingVisitorImpl<? extends JComponent>)Class.forName(visitorclassname).getDeclaredConstructors()[0].newInstance(this);
        } catch (InstantiationException | IllegalAccessException
            | IllegalArgumentException | InvocationTargetException
            | SecurityException | ClassNotFoundException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        if(visitor != null)
        {
          mcRenderingVisitors.put(field, visitor);
          visitor.initialize(field);
        }
        //        Else{ // log error }
      }

      for(EField field : EField.Set1)
      {
        JLabel label = mcStatusLabels.get(field);
        //        Add(label);
        //
        //        Add(mcRenderingVisitors.get(field).mcRenderer; // or whatever layout code…
      }
      refreshComponents();
    }

    void refreshComponents()
    {
      for(RenderingVisitorImpl visitor : mcRenderingVisitors.values())
      {
        visitor.visit();
      }
    }

    Map<EField, RenderingVisitorImpl<?>> mcRenderingVisitors = new HashMap<>();
    Map<EField, JLabel> mcStatusLabels = new HashMap<>();
  }
}
