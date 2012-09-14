package NPC;
import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.Projectile;
import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;


public class Archie extends GameObject
{
	int frame =0;
	Bitmap curr;
	List<Bitmap> left,right,up,down;
	int timer = 0, timer2 =0 ;
	public SpriteSheet spriteSheet;
	
	List<Projectile> projectiles;

	public Archie(SpriteSheet spriteSheet,Bitmap _iso)
	{
		super();
		iso = _iso;
		this.spriteSheet = spriteSheet;
		left = new ArrayList<Bitmap>();
		right = new ArrayList<Bitmap>(); 
		down = new ArrayList<Bitmap>();
		for(int x= 0;x < 7;x++)
			left.add(spriteSheet.tiles.get(x));
		for(int x=7;x < 14;x++)
			right.add(spriteSheet.tiles.get(x));
		for(int x=14;x < 21;x++)
			down.add(spriteSheet.tiles.get(x));
		curr = this.spriteSheet.tiles.get(0);
		rect = new RectF(0,0,100,100);
		position = new Vector(0,0);
		size = new Vector(100,100);
		super.type = "archie";
		projectiles =new ArrayList<Projectile>();
		super.Sender = this;
		debug = false;
	}

	@Override
	public void Draw(Canvas canvas)
	{
		canvas.drawBitmap(curr, null ,rect, paint);

		for(Projectile p : projectiles)
		{
			p.Draw(canvas);
		}
	}
	
	public void Update()
	{
		super.Update();
		rect = new RectF(position.x, position.y-size.y, position.x + size.x, position.y);
		Animate();
		WithinIsoTile(position,RenderThread.l.map);
		getMouse(RenderThread.l.map);
	}
	public void Shoot(Vector Dest)
	{
		RenderThread.addObject(new Projectile(position.get(), Dest.get(),null));
	}
	
	public void Animate()
	{
		if(timer < 4)
		{
			if(velocity.x < 0)
			{
				if(frame < left.size())
					curr = left.get(frame);
				else if (left.size() > 0)
				{
					curr = left.get(0);
					frame=0;//reset to 0
				}
			}
			else if (velocity.x > 0)
			{
				if(frame < right.size())
					curr = right.get(frame);
				
				else if (right.size() > 0)
				{
					curr = right.get(0);
					frame=0;//reset to 0
				}
			}
			timer++;
		}
		else 
		{
			timer = 0;
			frame++;//next frame
		}
	}
}
