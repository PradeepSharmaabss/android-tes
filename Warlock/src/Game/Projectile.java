package Game;


import android.util.Log;

import com.example.warlockgame.RenderThread;

import Tools.Vector;

public class Projectile extends GameObject {
	public Projectile(Vector _from, Vector _to, GameObject shooter)
	{
		super();
		health = 100;
		owner = shooter;
		ObjectType = Type.Projectile;
		Vector from = _from.get();
		Vector to = _to.get();
		
		position = from;
		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
		velocity = new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
		
	}
	public void Collision(GameObject obj)
	{
		switch(obj.ObjectType)
		{
		case Projectile:
			if(obj.owner.id!=owner.id)
			{
			RenderThread.delObject(obj.id);
			RenderThread.delObject(this.id);
			}
			break;
		case GameObject:
			if(obj.id!=owner.id)
			{
			obj.ProjectileHit(this.velocity);
			RenderThread.delObject(this.id);
			}
			break;
		case Enemy:
			if(obj.id!=owner.id)
			{
			obj.ProjectileHit(this.velocity);
			RenderThread.delObject(this.id);
			}
			break;
		case LineSpell:
			RenderThread.delObject(this.id);
			break;
		}
	
		
	}
	public void Update()
	{
		if(health > 0)
		{
			super.Update();
			health--;
		}
		else
			RenderThread.delObject(this.id);
		
	}
}
