package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.Static;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.R.menu;
import org.ups.ter.morsecode.Static.Morse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendMorseActivity extends Activity {
	private static final Double 		THRESHOLD = 0.6;
	private MediaRecorder 				myAudioRecorder = new MediaRecorder();
	private String 						outputFile = null;
	private final int 					duration = 3; // seconds
    private final int 					sampleRate = 8000;
    private final int 					numSamples = duration * sampleRate;
    private static final int			END_WORD_TIME = +1300;
	private static final int			END_LETTER_TIME = +400;
	private static final int			LIMIT_TIME = +400;
	
	private boolean						previousTone = false;
	private long 						lastEventTime;
	private ArrayList<Static.Morse>		message = new ArrayList<Static.Morse>();

    private ToneGenerator 				toneGen;
    final int type = 					ToneGenerator.TONE_DTMF_0;
    public ArrayList<Long> 				time;
    public EditText						editText;

    
    public void play(View v){
    	String str = editText.getText().toString().toLowerCase();
		
		for (int i = 0;i < str.length(); i++){
			
			char bla = str.charAt(i);
			pause();
			
		    switch(bla) {
		    case 'a': playShort(1);playLong(1);pause(); break;
		    case 'b': playLong(1);playShort(3); pause(); break;
		    case 'c': playLong(1);playShort(1); playLong(1); playShort(1);pause(); break;
		    case 'd': playLong(1);playShort(2); pause(); break;
		    case 'e': playShort(1); pause(); break;
		    case 'f': playShort(2);playLong(1);playShort(1); pause(); break;
		    case 'g': playLong(2);playShort(1); pause(); break;
		    case 'h': playShort(4);pause(); break;
		    case 'i': playShort(2);pause(); break;
		    case 'j': playShort(1); playLong(3); pause(); break;
		    case 'k': playLong(1);playShort(1); playLong(1); pause(); break;
		    case 'l': playShort(1); playLong(1); playShort(2); pause(); break;
		    case 'm': playLong(2); pause(); break;
		    case 'n': playLong(1); playShort(1); pause(); break;
		    case 'o': playLong(3); pause(); break;
		    case 'p': playShort(1); playLong(2); playShort(1); pause(); break;
		    case 'q': playLong(2); playShort(1); playLong(3); pause(); break;
		    case 'r': playShort(1); playLong(1); playShort(1); pause(); break;
		    case 's': playShort(3);; pause(); break;
		    case 't': playLong(1);;pause(); break;
		    case 'u': playShort(2); playLong(1);; pause(); break;
		    case 'v': playShort(3); playLong(1);; pause(); break;
		    case 'w': playShort(1); playLong(2);; pause(); break;
		    case 'x': playLong(1);playShort(2); playLong(1);;pause(); break;
		    case 'y': playLong(1);playShort(1); playLong(2);;pause(); break;
		    case 'z': playLong(2);playShort(2);pause(); break;
		    case '1': playShort(1); playLong(4);;pause(); break;
		    case '2': playShort(2); playLong(3);;pause(); break;
		    case '3': playShort(3); playLong(2);;pause(); break;
		    case '4': playShort(4); playLong(1);;pause(); break;
		    case '5': playShort(5);;;pause(); break;
		    case '6': playLong(1);playShort(4);;pause(); break;
		    case '7': playLong(2);playShort(3);;pause(); break;
		    case '8': playLong(3);playShort(2);;pause(); break;
		    case '9': playLong(4);playShort(1);pause(); break;
		    case '0': playLong(5);pause(); break;
		    default:pause(); break;
		    }
		}
        //toneGen.release();
    }
    
    
    public void record(View v){
    	outputFile = Environment.getExternalStorageDirectory().
			      getAbsolutePath() + "/myrecording.3gp";
		myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		myAudioRecorder.setOutputFile(outputFile);
		
		try {
	         myAudioRecorder.prepare();
	         myAudioRecorder.start();
	         Toast.makeText(getApplicationContext(), "Recording started", Toast.LENGTH_LONG).show();
	          
	      //   while(currentTime - lastEventTime < 2000) {
	         int i = 0;
	         while(i < 100) {
	        	parseSignal(myAudioRecorder.getMaxAmplitude()/2700.0);
	        	
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
	         }
	         stopRecording(v);
	         
	      } catch (IllegalStateException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
    }
    
    public void stopRecording(View v){
    	myAudioRecorder.stop();				 
	    Toast.makeText(getApplicationContext(), "Audio recorded successfully",
	    Toast.LENGTH_LONG).show();
	    
	    Toast.makeText(getApplicationContext(), message.toString(), Toast.LENGTH_LONG).show();
	    message.clear();
	    File file = new File(outputFile);
	    file.delete();
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_morse);
		
		 lastEventTime = Calendar.getInstance().getTimeInMillis();
		
		toneGen = new ToneGenerator(AudioManager.STREAM_MUSIC, toneGen.MAX_VOLUME);
		time = new ArrayList<Long>();
		editText = (EditText)findViewById(R.id.editTextSendMorse);
	}

	public  void pause() {
		try {
			Thread.sleep(600);					
		} catch (InterruptedException e) {					
			e.printStackTrace();
		}
	}

	protected void parseSignal(double a) {
		boolean currentTone = a >= THRESHOLD;
		long currentTime = Calendar.getInstance().getTimeInMillis();
		long deltaTime = currentTime-lastEventTime;
			
		System.out.println("LTime: "+lastEventTime);
		System.out.println("CTime: "+currentTime);
		System.out.println("Time: "+deltaTime);
		if(currentTone  && !previousTone) {
			if (deltaTime >= END_WORD_TIME ) {							
				message.add(Morse.WORD_END);
			}
			else if (deltaTime >= END_LETTER_TIME) {							
				message.add(Morse.LETTER_END);
			}	
			previousTone = true;
			lastEventTime = currentTime;
		}
		else if (!currentTone  && previousTone) {
			if (deltaTime >= LIMIT_TIME) {
				message.add(Morse.LONG);
			} else {
				message.add(Morse.SHORT);
			}
			previousTone = false;
			lastEventTime = currentTime;
		}
	}
	
	public void playLong(int times) {
		int i = 0;
		while (i<times)	{
			try {
				toneGen.startTone(type);
				Thread.sleep(600);
				toneGen.stopTone();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			i++;
		}
	}
	
	public void playShort(int times) {
		int i = 0;
		while (i<times)	{
			try {
				toneGen.startTone(type);
				Thread.sleep(200);
				toneGen.stopTone();
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}
}
