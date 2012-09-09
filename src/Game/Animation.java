package Game;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Animation {

	int frame = 0;
	int draws = 0;
	List<Bitmap> frames = new ArrayList<Bitmap>();
	public Animation(List<Bitmap> x)
	{
		this.frames = x;
	}
	public void GetFrame()
	{
		
	}
	public void Draw(Canvas c, Player p)
	{
		//System.out.println(""+frame +"<"+frames.size());
		if(draws < 20)
		{
			draws++;
		}
		else
		{
			try
			{
				if(frame < frames.size()-1)
				{
				c.drawBitmap(frames.get(frame), null, p.rect, new Paint());
				frame++;
				}
				else frame =0;
			}
			catch(Exception e)
			{
				frame = 0;
			}
		}
		
		
	}
}
