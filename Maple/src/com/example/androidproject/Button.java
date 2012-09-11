package com.example.androidproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Button {
	Paint paint;
	String text;
	public int id;
	RectF rect;
	public Button(float x ,float y, float w, float h,int t)
	{
		paint = new Paint();
		id = t;
		rect = new RectF(x, y, x+w, y+h);
	}
	
	public void Draw(Canvas c)
	{
		c.drawRect(rect, paint);
	}
	public boolean isDown()
	{
		if(rect.contains(Finger.position.x, Finger.position.y) && Finger.down)
    	{
			paint.setColor(Color.BLUE);
			return true;
    	}
		paint.setColor(Color.RED);
		return false;
	}
}
