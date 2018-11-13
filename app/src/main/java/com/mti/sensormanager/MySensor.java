package com.mti.sensormanager;
import android.hardware.*;

public class MySensor
{
	private String name;
	private float[] data;

	public MySensor(String name)
	{
		this.name = name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setData(float[] data)
	{
		this.data = data;
	}

	public float[] getData()
	{
		return data;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		
		if(data != null){
			builder.append(System.lineSeparator());
			builder.append(String.format("%1$.2f , %2$.2f , %3$.2f",data[0],data[1],data[2]));
		}
		
		return builder.toString();
	}
}
