package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.Static;

import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.Static;
import org.ups.ter.morsecode.morseconverter.LightManager;
import org.ups.ter.morsecode.morseconverter.MorseConverter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

// Activity used to send messages (sound, light, ...)
public class ChatActivity extends Activity {

	Static.SendMode sendMode;
	MorseConverter morseConverter;
	
	EditText chatTextBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set sendMode according to previous menu choice
		
		sendMode = Static.SendMode.Light;
		// sendMode = Static.SendMode.SMS;
		
		setContentView(R.layout.activity_chat);
		
		init();
	}
	
	
	private void init() {
		chatTextBox = (EditText) findViewById(R.id.chatTextBox);
		morseConverter = new MorseConverter(this);
	}


	public void sendClicked(View v) {

		String dataToSend = chatTextBox.getText().toString();
		
		switch(sendMode) {
		case Sound:
			break;
		case Light:
			morseConverter.sendLight(dataToSend);
			break;
		case SMS:
			morseConverter.vibrate();
			break;
		default:
			break;
		}
		
	}

}
