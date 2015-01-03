package org.ilite.util.platform;

import org.ilite.util.logging.ILog;
import org.ilite.util.logging.Logger;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.opencv.core.Core;

public class PlatformUtils
{
	
	private static final String OPENCV_LIB_PATH = 
	File.separator +
	"Aerial Assist Display" +
	File.separator +
	"opencv" + 
	File.separator + 
	"build" + 
	File.separator +
	"java" + 
	File.separator + 
	"x64";
	
	
	private static final ILog mLog = 
			Logger.createLog(PlatformUtils.class);
	private static ESupportedPlatform mCurrentPlatform = 
			ESupportedPlatform.UNSUPPORTED;

	public static String getDesktopLocation() {
		switch(mCurrentPlatform)
		{
		case WINDOWS:
			return WindowsUtils.getCurrentUserDesktopPath();
		}
		return "";
	}

	public static ESupportedPlatform getCurrentPlatform()
	{
		return mCurrentPlatform;
	}

	public static void printEnvironment()
	{
		for(String key : System.getenv().keySet())
		{
			mLog.debug(key + "= " + System.getenv(key));
		}
	}

	public static void printProperties()
	{

		Properties p = System.getProperties();
		for(Object o : p.keySet())
		{
			mLog.debug(o.toString() + "=" + p.get(o));
		}
	}

	static
	{
		if(System.getProperty("os.name").contains("Windows"))
		{
			mCurrentPlatform = ESupportedPlatform.WINDOWS;
		}
		IS_OPENCV_LIB_CONFIG = configureOpenCVLib();

	}

	/**Flag to indicate whether the opencv native libraries have been configured**/
	public static final boolean IS_OPENCV_LIB_CONFIG;

	/**
	 * Method to set up the native library for OPENCV, used by the Vision Code. 
	 * This should be run before any of the Vision code is initialized, otherwise
	 * the vision code will not work
	 * 
	 * @return
	 * 	true if the library is loaded correctl
	 *  false otherwise.
	 */
	private static boolean configureOpenCVLib() {
		boolean isLibLoaded = false;
		//Grab Library
		mLog.debug("Old lib path: ", System.getProperty("java.library.path"));
		
		File opencvDir = new File(getDesktopLocation() + File.separator + OPENCV_LIB_PATH);
		
		if(opencvDir.exists()) {
			
			if(opencvDir.isDirectory()) {
				if(opencvDir.list() != null && opencvDir.list().length >0) {
					String libPath = null;
					StringBuilder tempPath = new StringBuilder();
					tempPath.append(opencvDir.getAbsolutePath());
					tempPath.append(File.pathSeparator);
					tempPath.append(System.getProperty("java.library.path"));
					libPath = tempPath.toString();
					
					try {
						mLog.debug("Updating lib path to", libPath);
						System.setProperty( "java.library.path", libPath.toString());
						Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
						fieldSysPath.setAccessible( true );
						fieldSysPath.set( null, null );
						System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
						isLibLoaded = true;
					} catch(Throwable e) {
						isLibLoaded = false;
						e.printStackTrace();
					} 
				} else {
					mLog.error("OPENCV path: no files in directory: ", opencvDir.getAbsolutePath());
				}
			} else {
				mLog.error("OPENCV path: ", opencvDir.getAbsolutePath(), " is not a directory");
			}
		} else {
			mLog.error("OPENCV path: ", opencvDir.getAbsolutePath(), "does not exist");

		}

		return isLibLoaded;
	}

	public static void main(String[] pArgs) {
		printEnvironment();
		printProperties();
	}
	
}
