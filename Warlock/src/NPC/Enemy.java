package NPC;
import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.Projectile;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.RectF;


public class Enemy extends GameObject{
	List<Vector> d = new ArrayList<Vector>();
	Bitmap bitmap;
	int x = 0;
	
	public Enemy()//Bitmap bmp)
	{
		super();
		d.add(new Vector(100,0));
		d.add(new Vector(0,50));
		d.add(new Vector(50,100));
		d.add(new Vector(100,50));
		bitmap = bmp;
		rect = new RectF(0,0,100,100);
		position = new Vector(0,0);
		destination = new Vector(0,0);
		size = new Vector(100, 100);
	}
	//@Override
	public void setNull()
	{
		
	}
	public void Update()
	{
		if(position.x == destination.x && position.y==destination.y)
		{
			RenderThread.addObject(new Projectile(position,RenderThread.gameObjects.get(0).position.get(),this));
			x+=1;
			if(x > 3)
			{
				x=0;
			}
			destination = new Vector(d.get(x).x,d.get(x).y);
			   
		}
		super.Update();
	}
}