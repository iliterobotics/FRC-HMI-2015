package org.ilite.util.lang.reflection;

import org.ilite.util.gui.SimpleDataPrinter;

import java.awt.Color;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class DataStructureReader<Data>
{
  protected static final String
    OPEN_STRUCT = "{",
    CLOSE_STRUCT = "}",
    OPEN_ARRAY = "[",
    CLOSE_ARRAY = "]",
    OPEN_PARAM = "<",
    CLOSE_PARAM = ">",
    SUPERCLASS_FIELD = ">> ",
    EQUALS = " = ",
    SPACE = " ",
    EMPTY = "",
    INVALID = "<invalid>",
    MAX_RECURSION = "<max>",
    INTERFACE = " > ",
    SUPER_CLASS = " : ",
    NEWLINE = "\n",
    PUBLIC = "+",
    PRIVATE = "-",
    PROTECTED = "#",
    PACKAGE = "~",
    TAB = "\t"
    ;
  
  protected String mIndentTab = "   ";
  protected boolean mGetSuperClass = false;
  protected boolean mShowStructBraces = true;
  protected boolean mShowArrayBrackets = true;
  protected boolean mShowShortName = false;
  protected int mMaxRecursion = 4;
  protected List<String> mExemptClasses = new ArrayList<String>();
  protected String mResult = null;
  protected final Map<String, ReaderManipulator> mModifiers = new HashMap<String, ReaderManipulator>();
  
  protected static final Class<?>[] sDEFAULT_EXEMPT_CLASSES = new Class<?>[]
      {
        Class.class,
        Object.class,
        Integer.class,
        String.class,
        Double.class,
        Float.class,
        Short.class,
        Character.class,
        Void.class,
        Byte.class,
        Long.class,
        Color.class,
        Thread.class,
      };
  
  public DataStructureReader()
  {
    for(Class<?> clazz : sDEFAULT_EXEMPT_CLASSES)
    {
      mExemptClasses.add(clazz.getCanonicalName());
    }
  }
  
  /**
   * Will cause the reader to perform a Object.toString() on this exempt class
   * rather than reading all fields through reflection.
   * 
   * @param pClass -- Class to call toString() on for all instances
   */
  public void addClassExemption(Class<?> pClass)
  {
    mExemptClasses.add(pClass.getCanonicalName());
  }
  
  public void addFieldModifier(String pFieldName, ReaderManipulator pModifier)
  {
    mModifiers.put(pFieldName, pModifier);
  }
  
  public void setShowShortName(boolean pShowShortName)
  {
    mShowShortName = pShowShortName;
  }
  
  public void setRecursionLevel(int pLevel)
  {
    mMaxRecursion = pLevel;
  }
  
  public void setIndentSize(int pSize)
  {
    mIndentTab = EMPTY;
    for(int i = 0; i < pSize; i++)
    {
      mIndentTab += SPACE;
    }
  }
  
  public void setShowSuperClass(boolean pShowSuperClass)
  {
    mGetSuperClass = pShowSuperClass;
  }
  
  public void setShowBraces(boolean pShowBraces)
  {
    mShowStructBraces = pShowBraces;
  }
  
  public void setShowArrayBrackets(boolean pShowArrayBrackets)
  {
    mShowArrayBrackets = pShowArrayBrackets;
  }
  
  public String getResult()
  {
    return mResult;
  }

  public void read(Data pInstance)
  {
    mResult = read(pInstance.getClass(), pInstance, 0, mGetSuperClass, false);
  }
  
  protected String read(Class<?> pClass, Object pInstance, int pLevel, 
      boolean pGetSuperClass, boolean pIsSuperClass)
  {
    if(pLevel > mMaxRecursion)
    {
      return MAX_RECURSION;
    }
    
    StringBuilder sb = new StringBuilder();    
    String classname = getName(pClass);
    
    addTab(sb, pLevel).append(classname);
    appendSuperClassInformation(sb, pClass);
    sb.append(NEWLINE);
    
    if(mShowStructBraces)
    {
      addTab(sb, pLevel).append(OPEN_STRUCT).append(NEWLINE);
    }
    
    if(pGetSuperClass)
    {
      Class<?> superclass = pInstance.getClass().getSuperclass();
      if(superclass != Object.class && superclass != pClass)
      {
        Object o = superclass.cast(pInstance);
        String superresult = read(superclass, o, pLevel+1, true, true);
        sb.append(superresult);        
      }
    }
    
    Field[] fields = pClass.getDeclaredFields();
    for(Field field : fields)
    {
      field.setAccessible( true );
      int mods = field.getModifiers();
      String fieldname = field.getName();
      if(!Modifier.isStatic(mods) && !fieldname.contains("this"))
      {        
        addTab(sb, pLevel + 1);
        
        if(pIsSuperClass)
        {
          sb.append(classname).append(SUPERCLASS_FIELD);
        }
        
        sb.append(convertAccessModsToString(mods));
        sb.append(getName(field));
        sb.append(SPACE).append(fieldname).append(EQUALS);
        String object = null;
        if(field.getType().isPrimitive())
        {
          object = convertPrimitiveToString(field, pInstance);
        }
        else if (field.getType().isArray())
        {
          object = convertArrayToString(field, pLevel, pInstance);
        }
        else
        {
          object = readObject(field, pInstance, pLevel + 1, false, pGetSuperClass);
        }
        
        sb.append(object);
        sb.append(NEWLINE);        
      }
    }
    
    if(mShowStructBraces)
    {
      addTab(sb, pLevel).append(CLOSE_STRUCT).append(NEWLINE);
    }
    
    return sb.toString();
  }
  
  protected final String readObject(Field pField, Object pInstance, int pLevel, boolean pIsSuperClass, boolean pGetSuperClass)
  {
    if(pLevel > mMaxRecursion)
    {
      return MAX_RECURSION;
    }
    else
    {
      try
      {
        Object o = pField.get(pInstance);
        ReaderManipulator rm = checkForModifiers(pField, pInstance);
        if(rm == null)
        {
          if(o == null)
          {
            return "null";
          }
          else if(checkForExemption(o) == true || o instanceof Enum<?>)
          {
            return o.toString();
          }
          else
          {
            return read(o.getClass(), o, pLevel, pGetSuperClass, false);          
          }
        }
        else
        {
          return rm.execute(o);
        }
      } 
      catch (IllegalArgumentException | IllegalAccessException e)
      {
        return INVALID;
      } 
    }
  }
  
  protected final void appendSuperClassInformation(StringBuilder sb, Class<?> pClass)
  {
    Class<?>[] interfaces = pClass.getInterfaces();
    Class<?> superclass = pClass.getSuperclass();
    if(superclass != null && superclass != Object.class && superclass != pClass)
    {
      sb.append(SUPER_CLASS);
      sb.append(getName(superclass));
    }
    
    if(interfaces != null && interfaces.length > 0)
    {
      for(Class<?> iface : interfaces)
      {
        sb.append(INTERFACE);
        sb.append(getName(iface));
      }
    }
  }
  
  protected final String convertAccessModsToString(int pMods)
  {
    if(Modifier.isPublic(pMods)) return PUBLIC;
    if(Modifier.isPrivate(pMods)) return PRIVATE;
    if(Modifier.isProtected(pMods)) return PROTECTED;
    return PACKAGE;        
  }
  
  protected final StringBuilder addTab(StringBuilder sb, int pLevel)
  {
    for(int i = 0; i < pLevel; i++)
    {
      sb.append(mIndentTab);
    }
    return sb;
  }
  
  protected final String convertArrayToString(Field pField, int pLevel, Object pInstance)
  {
    if(!pField.getType().isArray()) return INVALID;
    
    StringBuilder sb = new StringBuilder();    
    try
    {
      Object o = pField.get(pInstance);
      
      ReaderManipulator rm = checkForModifiers(pField, pInstance);
      if(rm != null)
      {
        sb.append(rm.execute(o));
      }
      else
      {
        if(o instanceof int[])
        {
          sb.append(Arrays.toString((int[])o));
        } 
        else if (o instanceof short[])
        {
          sb.append(Arrays.toString((short[])o));
        }
        else if(o instanceof long[])
        {
          sb.append(Arrays.toString((long[])o));
        }
        else if(o instanceof double[])
        {
          sb.append(Arrays.toString((double[])o));
        }
        else if(o instanceof float[])
        {
          sb.append(Arrays.toString((float[])o));
        }
        else if(o instanceof byte[])
        {
          sb.append(Arrays.toString((byte[])o));
        }
        else if(o instanceof char[])
        {
          sb.append(Arrays.toString((char[])o));
        }
        else if (o instanceof Enum[])
        {
          sb.append(Arrays.toString((Enum[])o));
        }
        else
        {
          Arrays.toString((Object[])o);
        }
      }
    } 
    catch (IllegalArgumentException | IllegalAccessException e)
    {
      sb.append(INVALID);
    }
    
    return sb.toString();
  }
  
  protected final String convertPrimitiveToString(Field pField, Object pInstance)
  {
    try
    {
      ReaderManipulator rm = checkForModifiers(pField, pInstance);
      Object o = pField.get(pInstance);
      if(rm == null)
      {
        return o.toString();        
      }
      else
      {
        return rm.execute(o);
      }
    }
    catch (IllegalArgumentException | IllegalAccessException e)
    {
      return INVALID;
    }
    
  }
  
  protected final ReaderManipulator checkForModifiers(Field pField, Object pInstance)
  {
    ReaderManipulator result = mModifiers.get(pField.getName());
    return result;
  }
  
  protected final boolean checkForExemption(Object o)
  {
    if(o == null) return false;
    return mExemptClasses.contains(o.getClass().getCanonicalName());
  }
  
  protected final String getName(Class<?> pClass)
  {
    if(mShowShortName)
    {
      return pClass.getSimpleName();
    }
    else
    {
      return pClass.getCanonicalName();
    }
  }
  
  protected final String getName(Field pField)
  {
    if(mShowShortName)
    {
      return pField.getType().getSimpleName();          
    }
    else
    {
      return pField.getType().getCanonicalName();
    }
  }
  
  public static void main(String[] pArgs)
  {
    DataStructureReader reader = new DataStructureReader();
    final DateFormat dateformat= new SimpleDateFormat("HH:MM:ss:mmm");
    ReaderManipulator rm = new ReaderManipulator()
    {      
      @Override
      public String execute(Object input)
      {
        if(input instanceof Long)
        {
          String result = dateformat.format(new Date((Long)input));
          return result;          
        }
        else
        {
          return input.toString();
        }
      }
    };
    reader.addFieldModifier("time", rm);
    reader.addClassExemption(Date.class);
    reader.setShowBraces(false);
    reader.setShowSuperClass(true);
    reader.setShowShortName(true);
    reader.setRecursionLevel(3);
    SimpleDataPrinter sdp = new SimpleDataPrinter(reader);
    
    JFrame f = new JFrame();
    f.setPreferredSize(new Dimension(700, 900));
    f.setContentPane(sdp.getPanel());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
    
//    sdp.printObject(new Wrapper());
  }
}
