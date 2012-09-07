package World;

import android.graphics.RectF;

import com.example.androidproject.Drawable;
import com.example.androidproject.Vector;

public class Tile extends Drawable{
	public RectF rect;
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
	
	public void Draw(Object obj)
	{
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
		super.Draw(obj, rect);
		//c.save(Canvas.MATRIX_SAVE_FLAG);
		//c.setMatrix(matrix);
		//c.drawRect(rect, p);
		//c.restore();
	}

	public void DrawAt(Object obj, float x)
	{
		rect = new RectF(position.x - x, position.y, (position.x -x ) + size.x, position.y + size.y);
		System.out.println(""+(position.x - x));
		//rect.position.x -= x;
		//temp.offsetTo(position.x - x, position.y);
		super.Draw(obj, rect);
	}
	
	public Tile get()
	{
		return new Tile(position.get(),size.get());
	}
	
}
