package org.ilite.uitools.managers;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SensorManager extends Manager
{
	private DoubleProperty data= new SimpleDoubleProperty();
	
	public SensorManager()
	{
		data.setValue(0);
	}
	
	public DoubleProperty getData()
	{
		return data;
	}
	
	public void setData(double newData)
	{
		data.setValue(newData);
	}
	
	public void buildWidget()
	{
		
	}
}
