package org.ups.ter.morsecode.activities;

import java.util.ArrayList;
import java.util.Calendar;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.R.menu;
import org.ups.ter.morsecode.Static;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class LightSensorActivity extends Activity implements SensorEventListener {
	 private SensorManager 				mSensorManager;
	 private Sensor 					mLight;
	 private float						lastValue = 100;
	 private long 						lastEventTime = Calendar.getInstance().getTimeInMillis();
	 private boolean 					isLampOn = false;
	 private boolean 					firstTime = true;
	 private ArrayList<Static.Morse>	messsage = new ArrayList<Static.Morse>();
	 
	 private static final float			THRESHOLD = +1000f;
	 private static final int			END_LINE_TIME = +1300;
	 private static final int			END_WORD_TIME = +400;
	 private static final int			LIMIT_TIME = +400;
	 
	 

	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_light_sensor);
	
		 mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	     mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	 }
	 
	 @Override
	 protected void onResume() {
		 mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
		 super.onResume();
	 }
	 @Override
	 protected void onPause() {
		 mSensorManager.unregisterListener(this);
		 super.onPause();
	 }
	 
	 public void onAccuracyChanged(Sensor sensor, int accuracy) {
		 if(sensor.getType() == Sensor.TYPE_LIGHT){
			 System.out.println("Sensor Changed Accuracy :" + accuracy);
		 }
	 }
	
	 public void onSensorChanged(SensorEvent event) {
		 if( event.sensor.getType() == Sensor.TYPE_LIGHT){
			 Log.i("Sensor Changed", "onSensor Change :" + event.values[0]);
			 detectMorseCode(event.values[0]);
		 }
	 }
	 
	 private void detectMorseCode(float value){
		 long currentTime = Calendar.getInstance().getTimeInMillis();
		 long deltaTime = currentTime-lastEventTime;
		 
		 // rising edge
		 if( lastValue < THRESHOLD && value > THRESHOLD ){

			 if ( deltaTime >= END_LINE_TIME && !isLampOn && !firstTime){
				 messsage.add(Static.Morse.WORD_END);
			 } else if ( deltaTime >= END_WORD_TIME && !isLampOn && !firstTime){
				 messsage.add(Static.Morse.LETTER_END);
			 } 
			 
			 firstTime = false;
			 
			 isLampOn = true;
			 lastEventTime = currentTime;
			 
			 
		 // falling edge
		 } else if (lastValue > THRESHOLD && value < THRESHOLD) {// intensity variation of -70
			 
			 if (deltaTime >= LIMIT_TIME && isLampOn){
				 messsage.add(Static.Morse.LONG );
			 } else {
				 messsage.add(Static.Morse.SHORT);
			 }
			 
			 isLampOn = false;
			 lastEventTime = currentTime;

		 }
		 lastValue=value;
		 if (deltaTime >= 3800f){
			 System.out.println(messsage);
			 messsage.clear();
		 }
	 }	 
	 
	 
}
