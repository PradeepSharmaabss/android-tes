package Game;

import java.util.ArrayList;
import java.util.List;

import com.example.androidproject.Vector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Animation {

	int frame = 0;
	int draws = 0;
	List<Bitmap> frames = new ArrayList<Bitmap>();
	Paint paint;
	public Animation(List<Bitmap> x)
	{
		this.frames = x;
		paint = new Paint();
	}
	public Bitmap GetFrame(Vector velocity)
	{
		if(draws < 1)
		{
			draws++;
			return frames.get(frame);//return same frame until next should be drawn
		}
		else
		{
			draws = 0;
			if(frame < frames.size()-1 && frame >= 0)
			{
				
				return frames.get(frame++);
			}
			else
			{
				frame = 0;
				return frames.get(0);
			}
			
			
		}
	}
	public void Draw(Canvas c, Player p)
	{
		//System.out.println(""+frame +"<"+frames.size());
		
		if(draws < 100)
		{
			draws++;
		}
		else 
		{
			try
			{
				
				if(frames!= null && frame < frames.size())
				{
					//System.out.println(""+(frames) +" "+ p.rect);
					c.drawBitmap(frames.get(frame), null, p.rect, paint) ;
					frame++;
				}
				else 
				{
					frame =0;
				}

			}
			catch(Exception e)
			{
				//System.out.println(""+e.getLocalizedMessage());
				frame = 0;
			}
			draws =0;
		}
		
		
	}
}
