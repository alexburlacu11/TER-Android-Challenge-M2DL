package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.Static;
import org.ups.ter.morsecode.morseconverter.LightManager;
import org.ups.ter.morsecode.morseconverter.MorseConverter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class ChatActivity extends Activity {

	Static.SendMode sendMode;
	MorseConverter morseConverter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// set sendMode according to previous menu choice
		
		setContentView(R.layout.activity_chat);
		
		init();
	}
	
	
	private void init() {
		morseConverter = new MorseConverter();
	}


	public void sendClicked(View v) {
		
		morseConverter.sendLight();

//		switch(sendMode) {
//		case Sound:
//			break;
//		case Light:
//			break;
//		case SMS:
//			break;
//		default:
//			break;
//		}
		
		// Send !
		// morseConverter.sendSound(xxxxx) 
		// ...
		
	}

}
