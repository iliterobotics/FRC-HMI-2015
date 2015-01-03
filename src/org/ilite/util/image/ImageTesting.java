package org.ilite.util.image;

import ilite.display.camera.GenericWebCam;
import ilite.display.omi.CameraImagePanel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageTesting
{

  private CameraImagePanel mImagePanel;
  
  public ImageTesting()
  {
    String url = "http://cam6284208.miemasu.net/nphMotionJpeg?Resolution=640x480&amp;Quality=Clarity";
    GenericWebCam gwc = new GenericWebCam(url);
    mImagePanel = new CameraImagePanel(1024, 768);
    gwc.addListener(mImagePanel.getUpdatingImage());
    mImagePanel.addHorizontalCursor("One", Color.RED);
    
    new Thread(gwc).start();
  }
  
  public JPanel getContentPanel()
  {
    return mImagePanel.getPanel();
  }
  
  public static void main(String[] pArgs)
  {
    ImageTesting it = new ImageTesting();
    
    JFrame f = new JFrame();
    f.setContentPane(it.getContentPanel());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
//    f.setVisible(true);
  }
}
