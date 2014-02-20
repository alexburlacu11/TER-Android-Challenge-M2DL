package org.ups.ter.morsecode.imgproc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class TopView extends View {

	public Bitmap mBitmap;
	private Paint mPaintYellow;
	private Paint mPaintBlack;
	
	public byte[] mYUVData;
	public int mImageWidth, mImageHeight;
	
	private int canvasWidth, canvasHeight;
	private int centerW, centerH;
	
	public TopView(Context ctx){
		super(ctx);
		
		mPaintBlack = new Paint();
        mPaintBlack.setStyle(Paint.Style.FILL);
        mPaintBlack.setColor(Color.BLACK);
        mPaintBlack.setTextSize(25);
        
        mPaintYellow = new Paint();
        mPaintYellow.setStyle(Paint.Style.FILL);
        mPaintYellow.setColor(Color.YELLOW);
        mPaintYellow.setTextSize(25);
        
        mBitmap = null;
        mYUVData = null;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(mBitmap ==null){
			canvasWidth = canvas.getWidth();
        	canvasHeight = canvas.getHeight();  
        	centerW = canvasWidth>>1;
        	centerH = canvasHeight>>1;
        	
        	
        	//image processing functions goes here
        	//image data is in the mYUVData byte array
        	//this is a yuv formated image(you can get a grayscale image by extracting the first wxh bytes from this array
        	//or use image proccesing libraries such as OpenCV
        	
        	//optionally, draw the processed image
        	//fill the mBitmap variable with processed array data and display it with canvas.drawBitmap
		}
		super.onDraw(canvas);
	}
	
	
}
