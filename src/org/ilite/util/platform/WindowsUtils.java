package org.ilite.util.platform;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author JesseK
 *  Class of utilities for Windows-specific platforms.  Most of this is yanked
 *  from stack overflow or other places, so don't blame me for the problems of
 *  developing in Windows.
 */
public class WindowsUtils
{
    private static final String REGQUERY_UTIL = "reg query ";
    private static final String REGSTR_TOKEN = "REG_SZ";
    private static final String DESKTOP_FOLDER_CMD = REGQUERY_UTIL 
       + "\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\" 
       + "Explorer\\Shell Folders\" /v DESKTOP";

    private WindowsUtils() {}

    /**
     * Retrieves the desktop folder location from the Windows registry.  This has
     * been verified to work even if the user has a Roaming profile or has moved 
     * the directory to a non-standard location.
     * @return Full path to the Desktop folder, including drive letter annotation
     */
    public static String getCurrentUserDesktopPath() {
      try {
        Process process = Runtime.getRuntime().exec(DESKTOP_FOLDER_CMD);
        StreamReader reader = new StreamReader(process.getInputStream());

        reader.start();
        process.waitFor();
        reader.join();
        String result = reader.getResult();
        int p = result.indexOf(REGSTR_TOKEN);

        if (p == -1) return null;
        return result.substring(p + REGSTR_TOKEN.length()).trim();
      }
      catch (Exception e) {
        return null;
      }
    }


    static class StreamReader extends Thread {
      private InputStream is;
      private StringWriter sw;

      StreamReader(InputStream is) {
        this.is = is;
        sw = new StringWriter();
      }

      @Override
      public void run() {
        try {
          int c;
          while ((c = is.read()) != -1)
            sw.write(c);
          }
          catch (IOException e) { ; }
        }

      String getResult() {
        return sw.toString();
      }
    }
}
