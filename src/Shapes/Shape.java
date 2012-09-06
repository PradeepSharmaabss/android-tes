package Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.androidproject.Vector;

public abstract class Shape {
	public Vector position,size;
	public Paint paint;
	public String type = "NULL";
	public String text = "none";
	public float angle = 0;
	
	public Shape()
	{
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(45);
	}
	
	public void Draw(Canvas canvas)
	{
		
	}
	
	public void Update()
	{
		
	}
	
	public float Left() { return this.position.x; }
	public float Right() { return this.position.x + this.size.x; }
	public float Top() { return this.position.y; }
	public float Bottom() { return this.position.y + this.size.y; }
	public float Bot() { return Bottom(); }
	
	public boolean Contains(float x, float y)
	{
		if(x <= Right() && x >= Left() && y <= Bot() && y >= Top())
			return true;	
		return false;
	}
	Rectangle snippet;
	public boolean Overlap(float x , float y)
	{
		snippet = new Rectangle(new Vector(position.x,position.y),new Vector(size.x,10));
		if(snippet.Contains(x, y))
			return true;
		else
			return false;
	}
}
