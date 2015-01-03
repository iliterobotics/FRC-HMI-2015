package org.ilite.util.gui;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;
import org.ilite.telemetry.data.IGraphicData;
import org.ilite.util.lang.Data;
import org.ilite.util.lang.IUpdate;

import java.awt.Component;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class GraphicDataPrinter<DataStructure> implements IUpdate<DataStructure>
{
  private static final int INSET = 3;
  private final int mLabelSpacing;
  private final IGraphicData<String, DataStructure> mReader;
  private final JPanel mPanel;
  
  public GraphicDataPrinter(IGraphicData<String, DataStructure> pReader)
  {
    this(pReader, 50);
  }

  public GraphicDataPrinter(IGraphicData<String, DataStructure> pReader, int pLabelSpacing)
  {
    mReader = pReader;
    mPanel = generateComponents();
    mLabelSpacing = pLabelSpacing;
  }
  
  public JPanel getGraphicPanel()
  {
    return mPanel;
  }

  @Override
  public void update(DataStructure pData)
  {
    mReader.update(pData);
  }
  
  private JPanel generateComponents()
  {
    JPanel result = new JPanel();
    SpringLayout sl = new SpringLayout();
    result.setLayout(sl);
    result.setBorder(BorderFactory.createTitledBorder("Graphic Data Printer"));
    List<Data<String, Component>> components = mReader.generateGraphics();
    Component prev = null;
    if(components != null && components.isEmpty() == false)
    {
      for(Data<String, Component> data : components)
      {
        JLabel label = new JLabel(data.getName());
        Component c = data.getValue();
        result.add(label);
        result.add(c);
        
        if(prev == null)
        {
          sl.putConstraint(NORTH, label, INSET, NORTH, prev);
        }
        else
        {
          sl.putConstraint(NORTH, label, INSET, SOUTH, prev);
        }
        sl.putConstraint(WEST, label, INSET, WEST, result);
        sl.putConstraint(WEST, c, mLabelSpacing, WEST, result);
        sl.putConstraint(EAST, c, -INSET, EAST, result);        
        prev = c;
      }
    }
    return result;
  }
}
