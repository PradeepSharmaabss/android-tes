package World;

import Tools.Drawable;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.RectF;


public class Tile extends Drawable {
	public RectF rect;
	public RectF platform;
	public Vector position,size;
	RectF temp = new RectF();
	
	public Tile()
	{
		this(0,0,0,0);
	}
	public Tile(float x, float y, float w, float h)
	{
		this(new Vector(x,y),new Vector(w,h));
	}
	public Tile(Vector v, Vector v2)
	{
		super();
		position = v.get();
		size = v2.get();
		rect = new RectF(v.x, v.y, v2.x, v2.y);
	}
	
	public void Draw(Canvas c)
	{
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
		platform = new RectF(rect.left, rect.top, rect.left + size.x, rect.top+1);
		super.Draw(c, rect);
	}

	public void DrawAt(Canvas c, float x, float y)
	{
		rect = new RectF(position.x - x, position.y - y, 
				(position.x - x ) + size.x,
				(position.y - y ) + size.y);
		platform = new RectF(rect.left,rect.top, rect.left + rect.width(), rect.top + 1);
		super.Draw(c, rect);
	}
	
	public Tile get()
	{
		return new Tile(position.get(),size.get());
	}
	
}
