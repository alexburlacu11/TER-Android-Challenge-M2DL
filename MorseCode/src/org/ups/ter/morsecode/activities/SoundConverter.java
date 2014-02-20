package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Handler;


/**
 *
 * @author alex
 */

public class SoundConverter extends Activity {
	 private final int duration = 3; // seconds
	 private final int sampleRate = 8000;
	 private final int numSamples = duration * sampleRate;
	 private final double sample[] = new double[numSamples];
	 private final double freqOfTone = 100; // hz

	 private final byte generatedSnd[] = new byte[2 * numSamples];

	 Handler handler = new Handler();
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  
	  setContentView(R.layout.activity_send_morse);
	 }

	 @Override
	 protected void onResume() {
	  super.onResume();

	  // Use a new tread as this can take a while
	  Thread thread = new Thread(new Runnable() {
	      public void run() {
	        genTone();
	        handler.post(new Runnable() {
	    
	    public void run() {
	     playSound();
	    }
	   });
	      }   
	    });
	    thread.start();
	 }
	 
	 void genTone(){
	  // fill out the array
	  for (int i = 0; i < numSamples; ++i) {
	   sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
	  }

	  // convert to 16 bit pcm sound array
	  // assumes the sample buffer is normalised.
	  int idx = 0;
	  for (double dVal : sample) {
	   short val = (short) (dVal * 32767);
	   generatedSnd[idx++] = (byte) (val & 0x00ff);
	   generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
	  }
	 }

	void playSound(){
		AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
		8000, AudioFormat.CHANNEL_CONFIGURATION_MONO,
		AudioFormat.ENCODING_PCM_16BIT, numSamples,
		AudioTrack.MODE_STATIC);
		audioTrack.write(generatedSnd, 0, numSamples);
		audioTrack.play();
	}
}
