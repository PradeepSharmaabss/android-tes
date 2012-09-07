package com.example.androidproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

public abstract class Drawable {

	public Matrix matrix;
	public Paint paint = new Paint();
	public Drawable()
	{
		paint.setColor(Color.BLUE);
	}
	public void Draw(Object obj, RectF r)
	{
		Canvas c = (Canvas)obj;
		c.drawRect(r, paint);
		//c.save(Canvas.MATRIX_SAVE_FLAG);
		//c.setMatrix(matrix);
		//c.drawRect(rect, p);
		//c.restore();
	}
	public void Draw(Object obj)
	{
		Canvas c = (Canvas)obj;
	}
}
