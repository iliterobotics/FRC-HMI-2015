package org.ilite.util.gui;

import org.ilite.util.lang.FixedSizeQueue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class FixedSizeListModel<E> extends FixedSizeQueue<E> implements ListModel<E>
{
  public FixedSizeListModel(Collection<? extends E> c)
  {
    super(c);
  }
  
  public FixedSizeListModel()
  {
    this(1000);
  }
  
  public FixedSizeListModel(int pQueueSize)
  {
    super(pQueueSize);
  }
  
  private final Set<ListDataListener> mListeners = new HashSet<>();
  @Override
  public int getSize()
  {
    return size();
  }

  @Override
  public E getElementAt(int index)
  {
    return get(index);
  }

  @Override
  public void addListDataListener(ListDataListener l)
  {
    mListeners.add(l);
  }

  @Override
  public void removeListDataListener(ListDataListener l)
  {
    mListeners.remove(l);
  }

}
