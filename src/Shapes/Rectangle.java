package Shapes;
import com.example.androidproject.Finger;
import com.example.androidproject.Vector;

import Game.Player;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;

public class Rectangle extends Shape {

	public Vector topright = new Vector();
	public Vector topleft = new Vector();
	public Matrix matrix = new Matrix();
	public Rectangle(Vector v, Vector v2) {
		super();
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
		super.Draw(c);
		c.save(Canvas.MATRIX_SAVE_FLAG);
		c.setMatrix(matrix);
		c.drawRect(Left(), Top(), Right(), Bottom(), paint);
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
}
