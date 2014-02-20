package org.ups.ter.morsecode.morseconverter;

import org.ups.ter.morsecode.Constants;
import org.ups.ter.morsecode.Static;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;

public class LightManager {

	private Context context;

	Camera cam;
	Handler handler;
	
	boolean isCameraActive = false;
	int currentLight = 0;

	
	// Temp sequence for testing
	Static.Morse[] lights = { Static.Morse.SHORT, Static.Morse.LONG, Static.Morse.WORD_END, Static.Morse.LONG };
	
	

	public LightManager(Context context) {
		this.context = context;
		this.handler = new Handler();
	}

	public void sendLight(String data) {
		// lights = data ...
		
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
	
	protected void toggleSpace() {
		handler.postDelayed(lighsOffRunnable, Constants.SPACE_TIME);
	}

	protected void nextLight(int i) {
		if (i < lights.length) {
			switch (lights[i]) {
			case WORD_END:
				toggleSpace();
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
