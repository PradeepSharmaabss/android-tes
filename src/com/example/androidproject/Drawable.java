package com.example.androidproject;

import java.io.File;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class Drawable {

	public Matrix matrix;
	public Paint paint = new Paint();
	public Bitmap bmp;
	public Drawable()
	{
		paint.setColor(Color.BLUE);
		
	}
	public void Draw(Object obj, RectF r)
	{
		Canvas c = (Canvas)obj;
			//c.drawBitmap(bmp, null, r, paint);
		if(bmp == null)
			c.drawRect(r, paint);
	}
	public void Draw(Object obj)
	{
		Canvas c = (Canvas)obj;
		if(bmp!=null)c.drawBitmap(bmp, 0, 0,paint);
		
		//imageView.setImageBitmap(bmp);
	}
}
