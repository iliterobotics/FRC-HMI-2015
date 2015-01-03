package org.ilite.util.lang;

import java.util.Collection;
import java.util.LinkedList;

public class FixedSizeQueue <T> extends LinkedList<T>
{
  private int mQueueSize = Integer.MAX_VALUE;
  
  
  public FixedSizeQueue(Collection<? extends T> c)
  {
    super(c);
    mQueueSize = c.size();
  }
  
  public FixedSizeQueue()
  {
    this(Integer.MAX_VALUE);
  }
  
  public FixedSizeQueue(int pQueueSize)
  {
    mQueueSize = pQueueSize;
  }
  
  private final void checkSize()
  {
    while(size() > mQueueSize)
    {
      this.removeFirst();
    }
  }
  
  public final void setQueueSize(int pMaxQueueSize)
  {
    mQueueSize = pMaxQueueSize;
    checkSize();
  }

  @Override
  public boolean addAll(Collection<? extends T> c)
  {
    boolean result = super.addAll(c);
    checkSize();
    return result;
  }

  @Override
  public boolean add(T e)
  {
    boolean result = super.add(e);
    checkSize();
    return result;
  }

  @Override
  public boolean offer(T e)
  {
    boolean result = super.offer(e);
    checkSize();
    return result;
  }
  
  @Override
  public boolean offerFirst(T e)
  {
    boolean result = super.offerFirst(e);
    checkSize();
    return result;
  }
  
  @Override
  public boolean offerLast(T e)
  {
    boolean result = super.offerLast(e);
    checkSize();
    return result;
  }
  
  @Override
  public void addFirst(T e)
  {
    super.addFirst(e);
    checkSize();
  }
  
  @Override
  public void addLast(T e)
  {
    super.addLast(e);
    checkSize();
  }
}
