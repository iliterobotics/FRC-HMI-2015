package org.ilite.util.lang;

public interface Closure <Input, Output>
{
  public Output execute(Input input);
}
