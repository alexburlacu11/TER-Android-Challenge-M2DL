package org.ups.ter.morsecode.morseconverter;

import java.util.ArrayList;

import org.ups.ter.morsecode.Coder;
import org.ups.ter.morsecode.Constants;
import org.ups.ter.morsecode.Static;
import org.ups.ter.morsecode.Static.Morse;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;

public class LightManager {

	private Context context;

	Camera cam;
	Handler handler;
	private Coder coder;

	boolean isCameraActive = false;
	int currentLight = 0;

	ArrayList<Morse> sequence;

	public LightManager(Context context) {
		this.context = context;
		this.handler = new Handler();
		this.coder = new Coder();
	}

	public void sendLight(String data) {
		sequence = coder.encrypt(data);
		
		currentLight = 0;
		nextLight(currentLight);
	}

	protected void ledOn() {
		cam = Camera.open();
		Parameters p = cam.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		cam.setParameters(p);
		cam.startPreview();
		isCameraActive = true;
	}

	protected final void ledOff() {
		if(isCameraActive) {
			cam.stopPreview();
			cam.release();
			isCameraActive = false;
		}
	}

	protected void toggleShortLight() {
		ledOn();
		handler.postDelayed(lighsOffRunnable, Constants.DOT_TIME);
	}

	protected void toggleLongLight() {
		ledOn();
		handler.postDelayed(lighsOffRunnable, Constants.DASH_TIME);
	}
	
	protected void toggleSpace(int duration) {
		handler.postDelayed(lighsOffRunnable, duration);
	}

	protected void nextLight(int i) {
		if (i < sequence.size()) {
			switch (sequence.get(i)) {
			case WORD_END:
				toggleSpace(Constants.SPACE_TIME);
				break;
			case LETTER_END:
				toggleSpace(Constants.BETWEEN_LETTERS_TIME);
				break;
			case SHORT:
				toggleShortLight();
				break;
			case LONG:
				toggleLongLight();
				break;
			default:
				ledOff();
				break;
			}
		}
	}

	final Runnable lighsOffRunnable = new Runnable() {
		public void run() {
			ledOff();
			// Delay between 2 elements = dot time
			handler.postDelayed(nextLightRunnable, Constants.DOT_TIME);
		}
	};
	
	final Runnable nextLightRunnable = new Runnable() {
		public void run() {
			nextLight(++currentLight);
		}
	};

}
