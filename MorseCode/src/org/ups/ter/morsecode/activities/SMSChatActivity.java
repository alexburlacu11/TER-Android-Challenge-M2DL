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
public class SMSChatActivity extends Activity {

	private MorseConverter morseConverter;
	
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
	}

	public void sendClicked(View v) {
		String dataToSend = chatTextBox.getText().toString();
		morseConverter.vibrate(dataToSend);
		messages.add(new ChatMessage(dataToSend, true));
		chatTextBox.setText("");
		adapter.notifyDataSetChanged();
	}

}
