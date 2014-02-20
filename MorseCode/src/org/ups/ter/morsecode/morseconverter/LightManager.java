package org.ups.ter.morsecode.morseconverter;

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

	int[] lights = { 1, 2, 0, 2 };
	
	int currentLight = 0;

	public LightManager() {
		handler = new Handler();
	}

	public void sendLight() {
		currentLight = 0;
		nextLight(currentLight);
	}

	protected void ledon() {
		cam = Camera.open();
		Parameters p = cam.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);
		cam.setParameters(p);
		cam.startPreview();
		isCameraActive = true;
	}

	protected final void ledoff() {
		if(isCameraActive) {
			cam.stopPreview();
			cam.release();
			isCameraActive = false;
		}
	}

	protected void toggleShortLight() {
		ledon();
		handler.postDelayed(lighsOffRunnable, DOT_TIME);
	}

	protected void toggleLongLight() {
		ledon();
		handler.postDelayed(lighsOffRunnable, DASH_TIME);
	}
	
	protected void toggleSpace() {
		handler.postDelayed(lighsOffRunnable, SPACE_TIME);
	}

	protected void nextLight(int i) {
		if (i < lights.length) {
			switch (lights[i]) {
			case 0:
				toggleSpace();
				break;
			case 1:
				toggleShortLight();
				break;
			case 2:
				toggleLongLight();
				break;
			default:
				ledoff();
				break;
			}
		}
	}

	final Runnable lighsOffRunnable = new Runnable() {
		public void run() {
			ledoff();
			handler.postDelayed(nextLightRunnable, DOT_TIME);
		}
	};
	
	final Runnable nextLightRunnable = new Runnable() {
		public void run() {
			nextLight(++currentLight);
		}
	};

}
