package org.ilite.util.lang;

public class StateDatum<T extends IClone<T>> {

	private final Delegator<T> mDelegator = new Delegator<T>();
	private T mData = null;
	
	public T getData()
	{
		return mData;
	}
	
	public void setData(T pData)
	{
		mData = pData;
		mDelegator.update(pData);
	}
	
	public void addListener(IUpdate<T> pListener)
	{
		mDelegator.addListener(pListener);
	}
}
