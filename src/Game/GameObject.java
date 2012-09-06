package Game;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.androidproject.Vector;

public abstract class GameObject {
	public Vector position,
		size,
		velocity,maxVelocity;
	public boolean jumping = false, grounded = false;
	
	public Paint paint;
	public GameObject()
	{
		position = new Vector(0,0);
		size = new Vector(50,50);
		velocity = new Vector(0,0);
		paint = new Paint();
		maxVelocity = new Vector(5,5);
	}
	public void Draw(Canvas c)
	{
		
	}
	public void Update()
	{
		
	}
	public void Gravity()
	{	
		if(!grounded)
			velocity.y += 0.5;
	}
}
