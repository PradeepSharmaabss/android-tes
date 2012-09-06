package Game;

import com.example.androidproject.Vector;

import android.graphics.Canvas;

public class Arrow extends GameObject{
	
	GameObject owner;
	public Arrow(GameObject obj)
	{
		super();
		owner = obj;
		position = owner.position.get();
	}
	
	public void Draw(Canvas c)
	{
		super.Draw(c);
		
	}
	public void Fire(Vector v)
	{
		//float difx = owner.position.x > v.x ? owner.position.y - v.x : v.x - owner.position.x;
		float dify = v.y - owner.position.y;
		
		float difx = v.x - owner.position.x;
		
		velocity = new Vector((difx/30)+owner.velocity.x, (dify/30)+owner.velocity.y);
	}
}
