package com.example.warlockgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Bar {

	int x;
	int y;
	int h;
	int w;
	Paint Colour;

	public Bar(int _x, int _y, int _width, int _height) {

		this.x = _x;
		this.y = _y;
		this.h = _height;
		this.w = _width;
		this.Colour = new Paint();
	}

	void Draw(Canvas c, Statistic s) {

		if (s.current / s.max > 0.8)
			this.Colour.setColor(Color.YELLOW);
		else if (s.current / s.max > 0.2)
			this.Colour.setColor(Color.GREEN);
		else
			this.Colour.setColor(Color.RED);
		c.drawRect(new Rect(this.x, this.y, (int) (this.x + this.w
				* (s.current / s.max)), this.y + this.h), this.Colour);
	}
}