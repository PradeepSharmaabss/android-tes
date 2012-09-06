package Shapes;
import com.example.androidproject.Finger;
import com.example.androidproject.Vector;

import android.graphics.Canvas;
import android.graphics.Color;

public class Rectangle extends Shape {

	public Rectangle(Vector v, Vector v2) {
		super();
		this.type = "rectangle";
		this.position = v;
		this.size = v2;

	}
	
	public void Draw(Canvas c)
	{
		super.Draw(c);
		c.drawRect(Left(), Top(), Right(), Bottom(), paint);
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
	public Rectangle get()
	{
		return new Rectangle(position,size);
	}
}
