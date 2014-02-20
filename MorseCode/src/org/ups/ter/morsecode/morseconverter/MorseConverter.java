package org.ups.ter.morsecode.morseconverter;

import android.content.Context;

public class MorseConverter {

	private Context context;
	private LightManager lightManager;
	private ShortMessageManager smsManager;
	
	public MorseConverter(Context context) {
		this.context = context;
		this.lightManager = new LightManager(context);
		this.smsManager = new ShortMessageManager(context);
	}
	
	public void sendLight(String data) {
		lightManager.sendLight(data);
	}

	public void vibrate() {
		smsManager.readSequence();
	}
	
}
