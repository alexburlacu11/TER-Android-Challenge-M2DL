package org.ups.ter.morsecode.morseconverter;

public class MorseConverter {

	private LightManager lightManager;
	
	public MorseConverter() {
		lightManager = new LightManager();
	}
	
	public void sendLight() {
		lightManager.sendLight();
	}
	
}
