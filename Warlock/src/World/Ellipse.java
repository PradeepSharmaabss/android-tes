package World;
import com.example.warlockgame.RenderThread;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import Tools.Vector;
public class Ellipse {
	Vector Position;
	Vector Size;
	Paint paint = new Paint();
public Ellipse(Vector _position,Vector _size)
{
	Position = _position;
	Size = _size;

}
int phase;
void Draw(Canvas c)
{
	phase+=1;
	if(Size.x>5)
	{if(phase%5==1)
	{
	Size.x-=2;
	Size.y-=1;
	}
	}
	paint = new Paint();
	paint.setColor(Color.DKGRAY);
	c.drawOval(new RectF(Position.x-Size.x/2,Position.y-Size.y/2,Position.x+Size.x/2,Position.y+Size.y/2), paint);
	paint.setAlpha(125);
	if(ArchieWithin())
	{
	paint.setColor(Color.GRAY);
	}
	else
	{
	paint.setColor(Color.RED);
	}
	c.drawOval(new RectF(Position.x-Size.x/2+Size.x/7,Position.y-Size.y/2+Size.y/7,Position.x+Size.x/2-Size.x/7,Position.y+Size.y/2-Size.y/7), paint);
}
public boolean ArchieWithin ()
{
	if(WithinEllipse(Position.x,Position.y-RenderThread.size.y/2-Size.y/2,Size.x,Size.y,RenderThread.archie.feet.x,RenderThread.archie.feet.y))
	{
		return true;
	}
	return false;
}
public boolean WithinEllipse ( float ex , float ey , float ea , float eb ,float px ,float py ) {
//	 ex,ey = position ellipse
//	 ea,eb = radiants of ellipse
//	 px,py = position of point
	 float dx = px - ex;
	 float dy = py - ey;

	 return (dx*dx)/(ea*ea) + (dy*dy)/(eb*eb) <= 1;

	 }

}
