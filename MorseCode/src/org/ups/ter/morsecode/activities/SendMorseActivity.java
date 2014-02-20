package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SendMorseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_morse);
		
		final EditText editText = (EditText) findViewById(R.id.editTextCode);
		
		
		Button send = (Button) findViewById(R.id.buttonSend); 
		send.setOnClickListener(new OnClickListener() { 
			
			@Override 
			public void onClick(View v) {
				// get text from edit text and play
			//	MorseCode m = new MorseCode(editText.getText().toString());
				try {
					//m.convert();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
	}


}