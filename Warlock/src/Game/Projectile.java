package Game;

import android.graphics.Canvas;
import Tools.Vector;

public class Projectile extends GameObject {
	public Projectile(Vector pos, Vector vel)
	{
		velocity = vel;
		position = pos;
	}

}
