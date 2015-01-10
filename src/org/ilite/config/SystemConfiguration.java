package org.ilite.config;

import org.ilite.util.lang.Delegator;
import org.ilite.util.lang.IUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SystemConfiguration implements TableModel, ISystemConfiguration
{
  private enum EType
  {
    STRING(String.class), VOID(void.class);

    static EType fromString(String pString)
    {
      EType result = VOID;
      for (EType t : EType.values())
      {
        if (t.name().equalsIgnoreCase(pString))
        {
          result = t;
          break;
        }
      }
      return result;
    }

    public Class<?> getType()
    {
      return mClass;
    }

    private Class<?> mClass;

    private EType(Class<?> pClass)
    {
      mClass = pClass;
    }
  }

  private final List<ConfigItem> mConfig;
  private Map<String, Delegator<String>> mConfigChangeListeners = new HashMap<String, Delegator<String>>();

  private static final String[] sCOLS = new String[] { "Name", "Value", "Type" };

  public SystemConfiguration(List<ConfigItem> pConfig)
  {
    mConfig = pConfig;
  }

  public void addPropertyChangeListener(String pProperty,
      IUpdate<String> pListener)
  {
    Delegator<String> delegator = mConfigChangeListeners.get(pProperty);
    if (delegator == null)
    {
      delegator = new Delegator<String>();
      mConfigChangeListeners.put(pProperty, delegator);
    }
    delegator.addListener(pListener);
  }

  public String getString(String pKey)
  {
    for (ConfigItem ci : mConfig)
    {
      if (ci.mName.equals(pKey))
      {
        return ci.mValue;
      }
    }
    return null;
  }

  public Integer getInt(String pKey)
  {
    String s = getString(pKey);
    if(s == null)
      return 0;
    else
      return Integer.parseInt(getString(pKey));
  }

  public Double getDouble(String pKey)
  {
    String s = getString(pKey);
    if(s == null)
      return 0d;
    else
      return Double.parseDouble(getString(pKey));
  }

  public Float getFloat(String pKey)

  {
    String s = getString(pKey);
    if(s == null)
      return 0f;
    else
      return Float.parseFloat(getString(pKey));
  }

  public Boolean getBool(String pKey)
  {
    String s = getString(pKey);
    if(s == null)
      return false;
    else
      return Boolean.parseBoolean(getString(pKey));
  }

  @Override
  public int getRowCount()
  {
    return mConfig.size();
  }

  @Override
  public int getColumnCount()
  {
    return sCOLS.length;
  }

  @Override
  public String getColumnName(int columnIndex)
  {
    return sCOLS[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex)
  {
    return String.class;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex)
  {
    if (columnIndex == 1)
      return true;
    return false;
  }
  
  @Override
  public boolean setConfigItem(String pKey, String pValue)
  {
    boolean updated = false;
    for(ConfigItem ci : mConfig)
    {
      if(ci.mName.equalsIgnoreCase(pKey))
      {
        ci.mValue = pValue;
        updated = true;
        break;
      }
    }
    if(updated == false)
    {
      ConfigItem ci = new ConfigItem();
      ci.set(ConfigItem.NAME, pKey);
      ci.set(ConfigItem.VALUE, pValue);
      mConfig.add(ci);
    }
    return updated;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    return mConfig.get(rowIndex).value(columnIndex);
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex)
  {
    String s = (String) aValue;
    ConfigItem ci = mConfig.get(rowIndex);
    ci.set(columnIndex, s);
    if (mConfigChangeListeners.containsKey(ci.mName))
    {
      mConfigChangeListeners.get(ci.mName).update(ci.mValue);
    }
  }

  @Override
  public void addTableModelListener(TableModelListener l)
  {

  }

  @Override
  public void removeTableModelListener(TableModelListener l)
  {

  }

  @Override
  public void writeToFile()
  {
    Thread t = new Thread()
    {
      @Override
      public void run()
      {
        new ConfigurationXmlWriter().write(new ArrayList<ConfigItem>(mConfig));
      }
    };
    t.start();
  }

}
