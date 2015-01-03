package org.ilite.util.gui.builder;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Describes the class name of the visitor that actually writes a field on the display.
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD,TYPE})
public @interface RenderingVisitorClassName
{
  String value();
}
