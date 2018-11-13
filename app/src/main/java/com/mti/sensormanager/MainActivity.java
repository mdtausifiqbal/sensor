package com.mti.sensormanager;

import android.app.*;
import android.content.*;
import android.hardware.*;
import android.os.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends ListActivity implements SensorEventListener
{
	
	private SensorManager manager;
	private Sensor mSensor;
	private ArrayList<MySensor> list = new ArrayList<>();
	private List<Sensor> sensors = new ArrayList<>();
	@Override
	public void onSensorChanged(SensorEvent p1)
	{
		Sensor temp = p1.sensor;
		int index = sensors.indexOf(temp);
		list.get(index).setData(p1.values);
		((ArrayAdapter)getListView().getAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onAccuracyChanged(Sensor p1, int p2)
	{
		// TODO: Implement this method
	}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensors = manager.getSensorList(Sensor.TYPE_ALL);
		for(Sensor s:sensors){
			list.add(new MySensor(s.getName()));
			manager.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
		}
		setListAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,list));
    }

	@Override
	protected void onStart()
	{
		// TODO: Implement this method
		super.onStart();
		for(Sensor s:sensors){
			manager.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
		}
		
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		manager.unregisterListener(this);
		
	}
	
	
}
