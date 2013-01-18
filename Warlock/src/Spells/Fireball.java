package Spells;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import Game.*;
import Tools.Vector;

public class Fireball extends Projectile {
	public Fireball(Vector _from, Vector _to, GameObject _shooter)
	{
		super(_from, _to, _shooter);
		paint.setColor(Color.RED);
		shadowPaint.setColor(Color.argb(200, 0, 0, 0));
	}
	public void Draw(Canvas c)
	{
		c.drawCircle(position.x, position.y, size.x/2, paint);
		for(int x = 0; x<10;x++)
		{
			paint.setARGB(((10-x)*25), 255, x*(int)(Math.random()*25),0);
			//paint.setColor(paint.getColor()+(int)(x*Math.random()*10));
			float 	posx = position.x-(int)(20*Math.random()-10+velocity.x * x) ,
					posy = position.y-(int)(20 * Math.random()-10 + velocity.y * x);
			c.drawCircle(posx+20,posy+20, size.x/3,shadowPaint);
			c.drawCircle(posx, posy, size.x/3, paint);
			
		}	

	}
	
}
