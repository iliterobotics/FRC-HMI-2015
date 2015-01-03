package org.ilite.util.gui.builder.components;

import org.ilite.util.gui.builder.IRenderer;

import javax.swing.JLabel;

public class JLabelRenderer extends JLabel implements IRenderer
{
  @Override
  public <T> void render(T pValue)
  {
    setText(pValue == null ? "null" : pValue.toString());
  }

}
