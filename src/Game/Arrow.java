package Game;

import com.example.androidproject.Vector;

import android.graphics.Canvas;
/***
 * summary this class should be used from a player/monster class using "this" with the constructor to set ownership. 
 * cooldowns should also be set for the player.
 * @author Zac
 *
 */
public class Arrow extends GameObject{
	
	GameObject owner;
	public Arrow(GameObject obj)
	{
		super();
		type = "arrow";
		owner = obj;
		position = owner.position.get();
		size = new Vector(15,15);
		rect.size = size;
	}
	
	public void Draw(Canvas c)
	{
		super.Draw(c);
		
	}
	public void Fire(Vector v)
	{
		float dify = v.y - owner.position.y;
		float difx = v.x - owner.position.x;
		velocity = new Vector((difx/30)+owner.velocity.x, (dify/30)+owner.velocity.y);
	}
}
