package Game;

import com.example.warlockgame.RenderThread;

import android.graphics.Canvas;
import android.graphics.RectF;
import Tools.Vector;

public class Meteor extends Projectile {
	int height=400;
	public Meteor(Vector _from, Vector _to, GameObject shooter) {
		super(_from, _to, shooter);
		health =20;
		size=new Vector(150,150);
		// TODO Auto-generated constructor stub
	}
	public void Update()
	{
super.Update();
height -=20;
if(health <=3)
{
	size = new Vector(250,250);
}
	}
	
	public void Collision(GameObject obj)
	{
		if(health==3){
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
		if(health<=3)
	return super.Intersect(PassedObj);
	return false;
	}

	public void Draw(Canvas c)
	{

			c.drawCircle(position.x,position.y, size.x/3,shadowPaint);
			c.drawCircle(position.x, position.y-height, size.x/3, paint);
			
		

	}

}

