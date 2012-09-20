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

}
}
