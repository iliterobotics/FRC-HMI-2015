package org.ilite.interfaces.fileio;

import org.ilite.util.logging.ILog;
import org.ilite.util.logging.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

public class DataLogger2012 {
	private ILog mLog = Logger.createLog(DataLogger2012.class);
	long mTimeChange;
	long mLastTime;
	FileWriter outFile;
	PrintWriter out;
	private AtomicBoolean mInitialized = new AtomicBoolean(false);
	
	public DataLogger2012() {
		out = null;
		outFile = null;
	}
	
	public void initialize() {
		mTimeChange = 0;
		mLastTime = System.nanoTime();
		setRead();
		out = new PrintWriter(outFile);
		mInitialized.set(true);
	}
	
	public void setRead() {
		try {
			outFile = new FileWriter(new File("GameData.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void write(byte[] header, byte[] size, byte[] message) {
		if(!mInitialized.get())
		{
			initialize();
		}
		
		byte [] timeChangeInBytes = new byte[ Long.bitCount(mTimeChange) / 8];
		for(int i= 0; i < Long.bitCount(mTimeChange) / 8; i++) {
		   timeChangeInBytes[Long.bitCount(mTimeChange) / 8 - 1 - i] = (byte)(mTimeChange >>> (i * 8));
		}
		
		out.print(timeChangeInBytes);
		out.print(header);
		out.print(size);
		out.print(message);
		out.flush();
		
		mTimeChange = System.nanoTime() - mLastTime;
	}
	
	public void close() {
		if(out != null) {
			out.close();
		}
	}
}
