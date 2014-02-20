package org.ups.ter.morsecode.morseconverter;

import org.ups.ter.morsecode.Static;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Handler;

public class LightManager {

	private static int DOT_TIME = 200;
	private static int DASH_TIME = 3 * DOT_TIME;
	private static int BETWEEN_LETTERS_TIME = 3 * DOT_TIME;
	private static int SPACE_TIME = 7 * DOT_TIME;

	Camera cam;
	Handler handler;
	
	boolean isCameraActive = false;
	int currentLight = 0;

	
	// Temp sequence for testing
	Static.Morse[] lights = { Static.Morse.SHORT, Static.Morse.LONG, Static.Morse.EMPTY, Static.Morse.LONG };
	
	

	public LightManager() {
		handler = new Handler();
	}

	public void sendLight() {
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
		handler.postDelayed(lighsOffRunnable, DOT_TIME);
	}

	protected void toggleLongLight() {
		ledOn();
		handler.postDelayed(lighsOffRunnable, DASH_TIME);
	}
	
	protected void toggleSpace() {
		handler.postDelayed(lighsOffRunnable, SPACE_TIME);
	}

	protected void nextLight(int i) {
		if (i < lights.length) {
			switch (lights[i]) {
			case EMPTY:
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
			handler.postDelayed(nextLightRunnable, DOT_TIME);
		}
	};
	
	final Runnable nextLightRunnable = new Runnable() {
		public void run() {
			nextLight(++currentLight);
		}
	};

}
