package Game;

import Tools.Vector;

public class Projectile extends GameObject {
	public Projectile(Vector _from, Vector _to)
	{
		super();
		Vector from = _from.get();
		Vector to = _to.get();
		
		position = from;
		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
		velocity=new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
	}

}
