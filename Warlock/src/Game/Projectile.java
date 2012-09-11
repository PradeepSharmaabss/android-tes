package Game;

import Input.Finger;
import Tools.Vector;

public class Projectile extends GameObject {
	public Projectile(Vector pos)
	{
		super();
		position = pos.get();
		
		float distanceX = Finger.position.x -position.x;
		float distanceY = Finger.position.y -position.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
		velocity=new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
	}

}
