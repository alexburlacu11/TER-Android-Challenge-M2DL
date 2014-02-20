package org.ups.ter.morsecode.activities;

import java.util.ArrayList;
import java.util.Calendar;

import org.ups.ter.morsecode.ChatMessage;
import org.ups.ter.morsecode.Coder;
import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.Static;
import org.ups.ter.morsecode.Static.Morse;
import org.ups.ter.morsecode.morseconverter.LightManager;
import org.ups.ter.morsecode.morseconverter.MorseConverter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

// Activity used to send / receive light messages
public class LightChatActivity extends Activity implements SensorEventListener {

	private static final float THRESHOLD = +600f;
	private static final int END_LINE_TIME = +1300;
	private static final int END_WORD_TIME = +400;
	private static final int LIMIT_TIME = +400;
	
	private Coder coder;
	private MorseConverter morseConverter;
	private SensorManager mSensorManager;
	private Sensor mLight;
	private float lastValue = 100;
	private long lastEventTime = Calendar.getInstance().getTimeInMillis();
	private boolean isLampOn = false;
	private boolean firstTime = true;
	private ArrayList<Morse> receivedMessage = new ArrayList<Morse>();


	ArrayAdapter<ChatMessage> adapter;
	ArrayList<ChatMessage> messages = new ArrayList<ChatMessage>();

	ListView messagesListView;
	EditText chatTextBox;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lightchat);
		init();
	}
	
	
	private void init() {
		chatTextBox = (EditText) findViewById(R.id.chatTextBox);
		messagesListView = (ListView) findViewById(R.id.messagesListView);

		adapter = new ArrayAdapter<ChatMessage>(this,
				android.R.layout.simple_list_item_1, messages);
		messagesListView.setAdapter(adapter);

		morseConverter = new MorseConverter(this);
		coder = new Coder();
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
	}
	
	
	@Override
	protected void onResume() {
		mSensorManager.registerListener(this, mLight,
				SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		if (sensor.getType() == Sensor.TYPE_LIGHT) {
			// System.out.println("Sensor Changed Accuracy :" + accuracy);
		}
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
			Log.i("Sensor Changed", "onSensor Change :" + event.values[0]);
			detectMorseCode(event.values[0]);
		}
	}

	private void detectMorseCode(float value) {
		long currentTime = Calendar.getInstance().getTimeInMillis();
		float deltaTime = currentTime - lastEventTime;

		// rising edge
		if (lastValue < THRESHOLD && value > THRESHOLD) {

			if (deltaTime >= END_LINE_TIME && !isLampOn && !firstTime) {
				receivedMessage.add(Static.Morse.WORD_END);
			} else if (deltaTime >= END_WORD_TIME && !isLampOn && !firstTime) {
				receivedMessage.add(Static.Morse.LETTER_END);
			}

			firstTime = false;

			isLampOn = true;
			lastEventTime = currentTime;

			// falling edge
		}
		else if (lastValue > THRESHOLD && value < THRESHOLD) {// intensity
																// variation of
																// -70
			if (deltaTime >= LIMIT_TIME && isLampOn) {
				receivedMessage.add(Static.Morse.LONG);
			} else {
				receivedMessage.add(Static.Morse.SHORT);
			}

			isLampOn = false;
			lastEventTime = currentTime;
		}
		
		lastValue = value;
		
		
		if (deltaTime >= 3800f) {
			if(receivedMessage.size() > 0) {	
				System.out.println(receivedMessage);
				messages.add(new ChatMessage(receivedMessage.toString(), false));
				adapter.notifyDataSetChanged();			
				receivedMessage.clear();
			}
		}
	}


	public void sendClicked(View v) {
		String dataToSend = chatTextBox.getText().toString();
		morseConverter.sendLight(dataToSend);
		messages.add(new ChatMessage(dataToSend, true));
		chatTextBox.setText("");
		adapter.notifyDataSetChanged();
	}

}
