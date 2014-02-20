package org.ups.ter.morsecode.morseconverter;

import org.ups.ter.morsecode.Constants;
import org.ups.ter.morsecode.Static;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;

public class ShortMessageManager {

	private Handler handler;
	private Vibrator vibrator;

	int currentSign = 0;

	
	// Temp sequence for testing
	Static.Morse[] sequence = { Static.Morse.SHORT, Static.Morse.EMPTY, Static.Morse.LONG, Static.Morse.SHORT };

		
	public ShortMessageManager(Context context) {
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		handler = new Handler();
	}
	
	public void readSequence() {
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
		if (i < sequence.length) {
			switch (sequence[i]) {
			case EMPTY:
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
