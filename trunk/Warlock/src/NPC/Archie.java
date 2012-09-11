package NPC;
import java.util.ArrayList;
import java.util.List;
import Game.GameObject;
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
	boolean shoot = false;
	public SpriteSheet spriteSheet;
	Vector destination;

	public Archie(SpriteSheet spriteSheet)
	{
		super();
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
		size = new Vector(100, 100);
		super.type = "archie";
		super.Sender = this;
	}
	
	public void StartTo(Vector Dest)
	{
		destination=new Vector(Dest.x-16,Dest.y-64);
	}
	public void Draw(Canvas canvas)
	{
		if(velocity.x>0)
		{
			canvas.drawBitmap(curr, null ,rect, paint);
		}
		else
		{
			canvas.drawBitmap(curr, null, rect, paint);
		}
	}
	
	void GoTo(Vector d)
	{
		float distanceX = d.x -position.x;
		float distanceY = d.y -position.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
	
		if(totalDist > maxVelocity)
		{
			velocity=new Vector(maxVelocity*(distanceX/totalDist),maxVelocity*distanceY/totalDist);
		}
		else
		{
			position = destination;
			destination = null;
			velocity = new Vector(0,0);
		}
	}
	public void Update()
	{
		super.Update();
		if(destination!=null)
		{
			GoTo(destination);
		}
	
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
		Animate();
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
