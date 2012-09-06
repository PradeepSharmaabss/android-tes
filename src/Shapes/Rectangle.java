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
	float[] points = new float[4];
	public Rectangle(Vector v, Vector v2, float angle) {
		super();
		this.type = "rectangle";
		this.position = v;
		this.size = v2;
		this.angle = angle;
		System.out.println(angle);
		matrix.setRotate(this.angle, position.x, position.y);
		points[0] = Left();
		points[1] = Top();
		points[2] = Right();
		points[3] = Top();
		matrix.mapPoints(points);
	}
	public void Draw(Canvas c)
	{
		super.Draw(c);
		new Text().Draw(c, new Vector(points[2],points[3]));
		c.save(Canvas.MATRIX_SAVE_FLAG);
		c.setMatrix(matrix);
		c.drawRect(Left(), Top(), Right(), Bottom(), paint);
		c.restore();
		float xlength =  points[2] - points[0];
		float ylength = points[1] > points[3] ? points[1] - points[3]: points[3] - points[1];
		float test = xlength / 2;
		float test2 = ylength / 2;
		
		
		if(angle != 0)System.out.println(test+","+test2);
		new Text().Draw(c,new Vector(points[0]+test, points[1] - test2));
	}
	
	public float translate(Vector v)
	{
		float xlength = points[2] - points[0];
		float ylength = points[3] - points[1];
		float test = xlength / 2;
		float test2 = ylength / 2;
		//System.out.println(""+angle);
		return 5;
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
