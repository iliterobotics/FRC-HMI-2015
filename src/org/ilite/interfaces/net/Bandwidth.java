package org.ilite.interfaces.net;


import org.ilite.util.lang.IClone;
import org.ilite.util.logging.ILog;
import org.ilite.util.logging.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Bandwidth implements IClone<Bandwidth>{
	public enum EUnit
	{
		B,
		kB,
		MB,
		GB,
		TB;
	}
	
	private final ILog mLog = Logger.createLog(Bandwidth.class); 
	private final static NumberFormat sFORMAT = new DecimalFormat("00.00");
	private int mNumBytes = 0;
	private int mNumPackets;
	private long mInitialTime;
	public final static double
		sMILLIS_PER_SEC = 1000,
		sBITS_PER_BYTE = 8,
		sBYTES_PER_KB = 1024,
		sKB_PER_MB = 1024,
		sMB_PER_GB = 1024,
		sGB_PER_TB = 1024,
		sBYTES_PER_MB = sBYTES_PER_KB * sKB_PER_MB;
	
	public Bandwidth createClone()
	{
		Bandwidth bw = new Bandwidth();
		bw.mNumPackets = this.mNumPackets;
		bw.mInitialTime = this.mInitialTime;
		return bw;
	}
	
	public Bandwidth()
	{
		reset();
	}
	
	public final void reset()
	{
		mNumPackets = 0;
		mNumBytes = 0;
		mInitialTime = System.currentTimeMillis();
	}
	
	public long getMillisSinceStart()
	{
		return (long)((System.currentTimeMillis() - mInitialTime));
	}
	
	public double getBytes()
	{
		return mNumBytes;
	}
	
	public int getNumPackets()
	{
		return mNumPackets;
	}
	
	public double getSecondsSinceStart()
	{
		return (double)(getMillisSinceStart())/sMILLIS_PER_SEC;
	}
	
	public double getPacketsPerSecond() {
		return  ((double)mNumPackets) / getSecondsSinceStart();
	}
	
	public String getFormattedBandwidth()
	{
		StringBuilder sb = new StringBuilder();
		double d = 
				(double)((double)mNumBytes * sBITS_PER_BYTE / 1024d / 1024d)   / 
				(double)(getSecondsSinceStart());
		EUnit units = EUnit.MB;
		sb.append(sFORMAT.format(d));
		sb.append(" ");
		sb.append(units.name());
		sb.append("/s");
		return sb.toString();
	}
	
	public void addBytes(int pBytes)
	{
		mNumBytes += pBytes;
		mNumPackets++;
		mLog.debug("[Bandwidth] Bytes = " + mNumBytes + " Packets = " + mNumPackets + " Seconds = " + getSecondsSinceStart());
	}
	
	
}
