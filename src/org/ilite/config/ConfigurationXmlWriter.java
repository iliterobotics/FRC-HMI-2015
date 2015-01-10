package org.ilite.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConfigurationXmlWriter
{
  ConfigurationXmlWriter()
  {
  }
  
  public void write(List<ConfigItem> pConfigItems)
  {
    System.out.println("writing");
    File f = new File("config.xml");
    BufferedWriter writer = null;
    try
    {
      writer = new BufferedWriter(new FileWriter(f));
      writer.write("<robot>"); writer.newLine();
      writer.write("\t<config>"); writer.newLine();
      
      for(ConfigItem ci : pConfigItems)
      {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<property ");
        sb.append("name=\"");
        sb.append(ci.mName);
        sb.append("\" value=\"");
        sb.append(ci.mValue);
        sb.append("\" type=\"");
        sb.append(ci.mType);
        sb.append("\"></property>");
        writer.write(sb.toString());
        writer.newLine();
      }
      
      writer.write("\t</config>"); writer.newLine();
      writer.write("</robot>"); writer.newLine();
    } 
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if(writer != null)
      {
        try
        {
          writer.close();
        } catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}
