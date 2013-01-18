package NPC;
import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.Type;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;


public class Enemy extends GameObject{
	List<Vector> d = new ArrayList<Vector>();
	Bitmap bitmap;
	int x = 0;
	
	public Enemy()//Bitmap bmp)
	{
		super();
		ObjectType = Type.Enemy;
		d.add(new Vector(100,0));
		d.add(new Vector(0,50));
		d.add(new Vector(50,100));
		d.add(new Vector(100,50));
		//bitmap = bitmap;
		rect = new RectF(0,0,100,100);
		position = new Vector(0,0);
		destination = new Vector(0,0);
		size = new Vector(100, 100);
	}
	//@Override
	public void setNull()
	{
		
	}
	int tmptimer = 0;
	public void Update()
	{
		if(tmptimer < 100)
		{
			tmptimer++;
		}
		else 
		{
			System.out.println("test");
			Spells[0].Cast(RenderThread.archie.getCenter());
			tmptimer = 0;
		}
		//System.out.println("update");
		if(position.x == destination.x && position.y==destination.y)
		{
			//RenderThread.addObject(new Projectile(position, RenderThread.gameObjects.get(0).position.get(),this));
			x+=1;
			if(x > 3)
			{
				x=0;
			}
			destination = new Vector(d.get(x).x, d.get(x).y);
			   
		}
		super.Update();
	}
	public void Draw(Canvas canvas)
	{
		//RenderThread.gameObjects.add()
		Vector p1 = RenderThread.archie.getCenter(),
				p2 = getCenter();
		paint.setColor(Color.GREEN);
		canvas.drawLine(p2.x, p2.y, p1.x, p1.y, paint);
		paint.setColor(Color.BLUE);
		canvas.drawLine(p2.x, p2.y, destination.x, destination.y, paint);

		paint.setColor(Color.WHITE);
		canvas.drawLine(p2.x, p2.y, p2.x+30*velocity.x, p2.y+30*velocity.y, paint);
		super.Draw(canvas);
	}
}