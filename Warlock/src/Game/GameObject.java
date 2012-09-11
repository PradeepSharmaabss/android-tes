package Game;
import Tools.Drawable;
import Tools.Screen;
import Tools.Vector;
import android.graphics.RectF;


public abstract class GameObject extends Drawable{
	
	public Object Sender = null;
	public int id = 0;
	public String type = "default";
	public RectF rect,feet;
	float maxChange = (float)2;
	public boolean AI = true;
	public Vector 
		position,
		size,
		velocity,
		acceleration,
		destination;
	int curPhaase;
	protected int maxPhases;
	public boolean jumping = false, grounded = false;
	protected float maxVelocity;
	public float health = 100, armour = 0, resist = 0;
	
	public GameObject(Object sender)
	{
		this();
		Sender = (Object)sender;
	}
	public GameObject()
	{
		super();
		position = new Vector(0,0);
		size = new Vector(50,50);
		velocity = new Vector(0,0);
		acceleration = new Vector(1,1);
		maxVelocity = 15;
		
		rect = new RectF(position.x, position.y, size.x, size.y);
		feet = new RectF(position.x,position.y,size.x,5);
	}
	public void Shoot(){}
	public void Command(int id)
	{
		switch(id)
		{
		case 0:
			Shoot();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}
	
	public void Draw(Object obj)
	{
		//CollideScreen();
		feet = new RectF(Screen.size.x/2,position.y, Screen.size.x/ 2 + size.x, Screen.size.y/2+1);
		
		
		super.Draw(obj,rect);
	}
	public void Update()
	{
		Physics();
		if(destination!=null)
		{
			GoTo(destination);
		}
		position = position.add(velocity);
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
		
	}

	public void Physics()
	{
		//if(!grounded)
			//velocity.y += 0.5;
		if(grounded && !AI )//&& !Screen.buttonDown)
		{
			velocity.x *= 0.95;
			if(velocity.x < 0.0001 || velocity.x > -0.0001)
				velocity.x = 0;
		}
		if("arrow".equals(type) && grounded)
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
		if(rect.right > Screen.size.x || rect.left < 0 )
			velocity.x = -velocity.x;
	}
	protected void GoTo(Vector d)
	{
		
		float distanceX = d.x -position.x;
		float distanceY = d.y -position.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
	
		if(totalDist > maxVelocity)
		{
			Vector newvelocity=new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
			if(Math.abs(newvelocity.x-velocity.x)>maxChange)	
			{
				if(newvelocity.x>velocity.x)
				newvelocity.x = velocity.x+maxChange;
				else
					newvelocity.x = velocity.x-maxChange;
			}
			if(Math.abs(newvelocity.y-velocity.y)>maxChange)	
			{
				if(newvelocity.y>velocity.y)
				newvelocity.y = velocity.y+maxChange;
				else
					newvelocity.y = velocity.y-maxChange;
			}
			velocity = newvelocity;
		}
		else
		{
			position = destination;
			destination = null;
			velocity = new Vector(0,0);
		}
	}
}
