package org.ilite.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ilite.util.logging.ILog;
import org.ilite.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigurationXmlParser
{
  private final String mFilePath;
  private final String mFileName;
  private List<ConfigItem> mItems = new ArrayList<>();
  private final ILog mLog = Logger.createLog(ConfigurationXmlParser.class);

  public ConfigurationXmlParser(String pFilePath, String pFileName)
  {
    mFilePath = pFilePath;
    mFileName = pFileName;
  }

  public void parse() throws ParserConfigurationException, SAXException,
  IOException
  {
    File file = null;
    if (mFilePath == null)
      file = new File(mFileName);
    else
      file = new File(mFilePath, mFileName);
    
    if(file.exists() == false || file.canRead() == false) return;
    mLog.warn("Loading config file ",file.getAbsolutePath());

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    //Using factory get an instance of document builder
    DocumentBuilder db = dbf.newDocumentBuilder();

    //parse using builder to get DOM representation of the XML file
    Document dom = db.parse(file);
    //get the root element
    Element docEle = dom.getDocumentElement();

    //get a nodelist of  elements
    NodeList nl = docEle.getElementsByTagName("config");
    if(nl != null && nl.getLength() > 0) {
      for(int i = 0 ; i < nl.getLength();i++) {
        //get the element
        Element el = (Element)nl.item(i);
        NodeList props = el.getElementsByTagName("property");
        for(int j = 0; j < props.getLength(); j++)
        {
          Element prop = (Element)props.item(j);
          ConfigItem ci = new ConfigItem();
          ci.mName = prop.getAttribute("name");
          ci.mValue = prop.getAttribute("value");
          ci.mType = prop.getAttribute("type").toUpperCase();
          mItems.add(ci);
        }           
      }
    }

  }
  
  public List<ConfigItem> getConfigurationItems()
  {
    return mItems;
  }
}
