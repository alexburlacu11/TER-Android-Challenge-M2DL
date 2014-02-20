package org.ups.ter.morsecode.imgproc;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;


public class PreviewSurf extends SurfaceView implements Callback {

	private Camera mCamera;
	private SurfaceHolder mHolder;
	private TopView mTopView;
	private boolean mFinished;
	
	public PreviewSurf(Context ctx, TopView topView){
		super(ctx);
		
		mTopView = topView;
		mFinished = false;
		
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Auto-generated method stub
		Camera.Parameters parameters = mCamera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        int frameWidth = w;
        int frameHeight = h;

        // selecting optimal camera preview size
        {
            double minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizes) {
                if (Math.abs(size.height - h) < minDiff) {
                    frameWidth = size.width;
                    frameHeight = size.height;
                    minDiff = Math.abs(size.height - h);
                }
            }
        }
        Log.i("MIN RES", String.valueOf(frameWidth) + " x " + String.valueOf(frameHeight));
        parameters.setPreviewSize(frameWidth, frameHeight);
        
        int max = 15;
        List<Integer> fps = parameters.getSupportedPreviewFrameRates();
        {
        	int maxFps = Integer.MIN_VALUE;
        	for(Integer i : fps){
        		if(i.intValue() > maxFps){
        			max = i.intValue();
        			maxFps = max;
        		}
        	}
        }
        Log.i("MAX FPS", String.valueOf(max));
        parameters.setPreviewFrameRate(max);
        parameters.setSceneMode(Camera.Parameters.SCENE_MODE_NIGHT);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_SHADE);
        mCamera.setParameters(parameters);
        mCamera.startPreview();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		mCamera = Camera.open();
		try{
			mCamera.setPreviewDisplay(holder);
			//set callback for camera frames
			mCamera.setPreviewCallback(new Camera.PreviewCallback() {
				
				public void onPreviewFrame(byte[] data, Camera camera) {
					// TODO Auto-generated method stub
					if(mTopView == null || mFinished){
						return;
					}
					
					if(mTopView.mBitmap == null){
						Camera.Parameters params = mCamera.getParameters();
						mTopView.mImageHeight = params.getPreviewSize().width;
						mTopView.mImageWidth = params.getPreviewSize().height;
						mTopView.mBitmap = Bitmap.createBitmap(mTopView.mImageWidth, mTopView.mImageHeight, Bitmap.Config.ARGB_8888);
						mTopView.mYUVData = new byte[data.length];
					}
					System.arraycopy(data, 0, mTopView.mYUVData, 0, data.length);
					mTopView.invalidate();
				}
			});
		}catch(Exception e){
			mCamera.release();
			mCamera = null;
		}

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mFinished = true;
    	mCamera.setPreviewCallback(null);
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
		
	}

}
