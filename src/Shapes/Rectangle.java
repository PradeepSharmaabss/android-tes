package Shapes;
import com.example.androidproject.Finger;
import com.example.androidproject.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Rectangle {
	public Vector position,size;
	public Paint paint;
	public String type = "NULL";
	public String text = "none";
	public float angle = 0;
	
	public float Left() { return this.position.x; }
	public float Right() { return this.position.x + this.size.x; }
	public float Top() { return this.position.y; }
	public float Bottom() { return this.position.y + this.size.y; }
	public float Bot() { return Bottom(); }
	
	public Vector topright = new Vector();
	public Vector topleft = new Vector();
	public Matrix matrix = new Matrix();
	public Rectangle(Vector v, Vector v2) {
		super();
		paint = new Paint();
		this.type = "rectangle";
		this.position = v;
		this.size = v2;
	}
	public Rectangle(Vector v, Vector v2, float a) {
		super();
		this.type = "rectangle";
		this.position = v;
		this.size = v2;
		this.angle = a;
	}
	public void Draw(Canvas c)
	{
		c.save(Canvas.MATRIX_SAVE_FLAG);
		c.setMatrix(matrix);
		c.drawRect(Left(), Top(), Right(), Bottom(), paint);
		c.restore();
	}
	public void Draw(Canvas c, Paint p)
	{
		c.save(Canvas.MATRIX_SAVE_FLAG);
		c.setMatrix(matrix);
		c.drawRect(Left(), Top(), Right(), Bottom(), p);
		c.restore();
	}
	public void Update()
	{
		
	}
	public boolean Click()
	{
    	if(Contains(Finger.position.x, Finger.position.y) && Finger.down)
    	{
    		paint.setColor(Color.BLUE);
    		return true;
    	}
		paint.setColor(Color.RED);
		return false;
	}
	public boolean Down()
	{
		if(Contains(Finger.position.x, Finger.position.y) && Finger.down)
    	{
			paint.setColor(Color.BLUE);
			return true;
    	}
		paint.setColor(Color.RED);
		return false;
	}
	public Rectangle get()
	{
		return new Rectangle(position.get(), size.get());
	}
	
	public boolean Contains(float x, float y)
	{
		if(x <= Right() && x >= Left() && y <= Bot() && y >= Top())
			return true;	
		return false;
	}
	
	Rectangle snippet;
	public boolean Overlap(float x , float y)
	{
		snippet = new Rectangle(new Vector(position.x, position.y),new Vector(size.x,8));
		if(snippet.Contains(x, y))
			return true;
		else
			return false;
	}
	public boolean Overlap(float x , float y, float offset)
	{
		snippet = new Rectangle(new Vector(position.x - offset, position.y),new Vector(size.x, 8));
		if(snippet.Contains(x, y))
			return true;
		else
			return false;
	}

}
