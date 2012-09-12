package Game;


import com.example.warlockgame.RenderThread;

import Tools.Vector;

public class Projectile extends GameObject {
	int life = 100;
	public Projectile(Vector _from, Vector _to,GameObject shooter)
	{
		super();
		owner = shooter;
		type = "projectile";
		Vector from = _from.get();
		Vector to = _to.get();
		
		position = from;
		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
		velocity=new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
	}
	public void Update()
	{
		if(life>0)
		{
		super.Update();
		life--;
		}
		else
		{
			//RenderThread.delObject(this.id);
		}
		
	}
}
