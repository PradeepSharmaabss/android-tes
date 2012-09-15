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
		life = 20;
	}

	public void Draw( Canvas c)
	{
paint.setStrokeWidth(3);
		for(int p = 0; p<4;p++)
		{
			Vector s= Start.get();
			float dx=Dest.x-Start.x;
			float dy = Dest.y-Start.y;
		for(int i = 0; i<10; i++)
		{
			float offsetx = (float) (Math.random()*20-10);
			float offsety = (float) (Math.random()*20-10);
			c.drawLine(s.x,s.y, s.x+(dx/11)+offsetx, s.y+(dy/11)+offsety, paint);
			s = new Vector(s.x+dx/11+offsetx,s.y+dy/11+offsety);
		}
		c.drawLine(s.x,s.y, Dest.x, Dest.y, paint);
		}

//		c.drawLine(Start.x, Start.y, Dest.x, Dest.y, paint);
//
//		c.drawLine(Start.x, Start.y, (float)(Dest.x+Math.random()*20),(float)( Dest.y+Math.random()*20), paint);
//
//		c.drawLine(Start.x, Start.y, (float)(Dest.x+Math.random()*20),(float)( Dest.y+Math.random()*20), paint);
		
	}
	public boolean Intersect(RectF s)
	{
		boolean in = false;
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.left,s.top,s.right,s.top))
	{
		in = true;
	}
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.right,s.top,s.right,s.bottom))
	{
		in = true;
	}
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.right,s.bottom,s.left,s.bottom))
	{
		in = true;
	}
	if(lineIntersect(Start.x,Start.y,Dest.x,Dest.y,s.left,s.bottom,s.left,s.top))
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
