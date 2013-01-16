package NPC;
import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;


public class Archie extends GameObject
{
	int frame = 0;
	List<Bitmap> left, right, up, down, downleft, downright, upright, upleft;
	int timer = 0, timer2 =0 ;
	public SpriteSheet spriteSheet;
	public Vector center;
	
	public Archie(SpriteSheet spriteSheet)
	{
		super();
		
		super.type = "archie";
		super.owner = this;
		position = new Vector(0, 0);
		size = new Vector(100,100);
		
		this.spriteSheet = spriteSheet;
		this.spriteSheet.Load(size);
		right = new ArrayList<Bitmap>(); 
		left = new ArrayList<Bitmap>();
		down = new ArrayList<Bitmap>();
		up = new ArrayList<Bitmap>();
		downleft = new ArrayList<Bitmap>();
		downright= new ArrayList<Bitmap>();
		upright= new ArrayList<Bitmap>();
		upleft= new ArrayList<Bitmap>();
		for(int x= 0;x < 7;x++)
			left.add(spriteSheet.tiles.get(x));
		for(int x=7;x < 14;x++)
			upleft.add(spriteSheet.tiles.get(x));
		for(int x=14;x < 21;x++)
			up.add(spriteSheet.tiles.get(x));
		for(int x=21;x < 28;x++)
			upright.add(spriteSheet.tiles.get(x));
		for(int x=28;x < 35;x++)
			right.add(spriteSheet.tiles.get(x));
		for(int x=35;x < 42;x++)
			downright.add(spriteSheet.tiles.get(x));
		for(int x=42;x < 49;x++)
			down.add(spriteSheet.tiles.get(x));
		for(int x=49;x < 56;x++)
			downleft.add(spriteSheet.tiles.get(x));
		curr = this.spriteSheet.tiles.get(0);
		rect = new RectF(0,0,100,100);

		paint.setTextSize(30);
		paint.setColor(Color.BLACK);
		center = new Vector(RenderThread.size.x / 2 - size.x / 2,RenderThread.size.y / 2 - size.y / 2);
		shadowPaint = new Paint();
		BlurMaskFilter blurFilter = new BlurMaskFilter(30, BlurMaskFilter.Blur.INNER);
		shadowPaint.setMaskFilter(blurFilter);
		paint.setAntiAlias(true); 
		//paint.setShadowLayer(10, 50, -50, Color.argb(100, 0, 0, 0)); 
	}

	@Override
	public void Draw(Canvas canvas)
	{
		super.Draw(canvas);
		if(curr!=null)
			canvas.drawBitmap(curr, position.x, position.y, paint);
		paint.setColor(Color.WHITE);
		//canvas.drawText(""+angleInDegrees +",", rect.left, rect.top, paint);
		DrawHealthBar(canvas);
		// canvas.drawRect(new RectF(position.x, position.y,position.x+4,position.y+4), paint);
	}
	private void DrawHealthBar(Canvas c)
	{
		Paint s = new Paint();
		s.setColor(Color.BLACK);
		c.drawRect(rect.left-2, rect.top-2, rect.right+2, rect.top+10+2, s);
		s.setColor(Color.GRAY);
		c.drawRect(rect.left, rect.top, rect.right, rect.top+10, s);
		if((float)this.health/(float)this.maxhealth<0.2)
		{
		s.setColor(Color.RED);
		}
		else
		{
			if((float)this.health/(float)this.maxhealth<0.5)
			{
			s.setColor(Color.YELLOW);
			}
			else
			{
				s.setColor(Color.GREEN);
			}
		}
		c.drawRect(rect.left, rect.top, rect.right-((1-((float)this.health/(float)this.maxhealth))*rect.width()), rect.top+10, s);
	}
	public void Update()
	{
		super.Update();
		rect = new RectF(position.x, position.y,position.x + size.x, position.y+size.y);
		Animate();
		//RenderThread.l.onTile(new Vector(position.x + rect.width()/2, position.y + rect.height()));
	}
	double angleInDegrees =0;
	public void Animate()
	{
		if(destination!=null)
		{
		float deltaY = Math.abs(destination.y) - Math.abs(feet.y);
		float deltaX = Math.abs(destination.x) - Math.abs(feet.x);
	 angleInDegrees =  Math.atan2(deltaY, deltaX) * 180 / Math.PI+180;
		}
		if(timer < 7)
		{
			if(angleInDegrees>= 157.5&&angleInDegrees<202.5)
			{
				if(frame < right.size())
					curr = right.get(frame);
				else if (right.size() > 0)
				{
					curr = right.get(0);
					frame=0;//reset to 0
				}
			}
			else if(angleInDegrees>=112.5&&angleInDegrees<157.5)
			{
				if(frame < upright.size())
					curr = upright.get(frame);
				
				else if (upright.size() > 0)
				{
					curr = upright.get(0);
					frame=0;//reset to 0
				}
			}
			else if(angleInDegrees>=202.5&&angleInDegrees<247.5)
			{
				if(frame < downright.size())
					curr = downright.get(frame);
				
				else if (downright.size() > 0)
				{
					curr = downright.get(0);
					frame=0;//reset to 0
				}
			}
			else if(angleInDegrees>=247.5&&angleInDegrees<292.5)
			{
				if(frame < down.size())
					curr = down.get(frame);
				
				else if (down.size() > 0)
				{
					curr = down.get(0);
					frame=0;//reset to 0
				}
			}
			else if(angleInDegrees>=292.5&&angleInDegrees<337.5)
			{
				if(frame < downleft.size())
					curr = downleft.get(frame);
				
				else if (downleft.size() > 0)
				{
					curr = downleft.get(0);
					frame=0;//reset to 0
				}
			}
			else if (angleInDegrees<22.5||angleInDegrees>=337.5)
			{
				if(frame < left.size())
					curr = left.get(frame);
				
				else if (left.size() > 0)
				{
					curr = left.get(0);
					frame=0;//reset to 0
				}
			}
			else if(angleInDegrees>=22.5&&angleInDegrees<67.5)
			{
				if(frame < upleft.size())
					curr = upleft.get(frame);
				
				else if (upleft.size() > 0)
				{
					curr = upleft.get(0);
					frame=0;//reset to 0
				}
			}
			else if (angleInDegrees>=67.5&&angleInDegrees<112.5)
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
