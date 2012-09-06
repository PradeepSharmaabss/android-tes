package com.example.androidproject;

import android.graphics.Canvas;
import android.graphics.Color;
import Shapes.Rectangle;

public class Button {
	String text;
	public int id;
	Rectangle rect;
	public Button(float x ,float y, float w, float h,int t)
	{
		id = t;
		rect = new Rectangle(new Vector(x,y),new Vector(w,h));
	}
	
	public void Draw(Canvas c)
	{
		rect.Draw(c);

	}
	public boolean isDown()
	{
		if(rect.Contains(Finger.position.x, Finger.position.y) && Finger.down)
    	{
			rect.paint.setColor(Color.BLUE);
			return true;
    	}
		rect.paint.setColor(Color.RED);
		return false;
	}
}
