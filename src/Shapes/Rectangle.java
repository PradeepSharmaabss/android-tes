package Shapes;
import com.example.androidproject.Finger;
import com.example.androidproject.Vector;

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
	public Rectangle(Vector v, Vector v2, float angle) {
		super();
		this.type = "rectangle";
		this.position = v;
		this.size = v2;
		this.angle = angle;
		System.out.println(angle);
		matrix.setRotate(this.angle, position.x, position.y);
		
	}
	public void Draw(Canvas c)
	{
		super.Draw(c);
		c.save(Canvas.MATRIX_SAVE_FLAG);
		c.setMatrix(matrix);
		c.drawRect(Left(), Top(), Right(), Bottom(), paint);
		c.restore();
	}
	
	public Vector translate(Vector v)
	{
		float[] pts = new float[2];
		pts[0] = v.x;
		pts[1] = v.y;
		matrix.mapPoints(pts);
		//System.out.println(""+angle);
		return new Vector(pts[0], pts[1]);
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
		return new Rectangle(position,size);
	}
}
