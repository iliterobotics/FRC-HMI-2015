package org.ilite.telemetry.data;

import org.ilite.util.lang.Data;
import org.ilite.util.lang.IUpdate;

import java.awt.Component;
import java.util.List;

/**
 * @author JesseK
 *
 *  Provides an interface which a class can use to convert a complex data
 *  structure into many different combinations of graphic representations.
 * @param <DataPieceLabel> - often time a String, yet it may also be a Component
 * @param <DataStructure> - the complex data structure which this interface reads
 */
public interface IGraphicData<DataPieceLabel, DataStructure> extends IUpdate<DataStructure>
{
  /**
   * Convert a full data structure into several pieces of data represented by 
   * graphics.  Each Data<DataPieceLabel, Component> represents a row.
   * Generally speaking, if the Label extends a Component (like a JLabel) then
   * it will be added as-is to the displaying component to the left of its
   * associated data piece.  Otherwise, toString() will be called for a simple 
   * display of a string.
   * <p>A List is returned so the implementor has the option of returning a 
   * sorted list (like a LinkedList) and can thus control the order of how the
   * data structure is displayed.
   * <p>The implementor may also choose to not fully-represent the data structure
   * on the graph.  For example if a data structure contains a float, 2 doubles
   * and and a String, yet the implementor may exclude any part.
   *  
   * @param pData
   * @return a List of what to display
   */
  public List<Data<DataPieceLabel, Component>> generateGraphics();
}
