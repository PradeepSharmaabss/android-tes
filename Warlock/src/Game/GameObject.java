package Game;
import com.example.warlockgame.RenderThread;

import Input.Finger;
import Spells.EarthquakeSpell;
import Spells.LightningSpell;
import Spells.Spell;
import Spells.WallSpell;
import Tools.Vector;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public abstract class GameObject {

	public GameObject owner = null;
	public Bitmap curr = null;
	public RectF rect;
	public Paint paint, shadowPaint;
	public int id = 0, health = 1000, armour = 0, resist = 0,curPhase,maxhealth=health,mana=0;
	public boolean jumping = false, grounded = false , shadow = true, AI = true, shoot = false,hit = false, isMoving = false;
	public Type ObjectType;

	
	protected float acceleration = 0.4f, maxVelocity;
	public Vector 
		position,
		size,
		velocity,
		destination,
		feet;
	public Spell[] Spells;
	protected int maxPhases;
	
	public enum ActionState{shoot};
	public ActionState action;
	public GameObject(GameObject owner)
	{
		this();
		owner = this.owner;
		
		//Sender = (Object)sender;
		paint.setColor(Color.RED);
	}
	public GameObject()
	{
		ObjectType = Type.GameObject;
		position = new Vector(0,0);
		size = new Vector(50,50);
		velocity = new Vector(0,0);
		maxVelocity = 15;
		Spells = new Spell[10];
		paint = new Paint();
		paint.setColor(Color.RED);
		//if(shadow)
		//{
			shadowPaint = new Paint();
			shadowPaint.setColor(Color.BLACK);
			shadowPaint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.INNER));
		//}
		for(int x = 0; x < 10;x++)
		{
			Spells[x] = new Spell(this);
			if(x == 1)
				Spells[x] = new LightningSpell(this);
			if(x == 2)
				Spells[x] = new EarthquakeSpell(this);
			if(x == 3)
				Spells[x] = new WallSpell(this);
 		}
		rect = new RectF(position.x, position.y, position.x+size.x,position.y+ size.y);
		feet = new Vector(position.x+size.x/2,position.y-size.y);
	}

	public void Damage(float dmgDealt)
	{		if(dmgDealt>health)
			health=0;
		else
			health-=dmgDealt;
	}
	public boolean Intersect(RectF PassedObj)
	{
		
		if(RectF.intersects(rect,PassedObj))
		{
			return true;
		}
		return false;
	}

	
	public void Draw(Canvas canvas)
	{
		
		canvas.save();
		canvas.translate(rect.left+rect.width()/2,rect.top-rect.height()/2);
		canvas.rotate(45);
		if(curr!=null)
			canvas.drawBitmap(curr.extractAlpha(), null, new RectF(size.x/2,0,rect.width()+size.x/3,rect.height()),shadowPaint);
		else
			canvas.drawRect(new RectF(size.x/2,0,rect.width()+size.x/3,rect.height()), shadowPaint);
		canvas.restore();
		if(curr==null)
			canvas.drawRect(rect, paint);
		//super.Draw(canvas,rect);
		
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
		feet = new Vector(position.x+size.x/2,position.y+size.y);
		Physics();
		if(!RenderThread.l.platform.Within(feet))
		{
			Damage(3);
		}
		position = position.add(velocity);
		//CollideScreen();
		if(destination != null && !hit)
		{
			GoTo(destination);
		}
		hit = false;
		if(Finger.down == true &&  Finger.position.y < RenderThread.size.y && action == null && ObjectType.equals(Type.Player) && !Finger.fired)
		{
			StartTo(new Vector(feet.x+(Finger.position.x-RenderThread.size.x/2), feet.y+(Finger.position.y-RenderThread.size.y/2)));
		}	
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
		
		for(Spell s : Spells)
		{
			s.Update();
		}
	
	}
	public void StartTo(Vector Dest)
	{
//		if(destination.x<RenderThread.l.bounds.right)
//			if(destination.x>=0)
//				if(destination.y>=0)
//					if(destination.y<RenderThread.l.bounds.bottom)
							destination = new Vector( Dest.x,Dest.y);
	}
	public void Physics()
	{
		//if(!grounded)
			//velocity.y += 0.5;
		if(grounded && !AI )//&& !Screen.buttonDown)
		{
			velocity.x *= 0.95;
			if(velocity.x < 0.0001 || velocity.x > -0.0001)
			{
				velocity.x = 0;
				isMoving = false;
			}
			else if(!isMoving)
			{
				isMoving = true;
			}
				
		}
//		if("arrow".equals(type) && grounded)
//			velocity.x *= 0.95;
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
		if(position.x+size.x > RenderThread.l.bounds.right)
			velocity.x = -10;
		if(position.x < RenderThread.l.bounds.left)
			velocity.x = 10;
		if(position.y + size.y < RenderThread.l.bounds.top)
			velocity.y = 10;
		if(position.y+size.y > RenderThread.l.bounds.bottom)
			velocity.y = -10;
	}
	protected void GoTo(Vector d)
	{
		float distanceX = d.x -feet.x;
		float distanceY = d.y -feet.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
	
		if(totalDist > maxVelocity)
		{
			Vector newvelocity=new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
			if(Math.abs(newvelocity.x-velocity.x)>acceleration)	
			{
				if(newvelocity.x>velocity.x)
				newvelocity.x = velocity.x+acceleration;
				else
					newvelocity.x = velocity.x-acceleration;
			}
			if(Math.abs(newvelocity.y-velocity.y)>acceleration)	
			{
				if(newvelocity.y>velocity.y)
				newvelocity.y = velocity.y+acceleration;
				else
					newvelocity.y = velocity.y-acceleration;
			}
			velocity = newvelocity;
		}
		else
		{
			feet = destination;
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
		switch(obj.ObjectType)
		{
		case Projectile:
			if(obj.owner.id!=id)
			{
				this.ProjectileHit(obj.velocity);
			RenderThread.delObject(obj.id);
	
			}
			break;
		case GameObject:
			if(owner!=null)
			if(obj.id!=owner.id)
			{
				Vector Tempvel = this.velocity.get();
				Vector Tempvel2 = obj.velocity.get();
			//obj.ProjectileHit(this.velocity);
			this.velocity = Tempvel;
		//	ProjectileHit(Tempvel2);
			}
			break;
		case Enemy:
			if(owner!=null)
				if(obj.id!=owner.id)
				{
					Vector Tempvel = this.velocity.get();
					Vector Tempvel2 = obj.velocity.get();
			//	obj.ProjectileHit(this.velocity);
				this.velocity = Tempvel;
			//	ProjectileHit(Tempvel2);
				}
			break;
		}
	}
	public void ProjectileHit(Vector v)
	{
	
			paint.setColor(Color.RED);
			velocity=v.add(velocity);
			position.add(velocity.get());
		
		
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
	public Vector getCenter()
	{
		return new Vector(rect.left+rect.width()/2,rect.top + rect.height()/2);
	}
	
}
