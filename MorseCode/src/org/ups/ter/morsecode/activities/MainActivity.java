package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.R;
import org.ups.ter.morsecode.R.layout;
import org.ups.ter.morsecode.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void toCamera(View v){
		Intent intent = new Intent(getApplicationContext(),
				LightSensorActivity.class);
		startActivity(intent);
	}
	
	public void toSound(View v){
		Intent intent = new Intent(getApplicationContext(),
				SoundActivity.class);
		startActivity(intent);
	}

}
