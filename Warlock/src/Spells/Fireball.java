package Spells;

import android.graphics.Canvas;
import android.graphics.Color;
import Game.*;
import Tools.Vector;

public class Fireball extends Projectile {
public Fireball(Vector _from,Vector _to,GameObject _shooter)
{
	super(_from, _to,_shooter);
}
public void Draw(Canvas c)
{
	paint.setColor(Color.RED);
	c.drawCircle(position.x, position.y, size.x/2, paint);
	for(int x = 0; x<10;x++)
	{
		paint.setARGB(((10-x)*25), 255, x*(int)(Math.random()*25),0);
		//paint.setColor(paint.getColor()+(int)(x*Math.random()*10));
	c.drawCircle(position.x-(int)(20*Math.random()-10+velocity.x*x), position.y-(int)(20*Math.random()-10+velocity.y*x), size.x/3, paint);
	}
}
}
