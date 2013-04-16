package com.example.warlockgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;

public class Creature extends GameObject {
	int posX = 50;
	int posY = 150;
	int HornLength = 1;
	float HeadRoll = 0;
	int HeadRollDirection = 1;
	int Direction = 1;
	Paint colour = new Paint();
	Paint stroke = new Paint();
	Statistic Stomach = new Statistic(0, 100);

	Foot frontFoot;
	Foot backFoot;
	int bodyY = 50;
	int bodyX = 50;
	int poopTimer = 100;
	Bar StomachBar = new Bar(30, 50, 70, 15);
	int r = 30, g = 30, b = 30;

	public Creature() {
		this.frontFoot = new Foot(10, this.colour, this.stroke);
		this.backFoot = new Foot(-20, this.colour, this.stroke);
		this.colour.setColor(Color.GRAY);
		this.colour.setStyle(Style.FILL);
		this.stroke.setStyle(Paint.Style.STROKE);
		this.stroke.setStrokeWidth(2);
		this.stroke.setARGB(255, 0, 0, 0);
	}

	public int Feed(int Cooldown) {
		if (Cooldown == 0) {
			if (this.Stomach.max - this.Stomach.current < 20)
				this.Stomach.current = this.Stomach.max;
			else
				this.Stomach.current += 20;
			Cooldown = 100;
			this.HornLength += 1;
			this.bodyY += 1;
			this.bodyX += 1;
			this.r += 2;
			this.colour.setARGB(255, this.r, this.g, this.b);
			// this.colour = color(red(this.colour),
			// green(this.colour),blue(this.colour), 255);
		}
		return Cooldown;
	}

	@Override
	public void Draw(Canvas c) {
		if (this.HeadRollDirection == 1) {
			if (this.HeadRoll > 30)
				this.HeadRollDirection = -1;
		} else if (this.HeadRoll < 0)
			this.HeadRollDirection = 1;
		this.HeadRoll += this.HeadRollDirection;

		if (this.posX > 200) {
			this.Direction = -1;
			;
		}
		if (this.posX < 50)
			this.Direction = 1;
		this.frontFoot.Update();
		this.backFoot.Update();

		this.posX += this.Direction;

		c.save();
		c.translate(this.posX * 2, this.posY);

		c.scale(5, 5);
		// fill(this.colour);
		this.backFoot.Draw(c, this.Direction);
		c.save();

		// c.translate(0, this.bodyY - 50 + this.frontFoot.footY / 3);
		c.scale(this.Direction, 1);
		c.rotate((float) (this.HeadRoll / 360 * Math.PI));

		// fill(this.colour);

		// stroke(0);
		c.drawOval(new RectF(0 - this.bodyX / 2, -(this.bodyY - 50)
				- this.bodyY / 2, this.bodyX / 2, -this.bodyY + 50 + this.bodyY
				/ 2), this.colour);
		c.drawOval(new RectF(0 - this.bodyX / 2, -(this.bodyY - 50)
				- this.bodyY / 2, this.bodyX / 2, -this.bodyY + 50 + this.bodyY
				/ 2), this.stroke);
		// (0, -(this.bodyY - 50), this.bodyX, this.bodyY);
		c.drawOval(new RectF(0, -10 - (this.bodyY - 50), 10, -this.bodyY + 50),
				this.colour);
		c.drawOval(new RectF(0, -10 - (this.bodyY - 50), 10, -this.bodyY + 50),
				this.stroke);
		Path wallpath = new Path();

		wallpath.reset(); // only needed when reusing this path for a new build
		wallpath.moveTo(20, -5 - (this.bodyY - 50));
		wallpath.lineTo(20 + this.HornLength, -20 - this.HornLength
				- (this.bodyY - 50));

		wallpath.lineTo(0, -20 - (this.bodyY - 50));
		c.drawPath(wallpath, this.colour);
		c.drawPath(wallpath, this.stroke);
		c.restore();
		// fill(this.colour);
		this.frontFoot.Draw(c, this.Direction);
		c.restore();
		// Draw Stomach bar
		if (this.Stomach.current > 0)
			this.Stomach.current -= 1;

		this.StomachBar.Draw(c, this.Stomach);
		boolean poop = false;

		if (this.poopTimer != 0)
			this.poopTimer--;
		else {
			this.poopTimer = 100;
			poop = true;
		}

	}
}