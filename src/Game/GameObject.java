package Game;

import Shapes.Rectangle;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.androidproject.Screen;
import com.example.androidproject.Vector;

public abstract class GameObject {
	public String type = "default";
	public Rectangle rect;
	public boolean AI = true;
	public Vector 
		position,
		size,
		velocity,
		maxVelocity;
	public boolean jumping = false, grounded = false;
	
	public float health = 100, armour = 0, resist = 0;
	
	
	public Paint paint;
	public GameObject()
	{
		position = new Vector(0,0);
		size = new Vector(50,50);
		velocity = new Vector(0,0);
		paint = new Paint();
		maxVelocity = new Vector(15,15);
		rect=new Rectangle(position,size);
	}
	public void Draw(Canvas c)
	{
		
		CollideScreen();
		position = position.add(velocity);
		
		rect.Draw(c, paint);
		
		rect.position = position;
		Physics();
	}
	public void Update()
	{
		
	}
	
	public void Physics()
	{	
		if(!grounded)
			velocity.y += 0.5;
		if(grounded && !AI && !Screen.buttonDown)
			velocity.x *= 0.95;
	}
	public void jump()
	{
		if(jumping == false)
		{
			grounded = false;
			velocity.y = -10;
			jumping = true;
		}
	}
	
	public void CollideScreen()
	{
		if(rect.Right() > Screen.size.x ||rect.Left()< 0)
			velocity.x = -velocity.x;
			
	}
}
