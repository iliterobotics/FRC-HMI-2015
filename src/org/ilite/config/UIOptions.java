package org.ilite.config;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public class UIOptions {
	private static UIOptions sInstance = new UIOptions();
	
	public static final Color
	greyPurple = new ColorUIResource(0xb3, 0xb5, 0xb8),
	darkPurple = new ColorUIResource(0x22, 0x0b, 0x42),
	brightPurple = new ColorUIResource(0x79, 0x51, 0xa1),
	darkBlob = new ColorUIResource(0x23, 0x1f, 0x20),
	dullGreen = new ColorUIResource(0x43, 0x94, 0x2d),
	brightGreen = new ColorUIResource(0x65, 0xe1, 0x44);
	
	public static Color transparentBackground;
	
	public static final int sFONT_STYLE = Font.BOLD;
	public static final String sFONT_NAME = Font.MONOSPACED;
	
	static
	{
    Color c = UIOptions.darkPurple;
    transparentBackground = new Color(c.getRed(), c.getGreen(), c.getBlue(), 128);
	}
	
	public static UIOptions inst()
	{
		return sInstance;
	}
	
	public void setFontSize(int pSize)
	{
    Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof FontUIResource) {
        FontUIResource orig = (FontUIResource) value;
        Font font = new Font(orig.getFontName(), orig.getStyle(), pSize);
        UIManager.put(key, new FontUIResource(font));
      }
    }
	}
	
	public void setDefaultLookAndFeel() 
	throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{	  
	  Toolkit.getDefaultToolkit().setDynamicLayout(true);
	  System.setProperty("sun.awt.noerasebackground", "true");

	  try {
	      UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
	  } catch(Exception ex) {
	      ex.printStackTrace();
	  }

	}
}
