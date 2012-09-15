package Game;

import android.graphics.Canvas;
import android.graphics.RectF;
import Tools.Vector;

public class LightningBolt extends Projectile {
	Vector Start;
	Vector Dest;

	public LightningBolt( Vector _start,Vector _dest)
	{
		super(_start,_dest,null);
		
		Start = _start;
		Dest = _dest;
		life = 5;
	}

	public void Draw( Canvas c)
	{
		c.drawLine(Start.x, Start.y, Dest.x, Dest.y, paint);

		c.drawLine(Start.x, Start.y, (float)(Dest.x+Math.random()*20),(float)( Dest.y+Math.random()*20), paint);

		c.drawLine(Start.x, Start.y, (float)(Dest.x+Math.random()*20),(float)( Dest.y+Math.random()*20), paint);
		
	}
	public boolean Intersect(RectF s)
	{
		boolean in = false;
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.top,s.left,s.top,s.right))
	{
		in = true;
	}
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.top,s.right,s.bottom,s.right))
	{
		in = true;
	}
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.bottom,s.right,s.bottom,s.left))
	{
		in = true;
	}
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.bottom,s.left,s.top,s.left))
	{
		in = true;
	}
		return in;
	}
	  public static boolean lineIntersect(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
	      float denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
	      if (denom == 0.0) { // Lines are parallel.
	         return false;
	      }
	      float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3))/denom;
	      float ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3))/denom;
	        if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f) {
	            // Get the intersection point.
	            return true;
	        }

	      return false;
	   }
}
