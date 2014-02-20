package org.ups.ter.morsecode.activities;

import org.ups.ter.morsecode.imgproc.PreviewSurf;
import org.ups.ter.morsecode.imgproc.TopView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;


public class MorseCamReaderActivity extends Activity {
	
	private TopView mTopView;
	private PreviewSurf mPreview;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Hide the window title.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        mTopView = new TopView(this);
        mPreview = new PreviewSurf(this, mTopView);
        setContentView(mPreview);
        addContentView(mTopView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }
}