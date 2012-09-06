package Game;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.androidproject.Vector;

public abstract class GameObject {
	public Vector position,
		size,
		velocity;
	
	public Paint paint;
	public GameObject()
	{
		position = new Vector(0,0);
		size = new Vector(50,50);
		velocity = new Vector(0,0);
		paint = new Paint();
	}
	public void Draw(Canvas c)
	{
		
	}
	public void Update()
	{
		
	}
	public void Gravity()
	{
		velocity.y+= 0.2;
	}
}
