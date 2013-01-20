package SpellProjectiles;

import com.example.warlockgame.RenderThread;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import Game.GameObject;
import Tools.Vector;

public class Meteor extends Projectile {
	int height=400;
	public Meteor(Vector _from, Vector _to, GameObject shooter) {
		super(_from, _to, shooter);
		health =110;
		size=new Vector(150,150);
		maxVelocity = 4;
		Vector from = _from.get();
		Vector to = _to.get();
		paint.setColor(Color.CYAN);

		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
		velocity = new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
		position = new Vector(to.x-velocity.x*20,to.y-velocity.y*20);
		// TODO Auto-generated constructor stub
	}
	public void Update()
	{
super.Update();
if(height>0)
height -=4;
if(health <=10)
{
	size = new Vector(250,250);
}
	}
	
	public void Collision(GameObject obj)
	{
		if(health==10){
		switch(obj.ObjectType)
		{
		case Projectile:
			if(obj.owner.id!=owner.id)
			{
		//	RenderThread.delObject(obj.id);
			obj.ProjectileHit(this.velocity);
			}
			break;
		case GameObject:
			if(obj.id!=owner.id)
			{
			obj.ProjectileHit(this.velocity);
			}
			break;
		case Enemy:
			if(obj.id!=owner.id)
			{
			obj.ProjectileHit(this.velocity);
			}
			break;
		case LineSpell:
	
			break;
		case Meteor:
			if(obj.id!=owner.id)
			{
				if(obj.health==3)
					RenderThread.delObject(this.id);
			}
			break;
		}
	
		}
	}
	public boolean Intersect(RectF PassedObj)
	{
		if(health<=10)
	return super.Intersect(PassedObj);
	return false;
	}

	public void Draw(Canvas c)
	{

			c.drawCircle(position.x,position.y, size.x/3,shadowPaint);
			c.drawCircle(position.x, position.y-height, size.x/3, paint);
			
		

	}

}

