package NPC;
import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.LightningBolt;
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
	public Vector center;
	public Archie(SpriteSheet spriteSheet)
	{
		super();
		super.type = "archie";
		super.owner = this;
		
		this.spriteSheet = spriteSheet;
		left = new ArrayList<Bitmap>();
		right = new ArrayList<Bitmap>(); 
		down = new ArrayList<Bitmap>();
		up = new ArrayList<Bitmap>();
		for(int x= 0;x < 7;x++)
			left.add(spriteSheet.tiles.get(x));
		for(int x=7;x < 14;x++)
			right.add(spriteSheet.tiles.get(x));
		for(int x=14;x < 21;x++)
			down.add(spriteSheet.tiles.get(x));
		for(int x=21;x < 28;x++)
			up.add(spriteSheet.tiles.get(x));
		curr = this.spriteSheet.tiles.get(0);
		rect = new RectF(0,0,100,100);
		position = new Vector(0, 0);
		size = new Vector(100,100);

		debug = false;
		center = new Vector(RenderThread.size.x / 2 - size.x / 2,RenderThread.size.y / 2 - size.y / 2);
	}

	@Override
	public void Draw(Canvas canvas)
	{
		canvas.drawBitmap(curr, null ,rect, paint);

		canvas.drawText(""+angleInDegrees, rect.left, rect.top, paint);
		// canvas.drawRect(new RectF(position.x, position.y,position.x+4,position.y+4), paint);
	}
	
	public void Update()
	{
		super.Update();
		rect = new RectF(position.x, position.y,position.x + size.x, position.y+size.y);
		Animate();
		RenderThread.l.onTile(new Vector(position.x + rect.width()/2, position.y + rect.height()));
	}
	double angleInDegrees =0;
	public void Animate()
	{
		if(destination!=null)
		{
		float deltaY = Math.abs(position.y) - Math.abs(destination.y);
		float deltaX = Math.abs(position.x) - Math.abs(destination.x);
	 angleInDegrees =  Math.atan2(deltaY, deltaX) * 180 / Math.PI;
		}
		if(timer < 4)
		{
			if(angleInDegrees+45< -90)
			{
				if(frame < right.size())
					curr = right.get(frame);
				else if (left.size() > 0)
				{
					curr = right.get(0);
					frame=0;//reset to 0
				}
			}
			else if(angleInDegrees+45<0)
			{
				if(frame < down.size())
					curr = down.get(frame);
				
				else if (down.size() > 0)
				{
					curr = down.get(0);
					frame=0;//reset to 0
				}
			}
			else if (angleInDegrees+45<90)
			{
				if(frame < left.size())
					curr = left.get(frame);
				
				else if (left.size() > 0)
				{
					curr = left.get(0);
					frame=0;//reset to 0
				}
			}
			else if (angleInDegrees+45<180)
			{
				if(frame < up.size())
					curr = up.get(frame);
				
				else if (up.size() > 0)
				{
					curr = up.get(0);
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
