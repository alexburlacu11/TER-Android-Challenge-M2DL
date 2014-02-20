package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.R.menu;

import java.io.File;
import java.io.IOException;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SoundActivity extends Activity {

MediaRecorder recorder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sound);
		recorder = new MediaRecorder();
		//String status = Environment.getExternalStorageState();
		
		
		
		Button start = (Button) findViewById(R.id.buttonStart);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String path = Environment.getExternalStorageDirectory().getAbsolutePath();
				path += "/audiorecordtest.3gp";

				recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				recorder.setOutputFile(path);
				try {
					recorder.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				recorder.start();
				
				
			}
		});
		
		Button stop = (Button) findViewById(R.id.buttonStop);
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				recorder.stop();
				recorder.release();
				
				File SDCardpath = getFilesDir();
		        File myDataPath = new File(SDCardpath.getAbsolutePath()
		                + "/.My Recordings");

		        // mydir = context.getDir("media", Context.MODE_PRIVATE);
		        if (!myDataPath.exists())
		            myDataPath.mkdir();


		        File audiofile = new File(myDataPath + "/" + "morse_rec");
		        
			}
		});
		
		Button convert = (Button) findViewById(R.id.buttonConvert); 
		convert.setOnClickListener(new OnClickListener() { 
			
			@Override 
			public void onClick(View v) {
				// Show Toast with text
				
				
				
			}
		});
		
		Button sendCode = (Button) findViewById(R.id.buttonSendCode); 
		sendCode.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SoundConverter.class);
				startActivity(intent);
			}
		});
		
	}	

}
