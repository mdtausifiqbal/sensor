package com.mti.sensormanager;

import android.app.*;
import android.content.*;
import android.hardware.*;
import android.os.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity implements SensorEventListener
{

	@Override
	public void onSensorChanged(SensorEvent p1)
	{
		switch(p1.sensor.getType()){
			case Sensor.TYPE_PROXIMITY:
				tv.setText(String.format("Proximity sensor %1$.2f",p1.values[0]));
				break;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor p1, int p2)
	{
		// TODO: Implement this method
	}
	
	private TextView tv;
	private SensorManager manager;
	private Sensor proximity;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		tv = (TextView) findViewById(R.id.list);
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		proximity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

	@Override
	protected void onStart()
	{
		// TODO: Implement this method
		super.onStart();
		if(proximity == null){
			tv.setText("Not found Proximity sensor");
		}else{
			manager.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		if(proximity != null){
			manager.unregisterListener(this);
		}
	}
	
	
}
