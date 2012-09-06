package World;

import android.graphics.Canvas;
import com.example.androidproject.Vector;
import Shapes.Rectangle;

public class Tile {
	public Vector size = new Vector(),position = new Vector();
	public Rectangle rect = new Rectangle(new Vector(),new Vector());
	public Tile()
	{
		this(0,0,0,0);
	}
	public Tile(float x, float y, float w, float h)
	{
		this(new Vector(x,y),new Vector(w,h));
	}
	public Tile(Vector v,Vector v2)
	{
		position = v;
		size = v2;
		rect.position = position;
		rect.size = size;
	}
	
	public void Draw(Canvas c)
	{
		rect.Draw(c);
	}
	
	public Tile get()
	{
		return new Tile(position.get(),size.get());
	}
}
