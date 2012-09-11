package NPC;


import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.DisplayMetrics;

public class Archie extends GameObject{
	public List<Bitmap> left,idle,right,up,down;
	
	int frame =0;
	Bitmap bitmap1,bitmap2,curr;
	int timer = 0,timer2 =0 ;
	boolean shoot = false;
	Vector ballpos = new Vector(45,45),ballvel = new Vector(0,2);
	public Archie(Bitmap bmp, Bitmap bmp2)
	{
		idle = new ArrayList<Bitmap>();
		idle.add(bmp);
		idle.add(bmp2);
		left = new ArrayList<Bitmap>();
		curr = bitmap1;
		rect = new RectF(0,0,100,100);
		position = new Vector(0,0);
		size = new Vector(100, 100);
	}
	Bitmap flip(Bitmap d)
	{
	    Matrix m = new Matrix();
	    m.preScale(-1, 1);
	    Bitmap src = d;
	    Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
	    dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
	    return dst;
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
	public void Update()
	{
		super.Update();

		ballpos.x += ballvel.x;
		ballpos.y += ballvel.y;
		rect = new RectF(position.x, position.y, position.x + size.x, position.y + size.y);
		if(timer < 10)
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
			timer =0;
			frame++;//next frame
		}
		
	}
}
