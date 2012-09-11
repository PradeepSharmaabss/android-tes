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
	public List<Bitmap> left,idle,right,up,down;
	
	int frame =0;
	Bitmap bitmap1,bitmap2,curr;
	int timer = 0,timer2 =0 ;
	boolean shoot = false;
	public SpriteSheet spriteSheet;
	Vector ballpos = new Vector(45,45),ballvel = new Vector(0,2);
	
	
	public Archie(Bitmap bmp, Bitmap bmp2)
	{
		super();
		idle = new ArrayList<Bitmap>();
		idle.add(bmp);
		idle.add(bmp2);
		left = new ArrayList<Bitmap>();
		curr = bitmap1;
		rect = new RectF(0,0,100,100);
		position = new Vector(0,0);
		size = new Vector(100, 100);
		super.type = "archie";
		super.Sender = this;
	}
	public Archie(SpriteSheet spriteSheet)
	{
		super();
		this.spriteSheet = spriteSheet;
		curr = this.spriteSheet.tiles.get(0);
		/*idle = new ArrayList<Bitmap>();
		idle.add(bmp);
		idle.add(bmp2);
		left = new ArrayList<Bitmap>();
		curr = bitmap1;
		rect = new RectF(0,0,100,100);
		position = new Vector(0,0);
		size = new Vector(100, 100);*/
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
			canvas.drawBitmap(idle.get(0), null ,rect, paint);
		}
		else
		{
			canvas.drawBitmap(curr, null, rect, paint);
		}
		canvas.drawCircle(ballpos.x, ballpos.y, 10, paint);
	}
	Vector destination;
	void GoTo(Vector d)
	{
		float distanceX = d.x -position.x;
		float distanceY = d.y -position.y;
		float totalDist= Math.abs(distanceX) +Math.abs( distanceY);
	
		if(totalDist >maxVelocity)
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
		if(destination!=null)
		{
			GoTo(destination);
		}
		super.Update();

		ballpos.x += ballvel.x;
		ballpos.y += ballvel.y;
	
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);

	//	Log.d("mx","maxphases:" + maxPhases );
		curr = spriteSheet.tiles.get(0);
	}
	public void Animate()
	{
		if(timer < 4)
		{
			if(velocity.x < 0)
			{
				if(frame < left.size())
					curr = left.get(frame);
				else if (left.size()>0)
				{
					curr = left.get(0);
					frame=0;//reset to 0
				}
			}
			else
			{
				curr = idle.get(0);
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
