package Game;
import com.example.warlockgame.RenderThread;

import HUD.Button;
import Input.Finger;
import Tools.Drawable;
import Tools.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;


public abstract class GameObject extends Drawable{
	
	public Object Sender = null;
	public GameObject owner = null;
	public int id = 0;
	public String type = "default";
	public RectF rect;
	float maxChange = (float)1;
	boolean hit = false;
	public boolean AI = true,shoot = false;
	public Vector 
		position,
		size,
		velocity,
		acceleration,
		destination,
		feet;
	
	int curPhase;
	protected int maxPhases;
	public boolean jumping = false, grounded = false;
	protected float maxVelocity;
	public float health = 100, armour = 0, resist = 0;
	public Button currButton = null;
	public enum ActionState{shoot};
	public ActionState action;
	public GameObject(Object sender)
	{
		this();
		Sender = (Object)sender;
	}
	public void WithinIsoTile()
	{
		RenderThread.l.onTile(position);
	}
	public static void getMouse()
	{
		
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
		feet = new Vector(position.x+size.x/2,position.y-size.y);
	}
	
	public void Draw(Canvas c)
	{
		feet = new Vector(position.x+size.x/2,position.y-size.y);
		super.Draw(c,rect);
	}
	static Vector isoCoordsForPoint(Vector point,int[][] map) {
		float tw = 64;//tileSize_.width;
		float th = 64;//tileSize_.height;
		float mw = map[0].length;//mapSize_.width;
		float mh = 24;//mapSize_.height;

		int posY = (int) (mh - point.x/tw + mw/2 - point.y/th);
		int posX = (int) (mh + point.x/tw - mw/2 - point.y/th);

		return (new Vector(posX, posY));
	}
	static Vector test(Vector touch)
	{
		
		int ts = 40;
		int isoy = (int) ((((touch.y/ ts) + (touch.x - (10 * ts/2)) / ts) - 10) *-1);
		int isox = (int) ((((touch.y/ ts) - (touch.x - (10 * ts/2)) / ts) -10) *-1);
		return new Vector(isox,isoy);
	}
	public void Update()
	{
		Physics();
	
		position = position.add(velocity);
		CollideScreen();
		if(destination != null&&!hit)
		{
			GoTo(destination);
		}
		hit = false;
		if(Finger.down == true && Finger.position.y < RenderThread.size.y && action == null && type.equals("archie") && !Finger.fired)
		{
			StartTo(Finger.position);
		}	
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
		

		if(action != null && Finger.position.y < RenderThread.size.y)
		{
			if(action == ActionState.shoot && Finger.down == true)
			{
				Shoot(Finger.position);
				Finger.fired = true;
				action = null;
			}
		}
	
	}
	public void StartTo(Vector Dest)
	{
		destination = new Vector(Dest.x-16,Dest.y-64);
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
		if(rect.right > RenderThread.size.x )
			velocity.x = -10;
			if( rect.left < 0 )
			velocity.x = 10;
		if(rect.top<0)
			velocity.y = 10;
			if(rect.bottom>RenderThread.size.y)
			velocity.y = -10;
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
			setNull();
			velocity = new Vector(0,0);
		}
	}
	public void setNull()
	{
		destination = null;
	}
	public void Collision(GameObject obj)
	{
		if(obj.owner!=null)
		{
			if(obj.type.equals("projectile") && obj.owner.id != id)
			{
			ProjectileHit(obj.velocity.get());
				//RenderThread.delObject(obj.id);
			}
		}
	}
	public void ProjectileHit(Vector v)
	{
		if(!this.type.equals("projectile"))
		{
		paint.setColor(Color.RED);
		velocity=v.add(velocity);
		debug = true;
		hit = true;
		}
	}
	public void Input(int item){
		switch(item)
		{
		case 0:
			action = ActionState.shoot;
			break;
		default:
			action = null;
			break;
		}
	}
	public void Shoot(Vector Dest) {
		// TODO Auto-generated method stub
		
	}
}
