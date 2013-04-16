package com.example.warlockgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class Foot extends GameObject {
	int footY = 0;
	int footX = 10;
	int footDirectionX = 1;
	int footDirectionY = 0;
	Paint colour;
	Paint stroke;

	public Foot(int _fx, Paint _c, Paint _s) {
		this.footX = _fx;
		this.colour = new Paint();
		this.colour.setStyle(Style.FILL);
		this.colour = _c;
		this.stroke = _s;
	}

	@Override
	public void Update() {
		// this.x += 1;
		this.footX += this.footDirectionX;
		if (this.footX > 10) {
			this.footDirectionX = -1;
			this.footDirectionY = 0;
		}
		if (this.footX < -20) {
			this.footDirectionX = 1;
			this.footDirectionY = -1;
		}
		this.footY += this.footDirectionY;
		if (this.footY < -15)
			this.footDirectionY = 1;

	}

	public void Draw(Canvas c, int Direction) {

		c.save();

		c.scale(Direction, 1);
		Path wallpath = new Path();

		wallpath.reset(); // only needed when reusing this path for a new build
		wallpath.moveTo(5, 5);
		wallpath.lineTo(5 - (this.footY / 2), 25);
		wallpath.lineTo(this.footX, -15 + this.footY + 50);
		wallpath.lineTo(15 + this.footX, -15 + this.footY + 50);
		wallpath.lineTo(20 + this.footX, this.footY + 50);
		wallpath.lineTo(this.footX - 20, this.footY + 50);
		wallpath.lineTo(this.footX - 10, this.footY + 30);
		wallpath.lineTo(-10 - (this.footY / 2), 15);
		wallpath.lineTo(-10, 0);// used for first point
								// there is a setLastPoint action but i
								// found it not to work as expected
		c.drawPath(wallpath, this.colour);
		c.drawPath(wallpath, this.stroke);
		c.restore();

	}
}
