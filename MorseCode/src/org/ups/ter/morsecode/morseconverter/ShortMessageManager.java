package org.ups.ter.morsecode.morseconverter;

import java.util.ArrayList;

import org.ups.ter.morsecode.Coder;
import org.ups.ter.morsecode.Constants;
import org.ups.ter.morsecode.Static;
import org.ups.ter.morsecode.Static.Morse;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.telephony.SmsManager;

public class ShortMessageManager {

	private static String SMS_DEST = "+33614441619";
	
	private Coder coder;
	private Handler handler;
	private Vibrator vibrator;

	int currentSign = 0;

	ArrayList<Morse> sequence;
		
	public ShortMessageManager(Context context) {
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		handler = new Handler();
		coder = new Coder();
	}
	
	public void sendSequence(String data) {
		if(null != data && !data.trim().equals("")) {
			SmsManager smsManager =	SmsManager.getDefault();
			smsManager.sendTextMessage(SMS_DEST, null, data, null, null);
		}
	}
	
	public void readSequence(String data) {
		sequence = coder.encrypt(data);
		currentSign = 0;
		nextSign(currentSign);
	}
	
	protected void makePhoneVibrate(int duration) {
		vibrator.vibrate(duration);		
		
		// vibrate() n'est pas bloquant ...
		// on wait la durée de la vibration + la durée de l'espace entre signe
		handler.postDelayed(nextSignRunnable, duration + Constants.DOT_TIME);
	}
	
	protected void toggleSpace() {
		handler.postDelayed(nextSignRunnable, Constants.SPACE_TIME);
	}

	protected void nextSign(int i) {
		if (i < sequence.size()) {
			switch (sequence.get(i)) {
			case WORD_END:
				toggleSpace();
				break;
			case LETTER_END:
				toggleSpace();
				break;
			case SHORT:
				makePhoneVibrate(Constants.DOT_TIME);
				break;
			case LONG:
				makePhoneVibrate(Constants.DASH_TIME);
				break;
			default:
				break;
			}
		}
	}
	
	
	final Runnable nextSignRunnable = new Runnable() {
		public void run() {
			nextSign(++currentSign);
		}
	};

	
}
